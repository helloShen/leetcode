/**
 * Leetcode - Algorithm - Gas Station
 */
package com.ciaoshen.leetcode;
import java.util.*;

class GasStation {
    public class SolutionV1 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) { return -1; }
            int size = gas.length;
            outFor:
            for (int i = 0; i < size; i++) {
                int tankRemain = 0;
                for (int j = 0, cur = i; j < size; j++, cur = (++cur)%size) {
                    tankRemain = tankRemain + gas[cur] - cost[cur];
                    System.out.println("+" + gas[cur] + "-" + cost[cur] + "=" + tankRemain);
                    if (tankRemain < 0) { continue outFor; }
                }
                return i;
            }
            return -1;
        }
    }
    public class SolutionV2 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) { return -1; }
            int size = gas.length;
            int[] diff = new int[size];
            for (int i = 0; i < size; i++) {
                diff[i] = gas[i] - cost[i];
            }
            outFor:
            for (int i = 0; i < size; i++) {
                int tankRemain = 0;
                for (int j = 0, cur = i; j < size; j++,cur++) {
                    if (cur == size) { cur = 0; }
                    tankRemain = tankRemain + diff[cur];
                    //System.out.println("+" + gas[cur] + "-" + cost[cur] + "=" + tankRemain);
                    if (tankRemain < 0) { continue outFor; } // 赤字
                }
                return i;
            }
            return -1;
        }
    }
    public class SolutionV3 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) { return -1; }
            int size = gas.length;
            int[] diff = new int[size];
            for (int i = 0; i < size; i++) {
                diff[i] = gas[i] - cost[i];
            }
            outFor:
            for (int i = 0; i < size; i++) {
                //System.out.println("i=" + i);
                int tankRemain = 0;
                for (int cur = i; cur < i+size; cur++) {
                    tankRemain += diff[cur%size];
                    if (tankRemain < 0) {
                        i = cur;
                        continue outFor;
                    }
                }
                return i;
            }
            return -1;
        }
    }
    public class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) { return -1; }
            int size = gas.length;
            int[] diff = new int[size];
            int globalSum = 0;
            for (int i = 0; i < size; i++) {
                diff[i] = gas[i] - cost[i];
                globalSum += diff[i];
            }
            if (globalSum < 0) { return -1; }
            outFor:
            for (int i = 0; i < size; i++) {
                //System.out.println("i=" + i);
                int tankRemain = 0;
                for (int cur = i; cur < i+size; cur++) {
                    tankRemain += diff[cur%size];
                    if (tankRemain < 0) {
                        i = cur;
                        continue outFor;
                    }
                }
                return i;
            }
            return -1;
        }
    }
    private static GasStation test = new GasStation();
    private static void testCanCompleteCircuit() {
        Solution solution = test.new Solution();
        int[] gas = new int[]{10,8,7,11,20,5};
        int[] cost = new int[]{7,20,11,4,5,10};
        System.out.println("Answer = 3" + ",Result = " + solution.canCompleteCircuit(gas,cost));
    }
    public static void main(String[] args) {
        testCanCompleteCircuit();
    }
}
