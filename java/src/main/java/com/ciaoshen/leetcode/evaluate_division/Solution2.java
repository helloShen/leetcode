/**
 * Leetcode - evaluate_division
 */
package com.ciaoshen.leetcode.evaluate_division;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution2 implements Solution {

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // detect size
        Map<String, Integer> idTable = new HashMap<>();
        int id = 0;
        for (String[] eq : equations) {
            if (!idTable.containsKey(eq[0]))  idTable.put(eq[0], id++);
            if (!idTable.containsKey(eq[1]))  idTable.put(eq[1], id++);
        }
        int size = idTable.size();
        // init floydWarshall
        double[][] floydWarshall = new double[size][size];
        for (int i = 0; i < equations.length; i++) {
            int idA = idTable.get(equations[i][0]);
            int idB = idTable.get(equations[i][1]);
            double value = values[i];
            floydWarshall[idA][idA] = 1.0;
            floydWarshall[idB][idB] = 1.0;
            floydWarshall[idA][idB] = value;
            floydWarshall[idB][idA] = 1 / value;
        }
        printfloydWarshall(floydWarshall);
        // derive all relations
        for (int media = 0; media < size; media++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (floydWarshall[i][media] != 0.0 && floydWarshall[media][j] != 0.0) {
                        floydWarshall[i][j] = floydWarshall[i][media] * floydWarshall[media][j];
                    }
                }
            }
        }
        printfloydWarshall(floydWarshall);
        // collect result
        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (idTable.containsKey(queries[i][0]) && idTable.containsKey(queries[i][1])) {
                int idA = idTable.get(queries[i][0]);
                int idB = idTable.get(queries[i][1]);
                result[i] = (floydWarshall[idA][idB] == 0.0)? -1.0 : floydWarshall[idA][idB];
            } else {
                result[i] = -1.0;
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    private void printfloydWarshall(double[][] floydWarshall) {
        System.out.println("floydWarshall: ");
        for (double[] line : floydWarshall) {
            System.out.println(Arrays.toString(line));
        }
    }

}
