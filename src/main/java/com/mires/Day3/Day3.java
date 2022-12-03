package com.mires.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {
    public static void main(String[] args) {
        int priority = 0;
        final List<String> inputArray = new ArrayList<>();
        try {
            final File file = new File("C:\\Users\\Mateusz\\IdeaProjects\\WŁASNE PROJEKTY\\AdventOfCode2022\\AoC2022\\src\\main\\java\\com\\mires\\Day3\\input.txt");
            final Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }

            scanner.close();

            for (String s : inputArray) {
                final String firstPart = s.substring(0, s.length()/2);
                final String secondPart = s.substring(s.length()/2);

                priority += getPriority(getSamePart(firstPart, secondPart));
            }

            System.out.println("Priority: " + priority); //7878

            int priority2 = 0;

            for (int i = 0; i < inputArray.size(); i+=3) {
                final Group group = new Group(inputArray.get(i), inputArray.get(i+1), inputArray.get(i+2));
                priority2 += getPriority(group.findSamePart());
            }

            System.out.println("Badge priority: " + priority2); //2760


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getSamePart(final String firstPart, final String secondPart) {
        for (int i = 0; i < secondPart.length(); i++) {
            if (firstPart.contains(String.valueOf(secondPart.charAt(i)))) {
                return String.valueOf(secondPart.charAt(i));
            }
        }
        return "";
    }

    private static int getPriority(final String s) {
        return switch (s) {
            case "a" -> 1;
            case "b" -> 2;
            case "c" -> 3;
            case "d" -> 4;
            case "e" -> 5;
            case "f" -> 6;
            case "g" -> 7;
            case "h" -> 8;
            case "i" -> 9;
            case "j" -> 10;
            case "k" -> 11;
            case "l" -> 12;
            case "m" -> 13;
            case "n" -> 14;
            case "o" -> 15;
            case "p" -> 16;
            case "q" -> 17;
            case "r" -> 18;
            case "s" -> 19;
            case "t" -> 20;
            case "u" -> 21;
            case "v" -> 22;
            case "w" -> 23;
            case "x" -> 24;
            case "y" -> 25;
            case "z" -> 26;
            case "A" -> 27;
            case "B" -> 28;
            case "C" -> 29;
            case "D" -> 30;
            case "E" -> 31;
            case "F" -> 32;
            case "G" -> 33;
            case "H" -> 34;
            case "I" -> 35;
            case "J" -> 36;
            case "K" -> 37;
            case "L" -> 38;
            case "M" -> 39;
            case "N" -> 40;
            case "O" -> 41;
            case "P" -> 42;
            case "Q" -> 43;
            case "R" -> 44;
            case "S" -> 45;
            case "T" -> 46;
            case "U" -> 47;
            case "V" -> 48;
            case "W" -> 49;
            case "X" -> 50;
            case "Y" -> 51;
            case "Z" -> 52;
            default -> 0;
        };
    }


    protected static class Group {
        protected String elf1, elf2, elf3;

        protected Group(String elf1, String elf2, String elf3) {
            this.elf1 = elf1;
            this.elf2 = elf2;
            this.elf3 = elf3;
        }


        protected String findSamePart() {
            final List<String> array = Arrays.asList(elf1, elf2, elf3);
            Collections.sort(array);
            for (int i = 0; i < array.get(0).length(); i++) {
                if (array.get(1).contains(String.valueOf(array.get(0).charAt(i))) && array.get(2).contains(String.valueOf(array.get(0).charAt(i)))) {
                    return String.valueOf(array.get(0).charAt(i));
                }
            }
            return "";
        }

    }
}
