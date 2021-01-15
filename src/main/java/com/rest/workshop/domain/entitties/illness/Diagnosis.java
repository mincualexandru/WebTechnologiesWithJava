package com.rest.workshop.domain.entitties.illness;

import java.util.Arrays;
import java.util.List;

public enum Diagnosis {
    PHARMACEUTICAL(Values.PHARMACEUTICAL),
    PHYSIOTHERAPEUTIC_AND_KINEOTHERAPEUTIC(Values.PHARMACEUTICAL),
    SURGICAL(Values.PHARMACEUTICAL);

    private final String label;

    Diagnosis(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static List<Diagnosis> getValues() {
        return Arrays.asList(Diagnosis.values());
    }

    public static class Values {
        static final String PHARMACEUTICAL = "Pharmaceutical treatment";
        static final String PHYSIOTHERAPEUTIC_AND_KINEOTHERAPEUTIC = "Physiotherapeutic and kineotherapeutic";
        static final String SURGICAL = "Surgical";

        private Values() {
        }
    }
}
