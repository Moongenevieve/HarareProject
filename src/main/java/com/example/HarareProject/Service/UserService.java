package com.example.HarareProject.Service;

import com.example.HarareProject.Entity.RegisterUserRequest;
import com.example.HarareProject.Entity.UserResponseDTO;
import com.example.HarareProject.Entity.Users;
import com.example.HarareProject.Repo.UsersDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UsersDetailsRepo usersDetailsRepo;

    private final PasswordEncoder passwordEncoder;

    public UserService(UsersDetailsRepo usersDetailsRepo, PasswordEncoder passwordEncoder) {
        this.usersDetailsRepo = usersDetailsRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO registerUser(RegisterUserRequest registerUserRequest) {

        //check if already present
        if(usersDetailsRepo.findByUsername(registerUserRequest.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists please");
        }

        //encode the password
        Users user = new Users();
        user.setUsername(registerUserRequest.getUsername());
        user.setRole(registerUserRequest.getRole());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));

        //save users
        Users savedUser = usersDetailsRepo.save(user);
        return new UserResponseDTO(savedUser.getId(),savedUser.getUsername(), savedUser.getRole().name());

    }
}
