/**
 * Leetcode - Algorithm - QueueReconstructionByHeight
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class QueueReconstructionByHeight implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private QueueReconstructionByHeight() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[][] reconstructQueue(int[][] people); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        private int[][] local = new int[0][0];
        // implement your solution's method HERE...
        public int[][] reconstructQueue(int[][] people) {
            return people;
        }

        /* sort by second value: order */
        private void sortByOrder(int lo, int hi) {
            if (lo >= hi) { return; }
            int[] border = partitionOrder(lo,hi);
            sortByOrder(lo,border[0]);
            sortByOrder(border[1],hi);
        }
        private int[] partitionOrder(int lo, int hi) {
            int pivot = local[hi][1];
            exch(lo,hi);
            int st = lo, gt = hi;
            int cur = lo + 1;
            while (cur <= gt) {
                int order = local[cur][1];
                if (order < pivot) {
                    exch(st++,cur++);
                } else if (order > pivot){
                    exch(cur,gt--);
                } else {
                    cur++;
                }
            }
            return new int[]{st-1,gt+1};
        }
        /* sort by second value: order */
        private void sortByHeight(int lo, int hi) {
            if (lo >= hi) { return; }
            int[] border = partitionHeight(lo,hi);
            sortByHeight(lo,border[0]);
            sortByHeight(border[1],hi);
        }
        private int[] partitionHeight(int lo, int hi) {
            int pivot = local[hi][0];
            exch(lo,hi);
            int st = lo, gt = hi;
            int cur = lo + 1;
            while (cur <= gt) {
                int height = local[cur][0];
                if (height < pivot) {
                    exch(st++,cur++);
                } else if (height > pivot){
                    exch(cur,gt--);
                } else {
                    cur++;
                }
            }
            return new int[]{st-1,gt+1};
        }
        private void exch(int x, int y) {
            int tempHeight = local[y][0];
            int tempOrder = local[y][1];
            local[y][0] = local[x][0];
            local[y][1] = local[x][1];
            local[x][0] = tempHeight;
            local[x][1] = tempOrder;
        }
        private Random r = new Random();
        private final int MAX = 100;
        private int[][] random(int size) {
            int[][] peoples = new int[size][2];
            for (int i = 0; i < size; i++) {
                int height = r.nextInt(MAX)+1;
                peoples[i][0] = height;
                int order = 0;
                for (int j = 0; j < i; j++) { if (peoples[j][0] >= height) { ++order; } }
                peoples[i][1] = order;
            }
            return peoples;
        }
        private void shuffle() {
            int len = local.length;
            int pos1 = 0, pos2 = 0;
            for (int i = 0; i <= len * 10; i++) {
                pos1 = r.nextInt(len);
                pos2 = r.nextInt(len);
                exch(pos1,pos2);
            }
        }
        protected void sometest() {
            int size = 10;
            local = random(size);
            System.out.println("Original People Queue: ");
            Matrix.print(local);
            System.out.println("Shuffled People Queue: ");
            shuffle();
            Matrix.print(local);
            System.out.println("Sorted by Height: ");
            sortByHeight(0,local.length-1);
            Matrix.print(local);
            System.out.println("Sorted by Order: ");
            sortByOrder(0,local.length-1);
            Matrix.print(local);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int[][] reconstructQueue(int[][] people) {
            peopleList.clear();
            peopleArray = new int[people.length][2];
            cur = 0;
            for (int[] p : people) { peopleList.add(new People(p[0],p[1])); }
            return backtracking();
        }
        private class People {
            private int height;
            private int order;
            private People(int height, int order) {
                this.height = height;
                this.order = order;
            }
        }
        private List<People> peopleList = new ArrayList<>();
        private int[][] peopleArray = new int[0][0];
        private int cur = 0;

        private int[][] backtracking() {
            if (peopleList.isEmpty()) {
                return isValide(peopleArray)? peopleArray : null;
            }
            for (int i = 0; i < peopleList.size(); i++) {
                People p = peopleList.remove(i);
                peopleArray[cur][0] = p.height;
                peopleArray[cur][1] = p.order;
                cur++;
                int[][] res = backtracking();
                if (res != null) { return res; }
                peopleList.add(i,p);
                cur--;
            }
            return null;
        }
        private boolean isValide(int[][] people) {
            for (int i = 0; i < people.length; i++) {
                int height = people[i][0];
                int count = 0;
                for (int j = 0; j < i; j++) {
                    if (people[j][0] >= height) { ++count; }
                }
                if (count != people[i][1]) { return false; }
            }
            return true;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        private int[][] local = new int[0][0];
        // implement your solution's method HERE...
        public int[][] reconstructQueue(int[][] people) {
            if (people.length == 0) { return people; }
            local = people;
            sort(); // 先按Height升序排序。然后在身高相同的人之间，按Order降序排列
            int[][] res = new int[local.length][2];
            for (int i = local.length - 1; i >= 0; i--) { // 从右往左遍历数组，依次插入元素
                int height = local[i][0];
                int order = local[i][1];
                insert(res, order, height, order);
            }
            return res;
        }
        /* 先按Height升序排序。然后在身高相同的人之间，按Order降序排列 */
        private void sort() {
            sortByHeight(0,local.length-1); // 按Height升序排序
            // System.out.println("After Sort by Height: ");
            // Matrix.print(local);
            int cur = 0;
            while (cur < local.length) { // 对所有身高相同的人按Order降序排列
                int height = local[cur][0];
                int fast = cur;
                while (fast < local.length && local[fast][0] == height) { fast++; }
                sortByOrder(cur,fast-1);
                cur = fast;
            }
            // System.out.println("After Sort by Order: ");
            // Matrix.print(local);
        }
        /* sort by second value: order(逆序,从大到小) */
        private void sortByOrder(int lo, int hi) {
            if (lo >= hi) { return; }
            int[] border = partitionOrder(lo,hi);
            sortByOrder(lo,border[0]);
            sortByOrder(border[1],hi);
        }
        private int[] partitionOrder(int lo, int hi) {
            int pivot = local[hi][1];
            exch(lo,hi);
            int st = lo, gt = hi;
            int cur = lo + 1;
            while (cur <= gt) {
                int order = local[cur][1];
                if (order > pivot) {
                    exch(st++,cur++);
                } else if (order < pivot){
                    exch(cur,gt--);
                } else {
                    cur++;
                }
            }
            return new int[]{st-1,gt+1};
        }
        /* sort by second value: order */
        private void sortByHeight(int lo, int hi) {
            if (lo >= hi) { return; }
            int[] border = partitionHeight(lo,hi);
            sortByHeight(lo,border[0]);
            sortByHeight(border[1],hi);
        }
        private int[] partitionHeight(int lo, int hi) {
            int pivot = local[hi][0];
            exch(lo,hi);
            int st = lo, gt = hi;
            int cur = lo + 1;
            while (cur <= gt) {
                int height = local[cur][0];
                if (height < pivot) {
                    exch(st++,cur++);
                } else if (height > pivot){
                    exch(cur,gt--);
                } else {
                    cur++;
                }
            }
            return new int[]{st-1,gt+1};
        }
        private void insert(int[][] res, int index, int height, int order) {
            System.out.println("Each Step: insert [" + height + "," + order + "] to position" + index);
            for (int i = index; i < res.length; i++) {
                int tempHeight = res[i][0];
                int tempOrder = res[i][1];
                res[i][0] = height;
                res[i][1] = order;
                if (tempHeight == 0) { break; }
                height = tempHeight;
                order = tempOrder;
            }
            Matrix.print(res);
        }
        private void exch(int x, int y) {
            int tempHeight = local[y][0];
            int tempOrder = local[y][1];
            local[y][0] = local[x][0];
            local[y][1] = local[x][1];
            local[x][0] = tempHeight;
            local[x][1] = tempOrder;
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
        private QueueReconstructionByHeight problem = new QueueReconstructionByHeight();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] people, int[][] answer) {
            System.out.println("Original Queue: \t");
            Matrix.print(people);

            System.out.println("\nReconstructed Queue: \t");
            Matrix.print(solution.reconstructQueue(people));

            System.out.println("\nAnswer is: \t");
            Matrix.print(answer);

            System.out.println("\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] people1 = new int[][]{{0,0}};
            int[][] answer1 = new int[][]{{0,0}};
            int[][] people2 = new int[][]{{1,0}};
            int[][] answer2 = new int[][]{{1,0}};
            int[][] people3 = new int[][]{{5,2},{4,4},{6,1},{7,1},{5,0},{7,0}};
            int[][] answer3 = new int[][]{{5,0},{7,0},{5,2},{6,1},{4,4},{7,1}};
            int[][] people4 = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
            int[][] answer4 = new int[][]{{5,0},{7,0},{5,2},{6,1},{4,4},{7,1}};
            int[][] people5 = new int[][]{{9,0},{7,0},{1,9},{3,0},{2,7},{5,3},{6,0},{3,4},{6,2},{5,2}};
            int[][] answer5 = new int[][]{{3,0},{6,0},{7,0},{5,2},{3,4},{5,3},{6,2},{2,7},{9,0},{1,9}};
            /** involk call() method HERE */
            // call(people1,answer1);
            // call(people2,answer2);
            // call(people3,answer3);
            // call(people4,answer4);
            call(people5,answer5);
            // solution.sometest();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
