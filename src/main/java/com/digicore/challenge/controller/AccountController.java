package com.digicore.challenge.controller;/*
 * @author Okala III
 */

import com.digicore.challenge.model.dto.*;
import com.digicore.challenge.model.entity.Account;
import com.digicore.challenge.model.entity.Transactions;
import com.digicore.challenge.model.repository.AccountDAO;
import com.digicore.challenge.model.service.AccountService;
import com.digicore.challenge.model.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountService accountService = new AccountService();

    private TransactionService transactionService;

    @PostMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountDTO dto){


        Account account = accountService.createAccount(dto);
        return new ResponseEntity<>(new AccountResponse(200,true,String.format("Account Created Successfully. Your account number is %s", account.getAccountNumber())), HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositDTO dto){

        Account account = accountService.deposit(dto);
        return new ResponseEntity<>(new AccountResponse(200,true,String.format("Deposit Successful. New Account balance is %s ", account.getBalance())), HttpStatus.OK);
    }


    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawDTO dto){
        Account account = accountService.withdraw(dto);
        return new ResponseEntity<>(new AccountResponse(200,true,String.format("Withdraw Successful, New Account Balance is %s",account.getBalance())), HttpStatus.OK);
    }

    @PostMapping("/account_info")
    public ResponseEntity<?> accountInfo(@RequestBody RequestDto dto){
        Account account = accountService.accountInfo(dto);
        return new ResponseEntity<>(new AccountInfoDTO(200,true,"See Object for details",account), HttpStatus.OK);

    }

    @PostMapping("/account_statement")
    public ResponseEntity<?> accountStatus(@RequestBody RequestDto dto){
        List<Transactions> transactionsList = transactionService.findAll();
        return new ResponseEntity<>(transactionsList, HttpStatus.OK);
    }

}
