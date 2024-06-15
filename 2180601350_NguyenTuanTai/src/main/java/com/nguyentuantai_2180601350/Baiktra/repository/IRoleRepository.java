package com.nguyentuantai_2180601350.Baiktra.repository;


import com.nguyentuantai_2180601350.Baiktra.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
}
