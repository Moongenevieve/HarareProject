package com.example.HarareProject.Repo;

import com.example.HarareProject.Entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ResourceRepo extends JpaRepository<Resource,Long> {
    List<Resource> findByTitle(String title);
    List<Resource> findByType(String type);

}
