package com.evoting.app;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;


import com.evoting.registration.CandidateRegistration;
import com.evoting.registration.VoterRegistration;
import com.evoting.voting.VotingProcess;

public class Main {
    private static final DateTimeFormatter DOB_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CandidateRegistration candidateReg = new CandidateRegistration();
        VoterRegistration voterReg = new VoterRegistration();

        VotingProcess voting = null;
        int choice = 0;

        do {
            System.out.println("\n===== E-VOTING SYSTEM =====");
            System.out.println("1. Add Candidate For Election");
            System.out.println("2. Voter Registration");
            System.out.println("3. Show Candidates List");
            System.out.println("4. Vote");
            System.out.println("5. Show Election Results");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            try { choice = sc.nextInt(); sc.nextLine(); }
            catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Candidate Name: ");
                    String name = sc.nextLine();
                    System.out.print("DOB (dd-MM-yyyy): ");
                    String dobStr = sc.nextLine();
                    System.out.print("Area: ");
                    String area = sc.nextLine();

                    LocalDate dob = parseDob(dobStr);
                    if (dob != null) candidateReg.addCandidate(name, dob, area);
                }
                case 2 -> {
                    System.out.print("Voter Name: ");
                    String name = sc.nextLine();
                    System.out.print("DOB (dd-MM-yyyy): ");
                    String dobStr = sc.nextLine();

                    LocalDate dob = parseDob(dobStr);
                    if (dob != null) voterReg.registerVoter(name, dob);
                }
                case 3 -> candidateReg.showCandidates();
                case 4 -> {
                    if (voting == null)
                        voting = new VotingProcess(candidateReg.getCandidates(), voterReg.getVoters());

                    System.out.print("Voter ID: ");
                    String vid = sc.nextLine();
                    System.out.print("Candidate ID: ");
                    String cid = sc.nextLine();
                    voting.vote(vid, cid);
                }
                case 5 -> {
                    if (voting == null)
                        voting = new VotingProcess(candidateReg.getCandidates(), voterReg.getVoters());
                    voting.showResults();
                }
                case 6 -> System.out.println("Exiting. Thank you!");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 6);
        sc.close();
    }

    private static LocalDate parseDob(String dobStr) {
        try { return LocalDate.parse(dobStr.trim(), DOB_FORMAT); }
        catch (Exception e) {
            System.out.println(" Invalid date format! Use dd-MM-yyyy.");
            return null;
        }
    }
}
