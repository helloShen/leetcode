/**
 * Leetcode - find_all_anagrams_in_a_string
 */
package com.ciaoshen.leetcode.find_all_anagrams_in_a_string;
import java.util.*;
import com.ciaoshen.leetcode.util.*;
// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Solution2 implements Solution {

    private static final Logger LOGGER = LoggerFactory.getLogger(Solution2.class);

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s.length() < p.length()) return list;
        int[] table = new int[26];
        int diff = p.length();
        for (int i = 0; i < p.length(); i++) {
            table[p.charAt(i) - 'a']++;
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("standard add {}", p.charAt(i));
            }
        }
        for (int i = 0; i < p.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (table[idx] > 0) {
                diff--;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("need {}", s.charAt(i));
                }
            } else {
                diff++;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("too much {}", s.charAt(i));
                }
            }
            table[idx]--;
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("add {}", s.charAt(i));
                LOGGER.debug("diff = {}", diff);
            }

        }
        if (diff == 0) list.add(0);
        for (int i = p.length(), h = 0; i < s.length(); i++, h++) {
            int rmIdx = s.charAt(h) - 'a'; // remove first element in window
            if (table[rmIdx] < 0) {
                diff--;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("need to remove {}", s.charAt(h));
                }
            } else {
                diff++;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("need more {}", s.charAt(h));
                }
            }
            table[rmIdx]++;
            int addIdx = s.charAt(i) - 'a'; // add new element into window
            if (table[addIdx] > 0) {
                diff--;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("need {}", s.charAt(i));
                }
            } else {
                diff++;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("too much {}", s.charAt(i));
                }
            }
            table[addIdx]--;
            if (diff == 0) list.add(h + 1);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("diff = {}", diff);
            }
        }
        return list;
    }

}
