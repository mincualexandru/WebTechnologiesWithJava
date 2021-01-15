package com.rest.workshop.domain.entitties.symptom;

import com.rest.workshop.domain.entitties.AuditEntity;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "RespondentAnswer")
public class RespondentAnswer extends AuditEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ResponseId")
    private Evaluation evaluation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "QuestionId")
    private Question question;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ReportId")
    private Answer answer;

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
