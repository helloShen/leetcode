/**
 * Leetcode - find_all_anagrams_in_a_string
 */
package com.ciaoshen.leetcode.find_all_anagrams_in_a_string;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s.length() < p.length()) return list;
        int[] stdFreq = new int[26];
        int[] actualFreq = new int[26];
        for (int i = 0; i < p.length(); i++) {
            stdFreq[p.charAt(i) - 'a']++;
            actualFreq[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(stdFreq, actualFreq)) list.add(0);
        for (int i = p.length(), h = 0; i < s.length(); i++, h++) {
            actualFreq[s.charAt(h) - 'a']--;
            actualFreq[s.charAt(i) - 'a']++;
            if (Arrays.equals(stdFreq, actualFreq)) list.add(h + 1);
        }
        return list;
    }

}
