/**
 * Leetcode - #385 - Mini Parser
 */
package com.ciaoshen.leetcode.mini_parser;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {
  
    public void test(Solution s) {
        for (String testcase : testcases) {
            call(s, testcase);
        }
    }
    
    private String testcase1 = "324";
    private String testcase2 = "[123,[456,[789]]]"; 
    private String[] testcases = new String[]{testcase1, testcase2};

    private void call(Solution s, String str) {
        System.out.println("Original String = " + str);
        System.out.println("Result = " + s.deserialize(str));
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Test t = new Test();
        Solution s1 = new Solution1();
        t.test(s1);
    }
}
