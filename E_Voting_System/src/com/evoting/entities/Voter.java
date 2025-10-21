package com.evoting.entities;

import java.time.LocalDate;

public class Voter {
    private final String voterId;
    private final String name;
    private final LocalDate dob;
    private boolean hasVoted;

    public Voter(String voterId, String name, LocalDate dob) {
        this.voterId = voterId;
        this.name = name;
        this.dob = dob;
        this.hasVoted = false;
    }

    public String getVoterId() { return voterId; }
    public String getName() { return name; }
    public LocalDate getDob() { return dob; }
    public boolean hasVoted() { return hasVoted; }
    public void setVoted(boolean voted) { this.hasVoted = voted; }

    @Override
    public String toString() {
        return voterId + " | " + name + " | " + dob;
    }
}
