package com.example.HarareProject.Service;

import com.example.HarareProject.Entity.HelpRequest;
import com.example.HarareProject.Entity.Users;
import com.example.HarareProject.Repo.HelpRequestRepo;
import com.example.HarareProject.Repo.UsersDetailsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HelpRequestService {

    @Autowired
    private final HelpRequestRepo helpRepo;

    @Autowired
    private final UsersDetailsRepo usersDetailsRepo;

    public HelpRequestService(HelpRequestRepo helpRepo, UsersDetailsRepo usersDetailsRepo) {
        this.helpRepo = helpRepo;
        this.usersDetailsRepo = usersDetailsRepo;
    }

    public List<HelpRequest> getAllHelpRequest(){
        return helpRepo.findAll();
    }

    public HelpRequest getHelpRequestByID(Long id) {
        return helpRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Help Request not found with id: " + id));
    }

    public List<HelpRequest> getHelpRequestByUserID(Long id){
        return helpRepo.findByUserId(id);
    }

    public List<HelpRequest> getHelpRequestByStatus(String status){
        return helpRepo.findByStatus(status);
    }


    public List<HelpRequest> getHelpRequestBySubject(String subject){
        return helpRepo.findByStatus(subject);
    }


    // Add (create new request)
    public HelpRequest addHelpRequest(Long userId, String subject, String message) {
        Users user = usersDetailsRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Help Request not found with id: " + userId));

        HelpRequest helpRequest = new HelpRequest();
        helpRequest.setUser(user);
        helpRequest.setSubject(subject);
        helpRequest.setMessage(message);

        return helpRepo.save(helpRequest);
    }


    public HelpRequest updateHelpRequest(Long id, String status) {
        Optional<HelpRequest> existingHelpRequest= helpRepo
                .findById(id);

        if(existingHelpRequest.isPresent()){
            HelpRequest helpRequestToUpdate = existingHelpRequest.get();
            helpRequestToUpdate.setStatus(status);
            helpRepo.save(helpRequestToUpdate);
            return helpRequestToUpdate;
        }

        return null;
    }

    @Transactional
    public void deleteHelpRequest(Long id) {
        helpRepo.deleteById(id);
    }


}
