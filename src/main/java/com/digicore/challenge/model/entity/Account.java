package com.digicore.challenge.model.entity;/*
 * @author Okala III
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

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

    public double deposit(double amount){
        if (amount < 1) throw new IllegalArgumentException("Invalid Amount");
        return getBalance() + amount;
    }


    public void setAccountName(String accountName) {
//        if (nameExists(accountName))
            this.accountName = accountName;
//        else throw new IllegalArgumentException("Name Already exists");
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

//    boolean nameExists(String name) {
//        {
//            for (String key : accounts.keySet())
//                if (accounts.get(key).getAccountName().equals(name))
//                    return true;
//        }
//
//        return false;
//    }

//    public boolean persistAccountDetails(Account account) {
//
//        accounts.put(account.getAccountNumber(), account);
//        log.info("Account created");
//        return true;
//    }

    public static void main(String[] args) {
        Account a = new Account("Bashir","password");

        Account a2 = new Account("onuche","password");
//        Account a3 = new Account("ibrahim","password");
//        Account a4 = new Account("okala","password");
//        a.persistAccountDetails(a);
//        a2.persistAccountDetails(a2);
//        a3.persistAccountDetails(a3);
//        a4.persistAccountDetails(a4);


//        for (String key : accounts.keySet())
//            System.out.println(key);
    }


}