/**
 * Leetcode - word_ladder
 */
package com.ciaoshen.leetcode.word_ladder;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution2 implements Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        return bfs(findNeighbours(wordList), beginWord, endWord, wordList);
    }

    private int bfs(Map<String, List<String>> neighbours, String begin, String end, List<String> wordList) {
        Set<String> visited = new HashSet<>();
        List<String> thisLevel = new ArrayList<>();
        thisLevel.add(begin);
        visited.add(begin);
        int level = 1;
        while (!thisLevel.isEmpty()) {
            List<String> nextLevel = new ArrayList<>();
            for (String neighbour : thisLevel) {
                if (neighbour.equals(end)) {
                    return level;
                }
                if (neighbours.containsKey(neighbour)) {
                    for (String nbofnb : neighbours.get(neighbour)) {
                        if (visited.add(nbofnb)) {
                            nextLevel.add(nbofnb);
                        }
                    }
                    neighbours.remove(neighbour);
                }
            }
            thisLevel = nextLevel;
            level++;
        }
        return 0;
    }
    private Map<String, List<String>> findNeighbours(List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            String a = wordList.get(i);
            for (int j = i + 1; j < wordList.size(); j++) {
                String b = wordList.get(j);
                if (distance(a, b) == 1) {
                    if (!map.containsKey(a)) {
                        map.put(a, new ArrayList<String>());
                    }
                    map.get(a).add(b);
                    if (!map.containsKey(b)) {
                        map.put(b, new ArrayList<String>());
                    }
                    map.get(b).add(a);
                }
            }
        }
        return map;
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
