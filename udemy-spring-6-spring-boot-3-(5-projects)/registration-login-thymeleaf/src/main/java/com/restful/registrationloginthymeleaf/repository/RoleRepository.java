package com.restful.registrationloginthymeleaf.repository;

import com.restful.registrationloginthymeleaf.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {
}
