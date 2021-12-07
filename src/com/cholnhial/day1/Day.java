package com.cholnhial.day1;

import util.FileUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Day {

    public static void main(String... args) throws Exception {
        solvePartOne();;
        solvePartTwo();
    }

    private static void solvePartOne() throws Exception {
        FileUtil fileUtil = new FileUtil();
        var lines = fileUtil.readFileFromResource("day1.input.txt");

        List<Integer> numbers = lines.stream().map(Integer::valueOf).collect(Collectors.toList());
        int totalIncreases = getTotalIncreases(numbers);

        System.out.printf("Solution 1: Total Increases: %d\n", totalIncreases);
    }

    private static void solvePartTwo() throws Exception {
        FileUtil fileUtil = new FileUtil();
        var lines = fileUtil.readFileFromResource("day1.input.txt");

        List<Integer> numbers = lines.stream().map(Integer::valueOf).collect(Collectors.toList());
        var slidingWindowGroups = getSlidingWindows(numbers);

        List<Integer> sums = slidingWindowGroups
                .stream().map(l -> l.stream().mapToInt(Integer::intValue)
                .sum()).collect(Collectors.toList());

        int totalIncreases = getTotalIncreases(sums);
        System.out.printf("Solution 2: Total Increases: %d\n", totalIncreases);
    }

    public static List<List<Integer>> getSlidingWindows(List<Integer> numbers) {
        List<List<Integer>> results = new ArrayList<>();

        int i =  0;
        while(i < numbers.size()) {
            List<Integer> threeNumbers = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                if (i+j < numbers.size()) {
                    threeNumbers.add(numbers.get(i+j));
                }
            }
            i++;
            results.add(threeNumbers);
        }

        return results;
    }

    private static int getTotalIncreases(List<Integer> numbers) {
        int totalIncreases = 0;
        Iterator<Integer> iter = numbers.iterator();

        Integer lastNumber = iter.next();
        while(iter.hasNext()) {
            Integer nextLastNumber =  iter.next();
            if (nextLastNumber > lastNumber) {
                totalIncreases++;
            }
            lastNumber = nextLastNumber;
         }
        return totalIncreases;
    }


}
