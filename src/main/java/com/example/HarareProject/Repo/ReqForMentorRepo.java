package com.example.HarareProject.Repo;

import com.example.HarareProject.Entity.RequestForMentor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ReqForMentorRepo extends JpaRepository<RequestForMentor,Long> {
    List<RequestForMentor> findByUserId(Long userId);
    List<RequestForMentor> findByMentorId(Long mentorId);
}

