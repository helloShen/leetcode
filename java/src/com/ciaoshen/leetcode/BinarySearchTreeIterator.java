/**
 * Leetcode - Algorithm - Binary Search Tree Iterator
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class BinarySearchTreeIterator {
    public class BSTIterator {
        private Deque<TreeNode> stack = new LinkedList<>();
        public BSTIterator(TreeNode root) {
            TreeNode cur = root;
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number.
         *  return 0, if the iterator reach the end of the tree.
         */
        public int next() {
            if (hasNext()) {
                TreeNode curr = stack.pollFirst();
                int ret = curr.val;
                if  (curr.right != null) {
                    TreeNode cur = curr.right;
                    while (cur != null) {
                        stack.offerFirst(cur);
                        cur = cur.left;
                    }
                }
                return ret;
            } else {
                return 0;
            }
        }
    }
    private static BinarySearchTreeIterator test = new BinarySearchTreeIterator();
    private static void callBSTIterator(TreeNode root) {
        BSTIterator ite = test.new BSTIterator(root);
        while (ite.hasNext()) {
            System.out.print("[" + ite.next() + "] ");
        }
    }
    private static void test() {
        TreeNode root = TreeNode.randomBST(100);
        System.out.println("Tree = " + root.bfs() + "\n");
        callBSTIterator(root);
    }
    public static void main(String[] args) {
        test();
    }
}
