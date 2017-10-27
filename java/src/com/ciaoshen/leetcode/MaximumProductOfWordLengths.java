/**
 * Leetcode - Algorithm - MaximumProductOfWordLengths
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MaximumProductOfWordLengths implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MaximumProductOfWordLengths() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int maxProduct(String[] words); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }

    /** 最朴素的做法，单词两两间比较。适当进行了剪枝。 */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int maxProduct(String[] words) {
            int maxProduct = 0;
            boolean[] letters = new boolean[26];
            int maxSize = 0;
            for (String word : words) { maxSize = Math.max(maxSize,word.length()); }
            for1:
            for (int i = 0; i < words.length - 1; i++) {
                Arrays.fill(letters,false);
                String wordA = words[i];
                int sizeA = wordA.length();
                if (sizeA * maxSize <= maxProduct) { continue for1; }       // 剪枝
                for (int j = 0; j < sizeA; j++) {
                    letters[wordA.charAt(j)-'a'] = true;
                }
                for2:
                for (int j = 1; j < words.length; j++) {
                    String wordB = words[j];
                    int sizeB = wordB.length();
                    int possibleSize = sizeA * sizeB;
                    if (possibleSize <= maxProduct) { continue for2; }     // 剪枝
                    for3:
                    for (int k = 0; k < sizeB; k++) {
                        if (letters[wordB.charAt(k)-'a'] == true) {
                            continue for2;
                        }
                    }
                    maxProduct = possibleSize;
                }
            }
            return maxProduct;
        }
    }

    /** 把单词的字母信息，以bitmap的形式存在一个int中。 32位的int，存26个字母是否出现的信息 */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public int maxProduct(String[] words) {
            int maxProduct = 0;
            int[] bitmap = new int[words.length];
            int[] lengths = new int[words.length];
            for (int i = 0; i < words.length; i++) {
                int len = words[i].length();
                lengths[i] = len;
                for (int j = 0; j < len; j++) {
                    bitmap[i] |= (1 << words[i].charAt(j)-'a'); // int的低26位，表示26个字母是否出现
                }
            }
            for (int i = 0; i < words.length - 1; i++) {
                for (int j = 1; j < words.length; j++) {
                    if ((bitmap[i] & bitmap[j]) == 0) { // 没有相同字母
                        maxProduct = Math.max(maxProduct,(lengths[i] * lengths[j]));
                    }
                }
            }
            return maxProduct;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int maxProduct(String[] words) {
            return 3;
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
        private MaximumProductOfWordLengths problem = new MaximumProductOfWordLengths();
        private Solution solution = null;

        // call method in solution
        private void call(String[] nums) {
            System.out.println("For Array: " + Arrays.toString(nums) + ", \tMax product is: " + solution.maxProduct(nums));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String[] nums1 = new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
            String[] nums2 = new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
            String[] nums3 = new String[]{"a", "aa", "aaa", "aaaa"};
            String[] nums4 = new String[]{"eae","ea","aaf","bda","fcf","dc","ac","ce","cefde","dabae"};

            /** involk call() method HERE */
            call(nums1);
            call(nums2);
            call(nums3);
            call(nums4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
