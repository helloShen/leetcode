/**
 * Leetcode - Algorithm - Shortest Word Distance Three
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ShortestWordDistanceThree {
    public class SolutionV1 {
        public int shortestWordDistance(String[] words, String word1, String word2) {
            if (word1.equals(word2)) {
                return shortestDistanceEquals(words,word1);
            } else {
                return shortestDistance(words,word1,word2);
            }
        }
        private int shortestDistanceEquals(String[] words, String word) {
            int min = 0, pre = -1;
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word)) {
                    if (pre == -1) {
                        pre = i;
                    } else {
                        int dis = i - pre;
                        min = (min == 0)? dis : Math.min(min,dis);
                        pre = i;
                    }
                }
            }
            return min;
        }
        private int shortestDistance(String[] words, String word1, String word2) {
            int mindis = 0;
            int pos1 = -1, pos2 = -1;
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1)) {
                    pos1 = i;
                    if (pos2 != -1) {
                        int dis = pos1 - pos2;
                        mindis = (mindis == 0)? dis : Math.min(mindis,dis);
                    }
                }
                if (words[i].equals(word2)) {
                    pos2 = i;
                    if (pos1 != -1) {
                        int dis = pos2 - pos1;
                        mindis = (mindis == 0)? dis : Math.min(mindis,dis);
                    }
                }
            }
            return mindis;
        }
    }
    public class SolutionV2 {
        public int shortestWordDistance(String[] words, String word1, String word2) {
            String self = null, brother = null;
            int pre = -1, len = words.length;
            for (int i = 0; i < len; i++) {
                String word = words[i];
                if (word.equals(word1) || word.equals(word2)) {
                    boolean equalsWord1 = word.equals(word1);
                    self = (equalsWord1)? word1 : word2;
                    brother = (equalsWord1)? word2 : word1;
                    pre = i;
                }
            }
            int min = 0;
            for (int i = pre+1; i < len; i++) {
                String word = words[i];
                if (word.equals(brother)) {
                    int dis = i - pre;
                    min = (min == 0)? dis : Math.min(min,dis);
                    pre = i;
                    String temp = brother; // switch self & brother
                    brother = self;
                    self = temp;
                } else if (word.equals(self)) { // if word1.equals(word2), never enter here
                    pre = i;
                }
            }
            return min;
        }
    }
    public class Solution {
        public int shortestWordDistance(String[] words, String word1, String word2) {
            int min = 0;
            int pos1 = -1, pos2 = -1;
            boolean same = word1.equals(word2); // use a flag to mark if word1.equals(word2)
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (word.equals(word1) || word.equals(word2)) {
                    int dis = 0;
                    if (word.equals(word1)) {
                        if (same) { // if word1.equals(word2), maintains both pos1 & pos2 in this branch
                            pos2 = pos1;
                            pos1 = i;
                        } else {
                            pos1 = i;
                        }
                        if (pos2 != -1) { dis = pos1 - pos2; }
                    } else { // if word1.equals(word2), never enter this branch
                        pos2 = i;
                        if (pos1 != -1) { dis = pos2 - pos1; }
                    }
                    if (dis != 0) { min = (min == 0)? dis : Math.min(min,dis); }
                }
            }
            return min;
        }
    }
    private class Test {
        private void callShortestWordDistance(String[] words, String word1, String word2, String answer) {
            System.out.println("In words list: " + Arrays.toString(words));
            System.out.println("Shortest distance of word " + word1 + " and " + word2 + " is: " + solution.shortestWordDistance(words,word1,word2));
            System.out.println("Answer should be: " + answer);
        }
        private void test() {
            String[] words1 = new String[]{"practice", "makes", "perfect", "coding", "makes"};
            String word11 = "coding";
            String word12 = "practice";
            String word13 = "makes";
            String word14 = "coding";
            callShortestWordDistance(words1,word11,word12,"3");
            callShortestWordDistance(words1,word13,word14,"1");
            String[] words2 = new String[]{"a","b"};
            String word21 = "a";
            String word22 = "b";
            callShortestWordDistance(words2,word21,word22,"1");
            String[] words3 = new String[]{"a","c","a","b"};
            String word31 = "a";
            String word32 = "b";
            callShortestWordDistance(words3,word31,word32,"1");
            callShortestWordDistance(words3,word31,word31,"2");
        }
    }
    private static ShortestWordDistanceThree problem = new ShortestWordDistanceThree();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
