package com.cholnhial.day4;

import util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day4 {

    public static void main(String... args) throws Exception {
        solvePartOne();
        solvePartTwo();
    }

    private static class BoardNumber {
        private Integer number;
        private boolean isCalled;


        public BoardNumber(Integer n, boolean called) {
            this.number = n;
            this.isCalled = called;
        }

        public Integer getNumber() {
            return number;
        }

        public boolean isCalled() {
            return isCalled;
        }

        public void setCalled(boolean called) {
            isCalled = called;
        }
    }

    private static List<Integer>  readCalls(List<String> lines) {
       return lines.stream().limit(1).flatMap(l -> Arrays.stream(l.split(",")))
               .map(Integer::valueOf).collect(Collectors.toList());
    }

    private static List<BoardNumber[][]> readBoards(List<String> lines) {
        Iterator<String> iter = lines.iterator();
        List<BoardNumber[][]> boards = new ArrayList<>();

        // Skip calls
        iter.next();

        while(iter.hasNext()) {
            BoardNumber[][] board = new BoardNumber[5][5];
            int rowsCount = 0;
            while (rowsCount < 5) {
                String[] numbers = iter.next().split("\\s+");
                for(int i = 0; i < 5; i++) {
                    board[rowsCount][i] = new BoardNumber(Integer.valueOf(numbers[i]), false);
                }
                rowsCount++;
            }
            boards.add(board);
        }

        return boards;
    }


    private static BoardNumber[][] markBoards(List<BoardNumber[][]> boards, Integer call) {
        for (var board : boards) {

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j].getNumber().equals(call)) {
                        board[i][j].setCalled(true);
                        // check column
                        int marked = 0;
                        for(int k = 0; k < 5; k++) {
                            if (board[i][k].isCalled()) {
                                marked++;
                            }
                        }
                        if (marked == 5) {
                            return board;
                        }
                        // check row
                        marked = 0;
                        for (int k = 0; k < 5; k++) {
                            if (board[k][j].isCalled()) {
                                marked++;
                            }
                        }
                        if (marked == 5) {
                            return board;
                        }
                    }
                }
            }
        }

        return null;
    }

    private static List<BoardNumber[][]> markBoards2(List<BoardNumber[][]> boards, Integer call) {
        List<BoardNumber[][]> winningBoards = new ArrayList<>();

        for (var board : boards) {

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j].getNumber().equals(call)) {
                        board[i][j].setCalled(true);
                        // check column
                        int marked = 0;
                        for(int k = 0; k < 5; k++) {
                            if (board[i][k].isCalled()) {
                                marked++;
                            }
                        }
                        if (marked == 5) {
                            winningBoards.add(board);
                            break;
                        }
                        // check row
                        marked = 0;
                        for (int k = 0; k < 5; k++) {
                            if (board[k][j].isCalled()) {
                                marked++;
                            }
                        }
                        if (marked == 5) {
                            winningBoards.add(board);
                            break;
                        }
                    }
                }
            }
        }

        return winningBoards;
    }



    private static int getBoardScore(BoardNumber[][] board, Integer call) {
        int sum = Arrays.stream(board).map(Arrays::asList).flatMap(Collection::stream)
                .filter(bn -> !bn.isCalled)
                .mapToInt(BoardNumber::getNumber)
                .sum();

        return sum * call;
    }

    private static void solvePartOne() throws Exception {
        FileUtil fileUtil = new FileUtil();
        var lines = fileUtil.readFileFromResource("day4.input.txt");
        lines = lines.stream().filter(l -> !l.isEmpty()).map(String::trim).collect(Collectors.toList());

        List<Integer> calls = readCalls(lines);
        List<BoardNumber[][]> boards = readBoards(lines);


        for (Integer call : calls) {
          var board = markBoards(boards, call);
          if (board != null) {
              // bingo
              System.out.println("Solution 1: " + getBoardScore(board, call));
              break;

          }
        }


    }

    private static void solvePartTwo() throws Exception {
        FileUtil fileUtil = new FileUtil();
        var lines = fileUtil.readFileFromResource("day4.input.txt");
        lines = lines.stream().filter(l -> !l.isEmpty()).map(String::trim).collect(Collectors.toList());
        List<BoardNumber[][]> winningBoards = new ArrayList<>();
        List<Integer> calls = readCalls(lines);
        List<BoardNumber[][]> boards = readBoards(lines);
        int nboards = boards.size();

        for (Integer call : calls) {
            var winning = markBoards2(boards, call);
            if (!winning.isEmpty()) {
                winningBoards.addAll(winning);
                boards.removeAll(winning);
            }

            if (winningBoards.size() == nboards) {
                System.out.println("Solution 2: " + getBoardScore(winning.get(winning.size() - 1), call));
                break;
            }

        }


    }
}
