/**
 * Leetcode - Algorithm - Flatten2DVector
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class Flatten2DVector implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private Flatten2DVector() {
        register(new Vector2DV1(new ArrayList<>()));
        register(new Vector2DV2(new ArrayList<>()));
        register(new Vector2DV3(new ArrayList<>()));
    }
    private abstract class Solution implements Iterator<Integer> {
        private int id = 0;
        abstract public Integer next();
        abstract public boolean hasNext();
    }
    /** 职责放在next()函数上 */
    private class Vector2DV1 extends Solution {
        { super.id = 1; }
        private List<List<Integer>> list;
        private int index = 0, size = 0;
        private Iterator<Integer> ite;

        public Vector2DV1(List<List<Integer>> vec2d) {
            list = vec2d;
            size = list.size();
            nextIte();
        }
        // maintains the ite, if there are no more list, return null
        private void nextIte() {
            ite = null;
            while (index < size) {
                List<Integer> curr = list.get(index++);
                if (!curr.isEmpty()) { ite = curr.iterator(); return; }
            }
        }
        // next() method should keep ite.hasNext() always be true
        // if there are no more list, ite should be null.
        public Integer next() {
            if (!hasNext()) { return null; }
            Integer result = ite.next();
            if (!ite.hasNext()) { nextIte(); }
            return result;
        }

        public boolean hasNext() {
            return ite != null;
        }
    }

    /** 维护Iterator的职责放在hasNext()上 */
    private class Vector2DV2 extends Solution {
        { super.id = 2; }

        private Iterator<List<Integer>> listIte;
        private Iterator<Integer> intIte;

        public Vector2DV2(List<List<Integer>> vec2d) {
            listIte = vec2d.iterator();
            if (listIte.hasNext()) { intIte = listIte.next().iterator(); }
        }
        /** only call intIte.next() */
        public Integer next() {
            return (hasNext())? intIte.next() : null;
        }
        /** keep intIte.hasNext() always be true */
        public boolean hasNext() {
            if (intIte == null) { return false; }
            if (intIte.hasNext()) { return true; }
            intIte = null;
            while (listIte.hasNext()) {
                intIte = listIte.next().iterator();
                if (intIte.hasNext()) { return true; }
            }
            return false;
        }
    }

    private class Vector2DV3 extends Solution {
        { super.id = 3; }

        private Iterator<List<Integer>> listIte;
        private Iterator<Integer> intIte;

        public Vector2DV3(List<List<Integer>> vec2d) {
            listIte = vec2d.iterator();
        }
        public Integer next() {
            return (hasNext())? intIte.next() : null;
        }
        public boolean hasNext() {
            while ((intIte == null || !intIte.hasNext()) && listIte.hasNext()) {
                intIte = listIte.next().iterator();
            }
            return intIte != null && intIte.hasNext();
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
        private Flatten2DVector problem = new Flatten2DVector();
        private Solution solution = null;

        // call method in solution
        private void call() {
            System.out.print("[");
            while (solution.hasNext()) {
                System.out.print(solution.next());
                if (solution.hasNext()) { System.out.print(","); }
            }
            System.out.print("]\n");
        }
        private void init(List<List<Integer>> list) {
            problem.register(problem.new Vector2DV1(list));
            problem.register(problem.new Vector2DV2(list));
            problem.register(problem.new Vector2DV3(list));
        }
        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            call();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        List<Integer> one = new ArrayList<>(Arrays.asList(new Integer[]{1,2}));
        List<Integer> two = new ArrayList<>(Arrays.asList(new Integer[]{3}));
        List<Integer> three = new ArrayList<>(Arrays.asList(new Integer[]{4,5,6}));
        List<List<Integer>> list1 = new ArrayList<>();
        list1.add(one); list1.add(two); list1.add(three);
        test.init(list1);
        test.test(1);
        test.test(2);
        test.test(3);
    }
}
