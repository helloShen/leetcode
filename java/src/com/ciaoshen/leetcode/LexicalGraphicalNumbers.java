/**
 * Leetcode - Algorithm - LexicalGraphicalNumbers
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class LexicalGraphicalNumbers implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private LexicalGraphicalNumbers() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<Integer> lexicalOrder(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* 转换成字符，排序 */
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        private final int INT_SIZE = 10;
        private int[] ca = new int[INT_SIZE];
        private int[] cb = new int[INT_SIZE];
        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                res.add(i);
            }
            Collections.sort(res,new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    int pa = parse(ca,a);
                    int pb = parse(cb,b);
                    while (pa < INT_SIZE && pb < INT_SIZE) {
                        int x = ca[pa++];
                        int y = cb[pb++];
                        if (x < y) {
                            return -1;
                        } else if (x > y) {
                            return 1;
                        }
                    }
                    if (pa < INT_SIZE) { return 1; }    // a is longer
                    if (pb < INT_SIZE) { return -1; }   // b is longer
                    return 0;
                }
            });
            return res;
        }
        private int parse(int[] c, int n) {
            int pa = INT_SIZE;
            while (n > 0) {
                c[--pa] = n % 10;
                n /= 10;
            }
            return pa;
        }
    }

    /* 把数字左对齐，然后排序 */
    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                res.add(i);
            }
            Collections.sort(res,new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    long diff = leftAlign(a) - leftAlign(b);
                    if (diff < 0) {
                        return -1;
                    } else if (diff > 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            return res;
        }
        private final long STD = 10000000000L;
        private long leftAlign(int n) {
            long l = (long)n;
            while (true) {
                long next = l * 10;
                if (next < STD) {
                    l = next;
                } else {
                    break;
                }
            }
            return l;
        }
    }

    /* 有限状态机，逐个生成数字 */
    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            int[] board = new int[10];
            for (int i = 1; i < 10; i++) {
                int bp = -1;
                for (int j = i; j <= n; j *= 10) {
                    res.add(j);
                    board[++bp] = j+1;
                }
                // System.out.println(Arrays.toString(board));
                int maxbp = bp;
                // System.out.println("bp =" + bp + ", maxbp = " + maxbp);
                while (bp > 0) {
                    // System.out.println(Arrays.toString(board));
                    if (board[bp] / 10 < board[bp-1]) {                         // 1. 不往左进位
                        if (bp < maxbp) {                                       //     1.1 不是最后一个计数器
                            res.add(board[bp]++);
                            if (maxbp - bp > 1 || board[maxbp] <= n) { bp++; }  //         1.1.1 如果最后一个计数器到了最大值，不再往;右进一步
                        } else if (board[bp] <= n) {                            //     1.2 最后一个计数器，且没到阈值
                            res.add(board[bp]++);
                        } else {                                                //     1.3 最后一个计数器到了阈值
                            bp--;
                        }
                    } else {                                                    // 2. 往左进位
                        bp--;
                    }
                }
            }
            return res;
        }
    }

    /* dfs逐个生成下一个数字 */
    private class Solution4 extends Solution {
        { super.id = 4; }
        private List<Integer> list = new ArrayList<>();
        private int max = 0;
        public List<Integer> lexicalOrder(int n) {
            list.clear();
            max = n;
            dfs(0);
            return list;
        }
        private void dfs(int seed) {
            for (int i = 0; i < 10; i++) {
                if (seed == 0 && i == 0) { continue; } // 跳过0的情况
                int num = seed + i;
                if (num > max) { return; }
                list.add(num);
                dfs(num * 10);
            }
        }
    }

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
        private LexicalGraphicalNumbers problem = new LexicalGraphicalNumbers();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            System.out.println(n + "\t - > \t" + solution.lexicalOrder(n));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            call(0);
            call(1);
            call(10);
            call(100);
            call(192);
            call(1003);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        test.test(4);
    }
}
