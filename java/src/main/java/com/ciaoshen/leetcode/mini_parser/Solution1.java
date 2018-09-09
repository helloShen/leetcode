/**
 * Leetcode - #385 - Mini Parser
 */
package com.ciaoshen.leetcode.mini_parser;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
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
    public NestedInteger deserialize(String s) {
        init();
        NestedInteger dummy = new NestedInteger();
        NestedInteger curr = dummy;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                NestedInteger newNI = new NestedInteger();
                curr.add(newNI);
                stack.push(curr);
                curr = newNI;
            } else if (c == ']' || c == ',') {
                if (num != null) {
                    curr.add(new NestedInteger(num * sign));
                    num = null;
                    sign = 1;
                }
                if (c == ']') {
                    curr = stack.pop();
                }
            } else if (c =='-') {
                num = 0;
                sign = -1;
            } else { // is digit
                if (num == null) {
                    num = 0;
                }
                num = num * 10 + (c - '0');
            }
        }
        if (num != null) { // dump
            curr.add(new NestedInteger(num * sign));
        }
        return dummy.getList().get(0);
    }
    
    private int sign;
    private Integer num;
    private Deque<NestedInteger> stack;
    
    private void init() {
        sign = 1;
        num = null;
        stack = new LinkedList<NestedInteger>();
    }
}