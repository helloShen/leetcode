/**
 * Leetcode - Algorithm - GeneralizedAbbreviation
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class GeneralizedAbbreviation implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private GeneralizedAbbreviation() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<String> generateAbbreviations(String word); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /**
     * 完全递归暴力展开
     */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public List<String> generateAbbreviations(String word) {
            List<String> res = new ArrayList<>();
            if (word == null) { return null; }
            recursion(word,0,res);
            return res;
        }
        private void recursion(String word, int p, List<String> list) {
            if (p == word.length()) { list.add(word); return; }
            recursion(word,p+1,list);
            if (p > 0 && Character.isDigit(word.charAt(p-1))) { //前面有数字需要融合
                int begin = p-1;    //标记数字起首位置
                while (begin >= 0 && Character.isDigit(word.charAt(begin))) { begin--; }
                begin++;
                // System.out.println("begin=" + begin);
                int pre = Integer.parseInt(word.substring(begin,p));
                // System.out.println("pre=" + pre);
                String newPrefix = word.substring(0,begin) + String.valueOf(pre+1);
                // System.out.println("prefix=" + newPrefix);
                // System.out.println("postfix=" + word.substring(p+1,word.length()));
                recursion(newPrefix + word.substring(p+1,word.length()), newPrefix.length(), list);
            } else {
                recursion(word.substring(0,p) + "1" + word.substring(p+1,word.length()), p+1, list);
            }
        }
    }
    /**
     * Solution1中间生成了太多String，看能不能用回溯算法节省空间。
     * 实际上对每个字符只有两种选择，要么改，要么不改。
     * 先走不改分支，再走改分支，不需要回溯回来。
     * 思路是分两步走，
     *      1. 先全部替换成“1”
     *      2. 最后把“1”全部融合后翻译成要的缩写
     * 比如：word的"w3"，先写成"w111"，最后merge()函数合成"w3"。
     *
     * 实际结果：
     * Solution2并没有比Solution1高效
     */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public List<String> generateAbbreviations(String word) {
            if (word == null) { return null; }
            res = new ArrayList<String>();
            charArray = word.toCharArray();
            backtracking(0);
            return res;
        }
        private List<String> res;
        private char[] charArray;
        private void backtracking(int p) {
            if (p == charArray.length) { res.add(merge(charArray)); return; }
            backtracking(p+1);
            char c = charArray[p];
            charArray[p] = '1';
            backtracking(p+1);
            charArray[p] = c;
        }
        private String merge(char[] word) {
            int len = word.length;
            StringBuilder sb = new StringBuilder();
            int p = 0;
            while (p < len) {
                while (p < len && !Character.isDigit(word[p])) { sb.append(word[p]); p++; }
                int count = 0;
                while (p < len && Character.isDigit(word[p])) { p++; count++; }
                if (count > 0) { sb.append(String.valueOf(count)); }
            }
            return new String(sb);
        }
    }

    /**
     * Solution3算法和Solution1一样
     * 只是数据结构上做小幅优化：
     *      1. 用StringBuilder替代String
     *      2. 不要每次都重新把数字读出来，用一个int记录这个数字
     */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<String> generateAbbreviations(String word) {
            List<String> res = new ArrayList<>();
            if (word == null) { return null; }
            backtracking(new StringBuilder(), word, 0, 0, res);
            return res;
        }
        //这里回溯稍微有点复杂，但抓住关键就不会错：
        //回溯的主体是prefix，所以只有当prefix真正改变了，才需要调用delete()回溯。
        //每次递归展开两条支线：
        //      1. 数字支线：prefix暂不变化，暂记一位缩写，所以本层递归不回溯。哪里遇到字符了，缩写兑现到prefix里了，再回溯。
        //      2. 字符支线：本层递归prefix就变化，而且带着之前所有累计的缩写位数一起兑现到prefix里，所以当场回溯。
        //
        private void backtracking(StringBuilder prefix, String word, int p, int count, List<String> list) {
            //base case
            if (p == word.length()) {
                if (count > 0) {
                    int len = prefix.length();
                    list.add(new String(prefix.append(count)));
                    prefix.delete(len,prefix.length()); //数字线在这里真正加到prefix里之后回溯
                } else {
                    list.add(new String(prefix));       //字符线在前一步改变prefix的时候已经回溯，这里就不回溯
                }
                return;
            }
            //数字支线
            backtracking(prefix,word,p+1,count+1,list); //数字支线这里没有真的改变prefix，先不回溯
            //字符支线
            int len = prefix.length();
            if (count > 0) { prefix.append(String.valueOf(count)); }
            prefix.append(word.charAt(p));
            backtracking(prefix,word,p+1,0,list);
            prefix.delete(len,prefix.length());         //字符支线因为已经改变prefix，所以在这里回溯
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
        private GeneralizedAbbreviation problem = new GeneralizedAbbreviation();
        private Solution solution = null;

        // call method in solution
        private void call(String word) {
            System.out.println("[" + word + "]");
            System.out.println(solution.generateAbbreviations(word));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String word1 = null;
            String word2 = "";
            String word3 = "word";
            // String word4 = "abbreviation";

            /** involk call() method HERE */
            call(word1);
            call(word2);
            call(word3);
            // call(word4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        test.test(3);
    }
}
