/**
 * Unit Test of Three Sum Problem
 */
package com.ciaoshen.leetcode;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.*;

public class ThreeSumTest {
    private int[] input = new int[] {-1,0,1,2,-1,-4};
    private List<List<Integer>> output = new List<>();
    {
        output.add(new List<Integer>(Arrays.asList(new Integer[] {-1,0,1})));
        output.add(new List<Integer>(Arrays.asList(new Integer[] {-1,-1,2})));
    }
    /*
    @Test
    public void testThreeSum() {
        ThreeSum ts = new ThreeSum();
        List<List<Integer>> result = ts.threeSum(input);
        assert_that()
    }
    */
}
