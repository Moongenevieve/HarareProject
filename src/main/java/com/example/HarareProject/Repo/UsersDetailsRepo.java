package com.example.HarareProject.Repo;

import com.example.HarareProject.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersDetailsRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
}