/**
 * Leetcode - Algorithm - IntersectionOfTwoArraysTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class IntersectionOfTwoArraysTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private IntersectionOfTwoArraysTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[] intersect(int[] nums1, int[] nums2); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int[] intersect(int[] nums1, int[] nums2) {
            Map<Integer,Integer> map = new HashMap<>();
            for (Integer n : nums1) {
                Integer freq = map.get(n);
                map.put(n,(freq == null)? 1 : freq+1);
            }
            int[] inter = new int[Math.min(nums1.length,nums2.length)];
            int cur = 0;
            for (Integer n : nums2) {
                Integer freq = map.get(n);
                if (freq != null) {
                    inter[cur++] = n;
                    if (freq == 1) {
                        map.remove(n);
                    } else {
                        map.put(n,freq-1);
                    }
                }
            }
            return Arrays.copyOfRange(inter,0,cur);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int[] intersect(int[] nums1, int[] nums2) {
            return null;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int[] intersect(int[] nums1, int[] nums2) {
            return null;
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
        private IntersectionOfTwoArraysTwo problem = new IntersectionOfTwoArraysTwo();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums1, int[] nums2) {
            System.out.println("Array 1: " + Arrays.toString(nums1));
            System.out.println("Array 2: " + Arrays.toString(nums2));
            System.out.println("Intersection: " + Arrays.toString(solution.intersect(nums1,nums2)));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums11 = new int[]{1,2,2,1};
            int[] nums12 = new int[]{2,2};

            // int[] nums21 = new int[]{};
            // int[] nums22 = new int[]{};
            //
            // int[] nums31 = new int[]{};
            // int[] nums32 = new int[]{};

            /** involk call() method HERE */
            call(nums11,nums12);
            // call(nums21,nums22);
            // call(nums31,nums32);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
