/**
 * Leetcode - Algorithm - Repeated DNA Sequences
 */
package com.ciaoshen.leetcode;
import java.util.*;

class RepeatedDNASequences {
    public class SolutionV1 {
        public List<String> findRepeatedDnaSequences(String s) {
            Set<String> retSet = new HashSet<>();
            if (s == null || s.length() <= 10) { return new ArrayList<String>(retSet); }
            Set<String> memo = new HashSet<>();
            for (int i = 0; i <= s.length() - 10; i++) {
                String sub = s.substring(i,i+10);
                if (!memo.add(sub)) { retSet.add(sub); }
            }
            return new ArrayList<String>(retSet);
        }
    }
    public class SolutionV2 {
        public List<String> findRepeatedDnaSequences(String s) {
            Set<Long> memo = new HashSet<>();
            if (s == null || s.length() <= 10) { return new ArrayList<>(); }
            Set<String> retSet = new HashSet<>();
            for (int i = 0; i <= s.length()-10; i++) {
                // System.out.println("Hash Code for \"" + s.substring(i,i+10) + "\" = " + hashDNA(s,i,i+9));
                if (!memo.add(hashDNA(s,i,i+9))) { retSet.add(s.substring(i,i+10)); }
            }
            return new ArrayList<String>(retSet);
        }
        public Long hashDNA(String s, int lo, int hi) {
            Long hash = 17l;
            for (int i = lo; i <= hi; i++) {
                switch (s.charAt(i)) {
                    case 'A':
                        hash = (hash << 5 - 1) + 3; break;
                    case 'C':
                        hash = (hash << 5 - 1) + 5; break;
                    case 'G':
                        hash = (hash << 5 - 1) + 7; break;
                    case 'T':
                        hash = (hash << 5 - 1) + 11; break;
                }
                // System.out.println("Char = " + s.charAt(i) + ", hash = " + hash);
            }
            return hash;
        }
    }
    /**
     * 每个长度为10的子串，用20 bits就可以表示。
     *      A = 00
     *      C = 01
     *      G = 10
     *      T = 11
     */
    public class SolutionV3 {
        public List<String> findRepeatedDnaSequences(String s) {
            // defense
            if (s == null || s.length() <= 10) { return new ArrayList<String>(); }
            // two Set
            Set<Integer> memo = new HashSet<>(); // dictionary of bit code
            Set<String> retSet = new HashSet<>(); // result
            // iteration
            for (int i = 0; i <= s.length()-10; i++) {
                System.out.println("Bit Code for \"" + s.substring(i,i+10) + "\" = " + getBitCode(s,i,i+9));
                if (! memo.add(getBitCode(s,i,i+9))) { retSet.add(s.substring(i,i+10)); }
            }
            // return
            return new ArrayList<String>(retSet);
        }
        public int getBitCode(String s, int lo, int hi) {
            int bitCode = 0;
            int mask = 1;
            for (int i = hi; i >= lo; i--) {
                switch (s.charAt(i)) {
                    case 'A':
                        mask = mask << 2; break;
                    case 'C':
                        bitCode = bitCode | mask;
                        mask = mask << 2; break;
                    case 'G':
                        mask = mask << 1;
                        bitCode = bitCode | mask;
                        mask = mask << 1; break;
                    case 'T':
                        bitCode = bitCode | mask;
                        mask = mask << 1;
                        bitCode = bitCode | mask;
                        mask = mask << 1; break;
                }
            }
            return bitCode;
        }
    }
    /**
     * 还是V3的方法。简化版。
     */
    public class SolutionV4 {
        public List<String> findRepeatedDnaSequences(String s) {
            // defense
            if (s == null || s.length() <= 10) { return new ArrayList<String>(); }
            // two Set
            Set<Integer> memo = new HashSet<>(); // dictionary of bit code
            Set<String> retSet = new HashSet<>(); // result
            // iteration
            for (int i = 0; i <= s.length()-10; i++) {
                // System.out.println("Bit Code for \"" + s.substring(i,i+10) + "\" = " + getBitCode(s,i,i+9));
                if (! memo.add(getBitCode(s,i,i+9))) { retSet.add(s.substring(i,i+10)); }
            }
            // return
            return new ArrayList<String>(retSet);
        }
        public int getBitCode(String s, int lo, int hi) {
            int v = 0;
            int[] map = new int[26];
            map['C' - 'A'] = 1;
            map['G' - 'A'] = 2;
            map['T' - 'A'] = 3;
            for (int i = lo; i <= hi; i++) {
                v |= map[s.charAt(i) - 'A'];
                v = v << 2;
            }
            return v;
        }
    }
    /**
     * 还是V3的方法。更简化版。
     * 每次不重新计算Bit Code, 而是使用前一个Bit Code掐头去尾，修改得到。
     */
    public class Solution {
        public List<String> findRepeatedDnaSequences(String s) {
            // defense
            if (s == null || s.length() <= 10) { return new ArrayList<String>(); }
            // two Set
            Set<Integer> memo = new HashSet<>(); // dictionary of bit code
            Set<String> retSet = new HashSet<>(); // result
            // iteration
            int bitCode = -1;
            int[] map = new int[26];
            map['C' - 'A'] = 1;
            map['G' - 'A'] = 2;
            map['T' - 'A'] = 3;
            for (int i = 0; i <= s.length()-10; i++) {
                if (bitCode == -1) {
                    bitCode = 0;
                    for (int j = i; j <= i+9; j++) {
                        bitCode = bitCode << 2;
                        bitCode |= map[s.charAt(j) - 'A'];
                    }
                } else {
                    // 掐头
                    bitCode = bitCode << 14 >>> 12;
                    // 补尾
                    bitCode |= map[s.charAt(i+9) - 'A'];
                }
                if (! memo.add(bitCode)) { retSet.add(s.substring(i,i+10)); }
                System.out.println("Bit Code for \"" + s.substring(i,i+10) + "\" = " + printInt(bitCode));
            }
            // return
            return new ArrayList<String>(retSet);
        }
    }
    private static String printInt(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.insert(0, num&1);
            num = num >> 1;
        }
        return sb.toString();
    }
    private static RepeatedDNASequences test = new RepeatedDNASequences();
    private static Solution solution = test.new Solution();
    private static void callFindRepeatedDnaSequences(String s) {
        System.out.println("For String \"" + s + "\", repeated DNA are: " + solution.findRepeatedDnaSequences(s));
    }
    private static void test() {
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        String s2 = "GAGAGAGAGAGA";
        callFindRepeatedDnaSequences(s1);
        callFindRepeatedDnaSequences(s2);
    }
    /**
    private static void testHashDNA() {
        String s1 = "ACCCCCCAAA";
        String s2 = "AACCCCCAAA";
        System.out.println("For String \"" + s1 + "\", hash code = " + solution.hashDNA(s1,0,9));
        System.out.println("For String \"" + s2 + "\", hash code = " + solution.hashDNA(s2,0,9));
    }
    */
    public static void main(String[] args) {
        test();
        // testHashDNA();
    }
}
