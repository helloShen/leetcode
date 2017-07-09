/**
 * Leetcode - Algorithm - Evaluate Reverse Polish Notation
 */
package com.ciaoshen.leetcode;
import java.util.*;

enum Operators {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    DEFAULT("");
    private String name;
    private Operators(String s) {
        this.name = s;
    }
}
class EvaluateReversePolishNotation {
    /**
     * 加了防御式编程
     * 注意！ String比较不要用"==", 要用equals()
     */
    public class SolutionV1 {
        public int evalRPN(String[] tokens) {
            Deque<Integer> stack = new LinkedList<>();
            for (String token : tokens) {
                try {
                    stack.offerFirst(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    if (isOperator(token) && stack.size() >= 2) {
                        int b = stack.pollFirst();
                        int a = stack.pollFirst();
                        stack.offerFirst(calculate(a,b,token));
                    } else { // 格式不对
                        return 0;
                    }
                }
            }
            int size = stack.size();
            if (size == 0 || size > 1) { return 0; } // stack必须只能有结果一个值
            return stack.pollFirst();
        }
        private int calculate(int a, int b, String token) {
            if (token.equals("+")) {
                return a + b;
            } else if (token.equals("-")) {
                return a - b;
            } else if (token.equals("*")) {
                return a * b;
            } else if (token.equals("/")) {
                return a / b;
            } else {
                return 0;
            }
        }
        private boolean isOperator(String s) {
            return (s.equals("+")) || (s.equals("-")) || (s.equals("*")) || (s.equals("/"));
        }
    }
    public class Solution {
        public int evalRPN(String[] tokens) {
            Deque<Integer> stack = new LinkedList<>();
            for (String token : tokens) {
                if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                    int b = stack.pollFirst();
                    int a = stack.pollFirst();
                    stack.offerFirst(calculate(a,b,token));
                } else {
                    stack.offerFirst(Integer.parseInt(token));
                }
            }
            return stack.pollFirst();
        }
        public int calculate(int a, int b, String token) {
            if (token.equals("+")) {
                return a + b;
            } else if (token.equals("-")) {
                return a - b;
            } else if (token.equals("*")) {
                return a * b;
            } else if (token.equals("/")) {
                return a / b;
            } else {
                return 0;
            }
        }
    }
    private static EvaluateReversePolishNotation test = new EvaluateReversePolishNotation();
    private static Solution solution = test.new Solution();
    private static void testOperatorsEnum() {
        Operators plus = Operators.PLUS;
        Operators minus = Operators.MINUS;
        Operators multiply = Operators.MULTIPLY;
        Operators divide = Operators.DIVIDE;
        System.out.println("Plus Operator: " + plus.name());
        System.out.println("Minus Operator: " + minus.name());
        System.out.println("Multiply Operator: " + multiply.name());
        System.out.println("Divide Operator: " + divide.name());
    }
    private static void testEvalRPN() {
        String[] test1 = new String[]{"2", "1", "+", "3", "*"};
        String[] test2 = new String[]{"4", "13", "5", "/", "+"};
        String[] test3 = new String[]{"3", "-4", "+"};
        System.out.println(Arrays.toString(test1) + " = " + solution.evalRPN(test1));
        System.out.println(Arrays.toString(test2) + " = " + solution.evalRPN(test2));
        System.out.println(Arrays.toString(test3) + " = " + solution.evalRPN(test3));
    }
    public static void main(String[] args) {
        testEvalRPN();
        //testOperatorsEnum();
    }
}
