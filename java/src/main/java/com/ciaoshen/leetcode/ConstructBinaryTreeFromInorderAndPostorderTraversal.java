/**
 * Leetcode - Algorithm - Construct Binary Tree From Inorder and Postorder Traversal
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) { return null; }
        Map<Integer,Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) { inorderIndexMap.put(inorder[i],i); }
        TreeNode res = buildTreeRecur(postorder,postorder.length-1,0,inorder.length-1,inorderIndexMap);
        return res;
    }
    public TreeNode buildTreeRecur(int[] postorder, int cur, int lo, int hi, Map<Integer,Integer> inorderIndexMap) {
        if (lo > hi) { return null; }
        //System.out.println("Cur = " + cur + ", lo = " + lo + ", hi = " + hi);
        TreeNode root = new TreeNode(postorder[cur]);
        int indexInInorder = inorderIndexMap.get(postorder[cur]);
        int rightSize = hi - indexInInorder;
        root.left = buildTreeRecur(postorder,cur-rightSize-1,lo,indexInInorder-1,inorderIndexMap);
        root.right = buildTreeRecur(postorder,cur-1,indexInInorder+1,hi,inorderIndexMap);
        return root;
    }
    private static ConstructBinaryTreeFromInorderAndPostorderTraversal test = new ConstructBinaryTreeFromInorderAndPostorderTraversal();
    private static void testBuildTree() {
        TreeNode tree = TreeNode.randomBST(20);
        System.out.println("BFS: " + tree.bfs());
        System.out.println("Inorder: " + tree.toString());
        System.out.println("Postorder: " + tree.postOrder());
        System.out.println("Built Tree: " + test.buildTree(tree.toArray(),tree.postOrderToArray()).bfs());
    }
    private static void observation() {
        TreeNode tree = TreeNode.randomBST(20);
        System.out.println("BFS: " + tree.bfs());
        System.out.println("Inorder: " + tree.toString());
        System.out.println("Postorder: " + tree.postOrder());
    }
    public static void main(String[] args) {
        //observation();
        testBuildTree();
    }
}
