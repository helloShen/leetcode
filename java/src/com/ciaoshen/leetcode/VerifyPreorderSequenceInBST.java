/**
 * Leetcode - Algorithm - VerifyPreorderSequenceInBST
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class VerifyPreorderSequenceInBST implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private VerifyPreorderSequenceInBST() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
        register(new Solution6());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean verifyPreorder(int[] preorder);
    }
    /**
     * 完整实现TreeNode.
     * 完整构建二叉树，然后preorder遍历，最后比较原数组和preorder遍历的输出。
     */
    private class Solution1 extends Solution {
        { super.id = 1; }
        private class TreeNode {
            private int val;
            private TreeNode left;
            private TreeNode right;
            public TreeNode(int val) { this.val = val; }
            public boolean insertIterative(int val) {
                TreeNode node = new TreeNode(val);
                TreeNode cur = this;
                while (cur != null) {
                    if (cur.val > val) {
                        if (cur.left != null) {
                            cur = cur.left; continue;
                        } else {
                            cur.left = node; return true;
                        }
                    } else if (cur.val < val) {
                        if (cur.right != null) {
                            cur = cur.right; continue;
                        } else {
                            cur.right = node; return true;
                        }
                    } else { // duplicate value found
                        return false;
                    }
                }
                return true;
            }
            public boolean insert(int num) {
                TreeNode node = new TreeNode(num);
                if (val > num) { // go left
                    if (left == null) {
                        left = node; return true;
                    } else {
                        return left.insert(num);
                    }
                } else if (val < num) { // go right
                    if (right == null) {
                        right = node; return true;
                    } else {
                        return right.insert(num);
                    }
                } else { // duplicate value
                    return false;
                }
            }
        }
        public boolean verifyPreorder(int[] preorder) {
            if (preorder.length == 0) { return true; }
            TreeNode root = new TreeNode(preorder[0]);
            for (int i = 1; i < preorder.length; i++) {
                if(!root.insert(preorder[i])) { return false; }
            }
            List<Integer> order = preorder(root);
            if (preorder.length != order.size()) { return false; }
            for (int i = 0; i < preorder.length; i++) {
                if (preorder[i] != order.get(i)) { return false; }
            }
            return true;
        }
        private List<Integer> preorder(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) { return result; }
            result.add(root.val);
            result.addAll(preorder(root.left));
            result.addAll(preorder(root.right));
            return result;
        }
    }
    /**
     * 同样需要实现TreeNode。
     * 在构建二叉树的时候直接判断是否符合条件。不用后期preorder遍历再比较。
     */
    private class Solution2 extends Solution {
        { super.id = 2; }
        private class TreeNode {
            private int val;
            private TreeNode left;
            private TreeNode right;
            public TreeNode(int val) { this.val = val; }
            public boolean insertRecursively(int num, int[] min) {
                if (num < min[0]) { return false; }
                TreeNode node = new TreeNode(num);
                if (val > num) { // go left
                    if (left == null) {
                        left = node; return true;
                    } else {
                        return left.insertRecursively(num,min);
                    }
                } else if (val < num) { // go right
                    min[0] = Math.max(min[0],val); // 关键就在这里: 要进入右子树，以后再也不能进入左子树
                    if (right == null) {
                        right = node; return true;
                    } else {
                        return right.insertRecursively(num,min);
                    }
                } else { // duplicate value
                    return false;
                }
            }
        }
        public boolean insert(TreeNode root, int num, int[] min) {
            if (num < min[0]) { return false; }
            TreeNode node = new TreeNode(num);
            while (root != null) {
                if (root.val > num) { // go left
                    if (root.left != null) {
                        root = root.left; continue;
                    } else {
                        root.left = node; return true;
                    }
                } else { // go right
                    min[0] = Math.max(min[0],root.val); // KEY: once enter right subtree, never to back to left subtree
                    if (root.right != null) {
                        root = root.right; continue;
                    } else {
                        root.right = node; return true;
                    }
                }
            }
            return true;
        }
        public boolean verifyPreorder(int[] preorder) {
            if (preorder.length == 0) { return true; }
            TreeNode root = new TreeNode(preorder[0]);
            int[] min = new int[]{Integer.MIN_VALUE};
            for (int i = 1; i < preorder.length; i++) {
                if (!insert(root,preorder[i],min)) { return false; }
            }
            return true;
        }
    }

    /**
     * 不需要TreeNode.
     * 也不需要额外空间，直接在原数组上操作。
     * 时间复杂度：O(n^2)
     */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean verifyPreorder(int[] preorder) {
            int min = Integer.MIN_VALUE;
            for (int i = 0; i < preorder.length; i++) {
                if (preorder[i] < min) { return false; }
                for (int j = i; j >= 0; j--) {
                    if (preorder[i] > preorder[j]) { min = Math.max(min,preorder[j]); }
                }
            }
            return true;
        }
    }
    /**
     * 不构建TreeNode
     * 利用Stack，一次遍历完成。
     * 时间复杂度：O(n)
     */
    private class Solution4 extends Solution {
        { super.id = 4; }

        public boolean verifyPreorder(int[] preorder) {
            int min = Integer.MIN_VALUE;
            Deque<Integer> stack = new LinkedList<>(); // stack里的数字总是由大到小排列
            for (int num : preorder) {
                if (num < min) { return false; }
                while (!stack.isEmpty() && stack.peekFirst() < num) {
                    min = stack.pollFirst();
                }
                stack.offerFirst(num);
            }
            return true;
        }
    }
    /**
     * 不构建TreeNode
     * 利用数组模仿Stack，一次遍历完成。
     * 时间复杂度：O(n)
     */
    private class Solution5 extends Solution {
        { super.id = 5; }

        public boolean verifyPreorder(int[] preorder) {
            int min = Integer.MIN_VALUE;
            int[] stack = new int[preorder.length];
            int index = -1;
            for (int num : preorder) {
                if (num < min) { return false; }
                while (index >= 0 && stack[index] < num) {
                    min = stack[index--];
                }
                stack[++index] = num;
            }
            return true;
        }
    }

    /**
     * 不构建TreeNode
     * 利用数组模仿Stack，一次遍历完成。
     * 时间复杂度：O(n)
     * 允许破坏原始参数数组，可以不用额外空间
     */
    private class Solution6 extends Solution {
        { super.id = 6; }

        public boolean verifyPreorder(int[] preorder) {
            int min = Integer.MIN_VALUE;
            int index = -1;
            for (int num : preorder) {
                if (num < min) { return false; }
                while (index >= 0 && preorder[index] < num) {
                    min = preorder[index--];
                }
                preorder[++index] = num;
            }
            return true;
        }
    }

    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private VerifyPreorderSequenceInBST problem = new VerifyPreorderSequenceInBST();
        private Solution solution = null;

        // call method in solution
        private void call(int[] preorder, String answer) {
            System.out.println(Arrays.toString(preorder) + " is preorder? " + ((solution.verifyPreorder(preorder))? "true":"false") + "       answer[" + answer + "]");
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int[] nums1 = new int[]{9,5,4,7,6,8,12,10,11,15};
            int[] nums2 = new int[]{1,2,3,4,5};
            int[] nums3 = new int[]{7,9,5};
            int[] nums4 = new int[]{3,1,4,2};
            call(nums1,"true");
            call(nums2,"true");
            call(nums3,"false");
            call(nums4,"false");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.test(4);
        // test.test(5);
        test.test(6);

    }
}
