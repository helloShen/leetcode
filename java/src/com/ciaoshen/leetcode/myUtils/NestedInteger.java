/**
 * NestedInteger for Leetcode problem: Flatten Nested List Iterator
 */
package com.ciaoshen.leetcode.myUtils;
import java.util.*;

public class NestedInteger {
    private Integer num = null;
    private List<NestedInteger> nums = null;
    public NestedInteger(int num) {
        this.num = num;
    }
    public NestedInteger(int[] in) {
        nums = new ArrayList<NestedInteger>();
        for (int num : in) {
            nums.add(new NestedInteger(num));
        }
    }
    public boolean isInteger() {
        return nums == null;
    }
    public Integer getInteger() {
        return num;
    }
    public List<NestedInteger> getList() {
        return nums;
    }
    public boolean add(NestedInteger n) {
        if (isInteger()) {
            return false;
        } else {
            nums.add(n);
            return true;
        }
    }
}
