package com.Learning.SCEP_Backend.dto.Auth;

import com.Learning.SCEP_Backend.enums.Roles;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;

    private Long studentId;

    private Roles roles;
}
