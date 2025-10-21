# E-Voting_System

Project Overview
------------------
The E-Voting System is a console-based Java application that simulates an electronic voting process. It allows candidate registration, voter registration, voting, and result display efficiently. The system automatically generates unique IDs for both candidates and voters, ensuring transparency and traceability.

Key Features
------------------
--Candidate Registration:

 Add candidates with Name, DOB, and Area
 Generates unique Candidate ID in the format: AreaInitial-NameInitials-DOB-Count
--Voter Registration:

    Register voters with Name and DOB (age ≥ 18)
    Generates unique Voter ID in the format: NameInitials-DOB-Count
--Voting Process:

    Validates voter and candidate IDs
    Ensures each voter can vote only once
    Efficient vote recording using TreeMap
--Result Display:

  Displays votes for each candidate
  Automatically declares the winner

Technologies Used
------------------
  Java SE 8+
  Java Collections Framework: HashMap, TreeMap
  Console-based application for simplicity and clarity

Project Flow
------------------
  --Candidate Registration → Generates candidate ID
  
  --Voter Registration → Generates voter ID
  
  --Voting Process → Validates and records vote
  
  --Results Display → Shows votes and declares the winner

  
