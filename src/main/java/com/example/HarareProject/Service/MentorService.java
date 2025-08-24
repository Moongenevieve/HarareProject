package com.example.HarareProject.Service;

import com.example.HarareProject.Entity.Mentors;
import com.example.HarareProject.Repo.MentorsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentorService {

    @Autowired
    private final MentorsRepo mentorsRepo;

    public MentorService(MentorsRepo mentorsRepo) {
     this.mentorsRepo=mentorsRepo;
    }

    public List<Mentors> getAllMentors(){
        return mentorsRepo.findAll();
    }

    public Mentors getMentorByID(Long id) {
        return mentorsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor not found with id: " + id));
    }

    public List<Mentors> getMentorsByField(String field){
        return mentorsRepo.findByFieldContainingIgnoreCase(field);
    }

    public List<Mentors> getMentorsByName(String name){
        return mentorsRepo.findByName(name);
    }


    public Mentors addMentor(Mentors mentor) {
        mentorsRepo.save(mentor);
        return mentor;
    }
    public Mentors updateMentor(Mentors updatedmentor) {
        Optional<Mentors> existingMentor = mentorsRepo.findById(updatedmentor.getId());

        if(existingMentor.isPresent()){
            Mentors mentorToUpdate = existingMentor.get();
            mentorToUpdate.setName(updatedmentor.getName());
            mentorToUpdate.setField(updatedmentor.getField());
            mentorToUpdate.setBio(updatedmentor.getBio());
            mentorToUpdate.setYearsOfExperience(updatedmentor.getYearsOfExperience());
            mentorToUpdate.setIndustries(updatedmentor.getIndustries());
            mentorToUpdate.setInterests(updatedmentor.getInterests());

            mentorsRepo.save(mentorToUpdate);
            return mentorToUpdate;
        }

        return null;
    }


    @Transactional
    public void deleteMentor(Long id) {
        mentorsRepo.deleteById(id);
    }



        /*
    *   public List<Mentors> getMentorsByName(String name){
        return mentorsRepo.findAll().stream()
                .filter(mentors -> mentors.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    *
    *     public List<Mentors> getMentorsByField(String field){
        return mentorsRepo.findAll().stream()
                .filter(mentors -> mentors.getField().toLowerCase().contains(field.toLowerCase()))
                .collect(Collectors.toList());

    }
    * */
















}
