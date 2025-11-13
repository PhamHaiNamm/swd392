package model;

public enum CandidateType {
    EXPERIENCE(0, "Experience"),
    FRESHER(1, "Fresher"),
    INTERN(2, "Intern");

    private final int code;
    private final String displayName;

    CandidateType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static CandidateType fromCode(int code) {
        for (CandidateType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}

