/**
 * Leetcode - Algorithm - Shortest Word Distance Two
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ShortestWordDistanceTwo {
    public class WordDistance {
        private Map<String,List<Integer>> dic = new HashMap<>();
        public WordDistance(String[] words) {
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                List<Integer> position = dic.get(word);
                if (position == null) { position = new ArrayList<Integer>(); }
                position.add(i);
                dic.put(word,position);
            }
        }
        public int shortest(String word1, String word2) {
            List<Integer> list1 = dic.get(word1);
            List<Integer> list2 = dic.get(word2);
            if (list1 == null || list2 == null) { return 0; }
            int min = -1, dis = 0;
            for (int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
                int pos1 = list1.get(i);
                int pos2 = list2.get(j);
                if (pos1 > pos2) {
                    dis = pos1 - pos2;
                    j++;
                } else {
                    dis = pos2 - pos1;
                    i++;
                }
                min = (min == -1)? dis : Math.min(min,dis);
            }
            return (min == -1)? 0 : min;
        }
    }
    private static class Test {
        private ShortestWordDistanceTwo problem = new ShortestWordDistanceTwo();
        private void callShortest(WordDistance testcase, String word1, String word2, String answer) {
            System.out.println("In words list: " + testcase.dic);
            System.out.println("Shortest distance of word " + word1 + " and " + word2 + " is: " + testcase.shortest(word1,word2));
            System.out.println("Answer should be: " + answer);
        }
        private void test() {
            String[] words1 = new String[]{"practice", "makes", "perfect", "coding", "makes"};
            WordDistance case1 = problem.new WordDistance(words1);
            String word11 = "coding";
            String word12 = "practice";
            String word13 = "makes";
            String word14 = "coding";
            callShortest(case1,word11,word12,"3");
            callShortest(case1,word13,word14,"1");

            String[] words2 = new String[]{"a","b"};
            WordDistance case2 = problem.new WordDistance(words2);
            String word21 = "a";
            String word22 = "b";
            callShortest(case2,word21,word22,"1");

            String[] words3 = new String[]{"a","c","a","b"};
            WordDistance case3 = problem.new WordDistance(words3);
            String word31 = "a";
            String word32 = "b";
            callShortest(case3,word31,word32,"1");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}
