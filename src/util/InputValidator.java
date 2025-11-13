package util;

import java.time.Year;
import java.util.regex.Pattern;

import model.Candidate;
import model.CandidateType;
import model.GraduationRank;

public final class InputValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("^\\d+$");

    private InputValidator() {
    }

    public static boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidBirthYear(String value) {
        if (!DIGIT_PATTERN.matcher(value).matches() || value.length() != 4) {
            return false;
        }
        int year = Integer.parseInt(value);
        int currentYear = Candidate.getCurrentYear();
        return year >= 1900 && year <= currentYear;
    }

    public static boolean isValidPhone(String value) {
        return value != null
                && value.length() >= 10
                && DIGIT_PATTERN.matcher(value).matches();
    }

    public static boolean isValidEmail(String value) {
        return value != null && EMAIL_PATTERN.matcher(value).matches();
    }

    public static boolean isValidYearOfExperience(int yearOfExperience) {
        return yearOfExperience >= 0 && yearOfExperience <= 100;
    }

    public static boolean isValidGraduationRank(String value) {
        return GraduationRank.fromString(value) != null;
    }

    public static boolean isValidCandidateTypeCode(int code) {
        return CandidateType.fromCode(code) != null;
    }

    public static boolean isValidSemester(int semester) {
        return semester >= 1;
    }

    public static boolean isValidGraduationYear(int year) {
        int currentYear = Year.now().getValue();
        return year >= 1900 && year <= currentYear;
    }
}

