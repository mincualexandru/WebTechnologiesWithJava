package com.rest.workshop.domain.entitties.symptom;

import com.rest.workshop.domain.entitties.AuditEntity;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Answer")
public class Answer extends AuditEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "QuestionId")
    private Question question;

    @Column(name = "Message")
    private String message;

    @OneToMany(
            mappedBy = "answer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<RespondentAnswer> respondentAnswers = new HashSet<>();

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<RespondentAnswer> getRespondentAnswers() {
        return respondentAnswers;
    }

    public void setRespondentAnswers(Set<RespondentAnswer> respondentAnswers) {
        this.respondentAnswers = respondentAnswers;
    }
}
