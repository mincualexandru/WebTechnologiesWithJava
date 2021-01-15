package com.rest.workshop.domain.entitties.symptom;

import com.rest.workshop.domain.entitties.AuditEntity;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Question")
public class Question extends AuditEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SymptomId")
    private Symptom symptom;

    @Column(name = "Message")
    private String message;

    @NotNull
    @Column(name = "Required")
    private Boolean required;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RespondentAnswer> respondentAnswers = new ArrayList<>();

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers.clear();
        if (answers != null) {
            this.answers.addAll(answers);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<RespondentAnswer> getRespondentAnswers() {
        return respondentAnswers;
    }

    public void setRespondentAnswers(List<RespondentAnswer> respondentAnswers) {
        this.respondentAnswers = respondentAnswers;
    }
}
