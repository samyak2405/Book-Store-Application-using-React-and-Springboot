package com.javahunter.application.repository;

import com.javahunter.application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    boolean existsByName(String name);
    Role findByName(String name);
}
