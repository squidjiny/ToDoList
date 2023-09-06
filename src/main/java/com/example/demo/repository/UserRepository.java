package com.example.demo.repository;

import com.example.demo.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
//    List<User> findByUserid(long userid);
//
   Optional<User> findByUserid(Long userid);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Boolean findByUsername(String username);

    User getByUsername(String username);
    User getByUserid(String userid);
}

