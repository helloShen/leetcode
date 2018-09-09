/**
 * Leetcode - #379 - Design Phone Directory
 */
package com.ciaoshen.leetcode.design_phone_directory;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public Solution1(int maxNumbers) {
        max = maxNumbers;
        bankP = 0; // point to next number to assign
        recycle = new HashSet<Integer>();    
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (!recycle.isEmpty()) {
            return getFromRecycle();
        }
        if (bankP < max) {
            return bankP++;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return legal(number) && !assigned(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (legal(number) && assigned(number)) {
            recycle.add(number);
        }
    }

    
    /**====================== 【私有成员】 ========================*/
    private int max;
    private int bankP;
    private Set<Integer> recycle;

    private boolean legal(int number) {
        return number >= 0 && number < max;
    }
    private boolean assigned(int number) {
        return number < bankP && !recycle.contains(number);
    }
    private int getFromRecycle() {
        Iterator<Integer> ite = recycle.iterator();
        int num = ite.next();
        ite.remove();
        return num;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */