package model;

public class InternCandidate extends Candidate {
    private final String majors;
    private final int semester;
    private final String universityName;

    public InternCandidate(String candidateId,
                           String firstName,
                           String lastName,
                           int birthYear,
                           String address,
                           String phone,
                           String email,
                           String majors,
                           int semester,
                           String universityName) {
        super(candidateId, firstName, lastName, birthYear, address, phone, email, CandidateType.INTERN);
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    public String getMajors() {
        return majors;
    }

    public int getSemester() {
        return semester;
    }

    public String getUniversityName() {
        return universityName;
    }
}

