/**
 * Leetcode - Algorithm - Flatten Binary Tree to Linked List
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class FlattenBinaryTreeToLinkedList {
    public void flattenV1(TreeNode root) {
        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur.left != null || cur.right != null) {
                if (cur.left != null) {
                    if (cur.right != null) {
                        stack.offerFirst(cur.right);
                    }
                    cur.right = cur.left;
                    cur.left = null;
                }
                cur = cur.right;
            }
            cur.right = stack.pollFirst();
            cur = cur.right;
        }
    }
    public void flattenV2(TreeNode root) {
        flattenRecurV1(root);
    }
    // return TreeNode[2]: [head,tail]
    public TreeNode[] flattenRecurV1(TreeNode root) {
        TreeNode[] headTail = new TreeNode[2];
        if (root == null) { return headTail; }
        TreeNode[] leftSub = flattenRecurV1(root.left);
        TreeNode[] rightSub = flattenRecurV1(root.right);
        TreeNode cur = root;
        if (leftSub[0] != null) { cur.left = null; cur = grafting(cur,leftSub); }
        if (rightSub[0] != null) { cur = grafting(cur,rightSub); }
        headTail[0] = root; headTail[1] = cur;
        return headTail;
    }
    // graft target as the right subtree of root.
    // return the tail of the target
    public TreeNode grafting(TreeNode root, TreeNode[] target) {
        TreeNode cur = root;
        cur.right = target[0];
        return target[1];
    }
    public void flatten(TreeNode root) {
        flattenRecur(root,null);
    }
    public TreeNode flattenRecur(TreeNode root, TreeNode pre) {
        if (root == null) { return pre; }
        TreeNode cur = root;
        pre = flattenRecur(root.right,pre);
        pre = flattenRecur(root.left,pre);
        root.left = null;
        root.right = pre;
        return root;
    }
    private static FlattenBinaryTreeToLinkedList test = new FlattenBinaryTreeToLinkedList();
    private static void testFlatten() {
        TreeNode tree = TreeNode.randomBST(20);
        System.out.println("BFS: " + tree.bfs());
        test.flatten(tree);
        System.out.println("After flatten: " + tree.bfs());
    }
    public static void main(String[] args) {
        testFlatten();
    }
}
