package com.digicore.challenge.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AccountStatementDTO {

    private Date transactionDate;

    private String transactionType;  //(Deposit or Withdrawal)

    private String narration;

    private Double amount;

    private Double accountBalance; //(after the transaction
}
