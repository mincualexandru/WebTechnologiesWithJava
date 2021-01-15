package com.rest.workshop.domain.search_params;

import com.rest.workshop.domain.entitties.illness.Diagnosis;
import com.rest.workshop.domain.entitties.illness.Risk;
import com.rest.workshop.domain.entitties.symptom.Symptom;

import java.util.List;

public class IllnessSearchParams {
    private String content;
    private List<Symptom> symptoms;
    private List<Risk> risks;
    private List<Diagnosis> diagnoses;

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
        this.symptoms = symptoms;
    }

    public List<Risk> getRisks() {
        return risks;
    }

    public void setRisks(List<Risk> risks) {
        this.risks = risks;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }
}
