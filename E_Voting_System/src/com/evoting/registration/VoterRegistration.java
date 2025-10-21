package com.evoting.registration;

import com.evoting.entities.Voter;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class VoterRegistration {
    private final Map<String, Voter> voters = new HashMap<>();
    private final Map<String, Integer> voterCount = new HashMap<>();

    // Register voter
    public String registerVoter(String name, LocalDate dob) {
        if (!isAdult(dob)) {
            System.out.println(" Voter must be 18 or older!");
            return null;
        }

        String initials = generateInitials(name);
        String dobStr = dob.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseId = initials + dobStr;

        int count = voterCount.getOrDefault(baseId, 0) + 1;
        voterCount.put(baseId, count);

        String voterId = baseId + "-" + count;
        Voter voter = new Voter(voterId, name, dob);
        voters.put(voterId, voter);

        System.out.println("Voter Registered: " + voter);
        return voterId;
    }

    public Map<String, Voter> getVoters() {
        return voters;
    }

    // Check age >= 18
    private boolean isAdult(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears() >= 18;
    }

    private String generateInitials(String name) {
        String[] parts = name.trim().split("\\s+");
        if (parts.length == 1) return parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
        return Arrays.stream(parts).map(s -> s.substring(0, 1).toUpperCase()).reduce("", String::concat);
    }
}


