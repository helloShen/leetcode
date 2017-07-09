/**
 * Leetcode - Algorithm - Sum Root to Leaf Numbers
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class SumRootToLeafNumbers {
    public int sumNumbersV1(TreeNode root) {
        List<List<Integer>> res = dpV1(root);
        int sum = 0;
        for (List<Integer> list : res) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                sum += list.get(i) * (int)Math.pow(10,size-1-i);
            }
        }
        return sum;
    }
    public List<List<Integer>> dpV1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) { return res; }
        if (root.left == null && root.right == null) {
            res.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{root.val})));
            return res;
        }
        List<List<Integer>> left = dpV1(root.left);
        List<List<Integer>> right = dpV1(root.right);
        for (List<Integer> list : left) {
            list.add(0,root.val);
            res.add(list);
        }
        for (List<Integer> list: right) {
            list.add(0,root.val);
            res.add(list);
        }
        return res;
    }
    public int sumNumbersV2(TreeNode root) {
        List<String> res = dpV2(root);
        int sum = 0;
        for (String s : res) {
            sum += Integer.parseInt(s);
        }
        return sum;
    }
    public List<String> dpV2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) { return res; }
        if (root.left == null && root.right == null) {
            res.add(""+root.val);
            return res;
        }
        List<String> left = dpV2(root.left);
        List<String> right = dpV2(root.right);
        for (String s : left) {
            res.add(root.val + s);
        }
        for (String s : right) {
            res.add(root.val + s);
        }
        return res;
    }
    public int sumNumbers(TreeNode root) {
        if (root == null) { return 0; }
        int[] res = new int[1];
        backtracking(root,0,res);
        return res[0];
    }
    public void backtracking(TreeNode root, int sum, int[] res) {
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) { res[0] += sum; }
        if (root.left != null) { backtracking(root.left,sum,res); }
        if (root.right != null) { backtracking(root.right,sum,res); }
    }
    private static SumRootToLeafNumbers test = new SumRootToLeafNumbers();
    /**
    private static void testDp() {
        TreeNode tree = TreeNode.randomBST(9);
        System.out.println(tree.bfs());
        System.out.println(test.dp(tree));
    }
    */
    private static void testSumNumbers(TreeNode tree) {
        System.out.println("Tree: " + tree.bfs());
        //System.out.println("Numbers: " + test.dp(tree));
        System.out.println("Sum: " + test.sumNumbers(tree));
    }
    public static void main(String[] args) {
        //testDp();
        TreeNode tree = TreeNode.randomBST(9);
        testSumNumbers(tree);
        TreeNode zeroOne = new TreeNode(0);
        zeroOne.right = new TreeNode(1);
        testSumNumbers(zeroOne);
    }
}
