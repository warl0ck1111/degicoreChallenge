package com.digicore.challenge.model.entity;/*
 * @author Okala III
 */

import com.digicore.challenge.model.repository.AccountDAO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class Transactions {
    private String id;
    private Date transactionDate;
    private String transactionType;// (Deposit or Withdrawal);
    private String narration;
    private Double amount;
    private Double accountBalance;// (after the transaction)

    public Transactions() {
        id =  generateTransactionId();
    }

    public Transactions( String transactionType, String narration, Double amount, Double accountBalance) {
        id =  generateTransactionId();
        this.transactionDate = new Date();
        this.transactionType = transactionType;
        this.narration = narration;
        this.amount = amount;
        this.accountBalance = accountBalance;
    }

    String generateTransactionId() {
        String s = UUID.randomUUID().toString().replace("-", "");
        char[] tokens = new char[s.length()];
        s.getChars(0, s.length(), tokens, 0);
        StringBuilder transactionId = new StringBuilder();
        for (Character c : tokens) {
            if (transactionId.length() == 4)
                break;
            if (Character.isAlphabetic(c))
                continue;
            if (Character.isDigit(c))
                transactionId.append(c);
        }
        System.out.println(transactionId);
        return transactionId.toString();

    }

    double getAccountBalance(String accountNumber){
        return AccountDAO.accounts.get(accountNumber).getBalance();
    }



    public static void main(String[] args) {
        Transactions t = new Transactions();

    }
}
