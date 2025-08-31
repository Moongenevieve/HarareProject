package com.example.HarareProject.Repo;

import com.example.HarareProject.Entity.HelpRequest;
import com.example.HarareProject.Entity.HelpResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpResponseRepo extends JpaRepository<HelpResponse,Long> {
    List<HelpResponse> findByUserId(Long userId);
    List<HelpResponse> findByHelpRequest_Id(Long helpRequestId);

}
