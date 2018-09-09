/**
 * Leetcode - Algorithm - MaximumBinaryTree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MaximumBinaryTree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MaximumBinaryTree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public TreeNode constructMaximumBinaryTree(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public TreeNode constructMaximumBinaryTree(int[] nums) {
            if (nums.length == 0) { return null; }
            TreeNode root = null;
            for (int num : nums) {
                root = insert(root,num);
            }
            return root;
        }
        private TreeNode insert(TreeNode root, int num) {
            TreeNode newNode = new TreeNode(num);
            if (root == null) {
                return newNode;
            } else {
                int val = root.val;
                if (val < num) {
                    newNode.left = root;
                    return newNode;
                } else {
                    root.right = insert(root.right,num);
                    return root;
                }
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return null;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public TreeNode constructMaximumBinaryTree(int[] nums) {
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
        private MaximumBinaryTree problem = new MaximumBinaryTree();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums) {
            System.out.println("Given numbers: " + Arrays.toString(nums));
            System.out.println("Maximum Binary Tree: " + solution.constructMaximumBinaryTree(nums).bfs());
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{3,2,1,6,0,5};

            /** involk call() method HERE */
            call(nums1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
