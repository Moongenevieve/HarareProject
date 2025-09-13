package com.example.HarareProject.Controller;

import com.example.HarareProject.DTOs.ResourceAddDTO;
import com.example.HarareProject.DTOs.ResourceUpdateDTO;
import com.example.HarareProject.Entity.Resource;
import com.example.HarareProject.Service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/resource")
public class ResourceController {

    @Autowired
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }


    @GetMapping("/get")
    public List<Resource> getResourcer(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String type
    ) {
        if(title != null) {
            return resourceService.getResourceByTitle(title);
        }
        else if(type != null) {
            return resourceService.getResourceByType(type);
        }
        else {
            return resourceService.getAllResource();
        }
    }

    @GetMapping("/getbyid")
    public Resource getResourceById(@RequestParam Long id) {
        return resourceService.getResourceByID(id);
    }

    @PostMapping
    public ResponseEntity<Resource> addResource(@RequestBody ResourceAddDTO resource) {
        Resource createdReq = resourceService.addResource(resource);
        return new ResponseEntity<>(createdReq, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Resource> updateResource(@RequestBody ResourceUpdateDTO resource) {
        Resource updatedResource = resourceService.updateResource(resource);
        if (updatedResource!= null) {
            return new ResponseEntity<>(updatedResource, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
        return new ResponseEntity<>("Resource deleted successfully",HttpStatus.OK);
    }

}
