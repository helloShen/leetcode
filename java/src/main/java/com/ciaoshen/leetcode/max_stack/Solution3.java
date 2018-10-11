/**
 * Leetcode - max_stack
 */
package com.ciaoshen.leetcode.max_stack;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution3 implements Solution {

    private LinkedList<Integer> numStack;
    private LinkedList<Integer> maxStack;

    public Solution3() {
        numStack = new LinkedList<Integer>();
        maxStack = new LinkedList<Integer>();
    }

    public void push(int x) {
        numStack.push(x);
        maxStack.push((maxStack.isEmpty())? x : Math.max(maxStack.peek(), x));
    }

    public int pop() {
        maxStack.pop();
        return numStack.pop();
    }

    public int top() {
        return numStack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }
    public int popMax() {
        int max = maxStack.peek();
        LinkedList<Integer> temp = new LinkedList<>();
        int num = 0;
        while ((num = numStack.pop()) != max) {
            temp.push(num);
            maxStack.pop();
        }
        maxStack.pop();
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        return max;
    }

}
