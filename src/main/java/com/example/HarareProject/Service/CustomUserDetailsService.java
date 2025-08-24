package com.example.HarareProject.Service;

import com.example.HarareProject.Repo.UsersDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersDetailsRepo udr;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return udr.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username not found ooo"));
    }


}

