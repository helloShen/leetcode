/**
 * Leetcode - Algorithm - FlipGameTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FlipGameTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FlipGameTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean canWin(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public boolean canWin(String s) {
            return helper(s,true);
        }
        // 每次递归回答的都是我有没有必胜解
        // 我所有可能解里只要找到一条必胜解，我就必胜
        // 对手所有可能解里，我都必胜，我才必胜
        private boolean helper(String s, boolean isMe) {
            List<String> ways = ways(s);
            // base case
            if (ways.size() == 0) {                 // 谁没解谁就输了
                return !isMe;
            }
            // recursion
            for (String way : ways) {
                boolean res = helper(way,!isMe);    // 把问题抛给对手
                if (isMe) {
                    if (res) { return true; }       // 我所有可能解里只要找到一条必胜解，我就必胜
                } else {
                    if (!res) { return false; }     // 对手所有可能解里，我都必胜，我才必胜
                }
            }
            return !isMe;
        }
        private List<String> ways(String s) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < s.length()-1; i++) {
                if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                    res.add(s.substring(0,i) +
                            "--" +
                            s.substring(i+2,s.length()));
                }
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean canWin(String s) {
            return helper(s,true);
        }
        // 思路一样，但不需要预先找好所有可行解，走一步看一步
        private boolean helper(String s, boolean isMe) {
            for (int i = 0; i < s.length()-1; i++) {
                if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {   // 找到可翻转的"++"
                    String way = s.substring(0,i) +
                                 "--" +
                                 s.substring(i+2,s.length());
                    boolean res = helper(way,!isMe);                // 递归
                    if (isMe) {
                        if (res) { return true; }                   // 我所有可能解里只要找到一条必胜解，我就必胜
                    } else {
                        if (!res) { return false; }                 // 对手所有可能解里，我都必胜，我才必胜
                    }
                }
            }
            return !isMe;       // 注意！for循环之后的剩余支线和无解的base case正好重合
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        private char[] way = new char[0];

        public boolean canWin(String s) {
            way = s.toCharArray();
            return helper(true);
        }
        // 思路一样，但运用回溯算法，并且用一个数组代替每次拷贝字符串
        private boolean helper(boolean isMe) {
            for (int i = 0; i < way.length-1; i++) {
                if (way[i] == '+' && way[i+1] == '+') {
                    way[i] = '-'; way[i+1] = '-';
                    boolean res = helper(!isMe);                // 递归
                    way[i] = '+'; way[i+1] = '+';               // 马上回溯
                    if (isMe) {
                        if (res) { return true; }               // 我所有可能解里只要找到一条必胜解，我就必胜
                    } else {
                        if (!res) { return false; }             // 对手所有可能解里，我都必胜，我才必胜
                    }
                }
            }
            return !isMe;       // 注意！for循环之后的剩余支线和无解的base case正好重合
        }
    }
    // you can expand more solutions HERE if you want...
    private class Solution4 extends Solution {
        { super.id = 4; }

        private char[] way = new char[0];
        private Map<String,Boolean> memo = new HashMap<>();

        public boolean canWin(String s) {
            way = s.toCharArray();
            memo.clear();
            return helper(true);
        }
        // 回溯 + 表驱动的动态规划
        private boolean helper(boolean isMe) {
            String s = new String(way);
            if (memo.containsKey(new String(way))) { return memo.get(s); }
            for (int i = 0; i < way.length-1; i++) {
                if (way[i] == '+' && way[i+1] == '+') {
                    way[i] = '-'; way[i+1] = '-';
                    boolean res = helper(!isMe);                // 递归
                    way[i] = '+'; way[i+1] = '+';               // 马上回溯
                    if (isMe) {
                        if (res) {                              // 我所有可能解里只要找到一条必胜解，我就必胜
                            memo.put(s,true);
                            return true;
                        }
                    } else {
                        if (!res) {                             // 对手所有可能解里，我都必胜，我才必胜
                            memo.put(s,false);
                            return false;
                        }
                    }
                }
            }
            memo.put(s,!isMe);
            return !isMe;       // 注意！for循环之后的剩余支线和无解的base case正好重合
        }
    }

    private class Solution5 extends Solution {
        { super.id = 5; }

        private Map<String,Boolean> memo = new HashMap<>();

        public boolean canWin(String s) {
            memo.clear();
            return helper(s,true);
        }
        // 回溯 + 表驱动的动态规划
        private boolean helper(String s, boolean isMe) {
            if (memo.containsKey(s)) { return memo.get(s); }
            for (int i = 0; i < s.length()-1; i++) {
                if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                    String way = s.substring(0,i) +
                                 "--" +
                                 s.substring(i+2,s.length());
                    boolean res = helper(way,!isMe);                // 递归
                    if (isMe) {
                        if (res) {                              // 我所有可能解里只要找到一条必胜解，我就必胜
                            memo.put(s,true);
                            return true;
                        }
                    } else {
                        if (!res) {                             // 对手所有可能解里，我都必胜，我才必胜
                            memo.put(s,false);
                            return false;
                        }
                    }
                }
            }
            memo.put(s,!isMe);
            return !isMe;       // 注意！for循环之后的剩余支线和无解的base case正好重合
        }
    }


    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private FlipGameTwo problem = new FlipGameTwo();
        private Solution solution = null;

        // call method in solution
        private void call(String s) {
            System.out.println("For String \"" + s + "\", I " + ((solution.canWin(s))? "CAN" : "CAN NOT") + " win!");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "++++";
            String s2 = "+++++++";
            String s3 = "+++++++++++";

            /** involk call() method HERE */
            call(s1);
            call(s2);
            call(s3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.test(4);
        test.test(5);

    }
}
