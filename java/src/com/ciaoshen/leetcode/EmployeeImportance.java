/**
 * Leetcode - Algorithm - EmployeeImportance
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class EmployeeImportance implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private EmployeeImportance() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private static class Employee {
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
        // constructor
        public Employee(int id, int importance, int[] sub) {
            this.id = id;
            this.importance = importance;
            subordinates = new ArrayList<Integer>();
            for (int i : sub) {
                subordinates.add(i);
            }
        }
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int getImportance(List<Employee> employees, int id); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int getImportance(List<Employee> employees, int id) {
            return dp(getMap(employees),id);
        }
        private Map<Integer,Employee> getMap(List<Employee> employees) {
            Map<Integer,Employee> map = new HashMap<>();
            for (Employee employee : employees) {
                map.put(employee.id,employee);
            }
            return map;
        }
        private int dp(Map<Integer,Employee> map, int target) {
            Employee e = map.get(target);
            int res = e.importance;
            for (Integer i : e.subordinates) {
                res += dp(map,i);
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int getImportance(List<Employee> employees, int id) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int getImportance(List<Employee> employees, int id) {
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
        private EmployeeImportance problem = new EmployeeImportance();
        private Solution solution = null;

        // call method in solution
        private void call(List<Employee> employees, int id) {
            System.out.println("Importance of #" + id + " = " + solution.getImportance(employees,id) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            List<Employee> employees1 = new ArrayList<>();
            employees1.add(new Employee(1,5,new int[]{2,3}));
            employees1.add(new Employee(2,3,new int[0]));
            employees1.add(new Employee(3,3,new int[0]));
            int id1 = 1;

            /** involk call() method HERE */
            call(employees1,id1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
