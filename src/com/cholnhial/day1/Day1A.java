package com.cholnhial.day1;

import util.FileUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class Day1A {

    public static void main(String... args) throws Exception {
        FileUtil fileUtil = new FileUtil();
        var lines = fileUtil.readFileFromResource("day1.input.txt");

        List<Integer> numbers = lines.stream().map(Integer::valueOf).collect(Collectors.toList());
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

        System.out.printf("Total Increases: %d\n", totalIncreases);
    }



}
