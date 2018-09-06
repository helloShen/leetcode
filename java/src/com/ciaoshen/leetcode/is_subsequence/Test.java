/**
 * Leetcode - #392 - Is Subsequence
 */
package com.ciaoshen.leetcode.is_subsequence;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {
  
    public void test(Solution solution) {
        for (int i = 0; i < sa.length; i++) {
            call(solution, sa[i], ta[i]);
        }
    }
    
    private String s1 = "abc";
    private String t1 = "ahbgdc";

    private String s2 = "axc";
    private String t2 = "ahbgdc";

    private String[] sa = new String[]{s1,s2}; 
    private String[] ta = new String[]{t1,t2}; 

    private void call(Solution solution, String s, String t) {
        System.out.println("s = " + s + ",\t t = " + t);
        System.out.println("Result = " + solution.isSubsequence(s, t));
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Test t = new Test();
        // Solution s1 = new Solution1();
        // t.test(s1);
        Solution s1 = new Solution1();
        t.test(s1);
    }
}
