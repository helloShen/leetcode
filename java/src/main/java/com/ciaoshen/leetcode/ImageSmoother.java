/**
 * Leetcode - Algorithm - ImageSmoother
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ImageSmoother implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ImageSmoother() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[][] imageSmoother(int[][] M); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[][] local = null;
        private int height = 0, width = 0;

        public int[][] imageSmoother(int[][] M) {
            height = M.length;
            if (height == 0) { return M; }
            local = M;
            width = local[0].length;
            int[] pre = null, curr = null, temp = null;
            int copyIndex = 0, scanIndex = 0;
            while (scanIndex < height) {
                if (curr == null) {
                    curr = new int[width];
                } else {
                    // System.out.println("row: " + Arrays.toString(curr));
                    // System.out.println("replace row: " + Arrays.toString(local[copyIndex]));
                    copy(local[copyIndex++],curr);
                }
                for (int i = 0; i < width; i++) {
                    curr[i] = smooth(scanIndex,i);
                    // System.out.println("Average of point [" + scanIndex + "," + i + "] = " + curr[i]);
                }
                temp = curr;
                curr = pre;
                pre = temp;
                scanIndex++;
            }
            if (curr != null) { copy(local[copyIndex++],curr); }
            if (pre != null) { copy(local[copyIndex++],pre); }
            return local;
        }
        private void copy(int[] target, int[] resource) {
            for (int i = 0; i < target.length; i++) {
                target[i] = resource[i];
            }
        }
        private int smooth(int row, int col) {
            int[] pair = collectRow(row,col); // collect mid line
            int count = pair[0], sum = pair[1];
            if (row > 0) {                      // if not the first line, collect first line
                pair = collectRow(row-1,col);
                count += pair[0];
                sum += pair[1];
            }
            if (row < height - 1) {             // if not the last line, collect last line
                pair = collectRow(row+1,col);
                count += pair[0];
                sum += pair[1];
            }
            return sum / count;
        }
        private int[] collectRow(int row, int col) {
            int count = 1, sum = local[row][col];
            if (col > 0) {              // not first column
                count++;
                sum += local[row][col-1];
            }
            if (col < width - 1) {      // not the last column
                count++;
                sum += local[row][col+1];
            }
            return new int[]{count,sum};
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private int height = 0, width = 0;

        public int[][] imageSmoother(int[][] M) {
            height = M.length;
            if (height == 0) { return M; }
            width = M[0].length;
            int[][] res = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int count = 0, sum = 0;
                    for (int x = i-1; x <= i+1; x++) {
                        for (int y = j-1; y <= j+1; y++) {
                            if (isValid(x,y)) {
                                count++;
                                sum += M[x][y];
                            }
                        }
                    }
                    res[i][j] = sum / count;
                }
            }
            return res;
        }
        private boolean isValid(int x, int y) {
            return (x >= 0) && (x < height) && (y >= 0) && (y < width);
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int[][] imageSmoother(int[][] M) {
            return new int[][]{{3}};
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
        private ImageSmoother problem = new ImageSmoother();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] M, int[][] ans) {
            System.out.println("Original Image: ");
            Matrix.print(M);
            System.out.println("Smoothed Image: ");
            Matrix.print(solution.imageSmoother(M));
            System.out.println("Answer should be: ");
            Matrix.print(ans);
            System.out.println("\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] M1 = new int[][]{
                {1,1,1},
                {1,0,1},
                {1,1,1}
            };
            int[][] ans1 = new int[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}
            };
            int[][] M2 = new int[][]{
                {2,3,4},
                {5,6,7},
                {8,9,10},
                {11,12,13},
                {14,15,16}
            };
            int[][] ans2 = new int[][]{
                {4,4,5},
                {5,6,6},
                {8,9,9},
                {11,12,12},
                {13,13,14}
            };

            /** involk call() method HERE */
            call(M1,ans1);
            call(M2,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
