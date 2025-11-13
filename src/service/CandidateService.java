package service;

import java.util.List;

import model.Candidate;
import model.CandidateType;
import model.ExperienceCandidate;
import model.FresherCandidate;
import model.GraduationRank;
import model.InternCandidate;
import repository.CandidateRepository;

public class CandidateService {
    private final CandidateRepository repository;
    private int experienceSequence = 1;
    private int fresherSequence = 1;
    private int internSequence = 1;

    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    public Candidate createExperienceCandidate(String firstName,
                                               String lastName,
                                               int birthYear,
                                               String address,
                                               String phone,
                                               String email,
                                               int yearsOfExperience,
                                               String professionalSkill) {
        String id = generateCandidateId(CandidateType.EXPERIENCE);
        ExperienceCandidate candidate = new ExperienceCandidate(
                id,
                firstName,
                lastName,
                birthYear,
                address,
                phone,
                email,
                yearsOfExperience,
                professionalSkill);
        repository.save(candidate);
        return candidate;
    }

    public Candidate createFresherCandidate(String firstName,
                                            String lastName,
                                            int birthYear,
                                            String address,
                                            String phone,
                                            String email,
                                            int graduationYear,
                                            GraduationRank graduationRank,
                                            String education) {
        String id = generateCandidateId(CandidateType.FRESHER);
        FresherCandidate candidate = new FresherCandidate(
                id,
                firstName,
                lastName,
                birthYear,
                address,
                phone,
                email,
                graduationYear,
                graduationRank,
                education);
        repository.save(candidate);
        return candidate;
    }

    public Candidate createInternCandidate(String firstName,
                                           String lastName,
                                           int birthYear,
                                           String address,
                                           String phone,
                                           String email,
                                           String majors,
                                           int semester,
                                           String universityName) {
        String id = generateCandidateId(CandidateType.INTERN);
        InternCandidate candidate = new InternCandidate(
                id,
                firstName,
                lastName,
                birthYear,
                address,
                phone,
                email,
                majors,
                semester,
                universityName);
        repository.save(candidate);
        return candidate;
    }

    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    public List<Candidate> getCandidatesByType(CandidateType type) {
        return repository.findByType(type);
    }

    public List<Candidate> searchCandidates(String keyword, CandidateType type) {
        return repository.searchByNameAndType(keyword, type);
    }

    private String generateCandidateId(CandidateType type) {
        int sequence;
        switch (type) {
            case EXPERIENCE:
                sequence = experienceSequence++;
                break;
            case FRESHER:
                sequence = fresherSequence++;
                break;
            case INTERN:
                sequence = internSequence++;
                break;
            default:
                sequence = 0;
        }
        return type.getDisplayName().substring(0, 1).toUpperCase() + String.format("%03d", sequence);
    }
}

