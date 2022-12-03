package com.mires.Day2.enums;

public enum Outcome {
    WIN("Z", 6),
    DRAW("Y", 3),
    LOSS("X", 0);
    private final String name;
    private final int points;

    Outcome(final String name, final int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public static Outcome getOutcome(String name) {
        for (Outcome outcome : Outcome.values()) {
            if (outcome.getName().equalsIgnoreCase(name)) {
                return outcome;
            }
        }
        return null;
    }
}
