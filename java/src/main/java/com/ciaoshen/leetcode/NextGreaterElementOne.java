/**
 * Leetcode - Algorithm - NextGreaterElementOne
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class NextGreaterElementOne implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private NextGreaterElementOne() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[] nextGreaterElement(int[] nums1, int[] nums2); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int len1 = nums1.length, len2 = nums2.length;
            int[] res = new int[len1];
            for (int i = 0; i < len1; i++) {
                int lastGreater = -1;
                int n1 = nums1[i];
                for (int j = len2 - 1; j >= 0; j--) {
                    int n2 = nums2[j];
                    if (n2 > n1) {
                        lastGreater = n2;
                    } else if (n2 == n1) {
                        res[i] = lastGreater;
                        break;
                    }
                }
            }
            return res;
        }

    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            if (nums1.length == 0) { return res; }
            Map<Integer,Integer> map = new HashMap<>();
            Stack<Integer> stack = new Stack<>();
            stack.push(nums2[0]);
            for (int i = 1; i < nums2.length; i++) {
                if (nums2[i] > nums2[i-1]) {
                    while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                        map.put(stack.pop(),nums2[i]);
                    }
                }
                stack.push(nums2[i]);
            }
            Integer lastGreater = null;
            for (int i = 0; i < nums1.length; i++) {
                lastGreater = map.get(nums1[i]);
                res[i] = (lastGreater == null)? -1 : lastGreater;
            }
            return res;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            return new int[1];
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
        private NextGreaterElementOne problem = new NextGreaterElementOne();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums1, int[] nums2, int[] ans) {
            System.out.println("Array 1: " + Arrays.toString(nums1));
            System.out.println("Array 2: " + Arrays.toString(nums2));
            System.out.println("Result: " + Arrays.toString(solution.nextGreaterElement(nums1,nums2)));
            System.out.println("Answer should be: " + Arrays.toString(ans) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums11 = new int[]{4,1,2};
            int[] nums12 = new int[]{1,3,4,2};
            int[] ans1 = new int[]{-1,3,-1};

            int[] nums21 = new int[]{2,4};
            int[] nums22 = new int[]{1,2,3,4};
            int[] ans2 = new int[]{3,-1};

            int[] nums31 = new int[]{2,1,3};
            int[] nums32 = new int[]{2,3,1};
            int[] ans3 = new int[]{3,-1,-1};

            /** involk call() method HERE */
            call(nums11,nums12,ans1);
            call(nums21,nums22,ans2);
            call(nums31,nums32,ans3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
