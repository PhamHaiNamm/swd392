package controller;

import java.util.List;

import model.Candidate;
import model.CandidateType;
import model.GraduationRank;
import service.CandidateService;
import util.InputValidator;
import view.CandidateView;

public class CandidateController {
    private final CandidateService candidateService;
    private final CandidateView candidateView;

    public CandidateController(CandidateService candidateService, CandidateView candidateView) {
        this.candidateService = candidateService;
        this.candidateView = candidateView;
    }

    public void start() {
        boolean running = true;
        while (running) {
            candidateView.displayMainMenu();
            String choice = candidateView.prompt("");
            switch (choice) {
                case "1":
                    handleCreateCandidate(CandidateType.EXPERIENCE);
                    break;
                case "2":
                    handleCreateCandidate(CandidateType.FRESHER);
                    break;
                case "3":
                    handleCreateCandidate(CandidateType.INTERN);
                    break;
                case "4":
                    handleSearchCandidate();
                    break;
                case "5":
                    running = false;
                    candidateView.showMessage("Thoát chương trình. Tạm biệt!");
                    break;
                default:
                    candidateView.showMessage("Lựa chọn không hợp lệ. Vui lòng chọn từ 1 đến 5.");
            }
        }
    }

    private void handleCreateCandidate(CandidateType type) {
        boolean continueCreating = true;
        while (continueCreating) {
            Candidate candidate = createCandidateByType(type);
            candidateView.showMessage("Tạo ứng viên thành công: " + candidate.getFullName());
            String continueChoice = candidateView.prompt("Do you want to continue (Y/N)? ");
            continueCreating = continueChoice.equalsIgnoreCase("Y");
        }
        candidateView.displayAllCandidates(candidateService.getAllCandidates());
    }

    private Candidate createCandidateByType(CandidateType type) {
        String firstName = promptNonEmpty("Nhập First Name: ");
        String lastName = promptNonEmpty("Nhập Last Name: ");
        int birthYear = promptBirthYear();
        String address = promptNonEmpty("Nhập Address: ");
        String phone = promptPhone();
        String email = promptEmail();

        switch (type) {
            case EXPERIENCE:
                int yearsOfExperience = promptYearOfExperience();
                String professionalSkill = promptNonEmpty("Nhập Professional Skill: ");
                return candidateService.createExperienceCandidate(
                        firstName,
                        lastName,
                        birthYear,
                        address,
                        phone,
                        email,
                        yearsOfExperience,
                        professionalSkill);
            case FRESHER:
                int graduationYear = promptGraduationYear();
                GraduationRank graduationRank = promptGraduationRank();
                String education = promptNonEmpty("Nhập Education: ");
                return candidateService.createFresherCandidate(
                        firstName,
                        lastName,
                        birthYear,
                        address,
                        phone,
                        email,
                        graduationYear,
                        graduationRank,
                        education);
            case INTERN:
                String majors = promptNonEmpty("Nhập Majors: ");
                int semester = promptSemester();
                String universityName = promptNonEmpty("Nhập University Name: ");
                return candidateService.createInternCandidate(
                        firstName,
                        lastName,
                        birthYear,
                        address,
                        phone,
                        email,
                        majors,
                        semester,
                        universityName);
            default:
                throw new IllegalArgumentException("Loại ứng viên không được hỗ trợ: " + type);
        }
    }

    private void handleSearchCandidate() {
        List<Candidate> allCandidates = candidateService.getAllCandidates();
        if (allCandidates.isEmpty()) {
            candidateView.showMessage("Chưa có ứng viên nào trong hệ thống.");
            return;
        }
        candidateView.displayAllCandidates(allCandidates);
        String keyword = promptNonEmpty("Input Candidate name (First name or Last name): ");
        CandidateType type = promptCandidateType();
        List<Candidate> results = candidateService.searchCandidates(keyword, type);
        candidateView.displaySearchResults(results);
    }

    private String promptNonEmpty(String message) {
        while (true) {
            String value = candidateView.prompt(message);
            if (!InputValidator.isNullOrBlank(value)) {
                return value.trim();
            }
            candidateView.showMessage("Giá trị không được để trống. Vui lòng nhập lại.");
        }
    }

    private int promptBirthYear() {
        while (true) {
            String value = candidateView.prompt("Nhập Birth Year (YYYY): ");
            if (InputValidator.isValidBirthYear(value)) {
                return Integer.parseInt(value);
            }
            candidateView.showMessage("Birth Year phải là số 4 chữ số trong khoảng 1900 - "
                    + Candidate.getCurrentYear() + ".");
        }
    }

    private String promptPhone() {
        while (true) {
            String value = candidateView.prompt("Nhập Phone: ");
            if (InputValidator.isValidPhone(value)) {
                return value;
            }
            candidateView.showMessage("Phone phải là số với tối thiểu 10 ký tự.");
        }
    }

    private String promptEmail() {
        while (true) {
            String value = candidateView.prompt("Nhập Email: ");
            if (InputValidator.isValidEmail(value)) {
                return value;
            }
            candidateView.showMessage("Email không đúng định dạng. Ví dụ: example@domain.com");
        }
    }

    private int promptYearOfExperience() {
        while (true) {
            String value = candidateView.prompt("Nhập Years of Experience (0-100): ");
            try {
                int years = Integer.parseInt(value);
                if (InputValidator.isValidYearOfExperience(years)) {
                    return years;
                }
            } catch (NumberFormatException ignored) {
                // continue prompt
            }
            candidateView.showMessage("Years of Experience phải là số trong khoảng 0 - 100.");
        }
    }

    private int promptGraduationYear() {
        while (true) {
            String value = candidateView.prompt("Nhập Graduation Year (YYYY): ");
            try {
                int year = Integer.parseInt(value);
                if (InputValidator.isValidGraduationYear(year)) {
                    return year;
                }
            } catch (NumberFormatException ignored) {
                // continue
            }
            candidateView.showMessage("Graduation Year phải là số 4 chữ số trong khoảng hợp lệ.");
        }
    }

    private GraduationRank promptGraduationRank() {
        while (true) {
            String value = candidateView.prompt("Nhập Rank of Graduation (Excellence, Good, Fair, Poor): ");
            GraduationRank rank = GraduationRank.fromString(value);
            if (rank != null) {
                return rank;
            }
            candidateView.showMessage("Rank không hợp lệ. Chỉ chấp nhận: Excellence, Good, Fair, Poor.");
        }
    }

    private int promptSemester() {
        while (true) {
            String value = candidateView.prompt("Nhập Semester (>=1): ");
            try {
                int semester = Integer.parseInt(value);
                if (InputValidator.isValidSemester(semester)) {
                    return semester;
                }
            } catch (NumberFormatException ignored) {
                // continue
            }
            candidateView.showMessage("Semester phải là số nguyên >= 1.");
        }
    }

    private CandidateType promptCandidateType() {
        while (true) {
            String value = candidateView.prompt("Input type of candidate (0-Experience, 1-Fresher, 2-Intern): ");
            try {
                int code = Integer.parseInt(value);
                CandidateType type = CandidateType.fromCode(code);
                if (type != null) {
                    return type;
                }
            } catch (NumberFormatException ignored) {
                // continue
            }
            candidateView.showMessage("Loại ứng viên không hợp lệ. Vui lòng nhập 0, 1 hoặc 2.");
        }
    }
}

