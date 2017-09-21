/**
 * Leetcode - Algorithm - QuickSort
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class QuickSort implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private QuickSort() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void sort(int[] nums, int lo, int hi); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* Three-way partition最朴素的解法。
     * 确保：
     *    1. slow指针： 指向第一个等于pivot的数（实际标出"smaller space"和"equal space"的边界）
     *    2. fast指针： 指向第一个大于pivot的数（实际标出"equal space"和"larger space"的边界）
     *
     * 所以，首先用一个while()循环把fast指针放到正确的位置。
     */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public void sort(int[] nums, int lo, int hi) {
            if (hi - lo < 1) { return; }
            int pivot = nums[hi];
            nums[hi] = nums[lo];
            nums[lo] = pivot;
            int slow = lo;              // first element equals to pivot
            int fast = slow + 1;        // first element larger than pivot
            while (fast <= hi) { // put fast to the first element larger than pivot
                if (nums[fast] > pivot) {
                    break;
                } else if (nums[fast] < pivot) {
                    nums[slow++] = nums[fast];
                    nums[fast++] = pivot;
                } else { // nums[fast] == pivot
                    ++fast;
                }
            }
            // if (slow <= hi) { System.out.println("Slow = " + nums[slow]); }
            // if (fast <= hi) { System.out.println("Fast = " + nums[fast]); }
            int cur = fast + 1;
            while (cur <= hi) {
                if (nums[cur] > pivot) {
                    ++cur;
                } else if (nums[cur] < pivot) {
                    nums[slow++] = nums[cur];
                    nums[cur++] = nums[fast];
                    nums[fast++] = pivot;
                } else {
                    nums[cur++] = nums[fast];
                    nums[fast++] = pivot;
                }
            }
            sort(nums,lo,slow-1);
            sort(nums,fast,hi);
        }
    }

    /* Three-way partition稍好一些的解法。
     * 确保：
     *    1. slow指针： 指向第一个等于pivot的数（实际标出"smaller space"和"equal space"的边界）
     *    2. fast指针： 指向第一个大于pivot的数（实际标出"equal space"和"larger space"的边界）
     *
     * 这次允许fast指针在"larger space"为空的时候，指向"equal space"最后一个元素。
     */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public void sort(int[] nums, int lo, int hi) {
            if (hi - lo < 1) { return; }
            int pivot = nums[hi];
            exchange(nums,lo,hi);
            int slow = lo;          // first element equals to pivot (always exist)
            int fast = lo;          // first element larger than pivot (if absent, then point to the last pivot)
            int cur = fast + 1;
            while (cur <= hi) {
                int n = nums[cur];
                if (n < pivot) { //
                    exchange(nums,slow++,cur);
                    if (fast < slow) { fast = slow; } // maintain the faster point if "larger space" is empty
                } else if (n > pivot) {
                    if (nums[fast] == pivot) { fast++; } // maintain the faster point if "larger space" is empty
                } else {
                    exchange(nums,fast++,cur);
                }
                ++cur;
            }
            if (nums[fast] == pivot) { ++fast; } // if "larger space" is empty, kill the faster pointer
            sort(nums,lo,slow-1);
            sort(nums,fast,hi);
        }
        /* 假设x,y都是合法的下标 */
        private void exchange(int[] nums, int x, int y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }
    }
    /* Djkstra的Three-way partition版本 */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public void sort(int[] nums, int lo, int hi) {
            if (hi - lo < 1) { return; }
            int pivot = nums[hi];
            exchange(nums,lo,hi);
            int lt = lo; // [lo,lt-1]空间中的元素，都小于pivot （换句话说，lt指向第一个等于pivot的元素）
            int gt = hi; // [gt+1,hi]空间中的元素，都大于pivot （换句话说，gt指向最后一个等于pivot的元素）
            int cur = lt + 1;
            while (cur <= gt) {
                int n = nums[cur];
                if (n < pivot) {
                    exchange(nums,lt++,cur++);
                } else if (n > pivot) {
                    exchange(nums,gt--,cur);
                } else {
                    ++cur;
                }
            }
            sort(nums,lo,lt-1);
            sort(nums,gt+1,hi);
        }
        /* 假设x,y都是合法的下标 */
        private void exchange(int[] nums, int x, int y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }
    }
    // you can expand more solutions HERE if you want...


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
        private QuickSort problem = new QuickSort();
        private Solution solution = null;

        private void call(int[] nums) {
            System.out.println("Original array: " + Arrays.toString(nums));
            solution.sort(nums,0,nums.length-1);
            System.out.println("Sorted array: " + Arrays.toString(nums));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int[] nums1 = new int[]{};
            int[] nums2 = new int[]{1,4,3,2};
            int[] nums3 = new int[]{1,100,2,101};
            int[] nums4 = new int[]{3,4,6,8,7,6,2,5,3,4,6};

            call(nums1);
            call(nums2);
            call(nums3);
            call(nums4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
