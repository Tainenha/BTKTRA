package com.nguyentuantai_2180601350.Baiktra.repository;

import com.nguyentuantai_2180601350.Baiktra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
