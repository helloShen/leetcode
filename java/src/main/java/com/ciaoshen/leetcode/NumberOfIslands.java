/**
 * Leetcode - Algorithm - Number of Islands
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.Matrix;

class NumberOfIslands {
    /**
     * Class Sea is a Union Find Data Structure
     */
    public class SolutionV1 {
        public int numIslands(char[][] grid) {
            if (grid.length == 0) { return 0; }
            int height = grid.length, width = grid[0].length;
            int size = height * width;
            Sea sea = new Sea(size+1);
            if (grid[0][0] == '1') { sea.createIsland(1); }
            for (int i = 1; i < width; i++) { // first line
                if (grid[0][i] == '1') {
                    // System.out.println("First line: [0," + i + "]");
                    if (grid[0][i-1] == '1') {
                        sea.union(i+1,i);
                    } else {
                        sea.createIsland(i+1);
                    }
                }
            }
            for (int i = 1; i < height; i++) { // first column
                if (grid[i][0] == '1') {
                    // System.out.println("First column: [" + i + ",0]");
                    if (grid[i-1][0] == '1') {
                        sea.union(i*width+1,(i-1)*width+1);
                    } else {
                        sea.createIsland(i*width+1);
                    }
                }
            }
            for (int i = 1; i < height; i++) {
                for (int j = 1; j < width; j++) {
                    if (grid[i][j] == '1') {
                        sea.createIsland(i*width+j+1);
                        if (grid[i-1][j] == '1') { sea.union(i*width+j+1,(i-1)*width+j+1); } // upper
                        if (grid[i][j-1] == '1') { sea.union(i*width+j+1,i*width+j); } // left
                    }
                }
            }
            return sea.size();
        }
        /**
         * Union Find Data Structure
         */
        private class Sea {
            private int[] sea;
            private int numIslands;
            /**
             * water is initialized by zero.
             * all zeroes(water) are not connected to each other.
             */
            public Sea(int size) {
                sea = new int[size];
            }
            /**
             * create a new island
             * x is the id of the new island
             */
            public void createIsland(int x) {
                sea[x] = x;
                numIslands++;
                // System.out.println("New Island " + x + " created!");
                // System.out.println("    >>> Now we have " + numIslands + " islands!");
            }
            /**
             * find and return the id of island x
             */
            public int findId(int x) {
                if (!isIsland(x)) { return 0; } // is water
                if (sea[x] == x) { return x; } // find id
                sea[x] = findId(sea[x]); // path compression
                return sea[x];
            }
            /**
             * return if point x and point y are belong to the same island
             */
            public boolean connected(int x, int y) {
                int idX = findId(x);
                int idY = findId(y);
                return (idX == idY) && (idX != 0) && (idY != 0);
            }
            /**
             * union two exist islands x and y
             * if island does not exist, create new island first
             * use the id of islands y as the id of new combined island
             */
            public void union(int x, int y) {
                if (!connected(x,y)) {
                    if (!isIsland(y)) { createIsland(y); }
                    if (!isIsland(x)) { createIsland(x); }
                    int idX = findId(x);
                    int idY = findId(y);
                    sea[idX] = idY;
                    numIslands--;
                    // System.out.println("Island " + idX + " is combined with island " + idY + "!");
                    // System.out.println("    >>> Now we have " + numIslands + " islands!");
                }
            }
            /**
             * verify if point x is an island?
             */
            public boolean isIsland(int x) {
                return sea[x] > 0;
            }
            /**
             * return the number of isolated islands
             */
             public int size() {
                 return numIslands;
             }
        }
    }
    /**
     * Class Sea is a Union Find Data Structure
     * 精简版
     */
    public class SolutionV2 {
        public int numIslands(char[][] grid) {
            if (grid.length == 0) { return 0; }
            int height = grid.length, width = grid[0].length;
            int size = height * width;
            Sea sea = new Sea(size+1);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (grid[i][j] == '1') {
                        int pos = i * width + j + 1;
                        sea.createIsland(pos);
                        // System.out.println("Find new island @[" + i + "," + j + "]!    Now we have " + sea.size() + " islands!");
                        if (i > 0 && grid[i-1][j] == '1') {
                            sea.union(pos,pos-width);
                            // System.out.println("island @[" + i + "," + j + "] is combined with island @[" + (i-1) + "," + j + "].   Now we have " + sea.size() + " islands!");
                        } // upper
                        if (j >0 && grid[i][j-1] == '1') {
                            sea.union(pos,pos-1);
                            // System.out.println("island @[" + i + "," + j + "] is combined with island @[" + i + "," + (j-1) + "].   Now we have " + sea.size() + " islands!");
                        } // left
                    }
                }
            }
            return sea.size();
        }
        /**
         * Union Find Data Structure
         */
        private class Sea {
            private int[] sea;
            private int numIslands;
            /**
             * water is initialized by zero.
             * all zeroes(water) are not connected to each other.
             */
            public Sea(int size) {
                sea = new int[size];
            }
            /**
             * create a new island
             * x is the id of the new island
             */
            public void createIsland(int x) {
                sea[x] = x;
                numIslands++;
            }
            /**
             * find and return the id of island x
             */
            public int findId(int x) {
                if (sea[x] == x) { return x; } // find id
                sea[x] = findId(sea[x]); // path compression
                return sea[x];
            }
            /**
             * union two exist islands x and y
             * use the id of islands y as the id of new combined island
             */
            public void union(int x, int y) {
                int idY = findId(y);
                int idX = findId(x);
                if (idY != idX) {
                    sea[idX] = idY;
                    numIslands--;
                }
            }
            /**
             * return the number of isolated islands
             */
             public int size() {
                 return numIslands;
             }
        }
    }
    /**
     * Do not use Sea class
     * Union Find data stucture is built in the method numIslands()
     */
    public class SolutionV3 {
        public int numIslands(char[][] grid) {
            if (grid.length == 0) { return 0; }
            int height = grid.length, width = grid[0].length;
            int size = height * width;
            int[] sea = new int[size+1];
            int numIslands = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (grid[i][j] == '1') {
                        int pos = i * width + j + 1;
                        if (i > 0 && grid[i-1][j] == '1') {  // merged with upper island
                            sea[pos] = findId(sea,pos-width);
                        } else { // upper is 0
                            sea[pos] = pos;
                            numIslands++;
                        }
                        if (j > 0 && grid[i][j-1] == '1') { // merge with left island
                            int idCurr = findId(sea,pos);
                            int idLeft = findId(sea,pos-1);
                            if (idCurr != idLeft) {
                                sea[idCurr] = idLeft;
                                numIslands--;
                            }
                        }
                    }
                }
            }
            return numIslands;
        }
        public int findId(int[] sea, int pos) {
            if (sea[pos] == pos) { return pos; }
            sea[pos] = findId(sea,sea[pos]); // path compression
            return sea[pos];
        }
    }
    /**
     * Build-In Find Union
     * weighted quick-union with path compression
     */
    public class solutionV4 {
         public int numIslands(char[][] grid) {
             if (grid.length == 0) { return 0; }
             int height = grid.length, width = grid[0].length;
             int size = height * width;
             int[] sea = new int[size+1];
             int[] weight = new int[size+1];
             int numIslands = 0;
             for (int i = 0; i < height; i++) {
                 for (int j = 0; j < width; j++) {
                     if (grid[i][j] == '1') {
                         int pos = i * width + j + 1;
                         if (i > 0 && grid[i-1][j] == '1') {  // merged with upper island
                             int idUpper = findId(sea,pos-width);
                             sea[pos] = idUpper;
                             weight[idUpper]++;
                         } else { // upper is 0
                             sea[pos] = pos;
                             weight[pos]++;
                             numIslands++;
                         }
                         if (j >0 && grid[i][j-1] == '1') { // merge with left island
                             int idCurr = findId(sea,pos);
                             int idLeft = findId(sea,pos-1);
                             if (idCurr != idLeft) {
                                 if (weight[idCurr] <= weight[idLeft]) {
                                    sea[idCurr] = idLeft; // expend current island to the left island
                                    weight[idLeft] += weight[idCurr];
                                    weight[idCurr] = 0;
                                 } else {
                                    sea[idLeft] = idCurr; // expand left island to the current island
                                    weight[idCurr] += weight[idLeft];
                                    weight[idLeft] = 0;
                                 }
                                 numIslands--;
                             }
                         }
                     }
                 }
             }
             return numIslands;
         }
         public int findId(int[] sea, int pos) {
             if (sea[pos] == pos) { return pos; }
             sea[pos] = findId(sea,sea[pos]); // path compression
             return sea[pos];
         }
    }
    /**
     * DFS探索整个岛。
     */
    public class Solution {
         public int numIslands(char[][] grid) {
             int count = 0;
             if (grid.length == 0) { return count; }
             for (int i = 0; i < grid.length; i++) {
                 for (int j = 0; j < grid[0].length; j++) {
                     if (grid[i][j] == '1') {
                         count++;
                         dfs(grid,i,j);
                     }
                 }
             }
             return count;
         }
         /**
          * 以[x,y]点为起点，DFS探索整个岛。探索到的点，临时标记为'x'。
          * 如果不能改动原来的grid，最后可以再遍历一遍，把所有'x'改回'1'。
          */
         public void dfs(char[][] grid, int x, int y) {
             // base case
             if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) { return; } // 越界
             char c = grid[x][y];
             if (c == '0' || c == 'x') { return; }
             // assert: grid[x][y] = '1'
             grid[x][y] = 'x';
             dfs(grid,x-1,y);
             dfs(grid,x+1,y);
             dfs(grid,x,y-1);
             dfs(grid,x,y+1);
         }
    }
    private static NumberOfIslands test = new NumberOfIslands();
    private static Solution solution = test.new Solution();
    private static void callNumIslands(char[][] grid, String ans) {
        System.out.println("Board: ");
        Matrix.print(grid);
        System.out.println("Has " + solution.numIslands(grid) + " islands!      [answer should be " + ans + "]");
    }
    private static void test() {
        char[][] grid1 = new char[][]{
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        char[][] grid2 = new char[][]{
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        char[][] grid3 = new char[][]{
            {'1','1','1'},
            {'0','1','0'},
            {'1','1','1'}
        };
        char[][] grid4 = new char[][]{
            {'1','1','1','1','1','1','1'},
            {'0','0','0','0','0','0','1'},
            {'1','1','1','1','1','0','1'},
            {'1','0','0','0','1','0','1'},
            {'1','0','1','0','1','0','1'},
            {'1','0','1','1','1','0','1'},
            {'1','1','1','1','1','1','1'}
        };
        callNumIslands(grid1,"1");
        callNumIslands(grid2,"3");
        callNumIslands(grid3,"1");
        callNumIslands(grid4,"1");
    }
    public static void main(String[] args) {
        test();
    }
}
