/**
 * Leetcode - exclusive_time_of_functions
 */
package com.ciaoshen.leetcode.exclusive_time_of_functions;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public int[] exclusiveTime(int n, List<String> logs) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] timeTable = new int[n];
        Arrays.fill(timeTable, -1);
        int[] callOrder = new int[n];
        int callOrderP = 0;
        int runningId = -1; // current function
        int runAt = -1;   // current function start time
        for (String log : logs) {
            // extract log info
            int first = log.indexOf(':');
            int second = log.lastIndexOf(':');
            int id = Integer.parseInt(log.substring(0, first));
            boolean isStart = (log.substring(first + 1, second).equals("start"))? true : false;
            int time = Integer.parseInt(log.substring(second + 1, log.length()));
            System.out.println(id + ", " + ((isStart)? "start" : "end") + ", " + time);
            if (isStart) {
                // suspend current function
                if (runningId >= 0) { 
                    timeTable[runningId] += (time - runAt); // current function end at [time - 1]
                    stack.push(runningId);
                    System.out.println("function " + runningId + " end at " + (time - 1));
                }
                // run new function
                if (timeTable[id] < 0) {
                    callOrder[callOrderP++] = id;
                    timeTable[id] = 0;
                }
                runningId = id;
                runAt = time;
                System.out.println("function " + id + " run at " + runAt);
            } else {
                // kill current function
                timeTable[id] += (time - runAt + 1); // current function end at [time]
                System.out.println("function " + id + " runs " + timeTable[id] + " seconds!");
                // call back father function if any
                if (!stack.isEmpty()) {
                    runningId = stack.pop();
                    runAt = time + 1;
                } else {
                    runningId = -1;
                }
                System.out.println("function " + id + " treatment end!");
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < callOrderP; i++) {
            res[i] = timeTable[callOrder[i]];
        }
        return res;
    }

}