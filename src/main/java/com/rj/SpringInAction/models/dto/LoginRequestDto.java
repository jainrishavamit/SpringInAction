package com.rj.SpringInAction.models.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
