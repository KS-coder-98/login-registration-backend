package com.example.demo.registration;

import com.example.demo.appuser.AppUserService;
import com.example.demo.registration.token.ChangePasswordTokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserController {

    private final RegistrationService registrationService;
    private final ChangePasswordTokenService changePasswordTokenService;
//    private final AppUserService appUserService;

    @PostMapping(path="register")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @GetMapping(path="change/password")
    public void changePassword(@RequestParam("email") String email){
        changePasswordTokenService.changePassword(email);
    }

    @PostMapping(path = "changePassword")
    public void confirm(@RequestBody ChangePasswordDto token) {
        try {
            changePasswordTokenService.setNewPassword(token.getToken(), token.getNewPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
