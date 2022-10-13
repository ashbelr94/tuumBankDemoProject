package com.tuum.bank.accountservice;


import com.tuum.bank.accountservice.Controller.AccountsController;
import com.tuum.bank.accountservice.Dto.AccountBalanceUpdateDto;
import com.tuum.bank.accountservice.Dto.AccountClientResponseDto;
import com.tuum.bank.accountservice.Dto.AccountCreateDto;
import com.tuum.bank.accountservice.Enum.Currency;
import com.tuum.bank.accountservice.Service.AccountService;
import com.tuum.bank.accountservice.Service.MapValidationErrorService;
import com.tuum.bank.accountservice.example.model.Accounts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@WebMvcTest(controllers = AccountsController.class)
@AutoConfigureWebClient
public class AccountServiceControllerTest {

    @MockBean
    private WebClient.Builder webTestClient;

    private static String baseUrl = "http://localhost:4000/api/v1";

    @MockBean
    private AccountService accountServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MapValidationErrorService mapValidationErrorService;

    @BeforeEach
    public void init(){
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        String result = client.delete().uri(baseUrl+"/accounts/deleteAccount?userId=TestUser")
                .exchange().expectBody(String.class).returnResult().getResponseBody();
        System.out.println("RESULT: "+result);
    }

    @Test
    @DisplayName("Should Create Account making POST request = /api/v1/accounts/")
    public void shouldCreateAccount() throws Exception{
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        AccountCreateDto createDto = new AccountCreateDto();
        createDto.setUserId("TestUser");
        createDto.setCountry("UK");
        createDto.setBalance(BigDecimal.valueOf(1400));
        createDto.setCurrency(String.valueOf(Currency.EUR));

//        AccountClientResponseDto accountsDto = client
//                .get().uri("http://localhost:8080/api/v1/accounts/getAccount?accountId=650ce689-ba98-4014-b9f6-ba4dc9cbd32c")
//                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();


        AccountClientResponseDto accountsDto = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                        .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        System.out.println(accountsDto);

        Assert.isTrue(accountsDto.getAccount().getUserId().equals("TestUser"), "Asserting User Id");
        Assert.isTrue(accountsDto.getAccount().getCountry().equals("UK"),"Asserting Country Value");
        Assert.isTrue(accountsDto.getStatus().equals("SUCCESS"), "Asserting Response Status");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/mybatis/getAllAccounts"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }



    @Test
    @DisplayName("Should get ALl accounts registered using GET Request to /acounts/mybatis/getAllAccounts")
    public void shouldGetAllAccounts() throws Exception{
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        List<Accounts> accountsList = client
                .get().uri(baseUrl+"/accounts/mybatis/getAllAccounts")
                .exchange().expectBody(new ParameterizedTypeReference<List<Accounts>>() {
                }).returnResult().getResponseBody();

        System.out.println(accountsList);
        int count = accountsList.size();

        AccountCreateDto createDto = new AccountCreateDto();
        createDto.setUserId("TestUser");
        createDto.setCountry("UK");
        createDto.setBalance(BigDecimal.valueOf(1400));
        createDto.setCurrency(String.valueOf(Currency.EUR));

        AccountClientResponseDto accountsDto = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        List<Accounts> accountsList2 = client
                .get().uri(baseUrl+"/accounts/mybatis/getAllAccounts")
                .exchange().expectBody(new ParameterizedTypeReference<List<Accounts>>() {
                }).returnResult().getResponseBody();

        Assert.isTrue(count+1 == accountsList2.size(), "Assert count of all accounts registered");
    }


    @Test
    @DisplayName("Should Find Accounts by Id -  GET Request with request param to /accounts/getAccount?accountId=")
    public void findAccountByAccountIdTest(){
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        AccountCreateDto createDto = new AccountCreateDto();
        createDto.setUserId("TestUser");
        createDto.setCountry("UK");
        createDto.setBalance(BigDecimal.valueOf(1400));
        createDto.setCurrency(String.valueOf(Currency.EUR));

        AccountClientResponseDto accountsDto = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        AccountClientResponseDto accountWithId = client
                .get().uri(baseUrl+"/accounts/getAccount?accountId="+accountsDto.getAccount().getAccountId())
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();


        Assert.isTrue(accountsDto.getAccount().getAccountId().equals(accountWithId.getAccount().getAccountId()),"Asserting Account Id" );
        Assert.isTrue(accountsDto.getAccount().getUserId().equals("TestUser"), "Asserting User Id");
        Assert.isTrue(accountsDto.getAccount().getCountry().equals("UK"),"Asserting Country Value");
        Assert.isTrue(accountsDto.getStatus().equals("SUCCESS"), "Asserting Response Status");

    }

    @Test
    @DisplayName("Should Find Accounts by Id -  GET Request with request param to /accounts/webclient/getAccount?accountId=")
    public void findAccountByAccountIdWebclientTest(){
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        AccountCreateDto createDto = new AccountCreateDto();
        createDto.setUserId("TestUser");
        createDto.setCountry("UK");
        createDto.setBalance(BigDecimal.valueOf(1400));
        createDto.setCurrency(String.valueOf(Currency.EUR));

        AccountClientResponseDto accountsDto = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        AccountClientResponseDto accountWithId = client
                .get().uri(baseUrl+"/accounts/webclient/getAccount?accountId="+accountsDto.getAccount().getAccountId())
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        Assert.isTrue(accountsDto.getAccount().getAccountId().equals(accountWithId.getAccount().getAccountId()),"Asserting Account Id" );
        Assert.isTrue(accountsDto.getAccount().getUserId().equals("TestUser"), "Asserting User Id");
        Assert.isTrue(accountsDto.getAccount().getCountry().equals("UK"),"Asserting Country Value");
        Assert.isTrue(accountsDto.getStatus().equals("SUCCESS"), "Asserting Response Status");
    }


    @Test
    @DisplayName("Should Find Accounts by user Id -  GET Request with request param to /accounts/getAccountByUser?userId=")
    public void findAccountByUserIdTest(){
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        AccountCreateDto createDto = new AccountCreateDto();
        createDto.setUserId("TestUser");
        createDto.setCountry("UK");
        createDto.setBalance(BigDecimal.valueOf(1400));
        createDto.setCurrency(String.valueOf(Currency.EUR));

        AccountClientResponseDto accountsDto = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        AccountClientResponseDto accountWithId = client
                .get().uri(baseUrl+"/accounts/getAccountByUser?userId="+createDto.getUserId())
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        Assert.isTrue(accountsDto.getAccount().getAccountId().equals(accountWithId.getAccount().getAccountId()),"Asserting Account Id" );
        Assert.isTrue(accountsDto.getAccount().getUserId().equals("TestUser"), "Asserting User Id");
        Assert.isTrue(accountsDto.getAccount().getCountry().equals("UK"),"Asserting Country Value");
        Assert.isTrue(accountsDto.getStatus().equals("SUCCESS"), "Asserting Response Status");
    }

    @Test
    @DisplayName("Update Account Balance by patching Request to /accounts/updateBalance")
    public void updateAccountBalance(){
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        AccountCreateDto createDto = new AccountCreateDto();
        createDto.setUserId("TestUser");
        createDto.setCountry("UK");
        createDto.setBalance(BigDecimal.valueOf(1400));
        createDto.setCurrency(String.valueOf(Currency.EUR));

        AccountClientResponseDto accountsDto = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        AccountBalanceUpdateDto balanceUpdateDto = new AccountBalanceUpdateDto();
        balanceUpdateDto.setBalance(BigDecimal.valueOf(200));
        balanceUpdateDto.setAccountId(accountsDto.getAccount().getAccountId());

        AccountClientResponseDto updatedAccount = client
                .patch().uri(baseUrl+"/accounts/updateBalance").body(Mono.just(balanceUpdateDto), AccountBalanceUpdateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        Assert.isTrue(accountsDto.getAccount().getAccountId().equals(updatedAccount.getAccount().getAccountId()),"Asserting Account Id" );
        Assert.isTrue(accountsDto.getAccount().getUserId().equals("TestUser"), "Asserting User Id");
        Assert.isTrue(accountsDto.getAccount().getCountry().equals("UK"),"Asserting Country Value");
        Assert.isTrue(accountsDto.getStatus().equals("SUCCESS"), "Asserting Response Status");
        Assert.isTrue(200 == updatedAccount.getAccount().getBalance().intValue(), "Asserting balance amount");
    }


    @Test
    @DisplayName("Update Account Balance by patching Request to /accounts/webclient/updateBalance")
    public void updateAccountBalanceWebClient(){
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        AccountCreateDto createDto = new AccountCreateDto();
        createDto.setUserId("TestUser");
        createDto.setCountry("UK");
        createDto.setBalance(BigDecimal.valueOf(1400));
        createDto.setCurrency(String.valueOf(Currency.EUR));

        AccountClientResponseDto accountsDto = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        AccountBalanceUpdateDto balanceUpdateDto = new AccountBalanceUpdateDto();
        balanceUpdateDto.setBalance(BigDecimal.valueOf(200));
        balanceUpdateDto.setAccountId(accountsDto.getAccount().getAccountId());

        AccountClientResponseDto updatedAccount = client
                .patch().uri(baseUrl+"/accounts/webclient/updateBalance").body(Mono.just(balanceUpdateDto), AccountBalanceUpdateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        Assert.isTrue(accountsDto.getAccount().getAccountId().equals(updatedAccount.getAccount().getAccountId()),"Asserting Account Id" );
        Assert.isTrue(accountsDto.getAccount().getUserId().equals("TestUser"), "Asserting User Id");
        Assert.isTrue(accountsDto.getAccount().getCountry().equals("UK"),"Asserting Country Value");
        Assert.isTrue(accountsDto.getStatus().equals("SUCCESS"), "Asserting Response Status");
        Assert.isTrue(200 == updatedAccount.getAccount().getBalance().intValue(), "Asserting balance amount");
    }

    @Test
    @DisplayName("Catching Exception by passing invalid parameteres patching Request to /accounts/mybatis/createAccount")
    public void createAccountException(){
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        AccountCreateDto createDto = new AccountCreateDto();
        createDto.setUserId("TestUser");
        createDto.setCurrency(String.valueOf(Currency.EUR));

        AccountClientResponseDto accountsDto = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();


        Assert.isTrue(accountsDto.getStatus().equals("ERROR"), "Asserting Response Status");
        Assert.isTrue(accountsDto
                .getBindingResultErrorResponse()
                .getErrorMessage().size() == 2, "Asserting Binding Errors");

    }


    @Test
    @DisplayName("Catching Exception by creating existing account POST Request to /accounts/mybatis/createAccount")
    public void createAccountAlreadyExistsException(){
        WebTestClient client = WebTestClient
                .bindToServer()
                .baseUrl(baseUrl)
                .build();

        AccountCreateDto createDto = new AccountCreateDto();
        createDto.setUserId("TestUser");
        createDto.setCountry("UK");
        createDto.setBalance(BigDecimal.valueOf(1400));
        createDto.setCurrency(String.valueOf(Currency.EUR));

        AccountClientResponseDto accountsDto = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        AccountClientResponseDto account2 = client
                .post().uri(baseUrl+"/accounts/mybatis/createAccount").body(Mono.just(createDto), AccountCreateDto.class)
                .exchange().expectBody(AccountClientResponseDto.class).returnResult().getResponseBody();

        Assert.isTrue(account2.getStatus().equals("ERROR"), "Asserting Response Status");
        Assert.hasText(String
                .format("Cannot Create Account, Account already exists for user: %s",
                        createDto.getUserId()),account2.getCustomExceptionResponse().getMessage());
    }


}
