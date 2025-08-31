package com.example.HarareProject.Controller;

import com.example.HarareProject.DTOs.HelpRequestDTO;
import com.example.HarareProject.DTOs.HelpRequestUpdateDTO;
import com.example.HarareProject.Entity.HelpRequest;
import com.example.HarareProject.Service.HelpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/helprequest")
public class HelpRequestController {


    @Autowired
    private final HelpRequestService helpRequestService;

    public HelpRequestController(HelpRequestService helpRequestService) {
        this.helpRequestService = helpRequestService;
    }


    @GetMapping("/get")
    public List<HelpRequest> getHelpRequest(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String subject

    ) {

        if(userId != null) {
            return helpRequestService.getHelpRequestByUserID(userId);
        }
        else if(status != null) {
            return helpRequestService.getHelpRequestByStatus(status);
        }
        else if(subject != null) {
            return helpRequestService.getHelpRequestBySubject(subject);
        }
        else {
            return helpRequestService.getAllHelpRequest();
        }
    }


    @GetMapping("/getbyid")
    public HelpRequest getHelpRequestById(@RequestParam Long id) {
        return helpRequestService.getHelpRequestByID(id);
    }


    @PostMapping
    public ResponseEntity<HelpRequest> addHelpRequest(@RequestBody HelpRequestDTO helpRequestDTO) {
        HelpRequest helpRequest = helpRequestService.addHelpRequest(
                helpRequestDTO.getUserId(),
                helpRequestDTO.getSubject(),
                helpRequestDTO.getMessage()
        );
        return new ResponseEntity<>(helpRequest, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<HelpRequest> updateHelpRequest(@RequestBody HelpRequestUpdateDTO helpRequestUpdateDTO) {
        HelpRequest updatedHelpRequest= helpRequestService.updateHelpRequest(
                helpRequestUpdateDTO.getId(),
                helpRequestUpdateDTO.getStatus());
        if (updatedHelpRequest!= null) {
            return new ResponseEntity<>(updatedHelpRequest, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHelpRequest(@PathVariable Long id) {
        helpRequestService.deleteHelpRequest(id);
        return new ResponseEntity<>("Help Request deleted successfully",HttpStatus.OK);
    }

}
