package com.jawbr.repository;

import com.jawbr.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Integer> {
}
