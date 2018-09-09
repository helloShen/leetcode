/**
 * Leetcode - Algorithm - BinaryWatch
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BinaryWatch implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BinaryWatch() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<String> readBinaryWatch(int num); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private boolean[] hourFlags = new boolean[4];
        private int[] hour = new int[]{8,4,2,1};
        private boolean[] minFlags = new boolean[6];
        private int[] min = new int[]{32,16,8,4,2,1};
        private Set<String> set = new HashSet<>();

        public List<String> readBinaryWatch(int num) {
            init();
            dfs(num,0,0);
            return new ArrayList<String>(set);
        }
        private void init() {
            set.clear();
        }
        /** Standard Backtracking Algorithm */
        private void dfs(int remain, int hours, int mins) {
            if (remain == 0) {
                String time = convert(hours,mins);
                if (!time.isEmpty()) { set.add(time); }
            }
            for (int i = 0; i < hourFlags.length; i++) {
                if (!hourFlags[i]) {
                    hourFlags[i] = true;
                    dfs(remain-1,hours+hour[i],mins);
                    hourFlags[i] = false;
                }
            }
            for (int i = 0; i < minFlags.length; i++) {
                if (!minFlags[i]) {
                    minFlags[i] = true;
                    dfs(remain-1,hours,mins+min[i]);
                    minFlags[i] = false;
                }
            }
        }
        /** return "" when given a not-valide time */
        private String convert(int hours, int mins) {
            if (hours > 11 || mins > 59) { return ""; }
            String time = String.valueOf(hours) + ":";
            if (mins < 10) { time += "0"; }
            return time + String.valueOf(mins);
        }
    }


    private class Solution2 extends Solution {
        { super.id = 2; }

        private Map<Integer,Set<String>> hours= new HashMap<>();
        private Map<Integer,Set<String>> mins = new HashMap<>();

        private int[] values = new int[]{1,2,4,8,16,32};
        private boolean[] hourFlags = new boolean[4];
        private boolean[] minFlags = new boolean[6];

        private void dfsHour(int remain, int sum, Set<String> set) {
            if (remain == 0 && sum < 12) { set.add(String.valueOf(sum)); }
            for (int i = 0; i < hourFlags.length; i++) {
                if (!hourFlags[i]) {
                    hourFlags[i] = true;
                    dfsHour(remain-1,sum+values[i],set);
                    hourFlags[i] = false;
                }
            }
        }
        private void dfsMin(int remain, int sum, Set<String> set) {
            if (remain == 0 && sum < 60) {
                String prefix = (sum < 10)? "0" : "";
                set.add((prefix + String.valueOf(sum)));
            }
            for (int i = 0; i < minFlags.length; i++) {
                if (!minFlags[i]) {
                    minFlags[i] = true;
                    dfsMin(remain-1,sum+values[i],set);
                    minFlags[i] = false;
                }
            }
        }
        private void init() {
            for (int i = 0; i <= hourFlags.length; i++) { // 4个标小时的灯不能都亮
                Set<String> set = new HashSet<>();
                dfsHour(i,0,set);
                hours.put(i,set);
            }
            System.out.println(hours);
            for (int i = 0; i <= minFlags.length; i++) {
                Set<String> set = new HashSet<>();
                dfsMin(i,0,set);
                mins.put(i,set);
            }
            System.out.println(mins);
        }

        { init(); }

        private String spliter = ":";

        public List<String> readBinaryWatch(int num) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 6; j++) {
                    if (i + j == num) {
                        Set<String> hourCandidates = hours.get(i);
                        Set<String> minCandidates = mins.get(j);
                        for (String hc : hourCandidates) {
                            for (String mc : minCandidates) {
                                res.add(hc + spliter + mc);
                            }
                        }
                    }
                }
            }
            return res;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<String> readBinaryWatch(int num) {
            List<String> res = new ArrayList<>();
            for (int h = 0; h < 12; h++) {
                for (int m = 0; m < 60; m++) {
                    if (Integer.bitCount(h) + Integer.bitCount(m) == num) {
                        res.add(String.format("%d:%02d",h,m));
                    }
                }
            }
            return res;
        }
    }
    // you can expand more solutions HERE if you want...
    private class Solution4 extends Solution {
        { super.id = 4; }

        private String[][] hours = {
                {"0"},
                {"1", "2", "4", "8"},
                {"3", "5", "6", "9", "10"},
                {"7", "11"}
        };
        private String[][] mins = {
            {"00"},
            {"01", "02", "04", "08", "16", "32"},
            {"03", "05", "06", "09", "10", "12", "17", "18", "20", "24", "33", "34", "36", "40", "48"},
            {"07", "11", "13", "14", "19", "21", "22", "25", "26", "28", "35", "37", "38", "41", "42", "44", "49", "50", "52", "56"},
            {"15", "23", "27", "29", "30", "39", "43", "45", "46", "51", "53", "54", "57", "58"},
            {"31", "47", "55", "59"}
        };
        private String spliter = ":";

        public List<String> readBinaryWatch(int num) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < 4; i++) {       // 不可能4个小时指示灯都亮
                for (int j = 0; j < 6; j++) {   // 不可能6个分钟指示灯都亮
                    if (i + j == num) {
                        for (String h : hours[i]) {
                            for (String m : mins[j]) {
                                res.add(h + spliter + m);
                            }
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
        private BinaryWatch problem = new BinaryWatch();
        private Solution solution = null;

        // call method in solution
        private void call(int num) {
            System.out.println(num + "\t -> \t" + solution.readBinaryWatch(num));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** test cases */
            for (int i = 0; i < 11; i++) {
                call(i);
            }
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
