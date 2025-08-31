package com.example.HarareProject.Service;

import com.example.HarareProject.Entity.*;
import com.example.HarareProject.Repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpResponseService {

    @Autowired
    private final HelpResponseRepo helpResponseRepo;

    @Autowired
    private final UsersDetailsRepo usersDetailsRepo;

    @Autowired
    private final HelpRequestRepo helpRequestRepo;

    public HelpResponseService(HelpResponseRepo helpResponseRepo, UsersDetailsRepo usersDetailsRepo, HelpRequestRepo helpRequestRepo) {
        this.helpResponseRepo = helpResponseRepo;
        this.usersDetailsRepo = usersDetailsRepo;
        this.helpRequestRepo = helpRequestRepo;
    }


    public List<HelpResponse> getAllHelpResponse(){
        return helpResponseRepo.findAll();
    }

    public HelpResponse getHelpResponseByID(Long id) {
        return helpResponseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Help Response not found with id: " + id));
    }

    public List<HelpResponse> getHelpResponseByUserID(Long id){
        return helpResponseRepo.findByUserId(id);
    }

    public List<HelpResponse> getHelpResponseByHelpRequest_Id (Long id){
        return helpResponseRepo.findByHelpRequest_Id(id);
    }


    // Add (create new Help Response)
    public HelpResponse addHelpResponse(Long userId, Long helpRequestId, String responseMessage) {
        Users user = usersDetailsRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User (admin) not found with id: " + userId));

        HelpRequest hr = helpRequestRepo.findById(helpRequestId)
                .orElseThrow(() -> new RuntimeException("Help request not found with id: " + helpRequestId));

        HelpResponse hrs = new HelpResponse();
        hrs.setUser(user);
        hrs.setHelpRequest(hr);
        hrs.setResponseMessage(responseMessage);

        return helpResponseRepo.save(hrs);
    }



    public HelpResponse updateHelpResponse(Long id, Long userId,String responseMessage) {
        Optional<HelpResponse> existingHelpResponse= helpResponseRepo
                .findById(id);
        Users user = usersDetailsRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User (admin) not found with id: " + userId));

        if(existingHelpResponse.isPresent()){
            HelpResponse reqToUpdate = existingHelpResponse.get();
            reqToUpdate.setResponseMessage(responseMessage);
            reqToUpdate.setUser(user);
            helpResponseRepo.save(reqToUpdate);
            return reqToUpdate;
        }

        return null;
    }

}
