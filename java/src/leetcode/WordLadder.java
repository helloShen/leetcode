/**
 * Leetcode - Algorithm - Word Ladder
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.Matrix;

class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) { return 0; }
        int[][] matrix = buildLevelOne(wordList);
        matrix = buildHigherLevel(matrix);
        Matrix.print(matrix);
        int min = Integer.MAX_VALUE;
        for (String word : wordList) {
            if (distance(beginWord,word) == 1) {
                min = Math.min(min,2+matrix[wordList.indexOf(word)][wordList.indexOf(endWord)]);
            }
        }
        return (min == Integer.MAX_VALUE)? 0 : min;
    }
    public int[][] buildLevelOne(List<String> wordList) {
        int size = wordList.size();
        int[][] matrix = new int[size][size];
        if (size < 2) { return matrix; }
        for (int i = 0; i < size-1; i++) {
            for (int j = i+1; j < size; j++) {
                int dis = distance(wordList.get(i),wordList.get(j));
                if (dis == 1) {
                    matrix[i][j] = 1;
                    matrix[j][i] = 1;
                }
            }
        }
        return matrix;
    }
    // Two String have the same length
    public int distance(String first, String second) {
        if (first.length() != second.length()) { return -1; }
        int count = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) { count++; }
        }
        return count;
    }
    public int[][] buildHigherLevel(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1) { backtracking(i,j,1,matrix); }
            }
        }
        return matrix;
    }
    public void backtracking(int departure, int relay, int distance, int[][] matrix) {
        if (relay == departure) { return; }
        if (matrix[departure][relay] != 0 && matrix[departure][relay] < distance) { return; }
        matrix[departure][relay] = distance;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[relay][i] == 1) {
                backtracking(departure,i,distance+1,matrix);
            }
        }
    }
    private static WordLadder test = new WordLadder();
    private static List<String> wordList1 = new ArrayList<>(Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"}));
    private static void testLadderLength() {
        String beginWord = "hit";
        String endWord = "cog";
        System.out.println("Word List = " + wordList1);
        System.out.println("Minimum Distance = " + test.ladderLength(beginWord,endWord,wordList1));
    }
    private static int[][] testBuildLevelOne(List<String> wordList) {
        System.out.println("Words = " + wordList);
        System.out.println("Level One Matrix = ");
        int[][] matrix = test.buildLevelOne(wordList);
        Matrix.print(matrix);
        return matrix;
    }
    private static int[][] testBuildHigherLevel(List<String> wordList) {
        int[][] matrix = testBuildLevelOne(wordList);
        System.out.println("Higher Level Matrix = ");
        matrix = test.buildHigherLevel(matrix);
        Matrix.print(matrix);
        return matrix;
    }
    public static void main(String[] args) {
        //testBuildLevelOne();
        //testBuildHigherLevel(wordList1);
        testLadderLength();
    }
}
