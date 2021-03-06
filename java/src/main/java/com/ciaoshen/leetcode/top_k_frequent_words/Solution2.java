/**
 * Leetcode - top_k_frequent_words
 */
package com.ciaoshen.leetcode.top_k_frequent_words;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * 先统计所有单词词频，然后排序。
 * 用String自带的compareTo()函数。效率比自己写的差一些。
 */
class Solution2 implements Solution {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> dic = new HashMap<>();
        for (String word : words) {
            if (!dic.containsKey(word)) {
                dic.put(word, 1);
            } else {
                dic.put(word, dic.get(word) + 1);
            }
        }
        PriorityQueue<Map.Entry<String, Integer>> order = new PriorityQueue<>(words.length, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
                int diff = b.getValue() - a.getValue();
                if (diff != 0) return diff;
                char[] wordA = a.getKey().toCharArray();
                char[] wordB = b.getKey().toCharArray();
                int ap = 0, bp = 0;
                while (ap < wordA.length && bp < wordB.length) {
                    char ca = wordA[ap++];
                    char cb = wordB[bp++];
                    if (ca != cb) return ca - cb;
                }
                if (ap == wordA.length && bp == wordB.length) return 0;
                if (ap == wordA.length) return -1;
                return 1; // bp == wordB.length
            }
        });
        for (Map.Entry<String, Integer> entry : dic.entrySet()) {
            order.add(entry);
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(order.poll().getKey());
        }
        return result;
    }
}
