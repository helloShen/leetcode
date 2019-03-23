/**
 * Leetcode - expressive_words
 */
package com.ciaoshen.leetcode.expressive_words;
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

    public int expressiveWords(String S, String[] words) {
        if (S.length() == 0) return 0;
        getPattern(S);
        int count = 0;
        for (String word : words) {
            if (isExpressive(word)) {
                // if (log.isDebugEnabled()) {
                //     log.debug("word [{}] is expressive!", word);
                // }
                count++;
            }
        }
        return count;
    }

    private char[] ca;
    private int[] na;

    private void getPattern(String word) {
        char[] charArr = new char[100];
        int[] numArr = new int[100];
        int wordP = 0, arrayP = 0;
        while (wordP < word.length()) {
            char c = word.charAt(wordP);
            charArr[arrayP] = c;
            int count = 0;
            while (wordP < word.length() && word.charAt(wordP) == c) {
                wordP++; count++;
            }
            numArr[arrayP++] = count;
        }
        ca = Arrays.copyOf(charArr, arrayP);
        na = Arrays.copyOf(numArr, arrayP);
        if (log.isDebugEnabled()) {
            log.debug("ca = {}", Arrays.toString(ca));
            log.debug("na = {}", Arrays.toString(na));
        }
    }

    private boolean isExpressive(String word) {
        // if (log.isDebugEnabled()) {
        //     log.debug("Test word [{}]", word);
        // }
        int arrP = 0, wordP = 0;
        while (arrP < ca.length && wordP < word.length()) {
            char c = word.charAt(wordP);
            // if (log.isDebugEnabled()) {
            //     log.debug("test char {}", c);
            // }
            if (ca[arrP] != c) {
                if (log.isDebugEnabled()) {
                    log.debug("word {} NOT! ca[{}] = {} != {}", word, arrP, ca[arrP], c);
                }
                return false;
            }
            int start = wordP;
            while (wordP < word.length() && word.charAt(wordP) == c) wordP++;
            int len = wordP - start;
            if (na[arrP] < 3) {
                if (len != na[arrP]) {
                    if (log.isDebugEnabled()) {
                        log.debug("word {} NOT! {} != na[{}] = {}", word, len, arrP, na[arrP]);
                    }
                    return false;
                }
            } else if (len > na[arrP]) {
                if (log.isDebugEnabled()) {
                    log.debug("word {} NOT! {} >= na[{}] = {}", word, len, arrP, na[arrP]);
                }
                return false;
            }
            arrP++;
        }
        if (log.isDebugEnabled()) {
            if (arrP == ca.length && wordP == word.length()) {
                log.debug("word {} is expressive!", word);
            }
        }
        return arrP == ca.length && wordP == word.length();
    }

}
