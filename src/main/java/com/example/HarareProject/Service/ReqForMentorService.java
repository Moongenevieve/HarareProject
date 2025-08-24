package com.example.HarareProject.Service;

import com.example.HarareProject.Entity.Mentors;
import com.example.HarareProject.Entity.RequestForMentor;
import com.example.HarareProject.Entity.Users;
import com.example.HarareProject.Repo.MentorsRepo;
import com.example.HarareProject.Repo.ReqForMentorRepo;
import com.example.HarareProject.Repo.UsersDetailsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ReqForMentorService {

    @Autowired
    private final ReqForMentorRepo reqForMentorRepo;

    @Autowired
    private final UsersDetailsRepo usersDetailsRepo;

    @Autowired
    private final MentorsRepo mentorsRepo;


    public ReqForMentorService(ReqForMentorRepo reqForMentorRepo, UsersDetailsRepo usersDetailsRepo, MentorsRepo mentorsRepo) {
        this.reqForMentorRepo = reqForMentorRepo;
        this.usersDetailsRepo = usersDetailsRepo;
        this.mentorsRepo = mentorsRepo;
    }

    public List<RequestForMentor> getAllRequestsForMentor(){
        return reqForMentorRepo.findAll();
    }

    public RequestForMentor getRequestsForMentorByID(Long id) {
        return reqForMentorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request For Mentor not found with id: " + id));
    }

    public List<RequestForMentor> getRequestsForMentorByUserID(Long id){
        return reqForMentorRepo.findByUserId(id);
    }

    public List<RequestForMentor> getRequestsForMentorByMentorID (Long id){
        return reqForMentorRepo.findByMentorId(id);
    }

/*
    public RequestForMentor addRequestsForMentor(RequestForMentor request) {
        reqForMentorRepo.save(request);
        return request;
    }

*/

    // Add (create new request)
    public RequestForMentor addRequestsForMentor(Long userId, Long mentorId, String message) {
        Users user = usersDetailsRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User (student) not found with id: " + userId));

        Mentors mentor = mentorsRepo.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor not found with id: " + mentorId));

        RequestForMentor request = new RequestForMentor();
        request.setUser(user);
        request.setMentor(mentor);
        request.setMessage(message);

        return reqForMentorRepo.save(request);
    }


    public RequestForMentor updateRequestsForMentor(Long id, String status) {
        Optional<RequestForMentor> existingRequest = reqForMentorRepo
                .findById(id);

        if(existingRequest.isPresent()){
            RequestForMentor reqToUpdate = existingRequest.get();
            reqToUpdate.setStatus(status);
            reqForMentorRepo.save(reqToUpdate);
            return reqToUpdate;
        }

        return null;
    }

    @Transactional
    public void deleteRequestsForMentor(Long id) {
        reqForMentorRepo.deleteById(id);
    }



}
