package com.digicore.challenge.model.dto;/*
 * @author Okala III
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateAccountDTO {
    private String accountName;
    private String accountPassword;

}
