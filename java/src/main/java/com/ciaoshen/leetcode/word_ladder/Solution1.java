/**
 * Leetcode - word_ladder
 */
package com.ciaoshen.leetcode.word_ladder;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(beginWord)) {
                wordList.add(beginWord);
        }
        neighbours = findNeighbours(wordList);
        minLen = null;
        Set<String> path = new HashSet<String>();
        path.add(beginWord);
        backtracking(path, beginWord, endWord);
        return (minLen == null)? 0 : minLen;
    }
    private Map<String, List<String>> neighbours;
    private Integer minLen;
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
            }
        }
        return dis;
    }
    private void backtracking(Set<String> path, String begin, String end) {
        // System.out.println("begin = [" + begin + "], end = [" + end + "], path size = " + path.size());
        if (begin.equals(end)) {
            // System.out.println("Find path: " + path);
            minLen = (minLen == null)? path.size() : Math.min(minLen, path.size());
            return;
        }
        if (neighbours.containsKey(begin)) {
            for (String neighbour : neighbours.get(begin)) {
                if (path.add(neighbour)) {
                    backtracking(path, neighbour, end);
                    path.remove(neighbour);
                }
            }
        }
    }

}
