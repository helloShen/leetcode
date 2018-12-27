/**
 * Leetcode - valid_parenthesis_string
 */
package com.ciaoshen.leetcode.valid_parenthesis_string;
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

    public boolean checkValidString(String s) {
        List<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) list.add(s.charAt(i));
        if (log.isDebugEnabled()) {
            log.debug("initially, list = {}", list);
        }
        eliminateParenthesis(list);
        if (log.isDebugEnabled()) {
            log.debug("after eliminateParenthesis(), list = {}", list);
        }
        return finishingCheck(list);
    }

    private void eliminateParenthesis(List<Character> list) {
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            char c = list.get(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')' && !stack.isEmpty()) {
                // if (log.isDebugEnabled()) {
                //     log.debug("find )");
                // }
                list.remove(i);
                // if (log.isDebugEnabled()) {
                //     log.debug("remove ) at {}", i);
                // }
                int pair = stack.pop();
                list.remove(pair);
                // if (log.isDebugEnabled()) {
                //     log.debug("remove ( at {}", pair);
                // }
                i -= 2;
            }
        }
    }

    private boolean finishingCheck(List<Character> list) {
        // check leading ')'
        int stack = 0;
        int firstLeftParenthesis = -1;
        for (int i = 0; i < list.size(); i++) {
            char c = list.get(i);
            if (c == '*') {
                stack++;
            } else if (c == ')') {
                if (stack == 0) {
                    return false;
                } else {
                    stack--;
                }
            } else { // c == '('
                firstLeftParenthesis = i;
                break;
            }
        }
        // check following '('
        if (firstLeftParenthesis != -1) {
            if (log.isDebugEnabled()) {
                log.debug("first left parenthesis = {}", firstLeftParenthesis);
            }
            stack = 0;
            for (int i = firstLeftParenthesis; i < list.size(); i++) {
                char c = list.get(i);
                if (c == '(') {
                    stack++;
                    if (log.isDebugEnabled()) {
                        log.debug("find not covered left parenthesis!");
                    }
                } else if (c == '*' && stack > 0) {
                    if (log.isDebugEnabled()) {
                        log.debug("1 more left parenthesis covered!");
                    }
                    stack--;
                }
            }
            if (stack > 0) return false;
        }
        return true;
    }

}
