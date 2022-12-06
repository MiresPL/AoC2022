package com.mires.Day5.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        final List<String> inputArray = new ArrayList<>();
        final Map<Integer, Stack> stackMap = new HashMap<>();
        try {
            final File inputFile = new File("C:\\Users\\Mateusz\\IdeaProjects\\WŁASNE PROJEKTY\\AdventOfCode2022\\AoC2022\\src\\main\\java\\com\\mires\\Day5\\input.txt");
            final Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 9; i++) {
            stackMap.put(i, new Stack());
        }


        for (String s : inputArray) {
            if (s.isEmpty() || s.contains(" 1   2   3")) break;
            s += " ";

            final List<String> toAdd = new ArrayList<>();
            for (int i = 0; i < s.length(); i+=4) {
                toAdd.add(s.substring(i, i + 4));
            }

            if (toAdd.size() < stackMap.size()) {
                for (int i = 0; i < stackMap.size() - toAdd.size(); i++) {
                    toAdd.add(" ");
                }
            }

            for (int i = 0; i < toAdd.size(); i++) {
                if (toAdd.get(i).replaceAll(" ", "").isEmpty()) continue;
                stackMap.get(i).addCreateFirst(toAdd.get(i).replaceAll(" ", ""));
            }

            for (int i = 0; i < stackMap.size(); i++) {
                stackMap.get(i).setTopCreate();
            }

        }


        for (String s : inputArray) {
            if (!s.contains("move")) continue;
            final String[] split = s.split(" ");
            final int amount = Integer.parseInt(split[1]);
            final int from = Integer.parseInt(split[3]);
            final int to = Integer.parseInt(split[5]);

            final List<String> toMove = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                toMove.add(stackMap.get(from - 1).removeCreate());
            }
            Collections.reverse(toMove);
            stackMap.get(to - 1).addCrates(toMove);
        }
        System.out.print("Top Crates: "); // LVMRWSSPZ
        for (int i = 0; i < stackMap.size(); i++) {
            System.out.print(stackMap.get(i).topCreate.replaceAll(" ", "").replace("[", "").replace("]", ""));
        }

    }


    protected static class Stack {
        private String topCreate = "";
        private final List<String> creates;

        public Stack() {
            this.creates = new ArrayList<>();
        }

        public void addCreateFirst(String create) {
            this.creates.add(create);
        }

        public void addCrates(List<String> crates) {
            for (int i = 0; i < crates.size(); i++) {
                this.creates.add(0, crates.get(i));
                if (i == crates.size() - 1) {
                    this.topCreate = crates.get(i);
                }
            }
        }

        public String removeCreate() {
            final String create = this.topCreate;
            this.creates.remove(0);
            if (this.creates.size() > 0) {
                this.topCreate = this.creates.get(0);
            } else {
                this.topCreate = "";
            }
            return create;
        }

        public void setTopCreate() {
            if (this.creates.isEmpty()) return;
            this.topCreate = this.creates.get(0);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("Top Create: ").append(this.topCreate).append("\n");
            for (int i = 0; i < this.creates.size(); i++) {
                sb.append(i).append(". ").append(this.creates.get(i)).append("\n");
            }
            return sb.toString();
        }
    }
}
