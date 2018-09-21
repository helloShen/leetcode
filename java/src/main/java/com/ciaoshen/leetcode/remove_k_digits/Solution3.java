/**
 * Leetcode - remove_k_digits
 */
package com.ciaoshen.leetcode.remove_k_digits;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * 用Stack的解法
 */
class Solution3 implements Solution {

    public String removeKdigits(String num, int k) {
        int size = num.length();
        char[] stack = new char[size];
        int top = 0;
        char[] numChar = num.toCharArray();
        int cur = 0;
        for ( ; cur < size; cur++) {
            while (k > 0 && top > 0 && (stack[top - 1] > numChar[cur])) {
                top--; k--;
            }
            if (top == 0 && numChar[cur] == '0') continue;
            stack[top++] = numChar[cur];
        }
        while (k > 0 && top > 0) {
            top--; k--;
        }
        String partOne = new String(stack, 0, top);
        String partTwo = new String(numChar, cur, size - cur);
        return (partOne.length() + partTwo.length() == 0)? "0" : partOne + partTwo;
    }

}
