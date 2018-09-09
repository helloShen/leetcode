/**
 * Leetcode - Algorithm - FactorCombinations
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FactorCombinations implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FactorCombinations() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<List<Integer>> getFactors(int n);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private Map<Integer,Set<List<Integer>>> memo = new HashMap<>();

        public List<List<Integer>> getFactors(int n) {
            Set<List<Integer>> result = memo.get(n);
            if (result != null) {
                ArrayList<List<Integer>> resultList = new ArrayList<>();
                resultList.addAll(result);
                return resultList;
            }
            result = new HashSet<>();
            for (int i = 2; i <= (int)Math.sqrt(n); i++) { // the other half are duplicates
                if ((n % i) == 0) {
                    int quotien = n / i;
                    Integer[] nums = new Integer[]{i,quotien};
                    Arrays.sort(nums);
                    result.add(new ArrayList<Integer>(Arrays.asList(nums)));
                    // System.out.println("Add " + Arrays.asList(nums) + " to result, because " + i + " * " + quotien + " = " + n);
                    List<List<Integer>> sublist = getFactors(quotien);
                    for (List<Integer> factors : sublist) {
                        List<Integer> localFactors = new ArrayList<>(factors);
                        localFactors.add(i);
                        Collections.sort(localFactors);
                        result.add(localFactors);
                    }
                }
            }
            memo.put(n,result);
            ArrayList<List<Integer>> resultList = new ArrayList<>();
            resultList.addAll(result);
            return resultList;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public List<List<Integer>> getFactors(int n) {
            List<List<Integer>> result = new ArrayList<>();
            backtracking(n,2,new ArrayList<Integer>(),result);
            return result;
        }
        public void backtracking(int n, int start, List<Integer> path, List<List<Integer>> result) {
            if (n < 2) {
                if (!path.isEmpty()) { result.add(new ArrayList<Integer>(path)); } return;
            }
            for (int i = start; i <= (int)Math.sqrt(n); i++) {
                if ((n % i) == 0) {
                    int quotien = n / i;
                    path.add(i);
                    backtracking(quotien,i,path,result);
                    path.add(quotien);
                    result.add(new ArrayList<>(path));
                    path.remove(path.size()-1);
                    path.remove(path.size()-1);
                }
            }
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<List<Integer>> getFactors(int n) {
            return null;
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
        private FactorCombinations problem = new FactorCombinations();
        private Solution solution = null;

        // call method in solution
        private void call(int n,List<List<Integer>> answer) {
            System.out.println("Factor combinations of number " + n + " is " + solution.getFactors(n));
            System.out.println("Answer should be: " + answer);
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** TEST CASES */
            // 1
            List<List<Integer>> answer1 = new ArrayList<>();
            call(1,answer1);
            // 2
            List<List<Integer>> answer2 = new ArrayList<>();
            call(37,answer2);
            // 3
            List<List<Integer>> answer3 = new ArrayList<>();
            answer3.add(new ArrayList<>(Arrays.asList(new Integer[]{2,6})));
            answer3.add(new ArrayList<>(Arrays.asList(new Integer[]{2,2,3})));
            answer3.add(new ArrayList<>(Arrays.asList(new Integer[]{3,4})));
            call(12,answer3);
            // 4
            List<List<Integer>> answer4 = new ArrayList<>();
            answer4.add(new ArrayList<>(Arrays.asList(new Integer[]{2,16})));
            answer4.add(new ArrayList<>(Arrays.asList(new Integer[]{2,2,8})));
            answer4.add(new ArrayList<>(Arrays.asList(new Integer[]{2,2,2,4})));
            answer4.add(new ArrayList<>(Arrays.asList(new Integer[]{2,2,2,2,2})));
            answer4.add(new ArrayList<>(Arrays.asList(new Integer[]{2,4,4})));
            answer4.add(new ArrayList<>(Arrays.asList(new Integer[]{4,8})));
            call(32,answer4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
    }
}
