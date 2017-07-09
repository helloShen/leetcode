/**
 * Leetcode - Algorithm - Word Break
 */
package com.ciaoshen.leetcode;
import java.util.*;

class WordBreak {
    /**
     * Dynamic programming
     */
    public class SolutionV1 {
        public boolean wordBreak(String s, List<String> wordDict) {
            return dp(s,0,wordDict,new HashMap<Integer,Boolean>());
        }
        public boolean dp(String s, int cur, List<String> wordDict, Map<Integer,Boolean> memo) {
            if (cur == s.length()) {
                 memo.put(cur,true);
                 return true;
             }
            for (int i = s.length()-1; i >= cur; i--) {
                Boolean sub = memo.get(i+1);
                if (sub == null) {
                    sub = dp(s,i+1,wordDict,memo);
                }
                if (sub && wordDict.contains(s.substring(cur,i+1))) {
                    memo.put(cur,true);
                    return true;
                }
            }
            memo.put(cur,false);
            return false;
        }
    }
    public class SolutionV2 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Map<Integer,Boolean> memo = new HashMap<>();
            memo.put(s.length(),true);
            return dp(s,0,wordDict,memo);
        }
        public boolean dp(String s, int cur, List<String> wordDict, Map<Integer,Boolean> memo) {
            for (int i = s.length(); i > cur; i--) {
                Boolean sub = memo.get(i);
                if (sub == null) {
                    sub = dp(s,i,wordDict,memo);
                }
                if (sub && wordDict.contains(s.substring(cur,i))) {
                    memo.put(cur,true);
                    return true;
                }
            }
            memo.put(cur,false);
            return false;
        }
    }
    /**
     * 非常干净的标准自底向上动态规划，迭代版
     */
    public class SolutionV3 {
        public boolean wordBreak(String s, List<String> wordDict) {
            int size = s.length();
            boolean[] memo = new boolean[size+1];
            memo[size] = true;
            for (int i = size-1; i >= 0; i--) {
                for (int j = size; j > i; j--) {
                    if (memo[j] && wordDict.contains(s.substring(i,j))) {
                        memo[i] = true;
                        break;
                    }
                }
            }
            return memo[0];
        }
    }
    /**
     * 预先把所有单词存入一个Map，加快查询的速度
     */
    public class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            int size = s.length();
            boolean[] memo = new boolean[size+1];
            Map<String,Object> dic = new HashMap<>();
            for (String word : wordDict) {
                dic.put(word,null);
            }
            memo[size] = true;
            for (int i = size-1; i >= 0; i--) {
                for (int j = size; j > i; j--) {
                    if (memo[j] && dic.containsKey(s.substring(i,j))) {
                        memo[i] = true;
                        break;
                    }
                }
            }
            return memo[0];
        }
    }
    private static WordBreak test = new WordBreak();
    private static void testWordBreak() {
        Solution solution = test.new Solution();
        List<String> dictionary = new ArrayList<>(Arrays.asList(new String[]{"leet","code"}));
        System.out.println("leetcode " + solution.wordBreak("leetcode",dictionary));
        System.out.println("helloworld " + solution.wordBreak("helloworld",dictionary));
    }
    public static void main(String[] args) {
        testWordBreak();
    }
}
