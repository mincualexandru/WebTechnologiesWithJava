package com.rest.workshop.domain.entitties.illness;

import com.rest.workshop.domain.entitties.AuditEntity;
import com.rest.workshop.domain.entitties.symptom.Symptom;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Illness")
public class Illness extends AuditEntity {

    @Column(name = "Content")
    private String content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "IllnessSymptom",
            joinColumns = @JoinColumn(name = "IllnessId"),
            inverseJoinColumns = @JoinColumn(name = "SymptomId")
    )
    private List<Symptom> symptoms = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "Risks")
    @CollectionTable(name = "IllnessRisk", joinColumns = @JoinColumn(name = "IllnessId"))
    @Fetch(FetchMode.SELECT)
    @Enumerated(EnumType.STRING)
    private Set<Risk> risks = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "Diagnoses")
    @CollectionTable(name = "IllnessDiagnoses", joinColumns = @JoinColumn(name = "IllnessId"))
    @Fetch(FetchMode.SELECT)
    @Enumerated(EnumType.STRING)
    private Set<Diagnosis> diagnoses = new HashSet<>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms.clear();
        if (symptoms != null) {
            this.symptoms.addAll(symptoms);
        }
    }

    public Set<Risk> getRisks() {
        return risks;
    }

    public void setRisks(List<Risk> risks) {
        this.risks.clear();
        if (risks != null) {
            this.risks.addAll(risks);
        }
    }

    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses.clear();
        if (diagnoses != null) {
            this.diagnoses.addAll(diagnoses);
        }
    }
}