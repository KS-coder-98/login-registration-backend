package com.example.demo.service.token;

import com.example.demo.model.appuser.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.email.EmailSender;
import com.example.demo.model.token.ChangePasswordToken;
import com.example.demo.repository.token.ChangePasswordTokenRepository;
import com.example.demo.security.PasswordEncoder;
import com.example.demo.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChangePasswordTokenService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final ChangePasswordTokenRepository changePasswordTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final EmailSender emailSender;
    private final AppUserService appUserService;



    public void saveConfirmationToken(ChangePasswordToken token) {
        changePasswordTokenRepository.save(token);
    }

    public Optional<ChangePasswordToken> getToken(String token) {
        return changePasswordTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        changePasswordTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public String setNewPassword(String token, String password) {
        ChangePasswordToken confirmationToken = changePasswordTokenRepository.findByToken(token).orElse(null);
        if (confirmationToken != null && confirmationToken.getConfirmedAt() == null) {
            AppUser appUserChangingPassword = confirmationToken.getAppUser();
            String hashFromPassword = String.valueOf(password.hashCode());
            boolean passwordAlreadyUsed = appUserService.addPasswordToHistory(appUserChangingPassword, hashFromPassword);
            if ( !passwordAlreadyUsed ){
                return "Password already used";
            }
            String encodePassword = passwordEncoder.bCryptPasswordEncoder().encode(password);

            confirmationToken.getAppUser().setPassword(encodePassword);
            setConfirmedAt(token);
            appUserRepository.save(confirmationToken.getAppUser());
            return "Success";
        }
        return "Wrong token or token already used";
    }

    public void changePassword(String email) {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));

        String token = UUID.randomUUID().toString();


        ChangePasswordToken changePasswordToken = new ChangePasswordToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        emailSender.send(
                email,
                buildEmail(token, "link"));

        saveConfirmationToken(changePasswordToken);
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Code to restart your password</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Input this token to change your password " + name +
                //todo add link frontend page with form to set new password
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
