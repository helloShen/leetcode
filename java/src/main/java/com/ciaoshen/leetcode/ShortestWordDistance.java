/**
 * Leetcode - Algorithm - Shortest Word Distance
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ShortestWordDistance {
    public class SolutionV1 {
        public int shortestDistance(String[] words, String word1, String word2) {
            int mindis = -1;
            int pos1 = -1, pos2 = -1;
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1)) {
                    pos1 = i;
                    if (pos2 != -1) {
                        int dis = pos1 - pos2;
                        mindis = (mindis == -1)? dis : Math.min(mindis,dis);
                    }
                }
                if (words[i].equals(word2)) {
                    pos2 = i;
                    if (pos1 != -1) {
                        int dis = pos2 - pos1;
                        mindis = (mindis == -1)? dis : Math.min(mindis,dis);
                    }
                }
            }
            return (mindis == -1)? 0 : mindis;
        }
    }
    public class Solution {
        public int shortestDistance(String[] words, String word1, String word2) {
            int mindis = -1;
            int pos1 = -1, pos2 = -1;
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1)) { pos1 = i; }
                if (words[i].equals(word2)) { pos2 = i; }
                if (pos1 != -1 && pos2 != -1) {
                    int dis = Math.abs(pos1 - pos2);
                    mindis = (mindis == -1)? dis : Math.min(mindis,dis);
                }
            }
            return (mindis == -1)? 0 : mindis;
        }
    }
    private class Test {
        private void callShortestDistance(String[] words, String word1, String word2, String answer) {
            System.out.println("In words list: " + Arrays.toString(words));
            System.out.println("Shortest distance of word " + word1 + " and " + word2 + " is: " + solution.shortestDistance(words,word1,word2));
            System.out.println("Answer should be: " + answer);
        }
        private void test() {
            String[] words1 = new String[]{"practice", "makes", "perfect", "coding", "makes"};
            String word11 = "coding";
            String word12 = "practice";
            String word13 = "makes";
            String word14 = "coding";
            callShortestDistance(words1,word11,word12,"3");
            callShortestDistance(words1,word13,word14,"1");
            String[] words2 = new String[]{"a","b"};
            String word21 = "a";
            String word22 = "b";
            callShortestDistance(words2,word21,word22,"1");
            String[] words3 = new String[]{"a","c","a","b"};
            String word31 = "a";
            String word32 = "b";
            callShortestDistance(words3,word31,word32,"1");
        }
    }
    private static ShortestWordDistance problem = new ShortestWordDistance();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
