package com.cholnhial.day2;

import util.FileUtil;

public class Day2 {

    private static void solvePartOne() throws Exception  {
        int horizontalPosition = 0;
        int depth = 0;

        FileUtil fileUtil = new FileUtil();
        var lines = fileUtil.readFileFromResource("day2.input.txt");

        for (String line : lines) {
            String[] command = line.split(" ");
            int amount = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "forward":
                    horizontalPosition += amount;
                    break;
                case "down":
                    depth += amount;
                    break;
                case "up":
                    depth -= amount;
                    break;
                default:
                    // do nothing
            }
        }

        System.out.println("Solution 1: " + horizontalPosition * depth);
    }


    private static void solvePartTwo() throws Exception  {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        FileUtil fileUtil = new FileUtil();
        var lines = fileUtil.readFileFromResource("day2.input.txt");

        for (String line : lines) {
            String[] command = line.split(" ");
            int amount = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "forward":
                    horizontalPosition += amount;
                    depth += (aim * amount);
                    break;
                case "down":
                    aim += amount;
                    break;
                case "up":
                    aim -= amount;
                    break;
                default:
                    // do nothing
            }
        }

        System.out.println("Solution 2: " + horizontalPosition * depth);
    }

    public static void main(String... args) throws Exception {
        solvePartOne();
        solvePartTwo();
    }
}
