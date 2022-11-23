package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppUserAuthenticationLogDto {

    private Boolean isSuccessful;
    private LocalDateTime whenAttempt;
}
