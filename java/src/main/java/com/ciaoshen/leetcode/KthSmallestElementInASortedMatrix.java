/**
 * Leetcode - Algorithm - KthSmallestElementInASortedMatrix
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class KthSmallestElementInASortedMatrix implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private KthSmallestElementInASortedMatrix() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int kthSmallest(int[][] matrix, int k); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        /** list of pointer */
        public int kthSmallest(int[][] matrix, int k) {
            if (matrix.length == 0) { return 0; }
            if (matrix.length == 1) { return (k == 1)? matrix[0][0] : 0; }
            int remain = k - 1;
            Pair min = new Pair(0,0);
            int minVal = matrix[0][0];
            List<Pair> list = new LinkedList<>();
            list.add(new Pair(0,1));
            list.add(new Pair(1,0));
            while (!list.isEmpty()) {
                System.out.println("Remain " + remain + " element to go! Current min value = " + minVal);
                if (remain == 0) { return minVal; }
                min = null;
                minVal = Integer.MAX_VALUE;
                for (Pair p : list) {
                    if (matrix[p.x][p.y] <= minVal) {
                        min = p;
                        minVal = matrix[p.x][p.y];
                        System.out.println("Find next min: [" + min.x + "," + min.y + "] -> " + minVal);
                    }
                }
                --remain;
                if (min.y < (matrix.length - 1)) {
                    ++min.y;
                } else {
                    list.remove(min);
                }
                if (!list.isEmpty()) {
                    Pair last = list.get(list.size()-1);
                    if ((last.y == 1) && (last.x < (matrix.length - 1))) {
                        list.add(new Pair(min.x+1,0));
                        System.out.println("Add new element [" + (min.x + 1) + "," + 0 + "] -> " + matrix[min.x+1][0]);
                    }
                }
            }
            return (remain == 0)? minVal : 0;
        }

        private class Pair {
            int x;
            int y;
            private Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int kthSmallest(int[][] matrix, int k) {
            int size = matrix.length;
            int lo = matrix[0][0];
            int hi = matrix[size-1][size-1];
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                int count = 0;
                int cur = size - 1;
                for (int i = 0; i < size; i++) { // 统计 <= mid 的数字个数
                    while (cur >= 0 && matrix[i][cur] > mid) { cur--; }
                    count += (cur + 1);
                }
                if (count < k) { // mid 太小
                    lo = mid + 1;
                } else { // mid 有可能太大，有可能正好
                    // 当 count > k 时，不能直接判断 mid 太大， 因为存在重复的数字
                    hi = mid;
                }
            }
            return lo;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int kthSmallest(int[][] matrix, int k) {
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
        private KthSmallestElementInASortedMatrix problem = new KthSmallestElementInASortedMatrix();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] nums, int k, int ans) {
            Matrix.print(nums);
            System.out.println(k + "th smallest element = " + solution.kthSmallest(nums,k) + "\t[answer: " + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] nums1 = new int[][] {
                {1,5,9},
                {10,11,13},
                {12,13,15}
            };
            int k1 = 8;
            int ans1 = 13;

            int[][] nums2 = new int[][] {
                {1,2},
                {1,3}
            };
            int k2 = 1;
            int ans2 = 1;

            int[][] nums3 = new int[][] {
                {1,2},
                {1,3}
            };
            int k3 = 4;
            int ans3 = 3;

            int[][] nums4 = new int[][] {
                {1,1,3,8,13},
                {4,4,4,8,18},
                {9,14,18,19,20},
                {14,19,23,25,25},
                {18,21,26,28,29}
            };
            int k4 = 13;
            int ans4 = 18;

            /** involk call() method HERE */
            call(nums1, k1, ans1);
            call(nums2, k2, ans2);
            call(nums3, k3, ans3);
            call(nums4, k4, ans4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
