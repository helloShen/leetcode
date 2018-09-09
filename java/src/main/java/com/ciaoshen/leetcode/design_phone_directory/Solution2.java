/**
 * Leetcode - #379 - Design Phone Directory
 */
package com.ciaoshen.leetcode.design_phone_directory;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution2 implements Solution {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public Solution2(int maxNumbers) {
        linkedTable = new int[maxNumbers];
        for (int i = 0; i < maxNumbers; i++) {
            linkedTable[i] = (i + 1) % maxNumbers;
        }
        p = 0;
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (linkedTable[p] == -1) {
            return -1;
        }
        int nextNum = p;
        p = linkedTable[p];
        linkedTable[nextNum] = -1;
        return nextNum;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return linkedTable[number] != -1;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (!check(number)) {
            linkedTable[number] = p;
            p = number;
        }
    }

    public String toString() {
        return Arrays.toString(linkedTable);
    }
    
    /**====================== 【私有成员】 ========================*/
    private int[] linkedTable;
    private int p;
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */