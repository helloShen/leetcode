/**
 * Leetcode - word_ladder
 */
package com.ciaoshen.leetcode.word_ladder;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution3 implements Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return bfs(beginWord, endWord, wordList);
    }
    private int bfs(String begin, String end, List<String> wordList) {
        List<String> thisLevel = new ArrayList<>();
        thisLevel.add(begin);
        int level = 1;
        while (!thisLevel.isEmpty()) {
            List<String> nextLevel = new ArrayList<>();
            for (String word : thisLevel) {
                if (word.equals(end)) {
                    return level;
                }
                Iterator<String> ite = wordList.iterator();
                while (ite.hasNext()) {
                    String anotherWord = ite.next();
                    if (distance(anotherWord, word) == 1) {
                        nextLevel.add(anotherWord);
                        ite.remove();
                    }
                }
            }
            thisLevel = nextLevel;
            level++;
        }
        return 0;
    }
    // assetion: a.length() == b.length()
    private int distance(String a, String b) {
        int dis = 0; 
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                dis++;
                if (dis >= 2) {
                    return 2;
                }
            }
        }
        return dis;
    }

}
