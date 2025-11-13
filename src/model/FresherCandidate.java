package model;

public class FresherCandidate extends Candidate {
    private final int graduationYear;
    private final GraduationRank graduationRank;
    private final String education;

    public FresherCandidate(String candidateId,
                            String firstName,
                            String lastName,
                            int birthYear,
                            String address,
                            String phone,
                            String email,
                            int graduationYear,
                            GraduationRank graduationRank,
                            String education) {
        super(candidateId, firstName, lastName, birthYear, address, phone, email, CandidateType.FRESHER);
        this.graduationYear = graduationYear;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public GraduationRank getGraduationRank() {
        return graduationRank;
    }

    public String getEducation() {
        return education;
    }
}

