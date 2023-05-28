package com.upk.diploma.customerservice.repository;

import com.upk.diploma.customerservice.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findUserAccountByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
