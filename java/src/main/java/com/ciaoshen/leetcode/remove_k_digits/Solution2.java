/**
 * Leetcode - remove_k_digits
 */
package com.ciaoshen.leetcode.remove_k_digits;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * 找第一个开始递减的数字。
 * 不用集合容器，直接在数组上操作。要删掉的数字暂时用"*"标记，最后统一剔除。
 */
class Solution2 implements Solution {

    private final char MARK = '*';
    public String removeKdigits(String num, int k) {
        char[] chars = num.toCharArray();
        int size = chars.length;
        while (k-- > 0) {
            int p = 0;
            while (p < size) {
                if (chars[p] == MARK) {
                    p++;
                    continue;
                }
                int cpP = p;
                while (++p < size && chars[p] == MARK);
                if (p < size && chars[p] < chars[cpP]) {
                        // System.out.println(chars[cpP] + " to *");
                        chars[cpP] = MARK;
                        break;
                }
            }
            if (p >= size) {
                int tail = size - 1;
                while (tail >= 0 && chars[tail] == MARK) tail--;
                chars[tail] = MARK;
            }
        }
        // System.out.println(Arrays.toString(chars));
        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (start < size && (chars[start] == MARK || chars[start] == '0')) start++;
        for (int i = start; i < size; i++) {
            if (chars[i] != MARK) sb.append(chars[i]);
        }
        if (sb.length() == 0) return "0";
        return sb.toString();
    }

}
