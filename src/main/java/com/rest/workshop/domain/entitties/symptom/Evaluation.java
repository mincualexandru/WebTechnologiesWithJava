package com.rest.workshop.domain.entitties.symptom;

import com.rest.workshop.domain.entitties.AuditEntity;
import com.rest.workshop.domain.entitties.user.User;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Evaluation")
public class Evaluation extends AuditEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "RespondentId")
    private User respondent;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SymptomId")
    private Symptom symptom;

    @OneToMany(
            mappedBy = "evaluation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<RespondentAnswer> respondentAnswers = new HashSet<>();

    public User getRespondent() {
        return respondent;
    }

    public void setRespondent(User respondent) {
        this.respondent = respondent;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public Set<RespondentAnswer> getRespondentAnswers() {
        return respondentAnswers;
    }

    public void setRespondentAnswers(Set<RespondentAnswer> respondentAnswers) {
        this.respondentAnswers = respondentAnswers;
    }
}
