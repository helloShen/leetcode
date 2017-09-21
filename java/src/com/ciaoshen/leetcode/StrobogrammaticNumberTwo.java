/**
 * Leetcode - Algorithm - StrobogrammaticNumberTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class StrobogrammaticNumberTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private StrobogrammaticNumberTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<String> findStrobogrammatic(int n);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        private String[] head = new String[]{"11","88","69","96"};
        private String[] normal = new String[]{"00","11","88","69","96"};
        private String[] oddMid = new String[]{"0","1","8"};
        public List<String> findStrobogrammatic(int n) {
            List<String> result = new ArrayList<>();
            if (n <= 0) { return result; }
            boolean isOdd = ((n % 2) != 0);
            int half = (n - 1) / 2;
            backtracking(0,half,isOdd,"",result);
            return result;
        }
        public void backtracking(int pos, int half, boolean isOdd, String num, List<String> result) {
            String[] candidates = normal;
            if ((pos == half) && isOdd) {
                candidates = oddMid;
            } else if (pos == 0) {
                candidates = head;
            }
            for (String c : candidates) {
                String newStr = num.substring(0,pos) + c + num.substring(pos);
                if (pos == half) {
                    result.add(newStr);
                } else {
                    backtracking(pos+1,half,isOdd,newStr,result);
                }
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public List<String> findStrobogrammatic(int n) {
            return recursion(n,n);
        }
        public List<String> recursion(int size, int maxSize) {
            // base case (don't use ArrayList, because remove() of ArrayList is expansive)
            if (size == 0) { return new LinkedList<String>(Arrays.asList(new String[]{""})); }
            if (size == 1) { return new LinkedList<String>(Arrays.asList(new String[]{"0","1","8"})); }
            // recursion
            List<String> cores = recursion(size-2,maxSize);
            int coreLen = cores.size();
            for (int i = 0; i < coreLen; i++) {
                String core = cores.remove(0);
                if (size != maxSize) { cores.add("0" + core + "0"); }
                cores.add("1" + core + "1");
                cores.add("8" + core + "8");
                cores.add("6" + core + "9");
                cores.add("9" + core + "6");
            }
            return cores;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<String> findStrobogrammatic(int n) {
            List<String> result = new LinkedList<>(); // don't use ArrayList, because remove() of ArrayList is expansive
            int coreSize = n % 2;
            String[] core = (coreSize == 0)? new String[]{""} : new String[]{"0","1","8"};
            result.addAll(Arrays.asList(core));
            for (int size = coreSize+2; size <= n; size+=2) {
                int len = result.size();
                for (int i = 0; i < len; i++) {
                    String str = result.remove(0);
                    if (size != n) { result.add("0" + str + "0"); }
                    result.add("1" + str + "1");
                    result.add("8" + str + "8");
                    result.add("6" + str + "9");
                    result.add("9" + str + "6");
                }
            }
            return result;
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
        private StrobogrammaticNumberTwo problem = new StrobogrammaticNumberTwo();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            System.out.println("Strobogrammatic Numbers of length " + n + " are: " + solution.findStrobogrammatic(n));
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            call(1);
            call(2);
            call(3);
            call(4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        test.test(3);
    }
}
