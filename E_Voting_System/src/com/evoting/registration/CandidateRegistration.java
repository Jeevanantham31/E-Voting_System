package com.evoting.registration;

import com.evoting.entities.Candidate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CandidateRegistration {
    private final Map<String, Candidate> candidates = new HashMap<>();
    private final Map<String, Integer> candidateCount = new HashMap<>();
    private static final DateTimeFormatter DOB_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Add candidate
    public String addCandidate(String name, LocalDate dob, String area) {
        String initials = generateInitials(name);
        String dobStr = dob.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseId = area.trim().substring(0,1).toUpperCase() + "-" + initials + dobStr;

        int count = candidateCount.getOrDefault(baseId, 0) + 1;
        candidateCount.put(baseId, count);

        String candidateId = baseId + "-" + count;
        Candidate candidate = new Candidate(candidateId, name, dob, area);
        candidates.put(candidateId, candidate);

        System.out.println("Candidate Added: " + candidate);
        return candidateId;
    }

    // Show all candidates
    public void showCandidates() {
        System.out.println("\n Candidates:");
        if (candidates.isEmpty()) System.out.println("No candidates registered.");
        else candidates.values().forEach(System.out::println);
    }

    // Get candidates map
    public Map<String, Candidate> getCandidates() {
        return candidates;
    }

    // Helper: generate initials
    private String generateInitials(String name) {
        String[] parts = name.trim().split("\\s+");
        if (parts.length == 1) return parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
        return Arrays.stream(parts).map(s -> s.substring(0, 1).toUpperCase()).reduce("", String::concat);
    }
}

