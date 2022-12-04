package com.mires.Day4;


import com.google.common.collect.Range;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        final List<String> inputArray = new ArrayList<>();
        int count = 0;
        int overlaps = 0;
        try {
            final File inputFile = new File("C:\\Users\\Mateusz\\IdeaProjects\\WŁASNE PROJEKTY\\AdventOfCode2022\\AoC2022\\src\\main\\java\\com\\mires\\Day4\\input.txt");
            final Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }

            for (String s : inputArray) {
                final String[] split = s.split(",");

                final int x1 = Integer.parseInt(split[0].split("-")[0]);
                final int x2 = Integer.parseInt(split[0].split("-")[1]);
                final int x3 = Integer.parseInt(split[1].split("-")[0]);
                final int x4 = Integer.parseInt(split[1].split("-")[1]);

                Range<Integer> range = Range.closed(x1, x2);

                Range<Integer> range2 = Range.closed(x3, x4);

                if (range.encloses(range2) || range2.encloses(range)) {
                    count++;
                }

                if (range.isConnected(range2) || range2.isConnected(range)) {
                    overlaps++;
                }

            }
            System.out.println("Liczba Powtorzen: " + count); // 580
            System.out.println("Liczba Przeciec: " + overlaps); // 895

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
