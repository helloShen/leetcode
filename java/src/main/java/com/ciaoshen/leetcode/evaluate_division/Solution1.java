/**
 * Leetcode - evaluate_division
 */
package com.ciaoshen.leetcode.evaluate_division;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // 1st pass
        int idx = 0;
        idTable = new HashMap<String, Integer>();
        for (String[] eq : equations) {
            if (!idTable.containsKey(eq[0])) {
                idTable.put(eq[0], idx++);
            }
            if (!idTable.containsKey(eq[1])) {
                idTable.put(eq[1], idx++);
            }
        }
        int size = idTable.size();
        System.out.println("Number size = " + size);
        // 2nd pass
        quotientMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(quotientMatrix[i], -1.0);
        }
        System.out.println("quotientMatrix = ");
        for (double[] line : quotientMatrix) {
            System.out.println(Arrays.toString(line));
        }
        visited = new HashSet<Integer>();
        for (int i = 0; i < equations.length; i++) {
            System.out.println("\n[" + equations[i][0] + ", "  + equations[i][1] + "]");
            int idA = idTable.get(equations[i][0]);
            int idB = idTable.get(equations[i][1]);
            int type = 0; // number of new points
            int newNum = -1;
            int passBy = -1;
            if (visited.add(idA)) {
                type++;
                newNum = idA;
                passBy = idB;
            }
            if (visited.add(idB)) {
                type++;
                newNum = idB;
                passBy = idA;
            }
            System.out.println("type = " + type + ", newNum = " + newNum + ", passBy = " + passBy);
            double quotient = values[i];
            switch(type) {
                case 2:
                case 0:
                    addNew(idA, idB, quotient);
                    addNew(idB, idA, 1 / quotient);
                    break;
                case 1:
                    if (newNum == idA) {
                        addNew(newNum, passBy, quotient);
                    } else {
                        addNew(newNum, passBy, 1 / quotient);
                    }
                    break;
            }
            System.out.println("quotientMatrix = ");
            for (double[] line : quotientMatrix) {
                System.out.println(Arrays.toString(line));
            }
        }
        // collect result
        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            System.out.println(queries[i][0] + " / " + queries[i][1]);
            if (!idTable.containsKey(queries[i][0]) || !idTable.containsKey(queries[i][1])) {
                result[i] = -1.0;
                continue;
            }
            int idA = idTable.get(queries[i][0]);
            int idB = idTable.get(queries[i][1]);
            result[i] = quotientMatrix[idA][idB];
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    private double[][] quotientMatrix;
    private Map<String, Integer> idTable;
    private Set<Integer> visited;

    private void addNew(int newNum, int passBy, double quotient) {
        quotientMatrix[newNum][passBy] = quotient;
        quotientMatrix[passBy][newNum] = 1 / quotient;
        for (int i = 0; i < quotientMatrix.length; i++) {
            System.out.println("Matrix[" + passBy + "][" + i + "] = " + quotientMatrix[passBy][i]);
            if (quotientMatrix[passBy][i] != -1.0) {
                System.out.println("Find friend of " + passBy + " is : " + i + ", quotient = " + quotientMatrix[passBy][i]);
                quotientMatrix[newNum][i] = quotient * quotientMatrix[passBy][i];
                for (int j = 0; j < quotientMatrix.length; j++) {
                    if (quotientMatrix[j][newNum] != -1.0) {
                        quotientMatrix[j][i] = quotientMatrix[j][newNum] * quotientMatrix[newNum][i];
                        quotientMatrix[i][j] = 1 / quotientMatrix[j][i];
                    }
                }
                System.out.println(newNum + " / " + i + " = " + quotientMatrix[newNum][i]);
                quotientMatrix[i][newNum] = 1 / quotientMatrix[newNum][i];
                System.out.println(i + " / " + newNum + " = " + quotientMatrix[i][newNum]);
            }
        }
    }

}
