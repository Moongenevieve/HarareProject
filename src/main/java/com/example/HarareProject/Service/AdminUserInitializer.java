package com.example.HarareProject.Service;

import com.example.HarareProject.Entity.Role;
import com.example.HarareProject.Entity.Users;
import com.example.HarareProject.Repo.UsersDetailsRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdminUser(
            UsersDetailsRepo usersDetailsRepo, PasswordEncoder passwordEncoder) {

        return args -> {
            if(usersDetailsRepo.findByUsername("admin").isEmpty()) {
                Users admin  = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole(Role.ADMIN);

                usersDetailsRepo.save(admin);
                System.out.println("Default admin user created");
            }

            if(usersDetailsRepo.findByUsername("user").isEmpty()) {
                Users admin  = new Users();
                admin.setUsername("user");
                admin.setPassword(passwordEncoder.encode("user1234"));
                admin.setRole(Role.USER);

                usersDetailsRepo.save(admin);
                System.out.println("Default ordinary user created");
            }

        };

    }




}
