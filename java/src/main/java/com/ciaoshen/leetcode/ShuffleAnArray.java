/**
 * Leetcode - Algorithm - ShuffleAnArray
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ShuffleAnArray implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ShuffleAnArray(int[] nums) {
        register(new Solution1(nums));
        register(new Solution2(nums));
        register(new Solution3(nums));
        register(new Solution4(nums));
    }
    private abstract class Solution {
        private int id = 0;
        protected int len = 0;
        protected int[] nums;
        protected int[] shuffle;
        protected Random r = new Random();
        public Solution(int[] nums) {
            this.nums = nums;
            len = nums.length;
            shuffle = new int[len];
        } // 构造函数
        abstract public int[] reset(); // 主方法接口
        abstract public int[] shuffle(); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        public Solution1(int[] nums) { super(nums); }

        // implement your solution's method HERE...
        public int[] reset() {
            partition(0,nums.length-1);
            return nums;
        }
        private void partition(int lo, int hi) {
            if (hi - lo < 1) { return; }
            int pivot = nums[hi];
            int l = lo, r = hi;
            exch(l,r);
            int cur = l + 1;
            while (cur <= r) {
                int n = nums[cur];
                if (n < pivot) {
                    exch(l++,cur++);
                } else if (n > pivot) {
                    exch(cur,r--);
                } else {
                    cur++;
                }
            }
            partition(lo,l-1);
            partition(r+1,hi);
        }
        public int[] shuffle() {
            if (nums.length < 2) { return nums; }
            for (int i = nums.length - 1; i > 0; i--) {
                int pos = r.nextInt(i+1);
                exch(pos,i);
            }
            return nums;
        }
        private void exch(int lo, int hi) {
            int temp = nums[hi];
            nums[hi] = nums[lo];
            nums[lo] = temp;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        public Solution2(int[] nums) { super(nums); }

        // implement your solution's method HERE...
        public int[] reset() {
            if (nums.length == 0) { return nums; }
            return mergeSort(0,nums.length-1);
        }
        private int[] mergeSort(int lo, int hi) {
            if (hi == lo) { return new int[]{nums[lo]}; }
            int mid = lo + (hi - lo) / 2;
            int[] l = mergeSort(lo,mid);
            int[] r = mergeSort(mid+1,hi);
            return merge(l,r);
        }
        private int[] merge(int[] a, int[] b) {
            int[] res = new int[a.length + b.length];
            int pa = 0, pb = 0, pr = 0;
            while (pa < a.length && pb < b.length) {
                int na = a[pa], nb = b[pb];
                if (na <= nb) {
                    res[pr++] = na; pa++;
                } else {
                    res[pr++] = nb; pb++;
                }
            }
            while (pa < a.length) { res[pr++] = a[pa++]; }
            while (pb < b.length) { res[pr++] = b[pb++]; }
            return res;
        }
        public int[] shuffle() {
            return nums;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        public Solution3(int[] nums) { super(nums); }

        // implement your solution's method HERE...
        public int[] reset() {
            return nums;
        }
        public int[] shuffle() {
            if (len < 2) { return nums; }
            for (int i = 0; i < len; i++) { shuffle[i] = nums[i]; } // copy nums to shuffle
            for (int i = len - 1; i > 0; i--) {
                int pos = r.nextInt(i+1);
                exch(pos,i);
            }
            return shuffle;
        }
        private void exch(int lo, int hi) {
            int temp = shuffle[hi];
            shuffle[hi] = shuffle[lo];
            shuffle[lo] = temp;
        }
    }
    // you can expand more solutions HERE if you want...
    private class Solution4 extends Solution {
        { super.id = 4; }
        public Solution4(int[] nums) { super(nums); }

        // implement your solution's method HERE...
        public int[] reset() {
            return nums;
        }
        public int[] shuffle() {
            if (len < 2) { return nums; }
            int[] shuffle = Arrays.copyOf(nums,len);
            int temp = 0, pos = 0;
            for (int i = len - 1; i > 0; i--) {
                pos = r.nextInt(i+1);
                temp = shuffle[pos];
                shuffle[pos] = shuffle[i];
                shuffle[i] = temp;
            }
            return shuffle;
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
        private ShuffleAnArray problem = null;
        private Solution solution = null;
        private Test(int[] nums) {
            problem = new ShuffleAnArray(nums);
        }

        // call method in solution
        private void call() {
            System.out.println("Original Array:" + Arrays.toString(solution.nums));
            int[] sortedNums = solution.reset();
            System.out.println("Sorted Array:" + Arrays.toString(sortedNums));
            int[] shuffledNums = solution.shuffle();
            System.out.println("Shuffle Array:" + Arrays.toString(shuffledNums));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);
            call();
        }
    }
    public static void main(String[] args) {
        // test cases
        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{1};
        int[] nums3 = new int[]{4,7,3,2,3,9,8,2,9};
        // tests
        Test test1 = new Test(nums1);
            // test1.test(1); // solution 1
            // test1.test(2); // solution 2
            // test1.test(3); // solution 3
            test1.test(4); // solution 4
        Test test2 = new Test(nums2);
            // test2.test(1);
            // test2.test(2);
            // test2.test(3);
            test2.test(4);
        Test test3 = new Test(nums3);
            // test3.test(1);
            // test3.test(2);
            // test3.test(3);
            test3.test(4);
    }
}
