package com.digicore.challenge.model.entity;/*
 * @author Okala III
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import static com.digicore.challenge.model.repository.AccountDAO.nameExists;

@Slf4j
@Data
public class Account {

    private String accountName;

    private String accountNumber;

    @JsonIgnore
    private String accountPassword;

    private double balance = 0.00;



    public Account() {
        accountNumber = generateAccountNumber();
    }

    public Account(String accountName, String accountPassword) {
        setAccountName(accountName);
        this.accountNumber = generateAccountNumber();
        this.accountPassword = accountPassword;

    }

    public boolean deposit(double amount){
//        if (amount < 1 || amount > 1000000) throw new IllegalArgumentException("deposit amount should be in the range of 1 to 1 000 000");
        setBalance(getBalance() + amount);
        return true;
    }

    public boolean withdraw(double amount){
        setBalance(getBalance() - amount);
        return true;
    }


    public void setAccountName(String accountName) {
        if (nameExists(accountName))
            throw new IllegalArgumentException("Account Name Already Exists");
        this.accountName = accountName;

    }

    String generateAccountNumber() {
        String s = UUID.randomUUID().toString().replace("-", "");
        char[] tokens = new char[s.length()];
        s.getChars(0, s.length(), tokens, 0);
        StringBuilder accNo = new StringBuilder();
        for (Character c : tokens) {
            if (accNo.length() == 10)
                break;
            if (Character.isAlphabetic(c))
                continue;
            if (Character.isDigit(c))
                accNo.append(c);
        }
        System.out.println(accNo);
        return accNo.toString();

    }




    public static void main(String[] args) {

    }


}