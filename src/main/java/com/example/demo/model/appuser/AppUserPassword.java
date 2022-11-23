package com.example.demo.model.appuser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AppUserPassword {
    @SequenceGenerator(
            name = "app_user_password_sequence",
            sequenceName = "app_user_password_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_password_sequence"
    )
    private Long id;

    private LocalDateTime settingDate;

    private String password;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public AppUserPassword(LocalDateTime settingDate, String password, AppUser appUser) {
        this.settingDate = settingDate;
        this.password = password;
        this.appUser = appUser;
    }
}
