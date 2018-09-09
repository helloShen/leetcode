/**
 * Leetcode - Algorithm - Group Anagrams
 */
package com.ciaoshen.leetcode;
import java.util.*;

class GroupAnagrams {

    public List<List<String>> groupAnagramsV1(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String,Integer> dic = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String str = new String(chars);
            Integer pos = dic.get(str);
            if (pos == null) { // new group
                dic.put(str,dic.size());
                res.add(new ArrayList<String>(Arrays.asList(new String[]{strs[i]})));
            } else { // already exist
                res.get(pos).add(strs[i]);
            }
        }
        return res;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<Long,Integer> dic = new HashMap<>();
        // 对应26个字母
        int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            long key = 1;
            for (char c : chars) {
                key *= prime[c-'a'];
            }
            Integer pos = dic.get(key);
            if (pos == null) { // new group
                dic.put(key,dic.size());
                res.add(new ArrayList<String>(Arrays.asList(new String[]{strs[i]})));
            } else { // already exist
                res.get(pos).add(strs[i]);
            }
        }
        return res;
    }
    private static void testGroupAnagrams() {
        GroupAnagrams test = new GroupAnagrams();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(test.groupAnagrams(strs));
    }
    public static void main(String[] args) {
        testGroupAnagrams();
    }
}
