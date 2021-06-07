package com.digicore.challenge.controller;/*
 * @author Okala III
 */

import com.digicore.challenge.model.dto.*;
import com.digicore.challenge.model.entity.Account;
import com.digicore.challenge.model.repository.AccountDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController("/api/account")
public class AccountController {

    private AccountDAO accountDAO;

    @PostMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountDTO dto){
        String accountPassword = dto.getAccountPassword();
        String accountName = dto.getAccountName();

        if(accountName.isBlank()) throw new IllegalArgumentException("account name can not be blank");
        if(accountPassword.isBlank()) throw new IllegalArgumentException("account password can not be blank");

        Account account = new Account(accountName,accountPassword);
        accountDAO.save(account);

        return new ResponseEntity<>(new AccountResponse(200,true,"Account Created Successfully"), HttpStatus.OK);


    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositDTO dto){
        String accountNumber = dto.getAccountNumber();
        Double amount = dto.getAmount();
        if(accountNumber.isBlank()) throw new IllegalArgumentException("Account name can not be blank");
        if(amount < 1 || amount > 1000000) throw new IllegalArgumentException("Invalid Amount");

        Account account = accountDAO.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("Invalid Account number"));
        account.deposit(amount);
        accountDAO.save(account);

        return new ResponseEntity<>(new AccountResponse(200,true,"Deposit Successful"), HttpStatus.OK);
//        return new ResponseEntity<>(new AccountResponse(500,true,"There was an Issue "), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/account_info")
    public ResponseEntity<?> accountInfo(@RequestBody RequestDto dto){
        String accountNumber = dto.getAccountNumber();
        String accountPassword = dto.getAccountPassword();


        if(accountNumber.isBlank()) throw new IllegalArgumentException("account number can not be blank");
        if(accountPassword.isBlank()) throw new IllegalArgumentException("account password can not be blank");

        Account account = accountDAO.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("Invalid Account number"));

        return new ResponseEntity<>(new AccountInfoDTO(200,true,"See Objcet for details",account), HttpStatus.OK);

    }
}
