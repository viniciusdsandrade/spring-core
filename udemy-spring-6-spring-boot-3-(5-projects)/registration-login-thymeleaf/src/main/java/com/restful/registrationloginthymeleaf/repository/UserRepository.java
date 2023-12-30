package com.restful.registrationloginthymeleaf.repository;

import com.restful.registrationloginthymeleaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String userEmail);

}