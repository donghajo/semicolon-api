package com.semicolonapi.server.user.repositories;

import com.semicolonapi.server.user.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
}
