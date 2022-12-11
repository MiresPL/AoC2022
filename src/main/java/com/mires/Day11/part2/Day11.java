package com.mires.Day11.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Day11 {


    // Z DLUGI KOD I SIE NIE WYKONUJE (ZA DUZO PETLI)
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
                    monkeyMap.get(id).addItemWorryLevel(new BigInteger(item));
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
        System.out.println("Monkey Business Level: " + (inspections.get(inspections.size() - 1) * inspections.get(inspections.size() - 2))); //25712998901
    }

    private static void startRounds() {
        for (int j = 0; j < 1000 ;j++) {
            for (Monkey monkey : monkeyMap.values()) {
                switch (monkey.getOperation().charAt(0) + "") {
                    case "*" -> {
                        final List<BigInteger> toRemove = new ArrayList<>();
                        for (int i = 0; i < monkey.getItemsWorryLevel().size(); i++) {
                            BigInteger item = monkey.getItemsWorryLevel().get(i);
                            toRemove.add(item);
                            BigInteger toMultiply;
                            if (monkey.getOperation().contains("old")) {
                                toMultiply = item;
                            } else {
                                toMultiply = BigInteger.valueOf(Integer.parseInt(monkey.getOperation().substring(1)));
                            }
                            BigInteger multiplied = item.multiply(toMultiply);
                            BigInteger dividable = BigInteger.valueOf(monkey.getDivisible());
                            if (multiplied.remainder(dividable).equals(BigInteger.ZERO)) {
                                monkeyMap.get(monkey.getMonkeyIdIfTrue()).addItemWorryLevel(multiplied);
                            } else {
                                monkeyMap.get(monkey.getMonkeyIdIfFalse()).addItemWorryLevel(multiplied);
                            }

                            monkey.incrementInspections();
                        }
                        for (BigInteger i : toRemove) {
                            monkey.removeItemWorryLevel(i);
                        }
                    }
                    case "+" -> {
                        final List<BigInteger> toRemove = new ArrayList<>();
                        for (int i = 0; i < monkey.getItemsWorryLevel().size(); i++) {
                            BigInteger item = monkey.getItemsWorryLevel().get(i);
                            toRemove.add(item);
                            BigInteger toAdd;
                            if (monkey.getOperation().contains("old")) {
                                toAdd = item;
                            } else {
                                toAdd = BigInteger.valueOf(Integer.parseInt(monkey.getOperation().substring(1)));
                            }
                            BigInteger sum = item.add(toAdd);
                            BigInteger dividable = BigInteger.valueOf(monkey.getDivisible());

                            if (sum.remainder(dividable).equals(BigInteger.ZERO)) {
                                monkeyMap.get(monkey.getMonkeyIdIfTrue()).addItemWorryLevel(sum);
                            } else {
                                monkeyMap.get(monkey.getMonkeyIdIfFalse()).addItemWorryLevel(sum);
                            }

                            monkey.incrementInspections();
                        }
                        for (BigInteger i : toRemove) {
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
        private final List<BigInteger> itemsWorryLevel;
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

        public List<BigInteger> getItemsWorryLevel() {
            return itemsWorryLevel;
        }

        public void addItemWorryLevel(final BigInteger worryLevel) {
            this.itemsWorryLevel.add(worryLevel);
        }


        public void removeItemWorryLevel(final BigInteger worryLevel) {
            for (BigInteger i : itemsWorryLevel) {
                if (i.equals(worryLevel)) {
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
