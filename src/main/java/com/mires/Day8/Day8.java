package com.mires.Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) {
        final TreeGrid treeGrid = new TreeGrid(200);
        try {
            final File file = new File("C:\\Users\\Mateusz\\Documents\\IdeaProjects\\AoC2022\\src\\main\\java\\com\\mires\\Day8\\input.txt");
            final Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                treeGrid.addRow(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Total Count: " + treeGrid.getVisibleCount());
        System.out.println("Max Scenic Score: " + treeGrid.getMaxScenicScore());

    }

    private static class TreeGrid {

        int[][] trees;
        int rows;
        int columns;

        public TreeGrid(int size) {
            this.trees = new int[size][size];
            this.rows = 0;
            this.columns = 0;
        }

        public void addRow(String input) {
            columns = input.length();
            String[] heights = input.split("");
            int i = 0;
            for (String height: heights) {
                trees[rows][i] = Integer.parseInt(height);
                i++;
            }
            rows++;
        }

        public int getVisibleCount() {
            boolean[][] visibility = getVisibilityArray();
            for (int i = 0; i < rows; i++) {
                checkVisibilityRow(visibility, i);
            }
            for (int i = 0; i < columns; i++) {
                checkVisibilityColumn(visibility, i);
            }
            return countVisible(visibility);
        }

        public int getMaxScenicScore() {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    int score = getScenicScore(i, j);
                    max = Integer.max(max, score);
                }
            }
            return max;
        }

        private boolean[][] getVisibilityArray() {
            boolean[][] visibility = new boolean[rows][columns];
            for (boolean[] row: visibility) {
                Arrays.fill(row, false);
            }
            return visibility;
        }

        private void checkVisibilityRow(boolean[][] visibility, int row) {
            if (row == 0 || row == rows - 1) {
                for (int i = 0; i < columns; i++) {
                    visibility[row][i] = true;
                }
                return;
            }
            int leftMax = Integer.MIN_VALUE;
            for (int i = 0; i < columns; i++) {
                if (trees[row][i] > leftMax) {
                    visibility[row][i] = true;
                    leftMax = trees[row][i];
                }
            }
            int rightMax = Integer.MIN_VALUE;
            for (int i = columns - 1; i >= 0; i--) {
                if (trees[row][i] > rightMax) {
                    visibility[row][i] = true;
                    rightMax = trees[row][i];
                }
            }
        }

        private void checkVisibilityColumn(boolean[][] visibility, int column) {
            if (column == 0 || column == columns - 1) {
                for (int i = 0; i < rows; i++) {
                    visibility[i][column] = true;
                }
                return;
            }
            int topMax = Integer.MIN_VALUE;
            for (int i = 0; i < rows; i++) {
                if (trees[i][column] > topMax) {
                    visibility[i][column] = true;
                    topMax = trees[i][column];
                }
            }
            int bottomMax = Integer.MIN_VALUE;
            for (int i = rows - 1; i >= 0; i--) {
                if (trees[i][column] > bottomMax) {
                    visibility[i][column] = true;
                    bottomMax = trees[i][column];
                }
            }
        }

        public int countVisible(boolean[][] visibility) {
            int count = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    count += visibility[i][j] ? 1 : 0;
                }
            }
            return count;
        }

        private int getScenicScore(int row, int column) {
            int top = getTopVisible(row, column);
            int bottom = getBottomVisible(row, column);
            int left = getLeftVisible(row, column);
            int right = getRightVisible(row, column);
            return top * bottom * left * right;
        }

        private int getTopVisible(int row, int column) {
            if (row == 0) {
                return 0;
            }
            int count = 0;
            int i = row - 1;
            while (i >= 0 && trees[i][column] < trees[row][column]) {
                count++;
                i--;
            }
            return count + (i >= 0 ? 1 : 0);
        }

        private int getBottomVisible(int row, int column) {
            if (row == rows - 1) {
                return 0;
            }
            int count = 0;
            int i = row + 1;
            while (i < rows && trees[i][column] < trees[row][column]) {
                count++;
                i++;
            }
            return count + (i < rows ? 1 : 0);
        }

        private int getLeftVisible(int row, int column) {
            if (column == 0) {
                return 0;
            }
            int count = 0;
            int i = column - 1;
            while (i >= 0 && trees[row][i] < trees[row][column]) {
                count++;
                i--;
            }
            return count + (i >= 0 ? 1 : 0);
        }

        private int getRightVisible(int row, int column) {
            if (column == columns - 1) {
                return 0;
            }
            int count = 0;
            int i = column + 1;
            while (i < columns && trees[row][i] < trees[row][column]) {
                count++;
                i++;
            }
            return count + (i < columns ? 1 : 0);
        }
    }

}
