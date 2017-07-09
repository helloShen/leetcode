/**
 * Leetcode - Algorithm - Kth Smallest Element in BST
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class KthSmallestElementInBST {
    /**
     * Iterate the Tree (Iteration)
     * time: O(n)
     * space: O(1)
     */
    public class SolutionV1 {
        public int kthSmallest(TreeNode root, int k) {
            int count = 0;
            Deque<TreeNode> stack = new LinkedList<>(); //已找到的待处理的左倾线上的节点
            TreeNode cur = root; // 当前需要检查左倾线的节点（通常是右节点，除非是根节点）
            // find the smallest element
            while (cur != null || !stack.isEmpty()) {
                while (cur != null) {
                    stack.offerFirst(cur);
                    cur = cur.left;
                }
                TreeNode node = stack.pollFirst(); count++; // count number
                System.out.println(count + "th element = " + node.val);
                if (count == k) { return node.val; }
                cur = node.right;
            }
            return 0; // never reached
        }
    }
    /**
     * Iterate the Tree (recursion)
     * time: O(n)
     * space: O(1)
     */
    public class SolutionV2 {
        public int kthSmallest(TreeNode root, int k) {
            int[] result = new int[2];
            result = kth(root,result,k);
            return result[1];
        }
        public int[] kth(TreeNode root, int[] parentOrder, int k) {
            if (root == null) { return parentOrder; }
            int[] pair = kth(root.left, parentOrder, k);
            if (pair[0] < k) {
                pair[0]++;
                if (pair[0] < k) {
                    return kth(root.right,pair,k);
                } else {
                    pair[1] = root.val; // this is the kth smallest element
                    return pair;
                }
            } else {
                return pair;
            }
        }
    }
    /**
     * Use 2 member fields
     */
    public class Solution {
        private int k = 0, order = 0, value = 0;
        public int kthSmallest(TreeNode root, int k) {
            init(k);
            kth(root);
            return value;
        }
        private void kth(TreeNode root) {
            if (root == null) { return; }
            kth(root.left);
            if (order == k) { return; }
            order++;
            if (order == k) {
                value = root.val; return;
            }
            kth(root.right);
        }
        private void init(int i) {
            k = i; order = 0; value = 0;
        }
    }
    private class Test {
        private void callKthSmallest(TreeNode root, int k) {
            System.out.println("In tree: " + root.bfs());
            System.out.println(k + "th Smallest Element is: " + solution.kthSmallest(root,k));
        }
        private void test() {
            TreeNode tree1 = TreeNode.randomBST(20);
            // TreeNode tree2 =;
            // TreeNode tree3 =;
            int k1 = 5;
            // int k2 = ;
            // int k3 = ;
            callKthSmallest(tree1,k1);
        }
    }
    private static KthSmallestElementInBST problem = new KthSmallestElementInBST();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
