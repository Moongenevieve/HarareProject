package com.example.HarareProject.Repo;

import com.example.HarareProject.Entity.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserResourceRepo extends JpaRepository<UserResource,Long> {
    List<UserResource> findByUserId(Long userId);
    List<UserResource> findUserResourceByStatus(String status);
    List<UserResource> findUserResourceByResource_Id(Long resourceId);
}
