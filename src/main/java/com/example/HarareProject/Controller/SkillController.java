package com.example.HarareProject.Controller;

import com.example.HarareProject.Entity.Skill;
import com.example.HarareProject.Service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/skill")
public class SkillController {

    @Autowired
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/get")
    public List<Skill> getSkill(
            @RequestParam(required = false) String name
    ) {

        if(name != null) {
            return skillService.getSkillByName(name);
        }
        else {
            return skillService.getAllSkill();
        }
    }

    @GetMapping("/getbyid")
    public Skill getSkillById(@RequestParam Long id) {
        return skillService.getSkillByID(id);
    }

    @PostMapping
    public ResponseEntity<Skill> addSkill(@RequestBody Skill skill) {
        Skill createdSkill = skillService.addSkill(skill);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) {
        Skill updatedSkill= skillService.updateSkill(skill);
        if (updatedSkill != null) {
            return new ResponseEntity<>(updatedSkill, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return new ResponseEntity<>("Skill deleted successfully",HttpStatus.OK);
    }

}
