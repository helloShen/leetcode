/**
 * Leetcode - remove_k_digits
 */
package com.ciaoshen.leetcode.remove_k_digits;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * 找第一个开始递减的数字。
 * 用List，找到一个删一个。
 */
class Solution1 implements Solution {

    public String removeKdigits(String num, int k) {
        List<Character> chars = new LinkedList<>();
        for (char c : num.toCharArray()) chars.add(c);
        while (k-- > 0) {
            boolean find = false;
            for (int i = 0; i < chars.size(); i++) {
                if ((i + 1) == chars.size() || chars.get(i) > chars.get(i + 1)) {
                    chars.remove(i);
                    find = true;
                    break;
                }
            }
            if (!find) chars.remove(chars.size() - 1);
        }
        while (!chars.isEmpty()) {
            if (chars.get(0) != '0') break;
            chars.remove(0);
        }
        if (chars.size() == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for (Character c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }

}
