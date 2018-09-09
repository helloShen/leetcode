/**
 * Leetcode - Algorithm - FlattenNestedListIterator
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FlattenNestedListIterator implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FlattenNestedListIterator() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract Integer next();
        abstract boolean hasNext();
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution implements Iterator<Integer> {
        { super.id = 1; }
        private List<NestedInteger> nestedList;
        private Integer next;
        private Iterator<NestedInteger> currIte;
        private Deque<Iterator<NestedInteger>> stack;
        public Solution1() { /* dummy constructor*/ }
        public Solution1(List<NestedInteger> nestedList){
            this.nestedList = nestedList;
            currIte = nestedList.iterator();
            stack = new LinkedList<Iterator<NestedInteger>>();
            updateNext();
        }
        public Integer next() {
            Integer res = next;
            if (res != null) { updateNext(); }
            return res;
        }
        public boolean hasNext() {
            return next != null;
        }
        private void updateNext() {
            next = null;
            while (!stack.isEmpty() || currIte.hasNext()) {
                if (currIte.hasNext()) {
                    // System.out.println("currIte has next element!");
                    NestedInteger nextEle = currIte.next();
                    if (nextEle.isInteger()) {
                        // System.out.println("next element is Integer " + nextEle.getInteger());
                        next = nextEle.getInteger();
                        return;
                    } else {
                        // System.out.println("next element is a List<NestedInteger>!");
                        stack.offerFirst(currIte);
                        // System.out.println("currIte goes deeper!");
                        currIte = nextEle.getList().iterator();
                    }
                } else {
                    // System.out.println("currIte has no more element!");
                    currIte = stack.pollFirst();
                    // System.out.println("currIte goes up!");
                }
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        private Deque<NestedInteger> stack;
        private Integer next;
        public Solution2() { /* dummy constructor*/ }
        public Solution2(List<NestedInteger> nestedList){
            stack = new LinkedList<NestedInteger>();
            int size = nestedList.size();
            for (int i = size - 1; i >= 0; i--) {
                stack.offerFirst(nestedList.get(i));
            }
            updateNext();
        }
        public Integer next() {
            Integer res = next;
            updateNext();
            return res;
        }
        public boolean hasNext() {
            return next != null;
        }
        private void updateNext() {
            next = null;
            while (!stack.isEmpty()) {
                NestedInteger curr = stack.pollFirst();
                if (curr.isInteger()) {
                    // System.out.println("Curr is Integer: " + curr.getInteger());
                    next = curr.getInteger(); return;
                } else {
                    // System.out.println("Curr is List of NestedInteger!");
                    List<NestedInteger> currList = curr.getList();
                    int size = currList.size();
                    for (int i = size - 1; i >= 0; i--) {
                        stack.offerFirst(currList.get(i));
                    }
                }
            }
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        private List<NestedInteger> nestedList;
        public Solution3() { /* dummy constructor*/ }
        public Solution3(List<NestedInteger> nestedList){
            this.nestedList = nestedList;
        }
        public Integer next() {
            return 0;
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
        private FlattenNestedListIterator problem = new FlattenNestedListIterator();
        private Solution solution = null;

        // call method in solution
        private void call() {
            List<Integer> res = new ArrayList<>();
            while (solution.hasNext()) {
                res.add(solution.next());
            }
            System.out.println("Flattened List: " + res);
        }

        // public API of Test interface
        public void test(int id, List<NestedInteger> list) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            switch (id) {
                case 1: solution = problem.new Solution1(list); break;
                case 2: solution = problem.new Solution2(list); break;
                case 3: solution = problem.new Solution3(list); break;
                default: return;
            }

            /** involk call() method HERE */
            call();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        List<NestedInteger> list1 = new ArrayList<>();
        NestedInteger ni11 = new NestedInteger(new int[]{1,1});
        NestedInteger ni12 = new NestedInteger(2);
        NestedInteger ni13 = new NestedInteger(new int[]{1,1});
        list1.add(ni11); list1.add(ni12); list1.add(ni13);
        NestedInteger ni21 = new NestedInteger(new int[]{6});
        NestedInteger ni22 = new NestedInteger(new int[]{4});
        NestedInteger ni23 = new NestedInteger(new int[]{1});
        ni23.add(ni22);
        ni22.add(ni21);
        List<NestedInteger> list2 = ni23.getList();
        // List<NestedInteger> list3 = ArrayList<NestedInteger>();
        test.test(1,list1);
        test.test(1,list2);
        // test.test(1,list3);
        // test.test(2,list1);
        // test.test(2,list2);
        // test.test(2,list3);
    }
}
