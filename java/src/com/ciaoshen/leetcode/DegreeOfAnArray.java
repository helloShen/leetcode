/**
 * Leetcode - Algorithm - DegreeOfAnArray
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DegreeOfAnArray implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DegreeOfAnArray() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int findShortestSubArray(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        /** one passe */
        public int findShortestSubArray(int[] nums) {
            Map<Integer,Integer> freq = new HashMap<>();
            Map<Integer,Integer> firstIndexOf = new HashMap<>();
            Map<Integer,Integer> lastIndexOf = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                Integer n = nums[i];
                if (freq.containsKey(n)) {
                    freq.put(n,freq.get(n) + 1);
                    lastIndexOf.put(n,i);
                } else {
                    freq.put(n,1);
                    firstIndexOf.put(n,i);
                    lastIndexOf.put(n,i);
                }
            }
            int maxFreq = 0;
            List<Integer> numsWithMaxFreq = new ArrayList<>();
            for (Map.Entry<Integer,Integer> entry : freq.entrySet()) {
                int f = entry.getValue();
                if (f > maxFreq) {
                    maxFreq = f;
                    numsWithMaxFreq.clear();
                    numsWithMaxFreq.add(entry.getKey());
                } else if (f == maxFreq) {
                    numsWithMaxFreq.add(entry.getKey());
                }
            }
            int minLen = Integer.MAX_VALUE;
            for (Integer n : numsWithMaxFreq) {
                minLen = Math.min(minLen, (lastIndexOf.get(n) - firstIndexOf.get(n) + 1));
            }
            return minLen;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int findShortestSubArray(int[] nums) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int findShortestSubArray(int[] nums) {
            return 3;
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
        private DegreeOfAnArray problem = new DegreeOfAnArray();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums, int ans) {
            System.out.println(Arrays.toString(nums));
            System.out.println("Shortest Sub Array = " + solution.findShortestSubArray(nums));
            System.out.println("Answer should be: " + ans + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1, 2, 2, 3, 1};
            int ans1 = 2;

            int[] nums2 = new int[]{1,2,2,3,1,4,2};
            int ans2 = 6;

            int[] nums3 = new int[]{1};
            int ans3 = 1;

            /** involk call() method HERE */
            call(nums1,ans1);
            call(nums2,ans2);
            call(nums3,ans3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
