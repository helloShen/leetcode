/**
 * Leetcode - Algorithm - FlipGame
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FlipGame implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FlipGame() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<String> generatePossibleNextMoves(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public List<String> generatePossibleNextMoves(String s) {
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

        public List<String> generatePossibleNextMoves(String s) {
            return null;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<String> generatePossibleNextMoves(String s) {
            return null;
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
        private FlipGame problem = new FlipGame();
        private Solution solution = null;

        // call method in solution
        private void call(String s) {
            System.out.println("For String:[" + s + "]");
            System.out.println("Generate Moves: " + solution.generatePossibleNextMoves(s));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "";
            String s2 = "++++";
            String s3 = "--";

            /** involk call() method HERE */
            call(s1);
            call(s2);
            call(s3);    
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
