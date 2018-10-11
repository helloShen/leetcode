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
class Solution1 implements Solution {

    LinkedList<Pair> list;

    public Solution1() {
        list = new LinkedList<Pair>();
    }

    public void push(int x) {
        list.add(new Pair(x));
    }

    public int pop() {
        return list.removeLast().val;
    }

    public int top() {
        return list.getLast().val;
    }

    public int peekMax() {
        if (list.isEmpty()) return 0;
        int max = list.getFirst().val;
        for (Pair p : list) {
            max = Math.max(max, p.val);
        }
        return max;
    }

    public int popMax() {
        if (list.isEmpty()) return 0;
        Pair max = list.getFirst();
        for (Pair p : list) {
            if (p.val >= max.val) max = p;
        }
        list.remove(max);
        return max.val;
    }

    private int id;
    private class Pair {
        int idx;
        int val;
        private Pair(int x) {
            idx = id++;
            val = x;
        }
    }

}
