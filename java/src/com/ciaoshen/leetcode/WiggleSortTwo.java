/**
 * Leetcode - Algorithm - WiggleSortTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class WiggleSortTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private WiggleSortTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void wiggleSort(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public void wiggleSort(int[] nums) {
            int[] copy = Arrays.copyOf(nums,nums.length);
            Arrays.sort(copy);
            int mid = (nums.length - 1) / 2;
            for (int i = mid, j = 0; i >= 0; i--,j+=2) {
                nums[j] = copy[i];
            }
            for (int i = nums.length-1, j = 1; i > mid; i--, j+=2) {
                nums[j] = copy[i];
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        private int[] copy = new int[0];
        public void wiggleSort(int[] nums) {
            local(nums);
            int midIndex = (nums.length - 1) / 2;
            System.out.println("Mid Index = " + midIndex);
            int mid = kthLargest(midIndex);
            System.out.println("Mid Number = " + mid);
            System.out.println("Array after Three-Way-Partition: " + Arrays.toString(copy));
            int even = 0;
            for (int i = midIndex; i >= 0; i--) {
                nums[even] = copy[i];
                even += 2;
            }
            int odd = ((nums.length - 1) % 2 == 0)? nums.length - 2 : nums.length - 1;
            for (int i = midIndex + 1; i < copy.length; i++) {
                nums[odd] = copy[i];
                odd -= 2;
            }
        }
        private void local(int[] nums) {
            copy = Arrays.copyOf(nums,nums.length);
        }
        private int kthLargest(int k) {
            int[] pivot = new int[]{-1,-1};
            int lo = 0, hi = copy.length-1;
            while (true) {
                pivot = threeWayPartition(lo,hi);
                if (pivot[1] < k) {
                    lo = pivot[1] + 1;
                } else if (pivot[0] > k) {
                    hi = pivot[0] - 1;
                } else {
                    return copy[pivot[0]];
                }
            }
        }
        private int[] threeWayPartition(int lo, int hi) {
            int pivot = copy[hi];
            exch(lo,hi);
            for (int i = lo + 1; i <= hi; ) {
                int num = copy[i];
                if (num < pivot) {
                    exch(lo++,i++);
                } else if (num > pivot){
                    exch(hi--,i);
                } else {
                    i++;
                }
            }
            int[] res = new int[2];
            res[0] = lo;
            res[1] = hi;
            return res;
        }
        private void exch(int x, int y) {
            int temp = copy[x];
            copy[x] = copy[y];
            copy[y] = temp;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public void wiggleSort(int[] nums) {
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
        private WiggleSortTwo problem = new WiggleSortTwo();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums) {
            System.out.println("Before Sort: " + Arrays.toString(nums));
            solution.wiggleSort(nums);
            System.out.println("After Sort: " + Arrays.toString(nums) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);


            int[] nums1 = new int[]{1, 5, 1, 1, 6, 4};
            int[] nums2 = new int[]{1, 3, 2, 2, 3, 1};
            int[] nums3 = new int[]{1,5,1,1,1,1,4,7,2};
            int[] nums4 = new int[]{4,5,5,6};

            /** involk call() method HERE */
            call(nums1);
            call(nums2);
            call(nums3);
            call(nums4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
