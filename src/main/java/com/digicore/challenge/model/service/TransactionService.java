package com.digicore.challenge.model.service;

import com.digicore.challenge.model.entity.Transactions;
import com.digicore.challenge.model.repository.TransactionDAO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TransactionService {

    public List<Transactions> findAll(){
        return TransactionDAO.findAll();
    }
}
