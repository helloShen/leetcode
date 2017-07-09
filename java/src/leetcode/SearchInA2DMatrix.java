/**
 * Leetcode - Algorithm - Search in a 2D Matrix
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.Matrix;

class SearchInA2DMatrix {
    /** DFS */
    public class SolutionV1 {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix.length == 0) { return false; }
            int height = matrix.length, width = matrix[0].length;
            int halfHeight = (height - 1) / 2;
            int halfWidth = (width - 1) / 2;
            boolean[][] visited = new boolean[height][width];
            return dfs(matrix,target,halfHeight,halfWidth,visited);
        }
        private boolean dfs(int[][] matrix, int target, int x, int y, boolean[][] visited) {
            if (x < 0 || x == matrix.length || y < 0 || y == matrix[0].length || visited[x][y]) { return false; }
            int val = matrix[x][y];
            visited[x][y] = true;
            if (val > target) {
                return dfs(matrix,target,x-1,y,visited) || dfs(matrix,target,x,y-1,visited);
            } else if (val < target) {
                return dfs(matrix,target,x+1,y,visited) || dfs(matrix,target,x,y+1,visited);
            } else {
                return true;
            }
        }
    }
    /** Binary Search */
    public class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix.length == 0) { return false; }
            return binarySearch(matrix,target,0,matrix.length-1,0,matrix[0].length-1);
        }
        private boolean binarySearch(int[][] matrix, int target, int up, int down, int left, int right) {
            if (up > down || left > right) { return false; }
            int mid = up + (down - up) / 2;
            int index = index(matrix,target,mid,left,right);
            if (index != matrix[0].length && (matrix[mid][index] == target)) {
                return true;
            } else {
                return binarySearch(matrix,target,mid+1,down,left,index-1) || binarySearch(matrix,target,up,mid-1,index,right);
            }
        }
        /**
         * if find the number, return the index of number
         * otherwise, return the position to insert(before the first number greater than the number).
         */
        private int index(int[][] matrix, int target, int row, int lo, int hi) {
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int val = matrix[row][mid];
                if (val > target) {
                    hi = mid - 1;
                } else if (val < target) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
            return lo;
        }
    }
    private class Test {
        private void callSearchMatrix(int[][] matrix, int target, String answer) {
            Matrix.print(matrix);
            System.out.println(target + " exists in matrix? " + solution.searchMatrix(matrix,target) + "      answer[" + answer + "]");
        }
        private void test() {
            int[][] matrix1 = new int[][]{
              {1,4,7,11,15},
              {2,5,8,12,19},
              {3,6,9,16,22},
              {10,13,14,17,24},
              {18,21,23,26,30}
            };
            int target1 = 5;
            int target2 = 20;
            // int[][] matrix2 = new int[][]{};
            // int target2 =;
            // int[][] matrix3 = new int[][]{};
            // int target3 =;
            callSearchMatrix(matrix1,target1,"true");
            callSearchMatrix(matrix1,target2,"false");
            // callSearchMatrix(matrix2,target2,"");
            // callSearchMatrix(matrix3,target3,"");
        }
    }
    private static SearchInA2DMatrix problem = new SearchInA2DMatrix();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
