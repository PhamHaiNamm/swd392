package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Candidate;
import model.CandidateType;

public class CandidateView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMainMenu() {
        System.out.println("=========== CANDIDATE MANAGEMENT SYSTEM ===========");
        System.out.println("1. Create Experience Candidate");
        System.out.println("2. Create Fresher Candidate");
        System.out.println("3. Create Intern Candidate");
        System.out.println("4. Search Candidate");
        System.out.println("5. Exit");
        System.out.print("Select an option (1-5): ");
    }

    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void displayAllCandidates(List<Candidate> candidates) {
        System.out.println("List of candidate:");
        for (CandidateType type : CandidateType.values()) {
            System.out.println("===========" + type.getDisplayName().toUpperCase() + " CANDIDATE============");
            String names = candidates.stream()
                    .filter(candidate -> candidate.getCandidateType() == type)
                    .map(Candidate::getFullName)
                    .collect(Collectors.joining("\n"));
            if (names.isEmpty()) {
                System.out.println("(Không có ứng viên)");
            } else {
                System.out.println(names);
            }
        }
    }

    public void displaySearchResults(List<Candidate> results) {
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy ứng viên nào thỏa mãn.");
            return;
        }
        System.out.println("The candidates found:");
        for (Candidate candidate : results) {
            System.out.println(candidate.toSummaryString());
        }
    }
}

