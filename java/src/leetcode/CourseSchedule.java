/**
 * Leetcode - Algorithm - Course Schedule
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.Matrix;

class CourseSchedule {
    /**
     * 用HashMap和HashSet储存图的信息
     */
    public class SolutionV1 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // create Map of schedule
            Map<Integer,List<Integer>> schedule = new HashMap<>();
            for (int[] pair : prerequisites) {
                int course = pair[0];
                List<Integer> pre = schedule.get(course);
                if (pre == null) {
                    List<Integer> preList = new ArrayList<>();
                    preList.add(pair[1]);
                    schedule.put(course,preList);
                } else {
                    pre.add(pair[1]);
                }
            }
            // System.out.println("Schedule is : " + schedule);
            Set<Integer> canFinish = new HashSet<>();
            Set<Integer> waitingList = new HashSet<>();
            for (int i = 0; i < numCourses; i++) {
                // System.out.println("Test " + i);
                if (!canFinishThisCourse(i,schedule,waitingList,canFinish)) { return false; }
            }
            // System.out.println("Can courses are: " + canFinish);
            return true;
        }
        public boolean canFinishThisCourse(int course, Map<Integer,List<Integer>> schedule, Set<Integer> waitingList, Set<Integer> canFinish) {
            if (canFinish.contains(course)) { return true; }
            if (waitingList.contains(course)) { return false; }
            List<Integer> preList = schedule.get(course);
            if (preList == null) { canFinish.add(course); return true; }
            // dfs backtracking
            waitingList.add(course);
            for (int pre : preList) {
                if (!canFinishThisCourse(pre,schedule,waitingList,canFinish)) { return false; }
            }
            waitingList.remove(course);
            canFinish.add(course); return true;
        }
    }
    /**
     * 用HashMap和HashSet储存图的信息. (改进版) 本题最优解。
     * 用完就删掉用过的edge
     */
    public class SolutionV10 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // create Map of schedule
            Map<Integer,List<Integer>> schedule = new HashMap<>();
            for (int[] pair : prerequisites) {
                int course = pair[0];
                List<Integer> pre = schedule.get(course);
                if (pre == null) {
                    List<Integer> preList = new ArrayList<>();
                    preList.add(pair[1]);
                    schedule.put(course,preList);
                } else {
                    pre.add(pair[1]);
                }
            }
            // System.out.println("Schedule is : " + schedule);
            boolean[] canFinish = new boolean[numCourses];
            boolean[] waitingList = new boolean[numCourses];
            for (int i = 0; i < numCourses; i++) {
                // System.out.println("Test " + i);
                if (!canFinishThisCourse(i,schedule,waitingList,canFinish)) { return false; }
            }
            // System.out.println("Can courses are: " + canFinish);
            return true;
        }
        public boolean canFinishThisCourse(int course, Map<Integer,List<Integer>> schedule, boolean[] waitingList, boolean[] canFinish) {
            if (canFinish[course]) { return true; }
            if (waitingList[course]) { return false; }
            List<Integer> preList = schedule.get(course);
            schedule.remove(course);
            if (preList == null) { canFinish[course] = true; return true; }
            // dfs backtracking
            waitingList[course] = true;
            for (int pre : preList) {
                if (!canFinishThisCourse(pre,schedule,waitingList,canFinish)) { return false; }
            }
            waitingList[course] = false;
            canFinish[course] = true; return true;
        }
    }
    /**
     * 尝试直接使用Edge List的图信息
     */
    public class SolutionV2 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            boolean[] canFinish = new boolean[numCourses];
            boolean[] waitingList = new boolean[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if (!canFinishThisCourse(i,prerequisites,waitingList,canFinish)) { return false; }
            }
            // System.out.println("Can finish courses are: " + canFinish);
            return true;
        }
        public boolean canFinishThisCourse(int course, int[][] prerequisites, boolean[] waitingList, boolean[] canFinish) {
            if (canFinish[course]) { return true; }
            if (waitingList[course]) { return false; } // find circle
            // dfs backtracking
            waitingList[course] = true;
            for (int[] pair : prerequisites) {
                if (pair[0] == course) {
                    if (!canFinishThisCourse(pair[1],prerequisites,waitingList,canFinish)) { return false; }
                }
            }
            waitingList[course] = false;
            canFinish[course] = true;
            return true;
        }
    }
    /**
     * 尝试把图信息转换成Matrix的形式。
     * 并且waitingList和canFinish也都用数组表示
     */
    public class SolutionV3 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            boolean[][] prerequiMatrix = new boolean[numCourses][numCourses];
            for (int[] edge : prerequisites) {
                prerequiMatrix[edge[0]][edge[1]] = true; // edge[0] require edge[1]
            }
            boolean[] canFinish = new boolean[numCourses];
            boolean[] waitingList = new boolean[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if (!canFinishThisCourse(i,prerequiMatrix,waitingList,canFinish)) { return false; }
            }
            // System.out.println("Can finish courses are: " + Arrays.toString(canFinish));
            return true;
        }
        public boolean canFinishThisCourse(int course, boolean[][] prerequiMatrix, boolean[] waitingList, boolean[] canFinish) {
            if (canFinish[course]) { return true; }
            if (waitingList[course]) { return false; }
            // dfs backtracking
            waitingList[course] = true;
            for (int i = 0; i < prerequiMatrix.length; i++) {
                if (prerequiMatrix[course][i]) { // i is the required course
                    if (!canFinishThisCourse(i,prerequiMatrix,waitingList,canFinish)){ return false; }
                }
            }
            waitingList[course] = false;
            canFinish[course] = true;
            return true;
        }
    }
    /**
     * 尝试把大数组以Solution类成员字段的形式储存，减小递归参数的负担。
     * 把图信息转换成Matrix的形式。
     * 并且waitingList和canFinish也都用数组表示
     */
    public class SolutionV4 {
        private class HelpContainer {
            private int size;
            private boolean[][] prerequiMatrix = new boolean[0][0];
            private boolean[] canFinish = new boolean[0];
            private boolean[] waitingList = new boolean[0];
            private HelpContainer() { this(0); }
            private HelpContainer(int size) {
                this.size = size;
                prerequiMatrix = new boolean[size][size];
                canFinish = new boolean[size];
                waitingList = new boolean[size];
            }
        }
        private HelpContainer container = new HelpContainer(); // create a dummy now
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            container = new HelpContainer(numCourses);
            // prepare graph matrix
            for (int[] edge : prerequisites) {
                container.prerequiMatrix[edge[0]][edge[1]] = true; // edge[0] require edge[1]
            }
            for (int i = 0; i < numCourses; i++) {
                if (container.canFinish[i]) { continue; }
                container.waitingList = new boolean[numCourses];
                if (!canFinishThisCourse(i)) { return false; }
            }
            // System.out.println("Can finish courses are: " + Arrays.toString(canFinish));
            int count = 0;
            for (int i = 0; i < numCourses; i++) {
                if (container.canFinish[i]) { count++; }
            }
            return count == numCourses;
        }
        public boolean canFinishThisCourse(int course) {
            for (int i = 0; i < container.size; i++) {
                if (container.prerequiMatrix[course][i]) { // i is the required course
                    if (!container.canFinish[i]) {
                        if (container.waitingList[i]) {  // find circle
                            return false;
                        } else { // backtracking recursion
                            container.waitingList[i] = true;
                            if (canFinishThisCourse(i)) {
                                container.canFinish[i] = true;
                            } else {
                                return false;
                            }
                            container.waitingList[i] = false;
                        }
                    }
                }
            }
            container.canFinish[course] = true;
            return true;
        }
    }
    /**
     * 尝试把大数组以Solution类成员字段的形式储存，减小递归参数的负担。
     * 不封装成类。
     * 把图信息转换成Matrix的形式。
     * 并且waitingList和canFinish也都用数组表示
     */
    public class SolutionV5 {
        private boolean[][] matrix = new boolean[0][0];
        private boolean[] canFinish = new boolean[0];
        private boolean[] waitingList = new boolean[0];

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            matrix = new boolean[numCourses][numCourses];
            // prepare graph matrix
            for (int[] edge : prerequisites) {
                matrix[edge[0]][edge[1]] = true; // edge[0] require edge[1]
            }
            canFinish = new boolean[numCourses];
            waitingList = new boolean[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if (!canFinishThisCourse(i)) { return false; }
            }
            // System.out.println("Can finish courses are: " + Arrays.toString(canFinish));
            return true;
        }
        public boolean canFinishThisCourse(int course) {
            if (canFinish[course]) { return true; }
            if (waitingList[course]) { return false; } // find circle
            // dfs backtracking
            waitingList[course] = true;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[course][i]) {
                    if (!canFinishThisCourse(i)) { return false; }
                }
            }
            waitingList[course] = false;
            canFinish[course] = true;
            return true;
        }
    }
    /**
     * Topological Search (BFS)
     * 从叶节点开始，按照深度顺序，一层层梳理
     */
    public class SolutionV6 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            int[] degree = new int[numCourses]; // 先导课数量
            for (int[] edge : prerequisites) { degree[edge[0]]++; }
            List<Integer> independent = new ArrayList<>();
            while (true) {
                for (int i = 0; i < numCourses; i++) {
                    if (degree[i] == 0) { independent.add(i); }
                }
                if (independent.isEmpty()) { break; }
                while (!independent.isEmpty()) {
                    int pre = independent.remove(0);
                    for (int [] edge : prerequisites) {
                        if (edge[1] == pre) {
                            degree[edge[0]]--;
                        }
                    }
                    degree[pre] = -1;
                }
            }
            for (int d : degree) { if (d > 0) { return false; } }
            return true;
        }
    }
    /**
     * Topological Search (BFS)  改进版
     * 从叶节点开始，按照深度顺序，一层层梳理
     * 改进是：用过的边，马上删掉。可以将search的过程复杂度降到 O(m)。m为edge的数量。
     */
    public class SolutionV7 {
        private class Edge {
            private int course;
            private int pre;
            private Edge(int c, int p) {
                course = c;
                pre = p;
            }
        }
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            int[] degree = new int[numCourses]; // 先导课数量
            List<Edge> edges = new ArrayList<>();
            for (int[] edge : prerequisites) {
                edges.add(new Edge(edge[0],edge[1]));
            }
            for (int[] edge : prerequisites) { degree[edge[0]]++; }
            List<Integer> independent = new ArrayList<>();
            while (true) {
                for (int i = 0; i < numCourses; i++) {
                    if (degree[i] == 0) { independent.add(i); }
                }
                if (independent.isEmpty()) { break; }
                while (!independent.isEmpty()) {
                    int pre = independent.remove(0);
                    Iterator<Edge> ite = edges.iterator();
                    while (ite.hasNext()) {
                        Edge edge = ite.next();
                        if (edge.pre == pre) {
                            degree[edge.course]--;
                            ite.remove();
                        }
                    }
                    degree[pre] = -1;
                }
            }
            for (int d : degree) { if (d > 0) { return false; } }
            return true;
        }
    }
    /**
     * Topological Search (BFS)  改进版
     * 从叶节点开始，按照深度顺序，一层层梳理
     * 改进是：用过的边，马上删掉。可以将search的过程复杂度降到 O(m)。m为edge的数量。
     */
    public class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // reduce edge list by pre
            // calculate degree
            Map<Integer,List<Integer>> edges = new HashMap<>();
            int[] degree = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                edges.put(i,new ArrayList<Integer>());
            }
            for (int[] edge : prerequisites) {
                edges.get(edge[1]).add(edge[0]);
                degree[edge[0]]++;
            }
            // Topological Search
            Queue<Integer> zeroDegree = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (degree[i] == 0) { zeroDegree.offer(i); }
            }
            while (!zeroDegree.isEmpty()) {
                int course = zeroDegree.poll();
                List<Integer> children = edges.remove(new Integer(course));
                for (int child : children) {
                    if (--degree[child] == 0) { zeroDegree.offer(child); }
                }
                degree[course] = -1;
            }
            for (int i = 0; i < numCourses; i++) {
                if (degree[i] > 0) { return false; }
            }
            return true;
        }
    }
    private static CourseSchedule test = new CourseSchedule();
    private static Solution solution = test.new Solution();
    private static void callCanFinish(int numCourses, int[][] prerequisites, String ant) {
        System.out.println("For Schedule: ");
        Matrix.print(prerequisites);
        System.out.println("Can we finish all " + numCourses + " courses? " + solution.canFinish(numCourses,prerequisites) + "  [answer: " + ant + "]");
    }
    private static void test() {
        int numCourses1 = 7;
        int[][] prerequisites1 = new int[][]{
            {2,1},{3,5},{4,6},{3,2},{4,3},{2,4}
        };
        int numCourses2 = 7;
        int[][] prerequisites2 = new int[][]{
            {2,1},{4,3},{5,2},{5,4},{6,0}
        };
        int numCourses3 = 2;
        int[][] prerequisites3 = new int[][]{
            {1,0}
        };
        int numCourses4 = 2;
        int[][] prerequisites4 = new int[][]{
            {1,0},{0,1}
        };
        int numCourses5 = 3;
        int[][] prerequisites5 = new int[][]{
            {0,1},
            {0,2},
            {1,2}
        };
        callCanFinish(numCourses1,prerequisites1,"false");
        callCanFinish(numCourses2,prerequisites2,"true");
        callCanFinish(numCourses3,prerequisites3,"true");
        callCanFinish(numCourses4,prerequisites4,"false");
        callCanFinish(numCourses5,prerequisites5,"true");
    }
    public static void main(String[] args) {
        test();
    }
}
