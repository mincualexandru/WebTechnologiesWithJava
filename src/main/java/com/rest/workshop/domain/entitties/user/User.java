package com.rest.workshop.domain.entitties.user;

import com.rest.workshop.domain.entitties.AuditEntity;
import com.rest.workshop.domain.entitties.symptom.Symptom;
import com.rest.workshop.domain.entitties.symptom.Evaluation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User extends AuditEntity {

    @NotNull
    @Column(name = "Username")
    private String username;

    @NotNull
    @Column(name = "Password")
    private String password;

    @NotNull
    @Column(name = "FirstName")
    private String firstName;

    @NotNull
    @Column(name = "LastName")
    private String lastName;

    @NotNull
    @Column(name = "Role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(name = "Enabled", columnDefinition = "BIT default 0")
    private Boolean enabled = false;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Symptom> symptoms = new HashSet<>();

    @OneToMany(
            mappedBy = "respondent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Evaluation> respons = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public Set<Evaluation> getRespons() {
        return respons;
    }

    public void setRespons(Set<Evaluation> respons) {
        this.respons = respons;
    }
}
