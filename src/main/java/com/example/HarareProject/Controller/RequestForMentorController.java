package com.example.HarareProject.Controller;

import com.example.HarareProject.DTOs.RequestForMentorDTO;
import com.example.HarareProject.DTOs.RequestUpdateDTO;
import com.example.HarareProject.Entity.RequestForMentor;
import com.example.HarareProject.Service.ReqForMentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reqformentor")
public class RequestForMentorController {

    @Autowired
    private final ReqForMentorService reqService;

    public RequestForMentorController(ReqForMentorService reqService) {
        this.reqService = reqService;
    }

    @GetMapping("/get")
    public List<RequestForMentor> getRequestForMentor(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long mentorId
    ) {

        if(userId != null) {
            return reqService.getRequestsForMentorByUserID(userId);
        }
        else if(mentorId != null) {
            return reqService.getRequestsForMentorByMentorID(mentorId);
        }
        else {
            return reqService.getAllRequestsForMentor();
        }
    }

    @GetMapping("/getbyid")
    public RequestForMentor getRequestForMentorById(@RequestParam Long id) {
        return reqService.getRequestsForMentorByID(id);
    }

    @PostMapping
    public ResponseEntity<RequestForMentor> addRequestForMentor(@RequestBody RequestForMentorDTO requestDTO) {
        RequestForMentor createdReq = reqService.addRequestsForMentor(
                requestDTO.getUserId(),
                requestDTO.getMentorId(),
                requestDTO.getMessage()
        );
        return new ResponseEntity<>(createdReq, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<RequestForMentor> updateRequestForMentor(@RequestBody RequestUpdateDTO requestDTO) {
        RequestForMentor updatedReq = reqService.updateRequestsForMentor(
                requestDTO.getId(),
                requestDTO.getStatus());
        if (updatedReq!= null) {
            return new ResponseEntity<>(updatedReq, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMentor(@PathVariable Long id) {
        reqService.deleteRequestsForMentor(id);
        return new ResponseEntity<>("Request For Mentor deleted successfully",HttpStatus.OK);
    }

}
