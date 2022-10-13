package com.tuum.bank.accountservice.util;

import com.tuum.bank.accountservice.Dto.AccountClientResponseDto;
import com.tuum.bank.accountservice.event.AccountEvent;
import com.tuum.bank.accountservice.example.model.Accounts;

public class EntityDtoUtil {

    public static AccountClientResponseDto toDto(Accounts account){
        AccountClientResponseDto responseDto = new AccountClientResponseDto();
        responseDto.setAccount(account);
        responseDto.setStatus("SUCCESS");
        return responseDto;
    }

    public static AccountEvent toEvent(Accounts account){
        AccountEvent event = new AccountEvent();
        event.setStatus("UPDATED");
        event.setAccount(account);
        event.setMessage(String.format("Account Balance has been updated: %s", account.getBalance()));
        return event;
    }
}
