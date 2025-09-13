package com.example.HarareProject.Controller;

import com.example.HarareProject.DTOs.HelpResponseDTO;
import com.example.HarareProject.DTOs.HelpResponseUpdateDTO;
import com.example.HarareProject.Entity.HelpResponse;
import com.example.HarareProject.Service.HelpResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/helpresponse")
public class HelpResponseController {

    @Autowired
    private final HelpResponseService hrs;

    public HelpResponseController(HelpResponseService hrs) {
        this.hrs = hrs;
    }


    @GetMapping("/get")
    public List<HelpResponse> HelpResponse(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long helpRequestId

    ) {

        if(userId != null) {
            return hrs.getHelpResponseByUserID(userId);
        }
        else if(helpRequestId != null) {
            return hrs.getHelpResponseByHelpRequest_Id(helpRequestId);
        }
        else {
            return hrs.getAllHelpResponse();
        }
    }


    @GetMapping("/getbyid")
    public HelpResponse getHelpResponseById(@RequestParam Long id) {
        return hrs.getHelpResponseByID(id);
    }


    @PostMapping
    public ResponseEntity<HelpResponse> addHelpResponse(@RequestBody HelpResponseDTO helpResponseDTO) {
        HelpResponse hp = hrs.addHelpResponse(
                helpResponseDTO.getUserId(),
                helpResponseDTO.getHelpRequestId(),
                helpResponseDTO.getResponseMessage()
        );
        return new ResponseEntity<>(hp, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<HelpResponse> updateHelpResponse(@RequestBody HelpResponseUpdateDTO hud) {
        HelpResponse updatedHelpResponse= hrs.updateHelpResponse(
                hud.getId(),
                hud.getUserId(),
                hud.getResponseMessage());
        if (updatedHelpResponse!= null) {
            return new ResponseEntity<>(updatedHelpResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
