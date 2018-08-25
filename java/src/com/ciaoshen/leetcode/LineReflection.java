/**
 * Leetcode - Algorithm - LineReflection
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class LineReflection implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private LineReflection() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        // 主要接口
        abstract public boolean isReflected(int[][] points);
        abstract public void testPoint();
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /**
     * 统一高度的点找中心对称轴
     * 不用容器，完全在数组上操作
     */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public boolean isReflected(int[][] points) {
            //按照高度y排序
            Arrays.sort(points,new Comparator<int[]>(){
                public int compare(int[] a, int[] b) {
                    return a[1] - b[1];
                }
            });
            //比较所有y轴相同的点的x轴位置
            Integer sum = null;
            int lo = 0, hi = 0;
            while (lo < points.length) {
                //找出所有y轴相同的点
                for (hi = lo; hi < points.length && points[lo][1] == points[hi][1]; hi++);
                //y轴相同的点按x轴排序
                Arrays.sort(points,lo,hi,new Comparator<int[]>(){
                    public int compare(int[] a, int[] b) {
                        return a[0] - b[0];
                    }
                });
                int rememberHi = hi--; //记住遍历到哪儿了
                if (sum == null) { //找到唯一可能的对称轴
                    sum = points[lo][0] + points[hi][0];
                }
                while (lo <= hi) {
                    if (points[lo++][0] + points[hi--][0] != sum) {
                        return false;
                    }
                    while (lo <= hi && points[lo][0] == points[lo-1][0]) { lo++; } //跳过重叠的点
                    while (lo <= hi && points[hi][0] == points[hi+1][0]) { lo++; } //跳过重叠的点
                }
                lo = rememberHi;
            }
            return true;
        }

        //其他解决方案依赖的接口
        public void testPoint() { }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean isReflected(int[][] points) {
            Set<Point> pointsSet = new HashSet<>();
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int[] point : points) {
                if (pointsSet.add(new Point(point[0],point[1]))) {
                    min = Math.min(min,point[0]);
                    max = Math.max(max,point[0]);
                }
            }
            int sum = min + max;
            // System.out.println("Points: " + pointsSet);
            // System.out.println("Max = " + max + ",\tMin = " + min + ",\tSum = " + sum);
            for (int[] point : points) {
                // System.out.println("寻找对称点：" + expectedReflection);
                Point expectedReflection = new Point(sum - point[0],point[1]);
                if (!pointsSet.contains(expectedReflection)) {
                    // System.out.println("对称点：" + expectedReflection + "没找到！");
                    // System.out.println("Points: " + pointsSet);
                    return false;
                }
                // System.out.println("对称点：" + expectedReflection + "找到了！");
            }
            return true;
        }
        private class Point {
            private int x;
            private int y;
            private Point() {
                x = 0;
                y = 0;
            }
            private Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
            @Override
            public boolean equals(Object obj) {
                Point anotherPoint = (Point)obj;
                // System.out.println("anotherPoint.x = " + anotherPoint.x);
                // System.out.println("anotherPoint.y = " + anotherPoint.y);
                // System.out.println("this.x = " + this.x);
                // System.out.println("this.y = " + this.y);
                return (anotherPoint.x == x) && (anotherPoint.y == y);
            }
            @Override
            public int hashCode() { //谨慎使用惰性更新，如果对象不是不可变的
                return x * 31 + y;
            }
            @Override
            public String toString() {
                return "[" + x + "," + y + "]";
            }
        }
        public void testPoint() {
            Point p1 = new Point(3,4);
            Point p2 = new Point(3,4);
            System.out.println(p1.equals(p2));
            Set<Point> pointsSet = new HashSet<>();
            pointsSet.add(p1);
            pointsSet.add(p2);
            System.out.println("PointsSet = " + pointsSet);
            System.out.println("PointsSet contains Point[" + p1 + "]? \t" + pointsSet.contains(p1));
            System.out.println("PointsSet contains Point[" + p2 + "]? \t" + pointsSet.contains(p2));
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean isReflected(int[][] points) {
            return false;
        }
        public void testPoint() { }
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
        private LineReflection problem = new LineReflection();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] points, boolean answer) {
            System.out.println("For points: ");
            Matrix.print(points);
            System.out.println(solution.isReflected(points) + "\t[answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] points1 = new int[][]{{1,1},{-1,1}};
            boolean answer1 = true;
            int[][] points2 = new int[][]{{1,1},{-1,-1}};
            boolean answer2 = false;
            int[][] points3 = new int[][]{{0,0},{1,0},{3,0}};
            boolean answer3 = false;
            int[][] points4 = new int[][]{{0,0}};
            boolean answer4 = true;
            int[][] points5 = new int[][]{{16,0},{16,0},{-16,0}}; //重复的点算作同一点
            boolean answer5 = true;


            /** involk call() method HERE */
            call(points1,answer1);
            call(points2,answer2);
            call(points3,answer3);
            call(points4,answer4);
            call(points5,answer5);
        }
        public void testPoint(int id) {
            Solution solution = problem.solution(id);
            solution.testPoint();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
        // test.testPoint(2);
    }
}
