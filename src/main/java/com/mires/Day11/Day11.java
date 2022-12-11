package com.mires.Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {
    private static final Map<Integer, Monkey> monkeyMap = new HashMap<>();

    public static void main(String[] args) {
        final List<String> inputArray = new ArrayList<>();
        try {
            final File file = new File("C:\\Users\\Mateusz\\IdeaProjects\\WŁASNE PROJEKTY\\AdventOfCode2022\\AoC2022\\src\\main\\java\\com\\mires\\Day11\\input.txt");
            final Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int id = 0;
        for (String s : inputArray) {
            if (s.contains("Monkey ")) {
                id = Integer.parseInt(s.replaceAll("Monkey ", "").replaceAll(":", ""));
                monkeyMap.put(id, new Monkey(id));
            }
            if (s.contains("Starting items: ")) {
                final String[] items = s.replaceAll("Starting items: ", "").replaceAll(" ", "").split(",");
                for (String item : items) {
                    monkeyMap.get(id).addItemWorryLevel(Integer.parseInt(item));
                }
            }
            if (s.contains("Operation: ")) {
                monkeyMap.get(id).setOperation(s.replaceAll("Operation:", "").replaceAll(" ", "").replace("new=old", ""));
            }
            if (s.contains("Test: ")) {
                monkeyMap.get(id).setDivisible(Integer.parseInt(s.replaceAll("Test:", "").replaceAll(" ", "").replace("divisibleby", "")));
            }
            if (s.contains("If true: ")) {
                monkeyMap.get(id).setMonkeyIdIfTrue(Integer.parseInt(s.replaceAll("If true: ", "").replaceAll(" ", "").replace("throwtomonkey", "")));
            }
            if (s.contains("If false: ")) {
                monkeyMap.get(id).setMonkeyIdIfFalse(Integer.parseInt(s.replaceAll("If false: ", "").replaceAll(" ", "").replace("throwtomonkey", "")));
            }
        }
        startRounds();

        List<Integer> inspections = new ArrayList<>();
        for (Monkey monkey : monkeyMap.values()) {
            inspections.add(monkey.getInspections());
        }
        Collections.sort(inspections);
        System.out.println("Monkey Business Level: " + (inspections.get(inspections.size() - 1) * inspections.get(inspections.size() - 2))); //108240
    }

    private static void startRounds() {
        for (int j = 0; j < 20; j++) {
            for (Monkey monkey : monkeyMap.values()) {
                switch (monkey.getOperation().charAt(0) + "") {
                    case "*" -> {
                        final List<Integer> toRemove = new ArrayList<>();
                        for (int i = 0; i < monkey.getItemsWorryLevel().size(); i++) {
                            int item = monkey.getItemsWorryLevel().get(i);
                            toRemove.add(item);
                            int toMultiply;
                            if (monkey.getOperation().contains("old")) {
                                toMultiply = item;
                            } else {
                                toMultiply = Integer.parseInt(monkey.getOperation().substring(1));
                            }
                            item = item * toMultiply;
                            item = (int) Math.floor(item / 3.0);

                            if (item % monkey.getDivisible() == 0) {
                                monkeyMap.get(monkey.getMonkeyIdIfTrue()).addItemWorryLevel(item);
                            } else {
                                monkeyMap.get(monkey.getMonkeyIdIfFalse()).addItemWorryLevel(item);
                            }

                            monkey.incrementInspections();
                        }
                        for (int i : toRemove) {
                            monkey.removeItemWorryLevel(i);
                        }
                    }
                    case "+" -> {
                        final List<Integer> toRemove = new ArrayList<>();
                        for (int i = 0; i < monkey.getItemsWorryLevel().size(); i++) {
                            int item = monkey.getItemsWorryLevel().get(i);
                            toRemove.add(item);
                            int toAdd;
                            if (monkey.getOperation().contains("old")) {
                                toAdd = item;
                            } else {
                                toAdd = Integer.parseInt(monkey.getOperation().substring(1));
                            }
                            item = item + toAdd;
                            item = (int) Math.floor(item / 3.0);

                            if (item % monkey.getDivisible() == 0) {
                                monkeyMap.get(monkey.getMonkeyIdIfTrue()).addItemWorryLevel(item);
                            } else {
                                monkeyMap.get(monkey.getMonkeyIdIfFalse()).addItemWorryLevel(item);
                            }
                            monkey.incrementInspections();
                        }
                        for (int i : toRemove) {
                            monkey.removeItemWorryLevel(i);
                        }
                    }
                }
            }
        }
    }


    public static class Monkey {
        private final int id;
        private int inspections;
        private final List<Integer> itemsWorryLevel;
        private String operation;
        private int divisible;
        private int monkeyIdIfTrue;
        private int monkeyIdIfFalse;

        public Monkey(final int id) {
            this.id = id;
            this.itemsWorryLevel = new ArrayList<>();
            this.operation = "";
            this.divisible = 0;
            this.monkeyIdIfTrue = 0;
            this.monkeyIdIfFalse = 0;
        }

        public int getInspections() {
            return inspections;
        }

        public void incrementInspections() {
            this.inspections++;
        }

        public void setMonkeyIdIfTrue(final int monkeyIdIfTrue) {
            this.monkeyIdIfTrue = monkeyIdIfTrue;
        }

        public void setMonkeyIdIfFalse(final int monkeyIdIfFalse) {
            this.monkeyIdIfFalse = monkeyIdIfFalse;
        }

        public int getMonkeyIdIfTrue() {
            return monkeyIdIfTrue;
        }

        public int getMonkeyIdIfFalse() {
            return monkeyIdIfFalse;
        }

        public void setOperation(final String operation) {
            this.operation = operation;
        }

        public String getOperation() {
            return operation;
        }

        public void setDivisible(final int divisible) {
            this.divisible = divisible;
        }

        public int getDivisible() {
            return divisible;
        }

        public List<Integer> getItemsWorryLevel() {
            return itemsWorryLevel;
        }

        public void addItemWorryLevel(final int worryLevel) {
            this.itemsWorryLevel.add(worryLevel);
        }


        public void removeItemWorryLevel(final int worryLevel) {
            for (Integer i : itemsWorryLevel) {
                if (i == worryLevel) {
                    itemsWorryLevel.remove(i);
                    break;
                }
            }
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "id=" + id + "\n" +
                    ", itemsWorryLevel=" + itemsWorryLevel + "\n" +
                    ", operation='" + operation + '\'' + "\n" +
                    ", divisible=" + divisible + "\n" +
                    ", monkeyIdIfTrue=" + monkeyIdIfTrue + "\n" +
                    ", monkeyIdIfFalse=" + monkeyIdIfFalse + "\n" +
                    '}';
        }
    }
}
