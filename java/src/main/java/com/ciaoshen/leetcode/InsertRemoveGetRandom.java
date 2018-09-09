/**
 * Leetcode - Algorithm - InsertRemoveGetRandom
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class InsertRemoveGetRandom implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private InsertRemoveGetRandom() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;

        abstract public boolean insert(int val); // 主接口函数
        abstract public boolean remove(int val); // 主接口函数
        abstract public int getRandom();     // 主接口函数

        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private HashMap<Integer,Integer> map;    // 键值对：[val,index]
        private List<Integer> list;              // 所有数字的列表
        private Random r;

        /** Initialize your data structure here. */
        public Solution1() {
            map = new HashMap<Integer,Integer>();
            list = new ArrayList<Integer>();
            r = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                int index = map.size();
                map.put(val,map.size());
                list.add(val);
                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            } else {
                int last = list.get(list.size()-1);
                int index = map.get(val);
                map.put(last,index);
                map.remove(val);
                list.set(index,last);
                list.remove(list.size()-1);
                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            if (list.isEmpty()) { return 0; }
            return list.get(r.nextInt(list.size()));
        }

    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean insert(int val) {
            return false;
        }
        public boolean remove(int val) {
            return false;
        }
        public int getRandom() {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        public boolean insert(int val) {
            return false;
        }
        public boolean remove(int val) {
            return false;
        }
        public int getRandom() {
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
        private InsertRemoveGetRandom problem = new InsertRemoveGetRandom();
        private Solution solution = null;

        // call method in solution
        private void call() {
            // Inserts 1 to the set. Returns true as 1 was inserted successfully.
            System.out.println(solution.insert(1) + " \t [Should be: true]");

            // Returns false as 2 does not exist in the set.
            System.out.println(solution.remove(2) + " \t [Should be: false]");

            // Inserts 2 to the set, returns true. Set now contains [1,2].
            System.out.println(solution.insert(2) + " \t [Should be: true]");

            // getRandom should return either 1 or 2 randomly.
            System.out.println(solution.getRandom() + " \t [Should be: either 1 or 2]");

            // Removes 1 from the set, returns true. Set now contains [2].
            System.out.println(solution.remove(1) + " \t [Should be: true]");

            // 2 was already in the set, so return false.
            System.out.println(solution.insert(2) + " \t [Should be: false]");

            // Since 2 is the only number in the set, getRandom always return 2.
            System.out.println(solution.getRandom() + " \t [Should be: 2]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */

            /** involk call() method HERE */
            call();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
