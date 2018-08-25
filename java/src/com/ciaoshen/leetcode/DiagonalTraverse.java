/**
 * Leetcode - Algorithm - DiagonalTraverse
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DiagonalTraverse implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DiagonalTraverse() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[] findDiagonalOrder(int[][] matrix); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[][] local = null;
        private int[] res = null;
        private int cur = 0;
        private int height = 0, width = 0;

        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix.length == 0) { return new int[0]; }
            init(matrix);
            up(0,0);
            return res;
        }
        private void init(int[][] matrix) {
            local = matrix;
            height = matrix.length;
            width = matrix[0].length;
            res = new int[height * width];
            cur = 0;
        }
        private void up(int x, int y) {
            if (y >= width) {
                down(x+2,y-1); return;
            } else if (x < 0) {
                down(x+1,y); return;
            }
            res[cur++] = local[x][y];
            if (x != height-1 || y != width-1) {
                up(x-1,y+1); return;
            }
        }
        private void down(int x, int y) {
            if (x >= height) {
                up(x-1,y+2); return;
            } else if (y < 0) {
                up(x,y+1); return;
            }
            res[cur++] = local[x][y];
            if (x != height-1 || y != width-1) {
                down(x+1,y-1); return;
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private int[][] local = null;
        private int[] res = null;
        private int cur = 0;
        private int height = 0, width = 0;

        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix.length == 0) { return new int[0]; }
            init(matrix);
            up(0,0);
            return res;
        }
        private void init(int[][] matrix) {
            local = matrix;
            height = matrix.length;
            width = matrix[0].length;
            res = new int[height * width];
            cur = 0;
        }
        private void up(int x, int y) {
            while (true) {
                if (y >= width) {
                    down(x+2,y-1); return;
                } else if (x < 0) {
                    down(x+1,y); return;
                }
                res[cur++] = local[x][y];
                if (x == height-1 && y == width-1) { return; }
                x--; y++;
            }
        }
        private void down(int x, int y) {
            while (true) {
                if (x >= height) {
                    up(x-1,y+2); return;
                } else if (y < 0) {
                    up(x,y+1); return;
                }
                res[cur++] = local[x][y];
                if (x == height-1 && y == width-1) { return; }
                x++; y--;
            }
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        private int[][] local = null;
        private int[] res = null;
        private int cur = 0;
        private int height = 0, width = 0;
        private boolean up = false;

        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix.length == 0) { return new int[0]; }
            init(matrix);
            parse(0,0);
            return res;
        }
        private void init(int[][] matrix) {
            local = matrix;
            height = matrix.length;
            width = matrix[0].length;
            res = new int[height * width];
            cur = 0;
            up = true;
        }
        private void parse(int x, int y) {
            while (true) {
                if (up) {
                    if (y >= width) {
                        x += 2; y -= 1;
                        up = false; continue;
                    } else if (x < 0) {
                        x += 1;
                        up = false; continue;
                    }
                    res[cur++] = local[x][y];
                    if (x == height-1 && y == width-1) { return; }
                    x--; y++;
                } else {
                    if (x >= height) {
                        x -= 1; y += 2;
                        up = true; continue;
                    } else if (y < 0) {
                        y += 1;
                        up = true; continue;
                    }
                    res[cur++] = local[x][y];
                    if (x == height-1 && y == width-1) { return; }
                    x++; y--;
                }
            }
        }
    }
    // you can expand more solutions HERE if you want...
    private class Solution4 extends Solution {
        { super.id = 4; }

        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0) { return new int[0]; }
            int height = matrix.length;
            int width = matrix[0].length;
            int[] res = new int[height * width];
            int cur = 0;
            int direction = -1; // up: -1, down: 1
            int x = 0, y = 0;
            while (true) {
                if (y >= width) { x += 2; y -= 1; direction = -direction; continue; }
                if (x >= height) { x -= 1; y += 2; direction = -direction; continue; }
                if (x < 0) { x += 1; direction = -direction; continue; }
                if (y < 0) { y += 1; direction = -direction; continue; }
                res[cur++] = matrix[x][y];
                if (x == height-1 && y == width-1) { return res; }
                x += direction; y -= direction;
            }
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
        private DiagonalTraverse problem = new DiagonalTraverse();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] matrix, int[] ans) {
            Matrix.print(matrix);
            System.out.println(Arrays.toString(solution.findDiagonalOrder(matrix)));
            System.out.println("Answer should be: " + Arrays.toString(ans));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] matrix1 = new int[][]{
                 { 1, 2, 3 },
                 { 4, 5, 6 },
                 { 7, 8, 9 }
            };
            int[] ans1 = new int[]{1,2,4,7,5,3,6,8,9};

            /** involk call() method HERE */
            call(matrix1, ans1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        test.test(4);
    }
}
