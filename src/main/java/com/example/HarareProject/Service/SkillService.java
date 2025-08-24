package com.example.HarareProject.Service;

import com.example.HarareProject.Entity.Mentors;
import com.example.HarareProject.Entity.Skill;
import com.example.HarareProject.Repo.MentorsRepo;
import com.example.HarareProject.Repo.SkillRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private final SkillRepo skillRepo;

    public SkillService(SkillRepo skillRepo) {
        this.skillRepo = skillRepo;
    }

    public Skill addSkill(Skill skill) {
        return skillRepo.save(skill);
    }

    public Skill updateSkill(Skill updatedSkill) {
        Optional<Skill> existingSkill = skillRepo.findById(updatedSkill.getId());

        if(existingSkill.isPresent()){
            Skill skillToUpdate = existingSkill.get();
            skillToUpdate.setName(updatedSkill.getName());
            skillRepo.save(skillToUpdate);
            return skillToUpdate;
        }
        return null;
    }

    public List<Skill> getAllSkill(){
        return skillRepo.findAll();
    }

    public Skill getSkillByID(Long id) {
        return skillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
    }

    public List<Skill> getSkillByName(String name){
        return skillRepo.findByName(name);
    }

    @Transactional
    public void deleteSkill(Long id) {
        skillRepo.deleteById(id);
    }

}
