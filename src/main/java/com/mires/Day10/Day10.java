package com.mires.Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) {
        final List<String> inputArray = new ArrayList<>();
        try {
            final File inputFile = new File("C:\\Users\\Mateusz\\IdeaProjects\\WŁASNE PROJEKTY\\AdventOfCode2022\\AoC2022\\src\\main\\java\\com\\mires\\Day10\\input.txt");
            final Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int x = 1;
        int cycles = 0;
        int cycles2 = 0;
        int signalStrength = 0;
        List<Integer> crtIndexList = Arrays.asList(0, 1, 2);
        StringBuilder sb = new StringBuilder();
        for (String s : inputArray) {
            System.out.println("Indexy: " + crtIndexList.get(0) + " " + crtIndexList.get(1) + " " + crtIndexList.get(2));
            if (s.equals("noop")) {
                cycles++;
                cycles2++;
                if (crtIndexList.contains(cycles2 - 1)) {
                    sb.append("█");
                } else {
                    sb.append(" ");
                }
                if (cycles == 20 || cycles == 60 || cycles == 100 || cycles == 140 || cycles == 180 || cycles == 220) signalStrength += x * cycles;
                if (cycles2 % 40 == 0 && cycles2 != 0) cycles2 -=40;
                continue;
            }

            for (int i = 0; i < 2; i++) {
                cycles++;
                cycles2++;
                if (crtIndexList.contains(cycles2 - 1)) {
                    sb.append("█");
                } else {
                    sb.append(" ");
                }
                if (cycles == 20 || cycles == 60 || cycles == 100 || cycles == 140 || cycles == 180 || cycles == 220) signalStrength += x * cycles;
                if (cycles2 % 40 == 0 && cycles2 != 0) cycles2 -=40;
            }
            final int toAdd = Integer.parseInt(s.split(" ")[1]);
            x += toAdd;
            crtIndexList.set(0, crtIndexList.get(0) + toAdd);
            crtIndexList.set(1, crtIndexList.get(1) + toAdd);
            crtIndexList.set(2, crtIndexList.get(2) + toAdd);
        }




        System.out.println(cycles);
        System.out.println("Signal strength: " + signalStrength);

        System.out.println("Printed Output: "); //EALGULPG
        System.out.println(sb.substring(0, 40));
        System.out.println(sb.substring(40, 80));
        System.out.println(sb.substring(80, 120));
        System.out.println(sb.substring(120, 160));
        System.out.println(sb.substring(160, 200));
        System.out.println(sb.substring(200, 240));

    }


}
