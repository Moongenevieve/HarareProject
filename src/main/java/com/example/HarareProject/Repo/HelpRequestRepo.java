package com.example.HarareProject.Repo;

import com.example.HarareProject.Entity.HelpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpRequestRepo extends JpaRepository<HelpRequest,Long> {
    List<HelpRequest> findByUserId(Long userId);
    List<HelpRequest> findByStatus(String status);
    List<HelpRequest> findBySubject(String subject);
}
