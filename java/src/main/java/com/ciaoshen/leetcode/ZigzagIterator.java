/**
 * Leetcode - Algorithm - ZigzagIterator
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ZigzagIterator implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ZigzagIterator() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract void init(List<Integer> list1, List<Integer> list2); // 承担构造器的责任
        abstract public int next(); // 接口方法
        abstract public boolean hasNext(); // 接口方法
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private Iterator<Integer> ite1 = null, ite2 = null;
        private boolean turnIte1 = false;

        public void init(List<Integer> list1, List<Integer> list2) {
            ite1 = list1.iterator();
            ite2 = list2.iterator();
            turnIte1 = true;
        }
        public boolean hasNext() {
            return ite1.hasNext() || ite2.hasNext();
        }
        public int next() {
            Iterator<Integer> localIte = (turnIte1)? ite1 : ite2;
            if (hasNext()) {
                if ((localIte == ite1) && (!ite1.hasNext())) { localIte = ite2; }
                if ((localIte == ite2) && (!ite2.hasNext())) { localIte = ite1; }
            }
            turnIte1 = !turnIte1;
            return localIte.next();
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private List<Iterator<Integer>> ites = new ArrayList<>();

        public void init(List<Integer> list1, List<Integer> list2) {
            Iterator<Integer> ite = list1.iterator();
            if (ite.hasNext()) { ites.add(ite); }
            ite = list2.iterator();
            if (ite.hasNext()) { ites.add(ite); }
        }
        public boolean hasNext() {
            return !ites.isEmpty();
        }
        public int next() {
            if (hasNext()) {
                Iterator<Integer> localIte = ites.remove(0);
                Integer res = localIte.next();
                if (localIte.hasNext()) {
                    ites.add(ites.size(),localIte);
                }
                return res;
            }
            return 0;
        }

    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void init(List<Integer> list1, List<Integer> list2) {

        }
        public boolean hasNext() {
            return false;
        }
        public int next() {
            return 3;
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
        private ZigzagIterator problem = new ZigzagIterator();
        private Solution solution = null;

        // call method in solution
        private void call(List<Integer> list1, List<Integer> list2, List<Integer> ans) {
            System.out.println(list1);
            System.out.println(list2);
            solution.init(list1,list2);
            System.out.print("[");
            while (solution.hasNext()) {
                System.out.print(solution.next());
                if (solution.hasNext()) { System.out.print(","); }
            }
            System.out.print("]\n");
            System.out.println("Answer should be: " + ans);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            List<Integer> list11 = new ArrayList<>(Arrays.asList(new Integer[]{1,2}));
            List<Integer> list12 = new ArrayList<>(Arrays.asList(new Integer[]{3,4,5,6}));
            List<Integer> ans1 = new ArrayList<>(Arrays.asList(new Integer[]{1,3,2,4,5,6}));

            List<Integer> list21 = new ArrayList<>(Arrays.asList(new Integer[0]));
            List<Integer> list22 = new ArrayList<>(Arrays.asList(new Integer[0]));
            List<Integer> ans2 = new ArrayList<>(Arrays.asList(new Integer[0]));

            /** involk call() method HERE */
            call(list11,list12,ans1);
            call(list21,list22,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
