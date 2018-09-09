/**
 * Leetcode - Algorithm - EncodeAndDecodeStrings
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class EncodeAndDecodeStrings implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private EncodeAndDecodeStrings() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String encode(List<String> strs); // 主方法接口
        abstract public List<String> decode(String s);    // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        /**
         * 每个String用空格" "分割，空字符串""后面也用一个空格分割
         * "\"是转义符
         * "\ "表示字符串中的空格
         * "\\"表示字符串中的转义符本身
         * 如果原始字符串列表为空，返回空字符串""
         * [Hello,World] -> "hello world "
         */
        public String encode(List<String> strs) {
            char space = ' ';
            char trans = '\\';
            StringBuilder res = new StringBuilder();
            for (String str : strs) {
                char[] chars = str.toCharArray();
                for (char c : chars) {
                    if (c == space || c == trans) { res.append(trans); }
                    res.append(c);
                }
                res.append(space); // 用空格分割每个字符串
            }
            return res.toString();
        }
        public List<String> decode(String s) {
            List<String> res = new ArrayList<>();
            char space = ' ';
            char trans = '\\';
            char[] chars = s.toCharArray();
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c == space) {
                    res.add(word.toString());
                    word.delete(0,word.length());
                } else if (c == trans) {
                    char next = chars[++i];
                    if (next == space) {
                        word.append(space);
                    } else if (next == trans) {
                        word.append(trans);
                    }
                } else {
                    word.append(c);
                }
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        /**
         * 计算每个单词的长度
         * [Hello,World] -> "5/Hello5/World"
         */
        public String encode(List<String> strs) {
            StringBuilder res = new StringBuilder();
            for (String str : strs) {
                res.append(String.valueOf(str.length()));
                res.append("/" + str);
            }
            return res.toString();
        }
        public List<String> decode(String s) {
            List<String> res = new ArrayList<>();
            int i = 0;
            while (i < s.length()) {
                int slash = s.indexOf("/",i);
                int offset = Integer.parseInt(s.substring(i,slash));
                res.add(s.substring(slash+1,slash+1+offset));
                i = slash + 1 + offset;
            }
            return res;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public String encode(List<String> strs) {
            return "3";
        }
        public List<String> decode(String s) {
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
        private EncodeAndDecodeStrings problem = new EncodeAndDecodeStrings();
        private Solution solution = null;

        private void call(List<String> str) {
            System.out.println("Original String List: " + str);
            String encoded = solution.encode(str);
            System.out.println("Encoded String: " + encoded);
            System.out.println("Decoded String List: " + solution.decode(encoded) + "\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            List<String> str1 = new ArrayList<String>(Arrays.asList(new String[]{"hello","world"}));
            List<String> str2 = new ArrayList<String>(Arrays.asList(new String[]{"hello "," world "}));
            List<String> str3 = new ArrayList<String>(Arrays.asList(new String[]{"沈","玮"}));
            List<String> str4 = new ArrayList<String>(Arrays.asList(new String[]{"",""}));
            List<String> str5 = new ArrayList<String>(Arrays.asList(new String[0]));

            /** involk call() method HERE */
            call(str1);
            call(str2);
            call(str3);
            call(str4);
            call(str5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
