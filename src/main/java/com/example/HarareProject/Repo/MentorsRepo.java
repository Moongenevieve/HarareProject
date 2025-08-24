package com.example.HarareProject.Repo;

import com.example.HarareProject.Entity.Mentors;
import com.example.HarareProject.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MentorsRepo  extends JpaRepository<Mentors,Long> {
    List<Mentors> findByName(String name);
    List<Mentors> findByFieldContainingIgnoreCase(String field);

}
