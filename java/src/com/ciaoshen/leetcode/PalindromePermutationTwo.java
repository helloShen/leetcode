/**
 * Leetcode - Algorithm - PalindromePermutationTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PalindromePermutationTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PalindromePermutationTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<String> generatePalindromes(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /** 回溯全排列。最后逐个判断是否是回文 */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public List<String> generatePalindromes(String s) {
            List<Character> letters = new ArrayList<>();
            for (char c : s.toCharArray()) { letters.add(c); }
            StringBuilder word = new StringBuilder();
            Set<String> permutations = new HashSet<>();
            permutation(letters,word,permutations);
            // System.out.println(permutations);
            Iterator<String> ite = permutations.iterator();
            while (ite.hasNext()) {
                if (!isPalindrome(ite.next())) { ite.remove(); }
            }
            return new ArrayList<String>(permutations);
        }
        /* 给出一系列字符，返回可能的排列表 */
        private void permutation(List<Character> letters, StringBuilder word, Set<String> permutations) {
            if (letters.isEmpty()) { permutations.add(word.toString()); return; }
            ListIterator<Character> ite = letters.listIterator();
            while (ite.hasNext()) {
                List<Character> copy = new ArrayList<>(letters);
                copy.remove(ite.nextIndex());
                word.append(ite.next());
                permutation(copy,word,permutations);
                int len = word.length();
                word = word.delete(len-1,len);
            }
        }
        /* 判断输入字符串是否是回文 */
        private boolean isPalindrome(String s) {
            int lo = 0, hi = s.length()-1;
            while (lo < hi) {
                if (s.charAt(lo++) != s.charAt(hi--)) { return false; }
            }
            return true;
        }
    }

    /** 更快一点的回溯全排列。*/
    private class Solution2 extends Solution {
        { super.id = 2; }

        public List<String> generatePalindromes(String s) {
            Set<String> permutations = new HashSet<>();
            permutation(new StringBuilder(s),new StringBuilder(),permutations);
            Iterator<String> ite = permutations.iterator();
            while (ite.hasNext()) {
                if (!isPalindrome(ite.next())) { ite.remove(); }
            }
            return new ArrayList<String>(permutations);
        }
        /* 用StringBuilder，更快的回溯 */
        private void permutation(StringBuilder letters, StringBuilder word, Set<String> permutations) {
            int len = letters.length();
            if (len == 0) { permutations.add(word.toString()); return; }
            for (int i = 0; i < len; i++) {
                char letter = letters.charAt(i);
                word.append(letter);
                letters.delete(i,i+1);
                permutation(letters,word,permutations);
                int wordLen = word.length();
                word = word.delete(wordLen-1,wordLen);
                letters.insert(i,letter);
            }
        }
        /* 判断输入字符串是否是回文 */
        private boolean isPalindrome(String s) {
            int lo = 0, hi = s.length()-1;
            while (lo < hi) {
                if (s.charAt(lo++) != s.charAt(hi--)) { return false; }
            }
            return true;
        }
    }

    /**
     * 还是单核的先去核。对称的部分取一半全排列。然后逐个补上另一半。
     * 这版回溯算法更快。
     */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<String> generatePalindromes(String s) {
            List<String> result = new ArrayList<>();
            char[] half = new char[s.length()/2];
            char[] core = new char[1];
            if (!canGeneratePalindrome(s,half,core)) { return result; }
            Set<String> permutations = new HashSet<>();
            // permutation()函数面对的下层接口是两个char[]，它向上承诺的接口是一个Set<String>
            permutation(half,new boolean[half.length],new StringBuilder(),permutations);
            // System.out.println("Half = " + Arrays.toString(half));
            // System.out.println("Core = " + Arrays.toString(core));
            // System.out.println("Permutations = " + permutations);
            // System.out.println("Result = " + result);
            if (permutations.isEmpty() && core[0] != '\0') { result.add(new String(core)); } // s长度为1，只有一个单核
            for (String str : permutations) {
                int halfLen = str.length();
                StringBuilder sb = new StringBuilder(str);
                if (core[0] != '\0') { sb.append(core[0]); }
                for (int i = halfLen - 1; i >= 0; i--) {
                    sb.append(sb.charAt(i));
                }
                result.add(sb.toString());
            }
            return result;
        }
        /*
         * 判断能否生成回文。如果能的，拆解出单核，以及对称的一半字符，备用。
         * 字符串长度为零，返回false。
         */
        private boolean canGeneratePalindrome(String s, char[] half, char[] core) {
            int len = s.length();
            if (len == 0) { return false; }
            Set<Character> set = new HashSet<>();
            int cur = 0;
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (!set.add(c)) {
                    set.remove(c);
                    half[cur++] = c;
                }
            }
            int size = set.size();
            if (size == 1) { core[0] = (char)set.toArray()[0]; }
            return size < 2;
        }
        /* 返回一系列字符串的全排列。更快的回溯算法 */
        private void permutation(char[] letters, boolean[] used, StringBuilder word, Set<String> permutations) {
            int wordLen = word.length();
            if (wordLen > 0 && wordLen == letters.length) { permutations.add(word.toString()); return; }
            for (int i = 0; i < letters.length; i++) {
                if (i > 0 && letters[i-1] == letters[i] && !used[i-1]) { continue; }
                if (!used[i]) {
                    word.append(letters[i]);
                    used[i] = true;
                    permutation(letters,used,word,permutations);
                    int len = word.length();
                    word.delete(len-1,len);
                    used[i] = false;
                }
            }
        }
    }
    // you can expand more solutions HERE if you want...

    /**
     * 还是单核的先去核。对称的部分取一半全排列。然后逐个补上另一半。
     * 但用动态规划做全排列。
     */
    private class Solution4 extends Solution {
        { super.id = 4; }

        public List<String> generatePalindromes(String s) {
            List<String> result = new ArrayList<>();
            char[] half = new char[s.length()/2];
            char[] core = new char[1];
            if (!canGeneratePalindrome(s,half,core)) { return result; }
            Set<String> permutations = permutation(new String(half));
            // System.out.println("Half = " + Arrays.toString(half));
            // System.out.println("Core = " + Arrays.toString(core));
            // System.out.println("Permutations = " + permutations);
            // System.out.println("Result = " + result);
            if (permutations.isEmpty() && core[0] != '\0') { result.add(new String(core)); } // s长度为1，只有一个单核
            for (String str : permutations) {
                StringBuilder sb = new StringBuilder(str);
                String mid = (core[0] == '\0')? "" : new String(core);
                result.add(sb.toString() + mid + sb.reverse().toString());
            }
            return result;
        }
        /*
         * 判断能否生成回文。如果能的，拆解出单核，以及对称的一半字符，备用。
         * 字符串长度为零，返回false。
         */
        private boolean canGeneratePalindrome(String s, char[] half, char[] core) {
            int len = s.length();
            if (len == 0) { return false; }
            Set<Character> set = new HashSet<>();
            int cur = 0;
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (!set.add(c)) {
                    set.remove(c);
                    half[cur++] = c;
                }
            }
            int size = set.size();
            if (size == 1) { core[0] = (char)set.toArray()[0]; }
            return size < 2;
        }
        /* 比Solution3更快的产生全排列。用自底向上的动态规划 */
        private Set<String> permutation(String letters) {
            Set<String> result = new HashSet<>();
            if (letters.length() == 0) { return result; }
            if (letters.length() == 1) { result.add(letters); return result ; }
            char c = letters.charAt(0);
            Set<String> subSet = permutation(letters.substring(1));
            for (String str : subSet) {
                int len = str.length();
                for (int i = 0; i <= len; i++) {
                    result.add(str.substring(0,i) + c + str.substring(i,len));
                }
            }
            return result;
        }
    }
    /**
     * 还是单核的先去核。找到对称的一半。但全排列的时候直接加上另一半。
     * 全排列还是用回溯算法。
     */
    private class Solution5 extends Solution {
        { super.id = 5; }

        public List<String> generatePalindromes(String s) {
            List<String> result = new ArrayList<>();
            char[] half = new char[s.length()/2];
            char[] core = new char[1];
            if (!canGeneratePalindrome(s,half,core)) { return result; }
            Set<String> permutations = new HashSet<>();
            permutation(half,core[0],0,new boolean[half.length],new StringBuilder(),permutations);
            // System.out.println("Half = " + Arrays.toString(half));
            // System.out.println("Core = " + Arrays.toString(core));
            // System.out.println("Permutations = " + permutations);
            return new ArrayList<String>(permutations);
        }
        /*
         * 判断能否生成回文。如果能的，拆解出单核，以及对称的一半字符，备用。
         * 字符串长度为零，返回false。
         */
        private boolean canGeneratePalindrome(String s, char[] half, char[] core) {
            int len = s.length();
            if (len == 0) { return false; }
            Set<Character> set = new HashSet<>();
            int cur = 0;
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (!set.add(c)) {
                    set.remove(c);
                    half[cur++] = c;
                }
            }
            int size = set.size();
            if (size == 1) { core[0] = (char)set.toArray()[0]; }
            return size < 2;
        }
        /* 返回一系列字符串的全排列。更快的回溯算法，而且直接补齐回文另一半。*/
        private void permutation(char[] letters, char core, int mid, boolean[] used, StringBuilder word, Set<String> permutations) {
            if (word.length() == letters.length * 2) {
                if (core != '\0') { word = word.insert(mid,core); }
                if (word.length() > 0) { permutations.add(word.toString()); }
                if (core != '\0') { word = word.delete(mid,mid+1); }
                return;
            }
            for (int i = 0; i < letters.length; i++) {
                // 下面这行非常重要，消除了很多重复的排列。leetcode有一项测试：aaaaaaaaaaaa，有了这一句的保护，就能通过。
                if (i > 0 && letters[i] == letters[i-1] && !used[i-1]) { continue; }
                if (!used[i]) {
                    char[] pair = new char[]{letters[i],letters[i]};
                    word = word.insert(mid,pair);
                    used[i] = true;
                    // System.out.println("word = " + word.toString());
                    // System.out.println("used = " + Arrays.toString(used));
                    permutation(letters,core,mid+1,used,word,permutations);
                    int len = word.length();
                    word.delete(mid,mid+2);
                    used[i] = false;
                }
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
        private PalindromePermutationTwo problem = new PalindromePermutationTwo();
        private Solution solution = null;

        // call method in solution
        private void call(String s, List<String> answer) {
            System.out.println("Palindrome Permutation of \"" + s + "\" is: ");
            System.out.println(solution.generatePalindromes(s));
            System.out.println("Answer should be: " + answer);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            String s1 = "aabb";
            List<String> answer1 = new ArrayList<>(Arrays.asList(new String[]{"abba", "baab"}));
            String s2 = "abc";
            List<String> answer2 = new ArrayList<>();
            String s3 = "a";
            List<String> answer3 = new ArrayList<>(Arrays.asList(new String[]{"a"}));
            String s4 = "aab";
            List<String> answer4 = new ArrayList<>(Arrays.asList(new String[]{"aba"}));
            String s5 = "aaaaaa";
            List<String> answer5 = new ArrayList<>(Arrays.asList(new String[]{"aaaaaa"}));
            String s6 = "civic";
            List<String> answer6 = new ArrayList<>(Arrays.asList(new String[]{"civic","icvci"}));
            call(s1,answer1);
            call(s2,answer2);
            call(s3,answer3);
            call(s4,answer4);
            call(s5,answer5);
            call(s6,answer6);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        test.test(4);
        // test.test(5);
    }
}
