package com.evoting.voting;

import com.evoting.entities.Candidate;
import com.evoting.entities.Voter;
import java.util.*;

public class VotingProcess {
    private final Map<String, Candidate> candidates;
    private final Map<String, Voter> voters;
    private final Map<String, Integer> votes = new TreeMap<>();

    public VotingProcess(Map<String, Candidate> candidates, Map<String, Voter> voters) {
        this.candidates = candidates;
        this.voters = voters;

        // Initialize vote counts
        for (String cid : candidates.keySet()) votes.put(cid, 0);
    }

    public void vote(String voterId, String candidateId) {
        voterId = voterId.trim();
        candidateId = candidateId.trim();

        if (!voters.containsKey(voterId)) {
            System.out.println("❌ Invalid Voter ID!");
            return;
        }

        Voter voter = voters.get(voterId);
        if (voter.hasVoted()) {
            System.out.println("⚠️ You have already voted!");
            return;
        }

        if (!candidates.containsKey(candidateId)) {
            System.out.println("❌ Invalid Candidate ID!");
            return;
        }

        votes.put(candidateId, votes.get(candidateId) + 1);
        voter.setVoted(true);
        System.out.println("🗳️ Vote recorded for " + candidates.get(candidateId).getName());
    }

    public void showResults() {
        System.out.println("\n📊 Election Results:");
        if (votes.isEmpty()) { System.out.println("No votes recorded."); return; }

        votes.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println(candidates.get(e.getKey()).getName() + " (" + e.getKey() + ") → " + e.getValue() + " votes"));

        votes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println("\n🏆 Winner: " + candidates.get(e.getKey()).getName() + " (ID: " + e.getKey() + ") with " + e.getValue() + " votes"));
    }
}
