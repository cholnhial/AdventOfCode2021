package com.cholnhial.day3;

import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day3 {

    public static void main(String... args) throws Exception {
        solvePartOne();
        solvePartTwo();
    }

    private static void solvePartOne() throws Exception {
        FileUtil fileUtil = new FileUtil();
        var lines = fileUtil.readFileFromResource("day3.input.txt");

        int bitLength = lines.get(0).length();
         int bitPosition = 0;
         List<String> gammaRateBits = new ArrayList<>();
         List<String> epsilonBits = new ArrayList<>();

        while(bitPosition < bitLength) {
            final int pos = bitPosition;
            long oneBits = lines.stream().filter(l -> l.charAt(pos) == '1').count();
            long zeroBits = lines.stream().filter(l -> l.charAt(pos) == '0').count();

           if (oneBits > zeroBits) {
               gammaRateBits.add("1");
               epsilonBits.add("0");
           } else {
               gammaRateBits.add("0");
               epsilonBits.add("1");
           }

            bitPosition++;
        }

        int finalGammaRate = Integer.parseInt(String.join("", gammaRateBits), 2);
        int finalEpsilonRate = Integer.parseInt(String.join("", epsilonBits), 2);

        System.out.println("Solution 1: " + finalEpsilonRate*finalGammaRate);
    }


    private static void solvePartTwo() throws Exception {
        FileUtil fileUtil = new FileUtil();
        var lines = fileUtil.readFileFromResource("day3.input.txt");

        int bitLength = lines.get(0).length();
        int bitPosition = 0;
        int oxygenGeneratorRating=  0;
        int c02ScrubberRating = 0;

        while(bitPosition < bitLength) {
            final int pos = bitPosition;
            long oneBits = lines.stream().filter(l -> l.charAt(pos) == '1').count();
            long zeroBits = lines.stream().filter(l -> l.charAt(pos) == '0').count();

            if (oneBits > zeroBits) {
                lines = lines.stream().filter(l -> l.charAt(pos) == '1').collect(Collectors.toList());
            } else if (zeroBits > oneBits) {
                lines = lines.stream().filter(l -> l.charAt(pos) == '0').collect(Collectors.toList());
            } else {
                lines = lines.stream().filter(l -> l.charAt(pos) == '1').collect(Collectors.toList());
            }

            if (lines.size() == 1) {
                oxygenGeneratorRating = Integer.parseInt(lines.get(0), 2);
                break;
            }

            bitPosition++;
        }

        bitPosition = 0;
        lines = fileUtil.readFileFromResource("day3.input.txt");

        while(bitPosition < bitLength) {
            final int pos = bitPosition;
            long oneBits = lines.stream().filter(l -> l.charAt(pos) == '1').count();
            long zeroBits = lines.stream().filter(l -> l.charAt(pos) == '0').count();

            if (oneBits < zeroBits) {
                lines = lines.stream().filter(l -> l.charAt(pos) == '1').collect(Collectors.toList());
            } else if (zeroBits < oneBits) {
                lines = lines.stream().filter(l -> l.charAt(pos) == '0').collect(Collectors.toList());
            } else {
                lines = lines.stream().filter(l -> l.charAt(pos) == '0').collect(Collectors.toList());
            }

            if (lines.size() == 1) {
                c02ScrubberRating = Integer.parseInt(lines.get(0), 2);
                break;
            }

            bitPosition++;
        }

        System.out.println("Solution 1: " + oxygenGeneratorRating*c02ScrubberRating);
    }
}
