/**
 * Leetcode - Algorithm - Populating Next Right Pointers to Each Node
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeLinkNode;

class PopulatingNextRightPointersToEachNode {
    public void connectV1(TreeLinkNode root) {
        if (root == null) { return; }
        List<TreeLinkNode> buffer = new ArrayList<>();
        buffer.add(root);
        while (!buffer.isEmpty()) {
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = buffer.remove(0);
                if (node.left != null && node.right != null) {
                    node.left.next = node.right;
                    if (node.next != null) {
                        node.right.next = node.next.left;
                    }
                    buffer.add(node.left);
                    buffer.add(node.right);
                }
            }
        }
    }
    public void connectV2(TreeLinkNode root) {
        if (root == null) { return; }
        recursion(root,null);
    }
    public void recursion(TreeLinkNode root, TreeLinkNode nextLevel) {
        if (root.left == null && root.right == null) { return; }
        if (nextLevel == null) { nextLevel = root.left; }
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        if (root.next != null) {
            recursion(root.next,nextLevel);
        } else {
            recursion(nextLevel,null);
        }
    }
    public void connect(TreeLinkNode root) {
        if (root == null) { return; }
        TreeLinkNode nextLevel = null, cur = root;
        while (cur.left != null && cur.right != null) {
            nextLevel = cur.left;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            cur = nextLevel;
        }
    }
    private static PopulatingNextRightPointersToEachNode test = new PopulatingNextRightPointersToEachNode();
    private static void testConnect() {
        return;
    }
    public static void main(String[] args) {

    }
}
