/**
 * Leetcode - Algorithm - Nested List Weight Sum Two
 */
package com.ciaoshen.leetcode.nested_list_weight_sum_two;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * *************************************************************
 * 2次遍历
 * 第一次遍历先计算最大深度
 * 第二次遍历带着最大深度开始计算
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
class Solution1 implements Solution {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null) { return 0; }
        NestedInteger root = new NestedInteger();
        for (NestedInteger num : nestedList) {
            root.add(num);
        }
        int depth = depth(root);
        return helper(root, depth);
    }

    private int depth(NestedInteger root) {
        // 空节点深度为0
        if (root == null) {
            return 0;
        }
        // 任何不为空的节点起点都是1
        int depth = 1;
        // 如果内部还套嵌了节点，就在所有套嵌节点最大值的基础上再加一
        if (!root.isInteger()) {
            for (NestedInteger sub : root.getList()) {
                if (!sub.isInteger()) {
                    depth = Math.max(depth, depth(sub) + 1);
                }
            }
        }
        return depth;
    }
    // return the [sum, depth] pair of the sub NestedInteger
    private int helper(NestedInteger root, int depth) {
        if (root == null) { return 0; }
        // System.out.println("Now depth = " + depth);
        if (root.isInteger()) {
            return depth * root.getInteger();
        }
        int sum = 0;
        List<NestedInteger> list = root.getList();
        if (list != null) {
            for (NestedInteger num : list) {
                if (num.isInteger()) {
                    // System.out.println("Result + " + num.getInteger());
                    sum += depth * num.getInteger();
                } else {
                    // System.out.println("Sub Integer = " + helper(num, depth - 1));
                    sum += helper(num, depth - 1);
                }
            }
        }
        return sum;
    }
}