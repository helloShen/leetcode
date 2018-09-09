/**
 * Leetcode - Algorithm - SparseMatrixMultiplication
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class SparseMatrixMultiplication implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private SparseMatrixMultiplication() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[][] multiply(int[][] A, int[][] B); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int[][] multiply(int[][] A, int[][] B) {
            if (A.length == 0 || B.length == 0) { return null; }
            int[][] res = new int[A.length][B[0].length];
            int x = 0, y = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j <B[0].length; j++) {
                    for (int k = 0; k < A[i].length; k++) {
                        x = A[i][k]; y = B[k][j];
                        if ((x | y) != 0) {
                            res[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int[][] multiply(int[][] A, int[][] B) {
            if (A.length == 0 || B.length == 0) { return null; }
            int[][] res = new int[A.length][B[0].length];
            Map<Integer,Map<Integer,Integer>> mapA = new HashMap<>();
            Map<Integer,Map<Integer,Integer>> mapB = new HashMap<>();
            int num = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    num = A[i][j];
                    if (num != 0) {
                        if (mapA.get(i) == null) { mapA.put(i,new HashMap<Integer,Integer>()); }
                        mapA.get(i).put(j,num);
                    }
                }
            }
            for (int i = 0; i < B[0].length; i++) {  // B的i列
                for (int j = 0; j < B.length; j++) { // B的j行
                    num = B[j][i];
                    if (num != 0) {
                        if (mapB.get(i) == null) { mapB.put(i,new HashMap<Integer,Integer>()); }
                        mapB.get(i).put(j,num);
                    }
                }
            }
            // System.out.println("mapA = " + mapA);
            // System.out.println("mapB = " + mapB);
            Map<Integer, Integer> x, y;
            int i = 0;
            for (Map.Entry<Integer,Map<Integer,Integer>> entryA : mapA.entrySet()) {
                x = entryA.getValue();
                // System.out.println("MapA col colletction = " + x);
                int j = 0;
                for (Map.Entry<Integer,Map<Integer,Integer>> entryB : mapB.entrySet()) {
                    y = entryB.getValue();
                    // System.out.println("MapB row colletction = " + y);
                    for (Map.Entry<Integer,Integer> entry : x.entrySet()) {
                        int k = entry.getKey();
                        if (y.containsKey(k)) {
                            // System.out.println("i=" + i + ", j=" + j + ", k=" + k + ", A[" + i + "][" + k + "]=" + entry.getValue() + ", B[" + k + "][" + j + "]=" + y.get(k));
                            res[entryA.getKey()][entryB.getKey()] += entry.getValue() * y.get(k); // A的i行k列 * B的k行j列
                        }
                    }
                    j++;
                }
                i++;
            }
            return res;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int[][] multiply(int[][] A, int[][] B) {
            if (A.length == 0 || B.length == 0) { return null; }
            int[][] res = new int[A.length][B[0].length];
            int[][][] ALight = new int[A.length][A[0].length][2];      // A每行的有效列
            int[][][] BLight = new int[B[0].length][B.length][2];   // B每列的有效行
            int num = 0;
            for (int i = 0; i < A.length; i++) {
                int offset = 0;
                for (int j = 0; j < A[i].length; j++) {
                    num = A[i][j];
                    if (num != 0) {                     // 记录[列标，值]对
                        ALight[i][offset][0] = j;
                        ALight[i][offset++][1] = num;
                        // System.out.println("ALight: [" + i + ", " + j + ", " + num + "]");
                    }
                }
                if (offset < A[0].length) { ALight[i][offset][0] = -1; }
            }
            for (int i = 0; i < B[0].length; i++) {  // B的i列
                int offset = 0;
                for (int j = 0; j < B.length; j++) { // B的j行
                    num = B[j][i];
                    if (num != 0) {                     // 记录[行标，值]对
                        BLight[i][offset][0] = j;
                        BLight[i][offset++][1] = num;
                        // System.out.println("BLight: [" + i + ", " + j + ", " + num + "]");
                        // Matrix.print(BLight[i]);
                    }
                }
                if (offset < B.length) { BLight[i][offset][0] = -1; }
            }
            for (int i = 0; i < ALight.length; i++) {
                // Matrix.print(ALight[i]);
                for (int j = 0; j < BLight.length; j++) {
                    int curA = 0, curB = 0;
                    // System.out.println("Col " + j + " of BLight");
                    // Matrix.print(BLight[j]);
                    for (int k = 0; k < A[0].length; k++) {
                        if (ALight[i][curA][0] < 0 || BLight[j][curB][0] < 0) { break; }
                        if (ALight[i][curA][0] > BLight[j][curB][0]) {
                            curB++;
                        } else if (ALight[i][curA][0] < BLight[j][curB][0]) {
                            curA++;
                        } else {
                            res[i][j] += (ALight[i][curA++][1] * BLight[j][curB++][1]);
                            // System.out.println(ALight[i][curA-1][1] + " * " + BLight[j][curB-1][1] + " = " + res[i][j]);
                        }
                    }
                }
            }
            return res;
        }
    }

    private class Solution4 extends Solution {
        { super.id = 4; }

        public int[][] multiply(int[][] A, int[][] B) {
            if (A.length == 0 || B.length == 0) { return null; }
            int[][] res = new int[A.length][B[0].length];
            int ASize = 0;
            for (int i = 0; i < A.length; i++) {
                int count = 0;
                for (int j = 0; j < A[i].length; j++) {
                    if (A[i][j] != 0) { count++; }
                }
                ASize = Math.max(ASize,count);
            }
            int BSize = 0;
            for (int i = 0; i < B[0].length; i++) {  // B的i列
                int count = 0;
                for (int j = 0; j < B.length; j++) { // B的j行
                    if (B[j][i] != 0) { count++; }
                }
                BSize = Math.max(BSize,count);
            }
            int[][][] ALight = new int[A.length][ASize][2];      // A每行的有效列
            int[][][] BLight = new int[B[0].length][BSize][2];   // B每列的有效行
            int num = 0;
            for (int i = 0; i < A.length; i++) {
                int offset = 0;
                for (int j = 0; j < A[i].length; j++) {
                    num = A[i][j];
                    if (num != 0) {                     // 记录[列标，值]对
                        ALight[i][offset][0] = j;
                        ALight[i][offset++][1] = num;
                        // System.out.println("ALight: [" + i + ", " + j + ", " + num + "]");
                    }
                }
                if (offset < ASize) { ALight[i][offset][0] = -1; }
            }
            for (int i = 0; i < B[0].length; i++) {  // B的i列
                int offset = 0;
                for (int j = 0; j < B.length; j++) { // B的j行
                    num = B[j][i];
                    if (num != 0) {                     // 记录[行标，值]对
                        BLight[i][offset][0] = j;
                        BLight[i][offset++][1] = num;
                        // System.out.println("BLight: [" + i + ", " + j + ", " + num + "]");
                        // Matrix.print(BLight[i]);
                    }
                }
                if (offset < BSize) { BLight[i][offset][0] = -1; }
            }
            for (int i = 0; i < ALight.length; i++) {
                // Matrix.print(ALight[i]);
                for (int j = 0; j < BLight.length; j++) {
                    int curA = 0, curB = 0;
                    // System.out.println("Col " + j + " of BLight");
                    // Matrix.print(BLight[j]);
                    for (int k = 0; k < A[0].length; k++) {
                        if (curA == ASize || curB == BSize || ALight[i][curA][0] < 0 || BLight[j][curB][0] < 0) { break; }
                        if (ALight[i][curA][0] > BLight[j][curB][0]) {
                            curB++;
                        } else if (ALight[i][curA][0] < BLight[j][curB][0]) {
                            curA++;
                        } else {
                            res[i][j] += (ALight[i][curA++][1] * BLight[j][curB++][1]);
                            // System.out.println(ALight[i][curA-1][1] + " * " + BLight[j][curB-1][1] + " = " + res[i][j]);
                        }
                    }
                }
            }
            return res;
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
        private SparseMatrixMultiplication problem = new SparseMatrixMultiplication();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] A, int[][] B) {
            System.out.println("\nA = ");
            Matrix.print(A);
            System.out.println("\nB = ");
            Matrix.print(B);
            System.out.println("\nAB = ");
            Matrix.print(solution.multiply(A,B));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] A1 = new int[][]{
                {1,0,0},
                {-1,0,3}
            };
            int[][] B1 = new int[][]{
                {7,0,0},
                {0,0,0},
                {0,0,1}
            };

            /** involk call() method HERE */
            call(A1,B1);
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
