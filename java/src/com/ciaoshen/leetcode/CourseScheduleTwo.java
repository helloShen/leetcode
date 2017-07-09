/**
 * Leetcode - Algorithm - Course Schedule Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class CourseScheduleTwo {
    /**
     * BFS Topological Sort
     */
    public class SolutionV1 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // build degree table, and the prerequisites map
            Map<Integer,List<Integer>> edges = new HashMap<>();
            for (int i = 0; i < numCourses; i++) {
                edges.put(i,new ArrayList<Integer>());
            }
            int[] degree = new int[numCourses];
            for (int[] edge : prerequisites) {
                degree[edge[0]]++;
                edges.get(edge[1]).add(edge[0]);
            }
            // System.out.println("Edge is: " + edges);
            // iteration from courses with 0 degree
            Queue<Integer> zeroDegree = new LinkedList<>();
            // System.out.println("zeroDegree = " + zeroDegree);
            int[] ret = new int[numCourses];
            int cur = 0;
            int count = 0;
            do {
                int size = zeroDegree.size();
                // System.out.println("Size = " + size);
                for (int i = 0; i < size; i++) {
                    List<Integer> upperCourses = edges.remove(zeroDegree.poll());
                    for (int upper : upperCourses) {
                        degree[upper]--;
                    }
                    // System.out.println("After poll() method, zeroDegree = " + zeroDegree);
                }
                for (int i = 0; i < numCourses; i++) {
                    if (degree[i] == 0) {
                        zeroDegree.offer(i);
                        ret[cur++] = i;
                        count++;
                        degree[i] = -1;
                    }
                }
                // System.out.println("zeroDegree = " + zeroDegree);
            } while (!zeroDegree.isEmpty());
            return (count == numCourses)? ret : new int[0];
        }
    }
    /**
     * DFS Topological Sort - method 1 - calculate depth
     */
    public class SolutionV2 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // Reduce the edges list into a Map. The map should not contains null entry
            Map<Integer,List<Integer>> edges = new HashMap<>();
            for (int i = 0; i < numCourses; i++) {
                edges.put(i,new ArrayList<Integer>());
            }
            for (int[] edge : prerequisites) {
                edges.get(edge[0]).add(edge[1]);
            }
            int[] depth = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                int d = dfs(i,edges,new boolean[numCourses],depth); // need a new log array each time
                if (d == 0) { return new int[0]; } // find circle
            }
            // reduce depth into a Map
            Map<Integer,List<Integer>> depthMap = new HashMap<>();
            for (int i = 1; i <= numCourses; i++) {
                depthMap.put(i,new ArrayList<Integer>());
            }
            for (int i = 0; i < numCourses; i++) {
                depthMap.get(depth[i]).add(i);
            }
            int[] ret = new int[numCourses];
            int cur = 0;
            for (int i = 1; i <= numCourses; i++) {
                List<Integer> level = depthMap.remove(i);
                for (int course : level) {
                    ret[cur++] = course;
                }
            }
            return ret;
        }
        /**
         * return the depth of the course
         * return 1 if this course has no prerequisites
         * return 0 if find circle
         */
        public int dfs(int course, Map<Integer,List<Integer>> edges, boolean[] log, int[] depth) {
            // find circle? kill!
            if (log[course] == true) { return 0; }
            // solved problem? merge!
            if (depth[course] > 0) { return depth[course]; }
            // dfs backtracking recursion
            List<Integer> pres = edges.remove(course);
            int courseDepth = 1;
            log[course] = true;
            for (int pre : pres) {
                int preDepth = dfs(pre,edges,log,depth);
                if (preDepth == 0) {
                    return 0;
                } else {
                    courseDepth = Math.max(courseDepth,preDepth+1);
                }
            }
            log[course] = false;
            depth[course] = courseDepth;
            return courseDepth;
        }
    }
    /**
     * DFS Topological Sort - post-order
     */
    public class SolutionV3 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // Reduce the edges list into a Map. The map should not contains null entry
            Map<Integer,List<Integer>> edges = new HashMap<>();
            for (int i = 0; i < numCourses; i++) {
                edges.put(i,new ArrayList<Integer>());
            }
            for (int[] edge : prerequisites) {
                edges.get(edge[0]).add(edge[1]);
            }
            int[] ret = new int[numCourses];
            int[] offset = new int[1];
            boolean[] finished = new boolean[numCourses];
            for (int i = 0; i < numCourses; i++) {
                boolean findCircle = dfs(i,edges,new boolean[numCourses],finished,ret,offset); // need a new log array each time
                if (findCircle) { return new int[0]; }
            }
            return ret;
        }
        /**
         * return true if find circle, other wise return false
         */
        public boolean dfs(int course, Map<Integer,List<Integer>> edges, boolean[] log, boolean[] finished, int[] ret, int[] offset) {
            // find circle? kill!
            if (log[course] == true) { return true; }
            // solved problem? kill!
            if (finished[course]) { return false; }
            // dfs backtracking recursion
            List<Integer> pres = edges.remove(course);
            log[course] = true;
            for (int pre : pres) {
                if (dfs(pre,edges,log,finished,ret,offset)) { return true; } // find circle
            }
            log[course] = false;
            ret[offset[0]++] = course;
            finished[course] = true;
            return false;
        }
    }
    /**
     * DFS Topological Sort - post-order - 用List装结果
     */
    public class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // Reduce the edges list into a Map. The map should not contains null entry
            Map<Integer,List<Integer>> edges = new HashMap<>();
            for (int i = 0; i < numCourses; i++) {
                edges.put(i,new ArrayList<Integer>());
            }
            for (int[] edge : prerequisites) {
                edges.get(edge[0]).add(edge[1]);
            }
            boolean[] finished = new boolean[numCourses];
            List<Integer> postOrder = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                boolean findCircle = dfs(i,edges,new boolean[numCourses],finished,postOrder); // need a new log array each time
                if (findCircle) { return new int[0]; }
            }
            int[] ret = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                ret[i] = postOrder.get(i);
            }
            return ret;
        }
        /**
         * return true if find circle, other wise return false
         */
        public boolean dfs(int course, Map<Integer,List<Integer>> edges, boolean[] log, boolean[] finished, List<Integer> postOrder) {
            // find circle? kill!
            if (log[course] == true) { return true; }
            // solved problem? kill!
            if (finished[course]) { return false; }
            // dfs backtracking recursion
            List<Integer> pres = edges.remove(course);
            log[course] = true;
            for (int pre : pres) {
                if (dfs(pre,edges,log,finished,postOrder)) { return true; } // find circle
            }
            log[course] = false;
            postOrder.add(course); // post-order topological sort
            finished[course] = true;
            return false;
        }
    }
    private static CourseScheduleTwo test = new CourseScheduleTwo();
    private static Solution solution = test.new Solution();
    private static void callFindOrder(int numCourses, int[][] prerequisites) {
        System.out.println("Schedule list for " + numCourses + " courses: ");
        System.out.println(prerequisitesToString(prerequisites));
        System.out.println("A possible order to finish the courses is: " + Arrays.toString(solution.findOrder(numCourses,prerequisites))+"\n\n");
    }
    private static void test() {
        int numCourses1 = 4;
        int[][] prerequisites1 = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        int numCourses2 = 2;
        int[][] prerequisites2 = new int[][]{{0,1},{1,0}};
        // int numCourses3 = 4;
        // int[][] prerequisites3 = new int[][]{};
        callFindOrder(numCourses1,prerequisites1);
        callFindOrder(numCourses2,prerequisites2);
        // callFindOrder(numCourses3,prerequisites3);
    }
    private static String prerequisitesToString(int[][] prerequisites) {
        StringBuilder sb = new StringBuilder();
        for (int[] edge : prerequisites) {
            sb.append("[" + edge[0] + " requires " + edge[1] + "]\n");
        }
        return sb.toString();
    }
    private static void testPrerequisitesToString() {
        System.out.println(prerequisitesToString(new int[][]{{1,0},{2,0},{3,1},{3,2}}));
    }
    public static void main(String[] args) {
        // testPrerequisitesToString();
        test();
    }
}
