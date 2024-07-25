package com.Learning.SCEP_Backend.dto.Auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
