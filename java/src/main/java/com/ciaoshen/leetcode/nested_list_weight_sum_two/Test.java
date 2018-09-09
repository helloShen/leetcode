/**
 * Leetcode - Algorithm - Nested List Weight Sum Two
 */
package com.ciaoshen.leetcode.nested_list_weight_sum_two;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {

    public Test(Solution s) {
        this.solution = s;
    }

    public void depthSumInverseTest(List<NestedInteger> list, int answer) {
        System.out.println("Result = " + solution.depthSumInverse(list));
        System.out.println("Answer should be = " + answer);
    }

    private Solution solution;
    private static List<NestedInteger> getTestList1() {
        List<NestedInteger> res = new ArrayList<>();
        NestedInteger aa = new NestedInteger(1);
        NestedInteger ab = new NestedInteger(1);
        NestedInteger a = new NestedInteger();
        a.add(aa); 
        a.add(ab);
        NestedInteger b = new NestedInteger(2);
        NestedInteger ca = new NestedInteger(1);
        NestedInteger cb = new NestedInteger(1);
        NestedInteger c = new NestedInteger();
        c.add(ca);
        c.add(cb);
        res.add(a);
        res.add(b);
        res.add(c);
        return res;        
    }
    private static List<NestedInteger> getTestList2() {
        List<NestedInteger> res = new ArrayList<>();
        NestedInteger six = new NestedInteger(6);
        NestedInteger sixWrapper = new NestedInteger();
        sixWrapper.add(six);
        NestedInteger four = new NestedInteger(4);
        NestedInteger fourSix = new NestedInteger();
        fourSix.add(four);
        fourSix.add(sixWrapper);
        NestedInteger one = new NestedInteger(1);
        res.add(one);
        res.add(fourSix);
        return res;
    }

    public static void main(String[] args) {
        Test t1 = new Test(new Solution1());
        t1.depthSumInverseTest(Test.getTestList1(),8);
        t1.depthSumInverseTest(Test.getTestList2(),17);

        Test t2 = new Test(new Solution2());
        t2.depthSumInverseTest(Test.getTestList1(),8);
        t2.depthSumInverseTest(Test.getTestList2(),17);

        Test t3 = new Test(new Solution3());
        t3.depthSumInverseTest(Test.getTestList1(),8);
        t3.depthSumInverseTest(Test.getTestList2(),17);
    }
}