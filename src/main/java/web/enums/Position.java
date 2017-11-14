package web.enums;

import java.util.Random;

/**
 * Created by Anastasia on 31.10.2017.
 */

public enum Position {

    SOFTWARE_DEVELOPER("Software Developer"), SENIOR_ENGINEER("Senior Engineer"),
    TESTER("Tester"), PROJECT_MANAGER("Project Manager"), TEAM_LEADER("Team Leader"),
    DESKTOP_SUPPORT_ENGINEER("Desktop Support Engineer"),
    NETWORK_ADMINISTRATOR("Network Administrator"), ARCHITECT("Architect"),
    SYSTEM_ENGINEER("System Engineer");

    private static final Position[] VALUES = values();
    private static final int SIZE = VALUES.length;
    public static final Random RANDOM = new Random();

    private String label;

    Position(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static Position getRandomPosition() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
