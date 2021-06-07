package com.digicore.challenge.model.repository;/*
 * @author Okala III
 */

import com.digicore.challenge.model.entity.Account;
import com.digicore.challenge.model.entity.Transactions;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

@Slf4j
public class TransactionDAO {

    public static HashMap<String, Transactions> transactions = new HashMap<>();

    boolean save(Transactions transactions){

        return true;
    }

    List<Transactions> findAll(){
        return null;
    }
}
