package com.fa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.api.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
