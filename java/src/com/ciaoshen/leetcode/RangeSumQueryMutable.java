/**
 * Leetcode - Algorithm - RangeSumQueryMutable
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class RangeSumQueryMutable implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private RangeSumQueryMutable() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void numArray(int[] nums);      // 主方法接口
        abstract public void update(int i, int val);    // 主方法接口
        abstract public int sumRange(int i, int j);     // 主方法接口
        abstract public void print();                   // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /**
     * 预先计算一个和
     * 主要简化sumRange()函数，复杂度O(1)
     * 而update()函数复杂度O(n)提高了
     * 适合调用sumRange()远多于update()的情况
     */
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[] orig = new int[0];
        private int[] sum = new int[0];

        public void numArray(int[] nums) {
            orig = new int[nums.length];
            sum = new int[nums.length];
            if (nums.length == 0) { return; }
            orig[0] = nums[0]; sum[0] = orig[0];
            for (int i = 1; i < nums.length; i++) {
                orig[i] = nums[i];
                sum[i] = sum[i-1] + nums[i];
            }
        }

        public void update(int i, int val) {
            int diff = val - orig[i];
            orig[i] = val;
            for (int j = i; j < sum.length; j++) {
                sum[j] += diff;
            }
        }

        public int sumRange(int i, int j) {
            return (i == 0)? sum[j] : sum[j] - sum[i-1];
        }

        public void print() {
            System.out.println("Original nums: " + Arrays.toString(orig));
            System.out.println("Sum: " + Arrays.toString(sum));
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private int[] numbers = new int[0];
        private int[] diff = new int[0];
        private int[] sum = new int[0];

        public void numArray(int[] nums) {
            numbers = new int[nums.length];
            diff = new int[nums.length];
            sum = new int[nums.length];
            if (nums.length == 0) { return; }
            numbers[0] = nums[0];
            sum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                numbers[i] = nums[i];
                sum[i] = sum[i-1] + nums[i];
            }
        }

        public void update(int i, int val) {
            int d = val - numbers[i];
            numbers[i] = val;
            diff[i] += d;
        }

        public int sumRange(int i, int j) {
            int res = (i == 0)? sum[j] : sum[j] - sum[i-1];
            for (int k = i; k <= j; k++) {
                res += diff[k];
            }
            return res;
        }

        public void print() {
            System.out.println("===========================================");
            System.out.println("Numbers: " + Arrays.toString(numbers));
            System.out.println("Diff: " + Arrays.toString(diff));
            System.out.println("Sum: " + Arrays.toString(sum));
            System.out.println("===========================================");
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void numArray(int[] nums) {
            localNums = nums;
            root = creatNode(0,nums.length-1);
        }

        public void update(int i, int val) {
            int diff = val - localNums[i];
            localNums[i] = val;
            System.out.println("nums[" + i + "]=" + localNums[i] + " -> " + val + "\t[diff=" + diff +"]");
            updateHelper(root,i,diff);
        }

        public int sumRange(int i, int j) {
            sum = 0;
            sumRangeHelper(root,i,j);
            return sum;
        }

        public void print() {
            System.out.println("===========================================");
            System.out.println("Segment Tree: " + root);
            System.out.println("===========================================");
        }


        private SegmentTreeNode root;
        private int[] localNums;

        private SegmentTreeNode creatNode(int start, int end) {
            if (start > end) { return null; }
            SegmentTreeNode root = new SegmentTreeNode(0);
            root.start = start;
            root.end = end;
            if (start == end) {
                root.val = localNums[start];
            } else {
                int mid = start + (end - start) / 2;
                root.left = creatNode(start,mid);
                root.right = creatNode(mid+1,end);
                root.val = root.left.val + root.right.val;
            }
            return root;
        }

        private void updateHelper(SegmentTreeNode root, int i, int diff) {
            if (root == null) { return; }
            if (root.start <= i && root.end >= i) {
                root.val += diff;
                updateHelper(root.left,i,diff);
                updateHelper(root.right,i,diff);
            }
        }

        //原理就是尽量使用更大块的计算好的区域元素和，而不是一个个元素去求和
        private int sum;
        private void sumRangeHelper(SegmentTreeNode root, int start, int end) {
            if (root == null) { return; }
            if (start <= root.start && end >= root.end) { // 此节点覆盖的域是求和范围的子集（没必要再往下递归，直接加上这一整块的和）
                sum += root.val;
            } else if (start <= root.end && end >= root.start){ // 我们只需要节点覆盖范围的一部分和，节点累加和不能直接用，需要向下递归到更精细的分区
                sumRangeHelper(root.left,start,end);
                sumRangeHelper(root.right,start,end);
            } // else { 剩下的完全没有交集的，也不需要递归下去 }
        }
    }
    // you can expand more solutions HERE if you want...
    private class Solution4 extends Solution {
        { super.id = 4; }

        public void numArray(int[] nums) {
            root = creatNode(0,nums.length-1,nums);
        }

        public void update(int i, int val) {
            updateHelper(root,i,val);
        }

        public int sumRange(int i, int j) {
            sum = 0;
            sumRangeHelper(root,i,j);
            return sum;
        }

        public void print() {
            System.out.println("===========================================");
            System.out.println("Segment Tree: " + root);
            System.out.println("===========================================");
        }


        private SegmentTreeNode root;

        private SegmentTreeNode creatNode(int start, int end, int[] nums) {
            if (start > end) { return null; }
            SegmentTreeNode root = new SegmentTreeNode(0);
            root.start = start;
            root.end = end;
            if (start == end) {
                root.val = nums[start];
            } else {
                int mid = start + (end - start) / 2;
                root.left = creatNode(start,mid,nums);
                root.right = creatNode(mid+1,end,nums);
                root.val = root.left.val + root.right.val;
            }
            return root;
        }

        private Integer updateHelper(SegmentTreeNode root, int i, int val) {
            Integer diff = null;
            if (root.start == i && root.end == i) {
                diff = val - root.val;
                root.val = val;
                return diff;
            }
            if (root.start <= i && root.end >= i) {
                if ((diff = updateHelper(root.left,i,val)) != null) {
                    root.val += diff;
                    return diff;
                };
                if ((diff = updateHelper(root.right,i,val)) != null) {
                    root.val += diff;
                    return diff;
                }
            }
            return diff;
        }

        //原理就是尽量使用更大块的计算好的区域元素和，而不是一个个元素去求和
        private int sum;
        private void sumRangeHelper(SegmentTreeNode root, int start, int end) {
            if (root == null) { return; }
            if (start <= root.start && end >= root.end) { // 此节点覆盖的域是求和范围的子集（没必要再往下递归，直接加上这一整块的和）
                sum += root.val;
            } else if (start <= root.end && end >= root.start){ // 我们只需要节点覆盖范围的一部分和，节点累加和不能直接用，需要向下递归到更精细的分区
                sumRangeHelper(root.left,start,end);
                sumRangeHelper(root.right,start,end);
            } // else { 剩下的完全没有交集的，也不需要递归下去 }
        }
    }


    private class Solution5 extends Solution {
        { super.id = 5; }

        private int[] data;
        private int[] bit;

        public void numArray(int[] nums) {
            data = new int[nums.length];
            bit = new int[nums.length+2];
            for (int i = 0; i < data.length; i++) {
                update(i,nums[i]);
            }
        }

        public void update(int i, int val) {
            int diff = val - data[i];
            data[i] = val;
            i++;
            while (i < bit.length) {
                bit[i] += diff;
                i += (i & -i); // double last 1-bit
            }
        }

        public int sumRange(int i, int j) {
            return sum(j) - sum(i-1);
        }

        private int sum(int end) {
            int sum = 0;
            int i = end+1;
            while (i > 0) {
                sum += bit[i];
                i -= (i & -i); // remove last 1-bit
            }
            return sum;
        }

        public void print() {
            System.out.println("===========================================");
            System.out.println("Data: " + Arrays.toString(data));
            System.out.println("Bit: " + Arrays.toString(bit));
            System.out.println("===========================================");
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
        private RangeSumQueryMutable problem = new RangeSumQueryMutable();
        private Solution solution = null;

        // call method in solution
        private final int MAX = 100;
        private void call(int[] nums) {
            solution.numArray(nums);
            solution.print();
            Random r = new Random();
            int i = r.nextInt(nums.length);
            int j = r.nextInt(nums.length - i) + i;
            System.out.println(">>> \t Range Sum [" + i + "," + j + "] = " + solution.sumRange(i,j));
            int updatePos = r.nextInt(nums.length);
            int newVal = r.nextInt(MAX);
            System.out.println(">>> \t Update nums[" + updatePos + "]=" + nums[updatePos] + " to " + newVal);
            solution.update(updatePos,newVal);
            solution.print();
            System.out.println(">>> \t New Range Sum [" + i + "," + j + "] = " + solution.sumRange(i,j));
        }
        private void specialCall1() {
            int[] specialNums = new int[]{7,2,7,2,0};
            int x,y = 0;
            solution.numArray(specialNums);
            solution.print();
            solution.update(4,6);
            solution.print();
            solution.update(0,2);
            solution.print();
            solution.update(0,9);
            solution.print();
            callSumRange(4,4);
            solution.update(3,8);
            solution.print();
            callSumRange(0,4);
            solution.update(4,1);
            solution.print();
            callSumRange(0,3);
            callSumRange(0,4);
            solution.update(0,4);
            solution.print();
        }
        private void callSumRange(int x, int y) {
            System.out.println("sum[" + x + "," + y + "] = " + solution.sumRange(x,y));
        }
        // public API of Test interface
        public void test(int id) {
            /* choose a solution */
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1,2,3,4,5,6,7,8,9,10};
            int[] nums2 = new int[]{1,3,5};

            /** involk call() method HERE */
            // call(nums1);
            // call(nums2);
            // call(nums3);
            specialCall1();
        }

    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.test(4);
        test.test(5);
    }
}
