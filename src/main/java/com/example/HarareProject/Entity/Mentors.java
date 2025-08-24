package com.example.HarareProject.Entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="HARAREMENTORS")
public class Mentors {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        //@Column(length = 10000)
        @Column(columnDefinition = "TEXT")
        private String bio;

        @Column(columnDefinition = "TEXT")
        private String field;

        private int yearsOfExperience;

        @Column(columnDefinition = "TEXT")
        private String industries;

        @Column(columnDefinition = "TEXT")
        private String interests;

    public Mentors(String name, String bio, String field, int yearsOfExperience, String industries, String interests, Long id) {
        this.name = name;
        this.bio = bio;
        this.field = field;
        this.yearsOfExperience = yearsOfExperience;
        this.industries = industries;
        this.interests = interests;
        this.id = id;
    }

    public Mentors() {

    }

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public int getYearsOfExperience() {
            return yearsOfExperience;
        }

        public void setYearsOfExperience(int yearsOfExperience) {
            this.yearsOfExperience = yearsOfExperience;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getIndustries() {
            return industries;
        }

        public void setIndustries(String industries) {
            this.industries = industries;
        }

        public String getInterests() {
            return interests;
        }

        public void setInterests(String interests) {
            this.interests = interests;
        }
}
