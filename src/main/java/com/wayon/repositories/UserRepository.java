package com.wayon.repositories;

import com.wayon.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE account = ?1", nativeQuery = true)
    User getAllUsersByAccount(String account);
}
