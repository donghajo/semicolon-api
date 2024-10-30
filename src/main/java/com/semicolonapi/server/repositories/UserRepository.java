package com.semicolonapi.server.repositories;

import com.semicolonapi.server.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
}
