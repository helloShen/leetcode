/**
 * Binary Tree Node
 */
package com.ciaoshen.leetcode.myUtils;
import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }
    // dfs & inorder(left->base->right) 对二叉查找树来说就是从小到大输出
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (left != null) {
            sb.append(left.toString());
        }
        sb.append("[" + val + "]");
        if (right != null) {
            sb.append(right.toString());
        }
        return sb.toString();
    }
    // dfs & inorder to int[]
    public int[] toArray() {
        int[] leftSub = new int[0];
        int[] rightSub = new int[0];
        if (left != null) { leftSub = left.toArray(); }
        if (right != null) { rightSub = right.toArray(); }
        int[] res = new int[leftSub.length+rightSub.length+1];
        for (int i = 0; i < leftSub.length; i++) {
            res[i] = leftSub[i];
        }
        res[leftSub.length] = this.val;
        for (int i = 0; i < rightSub.length; i++) {
            res[leftSub.length+1+i] = rightSub[i];
        }
        return res;
    }
    // pre-order
    public String preOrder() {
        StringBuilder sb = new StringBuilder("[" + val + "]");
        if (left != null) {
            sb.append(left.preOrder());
        }
        if (right != null) {
            sb.append(right.preOrder());
        }
        return sb.toString();
    }
    // dfs & preorder to int[]
    public int[] preOrderToArray() {
        int[] leftSub = new int[0];
        int[] rightSub = new int[0];
        if (left != null) { leftSub = left.preOrderToArray(); }
        if (right != null) { rightSub = right.preOrderToArray(); }
        int[] res = new int[leftSub.length+rightSub.length+1];
        res[0] = this.val;
        for (int i = 0; i < leftSub.length; i++) {
            res[i+1] = leftSub[i];
        }
        for (int i = 0; i < rightSub.length; i++) {
            res[leftSub.length+1+i] = rightSub[i];
        }
        return res;
    }
    // post-order
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        if (left != null) {
            sb.append(left.postOrder());
        }
        if (right != null) {
            sb.append(right.postOrder());
        }
        sb.append("[" + val + "]");
        return sb.toString();
    }
    // dfs & postorder to int[]
    public int[] postOrderToArray() {
        int[] leftSub = new int[0];
        int[] rightSub = new int[0];
        if (left != null) { leftSub = left.postOrderToArray(); }
        if (right != null) { rightSub = right.postOrderToArray(); }
        int[] res = new int[leftSub.length+rightSub.length+1];
        for (int i = 0; i < leftSub.length; i++) {
            res[i] = leftSub[i];
        }
        for (int i = 0; i < rightSub.length; i++) {
            res[leftSub.length+i] = rightSub[i];
        }
        res[leftSub.length+rightSub.length] = this.val;
        return res;
    }
    public List<List<Integer>> bfs() {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<TreeNode> buffer = new ArrayList<>();
        buffer.add(this);
        while (!buffer.isEmpty()) {
            List<Integer> nums = new ArrayList<>();
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = buffer.remove(0);
                if (node == null) {
                    nums.add(null);
                } else {
                    nums.add(node.val);
                    buffer.add(node.left);
                    buffer.add(node.right);
                }
            }
            if (!nums.isEmpty()) {
                res.add(nums);
            }
        }
        return res;
    }
    /**
     * Generate a random Tree with 6 nodes. Max value = 100
     * @return [Random tree. NOT necessarily a Binary Search Tree.]
     */
    public static TreeNode randomStd() {
        Random r = new Random();
        TreeNode tree = new TreeNode(r.nextInt(100)+1);
        tree.left = new TreeNode(r.nextInt(100)+1);
        tree.right = new TreeNode(r.nextInt(100)+1);
        tree.left.left = new TreeNode(r.nextInt(100)+1);
        tree.left.right = new TreeNode(r.nextInt(100)+1);
        tree.right.left = new TreeNode(r.nextInt(100)+1);
        tree.right.right = new TreeNode(r.nextInt(100)+1);
        return tree;
    }

    /**
     * Two Params required by randomBST() method
     */
    private static int max = 100;
    private static Random r = new Random();
    /**
     * Return a random valid Binary Search Tree
     * @param  max [the max value of the BST]
     * @return     [Random valid BST]
     */
    public static TreeNode randomBST(int max) {  // default depth is 5
        return recurBST(1,max,5);
    }
    public static TreeNode recurBST(int min, int max, int depth) {
        if (depth == 0) { return null; }
        if (min > max) { return null; }
        int num = r.nextInt(max-min+1)+min;
        TreeNode root = new TreeNode(num);
        root.left = recurBST(min,num-1,depth-1);
        root.right = recurBST(num+1,max,depth-1);
        return root;
    }
    private static void testRandomBST() {
        for (int i = 0; i < 10; i++) {
            System.out.println(randomBST(50));
        }
    }
    private static void testBfs() {
        TreeNode tree = randomBST(20);
        System.out.println("DFS: " + tree); // dfs
        System.out.println("BFS: " + tree.bfs()); // bfs
    }
    private static void testToArray() {
        TreeNode tree = randomBST(20);
        System.out.println("DFS Inorder toArray(): " + Arrays.toString(tree.toArray()));
    }
    private static void testPreOrderToArray() {
        TreeNode tree = randomBST(20);
        System.out.println("DFS Preorder toArray(): " + Arrays.toString(tree.preOrderToArray()));
    }
    private static void testPostOrderToArray() {
        TreeNode tree = randomBST(20);
        System.out.println("DFS Postorder toArray(): " + Arrays.toString(tree.postOrderToArray()));
    }
    public static void main(String[] args) {
        /**
        TreeNode tree = randomStd();
        System.out.println("DFS + inorder: " + tree);
        System.out.println("DFS + pre-order: " + tree.preOrder());
        System.out.println("DFS + post-order: " + tree.postOrder());
        */
       //testRandomBST();
       //testBfs();
       //testToArray();
       //testPreOrderToArray();
       testPostOrderToArray();
    }
}
