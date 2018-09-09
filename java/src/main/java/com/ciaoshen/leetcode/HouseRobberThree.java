/**
 * Leetcode - Algorithm - HouseRobberThree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class HouseRobberThree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private HouseRobberThree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int rob(TreeNode root); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[] houses = new int[0];

        public int rob(TreeNode root) {
            init(root);
            treeToArray(root,0);
            // System.out.println("After Tree to Array: " + Arrays.toString(houses));
            for (int i = houses.length - 1; i >= 0; i--) {
                int l = i * 2 + 1, r = l + 1;
                if (r < houses.length) { // has son
                    int ll = l * 2 + 1, lr = ll + 1;
                    int rl = r * 2 + 1, rr = rl + 1;
                    if (rr < houses.length) { // has grand son
                        int takeGrandSon = houses[i] + houses[ll] + houses[lr] + houses[rl] + houses[rr];
                        int takeSon = houses[l] + houses[r];
                        houses[i] = Math.max(takeGrandSon,takeSon);
                    } else {
                        houses[i] = Math.max(houses[i], houses[l] + houses[r]);
                    }
                }
            }
            // System.out.println("After Work Array = " + Arrays.toString(houses));
            return houses[0];
        }
        private void treeToArray(TreeNode root, int offset) {
            if (root == null) { return; }
            houses[offset] = root.val;
            treeToArray(root.left,offset * 2 + 1);
            treeToArray(root.right,offset * 2 + 2);
        }
        private void init(TreeNode root) {
            houses = new int[(int)(Math.pow(2,depth(root,0)+1)-1)];
        }
        private int depth(TreeNode root, int depth) {
            if (root == null) { return 0; }
            return Math.max(depth,Math.max(depth(root.left,depth+1),depth(root.right,depth+1)));
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int rob(TreeNode root) {
            if (root == null) { return 0; }
            int l = 0,r = 0,ll = 0,lr = 0,rl = 0,rr = 0;
            if (root.left != null) {
                l = rob(root.left);
                ll = rob(root.left.left);
                lr = rob(root.left.right);
            }
            if (root.right != null) {
                r = rob(root.right);
                rl = rob(root.right.left);
                rr = rob(root.right.right);
            }
            int takeSon = l + r;
            int takeGrandSon = root.val + ll + lr + rl + rr;
            return Math.max(takeSon,takeGrandSon);
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int rob(TreeNode root) {
            if (root == null) { return 0; }
            int takeSon = 0, takeGrandSon = root.val;
            if (root.left != null) {
                rob(root.left);
                takeSon += root.left.val;
                if (root.left.left != null) { takeGrandSon += root.left.left.val; }
                if (root.left.right != null) { takeGrandSon += root.left.right.val; }
            }
            if (root.right != null) {
                rob(root.right);
                takeSon += root.right.val;
                if (root.right.left != null) { takeGrandSon += root.right.left.val; }
                if (root.right.right != null) { takeGrandSon += root.right.right.val; }
            }
            root.val = Math.max(takeSon,takeGrandSon);
            return root.val;
        }
    }
    // you can expand more solutions HERE if you want...

    private class Solution4 extends Solution {
        { super.id = 4; }

        public int rob(TreeNode root) {
            int[] res = dp(root);
            return Math.max(res[0],res[1]);
        }
        private int[] dp(TreeNode root) {
            /**
             * res[0]: take son
             * res[1]: take itself and grand son
             */
            int[] res = new int[2];
            if (root == null) { return res; }
            int[] left = dp(root.left);
            int[] right = dp(root.right);
            res[0] = root.val + left[1] + right [1];
            res[1] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
            return res;
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
        private HouseRobberThree problem = new HouseRobberThree();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root) {
            System.out.println("Tree = " + root.bfs());
            System.out.println("Max Value = " + solution.rob(root));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            call(TreeNode.randomBST(50));

            TreeNode threeOne = new TreeNode(3);
            TreeNode threeTwo = new TreeNode(3);
            TreeNode oneOne = new TreeNode(1);
            TreeNode oneTwo = new TreeNode(1);
            TreeNode four = new TreeNode(4);
            TreeNode five = new TreeNode(5);
            four.left = oneTwo; four.right = threeTwo;
            five.right = oneOne;
            threeOne.left = four; threeOne.right = five;
            call(threeOne);
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
