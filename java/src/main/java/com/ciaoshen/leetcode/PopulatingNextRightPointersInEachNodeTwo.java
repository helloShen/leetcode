/**
 * Leetcode - Algorithm - Populating Next Right Pointers in Each Node Two
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeLinkNode;

class PopulatingNextRightPointersInEachNodeTwo {
    public void connectV1(TreeLinkNode root) {
        if (root == null) { return; }
        List<TreeLinkNode> buffer = new ArrayList<>();
        buffer.add(root);
        while (!buffer.isEmpty()) {
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = buffer.remove(0);
                if (i+1 < size) { // 链接同层下一个元素
                    TreeLinkNode nextNode = buffer.get(0);
                    node.next = nextNode;
                }
                if (node.left != null) { buffer.add(node.left); }
                if (node.right != null) { buffer.add(node.right); }
            }
        }
    }
    public void connectV2(TreeLinkNode root) {
        recursion(root,null);
    }
    public void recursion(TreeLinkNode root, TreeLinkNode nextLevel) {
        if (root == null) { return; }
        if (nextLevel == null) {
            if (root.left != null) {
                nextLevel = root.left;
            } else if (root.right != null) {
                nextLevel = root.right;
            }
        }
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }
        TreeLinkNode lastChild = null;
        if (root.right != null) {
            lastChild = root.right;
        } else if (root.left != null){
            lastChild = root.left;
        }
        TreeLinkNode next = root.next, firstNextChild = null;
        while (next != null) {
            if (next.left != null) { firstNextChild = next.left; break; }
            if (next.right != null) { firstNextChild = next.right; break; }
            next = next.next;
        }
        if (lastChild != null && firstNextChild != null) {
            lastChild.next = firstNextChild;
        }
        if (next != null) {
            recursion(next,nextLevel);
        } else {
            recursion(nextLevel,null);
        }
    }
    public void connectV3(TreeLinkNode root) {
        TreeLinkNode nextLevel = null;
        TreeLinkNode cur = root, next = cur.next;
        TreeLinkNode lastChild = null, firstNextChild = null;
        while (cur != null) {
            if (nextLevel == null) {
                if (cur.left != null) {
                    nextLevel = cur.left;
                } else if (cur.right != null) {
                    nextLevel = cur.right;
                }
            }
            if (cur.left != null && cur.right != null) {
                cur.left.next = cur.right;
            }
            lastChild = null;
            if (cur.right != null) {
                lastChild = cur.right;
            } else if (cur.left != null){
                lastChild = cur.left;
            }
            next = cur.next; firstNextChild = null;
            while (next != null) {
                if (next.left != null) { firstNextChild = next.left; break; }
                if (next.right != null) { firstNextChild = next.right; break; }
                next = next.next;
            }
            if (lastChild != null && firstNextChild != null) {
                lastChild.next = firstNextChild;
            }
            cur = (next != null)? next:nextLevel;
        }
    }
    public void connect(TreeLinkNode root) {
        TreeLinkNode cur = root; // 指向当前节点（实际处理的是当前节点的下一层子节点）
        TreeLinkNode nextLevelHead = null; // 下一层子节点的首元素（当这一层节点用完，需要跳转到下一行）
        TreeLinkNode pre = null; // 前一个被链接的子节点。
        while (cur != null) {
            if (cur.left != null) {
                if (pre == null) { // 当前节点的下一行，还没有一个节点被链接。
                    nextLevelHead = cur.left;
                } else {
                    pre.next = cur.left;
                }
                pre = cur.left;
            }
            if (cur.right != null) {
                if (pre == null) {
                    nextLevelHead = cur.right;
                } else {
                    pre.next = cur.right;
                }
                pre = cur.right;
            }
            if (cur.next != null) {
                cur = cur.next;
            } else {
                cur = nextLevelHead;
                nextLevelHead = null;
                pre = null;
            }
        }
    }
    private static PopulatingNextRightPointersInEachNodeTwo test = new PopulatingNextRightPointersInEachNodeTwo();
    private static void testConnect() {
        TreeLinkNode tree = TreeLinkNode.randomBST(20);
        System.out.println("Tree: " + tree.bfs());
        test.connect(tree);
        if (tree.left != null) { System.out.println(tree.left.next); }
        if (tree.right != null) { System.out.println(tree.right.next); }
        TreeLinkNode oneTwo = new TreeLinkNode(1);
        oneTwo.left = new TreeLinkNode(2);
        System.out.println(oneTwo.bfs());
        System.out.println(oneTwo.next);
        System.out.println(oneTwo.left.next);
    }
    public static void main(String[] args) {
        testConnect();
    }
}
