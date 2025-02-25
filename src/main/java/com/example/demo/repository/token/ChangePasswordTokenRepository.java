package com.example.demo.repository.token;

import com.example.demo.model.token.ChangePasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ChangePasswordTokenRepository extends JpaRepository<ChangePasswordToken, Long> {

    Optional<ChangePasswordToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ChangePasswordToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
