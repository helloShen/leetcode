/**
 * Leetcode - Algorithm - Unique Binary Search Trees Two
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;
import com.ciaoshen.leetcode.myUtils.Matrix;

class UniqueBinarySearchTreesTwo {
    public List<TreeNode> generateTreesV1(int n) {
        List<TreeNode> res = new ArrayList<>();
        List<List<Integer>> numsStream = permutation(n);
        for (List<Integer> nums : numsStream) {
            TreeNode tree = buildTree(nums);
            if (isNewTree(res,tree)) { res.add(tree); }
        }
        return res;
    }
    public boolean isNewTree(List<TreeNode> res, TreeNode tree) {
        for (TreeNode member : res) {
            if (equals(member,tree)) { return false; }
        }
        return true;
    }
    public List<List<Integer>> permutation(int n) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) { nums.add(i); }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        permutationRecur(nums,new ArrayList<Integer>(),res);
        return res;
    }
    public void permutationRecur(List<Integer> nums, List<Integer> temp, List<List<Integer>> res) {
        if (nums.isEmpty() && !temp.isEmpty()) { res.add(new ArrayList<Integer>(temp)); return; }
        for (int i = 0; i < nums.size(); i++) {
            temp.add(nums.remove(i));
            permutationRecur(nums,temp,res);
            nums.add(i,temp.remove(temp.size()-1));
        }
    }
    public TreeNode buildTree(List<Integer> nums) {
        if (nums.isEmpty()) { return null; }
        TreeNode res = new TreeNode(nums.get(0));
        for (int i = 1; i < nums.size(); i++) {
            res = insert(res,nums.get(i));
        }
        return res;
    }
    public TreeNode insert(TreeNode tree, int num) {
        TreeNode newNode = new TreeNode(num);
        if (tree == null) { return newNode; }
        TreeNode cur = tree, pre = tree;
        boolean goLeft = num < tree.val;
        while (cur != null) {
            pre = cur;
            if (num < cur.val) {
                cur = cur.left;
                goLeft = true;
            } else { // num > cur.val  (num == cur.val is unreachable)
                cur = cur.right;
                goLeft = false;
            }
        }
        if (goLeft) {
            pre.left = newNode;
        } else {
            pre.right = newNode;
        }
        return tree;
    }
    public boolean equals(TreeNode first, TreeNode second) {
        if (first == null && second == null) { return true; }
        if (!nodeEquals(first,second)) { return false; } // 数值不等，或者只有其中一个为空
        // assert: first != null && second != null && first.val == second.val
        return (equals(first.left,second.left) && equals(first.right,second.right));
    }
    public boolean nodeEquals(TreeNode first, TreeNode second) {
        return (first == null)? second == null : first.val == second.val;
    }
    private static UniqueBinarySearchTreesTwo test = new UniqueBinarySearchTreesTwo();
    private static void testGenerateTrees() {
        for (int i = 0; i < 6; i++) {
            System.out.println("Result n = " + i + " " + test.generateTrees(i));
        }
    }
    private static void testPermutationRecur() {
        for (int i = 1; i < 5; i++) {
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                nums.add(j+1);
            }
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            test.permutationRecur(nums,new ArrayList<Integer>(),res);
            System.out.println(res);
        }
    }
    private static void testPermutation() {
        for (int i = 1; i < 5; i++) {
            System.out.println(test.permutation(i));
        }
    }
    private static void testBuildTree() {
        List<List<Integer>> testNums = test.permutation(4);
        for (List<Integer> nums : testNums) {
            System.out.println(test.buildTree(nums));
        }
    }
    private static void testEquals() {
        TreeNode threeOneTwoFour = test.buildTree(new ArrayList<Integer>(Arrays.asList(new Integer[]{3,1,2,4})));
        TreeNode threeOneFourTwo = test.buildTree(new ArrayList<Integer>(Arrays.asList(new Integer[]{3,1,4,2})));
        TreeNode threeFourOneTwo = test.buildTree(new ArrayList<Integer>(Arrays.asList(new Integer[]{3,4,1,2})));
        TreeNode oneTwoThreeFour = test.buildTree(new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4})));
        TreeNode twoThreeFourOne = test.buildTree(new ArrayList<Integer>(Arrays.asList(new Integer[]{2,3,4,1})));
        TreeNode oneTwoThree = test.buildTree(new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3})));
        System.out.println("[3,1,2,4] = [3,1,4,2] ? " + test.equals(threeOneTwoFour,threeOneFourTwo)); // true
        System.out.println("[3,1,2,4] = [3,4,1,2] ? " + test.equals(threeOneTwoFour,threeFourOneTwo)); // true
        System.out.println("[3,1,4,2] = [3,4,1,2] ? " + test.equals(threeOneFourTwo,threeFourOneTwo)); // true
        System.out.println("[3,1,2,4] = [1,2,3,4] ? " + test.equals(threeOneTwoFour,oneTwoThreeFour)); // false
        System.out.println("[3,1,2,4] = [2,3,4,1] ? " + test.equals(threeOneTwoFour,twoThreeFourOne)); // false
        System.out.println("[3,1,2,4] = [1,2,3] ? " + test.equals(threeOneTwoFour,oneTwoThree)); // false
    }
    public List<TreeNode> generateTreesV2(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n <= 0) { return res; }
        if (n == 1) { res.add(new TreeNode(1)); return res; }
        res.addAll(generateTrees(n-1));
        TreeNode nodeN = new TreeNode(n);
        int size = res.size();
        for (int i = 0; i < size; i++) {
            TreeNode dummy = new TreeNode(Integer.MIN_VALUE), cur = dummy;
            dummy.right = res.remove(0);
            //int count = 0;
            while (cur != null) {
                //++count;
                TreeNode oldRight = cur.right;
                cur.right = nodeN; // 嫁接
                nodeN.left = oldRight;
                res.add(copyOf(dummy.right)); // 定妆照
                cur.right = oldRight; // 回溯
                nodeN.left = null;
                cur = cur.right;
            }
            //System.out.println("This tree has " + count+ " variants!");
        }
        return res;
    }
    public Iterator<TreeNode> iterator(TreeNode tree) {
        return new Iterator<TreeNode>() {
            private TreeNode myTree = tree;
            private TreeNode cur = myTree;
            private Deque<TreeNode> stack = new LinkedList<TreeNode>();
            public boolean hasNext() {
                return cur != null || !stack.isEmpty();
            }
            public TreeNode next() {
                if (!hasNext()) { return null; }
                while (cur != null) {
                    stack.offerFirst(cur);
                    cur = cur.left;
                }
                cur = stack.pollFirst();
                TreeNode res = cur;
                cur = cur.right;
                return res;
            }
            public void remove() {
                throw new UnsupportedOperationException("Tree Iterator doesn't support remove() method!");
            }
        };
    }
    public TreeNode copyOf(TreeNode tree) {
        if (tree == null) { return null; }
        TreeNode head = new TreeNode(tree.val);
        head.left = copyOf(tree.left);
        head.right = copyOf(tree.right);
        return head;
    }
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) { return new ArrayList<TreeNode>(); }
        return recursive(1,n);
    }
    public List<TreeNode> recursive(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) { res.add(null); return res; } // must have null
        for (int index = start; index <= end; index++) {
            List<TreeNode> leftSubTree = recursive(start,index-1);
            List<TreeNode> rightSubTree = recursive(index+1,end);
            for (TreeNode ln : leftSubTree) {
                for (TreeNode rn : rightSubTree) {
                    TreeNode root = new TreeNode(index);
                    root.left = ln;
                    root.right = rn;
                    res.add(root);
                }
            }
        }
        return res;
    }
    private static void testIterator() {
        TreeNode tree = TreeNode.randomStd();
        Iterator<TreeNode> ite = test.iterator(tree);
        System.out.println("Tree: " + tree);
        System.out.print("Iteration: ");
        while (ite.hasNext()) {
            System.out.print("[" + ite.next().val + "]");
        }
        System.out.println("");
    }
    private static void testCopyOf() {
        TreeNode tree = TreeNode.randomStd();
        System.out.println("Tree: " + tree);
        System.out.println("Copy: " + test.copyOf(tree));
    }
    public static void main(String[] args) {
        //testPermutationRecur();
        //testPermutation();
        //testBuildTree();
        //testEquals();
        //testIterator();
        //testCopyOf();
        testGenerateTrees();
    }
}
