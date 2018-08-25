/**
 * Leetcode - Algorithm - Nested List Weight Sum Two
 */
package com.ciaoshen.leetcode.nested_list_weight_sum_two;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * *************************************************************
 * 1次遍历
 * *************************************************************
 * 
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution2 implements Solution {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null) { return 0; } // defense
        int depth = 0; 
        int sum = 0;
        Map<Integer, Integer> levelSum = new HashMap<>();
        while (!nestedList.isEmpty()) {
            depth++;
            sum = 0;
            int size = nestedList.size();
            for (int i = 0; i < size; i++) {
                NestedInteger node = nestedList.remove(0);
                if (node.isInteger()) {
                    sum += node.getInteger();
                } else {
                    nestedList.addAll(node.getList());
                }
            }
            levelSum.put(depth, sum); // 统计每层未加权的数字和
        }
        depth++;
        int result = 0;
        for (Map.Entry<Integer, Integer> pair : levelSum.entrySet()) {
            result += (depth - pair.getKey()) * pair.getValue();
        }
        return result;
    }
}