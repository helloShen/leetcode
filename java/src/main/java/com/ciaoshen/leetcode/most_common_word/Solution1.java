/**
 * Leetcode - most_common_word
 */
package com.ciaoshen.leetcode.most_common_word;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * 用HashMap记录单词
 */
class Solution1 implements Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>();
        for (String ban : banned) {
            bannedSet.add(ban);
        }
        Map<String, Integer> wordFreq = new HashMap<>();
        int maxCount = 0;
        String mostFreqWord = "";
        for (String seg : paragraph.split(" ")) {
            int start = 0, end = 0;
            for ( ; start < seg.length(); start++) {
                if (Character.isLetter(seg.charAt(start))) {
                    break;
                }
            }
            for (end = start + 1; end < seg.length(); end++) {
                if (!Character.isLetter(seg.charAt(end))) {
                    break;
                }
            }
            String word = seg.substring(start, end).toLowerCase();
            if (word != null && word.length() > 0 && !bannedSet.contains(word)) {
                int count = 1;
                if (wordFreq.containsKey(word)) {
                    count = wordFreq.get(word) + 1;
                }
                wordFreq.put(word, count);
                if (count > maxCount) {
                    mostFreqWord = word;
                    maxCount = count;
                } 
            }
        }
        return mostFreqWord;
    }
}
