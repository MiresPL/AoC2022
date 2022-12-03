package com.mires.Day2;

import com.mires.Day2.enums.Outcome;
import com.mires.Day2.enums.Shapes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) {
        int points = 0;
        final List<String> inputArray = new ArrayList<>();
        try {
            final File inputFile = new File("C:\\Users\\Mateusz\\IdeaProjects\\WŁASNE PROJEKTY\\AdventOfCode2022\\AoC2022\\src\\main\\java\\com\\mires\\Day2\\input.txt");
            final Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }

            scanner.close();

            for (String s : inputArray) {
                final Shapes opponent = Shapes.getShape(s.split(" ")[0].replace(" ", ""));
                final Shapes you = Shapes.getShape(s.split(" ")[1].replace(" ", ""));

                assert you != null;
                assert opponent != null;

                points += Shapes.getOutcomePoints(you, opponent);
            }
            System.out.println("Finalny Wynik: " + points);

            System.out.println("Part 2");

            int points2 = 0;
            for (String s : inputArray) {
                final Outcome outcome = Outcome.getOutcome(s.split(" ")[1]);
                final Shapes opponent = Shapes.getShape(s.split(" ")[0]);


                assert opponent != null;
                points2 += getOutcomePointsFromOutcome(outcome, opponent);
            }

            System.out.println("Wynik po wytłumaczeniu: " + points2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int getOutcomePointsFromOutcome(final Outcome outcome, final Shapes oponent) {
        Shapes you = null;
        switch (oponent) {
            case ROCK:
                if (outcome == Outcome.WIN){
                    you = Shapes.PAPER;
                } else if (outcome == Outcome.LOSS){
                    you = Shapes.SCISSORS;
                } else {
                    you = Shapes.ROCK;
                }
                break;
            case PAPER:
                if (outcome == Outcome.WIN){
                    you = Shapes.SCISSORS;
                } else if (outcome == Outcome.LOSS){
                    you = Shapes.ROCK;
                } else {
                    you = Shapes.PAPER;
                }
                break;
            case SCISSORS:
                if (outcome == Outcome.WIN){
                    you = Shapes.ROCK;
                } else if (outcome == Outcome.LOSS){
                    you = Shapes.PAPER;
                } else {
                    you = Shapes.SCISSORS;
                }
                break;
        }
        assert you != null;
        return you.getPoints() + outcome.getPoints();
    }
}
