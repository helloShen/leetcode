/**
 * Leetcode - Algorithm - DecodeString
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DecodeString implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DecodeString() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String decodeString(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public String decodeString(String s) {
            Deque<Block> stack = new LinkedList<Block>();
            int cur = 0;
            stack.offerFirst(new Block(1)); // environment
            while (cur < s.length()) {
                if (Character.isDigit(s.charAt(cur))) {
                    int end = cur + 1;
                    while (Character.isDigit(s.charAt(end))) { end++; }
                    int num = Integer.valueOf(s.substring(cur,end));
                    stack.offerFirst(new Block(num));
                    cur = end;
                } else if (s.charAt(cur) == ']') {
                    Block curr = stack.pollFirst();
                    StringBuilder atom = curr.sb;
                    StringBuilder sub = new StringBuilder();
                    for (int i = 0; i < curr.repeat; i++) {
                        sub.append(atom);
                    }
                    stack.peekFirst().sb.append(sub);
                } else {
                    stack.peekFirst().sb.append(s.charAt(cur));
                }
                cur++;
            }
            return stack.pollFirst().sb.toString();
        }
        private class Block {
            private int repeat;
            private StringBuilder sb = new StringBuilder();
            private Block(int num) { repeat = num; }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public String decodeString(String s) {
            Stack<Integer> times = new Stack<>();
            Stack<String> atoms = new Stack<>();
            int cur = 0;
            times.push(1);
            atoms.push("");
            while (cur < s.length()) {
                if (Character.isDigit(s.charAt(cur))) {
                    int end = cur + 1;
                    while (Character.isDigit(s.charAt(end))) { end++; }
                    int num = Integer.valueOf(s.substring(cur,end));
                    times.push(num);
                    atoms.push("");
                    cur = end;
                } else if (s.charAt(cur) == ']') {
                    int repeat = times.pop();
                    String currAtom = atoms.pop();
                    String sub = "";
                    for (int i = 0; i < repeat; i++) {
                        sub += currAtom;
                    }
                    atoms.push(atoms.pop() + sub);
                } else {
                    atoms.push(atoms.pop() + s.charAt(cur));
                }
                cur++;
            }
            return atoms.pop();
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public String decodeString(String s) {
            return "3";
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
        private DecodeString problem = new DecodeString();
        private Solution solution = null;

        // call method in solution
        private void call(String s, String ans) {
            System.out.println("Encoded = " + s);
            System.out.println("Decoded = " + solution.decodeString(s));
            System.out.println("Answer should be: [" + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "3[a]2[bc]";
            String ans1 = "aaabcbc";
            String s2 = "3[a2[c]]";
            String ans2 = "accaccacc";
            String s3 = "2[abc]3[cd]ef";
            String ans3 = "abcabccdcdcdef";

            /** involk call() method HERE */
            call(s1,ans1);
            call(s2,ans2);
            call(s3,ans3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
