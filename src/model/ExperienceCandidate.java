package model;

public class ExperienceCandidate extends Candidate {
    private final int yearsOfExperience;
    private final String professionalSkill;

    public ExperienceCandidate(String candidateId,
                               String firstName,
                               String lastName,
                               int birthYear,
                               String address,
                               String phone,
                               String email,
                               int yearsOfExperience,
                               String professionalSkill) {
        super(candidateId, firstName, lastName, birthYear, address, phone, email, CandidateType.EXPERIENCE);
        this.yearsOfExperience = yearsOfExperience;
        this.professionalSkill = professionalSkill;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getProfessionalSkill() {
        return professionalSkill;
    }
}

