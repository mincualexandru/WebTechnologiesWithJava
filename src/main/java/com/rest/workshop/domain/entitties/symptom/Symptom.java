package com.rest.workshop.domain.entitties.symptom;

import com.rest.workshop.domain.entitties.AuditEntity;
import com.rest.workshop.domain.entitties.user.User;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Symptom")
public class Symptom extends AuditEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "OwnerId")
    private User owner;

    @Column(name = "Title")
    private String title;

    @NotNull
    @Column(name = "Enabled")
    private Boolean enabled;

    @OneToMany(
            mappedBy = "symptom",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Question> questions = new ArrayList<>();

    @OneToMany(
            mappedBy = "symptom",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Evaluation> evaluations = new ArrayList<>();

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions.clear();
        if (questions != null) {
            this.questions.addAll(questions);
        }
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
