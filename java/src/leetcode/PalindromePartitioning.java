/**
 * Leetcode - Algorithm - Palindrome Partitioning
 */
package com.ciaoshen.leetcode;
import java.util.*;

class PalindromePartitioning {
    public class SolutionV1 {
        private class BooleanMap {
            private boolean[] table;
            private int start, end;
            private BooleanMap(int size) {
                table = new boolean[size+1]; // 最高位为符号位，表示是否满了。
                start = 0; end = start+1;
            }
            public boolean hasNext() {
                return start < table.length;
            }
            public int[] next() {
                while (end < table.length && !table[end]) { end++; }
                int[] res = new int[]{ start,end };
                start = end; end++;
                return res;
            }
            public boolean plus() {
                table[table.length-1] = !table[table.length-1];
                int cur = table.length-1;
                while (!table[cur]) { table[cur-1] = !table[cur-1]; cur--; }
                start = 0; end = 1;
                if (table[0]) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) { return res; }
            char[] c = s.toCharArray();
            BooleanMap map = new BooleanMap(c.length-1);
            List<String> group = new ArrayList<>();
            outerLoop:
            do {
                group.clear();
                innerLoop:
                while (map.hasNext()) {
                    int[] range = map.next();
                    if (!isPalindrome(c,range[0],range[1]-1)) { // 有String不是回文
                        continue outerLoop;
                    } else {
                        group.add(new String(Arrays.copyOfRange(c,range[0],range[1])));
                    }
                }
                res.add(new ArrayList<String>(group));
            } while (map.plus());
            return res;
        }
        public boolean isPalindrome(char[] c, int lo, int hi) {
            while (lo <= hi) {
                if (c[lo] != c[hi]) {
                    return false;
                } else {
                    lo++; hi--;
                }
            }
            return true;
        }
    }
    // 动态规划。用Map<String,Boolean>做memo
    public class SolutionV2 {
        private class BooleanMap {
            private boolean[] table;
            private int start, end;
            private BooleanMap(int size) {
                table = new boolean[size+1]; // 最高位为符号位，表示是否满了。
                start = 0; end = start+1;
            }
            public boolean hasNext() {
                return start < table.length;
            }
            public int[] next() {
                while (end < table.length && !table[end]) { end++; }
                int[] res = new int[]{ start,end };
                start = end; end++;
                return res;
            }
            public boolean plus() {
                table[table.length-1] = !table[table.length-1];
                int cur = table.length-1;
                while (!table[cur]) { table[cur-1] = !table[cur-1]; cur--; }
                start = 0; end = 1;
                if (table[0]) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) { return res; }
            char[] c = s.toCharArray();
            BooleanMap map = new BooleanMap(c.length-1);
            Map<String,Boolean> memo = new HashMap<>();
            List<String> group = new ArrayList<>();
            outerLoop:
            do {
                group.clear();
                innerLoop:
                while (map.hasNext()) {
                    int[] range = map.next();
                    if (!isPalindrome(memo,c,range[0],range[1]-1)) { // 有String不是回文
                        continue outerLoop;
                    } else {
                        group.add(new String(Arrays.copyOfRange(c,range[0],range[1])));
                    }
                }
                res.add(new ArrayList<String>(group));
            } while (map.plus());
            //System.out.println(memo);
            return res;
        }
        public boolean isPalindrome(Map<String,Boolean> memo, char[] c, int lo, int hi) {
            String key = "" + lo + hi;
            Boolean res = memo.get(key);
            if (res != null) { return res; }
            while (lo <= hi) {
                if (c[lo] != c[hi]) {
                    //System.out.println("FALSE >>> key =" + key + ",c[" + lo + "]=" + c[lo] + ",c[" + hi + "]=" + c[hi]);
                    memo.put(key,false);
                    return false;
                } else {
                    //System.out.println("TRUE >>> key =" + key + ",c[" + lo + "]=" + c[lo] + ",c[" + hi + "]=" + c[hi]);
                    lo++; hi--;
                }
            }
            memo.put(key,true);
            return true;
        }
    }
    // 动态规划。用Boolean[][]做memo
    public class SolutionV3 {
        private class BooleanMap {
            private boolean[] table;
            private int start, end;
            private BooleanMap(int size) {
                table = new boolean[size+1]; // 最高位为符号位，表示是否满了。
                start = 0; end = start+1;
            }
            public boolean hasNext() {
                return start < table.length;
            }
            public int[] next() {
                while (end < table.length && !table[end]) { end++; }
                int[] res = new int[]{ start,end };
                start = end; end++;
                return res;
            }
            public boolean plus() {
                table[table.length-1] = !table[table.length-1];
                int cur = table.length-1;
                while (!table[cur]) { table[cur-1] = !table[cur-1]; cur--; }
                start = 0; end = 1;
                if (table[0]) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) { return res; }
            char[] c = s.toCharArray();
            BooleanMap map = new BooleanMap(c.length-1);
            Boolean[][] memo = new Boolean[c.length][c.length];
            List<String> group = new ArrayList<>();
            outerLoop:
            do {
                group.clear();
                innerLoop:
                while (map.hasNext()) {
                    int[] range = map.next();
                    if (!isPalindrome(memo,c,range[0],range[1]-1)) { // 有String不是回文
                        continue outerLoop;
                    } else {
                        group.add(new String(Arrays.copyOfRange(c,range[0],range[1])));
                    }
                }
                res.add(new ArrayList<String>(group));
            } while (map.plus());
            //System.out.println(memo);
            return res;
        }
        public boolean isPalindrome(Boolean[][] memo, char[] c, int lo, int hi) {
            Boolean res = memo[lo][hi];
            if (res != null) { return res; }
            int l = lo, h = hi;
            while (l <= h) {
                if (c[l] != c[h]) {
                    System.out.println("FALSE >>> c[" + l + "]=" + c[l] + ",c[" + h + "]=" + c[h]);
                    memo[lo][hi] = false;
                    return false;
                } else {
                    System.out.println("TRUE >>> c[" + l + "]=" + c[l] + ",c[" + h + "]=" + c[h]);
                    l++; h--;
                }
            }
            memo[lo][hi] = true;
            return true;
        }
    }
    public class SolutionV4 {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) { return res; }
            char[] c = s.toCharArray();
            List<List<List<String>>> memo = new ArrayList<>();
            List<List<String>> group = new ArrayList<>();
            group.add(new ArrayList<String>());
            memo.add(0,group);
            dp(c,0,memo);
            return memo.get(0);
        }
        public void dp(char[] c, int cur, List<List<List<String>>> memo) {
            if (cur == c.length) { return; }
            dp(c,cur+1,memo);
            List<List<String>> res = new ArrayList<>();
            for (int i = cur+1; i <= c.length; i++) {
                if (isPalindrome(c,cur,i-1)) {
                    List<List<String>> last = memo.get(i-cur-1);
                    for (List<String> group : last) {
                        List<String> newGroup = new ArrayList<>(group);
                        newGroup.add(0,new String(Arrays.copyOfRange(c,cur,i)));
                        res.add(newGroup);
                    }
                }
            }
            memo.add(0,res);
        }
        public boolean isPalindrome(char[] c, int lo, int hi) {
            while (lo <= hi) {
                if (c[lo] != c[hi]) {
                    return false;
                } else {
                    lo++; hi--;
                }
            }
            return true;
        }
    }
    public class SolutionV5 {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) { return res; }
            char[] c = s.toCharArray();
            List<List<List<String>>> memo = new ArrayList<>();
            List<List<String>> empty = new ArrayList<>();
            empty.add(new ArrayList<String>());
            memo.add(empty);
            // dp
            for (int left = c.length-1; left >= 0; left--) { // 从右往左逐渐加长字符串
                List<List<String>> temp = new ArrayList<>();
                for (int right = left; right < c.length; right++) { // [left,right]表示每次检查是否为pal的子串，如果为pal,(right,c.length-1]的结果就要被重用。
                    if (isPalindrome(c,left,right)) {
                        List<List<String>> last = memo.get(right-left);
                        String newPal = new String(Arrays.copyOfRange(c,left,right+1));
                        for (List<String> group : last) {
                            List<String> newGroup = new ArrayList<>(group);
                            newGroup.add(0,newPal);
                            temp.add(newGroup);
                        }
                    }
                }
                memo.add(0,temp);
            }
            return memo.get(0);
        }
        public boolean isPalindrome(char[] c, int lo, int hi) {
            while (lo <= hi) {
                if (c[lo] != c[hi]) {
                    return false;
                } else {
                    lo++; hi--;
                }
            }
            return true;
        }
    }
    public class SolutionV6 {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) { return res; }
            char[] c = s.toCharArray();
            List<List<List<String>>> memo = new ArrayList<>(); // 初始化备忘录
            List<List<String>> empty = new ArrayList<>();
            empty.add(new ArrayList<String>());
            memo.add(empty);
            boolean[][] isPal = new boolean[c.length][c.length]; // 初始化回文记录
            // dp
            for (int left = c.length-1; left >= 0; left--) { // 从右往左逐渐加长字符串
                List<List<String>> temp = new ArrayList<>();
                for (int right = left; right < c.length; right++) { // [left,right]表示每次检查是否为pal的子串，如果为pal,(right,c.length-1]的结果就要被重用。
                    if (c[left] == c[right] && (right - left < 2 || isPal[left+1][right-1])) {
                        isPal[left][right] = true;
                        List<List<String>> last = memo.get(right-left);
                        String newPal = new String(Arrays.copyOfRange(c,left,right+1));
                        for (List<String> group : last) {
                            List<String> newGroup = new ArrayList<>(group);
                            newGroup.add(0,newPal);
                            temp.add(newGroup);
                        }
                    }
                }
                memo.add(0,temp);
            }
            return memo.get(0);
        }
    }
    /**
     * 使用了两个备忘录
     * memo储存了更短的子串的全回文集合。
     * isPal[left][right]储存了，[left,right]子串是不是回文的信息，可以简化回文串的判断。
     */
    public class Solution {
        private boolean[][] isPal;
        private List<List<List<String>>> memo;
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            if (s == null || s.isEmpty()) { return res; }
            int len = s.length();
            memo = new ArrayList<>();
            List<List<String>> group = new ArrayList<>();
            group.add(new ArrayList<String>());
            memo.add(0,group);
            isPal = new boolean[len][len];
            dp(s,0);
            return memo.get(0);
        }
        public void dp(String s, int left) {
            if (left == s.length()) { return; }
            dp(s,left+1);
            List<List<String>> res = new ArrayList<>();
            for (int right = left; right < s.length(); right++) {
                String sub = s.substring(left,right+1);
                if (isPalindrome(s,left,right)) {
                    isPal[left][right] = true;
                    List<List<String>> last = memo.get(right-left);
                    for (List<String> group : last) {
                        List<String> newGroup = new ArrayList<>(group);
                        newGroup.add(0,sub);
                        res.add(newGroup);
                    }
                }
            }
            memo.add(0,res);
        }
        public boolean isPalindrome(String s, int lo, int hi) {
            return (s.charAt(lo) == s.charAt(hi) && (hi-lo < 2 || isPal[lo+1][hi-1]));
        }
    }
    private static PalindromePartitioning test = new PalindromePartitioning();
    private static Solution s = test.new Solution();
    private static String[] strs = new String[]{
        "a",
        "aab",
        "baabbaab"
    };
    /*
    private static void testIsPalindrome() {
        for (String str : strs) {
            char[] c = str.toCharArray();
            System.out.println(s.isPalindrome(c,0,c.length-1));
        }
    }
    */
    private static void testBooleanMap() {
        SolutionV3 s3 = test.new SolutionV3();
        SolutionV3.BooleanMap map = s3.new BooleanMap(4);
        do {
            System.out.println(Arrays.toString(map.table));
            while (map.hasNext()) {
                System.out.println(">>> " + Arrays.toString(map.next()));
            }
        } while (map.plus());
    }
    private static void testPartition() {
        for (String str : strs) {
            System.out.println(s.partition(str));
        }
    }
    public static void main(String[] args) {
        //testBooleanMap();
        //testIsPalindrome();
        testPartition();
    }
}
