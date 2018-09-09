/**
 * Leetcode - Algorithm - VerifyPreorderSerializationOfABinaryTree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class VerifyPreorderSerializationOfABinaryTree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private VerifyPreorderSerializationOfABinaryTree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean isValidSerialization(String preorder); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        /**
         * 利用一个Stack给树剪枝
         * 如果是一棵合法的二叉树：
         * 则当遍历完数组，Stack正好被剪空（只留一个为空的根节点）
         * Stack提前被剪空也不行
         */
        public boolean isValidSerialization(String preorder) {
            if (preorder == null || preorder.length() == 0) { return false; }
            //两个##抵消父节点
            Deque<Character> stack = new LinkedList<>();
            int p = 0;
            while (p < preorder.length()) {
                char c = preorder.charAt(p);
                System.out.println("Push node [" + c + "]");
                stack.push(c);
                if (c == '#') {
                    System.out.println("Call check()");
                    offset(stack);
                }
                // 找到下一个逗号","
                while (p < preorder.length() && preorder.charAt(p) != ',') { p++; }
                p++;
                //如果根节点也被抵消了，就结束
                if (stack.size() == 1 && stack.peek() == '#') { break; }
            }
            System.out.println("Finish, stack = " + stack);
            return p == preorder.length()+1 && stack.size() == 1 && stack.peek() == '#';
        }
        /**
         * 模拟树的剪枝：如果某节点左右两个节点都为空，说明这棵子树都探过了
         * 就把整棵子树都剪掉（删除左右两个空节点，再把本节点设为空）
         * 如果头两个元素是'#'，就删掉头3个元素，然后再压入一个'#'
         */
        private void offset(Deque<Character> stack) {
            if (stack == null || stack.size() < 3) { return; }
            Character first = stack.pop();
            Character second = stack.pop();
            System.out.println("first = " + first + ", second = " + second);
            if (first == '#' && second == '#') {
                Character c = stack.pop();
                System.out.println("Subtree [" + c + "] killed!");
                stack.push('#');
                offset(stack);
            } else {
                stack.push(second);
                stack.push(first);
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        /**
         * 优化Solution1，用一个char[]数组模拟Stack
         * 利用一个Stack给树剪枝
         * 如果是一棵合法的二叉树：
         * 则当遍历完数组，Stack正好被剪空（只留一个为空的根节点）
         * Stack提前被剪空也不行
         */
        //模拟Stack的数组
        private char[] stack;
        private int stackP;
        public boolean isValidSerialization(String preorder) {
            if (preorder == null || preorder.length() == 0) { return false; }
            //数组模拟Stack
            stack = new char[preorder.length()];
            stackP = 0;
            //两个##抵消父节点
            int p = 0;
            while (p < preorder.length()) {
                char c = preorder.charAt(p);
                System.out.println("Push node [" + c + "]");
                stack[stackP++] = c;
                if (c == '#') {
                    System.out.println("Call check()");
                    offset();
                }
                // 找到下一个逗号","
                while (p < preorder.length() && preorder.charAt(p) != ',') { p++; }
                p++;
                //当遍历完数组，Stack正好被剪空（只留一个为空的根节点）
                //Stack提前被剪空也不行
                if (stackP == 1 && stack[0] == '#') { break; }
            }
            System.out.println("Finish, stack = " + stack);
            return p == preorder.length()+1 && stackP == 1 && stack[0] == '#';
        }
        /**
         * 模拟树的剪枝：如果某节点左右两个节点都为空，说明这棵子树都探过了
         * 就把整棵子树都剪掉（删除左右两个空节点，再把本节点设为空）
         * 如果头两个元素是'#'，就删掉头3个元素，然后再压入一个'#'
         */
        private void offset() {
            if (stackP >= 3 && stack[stackP-1] == '#' && stack[stackP-2] == '#') {
                stack[stackP-3] = '#';
                System.out.println("Subtree [" + stack[stackP-3] + "] killed!");
                stackP -= 2;
                offset();
            }
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public boolean isValidSerialization(String preorder) {
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
        private VerifyPreorderSerializationOfABinaryTree problem = new VerifyPreorderSerializationOfABinaryTree();
        private Solution solution = null;

        // call method in solution
        private void call(String preorder, boolean answer) {
            System.out.println("Tree: [" + preorder + "]");
            System.out.println("Is valide? " + solution.isValidSerialization(preorder) + "\t\t[answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String preorder1 = ""; boolean answer1 = false;
            String preorder2 = "#"; boolean answer2 = true;
            String preorder3 = "#,1,2"; boolean answer3 = false;
            String preorder4 = "1,#"; boolean answer4 = false;
            String preorder5 = "9,#,#,1"; boolean answer5 = false;
            String preorder6 = "9,3,4,#,#,1,#,#,2,#,6,#,#"; boolean answer6 = true;
            String preorder7 = "9,#,92,#,#"; boolean answer7= true;


            /** involk call() method HERE */
            call(preorder1,answer1);
            call(preorder2,answer2);
            call(preorder3,answer3);
            call(preorder4,answer4);
            call(preorder5,answer5);
            call(preorder6,answer6);
            call(preorder7,answer7);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
