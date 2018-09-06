/**
 * Leetcode - #379 - Design Phone Directory
 */
package com.ciaoshen.leetcode.design_phone_directory;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {
  
    /** 测试用例对外服务接口 */
    public void test1(Solution s) {

        System.out.println("\n" + s);
        s.release(1);
        System.out.println("\n" + s);
        System.out.print(s.get() + ", ");     // 0
        System.out.println("\n" + s);
        System.out.print(s.check(1) + ", ");  // true
        s.release(1);
        System.out.print(s.check(1) + ", ");  // true

        System.out.print(s.get() + ", ");     // 1
        System.out.println("\n" + s);
        System.out.print(s.check(0) + ", ");  // false
        System.out.print(s.check(1) + ", ");  // false
        System.out.print(s.check(1) + ", ");  // false

        System.out.print("\n");

        System.out.println("[0, true, true, 1, false, false, false]");
    }
    
    /**========================== 【主入口】 ============================*/

    public static void main(String[] args) {
        Test t = new Test();
        int max1 = 2;
        Solution s11 = new Solution1(max1);
        Solution s21 = new Solution2(max1);
        Solution s31 = new Solution3(max1);
        // t.test1(s11);
        // t.test1(s21);
        t.test1(s31);
    }
}
