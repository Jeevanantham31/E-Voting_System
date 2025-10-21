package com.evoting.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Candidate {
    private final String candidateId;
    private final String name;
    private final LocalDate dob;
    private final String area;

    public Candidate(String candidateId, String name, LocalDate dob, String area) {
        this.candidateId = candidateId;
        this.name = name;
        this.dob = dob;
        this.area = area;
    }

    public String getCandidateId() { return candidateId; }
    public String getName() { return name; }
    public LocalDate getDob() { return dob; }
    public String getArea() { return area; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return candidateId + " | " + name + " | " + dob.format(fmt) + " | Area: " + area;
    }
}
