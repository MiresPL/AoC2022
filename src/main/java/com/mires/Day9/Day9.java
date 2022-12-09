package com.mires.Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 {
    private final static List<Possition> locations = new ArrayList<>();
    public static void main(String[] args) {
        final List<String> inputArray = new ArrayList<>();
        final Head head = new Head(0,0);
        try {
            final File inputFile = new File("C:\\Users\\Mateusz\\IdeaProjects\\WŁASNE PROJEKTY\\AdventOfCode2022\\AoC2022\\src\\main\\java\\com\\mires\\Day9\\input.txt");
            final Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (String s : inputArray) {
            addPoss(head, s);
        }

        System.out.println("lokalizacje: " + locations.size());

    }

    private static void addPoss(final Head head, final String input) {
        final String[] split = input.split(" ");
        for (int i = 0; i < Integer.parseInt(split[1]); i++) {
            switch (split[0]) {
                case "U" -> head.addY(1);
                case "D" -> head.addY(-1);
                case "L" -> head.addX(-1);
                case "R" -> head.addX(1);
            }
            final Possition pos = new Possition(head.getTail().getX(), head.getTail().getY());
            boolean isThere = false;
            for (final Possition possition : locations) {
                if (possition.getX() == pos.getX() && possition.getY() == pos.getY()) {
                    isThere = true;
                    break;
                }
            }
            if (isThere) continue;
            locations.add(pos);
        }
    }


    public static class Head {
        private int x, y;
        private final Tail tail;

        public Head(int x, int y) {
            this.x = x;
            this.y = y;
            this.tail = new Tail(x, y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void addX(int x) {
            this.x += x;
            this.keepTail();
        }

        public void addY(int y) {
            this.y += y;
            this.keepTail();
        }

        public Tail getTail() {
            return tail;
        }

        private void keepTail() {

            if (this.getX() - this.getTail().getX() == 1 && this.getY() - this.getTail().getY() > 1) {
                this.tail.addY(1);
                this.tail.addX(1);
            } else if (this.getX() - this.getTail().getX() == 1 && this.getTail().getY() - this.getY() > 1) {
                this.tail.addY(-1);
                this.tail.addX(1);
            } else if (this.getTail().getX() - this.getX() == 1 && this.getTail().getY() - this.getY() > 1) {
                this.tail.addY(-1);
                this.tail.addX(-1);
            } else if (this.getTail().getX() - this.getX() == 1 && this.getY() - this.getTail().getY() > 1) {
                this.tail.addY(1);
                this.tail.addX(-1);
            } else if (this.getX() - this.getTail().getX() > 1 && this.getY() - this.getTail().getY() == 1) {
                this.tail.addY(1);
                this.tail.addX(1);
            } else if (this.getTail().getX() - this.getX() > 1 && this.getY() - this.getTail().getY() == 1) {
                this.tail.addY(1);
                this.tail.addX(-1);
            } else if (this.getTail().getX() - this.getX() > 1 && this.getTail().getY() - this.getY() == 1) {
                this.tail.addY(-1);
                this.tail.addX(-1);
            } else if (this.getX() - this.getTail().getX() > 1 && this.getTail().getY() - this.getY() == 1) {
                this.tail.addY(-1);
                this.tail.addX(1);
            }



            if (this.getX() - this.tail.getX() > 1) {
                this.tail.addX(1);
            } else if (this.tail.getX() - this.getX() > 1) {
                this.tail.addX(-1);
            }
            if (this.getY() - this.getTail().getY() > 1) {
                this.tail.addY(1);
            } else if (this.getTail().getY() - this.getY() > 1) {
                this.tail.addY(-1);
            }
        }
    }

    public static class Tail {
        private int x, y;

        public Tail(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void addX(int x) {
            this.x += x;
        }

        public void addY(int y) {
            this.y += y;
        }
    }

    public static class Possition {
        private final int x, y;

        public Possition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Possition{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
