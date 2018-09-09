/**
 * Leetcode - Algorithm - BullsAndCows
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BullsAndCows implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BullsAndCows() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String getHint(String secret, String guess); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /*
     * Three Pass
     *      1. 第一次遍历统计频率
     *      2. 第二次遍历先处理bulls
     *      3. 第三次遍历再处理cows
     */
    private class Solution1 extends Solution {
        { super.id = 1; }
        public String getHint(String secret, String guess) {
            if (secret.length() != guess.length()) { return null; }
            int[] freq = new int[10];
            for (char c : secret.toCharArray()) {
                freq[c-'0']++;
            }
            int bulls = 0, cows = 0;
            // 先处理bulls
            for (int i = 0; i < guess.length(); i++) {
                int cs = secret.charAt(i) - '0';
                int cg = guess.charAt(i) - '0';
                if (cg == cs) {
                    ++bulls;
                    --freq[cg];
                }
            }
            // 必须处理完bulls再处理cows
            for (int i = 0; i < guess.length(); i++) {
                int cs = secret.charAt(i) - '0';
                int cg = guess.charAt(i) - '0';
                if (cg != cs && freq[cg] > 0) {
                     ++cows;
                    --freq[cg];
                }
            }
            return bulls + "A" + cows + "B";
        }
    }

    /*
     * One Pass
     * bulls和cows一起处理
     */
    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public String getHint(String secret, String guess) {
            if (secret.length() != guess.length()) { return null; }
            int[] secretMemo = new int[10];
            int[] guessMemo = new int[10];
            int bulls = 0, cows = 0;
            for (int i = 0; i < guess.length(); i++) {
                int numSecret = secret.charAt(i) - '0';
                int numGuess = guess.charAt(i) - '0';
                if (numSecret == numGuess) {
                    ++bulls;
                } else {
                    if (secretMemo[numGuess] > 0) { // 这次猜到前面的数字
                        ++cows;
                        --secretMemo[numGuess];
                    } else {
                        ++guessMemo[numGuess];      // 这次没猜到以前的数，才记录下来
                    }
                    if (guessMemo[numSecret] > 0) { // 这次的数是以前猜过的
                        ++cows;
                        --guessMemo[numSecret];
                    } else {
                        ++secretMemo[numSecret];    // 这次的数以前没猜过，才记录下来
                    }
                }
            }
            return bulls + "A" + cows + "B";
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public String getHint(String secret, String guess) {
            if (secret.length() != guess.length()) { return null; }
            int[] nums = new int[10];
            int bulls = 0, cows = 0;
            for (int i = 0; i < guess.length(); i++) {
                int s = secret.charAt(i) - '0';
                int g = guess.charAt(i) - '0';
                if (s == g) {
                    ++bulls;
                } else {
                    if (nums[s] < 0) { ++cows; }
                    if (nums[g] > 0) { ++cows; }
                    ++nums[s]; // 某数出现一次，加一
                    --nums[g]; // 某数猜过一次，减一
                }
            }
            return bulls + "A" + cows + "B";
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
        private BullsAndCows problem = new BullsAndCows();
        private Solution solution = null;

        // call method in solution
        private void call(String secret, String guess) {
            System.out.println("Secret Number = " + secret);
            System.out.println("Guess Number = " + guess);
            System.out.println("Hint = " + solution.getHint(secret,guess) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "1807";
            String g1 = "7810";

            String s2 = "1123";
            String g2 = "0111";

            String s3 = "1122";
            String g3 = "1222";

            call(s1,g1);
            call(s2,g2);
            call(s3,g3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
