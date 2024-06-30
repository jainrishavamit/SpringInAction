package com.rj.SpringInAction.models;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
