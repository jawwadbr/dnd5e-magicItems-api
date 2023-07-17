package com.jawbr.repository;

import com.jawbr.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Integer> {

    Collection<AuthRole> findByRole(String role);
}
