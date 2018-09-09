/**
 * Leetcode - Algorithm - Path Sum Two
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class PathSumTwo {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) { return res; }
        backtracking(root,sum,new ArrayList<Integer>(),res);
        return res;
    }
    public void backtracking(TreeNode root, int remain, List<Integer> path, List<List<Integer>> res) {
        if (root == null) { return; }
        path.add(root.val);
        int nowRemain = remain - root.val;
        if (root.left == null && root.right == null && nowRemain == 0) {
            res.add(new ArrayList<Integer>(path));
        } else {
            backtracking(root.left,nowRemain,path,res);
            backtracking(root.right,nowRemain,path,res);
        }
        path.remove(path.size()-1);
        return;
    }
    private static PathSumTwo test = new PathSumTwo();
    private static void testPathSum() {
        int sum = 50;
        for (int i = 0; i < 10; i++) {
            TreeNode tree = TreeNode.randomBST(20);
            System.out.println(tree.bfs());
            System.out.println("Sum Path " + sum + " = " + test.pathSum(tree,sum));
        }
    }
    public static void main(String[] args) {
        testPathSum();
    }
}
