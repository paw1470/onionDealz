package com.onion.dealz.api.model.dto;

import lombok.Data;

@Data
public class UserDetailsDto {
    private Long id;
    private String username;
    private boolean isAdmin;
}
