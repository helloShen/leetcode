/**
 * Leetcode - Algorithm - LargestBSTSubtree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class LargestBSTSubtree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private LargestBSTSubtree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int largestBSTSubtree(TreeNode root); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int largestBSTSubtree(TreeNode root) {
            if (root == null) { return 0; }
            return recursion(root)[0];
        }
        //res[0]: size
        //res[1]: 子节点值域左边界（最小值）
        //res[2]: 子节点值域右边界（最大值）
        //当子节点的左边界大于右边界，说明这棵子树已经死了（不是合法BST）
        private int[] recursion(TreeNode root) {
            //基
            if (root == null){ return null; }
            //递归
            int[] res = new int[3];
            res[0] = 1; res[1] = root.val; res[2] = root.val;
            int[] left = recursion(root.left);
            int[] right = recursion(root.right);
            int numLeft = (left == null)? 1 : left[0];
            int numRight = (right == null)? 1 : right[0];
            //死子树
            if ((left != null && left[1] > left[2]) || (right != null && right[1] > right[2])   //左右子树有一棵死了
                ||
                (root.left != null && root.val <= left[2]) || (root.right != null && root.val >= right[1])) { //左右子树都活，但当前节点不合法，树照样死
                return new int[]{Math.max(numLeft,numRight),1,-1};
            }
            //活子树
            if (left != null) {
                // System.out.println("[" + root.val + "]的左子节点[" + root.left.val + "]返回：" + Arrays.toString(left));
                // System.out.println("节点[" + root.val + "]左子节点[" + root.left.val + "]合法所以增加 " + left[0] + " 个节点");
                res[0] += left[0];
                res[1] = left[1];
            }
            if (right != null) {
                // System.out.println("[" + root.val + "]的右子节点[" + root.right.val + "]返回：" + Arrays.toString(right));
                // System.out.println("节点[" + root.val + "]右子节点[" + root.right.val + "]合法所以增加 " + right[0] + " 个节点");
                res[0] += right[0];
                res[2] = right[2];
            }
            // if (left == null && right == null) { System.out.println("Leaf" +Arrays.toString(res)); }
            // System.out.println("Node[" + root.val + "] -> " + Arrays.toString(res));
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int largestBSTSubtree(TreeNode root) {
            if (root == null) { return 0; }
            return recursion(root)[0];
        }
        /**
         * 函数返回值是一个数组int[3]:
         *     res[0]: 当前子树中最大BST的规模
         *     res[1]: 当前子树值域左边界（最小值）
         *     res[2]: 当前子树值域右边界（最大值）
         *
         * 如果当前子树不是合法BST，我设置res[1]=1, res[2]=-1，即值域为空，
         * 即告诉上层递归调用这棵子树已经死了
         */
        private int[] recursion(TreeNode root) {
            //基
            if (root == null){ return null; }
            //递归
            int[] res = new int[3];
            res[0] = 1; res[1] = root.val; res[2] = root.val;
            int[] left = recursion(root.left);
            int[] right = recursion(root.right);
            int numLeft = (left == null)? 1 : left[0];
            int numRight = (right == null)? 1 : right[0];
            //死子树
            if (notBST(root,left,right)) {
                return new int[]{Math.max(numLeft,numRight),1,-1};
            }
            //活子树
            if (left != null) {
                // System.out.println("[" + root.val + "]的左子节点[" + root.left.val + "]返回：" + Arrays.toString(left));
                // System.out.println("节点[" + root.val + "]左子节点[" + root.left.val + "]合法所以增加 " + left[0] + " 个节点");
                res[0] += left[0];
                res[1] = left[1];
            }
            if (right != null) {
                // System.out.println("[" + root.val + "]的右子节点[" + root.right.val + "]返回：" + Arrays.toString(right));
                // System.out.println("节点[" + root.val + "]右子节点[" + root.right.val + "]合法所以增加 " + right[0] + " 个节点");
                res[0] += right[0];
                res[2] = right[2];
            }
            // if (left == null && right == null) { System.out.println("Leaf" +Arrays.toString(res)); }
            // System.out.println("Node[" + root.val + "] -> " + Arrays.toString(res));
            return res;
        }
        private boolean notBST(TreeNode root, int[] left, int[] right) {
            //左右子树只要有一棵死了，整棵树都死
            boolean childrenDead = (left != null && left[1] > left[2]) || (right != null && right[1] > right[2]);
            //当前节点不合法，树被杀死
            boolean killRoot = (root.left != null && root.val <= left[2]) || (root.right != null && root.val >= right[1]);
            return childrenDead || killRoot;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int largestBSTSubtree(TreeNode root) {
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
        private LargestBSTSubtree problem = new LargestBSTSubtree();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root, int answer) {
            System.out.println(root);
            System.out.println("Largest BST Subtree = " + solution.largestBSTSubtree(root) + "\t[answer=" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            TreeNode root1 = null;
            String treeStr2 = "[10,5,15,1,8,null,7]";
            TreeNode root2 = TreeNode.stringToTreeNode(treeStr2);
            String treeStr3 = "[3,2,4,null,null,1]";
            TreeNode root3 = TreeNode.stringToTreeNode(treeStr3);
            String treeStr4 = "[5,2,null,4,3,1]";
            TreeNode root4 = TreeNode.stringToTreeNode(treeStr4);
            String treeStr5 = "[4,2,7,2,3,5,null,2,null,null,null,null,null,1]";
            TreeNode root5 = TreeNode.stringToTreeNode(treeStr5);
            String treeStr6 = "[-64,12,18,-4,-53,null,76,null,-51,null,null,-93,3,null,-31,47,null,3,53,-81,33,4,null,-51,-44,-60,11,null,null,null,null,78,null,-35,-64,26,-81,-31,27,60,74,null,null,8,-38,47,12,-24,null,-59,-49,-11,-51,67,null,null,null,null,null,null,null,-67,null,-37,-19,10,-55,72,null,null,null,-70,17,-4,null,null,null,null,null,null,null,3,80,44,-88,-91,null,48,-90,-30,null,null,90,-34,37,null,null,73,-38,-31,-85,-31,-96,null,null,-18,67,34,72,null,-17,-77,null,56,-65,-88,-53,null,null,null,-33,86,null,81,-42,null,null,98,-40,70,-26,24,null,null,null,null,92,72,-27,null,null,null,null,null,null,-67,null,null,null,null,null,null,null,-54,-66,-36,null,-72,null,null,43,null,null,null,-92,-1,-98,null,null,null,null,null,null,null,39,-84,null,null,null,null,null,null,null,null,null,null,null,null,null,-93,null,null,null,98]";
            TreeNode root6 = TreeNode.stringToTreeNode(treeStr6);

            /** involk call() method HERE */
            call(root1,0);
            call(root2,3);
            call(root3,2);
            call(root4,2);
            call(root5,2);
            call(root6,3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
