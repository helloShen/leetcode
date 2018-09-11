/**
 * Leetcode - most_common_word
 */
package com.ciaoshen.leetcode.most_common_word;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * 利用Trie来记录单词树
 */
class Solution2 implements Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Trie root = new Trie();
        Trie curr = root;
        for (String ban : banned) {
            for (int i = 0; i < ban.length(); i++) {
                int idx = ban.charAt(i) - 'a';
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
                curr = curr.next[idx];
            }
            curr.ban = true;
            curr = root;
        }
        int maxCount = 0;
        String mostFreqWord = "";
        paragraph = paragraph.toLowerCase();
        char[] pArray = paragraph.toCharArray();
        for (int start = 0, end = 0; start < pArray.length; start = end + 1) {
            while (start < pArray.length && (pArray[start] < 'a' || pArray[start] > 'z')) {
                start++;
            }
            for (end = start; end < pArray.length && (pArray[end] >= 'a' && pArray[end] <= 'z'); end++) {
                int idx = pArray[end] - 'a';
                if (curr.next[idx] == null) {
                    curr.next[idx] = new Trie();
                }
                curr = curr.next[idx];
            }
            if (curr != root && !curr.ban) {
                curr.count++;
                if (curr.count > maxCount) {
                    mostFreqWord = paragraph.substring(start, end);
                    maxCount = curr.count;
                }
            }
            curr = root;
        }
        return mostFreqWord;
    }
    private class Trie {
        private Trie[] next = new Trie[26];
        private int count;
        private boolean ban;
    }
}
