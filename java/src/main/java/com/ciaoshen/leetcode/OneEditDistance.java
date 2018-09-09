/**
 * Leetcode - Algorithm - OneEditDistance
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class OneEditDistance implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private OneEditDistance() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean isOneEditDistance(String s, String t); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /** 标准用一个二维数组的动态规划 */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public boolean isOneEditDistance(String s, String t) {
            int ls = s.length(), lt = t.length();
            if (Math.abs(ls-lt) > 1) { return false; }
            if (s.equals(t)) { return false; }
            int[][] matrix = new int[ls+1][lt+1];
            for (int i = 1; i <= lt; i++) {
                matrix[0][i] = matrix[0][i-1] + 1;
            }
            for (int i = 1; i <= ls; i++) {
                matrix[i][0] = matrix[i-1][0] + 1;
            }
            for (int i = 1; i <= ls; i++) {
                for (int j = 1; j <= lt; j++) {
                    matrix[i][j] = Math.min(Math.min(matrix[i-1][j],matrix[i][j-1]) + 1, (s.charAt(i-1) == t.charAt(j-1))? matrix[i-1][j-1] : matrix[i-1][j-1] + 1);
                }
            }
            return (matrix[ls][lt] == 1)? true : false;
        }
    }

    /** 改进，用一个一维数组完成动态规划。 */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean isOneEditDistance(String s, String t) {
            int ls = s.length(), lt = t.length();
            if (Math.abs(ls-lt) > 1) { return false; }
            if (s.equals(lt)) { return false; }
            int longer = Math.max(ls,lt);
            int shorter = Math.min(ls,lt);
            if (s.length() < t.length()) { // make sure s is the longer string
                String temp = s;
                s = t;
                t = temp;
            }
            int min = 0;
            int[] memo = new int[longer+1];
            for (int i = 1; i <= longer; i++) {
                memo[i] = memo[i-1] + 1;
            }
            int corner = 0, col = 0;
            for (int i = 1; i <= shorter; i++) {
                corner = i - 1; col = i;
                if (corner > 1 && min > 1) { // 剪枝
                    return false;
                }
                min = corner;
                for (int j = 1; j <= longer; j++) {
                    // System.out.print("memo[" + j + "] = (" + memo[j]);
                    int oldVal = memo[j];
                    memo[j] = Math.min(Math.min(memo[j],col) + 1, (t.charAt(i-1) == s.charAt(j-1))? corner : corner + 1);
                    min = Math.min(min, memo[j]);
                    // System.out.print(" -> " + memo[j] + ")\n");
                    corner = oldVal;
                    col = memo[j];
                }
            }
            // System.out.println("Edit distance = " + memo[ls]);
            return (memo[longer] == 1)? true : false;
        }
    }

    /** 递归动态规划 */
    private class Solution3 extends Solution {
        { super.id = 3; }

        private String strS;
        private String strT;
        private int lenS;
        private int lenT;

        public boolean isOneEditDistance(String s, String t) {
            strS = s;
            strT = t;
            lenS = s.length();
            lenT = t.length();
            int diff = lenS - lenT;
            if (diff < -1 || diff >1) { return false; }
            int distance = dp(0,0);
            return distance == 1;
        }

        private int dp(int ps, int pt) {
            if (ps == lenS && pt == lenT) { return 0; }
            int movePs = Integer.MAX_VALUE, movePt = movePs, moveBoth = movePs;
            if (ps < lenS) { movePs = dp(ps+1,pt) + 1; }
            if (pt < lenT) { movePt = dp(ps,pt+1) + 1; }
            if (ps < lenS && pt < lenT) { moveBoth = dp(ps+1,pt+1) + ((strS.charAt(ps) == strT.charAt(pt))? 0 : 1); }
            return Math.min(Math.min(movePs,movePt),moveBoth);
        }

    }

    private class Solution4 extends Solution {
        { super.id = 4; }

        public boolean isOneEditDistance(String s, String t) {
            int lenS = s.length();
            int lenT = t.length();
            int diff = lenS - lenT;
            if (diff < -1 || diff > 1) { return false; }
            for (int i = 0; i < Math.min(lenS,lenT); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    if ((diff == 0) && s.substring(i+1).equals(t.substring(i+1))) { return true; }
                    if ((diff == 1) && s.substring(i+1).equals(t.substring(i))) { return true; }
                    if ((diff == -1) && s.substring(i).equals(t.substring(i+1))) { return true; }
                    return false;
                }
            }
            return diff != 0;
        }
    }
    // you can expand more solutions HERE if you want...


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
        private OneEditDistance problem = new OneEditDistance();
        private Solution solution = null;

        // call method in solution
        private void call(String s, String t, boolean ans) {
            System.out.println("S = " + s);
            System.out.println("T = " + t);
            System.out.println(solution.isOneEditDistance(s,t)? "IS One Edit Distance!" : "IS-NOT One Edit Distance!");
            System.out.println("[Answer: " + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "abcd";
            String t1 = "abcdd";
            boolean ans1 = true;
            String s2 = "abcd";
            String t2 = "bcdef";
            boolean ans2 = false;
            String s3 = "";
            String t3 = "A";
            boolean ans3 = true;
            String s4 = "a";
            String t4 = "";
            boolean ans4 = true;
            String s5 = "a";
            String t5 = "ba";
            boolean ans5 = true;

            /** involk call() method HERE */
            call(s1,t1,ans1);
            call(s2,t2,ans2);
            call(s3,t3,ans3);
            call(s4,t4,ans4);
            call(s5,t5,ans5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        test.test(4);
    }
}
