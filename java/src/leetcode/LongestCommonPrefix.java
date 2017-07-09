/**
 * Leetcode: Longest Common Prefix
 */
package com.ciaoshen.leetcode;
import java.util.*;
import java.lang.Math.*;

public class LongestCommonPrefix {
    public String longestCommonPrefixV1(String[] strs) {
        if (strs.length == 0) { return ""; }
        if (strs.length == 1) { return strs[0]; }
        int maxLen = Integer.MAX_VALUE;
        for (String str : strs) {
            maxLen = Math.min(maxLen,str.length());
        }
        outerFor:
        for (int i = 0; i < maxLen; i++) {
            char c = strs[0].charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != c) {
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0].substring(0,maxLen);
    }

    public String longestCommonPrefixV2(String[] strs) {
        if (strs.length == 0) { return ""; }
        if (strs.length == 1) { return strs[0]; }
        int maxLen = Integer.MAX_VALUE;
        for (String str : strs) {
            maxLen = Math.min(maxLen,str.length());
        }
        return longestCommonPrefixRecursive(strs,0,maxLen-1);
    }
    private String longestCommonPrefixRecursive(String[] strs, int low, int high) {
        System.out.println("low = " + low + ", high = " + high);
        // base case
        if (high - low < 0) {
            return "";
        }
        int median = low + ((high-low)/2);
        char c = strs[0].charAt(median);
        for (String str : strs) {
            if (str.charAt(median) != c) {
                return longestCommonPrefixRecursive(strs,low,median-1);
            }
        }
        String leftPrefix = longestCommonPrefixRecursive(strs,low,median-1);
        if (leftPrefix.length() == (median-low) || (median - low) < 0) {
            return leftPrefix + c + longestCommonPrefixRecursive(strs,median+1,high);
        } else {
            return leftPrefix;
        }
    }

    /**
     * Use libaray
     */
    public String longestCommonPrefixV3(String[] strs) {
        if (strs == null || strs.length == 0) { return ""; }
        if (strs.length == 1) { return strs[0]; }
        outerFor:
        for (int i = strs[0].length() ; i > 0; i--) {
            innerFor:
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].indexOf(strs[0].substring(0,i)) != 0) { continue outerFor; }
            }
            return strs[0].substring(0,i);
        }
        return "";
    }

    /**
     * Sort, and compare the first and the last element
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) { return ""; }
        if (strs.length == 1) { return strs[0]; }
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length-1].toCharArray();
        int length = Math.min(first.length,last.length);
        for (int i = 0; i < length; i++) {
            if (first[i] != last[i]) { return new String(Arrays.copyOf(first,i)); }
        }
        return new String(Arrays.copyOf(first,length));
    }


    private static void testCaseInsensitiveComparator() {
        String[] strs = new String[] {"sss","SSS","sss","SSS","sss","SSS","sss","SSS","sss","SSS","sss","SSS"};
        Arrays.sort(strs,String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(strs));
    }

    private static void test() {
        LongestCommonPrefix test = new LongestCommonPrefix();
        String[] strs1 = new String[] {"abcdefg","abc","abcde","abcccccc"}; // answer: "abc"
        String[] strs2 = new String[] {"Hello","World"}; // answer: ""
        String[] strs3 = new String[] {"thisawiopefjpio","this","thisfajweij","thisajwe"}; // answer: "this"
        String[] strs4 = new String[] {"aaaaaaaaa","aaaaa"}; // "aaaaa"
        String[] strs5 = new String[] {"a","a","aa"};
        String[] strs6 = new String[] {"a","",""};
        String[][] testCases = new String[][] {strs1,strs2,strs3,strs4,strs5,strs6};
        for (String[] strs : testCases) {
            System.out.println(Arrays.toString(strs));
            System.out.println("Longest Prefix = " + test.longestCommonPrefix(strs));
        }
    }

    public static void main(String[] args) {
        //testCaseInsensitiveComparator();
        test();
    }
}
