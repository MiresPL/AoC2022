package com.mires.Day2.enums;

public enum Shapes {
    ROCK("A","X",1),
    PAPER("B","Y",2),
    SCISSORS("C","Z",3);
    private final String shapeName;
    private final String shapeName2;
    private final int points;

    Shapes(final String shapeName, final String shapeName2, final int points) {
        this.shapeName = shapeName;
        this.shapeName2 = shapeName2;
        this.points = points;
    }

    public String getShapeName() {
        return shapeName;
    }

    public String getShapeName2() {
        return shapeName2;
    }

    public int getPoints() {
        return points;
    }

    public static Shapes getShape(String shapeName) {
        for (Shapes shape : Shapes.values()) {
            if (shape.getShapeName().equalsIgnoreCase(shapeName) || shape.getShapeName2().equalsIgnoreCase(shapeName)) {
                return shape;
            }
        }
        return null;
    }

    private static Outcome getOutcome(final Shapes you, final Shapes oponent) {
        switch (you) {
            case ROCK:
                if (oponent == PAPER) {
                    return Outcome.LOSS;
                } else if (oponent == SCISSORS) {
                    return Outcome.WIN;
                } else {
                    return Outcome.DRAW;
                }
            case PAPER:
                if (oponent == SCISSORS) {
                    return Outcome.LOSS;
                } else if (oponent == ROCK) {
                    return Outcome.WIN;
                } else {
                    return Outcome.DRAW;
                }
            case SCISSORS:
                if (oponent == ROCK) {
                    return Outcome.LOSS;
                } else if (oponent == PAPER) {
                    return Outcome.WIN;
                } else {
                    return Outcome.DRAW;
                }
        }
        return null;
    }

    public static int getOutcomePoints(final Shapes you, final Shapes oponent) {
        assert getOutcome(you, oponent) != null;
        return you.getPoints() + getOutcome(you, oponent).getPoints();
    }
}
