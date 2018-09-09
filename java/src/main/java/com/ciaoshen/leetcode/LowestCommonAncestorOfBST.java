/**
 * Leetcode - Algorithm - Lowest common Ancestor of a Binary Search Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class LowestCommonAncestorOfBST {
    public class SolutionV1 {
        /**
         * Return the lowest common ancestor of two node p & q in a tree root.
         * Return null if nothing found.
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            Deque<TreeNode> pathP = getPath(root,p);
            Deque<TreeNode> pathQ = getPath(root,q);
            TreeNode ancestor = null;
            while (!pathP.isEmpty() && !pathQ.isEmpty()) {
                TreeNode currP = pathP.pollFirst();
                TreeNode currQ = pathQ.pollFirst();
                if (currP == currQ) { ancestor = currP; }
            }
            return ancestor;
        }
        /**
         * Return a queue of nodes on the path towards target node.
         * Return an empty container if target is not found.
         */
        private Deque<TreeNode> getPath(TreeNode root, TreeNode target) {
            Deque<TreeNode> path = new LinkedList<>();
            if (target == null) { return path; }
            findTarget(root,target,path);
            return path;
        }
        /**
         *  If target is found, return true. path parameter contains all node on the path.
         *  Otherwise, return false. And the path parameter will be empty.
         */
        private boolean findTarget(TreeNode root, TreeNode target, Deque<TreeNode> path) {
            if (root == null) { return false; }
            if (root == target) { path.offerFirst(root); return true; }
            if (findTarget(root.left,target,path)) { path.offerFirst(root); return true; }
            if (findTarget(root.right,target,path)) { path.offerFirst(root); return true; }
            return false;
        }
    }
    public class SolutionV2 {
        /**
         * Return the lowest common ancestor of two node p & q in a tree root.
         * Return null if nothing found.
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            List<TreeNode> pathP = getPath(root,p);
            List<TreeNode> pathQ = getPath(root,q);
            TreeNode ancestor = null;
            for (int i = 0; i < pathP.size() && i < pathQ.size(); i++) {
                TreeNode currP = pathP.get(i);
                TreeNode currQ = pathQ.get(i);
                if (currP == currQ) { ancestor = currP; }
            }
            return ancestor;
        }
        /**
         * Return a queue of nodes on the path towards target node.
         * Return an empty container if target is not found.
         */
        private List<TreeNode> getPath(TreeNode root, TreeNode target) {
            List<TreeNode> path = new ArrayList<>();
            if (target == null) { return path; }
            findTarget(root,target,path);
            return path;
        }
        /**
         *  If target is found, return true. path parameter contains all node on the path.
         *  Otherwise, return false. And the path parameter will be empty.
         */
        private void findTarget(TreeNode root, TreeNode target, List<TreeNode> path) {
            if (root == null) { path.clear(); return; }
            path.add(root);
            if (root.val > target.val) {
                findTarget(root.left,target,path);
            } else if (root.val < target.val) {
                findTarget(root.right,target,path);
            }
        }
    }
    /** 直接用两个指针搜索路径。
     *  如果有元素没有找到，返回 null。
     */
    public class SolutionV3 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode curP = root, curQ = root;
            TreeNode commonAncestor = null;
            while ((curP != null && curP != p) || (curQ != null && curQ != q)) {
                if (curP == curQ) {
                    commonAncestor = curP;
                } else {
                    break;
                }
                if (curP != null && curP != p) {
                    if (curP.val > p.val) {
                        curP = curP.left;
                    } else if (curP.val < p.val) {
                        curP = curP.right;
                    }
                }
                if (curQ != null && curQ != q) {
                    if (curQ.val > q.val) {
                        curQ = curQ.left;
                    } else if (curQ.val < q.val) {
                        curQ = curQ.right;
                    }
                }
            }
            return (curP == null || curQ == null)? null : commonAncestor;
        }
    }
    /**
     * 如果p或q元素不存在，就以他们应该插入的位置来查找最近共同祖先。
     */
    public class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode commonAncestor = null;
            TreeNode curP = root, curQ = root;
            while (curP == curQ && curP != null) {
                commonAncestor = curP;
                curP = next(curP,p);
                curQ = next(curQ,q);
            }
            return commonAncestor;
        }
        /**
         * pointer go forward in binary tree.
         * return null if curr == target (means stop iteration when target is found)
         */
        public TreeNode next(TreeNode curr, TreeNode target) {
            if (curr.val > target.val) {
                return curr.left;
            } else if (curr.val < target.val) {
                return curr.right;
            } else {
                return null;
            }
        }
    }

    private class Test {
        private TreeNode zero = new TreeNode(0);
        private TreeNode one = new TreeNode(1);
        private TreeNode two = new TreeNode(2);
        private TreeNode three = new TreeNode(3);
        private TreeNode four = new TreeNode(4);
        private TreeNode five = new TreeNode(5);
        private TreeNode six = new TreeNode(6);
        private TreeNode seven = new TreeNode(7);
        private TreeNode eight = new TreeNode(8);
        private TreeNode nine = new TreeNode(9);
        private void reset() {
            zero = new TreeNode(0);
            one = new TreeNode(1);
            two = new TreeNode(2);
            three = new TreeNode(3);
            four = new TreeNode(4);
            five = new TreeNode(5);
            six = new TreeNode(6);
            seven = new TreeNode(7);
            eight = new TreeNode(8);
            nine = new TreeNode(9);
        }
        private void callLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode d) {
            System.out.println("For Tree: " + root.bfs());
            TreeNode lowestCommonAncestor = solution.lowestCommonAncestor(root,p,d);
            if (lowestCommonAncestor == null) {
                System.out.println("The lowest common ancestor of " + p.val + " and " + d.val + " is: NULL");
            } else {
                System.out.println("The lowest common ancestor of " + p.val + " and " + d.val + " is: " + lowestCommonAncestor.val);
            }
        }
        private void test() {
            six.left = two; six.right = eight;
            two.left = zero; two.right = four;
            four.left = three; four.right = five;
            eight.left = seven; eight.right = nine;
            TreeNode root = six, p = two, q = eight;
            callLowestCommonAncestor(root,p,q);
            reset();
            two.left = one;
            root = two; p = two; q = one;
            callLowestCommonAncestor(root,p,q);
        }
    }
    private static LowestCommonAncestorOfBST problem = new LowestCommonAncestorOfBST();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
