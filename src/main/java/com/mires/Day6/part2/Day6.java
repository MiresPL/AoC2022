package com.mires.Day6.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        String input = "";
        try {
            final File inputFile = new File("C:\\Users\\Mateusz\\IdeaProjects\\WŁASNE PROJEKTY\\AdventOfCode2022\\AoC2022\\src\\main\\java\\com\\mires\\Day6\\input.txt");
            final Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder marker = new StringBuilder();
        StringBuilder past = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (marker.length() == 14) {
                break;
            }
            if (marker.toString().contains(input.charAt(i) + "")) {
                marker = new StringBuilder(marker.substring(marker.indexOf(input.charAt(i) + "") + 1));
            }
            marker.append(input.charAt(i));
            past.append(input.charAt(i));
        }

        System.out.println("Znaleziono Marker po: " + past.length() + " znakach"); // 3708
    }
}
