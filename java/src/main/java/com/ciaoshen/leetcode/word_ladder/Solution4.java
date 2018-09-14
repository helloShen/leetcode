/**
 * Leetcode - word_ladder
 */
package com.ciaoshen.leetcode.word_ladder;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution4 implements Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        return bfs(beginWord, endWord, wordSet);
    }
    private int bfs(String begin, String end, Set<String> wordSet) {
        List<String> thisLevel = new ArrayList<>();
        thisLevel.add(begin);
        int level = 1;
        while (!thisLevel.isEmpty()) {
            List<String> nextLevel = new ArrayList<>();
            for (String word : thisLevel) {
                if (word.equals(end)) {
                    return level;
                }
                // enumerate all possible variable rather than calculate the distance between every two words
                // and this do econimie times
                char[] cs = word.toCharArray();
                for (int i = 0; i < cs.length; i++) {
                    char orig = cs[i];
                    for (char c = 'a' ; c <= 'z'; c++) {
                        if (c == orig) { continue; }
                        cs[i] = c;
                        String variant = new String(cs);
                        if (wordSet.remove(variant)) {
                            nextLevel.add(variant);
                        }
                    }
                    cs[i] = orig;
                }
            }
            thisLevel = nextLevel;
            level++;
        }
        return 0;
    }

}
