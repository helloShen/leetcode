/**
 * Leetcode - #379 - Design Phone Directory
 */
package com.ciaoshen.leetcode.design_phone_directory;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

public interface Solution {
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get();
    
    /** Check if a number is available or not. */
    public boolean check(int number);
    
    /** Recycle or release a number. */
    public void release(int number);
}