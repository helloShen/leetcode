/**
 * Leetcode - unique_morse_code_words
 */
package com.ciaoshen.leetcode.unique_morse_code_words;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> morseSet = new HashSet<>();
        for (String word : words) {
            morseSet.add(toMorse(word));
        }
        return morseSet.size();
    }
    private final String[] MORSE = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    private String toMorse(String word) {
        char[] arr = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(MORSE[arr[i] - 'a']);
        }
        return sb.toString();
    }

}
