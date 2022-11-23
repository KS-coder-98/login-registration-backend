package com.example.demo.repository;

import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.appuser.AppUserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserPasswordRepository extends JpaRepository<AppUserPassword, Long> {

    Optional<AppUserPassword> findFirstByAppUserAndPassword(AppUser appUser, String password);

}
