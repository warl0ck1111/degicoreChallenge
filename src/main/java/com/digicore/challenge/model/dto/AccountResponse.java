package com.digicore.challenge.model.dto;/*
 * @author Okala III
 */

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class AccountResponse {
    private int responseCode;
    private boolean success;
    private String message;
}
