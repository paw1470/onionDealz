package com.onion.dealz.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
    private String login;
    private String password;
    private String description;
    private String photo;
}
