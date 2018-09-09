/**
 * Leetcode - #379 - Design Phone Directory
 */
package com.ciaoshen.leetcode.design_phone_directory;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * 占用的数字位设为1，没用过的设为0
 */
class Solution3 implements Solution {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public Solution3(int maxNumbers) {
        set = new BitSet();
        smallestFreeBit = 0;
        max = maxNumbers;        
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (smallestFreeBit == max) {
            return -1;
        }
        int res = smallestFreeBit;
        set.set(smallestFreeBit);
        smallestFreeBit = set.nextClearBit(smallestFreeBit);
        return res;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !set.get(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (set.get(number)) {
            set.clear(number);
            if (number < smallestFreeBit) {
                smallestFreeBit = number;
            }
        }
    }

    public String toString() {
        return set.toString();
    }

    /**====================== 【私有成员】 ========================*/
    BitSet set;
    int smallestFreeBit = 0;
    int max;
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */