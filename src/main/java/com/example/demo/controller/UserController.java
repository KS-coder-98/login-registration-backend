package com.example.demo.controller;

import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.service.token.ChangePasswordTokenService;
import com.example.demo.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserController {

    private final RegistrationService registrationService;
    private final ChangePasswordTokenService changePasswordTokenService;

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
    public ResponseEntity<String> setNewPassword(@RequestBody ChangePasswordDto token) {
        try {
            String msg = changePasswordTokenService.setNewPassword(token.getToken(), token.getNewPassword());
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("failed to change password");
        }
    }

}
