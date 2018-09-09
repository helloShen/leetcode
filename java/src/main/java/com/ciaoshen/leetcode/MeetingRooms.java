/**
 * Leetcode - Algorithm - MeetingRooms
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MeetingRooms implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MeetingRooms() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean canAttendMeetings(Interval[] intervals);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public boolean canAttendMeetings(Interval[] intervals) {
            if (intervals.length < 2) { return true; }
            Arrays.sort(intervals,new Comparator<Interval>() {
                public int compare(Interval i1, Interval i2) {
                    return i1.start - i2.start;
                }
            });
            System.out.println("Sorte Intervals: " + Arrays.toString(intervals));
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i-1].end > intervals[i].start) { return false; }
            }
            return true;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public boolean canAttendMeetings(Interval[] intervals) {
            if (intervals.length < 2) { return true; }
            try {
                Arrays.sort(intervals,new Comparator<Interval>() {
                    public int compare(Interval i1, Interval i2) throws RuntimeException {
                        if (i1.start < i2.start && i1.end <= i2.start) { return -1; }
                        if (i1.start > i2.start && i1.start >= i2.end) { return 1; }
                        throw new RuntimeException("Duplicate!");
                    }
                });
            } catch (RuntimeException e) {
                return false;
            }
            return true;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean canAttendMeetings(Interval[] intervals) {
            if (intervals.length < 2) { return true; }
            for (int i = 1; i < intervals.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (isConflict(intervals[i],intervals[j])) { return false; }
                }
            }
            return true;
        }
        public boolean isConflict(Interval a, Interval b) {
            boolean ibfj = (a.start < b.start) && (a.end <= b.start);
            boolean iaftj = (a.start > b.start) && (a.start >= b.end);
            return !(ibfj || iaftj);
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
        private MeetingRooms problem = new MeetingRooms();
        private Solution solution = null;

        private void call(Interval[] intervals, String answer) {
            System.out.println("For interval: " + Arrays.toString(intervals));
            System.out.println(solution.canAttendMeetings(intervals) + "       [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);
            // test case
            int[][] matrix1 = new int[][]{{0,30},{5,10},{15,20}};
            Interval[] intervals1 = Interval.intervals(matrix1);
            int[][] matrix2 = new int[][]{{5,10},{15,30},{50,100}};
            Interval[] intervals2 = Interval.intervals(matrix2);
            int[][] matrix3 = new int[][]{{1,17},{7,10},{12,14}};
            Interval[] intervals3 = Interval.intervals(matrix3);

            // call solution
            call(intervals1,"false");
            call(intervals2,"true");
            call(intervals3,"false");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        test.test(3);
    }
}
