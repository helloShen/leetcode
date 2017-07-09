/**
 * Leetcode - Algorithm - Basic Calculator Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class BasicCalculatorTwo {
    public class SolutionV1 {
        public int calculate(String s) {
            char[] chars = s.toCharArray();
            Deque<Long> stack = new LinkedList<>();
            int cur = 0;
            boolean isNegative = false, needMultiply = false, needDivide = false;
            while (cur < chars.length) {
                switch (chars[cur]) {
                    case ' ': cur++; break;
                    case '*': needMultiply = true; cur++; break;
                    case '/': needDivide = true; cur++; break;
                    case '+': cur++; break;
                    case '-': isNegative = true; cur++; break;
                    default: // [0-9]
                        int sign = 1;
                        if (isNegative) { sign = -1; isNegative = false; }
                        int[] pair = parseNum(chars,cur);
                        if (needMultiply) {
                            stack.push(stack.pop() * pair[0]);
                            needMultiply = false;
                        } else if (needDivide) {
                            stack.push(stack.pop() / pair[0]);
                            needDivide = false;
                        } else {
                            stack.push(sign * (long)pair[0]);
                        }
                        cur = pair[1];
                }
            }
            long sum = 0;
            for (long n : stack) { sum += n; }
            return (int)sum;
        }
        private int[] parseNum(char[] chars, int cur) {
            int[] result = new int[2];
            int num = 0;
            while (cur < chars.length && chars[cur] >= '0' && chars[cur] <= '9') {
                num = num * 10 + (chars[cur++] - '0');
            }
            result[0] = num; result[1] = cur;
            return result;
        }
    }
    public class Solution {
        public int calculate(String s) {
            char[] chars = s.toCharArray();
            Deque<Long> stack = new LinkedList<>();
            int cur = 0;
            char oprt = '+';
            while (cur < chars.length) {
                switch (chars[cur]) {
                    case ' ': cur++; break;
                    case '+': cur++; break;
                    case '-': oprt = '-'; cur++; break;
                    case '*': oprt = '*'; cur++; break;
                    case '/': oprt = '/'; cur++; break;
                    default: // [0-9]
                        int[] pair = parseNum(chars,cur);
                        cur = pair[1];
                        switch (oprt) {
                            case '-': stack.push(0 - (long)pair[0]); break;
                            case '*': stack.push(stack.pop() * pair[0]); break;
                            case '/': stack.push(stack.pop() / pair[0]); break;
                            default: stack.push((long)pair[0]); break; // +
                        }
                        oprt = '+';
                }
            }
            long sum = 0;
            for (long n : stack) { sum += n; }
            return (int)sum;
        }
        private int[] parseNum(char[] chars, int cur) {
            int[] result = new int[2];
            int num = 0;
            while (cur < chars.length && chars[cur] >= '0' && chars[cur] <= '9') {
                num = num * 10 + (chars[cur++] - '0');
            }
            result[0] = num; result[1] = cur;
            return result;
        }
    }
    private class Test {
        private void callCalculate(String s, String answer) {
            System.out.println(s + " = " + solution.calculate(s) + "    [answer = " + answer + "]");
        }
        private void test() {
            String s1 = "3+2*2";
            String s2 = " 3/2 ";
            String s3 = " 3+5 / 2 ";
            callCalculate(s1,"7");
            callCalculate(s2,"1");
            callCalculate(s3,"5");
        }
    }
    private static BasicCalculatorTwo problem = new BasicCalculatorTwo();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
