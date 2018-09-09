/**
 * Leetcode - #393 - UTF-8 Validation
 * 
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 *  1. For 1-byte character, the first bit is a 0, followed by its unicode code.
 *  2. For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, 
 *     followed by n-1 bytes with most significant 2 bits being 10.
 * 
 * Char. number range  |        UTF-8 octet sequence
 *    (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.utf8_validation;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/** Bitwise Operation */
class Solution1 implements Solution {
    
    private int mask1 = 0x80; // 1000 0000
    private int mask2 = 0xc0; // 1100 0000
    private int mask3 = 0xe0; // 1110 0000
    private int mask4 = 0xf0; // 1111 0000
    private int mask5 = 0xf8; // 1111 1000
    
    public boolean validUtf8(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int head = data[i];
            int len = 0;
            if ((head & mask1) == 0) { // 0xxx xxxx
                continue;
            } else if ((head & mask3) == mask2) { // 110xx xxxx
                len = 1;
            } else if ((head & mask4) == mask3) { // 1110 xxxx
                len = 2;
            } else if ((head & mask5) == mask4) { // 1111 0xxx
                len = 3;
            } else {
                return false;
            }
            for (int j = 0; j < len; j++) {
                if (++i == data.length) { // need more byte
                    return false;
                } 
                if ((data[i] & mask2) != mask1) { // not fllowed by 10xx xxxx
                    return false;
                }
            }
        }
        return true;
    }

}