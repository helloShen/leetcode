/**
 * Leetcode - string_compression
 */
package com.ciaoshen.leetcode.string_compression;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public int compress(char[] chars) {
        int pen = 0;
        int start = 0;
        while (start < chars.length) {
            for (int end = start + 1; end <= chars.length; end++) {
                if (end == chars.length || chars[end] != chars[start]) {
                    chars[pen++] = chars[start];
                    if (end - start > 1) {
                        String len = String.valueOf(end - start);
                        for (int i = 0; i < len.length() ; i++) {
                            chars[pen++] = len.charAt(i);
                        }
                    }
                    start = end;
                    break;
                }
            }
        }
        return pen;
    }

}
