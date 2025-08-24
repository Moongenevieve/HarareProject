package com.example.HarareProject.Service;

import com.example.HarareProject.DTOs.ResourceAddDTO;
import com.example.HarareProject.DTOs.ResourceUpdateDTO;
import com.example.HarareProject.Entity.*;
import com.example.HarareProject.Repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {


    @Autowired
    private final ResourceRepo resourceRepo;

    @Autowired
    private final SkillRepo skillRepo;

    public ResourceService(ResourceRepo resourceRepo, SkillRepo skillRepo) {
        this.resourceRepo = resourceRepo;
        this.skillRepo = skillRepo;
    }


    public List<Resource> getAllResource(){
        return resourceRepo.findAll();
    }

    public Resource getResourceByID(Long id) {
        return resourceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + id));
    }


    public List<Resource> getResourceByTitle (String title){
        return resourceRepo.findByTitle(title);
    }

    public List<Resource> getResourceByType (String type){
        return resourceRepo.findByType(type);
    }

    // Add (create new request)
    public Resource addResource(ResourceAddDTO resource) {
        Skill skill = skillRepo.findById(resource.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + resource.getCategoryId()));

        Resource res = new Resource();
        res.setSkill(skill);
        res.setTitle(resource.getTitle());
        res.setType(resource.getType());
        res.setLink(resource.getLink());
        res.setDescription(resource.getDescription());

        return resourceRepo.save(res);
    }


    public Resource updateResource(ResourceUpdateDTO updatedResource) {
        Optional<Resource> existingResource = resourceRepo.findById(updatedResource.getId());
        Skill existingSkill = skillRepo.findById(updatedResource.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + updatedResource.getCategoryId()));

        if(existingResource.isPresent()){
            Resource resourceToUpdate = existingResource.get();
            resourceToUpdate.setTitle(updatedResource.getTitle());
            resourceToUpdate.setType(updatedResource.getType());
            resourceToUpdate.setSkill(existingSkill);
            resourceToUpdate.setLink(updatedResource.getLink());
            resourceToUpdate.setDescription(updatedResource.getDescription());

            resourceRepo.save(resourceToUpdate);
            return resourceToUpdate;
        }

        return null;
    }

    @Transactional
    public void deleteResource(Long id) {
        resourceRepo.deleteById(id);
    }


}
