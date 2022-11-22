package com.example.demo.model.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUserAuthenticationLog {
    @SequenceGenerator(
            name = "app_user_authentication_log_sequence",
            sequenceName = "app_user_authentication_log_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_authentication_log_sequence"
    )
    private Long id;

    private Boolean isSuccessful;
    private LocalDateTime whenAttempt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public AppUserAuthenticationLog(Boolean isSuccessful, LocalDateTime when, AppUser appUser) {
        this.isSuccessful = isSuccessful;
        this.whenAttempt = when;
        this.appUser = appUser;
    }
}
