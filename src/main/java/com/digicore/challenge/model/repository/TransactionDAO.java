package com.digicore.challenge.model.repository;/*
 * @author Okala III
 */

import com.digicore.challenge.model.entity.Account;
import com.digicore.challenge.model.entity.Transactions;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class TransactionDAO {

    public static HashMap<Integer, Transactions> transactions = new HashMap<>();

    public static boolean save(Transactions transaction){
        transactions.put(transactions.size()+1,transaction);
        return true;
    }

    public static List<Transactions> findAll(){
        List<Transactions> allTransactions = new ArrayList<>();
        for(Integer key : transactions.keySet()){
            Transactions transaction = TransactionDAO.transactions.get(key);
            allTransactions.add(transaction);
        }
        return allTransactions;
    }
}
