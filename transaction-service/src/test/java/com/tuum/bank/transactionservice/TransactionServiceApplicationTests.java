package com.tuum.bank.transactionservice;

import com.tuum.bank.transactionservice.Controller.TransactionController;
import com.tuum.bank.transactionservice.Dto.AccountClientResponseDto;
import com.tuum.bank.transactionservice.Dto.TransactionDto;
import com.tuum.bank.transactionservice.Enum.Currency;
import com.tuum.bank.transactionservice.Service.MapValidationErrorService;
import com.tuum.bank.transactionservice.Service.TransactionService;
import com.tuum.bank.transactionservice.dto.AccountCreateDto;
import com.tuum.bank.transactionservice.responseDto.AccountAndTransactionResponseDto;
import com.tuum.bank.transactionservice.responseDto.TransactionResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@WebMvcTest(controllers = TransactionController.class)
@AutoConfigureWebClient
class TransactionServiceApplicationTests {
	@MockBean
	private WebClient.Builder webTestClient;

	private static String baseUrl = "http://localhost:4000/api/v1";

	@MockBean
	private TransactionService transactionService;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MapValidationErrorService mapValidationErrorService;

	private String accountId = "";

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
	@DisplayName("Create Deposit Transaction by send POST resuest to /transaction/mybatis/createTransaction")
	public void CreateDepositTransaction(){
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


		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTransactionDirection("IN");
		transactionDto.setCurrency(String.valueOf(Currency.EUR));
		transactionDto.setAmount(BigDecimal.valueOf(200));
		transactionDto.setDescription("Im Depositing");
		transactionDto.setAccountId(accountsDto.getAccount().getAccountId());

		TransactionResponseDto responseDto = client.post()
				.uri(baseUrl+"/transaction/mybatis/createTransaction")
				.body(Mono.just(transactionDto), TransactionDto.class)
				.exchange()
				.expectBody(TransactionResponseDto.class)
				.returnResult().getResponseBody();

		this.accountId = responseDto.getAccountId();

		Assert.isTrue(1600 == responseDto.getBalance().intValue(), "Asserting Incremented Balance Amount");
		Assert.isTrue(responseDto.getCurrency().equals(accountsDto.getAccount().getCurrency()), "Asserting Currency");
		Assert.isTrue(transactionDto.getDescription().equals(responseDto.getDescription()), "Asserting Description");
		Assert.isTrue(transactionDto.getAccountId().equals(responseDto.getAccountId()), "Asserting Account Id");
	}

	@Test
	@DisplayName("Find All Transactions by account Id :: GET Request /transaction/getTransactionsByAccountId?accountId=")
	public void findAllTransactionsByAccountId(){

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


		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTransactionDirection("IN");
		transactionDto.setCurrency(String.valueOf(Currency.EUR));
		transactionDto.setAmount(BigDecimal.valueOf(200));
		transactionDto.setDescription("Im Depositing");
		transactionDto.setAccountId(accountsDto.getAccount().getAccountId());

		TransactionResponseDto responseDto = client.post()
				.uri(baseUrl+"/transaction/mybatis/createTransaction")
				.body(Mono.just(transactionDto), TransactionDto.class)
				.exchange()
				.expectBody(TransactionResponseDto.class)
				.returnResult().getResponseBody();

		this.accountId = responseDto.getAccountId();

		transactionDto.setTransactionDirection("OUT");
		transactionDto.setAmount(BigDecimal.valueOf(700));

		TransactionResponseDto responseDto2 = client.post()
				.uri(baseUrl+"/transaction/mybatis/createTransaction")
				.body(Mono.just(transactionDto), TransactionDto.class)
				.exchange()
				.expectBody(TransactionResponseDto.class)
				.returnResult().getResponseBody();

		Assert.isTrue(900 == responseDto2.getBalance().intValue(), "Asserting after withdrawal");

		AccountAndTransactionResponseDto accountAndTransactionResponseDto =
				client.get().uri(baseUrl+"/transaction/getTransactionsByAccountId?accountId="+accountsDto.getAccount().getAccountId())
						.exchange().expectBody(AccountAndTransactionResponseDto.class).returnResult().getResponseBody();

		Assert.isTrue(accountAndTransactionResponseDto.getTransactions().size() == 2,
				"Asserting 2 transactions for Account "+accountsDto.getAccount().getAccountId());
	}


	@Test
	@DisplayName("Find All Transactions by account Id for Webclient :: GET Request /transaction/webclient/getTransactionsByAccountId?accountId=")
	public void findAllTransactionsByAccountIdWebClient(){

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


		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTransactionDirection("IN");
		transactionDto.setCurrency(String.valueOf(Currency.EUR));
		transactionDto.setAmount(BigDecimal.valueOf(200));
		transactionDto.setDescription("Im Depositing");
		transactionDto.setAccountId(accountsDto.getAccount().getAccountId());

		TransactionResponseDto responseDto = client.post()
				.uri(baseUrl+"/transaction/mybatis/createTransaction")
				.body(Mono.just(transactionDto), TransactionDto.class)
				.exchange()
				.expectBody(TransactionResponseDto.class)
				.returnResult().getResponseBody();

		this.accountId = responseDto.getAccountId();

		transactionDto.setTransactionDirection("OUT");
		transactionDto.setAmount(BigDecimal.valueOf(700));

		TransactionResponseDto responseDto2 = client.post()
				.uri(baseUrl+"/transaction/mybatis/createTransaction")
				.body(Mono.just(transactionDto), TransactionDto.class)
				.exchange()
				.expectBody(TransactionResponseDto.class)
				.returnResult().getResponseBody();

		Assert.isTrue(900 == responseDto2.getBalance().intValue(), "Asserting after withdrawal");

		AccountAndTransactionResponseDto accountAndTransactionResponseDto =
				client.get().uri(baseUrl+"/transaction/webclient/getTransactionsByAccountId?accountId="+accountsDto.getAccount().getAccountId())
						.exchange().expectBody(AccountAndTransactionResponseDto.class).returnResult().getResponseBody();

		Assert.isTrue(accountAndTransactionResponseDto.getTransactions().size() == 2,
				"Asserting 2 transactions for Account "+accountsDto.getAccount().getAccountId());
	}

	@Test
	@DisplayName("Exceptions in creating transaction :: POST Request /transaction/mybatis/createTransaction")
	public void transactionException(){
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


		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTransactionDirection("IN");
		transactionDto.setDescription("Im Depositing");
		transactionDto.setAccountId(accountsDto.getAccount().getAccountId());

		Map<String, Map<String, String>> responseDto = client.post()
				.uri(baseUrl+"/transaction/mybatis/createTransaction")
				.body(Mono.just(transactionDto), TransactionDto.class)
				.exchange()
				.expectBody(Map.class)
				.returnResult().getResponseBody();

		Assert.isTrue(responseDto.get("errorMessage").size() == 2, "Asserting 2 Exception");

		TransactionDto transactionDto2 = new TransactionDto();
		transactionDto2.setTransactionDirection("IN");
		transactionDto2.setCurrency(String.valueOf(Currency.EUR));
		transactionDto2.setAmount(BigDecimal.valueOf(200));
		transactionDto2.setDescription("Im Depositing");
		transactionDto2.setAccountId("1234");


		Map<String, String> responseDto2 = client.post()
				.uri(baseUrl+"/transaction/mybatis/createTransaction")
				.body(Mono.just(transactionDto2), TransactionDto.class)
				.exchange()
				.expectBody(Map.class)
				.returnResult().getResponseBody();

		Assert.isTrue(responseDto2.get("errorMessage").equals("Invalid Account / Account not Found"),
				"Asserting Invalid Account Id");


		TransactionDto transactionDto3 = new TransactionDto();
		transactionDto3.setTransactionDirection("OUT");
		transactionDto3.setCurrency(String.valueOf(Currency.EUR));
		transactionDto3.setAmount(BigDecimal.valueOf(2200));
		transactionDto3.setDescription("Im Depositing");
		transactionDto3.setAccountId(accountsDto.getAccount().getAccountId());


		Map<String, String> responseDto3 = client.post()
				.uri(baseUrl+"/transaction/mybatis/createTransaction")
				.body(Mono.just(transactionDto3), TransactionDto.class)
				.exchange()
				.expectBody(Map.class)
				.returnResult().getResponseBody();

		String exception = "Insufficient Funds to make this withdrawal, Available Balance: "+accountsDto.getAccount().getBalance().toPlainString();
		Assert.isTrue(responseDto3.get("errorMessage").equals(exception),
				"Asserting Insufficient Fund Exception for Account Id "+accountsDto
						.getAccount().getAccountId());

	}


	@AfterEach
	public void cleanUp(){

		WebTestClient client = WebTestClient
				.bindToServer()
				.baseUrl(baseUrl)
				.build();

		String result = client.delete().uri(baseUrl+"/transaction/deleteTransactionByAccountId?accountId="+this.accountId)
				.exchange().expectBody(String.class).returnResult().getResponseBody();
		System.out.println("DELETE RESULT: "+result);
	}



}
