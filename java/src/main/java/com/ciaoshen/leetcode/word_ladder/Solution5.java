/**
 * Leetcode - word_ladder
 */
package com.ciaoshen.leetcode.word_ladder;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution4 implements Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return bfs(beginWord, endWord, wordSet);
    }
    private int bfs(String begin, String end, List<String> wordSet) {
        HashSet<String> visited = new HashSet<>();
        visited.add(begin);
        int level = 1;
        while (!wordSetvisited.contains(end)) {
            int size = thisLevel.size();
            for (int i = 0; i < size; i++) {
                String word = thisLevel.remove(0);
                if (word.equals(end)) {
                    return level;
                }
                char[] cs = word.toCharArray();
                for (int j = 0; j < cs.length; j++) {
                    char orig = cs[j];
                    for (char c = 'a' ; c <= 'z'; c++) {
                        if (c == orig) { continue; }
                        cs[j] = c;
                        String variant = new String(cs);
                        if (wordSet.remove(variant)) {
                            thisLevel.add(variant);
                        }
                    }
                    cs[j] = orig;
                }
            }
            level++;
        }
        return 0;
    }

}
