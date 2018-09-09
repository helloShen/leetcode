/**
 * Leetcode - Algorithm - BinaryTreeLongestConsecutiveSequence
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BinaryTreeLongestConsecutiveSequence implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BinaryTreeLongestConsecutiveSequence() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int longestConsecutive(TreeNode root); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int max = 0;
        public int longestConsecutive(TreeNode root) {
            max = 0;
            helper(null,root,0);
            return max;
        }
        private void helper(TreeNode father, TreeNode root, int len) {
            if (root == null) { return; }
            // System.out.println("I am [node:" + root.val + "]");
            len = (father != null && (root.val - father.val == 1))? len + 1 : 1;
            max = Math.max(max,len);
            // System.out.println("Now Max = " + max);
            helper(root,root.left,len); // 尾递归
            helper(root,root.right,len);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int longestConsecutive(TreeNode root) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int longestConsecutive(TreeNode root) {
            return 3;
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
        private BinaryTreeLongestConsecutiveSequence problem = new BinaryTreeLongestConsecutiveSequence();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root) {
            System.out.println("TreeNode: " + root.bfs());

            System.out.println("Longest = " + solution.longestConsecutive(root));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            call(TreeNode.randomBST(30));
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
