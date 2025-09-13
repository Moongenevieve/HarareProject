package com.example.HarareProject.Controller;

import com.example.HarareProject.Entity.RegisterUserRequest;
import com.example.HarareProject.Entity.Role;
import com.example.HarareProject.Entity.UserResponseDTO;
import com.example.HarareProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://harareproject-production.up.railway.app")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        registerUserRequest.setRole(Role.USER);
        UserResponseDTO userResponse = userService.registerUser(registerUserRequest);

        return ResponseEntity.ok(userResponse);
    }


    @PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasAuthority('TIK_WRITE')")
    @PostMapping("/admin/create")
    public ResponseEntity<UserResponseDTO> registerByAdmin(@RequestBody RegisterUserRequest registerUserRequest) {
        UserResponseDTO userResponse = userService.registerUser(registerUserRequest);
        return ResponseEntity.ok(userResponse);
    }




}
