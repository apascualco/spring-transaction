package com.apascual.spring.transaction.persistence.repository;

import com.apascual.spring.transaction.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> { }
