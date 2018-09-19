/**
 * Leetcode - evaluate_division
 */
package com.ciaoshen.leetcode.evaluate_division;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution3 implements Solution {

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // detect size
        Map<String, Integer> idTable = new HashMap<>();
        int id = 0;
        for (String[] eq : equations) {
            if (!idTable.containsKey(eq[0]))  idTable.put(eq[0], id++);
            if (!idTable.containsKey(eq[1]))  idTable.put(eq[1], id++);
        }
        int size = idTable.size();
        floydWarshall = new double[size][size];
        // init floydWarshall
        for (int i = 0; i < equations.length; i++) {
            int idA = idTable.get(equations[i][0]);
            int idB = idTable.get(equations[i][1]);
            double value = values[i];
            floydWarshall[idA][idA] = 1.0;
            floydWarshall[idB][idB] = 1.0;
            floydWarshall[idA][idB] = value;
            floydWarshall[idB][idA] = 1 / value;
        }
        printfloydWarshall();
        double[] result = new double[queries.length];
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < queries.length; i++) {
            if (idTable.containsKey(queries[i][0]) && idTable.containsKey(queries[i][1])) {
                visited.clear();
                visited.add(idTable.get(queries[i][0]));
                result[i] = dfs(idTable.get(queries[i][0]), idTable.get(queries[i][1]), 1.0, visited);
            } else {
                result[i] = -1.0;
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    private double[][] floydWarshall;

    private double dfs(int from, int to, double pre, Set<Integer> visited) {
        if (from == to) {
            return pre;
        }
        for (int media = 0; media < floydWarshall.length; media++) {
            if (floydWarshall[from][media] != 0.0 && !visited.contains(media)) {
                visited.add(media);
                double result = dfs(media, to, pre * floydWarshall[from][media], visited);
                if (result != -1.0) { return result; }
                visited.remove(media);
            }
        }
        return -1.0;
    }

    private void printfloydWarshall() {
        System.out.println("floydWarshall: ");
        for (double[] line : floydWarshall) {
            System.out.println(Arrays.toString(line));
        }
    }

}
