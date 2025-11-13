package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.Candidate;
import model.CandidateType;

public class CandidateRepository {
    private final List<Candidate> candidates = new ArrayList<>();

    public void save(Candidate candidate) {
        candidates.add(candidate);
    }

    public List<Candidate> findAll() {
        return Collections.unmodifiableList(candidates);
    }

    public List<Candidate> findByType(CandidateType type) {
        return candidates.stream()
                .filter(candidate -> candidate.getCandidateType() == type)
                .collect(Collectors.toList());
    }

    public List<Candidate> searchByNameAndType(String keyword, CandidateType type) {
        String normalizedKeyword = keyword.trim().toLowerCase();
        return candidates.stream()
                .filter(candidate -> candidate.getCandidateType() == type)
                .filter(candidate ->
                        candidate.getFirstName().toLowerCase().contains(normalizedKeyword)
                                || candidate.getLastName().toLowerCase().contains(normalizedKeyword))
                .collect(Collectors.toList());
    }
}

