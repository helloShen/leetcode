/**
 * Leetcode - Algorithm - Rotate Image
 */
package com.ciaoshen.leetcode;
import java.util.*;

class RotateImage {

    public void rotateV1(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];
        for (int i = 0; n-i > i; i++) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int j = i; j < n - i - 1; j++) {
                list.add(matrix[i][j]);
            }
            for (int j = i; j < n - i - 1; j++) {
                list.add(matrix[j][n-i-1]);
            }
            for (int j = n - i - 1; j > i; j--) {
                list.add(matrix[n-i-1][j]);
            }
            for (int j = n - i - 1; j > i; j--) {
                list.add(matrix[j][i]);
            }
            System.out.println(list);
            int distance = n - 2 * i - 1;
            for (int j = 0; j < distance; j++) {
                list.offerFirst(list.pollLast());
            }
            System.out.println(list);
            for (int j = i; j < n - i - 1; j++) {
                result[i][j] = list.poll();
            }
            for (int j = i; j < n - i - 1; j++) {
                result[j][n-i-1] = list.poll();
            }
            for (int j = n - i - 1; j > i; j--) {
                result[n-i-1][j] = list.poll();
            }
            for (int j = n - i - 1; j > i; j--) {
                result[j][i] = list.poll();
            }
        }
        if (matrix.length % 2 == 1) {
            int mid = matrix.length / 2;
            result[mid][mid] = matrix[mid][mid];
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = result[i][j];
            }
        }
    }
    public void rotateV2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; n-i > i; i++) {
            int[] circle = new int[(n-i*2-1)*4];
            int cursor = 0;
            for (int j = i; j < n - i - 1; j++) {
                circle[cursor++] = matrix[i][j];
            }
            for (int j = i; j < n - i - 1; j++) {
                circle[cursor++] = matrix[j][n-i-1];
            }
            for (int j = n - i - 1; j > i; j--) {
                circle[cursor++] = matrix[n-i-1][j];
            }
            for (int j = n - i - 1; j > i; j--) {
                circle[cursor++] = matrix[j][i];
            }
            //System.out.println(Arrays.toString(circle));
            int distance = n - 2 * i - 1;
            int start = circle.length - distance;
            for (int j = i; j < n - i - 1; j++) {
                matrix[i][j] = circle[start++ % circle.length];
            }
            for (int j = i; j < n - i - 1; j++) {
                matrix[j][n-i-1] = circle[start++ % circle.length];
            }
            for (int j = n - i - 1; j > i; j--) {
                matrix[n-i-1][j] = circle[start++ % circle.length];
            }
            for (int j = n - i - 1; j > i; j--) {
                matrix[j][i] = circle[start++ % circle.length];
            }
        }
    }
    public void rotate(int[][] matrix) {
        for (int i = 0; i <= (matrix.length - 1) / 2; i++) {
            int[] temp = Arrays.copyOf(matrix[i],matrix.length);
            matrix[i] = matrix[matrix.length-1-i];
            matrix[matrix.length-1-i] = temp;
        }
        //printMatrix(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i > j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
    }
    private static RotateImage test = new RotateImage();
    private static int[][] matrix1 = new int[][] {
        {1}
    };
    private static int[][] matrix2 = new int[][] {
        {1,2},
        {3,4}
    };
    private static int[][] matrix3 = new int[][] {
        {1,2,3},
        {4,5,6},
        {7,8,9}
    };
    private static int[][] matrix4 = new int[][] {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,16}
    };
    private static void testRotate() {
        printMatrix(matrix1);
        test.rotate(matrix1);
        printMatrix(matrix1);
        printMatrix(matrix2);
        test.rotate(matrix2);
        printMatrix(matrix2);
        printMatrix(matrix3);
        test.rotate(matrix3);
        printMatrix(matrix3);
        printMatrix(matrix4);
        test.rotate(matrix4);
        printMatrix(matrix4);
    }
    private static void printMatrix(int[][] matrix) {
        System.out.println("\n\n");
        System.out.println("###################################");
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                System.out.print(matrix[y][x] + "\t");
                if (x == matrix.length-1) { System.out.print("##\n\n"); }
            }
        }
        System.out.println("###################################");
    }
    private static void testPrintMatrix() {
        test.printMatrix(matrix1);
        test.printMatrix(matrix2);
        test.printMatrix(matrix3);
        test.printMatrix(matrix4);
    }
    public static void main(String[] args) {
        //testPrintMatrix();
        testRotate();
    }
}
