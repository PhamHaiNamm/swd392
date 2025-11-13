package model;

public enum GraduationRank {
    EXCELLENCE("Excellence"),
    GOOD("Good"),
    FAIR("Fair"),
    POOR("Poor");

    private final String displayName;

    GraduationRank(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static GraduationRank fromString(String value) {
        if (value == null) {
            return null;
        }
        for (GraduationRank rank : values()) {
            if (rank.displayName.equalsIgnoreCase(value.trim())) {
                return rank;
            }
        }
        return null;
    }
}

