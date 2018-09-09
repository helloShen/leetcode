/**
 * Leetcode - Algorithm - PeekingIterator
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PeekingIterator implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PeekingIterator() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution implements Iterator<Integer> {
        private int id = 0;
        abstract public void init(Iterator<Integer> ite); // 初始化
        abstract public Integer peek(); // 接口方法
        abstract public Integer next(); // 接口方法
        abstract public boolean hasNext(); // 接口方法
        // remove() is an exist default method
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private Iterator<Integer> ite = null;
        private Integer next = null;

        // implement your solution's method HERE...
        public void init(Iterator<Integer> ite) {
            this.ite = ite;
            if (this.ite.hasNext()) {
                next = this.ite.next();
            }
        }
        public Integer peek() {
            return next;
        }
        public Integer next() {
            Integer res = next;
            next = (ite.hasNext())? ite.next() : null;
            return res;
        }
        public boolean hasNext() {
            return next != null;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public void init(Iterator<Integer> ite) {

        }
        public Integer peek() {
            return 2;
        }
        public Integer next() {
            return 2;
        }
        public boolean hasNext() {
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public void init(Iterator<Integer> ite) {

        }
        public Integer peek() {
            return 3;
        }
        public Integer next() {
            return 3;
        }
        public boolean hasNext() {
            return false;
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
        private PeekingIterator problem = new PeekingIterator();
        private Solution solution = null;

        // call method in solution
        private void call(Iterator<Integer> ite) {
            solution.init(ite);
            while (solution.hasNext()) {
                System.out.println("peek() -> " + solution.peek());
                System.out.println("next() -> " + solution.next());
            }
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            List<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,0}));
            Iterator<Integer> ite1 = list1.iterator();

            /** involk call() method HERE */
            call(ite1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
