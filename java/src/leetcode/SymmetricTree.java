/**
 * Leetcode - Algorithm - Symmetric Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class SymmetricTree {
    public boolean isSymmetricV1(TreeNode root) {
        return (root == null)? true : areSymmetricV1(root.left,root.right);
    }
    public boolean areSymmetricV1(TreeNode one, TreeNode two) {
        if (one == null || two == null) { return one == two; }
        return (one.val == two.val) && areSymmetricV1(one.left,two.right) && areSymmetricV1(one.right,two.left);
    }
    public boolean isSymmetricV2(TreeNode root) {
        if (root == null) { return true; }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        Deque<Boolean> direction = new LinkedList<Boolean>();
        Deque<TreeNode> memo = new LinkedList<TreeNode>();
        Deque<Boolean> directionMemo = new LinkedList<Boolean>();
        TreeNode cur = root;
        boolean reachMid = false;
        boolean fromLeft = false;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.offerFirst(cur);
                if (fromLeft) { // note direction
                    direction.offerFirst(true);
                } else {
                    direction.offerFirst(false);
                }
                cur = cur.left;
                fromLeft = true;
            }
            cur = stack.pollFirst();
            boolean isFromLeft = direction.pollFirst();
            if (!reachMid) { // 没到达中点之前，积累memo
                if (cur != root) {
                    memo.offerFirst(cur);
                    directionMemo.offerFirst(isFromLeft);
                } else {
                    reachMid = true;
                }
            } else { // 过了中点以后，开始和memo里的内容比较
                TreeNode sym = memo.pollFirst();
                Boolean dirInMemo = directionMemo.pollFirst();
                // 不但值必须相等，而且方向必须相反
                if (sym == null || sym.val != cur.val || dirInMemo == isFromLeft) { return false; }
            }
            cur = cur.right;
            fromLeft = false;
        }
        return memo.isEmpty();
    }
    //还是深度优先遍历
    public boolean isSymmetricV3(TreeNode root) {
        if (root == null) { return true; }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode cur = root;
        stack.offerFirst(root.left); stack.offerFirst(root.right);
        while (!stack.isEmpty()) {
            TreeNode n1 = stack.pollFirst(), n2 = stack.pollFirst();
            if (n1 == null && n2 == null) { continue; } // 一对null不算完，让stack吐干净
            if ((n1 != null && n2 == null) || (n1 == null && n2 != null) || (n1.val != n2.val)) { return false; }
            stack.offerFirst(n1.left); stack.offerFirst(n2.right);
            stack.offerFirst(n1.right); stack.offerFirst(n2.left);
        }
        return true;
    }
    // 广度优先遍历
    public boolean isSymmetric(TreeNode root) {
        if (root == null) { return true; }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode cur = root;
        stack.offerLast(root.left); stack.offerLast(root.right);
        while (!stack.isEmpty()) {
            TreeNode n1 = stack.pollFirst(), n2 = stack.pollFirst();
            if (n1 == null && n2 == null) { continue; } // 一对null不算完，让stack吐干净
            if ((n1 != null && n2 == null) || (n1 == null && n2 != null) || (n1.val != n2.val)) { return false; }
            stack.offerLast(n1.left); stack.offerLast(n2.right);
            stack.offerLast(n1.right); stack.offerLast(n2.left);
        }
        return true;
    }
    private static SymmetricTree test = new SymmetricTree();
    private static TreeNode symmetric() {
        TreeNode res = new TreeNode(1);
        res.left = new TreeNode(2);
        res.right = new TreeNode(2);
        res.left.left = new TreeNode(3);
        res.left.right = new TreeNode(4);
        res.right.left = new TreeNode(4);
        res.right.right = new TreeNode(3);
        return res;
    }
    private static TreeNode notSymmetric() {
        TreeNode res = new TreeNode(1);
        res.left = new TreeNode(2);
        res.right = new TreeNode(2);
        res.left.right = new TreeNode(3);
        res.right.right = new TreeNode(3);
        return res;
    }
    private static void testIsSymmetric(){
        System.out.println(test.isSymmetric(symmetric()));
        System.out.println(test.isSymmetric(notSymmetric()));
        TreeNode oneTwo = new TreeNode(1);
        oneTwo.left = new TreeNode(2);
        System.out.println(test.isSymmetric(oneTwo));
        TreeNode oneTwoThreeThreeTwo = new TreeNode(1);
        oneTwoThreeThreeTwo.left = new TreeNode(2);
        oneTwoThreeThreeTwo.right = new TreeNode(3);
        oneTwoThreeThreeTwo.left.left = new TreeNode(3);
        oneTwoThreeThreeTwo.left.right = null;
        oneTwoThreeThreeTwo.right.left = new TreeNode(2);
        oneTwoThreeThreeTwo.right = null;
        System.out.println(test.isSymmetric(oneTwoThreeThreeTwo));
    }
    public static void main(String[] args) {
        testIsSymmetric();
    }
}
