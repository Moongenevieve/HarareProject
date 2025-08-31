package com.example.HarareProject.Service;

import com.example.HarareProject.Entity.*;
import com.example.HarareProject.Repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserResourceService {

    @Autowired
    private final UserResourceRepo userResourceRepo;

    @Autowired
    private final UsersDetailsRepo usersDetailsRepo;

    @Autowired
    private final ResourceRepo resourceRepo;

    public UserResourceService(UserResourceRepo userResourceRepo, UsersDetailsRepo usersDetailsRepo, ResourceRepo resourceRepo) {
        this.userResourceRepo = userResourceRepo;
        this.usersDetailsRepo = usersDetailsRepo;
        this.resourceRepo = resourceRepo;
    }


    public List<UserResource> getAllUserResource(){
        return userResourceRepo.findAll();
    }

    public UserResource getUserResourceByID(Long id) {
        return userResourceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User Resource not found with id: " + id));
    }

    public List<UserResource> getUserResourceByUserID(Long id){
        return userResourceRepo.findByUserId(id);
    }

    public List<UserResource> getUserResourceByResourceID(Long id){
        return userResourceRepo.findUserResourceByResource_Id(id);
    }

    public List<UserResource> getUserResourceByStatus(String status){
        return userResourceRepo.findUserResourceByStatus(status);
    }


    // Add (create new request)
    public UserResource addUserResource(Long userId, Long resourceId) {
        Users user = usersDetailsRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Resource not found with id: " + userId));

        Resource resource = resourceRepo.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Mentor not found with id: " + resourceId));

        UserResource userResource = new UserResource();
        userResource.setUser(user);
        userResource.setResource(resource);

        return userResourceRepo.save(userResource);
    }


    public UserResource updateRUserResource(Long id, String status, int percentage) {
        Optional<UserResource> existingUserResource= userResourceRepo
                .findById(id);

        if(existingUserResource.isPresent()){
            UserResource userResourceToUpdate = existingUserResource.get();
            userResourceToUpdate.setStatus(status);
            userResourceToUpdate.setPercentage(percentage);
            userResourceRepo.save(userResourceToUpdate);
            return userResourceToUpdate;
        }

        return null;
    }

    @Transactional
    public void deleteUserResource(Long id) {
        userResourceRepo.deleteById(id);
    }


}
