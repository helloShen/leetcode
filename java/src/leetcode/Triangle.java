/**
 * Leetcode - Algorithm - Triangle
 */
package com.ciaoshen.leetcode;
import java.util.*;

class Triangle {
    public int minimumTotalV1(List<List<Integer>> triangle) {
        return backtracking(triangle,0,0);
    }
    // depth=[0,triangle.size()-1]
    public int backtracking(List<List<Integer>> triangle, int depth, int index) {
        if (depth == triangle.size()) { return 0; }
        int num = triangle.get(depth).get(index);
        int left = backtracking(triangle,depth+1,index);
        int right = backtracking(triangle,depth+1,index+1);
        return num + Math.min(left,right);
    }
    public int minimumTotalV2(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                matrix[i][j] = triangle.get(i).get(j);
            }
        }
        for (int i = size-2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                matrix[i][j] = matrix[i][j] + Math.min(matrix[i+1][j],matrix[i+1][j+1]);
            }
        }
        return matrix[0][0];
    }
    public int minimumTotalV3(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] memo = new int[size];
        for (int i = 0; i < size; i++) {
            memo[i] = triangle.get(size-1).get(i);
        }
        int register = 0;
        for (int i = size-2; i >= 0; i--) { // depth
            for (int j = i; j >= 0; j--) { // breadth
                int num = triangle.get(i).get(j);
                int temp = num + Math.min(memo[j],memo[j+1]);
                if (j < i) { memo[j+1] = register; }
                register = temp;
            }
            memo[0] = register;
            System.out.println(Arrays.toString(memo));
        }
        return memo[0];
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] memo = new int[size];
        for (int i = 0; i < size; i++) {
            memo[i] = triangle.get(size-1).get(i);
        }
        for (int i = size-2; i >= 0; i--) { // depth
            for (int j = 0; j <= i; j++) {
                memo[j] = triangle.get(i).get(j) + Math.min(memo[j],memo[j+1]); // 这里是错位写入，每次写在高位memo[j+1]，不影响下一位的计算。
            }
            System.out.println(Arrays.toString(memo));
        }
        return memo[0];
    }
    private static Triangle test = new Triangle();
    private static final int MAX = 10;
    private static List<List<Integer>> triangle() {
        Random r = new Random();
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        triangle.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{num(MAX)})));
        triangle.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{num(MAX),num(MAX)})));
        triangle.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{num(MAX),num(MAX),num(MAX)})));
        triangle.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{num(MAX),num(MAX),num(MAX),num(MAX)})));
        return triangle;
    }
    private static final Random R = new Random();
    private static int num(int max) {
        return R.nextInt(max)+1;
    }

    private static void testMinimumTotal() {
        List<List<Integer>> triangle = triangle();
        System.out.println(triangle);
        System.out.println("Minimum Path = " + test.minimumTotal(triangle));
    }
    private static void testTriangle() {
        System.out.println(triangle());
    }
    public static void main(String[] args) {
        //testTriangle();
        testMinimumTotal();
    }
}
