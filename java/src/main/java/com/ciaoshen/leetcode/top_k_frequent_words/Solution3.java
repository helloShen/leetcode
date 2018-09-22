/**
 * Leetcode - top_k_frequent_words
 */
package com.ciaoshen.leetcode.top_k_frequent_words;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * 统计词频的同时用PriorityQueue排序。
 */
class Solution3 implements Solution {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> dic = new HashMap<>();
        for (String word : words) {
            if (!dic.containsKey(word)) {
                dic.put(word, 1);
            } else {
                dic.put(word, dic.get(word) + 1);
            }
        }
        List<Map.Entry<String, Integer>> dicList = new ArrayList<>(dic.entrySet());
        Collections.sort(dicList, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
                int diff = b.getValue() - a.getValue();
                if (diff != 0) return diff;
                return a.getKey().compareTo(b.getKey());
            }
        });
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(dicList.get(i).getKey());
        }
        return result;
    }

}
