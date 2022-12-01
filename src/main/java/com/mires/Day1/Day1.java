package com.mires.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        final List<String> inputArray = new ArrayList<>();
        final List<Elf> elfArray = new ArrayList<>(3);
        try {
            final File inputFile = new File("C:\\Users\\Mateusz\\IdeaProjects\\WŁASNE PROJEKTY\\AdventOfCode2022\\AoC2022\\src\\main\\java\\com\\mires\\Day1\\input.txt");
            final Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }

            scanner.close();
            Elf elf = new Elf();
            for (int i = 0; i < inputArray.size(); i++) {
                if (!inputArray.get(i).isEmpty()) {
                    elf.add(Integer.parseInt(inputArray.get(i)));
                } else {
                    elfArray.add(elf);
                    elf = new Elf();
                }
            }

            int highest = 0;
            final List<Integer> highestList = new ArrayList<>();
            for (Elf elf1 : elfArray) {
                if (elf1.getCalories() > highest) {
                    highest = elf1.getCalories();
                }
            }
            highestList.add(highest);

            System.out.println("1 Najwieksza: " + highest);
            //ANSWER: 69528
            int secHighest = 0;
            for (Elf elf1 : elfArray) {
                if (elf1.getCalories() > secHighest && elf1.getCalories() < highest) {
                    secHighest = elf1.getCalories();
                }
            }
            highestList.add(secHighest);

            System.out.println(secHighest);

            int thirdHighest = 0;
            for (Elf elf1 : elfArray) {
                if (elf1.getCalories() > thirdHighest && elf1.getCalories() < secHighest && elf1.getCalories() < highest) {
                    thirdHighest = elf1.getCalories();
                }
            }
            highestList.add(thirdHighest);

            System.out.println(thirdHighest);

            System.out.println("Highest 3: " + highestList);
            System.out.println("Highest 3 sum: " + highestList.stream().mapToInt(Integer::intValue).sum());
            //ANSWER: 206152



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected static class Elf {
        protected int calories;

        protected Elf() {
            this.calories = 0;
        }

        protected void add(int calories) {
            this.calories += calories;
        }

        protected int getCalories() {
            return calories;
        }
    }
}
