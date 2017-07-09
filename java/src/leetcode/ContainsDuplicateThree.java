/**
 * Leetcode - Algorithm - Contains Duplicate Three
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ContainsDuplicateThree {
    /**
     * O(n): use HashSet to check the duplicate
     */
    public class SolutionV1 {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (nums.length < 2) { return false; }
            for (int slow = 0, fast = 1; fast < nums.length; fast++) {
                if (fast > k) { slow++; }
                int newNum = nums[fast];
                for (int cur = slow; cur < fast; cur++) {
                    long diff = Math.abs((long)nums[cur] - (long)newNum);
                    if (diff <= t) { System.out.println(nums[cur] + " and " + newNum + " diff less than " + t); return true; }
                }
            }
            return false;
        }
    }
    /**
     * Bucket of size t
     */
    public class SolutionV2 {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (nums.length < 2) { return false; }
            if (t < 0) { return false; }
            Map<Long,Long> buckets = new HashMap<>();
            Long[] bucketMemo = new Long[nums.length];
            for (int i = 0, j = 0; j < nums.length; j++) {
                Long numL = (long)nums[j] - Integer.MIN_VALUE;
                // System.out.println("Arranged number is : " + numL);
                Long bucket = numL / ((long)t + 1);
                // System.out.println("Bucket ID is : " + bucket);
                bucketMemo[j] = bucket;
                if (j > k) { buckets.remove(bucketMemo[i++]); }
                // check: bucket-1, this bucket and bucket+1
                if (buckets.get(bucket) != null) { return true; } // this bucket
                Long left = buckets.get(bucket-1);
                if (left != null && (numL - left) <= t) { return true; } // bucket-1
                Long right = buckets.get(bucket+1);
                if (right != null && (right - numL) <= t) { return true; } // bucket+1
                buckets.put(bucket,numL);
            }
            return false;
        }
    }
    /**
     * Use Binary Tree: O(nlogn)
     */
    private static class TreeNode {
        private long val;
        private TreeNode left;
        private TreeNode right;
        private TreeNode(long n) { val = n; }
        public String toString() { return "[" + val + "]"; }
    }
    private static class BinarySearchTree {
        private TreeNode head = null;
        private boolean insert(long n) {
            if (head == null) {
                head = new TreeNode(n);
                return true;
            } else {
                return insertRecur(head,n);
            }
        }
        private static boolean insertRecur(TreeNode head, long n) {
            if (head.val > n) {
                if (head.left == null) {
                    head.left = new TreeNode(n);
                    return true;
                } else {
                    return insertRecur(head.left,n);
                }
            } else if (head.val < n) {
                if (head.right == null) {
                    head.right = new TreeNode(n);
                    return true;
                } else {
                    return insertRecur(head.right,n);
                }
            } else {
                return false;
            }
        }
        private boolean remove(long n) {
            TreeNode result = removeRecur(head,null,false,n);
            if (result == null) { // n not found
                if (head != null && head.val == n) { head = null; } // the only node - head is removed
                return false;
            } else {
                if (head.val == n) { head = result; } // head is removed
                return true;
            }
        }
        /**
         * return the dummy node. return null if the target number doesn't exist
         */
        private static TreeNode removeRecur(TreeNode head, TreeNode pre, boolean isLeft, long n) {
            if (head == null) { return null; }
            if (head.val > n) { // go left
                return removeRecur(head.left, head, true, n);
            } else if (head.val < n) { // go right
                return removeRecur(head.right, head, false, n);
            } else { // find target, now start to remove
                TreeNode dummy = null;
                if (head.left == null && head.right != null) {
                    dummy = head.right;
                } else {
                    dummy = head.left;
                    if (head.right != null) { // grafting the right sub-tree to the left sub-tree
                        TreeNode cur = head.left;
                        while (cur.right != null) {
                            cur = cur.right;
                        }
                        cur.right = head.right;
                    }
                }
                if (pre != null) {
                    if (isLeft) {
                        pre.left = dummy;
                    } else {
                        pre.right = dummy;
                    }
                }
                return dummy;
            }
        }
        public String toString() {
            return inorder(head) + "    [head = " + ((head == null)? "null" : head.val) + "]";
        }
        private String inorder(TreeNode head) {
            if (head == null) { return ""; }
            StringBuilder sb = new StringBuilder();
            sb.append(inorder(head.left));
            sb.append("[" + head.val + "]");
            sb.append(inorder(head.right));
            return sb.toString();
        }
        /**
         * Return least element in this tree greater than or equals to target number
         */
        private TreeNode ceiling(long target) {
            TreeNode ceiling = null;
            TreeNode cur = head;
            while (cur != null) {
                if (cur.val > target) { // go left
                    ceiling = cur;
                    cur = cur.left;
                } else if (cur.val < target) { // go right
                    cur = cur.right;
                } else { // find element equals to target
                    return cur;
                }
            }
            return ceiling;
        }
    }
    public class SolutionV3 {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (nums.length < 2) { return false; }
            if (t < 0) { return false; }
            BinarySearchTree tree = new BinarySearchTree();
            for (int slow = 0, fast = 0; fast < nums.length; fast++) {
                if (fast > k) { tree.remove(nums[slow++]); }
                System.out.println("Slow=" + slow + ", Fast=" + fast + ", Tree= " + tree);
                long floor = (long)nums[fast] - t;
                long ceil = (long)nums[fast] + t;
                TreeNode firstGreaterThanFloor = tree.ceiling(floor);
                if (firstGreaterThanFloor != null && firstGreaterThanFloor.val <= ceil) { return true; }
                tree.insert((long)nums[fast]);
            }
            return false;
        }
    }
    /**
     * Another solution based on Binary Search Tree
     * But use in-build TreeSet Container
     */
    public class SolutionV4 {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (nums.length < 2) { return false; }
            if (t < 0) { return false; }
            TreeSet<Long> tree = new TreeSet<>();
            for (int slow = 0, fast = 0; fast < nums.length; fast++) {
                if (fast > k) { tree.remove((long)nums[slow++]); }
                // System.out.println("Slow=" + slow + ", Fast=" + fast + ", Tree= " + tree);
                long floor = (long)nums[fast] - t;
                long ceil = (long)nums[fast] + t;
                Long firstGreaterThanFloor = tree.ceiling(floor);
                if (firstGreaterThanFloor != null && firstGreaterThanFloor <= ceil) { return true; }
                tree.add((long)nums[fast]);
            }
            return false;
        }
    }
    private static class BinarySearchTreeWithDummy {
        private TreeNode dummy = new TreeNode(0); // sentinel

        private boolean add(long n) {
            TreeNode pre = dummy, cur = dummy.right; // head is always the right child of dummy
            boolean fromLeft = false;
            while (cur != null) {
                long val = cur.val;
                if (val > n) { // go left
                    pre = cur; cur = cur.left; fromLeft = true;
                } else if (val < n) { // go right
                    pre = cur; cur = cur.right;
                } else { // value already exist
                    return false;
                }
            }
            TreeNode newNode = new TreeNode(n);
            if (fromLeft) {
                pre.left = newNode;
            } else {
                pre.right = newNode;
            }
            return true;
        }
        private boolean remove(long n) {
            TreeNode pre = dummy, cur = dummy.right;
            boolean fromLeft = false;
            while (cur != null) {
                if (cur.val > n) { // go left
                    pre = cur; cur = cur.left; fromLeft = true;
                } else if (cur.val < n) { // go right
                    pre = cur; cur = cur.right;
                } else { // find target
                    TreeNode tempHead = new TreeNode(0);
                    if (cur.left != null) {
                        tempHead.left = cur.left;
                        tempHead.right = cur.right;
                        cur = cur.left;
                        while (cur.right != null) { cur = cur.right; }
                        cur.right = tempHead.right;
                    } else {
                        tempHead.left = cur.right;
                    }
                    if (fromLeft) {
                        pre.left = tempHead.left;
                    } else {
                        pre.right = tempHead.left;
                    }
                    return true;
                }
            }
            return false;
        }
        /**
         * Return least element greater than the input n
         * Return null if there is no element greater than n
         */
        private Long ceiling(long n) {
            TreeNode lastGreater = null;
            TreeNode cur = dummy.right;
            while (cur != null) {
                if (cur.val > n) { // go left
                    lastGreater = cur; cur = cur.left;
                } else if (cur.val < n) {
                    cur = cur.right;
                } else { // find target
                    return new Long(cur.val);
                }
            }
            return (lastGreater == null)? null : new Long(lastGreater.val);
        }
        public String toString() {
            return inorder(dummy.right);
        }
        private String inorder(TreeNode head) {
            if (head == null) { return ""; }
            StringBuilder sb = new StringBuilder();
            sb.append(head.left);
            sb.append(head.toString());
            sb.append(head.right);
            return sb.toString();
        }
    }
    /**
     * Another solution based on Binary Search Tree
     * Use my BinarySearchTreeWithDummy
     */
    public class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (nums.length < 2) { return false; }
            if (t < 0) { return false; }
            BinarySearchTreeWithDummy tree = new BinarySearchTreeWithDummy();
            for (int slow = 0, fast = 0; fast < nums.length; fast++) {
                if (fast > k) { tree.remove((long)nums[slow++]); }
                // System.out.println("Slow=" + slow + ", Fast=" + fast + ", Tree= " + tree);
                long floor = (long)nums[fast] - t;
                long ceil = (long)nums[fast] + t;
                Long firstGreaterThanFloor = tree.ceiling(floor);
                if (firstGreaterThanFloor != null && firstGreaterThanFloor <= ceil) { return true; }
                tree.add((long)nums[fast]);
            }
            return false;
        }
    }
    private static ContainsDuplicateThree test = new ContainsDuplicateThree();
    private static Solution solution = test.new Solution();
    private static void callContainsNearbyAlmostDuplicate(int[] nums, int k, int t, String answer) {
        System.out.println(Arrays.toString(nums) + " contains difference of " + t + " in window of " + k + "? " + solution.containsNearbyAlmostDuplicate(nums,k,t) + "    (answer = " + answer + ")");
    }
    private static void test() {
        int[] nums1 = new int[]{1,1,1,1};
        int[] nums2 = new int[]{1,2,3,4};
        int[] nums3 = new int[]{5,10,15,20,25,30,55,40,45,34};
        int[] nums4 = new int[]{-1,2147483647};
        int[] nums5 = new int[]{-1,-1};
        int[] nums6 = new int[]{1,2};
        int[] nums7 = new int[]{0,2147483647};
        callContainsNearbyAlmostDuplicate(nums1,2,1,"true");
        callContainsNearbyAlmostDuplicate(nums2,2,2,"true");
        callContainsNearbyAlmostDuplicate(nums3,3,4,"false");
        callContainsNearbyAlmostDuplicate(nums3,4,4,"true");
        callContainsNearbyAlmostDuplicate(nums4,1,2147483647,"false");
        callContainsNearbyAlmostDuplicate(nums5,1,-1,"false");
        callContainsNearbyAlmostDuplicate(nums6,0,1,"false");
        callContainsNearbyAlmostDuplicate(nums7,1,2147483647,"true");
    }
    private static void testBinarySearchTree() {
        Random r = new Random();
        int max = 100, half = 50;
        BinarySearchTree tree = new BinarySearchTree();
        long first = 0;
        long third = 0;
        for (int i = 0 ; i < 10; i++) {
            long num = r.nextInt(100) + 1;
            if (i == 0) { first = num; }
            if (i == 2) { third = num; }
            tree.insert(num);
        }
        System.out.println(tree);
        TreeNode ceiling = tree.ceiling(half);
        if (ceiling != null) {
            System.out.println("First element greater than " + half + " is: " + tree.ceiling(half));
        } else {
            System.out.println("No element greater than or equals to " + half);
        }
        tree.remove(third);
        System.out.println("After remove the third element " + third + ", Tree becomes: ");
        System.out.println(tree);
    }
    public static void main(String[] args) {
        test();
        // testBinarySearchTree();
    }
}
