package com.example.HarareProject.Controller;

import com.example.HarareProject.DTOs.UserResourceDTO;
import com.example.HarareProject.DTOs.UserResourceUpdate;
import com.example.HarareProject.Entity.UserResource;
import com.example.HarareProject.Service.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userresource")
public class UserResourceController {

    @Autowired
    private final UserResourceService userResourceService;

    public UserResourceController(UserResourceService userResourceService) {
        this.userResourceService = userResourceService;
    }


    @GetMapping("/get")
    public List<UserResource> getUserResource(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long resourceId,
            @RequestParam(required = false) String status
    ) {

        if(userId != null) {
            return userResourceService.getUserResourceByUserID(userId);
        }
        else if(resourceId != null) {
            return userResourceService.getUserResourceByResourceID(resourceId);
        }
        else if(status != null) {
            return userResourceService.getUserResourceByStatus(status);
        }
        else {
            return userResourceService.getAllUserResource();
        }
    }

    @GetMapping("/getbyid")
    public UserResource getUserResourceById(@RequestParam Long id) {
        return userResourceService.getUserResourceByID(id);
    }

    @PostMapping
    public ResponseEntity<UserResource> addUserResource(@RequestBody UserResourceDTO resourceDTO) {
        UserResource userResource = userResourceService.addUserResource(
                resourceDTO.getUserId(),
                resourceDTO.getResourceId()
        );
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<UserResource> updateUserResource(@RequestBody UserResourceUpdate update) {
        UserResource updatedUserResource= userResourceService.updateRUserResource(
                update.getId(),
                update.getStatus(),
                update.getPercentage());
        if (updatedUserResource!= null) {
            return new ResponseEntity<>(updatedUserResource, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserResource(@PathVariable Long id) {
        userResourceService.deleteUserResource(id);
        return new ResponseEntity<>("User Resource deleted successfully",HttpStatus.OK);
    }
}
