package com.digicore.challenge.model.dto;/*
 * @author Okala III
 */

import com.digicore.challenge.model.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountInfoDTO { //TODO might change to DTO
    private int responseCode;
    private boolean success;
    private String message;

    private Account account;
}
