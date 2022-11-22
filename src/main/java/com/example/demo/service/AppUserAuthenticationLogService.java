package com.example.demo.service;

import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserAuthenticationLog;
import com.example.demo.repository.AppUserAuthenticationLogRepository;
import com.example.demo.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserAuthenticationLogService {

    private final AppUserAuthenticationLogRepository authenticationLogRepository;
    private final AppUserRepository appUserRepository;

    public void saveAttempt(String appUser, boolean isSuccess) {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(appUser);
        if (appUserOptional.isPresent()) {
            AppUserAuthenticationLog appUserAuthenticationLog = new AppUserAuthenticationLog(isSuccess, LocalDateTime.now(), appUserOptional.get());
            authenticationLogRepository.save(appUserAuthenticationLog);
        }
    }
}
