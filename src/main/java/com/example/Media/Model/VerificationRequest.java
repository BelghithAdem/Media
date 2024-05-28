package com.example.Media.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificationRequest {

    private String email;
    private String code;
}
