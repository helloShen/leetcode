/**
 * Leetcode - next_greater_element
 */
package com.ciaoshen.leetcode.next_greater_element;
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

    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        if (findGreater(arr)) {
            try {
                return Integer.parseInt(new String(arr));
            } catch (NumberFormatException nfe) {
                return -1;
            }
        }
        return -1;
    }

    private boolean findGreater(char[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] > arr[i - 1]) {
                int j = i;
                while (j + 1 < arr.length && (arr[j + 1] > arr[i - 1])) j++;
                swap(arr, i - 1, j);
                Arrays.sort(arr, i, arr.length);
                return true;
            }
        }
        return false;
    }

    /** swap ith & (i-1)th element in arr */
    private void swap(char[] arr, int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
