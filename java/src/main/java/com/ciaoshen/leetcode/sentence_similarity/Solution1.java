/**
 * Leetcode - sentence_similarity
 */
package com.ciaoshen.leetcode.sentence_similarity;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution1 implements Solution {

    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        Map<String, Set<String>> pairDic = new HashMap<>();
        for (String[] pair : pairs) {
            if (!pairDic.containsKey(pair[0])) pairDic.put(pair[0], new HashSet<String>());
            pairDic.get(pair[0]).add(pair[1]);
        }
        if (log.isDebugEnabled()) {
            log.debug("Pair dictionary is: {}", pairDic);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) continue;
            if (pairDic.containsKey(words1[i]) && pairDic.get(words1[i]).contains(words2[i])) continue;
            if (pairDic.containsKey(words2[i]) && pairDic.get(words2[i]).contains(words1[i])) continue;
            return false;
        }
        return true;
    }

}
