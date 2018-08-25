/**
 * Leetcode - Algorithm - PathSumThree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PathSumThree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PathSumThree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int pathSum(TreeNode root, int sum); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int pathSum(TreeNode root, int sum) {
            if (root == null) { return 0; }
            int left = pathSum(root.left, sum);
            System.out.println("For root: " + root.val);
            System.out.println("\tLeft sub = " + left);
            int right = pathSum(root.right,sum);
            System.out.println("\tRight sub = " + right);
            int curr = dfs(root,sum);
            System.out.println("\tCurr root res= " + curr);
            return left + right + curr;
        }
        private int dfs(TreeNode root, int remain) {
            if (root == null) { return 0; }
            int left = dfs(root.left,remain - root.val);
            int right = dfs(root.right,remain - root.val);
            int curr = (root.val == remain)? 1 : 0;
            return left + right + curr;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int pathSum(TreeNode root, int sum) {
            if (root == null) { return 0; }
            return pathSum(root.left, sum) + pathSum(root.right,sum) + dfs(root,sum);
        }
        private int dfs(TreeNode root, int remain) {
            if (root == null) { return 0; }
            return dfs(root.left,remain - root.val) + dfs(root.right,remain - root.val) + ((root.val == remain)? 1 : 0);
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int pathSum(TreeNode root, int sum) {
            if (root == null) { return 0; }
            Map<Integer,Integer> map = new HashMap<>();
            map.put(0,1);
            return specialDFS(root,0,sum,map);
        }
        private int specialDFS(TreeNode root, int sum, int target, Map<Integer,Integer> map) {
            if (root == null) { return 0; }
            sum += root.val;
            int pathFromRootNum = map.getOrDefault(sum- target,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
            int res =   pathFromRootNum +
                        specialDFS(root.left,sum,target,map) +
                        specialDFS(root.right,sum,target,map);
            map.put(sum,map.getOrDefault(sum,0)-1);
            return res;
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
        private PathSumThree problem = new PathSumThree();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root, int sum) {
            System.out.println(root.bfs());
            System.out.println("Number of path sum = " + sum + " is " + solution.pathSum(root,sum) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            TreeNode root1 = TreeNode.randomBST(20);

            /** involk call() method HERE */
            call(root1,20);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
