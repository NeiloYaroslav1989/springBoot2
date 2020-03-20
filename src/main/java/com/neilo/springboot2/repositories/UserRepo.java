package com.neilo.springboot2.repositories;

import com.neilo.springboot2.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
