package com.example.demo.model.token;

import com.example.demo.model.appuser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChangePasswordToken extends BasicToken {

    @SequenceGenerator(
            name = "change_password_token_sequence",
            sequenceName = "change_password_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "change_password_token_sequence"
    )
    private Long id;

    public ChangePasswordToken(String token,
                               LocalDateTime createdAt,
                               LocalDateTime expiresAt,
                               AppUser appUser) {
        super(token, createdAt, expiresAt, appUser);
    }
}
