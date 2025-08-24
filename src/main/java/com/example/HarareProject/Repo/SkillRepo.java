package com.example.HarareProject.Repo;

import com.example.HarareProject.Entity.Mentors;
import com.example.HarareProject.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepo  extends JpaRepository<Skill,Long> {
    List<Skill> findByName(String name);

}
