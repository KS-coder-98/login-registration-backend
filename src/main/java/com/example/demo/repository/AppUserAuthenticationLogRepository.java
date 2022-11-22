package com.example.demo.repository;

import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserAuthenticationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppUserAuthenticationLogRepository extends JpaRepository<AppUserAuthenticationLog, Long> {

    List<AppUserAuthenticationLog> findAllByAppUser(AppUser appUser);
}
