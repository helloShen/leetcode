/**
 * Leetcode - Algorithm - Different way to add parentheses
 */
package com.ciaoshen.leetcode;
import java.util.*;

class DifferentWaysToAddParentheses {
    /** Divide & Conquer */
    public class Solution {
        public List<Integer> diffWaysToCompute(String input) {
            Map<String,List<Integer>> memo = new HashMap<>();
            return daq(input,memo);
        }
        private List<Integer> daq(String input, Map<String,List<Integer>> memo) {
            int len = input.length();
            // dp
            List<Integer> result = memo.get(input);
            if (result != null) { return result; }
            result = new ArrayList<>();
            // base case
            if (isDigit(input)) { result.add(Integer.parseInt(input)); memo.put(input,result); return result; }
            // recursion (divid & conquer)
            for (int i = 0; i < len; i++) {
                char c = input.charAt(i);
                if (c == '+' || c == '-' || c == '*') {
                    List<Integer> left = daq(input.substring(0,i),memo);
                    List<Integer> right = daq(input.substring(i+1,len),memo);
                    for (Integer il : left) {
                        for (Integer ir : right) {
                            switch (c) {
                                case '+': result.add(il + ir); break;
                                case '-': result.add(il - ir); break;
                                case '*': result.add(il * ir); break;
                                default: break;
                            }
                        }
                    }
                }
            }
            memo.put(input,result);
            return result;
        }
        private boolean isDigit(String s) {
            for (Character c : s.toCharArray()) {
                if (!Character.isDigit(c)) { return false; }
            }
            return true;
        }
    }
    private class Test {
        private void callDiffWaysToCompute(String input, int[] answer) {
            System.out.println("Following are all possible result of " + input + ": ");
            System.out.println(solution.diffWaysToCompute(input));
            System.out.println("Answer should be: " + Arrays.toString(answer));
        }
        private void test() {
            String input1 = "2-1-1";
            int[] answer1 = new int[]{0, 2};
            String input2 = "2*3-4*5";
            int[] answer2 = new int[]{-34, -14, -10, -10, 10};
            String input3 = "11";
            int[] answer3 = new int[]{11};
            callDiffWaysToCompute(input1,answer1);
            callDiffWaysToCompute(input2,answer2);
            callDiffWaysToCompute(input3,answer3);
        }
    }
    private static DifferentWaysToAddParentheses problem = new DifferentWaysToAddParentheses();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
