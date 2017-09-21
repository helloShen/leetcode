/**
 * Leetcode - Algorithm - LongestAbsoluteFilePath
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class LongestAbsoluteFilePath implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private LongestAbsoluteFilePath() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int lengthLongestPath(String input); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // simble
        private final char SPLITTER = '\n';
        private final char TAB = '\t';
        // globle path
        private String path = "";
        private int len = 0;
        private int index = 0;
        // init the path
        private void init(String path) {
            this.path = path;
            len = path.length();
            index = 0;
        }
        // main access
        public int lengthLongestPath(String input) {
            int max = 0;
            init(input); // init globle values
            if (len == 0) { return max; } // defance
            Deque<String> stack = new LinkedList<>();
            String root = getSubDir(stack); // no slash before the root
            if (root.isEmpty()) { return max; } // defance
            stack.offerFirst(root);
            System.out.println("Root is: " + root);
            while (canGoDeeper()) {
                int depth = getDepth(); // depth begin from 0
                System.out.println("Depth = " + depth + ", size of stack = " + stack.size());
                System.out.println("Stack is: " + stack);
                if (depth < stack.size()) {
                    max = Math.max(max,countPathLength(stack)); // find new leaf, update max length
                    while (depth < stack.size()) { stack.pollFirst(); }
                }
                stack.offerFirst(getSubDir(stack));
            }
            max = Math.max(max,countPathLength(stack)); // last possible leaf, update max length
            return max;
        }
        /* check if the next character is "\n" or "\t" (will not move the index pointer) */
        private boolean canGoDeeper() {
            return isSplitter() || isTab();
        }
        /* check if the next character is "\n" (will not move the index pointer) */
        private boolean isSplitter() {
            return index < len && path.charAt(index) == SPLITTER;
        }
        /* check if the next character is "\t" (will not move the index pointer) */
        private boolean isTab() {
            return index < len && path.charAt(index) == TAB;
        }
        /* extract the sub dir in the path from the given index. have to check if this is will be the root. (will move index pointer) */
        private String getSubDir(Deque<String> stack) {
            StringBuilder sb = new StringBuilder();
            if (stack.size() > 0) { sb.append("\\"); } // not root
            while (index < len && !isSplitter()) {
                sb.append(path.charAt(index++));
            }
            return sb.toString();
        }
        /* count the number of "\t" (will move the index pointer to the first character after "\t") */
        private int getDepth() {
            skipSplitter();
            int count = 0;
            while (isTab()) { ++index; ++count; }
            return count;
        }
        /* skip "\n" (will move the index pointer to the first character after "\n") */
        private void skipSplitter() {
            if (isSplitter()) { ++index; }
        }
        /* count the length of the current path in stack */
        private int countPathLength(Deque<String> stack) {
            int sum = 0;
            if (stack.isEmpty() || !isFile(stack.peekFirst())) { return sum; } // do not count the length if it's not a file
            System.out.print("Length of \"");
            for (String s : stack) {
                System.out.print(s);
                sum += s.length();
            }
            System.out.print("\" is: " + sum + "\n");
            return sum;
        }
        /* check if the first element in the stack is a file */
        private boolean isFile(String s) {
            return s.contains(".");
        }
    }
    /* 用数组模拟stack */
    private class Solution2 extends Solution {
        { super.id = 2; }
        // constant
        private final char SPLITTER = '\n';
        private final char TAB = '\t';
        private final String POINT = ".";
        // environment
        private char[] path = new char[0];          // input path
        private int len = 0;                        // length of input path
        private int p = 0;                          // pointer to the path
        // init enviroment
        private void init(String s) {
            path = s.toCharArray();             // input path
            len = s.length();                   // length of input path
            p = 0;                              // pointer
        }
        // assert: the input path should be valid path.
        public int lengthLongestPath(String input) {
            // defence
            if (input.length() == 0) { return 0; }
            // init environment
            init(input);
            // local variable
            int[] stack = new int[len];             // note length of substrings
            boolean[] isFile = new boolean[len];    // note if substrings in the stack are files
            int currDepth = -1;                     // root has depth 0
            int max = 0;                            // result
            // iterate sub path
            do {
                int depth = nextDepth();
                String subpath = nextSubPath();
                if (depth <= currDepth) {
                    max = Math.max(max,collect(stack,isFile,currDepth));
                    while (depth <= currDepth) { --currDepth; }
                }
                stack[++currDepth] = subpath.length();
                if (currDepth > 0) { ++stack[currDepth]; } // count slash
                isFile[currDepth] = isFile(subpath);
            } while (hasNext());
            max = Math.max(max,collect(stack,isFile,currDepth));
            return max;
        }
        /* have more sub path? if yes, move pointer to first '\t' */
        private boolean hasNext() {
            boolean res = (p < len) && path[p] == SPLITTER;
            if (res) { ++p; }
            return res;
        }
        /* return the depth of next dir/file. move pointer after the last '\t' */
        private int nextDepth() {
            int depth = 0;
            while (p < len && path[p] == TAB) { ++depth; ++p; }
            return depth;
        }
        /* return the next dir/file. move pointer to next '\n' */
        private String nextSubPath() {
            StringBuilder sb = new StringBuilder();
            while (p < len && path[p] != SPLITTER && path[p] != TAB) { sb.append(path[p++]); }
            return sb.toString();
        }
        /* check if the given string is the name of file. all file name contains "." */
        private boolean isFile(String s) {
            return s.contains(POINT);
        }
        /* sum up the length of sub path in current stack */
        private int collect(int[] stack, boolean[] isFile, int depth) {
            int len = 0;
            if (!isFile[depth]) { return 0; }
            for (int i = 0; i <= depth; i++) {
                len += stack[i];
            }
            return len;
        }
    }

    /*
     * 主要四点简化：
     *      1. 用split()函数切割字符串
     *      2. 用lastIndexOf()函数计算'\t'的数量，也就是depth
     *      3. stack数组里记录的不是每一层路径的长度，而是从根目录累计到这一层的总长度
     *      4. 当且仅当当前路径段是一个文件的时候，才更新最长路径记录。意味着不需要比较路径的深度，也不用记录每个路径是不是文件
     */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public int lengthLongestPath(String input) {
            if (input.length() == 0) { return 0; }
            int max = 0;
            int[] stack = new int[input.length()+1];
            for (String s : input.split("\n")) {
                int depth = s.lastIndexOf('\t') + 2; // depth of root is 1
                int len = stack[depth-1] + (s.length() - depth + 1);
                stack[depth] = (depth > 1)? len+1 : len; // add slash if is not root
                if (s.contains(".")) { max = Math.max(max,stack[depth]); } // is file
            }
            return max;
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
        private LongestAbsoluteFilePath problem = new LongestAbsoluteFilePath();
        private Solution solution = null;

        // call method in solution
        private void call(String input, String answer) {
            System.out.println("Path = " + input);
            System.out.println("Longest path to file is = " + solution.lengthLongestPath(input) + "     [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String input1 = "";
            String answer1 = "0";
            String input2 = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
            String answer2 = "20";
            String input3 = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
            String answer3 = "32";
            String input4 = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
            String answer4 = "20";
            String input5 = "dir\n    file.txt";
            String answer5 = "12";

            /** involk call() method HERE */
            call(input1,answer1);
            call(input2,answer2);
            call(input3,answer3);
            call(input4,answer4);
            call(input5,answer5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
