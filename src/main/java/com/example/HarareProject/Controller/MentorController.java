package com.example.HarareProject.Controller;

import com.example.HarareProject.Entity.Mentors;
import com.example.HarareProject.Service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mentor")
public class MentorController {

    @Autowired
    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping("/get")
    public List<Mentors> getMentors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String field
    ) {

        if(name != null) {
            return mentorService.getMentorsByName(name);
        }
        else if(field != null) {
            return mentorService.getMentorsByField(field);
        }
        else {
            return mentorService.getAllMentors();
        }
    }


    @GetMapping("/getbyid")
    public Mentors getMentorById(@RequestParam Long id) {
            return mentorService.getMentorByID(id);
    }

    @PostMapping
    public ResponseEntity<Mentors> addMentor(@RequestBody Mentors mentor) {
        Mentors createdMentor = mentorService.addMentor(mentor);
        return new ResponseEntity<>(createdMentor, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Mentors> updateMentor(@RequestBody Mentors mentor) {
        Mentors updatedMentor = mentorService.updateMentor(mentor);
        if (updatedMentor != null) {
            return new ResponseEntity<>(updatedMentor, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentor(id);
        return new ResponseEntity<>("Mentor deleted successfully",HttpStatus.OK);
    }


}
