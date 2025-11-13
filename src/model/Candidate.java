package model;

import java.time.Year;

public abstract class Candidate {
    private final String candidateId;
    private final String firstName;
    private final String lastName;
    private final int birthYear;
    private final String address;
    private final String phone;
    private final String email;
    private final CandidateType candidateType;

    protected Candidate(String candidateId,
                        String firstName,
                        String lastName,
                        int birthYear,
                        String address,
                        String phone,
                        String email,
                        CandidateType candidateType) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.candidateType = candidateType;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public CandidateType getCandidateType() {
        return candidateType;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String toSummaryString() {
        return String.format("%s | %d | %s | %s | %s | %d",
                getFullName(),
                birthYear,
                address,
                phone,
                email,
                candidateType.getCode());
    }

    public static int getCurrentYear() {
        return Year.now().getValue();
    }
}

