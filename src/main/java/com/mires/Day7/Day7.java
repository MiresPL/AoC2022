package com.mires.Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {
    public static void main(String[] args) {
        final List<String> inputArray = new ArrayList<>();
        final Map<String, Directory> directories = new HashMap<>();
        try {
            final File inputFile = new File("C:\\Users\\Mateusz\\Documents\\IdeaProjects\\AoC2022\\src\\main\\java\\com\\mires\\Day7\\input.txt");
            final Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final StringBuilder directory = new StringBuilder();
        List<String> fileList = new ArrayList<>();
        for (String s : inputArray) {
            if (s.equals("$ cd ..")) {
                directory.reverse();
                directory.delete(0, directory.toString().replace("//", "/").indexOf("/", 1));
                directory.reverse();
                fileList = new ArrayList<>();
            } else if (s.contains("$ cd ")) {
                if (directories.containsKey(directory.toString().replace("//", "/"))) {
                    if (!fileList.isEmpty()) {
                        directories.get(directory.toString().replace("//", "/")).setFiles(fileList);
                    }
                }
                directory.append(s.replace("$ cd ", "")).append("/");
                fileList = new ArrayList<>();
                directories.put(directory.toString().replace("//", "/"), new Directory(directory.toString().replace("//", "/"), fileList));
            } else if (s.equals("$ ls")) continue;
            else {
                fileList.add(s);
                if (!s.contains("dir")) {
                    directories.get(directory.toString().replace("//", "/")).addSize(Integer.parseInt(s.split(" ")[0]));
                }
            }
        }


        int allSize = 0;
        int highestSize = 0; // PART 2
        for (Directory dir : directories.values()) {
            final int dirSize = countDirSize(dir.getPath(), directories);
            if (dirSize > highestSize) { // PART 2
                highestSize = dirSize;
            }
            if (dirSize < 100000) {
                allSize += dirSize;
            }
        }

        System.out.println("All size: " + allSize); // 1989474

        // PART 2
        final int unUsedSpace = 70_000_000 - highestSize;
        final int minimumSpaceNeeded = 30_000_000;
        int smallestDir = 0;
        for (Directory dir : directories.values()) {
            final int dirSize = countDirSize(dir.getPath(), directories);
            if (dirSize >= minimumSpaceNeeded - unUsedSpace) {
                if (smallestDir == 0) {
                    smallestDir = dirSize;
                } else if (dirSize < smallestDir) {
                    smallestDir = dirSize;
                }
            }
        }

        System.out.println("Smallest dir: " + smallestDir); // 1111607

    }


    public static int countDirSize(final String dir, final Map<String, Directory> directories) {
        int size = 0;
        for (String file : directories.get(dir).getFileList()) {
            if (file.contains("dir")) {
                size += countDirSize(directories.get(dir + file.replace("dir ", "") + "/").getPath(), directories);
            } else {
                size += Integer.parseInt(file.split(" ")[0]);
            }
        }
        return size;
    }




    protected static class Directory {
        private final String path;
        private List<String> fileList;
        private int size;

        protected Directory(String path, List<String> fileList) {
            this.path = path;
            this.fileList = fileList;
            this.size = 0;
        }

        protected String getPath() {
            return path;
        }

        protected List<String> getFileList() {
            return fileList;
        }

        protected void setFiles(List<String> fileList) {
            this.fileList = fileList;
        }

        protected void addSize(int size) {
            this.size += size;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("Path: ").append(path).append("\nSize: ").append(size).append("\nFiles: ");
            for (String s : fileList) {
                sb.append(s).append("\n");
            }
            return sb.toString();
        }

    }

}
