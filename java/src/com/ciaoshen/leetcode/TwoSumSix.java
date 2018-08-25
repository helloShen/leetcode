/**
 * Leetcode - Algorithm - TwoSumSix
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class TwoSumSix implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private TwoSumSix() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean findTarget(TreeNode root, int k); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private final Set<Integer> set = new HashSet<>();
        private int target = 0;

        public boolean findTarget(TreeNode root, int k) {
            set.clear();
            target = k;
            return preOrder(root);
        }
        private boolean preOrder(TreeNode root) {
            if (root == null) { return false; }
            if (preOrder(root.left)) { return true; }
            if (set.contains(target - root.val)) { return true; }
            set.add(root.val);
            if (preOrder(root.right)) { return true; }
            return false;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean findTarget(TreeNode root, int k) {
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean findTarget(TreeNode root, int k) {
            return false;
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
        private TwoSumSix problem = new TwoSumSix();
        private Solution solution = null;

        private void call(TreeNode root, int k) {
            System.out.println(root.bfs() + " \t , Target = " + k);
            System.out.println("Result = " + solution.findTarget(root,k) + " \n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            Random r = new Random();
            call(TreeNode.randomBST(20),r.nextInt(40));
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
