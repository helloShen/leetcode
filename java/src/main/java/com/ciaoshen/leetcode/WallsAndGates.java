/**
 * Leetcode - Algorithm - WallsAndGates
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class WallsAndGates implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private WallsAndGates() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void wallsAndGates(int[][] rooms); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int localRooms[][] = new int[0][0];
        private int height = 0, width = 0;
        public void wallsAndGates(int[][] rooms) {
            if (rooms.length == 0) { return; }
            localRooms = rooms;
            height = rooms.length;
            width = rooms[0].length;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (rooms[i][j] == 0) {
                        update(i-1, j, 1);
                        update(i+1, j, 1);
                        update(i, j-1, 1);
                        update(i, j+1, 1);
                    }
                }
            }
        }
        // y纵轴，x横轴
        private void update(int y, int x, int dis) {
            if (y >= 0 && y < height && x >= 0 && x < width) {
                if (localRooms[y][x] > 0 && localRooms[y][x] > dis) {
                    localRooms[y][x] = dis;
                    update(y+1, x, dis+1); // up
                    update(y-1, x, dis+1); // down
                    update(y, x-1, dis+1); // left
                    update(y, x+1, dis+1); // right
                }
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public void wallsAndGates(int[][] rooms) {

        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void wallsAndGates(int[][] rooms) {

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
        private WallsAndGates problem = new WallsAndGates();
        private Solution solution = null;

        private void call(int[][] rooms) {
            System.out.println("Before: ");
            Matrix.print(rooms);
            solution.wallsAndGates(rooms);
            System.out.println("After: ");
            Matrix.print(rooms);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] room1 = new int[][]{
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE, },
            };

            /** involk call() method HERE */
            call(room1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
