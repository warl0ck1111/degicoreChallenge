package com.digicore.challenge.model.service;

import com.digicore.challenge.model.dto.CreateAccountDTO;
import com.digicore.challenge.model.dto.DepositDTO;
import com.digicore.challenge.model.dto.RequestDto;
import com.digicore.challenge.model.dto.WithdrawDTO;
import com.digicore.challenge.model.entity.Account;
import com.digicore.challenge.model.entity.Transactions;
import com.digicore.challenge.model.repository.AccountDAO;
import com.digicore.challenge.model.repository.TransactionDAO;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class AccountService {

    public Account createAccount(CreateAccountDTO dto){
        String accountPassword = dto.getAccountPassword();
        String accountName = dto.getAccountName();

        if(accountName.isBlank()) throw new IllegalArgumentException("Account name can not be empty");
        if(accountPassword.isBlank()) throw new IllegalArgumentException("Account password can not be empty");

        Account account = new Account(accountName,accountPassword);
        AccountDAO.save(account);
        return account;
    }

    public Account deposit(DepositDTO dto){
        String accountNumber = dto.getAccountNumber();
        Double amount = dto.getAmount();
        if(accountNumber.length() != 10) throw new IllegalArgumentException("Invalid Account Number. Account number must be exactly 10 digits");
        if(accountNumber.isBlank()) throw new IllegalArgumentException("Account number can not be Empty");
        if(amount < 1 || amount > 1000000) throw new IllegalArgumentException("Invalid Amount");

        Account account = AccountDAO.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("Invalid Account number"));
        account.deposit(amount);
        AccountDAO.save(account);
        Transactions transaction = new Transactions("DEPOSIT", "mNarration", amount, account.getBalance());
        TransactionDAO.save(transaction);
        return account;
    }

    public Account withdraw(WithdrawDTO dto){
        String accountNumber = dto.getAccountNumber();
        Double withdrawnAmount = dto.getWithdrawnAmount();
        String accountPassword = dto.getAccountPassword();

        if(accountNumber.isBlank()) throw new IllegalArgumentException("Account name can not be Empty");
        if(accountNumber.length() != 10) throw new IllegalArgumentException("Invalid Account Number. Account number must be exactly 10 digits");
        if(withdrawnAmount == null || withdrawnAmount.isNaN() || withdrawnAmount < 1) throw new IllegalArgumentException("invalid withdrawal amount.");
        if(accountPassword.isBlank()) throw new IllegalArgumentException("account Password can not be Empty");

        Account account = AccountDAO.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("Invalid Account number"));

        if(!account.getAccountPassword().equals(accountPassword)) throw new IllegalArgumentException(("Invalid Password"));

        if(!canWithdraw(accountNumber,withdrawnAmount))
           throw new IllegalArgumentException("Insufficient Balance");
        else{
            account.withdraw(withdrawnAmount);
            AccountDAO.save(account);
            Transactions transaction = new Transactions("WITHDRAW", "mNarration", withdrawnAmount, account.getBalance());
            TransactionDAO.save(transaction);
            return account;
        }
    }



    public Account accountInfo(RequestDto dto){
        String accountNumber = dto.getAccountNumber();
        String accountPassword = dto.getAccountPassword();

        if(accountNumber.length() != 10) throw new IllegalArgumentException("Invalid Account Number. Account number must be exactly 10 digits");
        if(accountNumber.isBlank()) throw new IllegalArgumentException("account number can not be blank");
        if(accountPassword.isBlank()) throw new IllegalArgumentException("account password can not be blank");

        return AccountDAO.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("Invalid Account number"));
    }


    public boolean canWithdraw(String accountNumber, double amount){
        if(accountNumber.length() != 10) throw new IllegalArgumentException("Invalid Account Number. Account number must be exactly 10 digits");
        Optional<Account> account = AccountDAO.findByAccountNumber(accountNumber);
        return account.filter(value -> value.getBalance() < amount).isPresent();

    }
}
