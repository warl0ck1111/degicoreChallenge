package com.digicore.challenge.model.repository;/*
 * @author Okala III
 */

import com.digicore.challenge.model.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AccountDAO {

    public static HashMap<String, Account> accounts = new HashMap<>();

    public Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    public boolean accountExists(String account){
        log.debug("Account exists");
        return accounts.containsKey(account);
    }


    public boolean save(Account account) {
        accounts.put(account.getAccountNumber(), account);
            log.debug("Account created");
        return true;
    }


}
