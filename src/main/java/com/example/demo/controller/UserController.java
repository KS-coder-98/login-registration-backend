package com.example.demo.controller;

import com.example.demo.dto.AppUserAuthenticationLogDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.mapper.AppUserAuthenticationLogMapper;
import com.example.demo.model.appuser.AppUserAuthenticationLog;
import com.example.demo.service.AppUserAuthenticationLogService;
import com.example.demo.service.token.ChangePasswordTokenService;
import com.example.demo.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final RegistrationService registrationService;
    private final ChangePasswordTokenService changePasswordTokenService;
    private final AppUserAuthenticationLogService authenticationLogService;
    private final AppUserAuthenticationLogMapper logMapper;

    @PostMapping(path="register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequestDto request) {
        registrationService.register(request);
        return ResponseEntity.ok("Success, check email");
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @GetMapping(path="change/password")
    public ResponseEntity<String> changePassword(@RequestParam("email") String email){
        changePasswordTokenService.changePassword(email);
        return ResponseEntity.ok("Success, check email");
    }

    @PostMapping(path = "setNewPassword")
    public ResponseEntity<String> setNewPassword(@RequestBody ChangePasswordDto passwordDto) {
        try {
            String msg = changePasswordTokenService.setNewPassword(passwordDto.getToken(), passwordDto.getNewPassword());
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("failed to change password");
        }
    }

    @GetMapping(path = "log")
    public ResponseEntity<List<AppUserAuthenticationLogDto>> getLogs(HttpServletRequest request){
        Principal userPrincipal = request.getUserPrincipal();
        List<AppUserAuthenticationLog> attempt = authenticationLogService.getAttempt(userPrincipal.getName());
        return ResponseEntity.ok(logMapper.mapToList(attempt));
    }

}
