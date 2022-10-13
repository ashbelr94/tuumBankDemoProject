package com.tuum.bank.accountservice;

import com.tuum.bank.accountservice.Dto.AccountCreateDto;
import com.tuum.bank.accountservice.Enum.Currency;
import com.tuum.bank.accountservice.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
		initialData();
	}

	private static AccountService accountService;

	@Autowired
	public AccountServiceApplication(AccountService accountService) {
		this.accountService = accountService;
	}

	private static void initialData(){
		AccountCreateDto createDto = new AccountCreateDto();
				createDto.setUserId("DemoUser");
		createDto.setBalance(BigDecimal.valueOf(2500));
		createDto.setCurrency(Currency.GBP.toString());
		createDto.setCountry("UK");
		accountService.createAccount(createDto);
	}
}
