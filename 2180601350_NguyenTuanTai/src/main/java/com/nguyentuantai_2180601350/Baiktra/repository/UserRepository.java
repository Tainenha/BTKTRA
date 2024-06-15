package com.nguyentuantai_2180601350.Baiktra.repository;



import com.nguyentuantai_2180601350.Baiktra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}