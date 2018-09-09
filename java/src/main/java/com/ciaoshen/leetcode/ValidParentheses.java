/**
 * Leetcode - Valid Parentheses
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class ValidParentheses {
    private Map<Character,Character> parentheses = new HashMap<>();
    {
        parentheses.put('(',')');
        parentheses.put('[',']');
        parentheses.put('{','}');
    }

    public boolean isValidV1(String s) {
        if (s == null || s.length() == 0) { return false; }
        char[] chars = s.toCharArray();
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < chars.length; i++) {
            if (parentheses.containsKey(chars[i])) {
                stack.push(chars[i]);
                continue;
            }
            if (parentheses.containsValue(chars[i])) {
                Character top = stack.peek();
                if (top == null) {
                    System.out.println("Exit2: Stack里没有内容！");
                    return false;
                } else {
                    if (parentheses.get(top) == chars[i]) {
                        stack.pop();
                        continue;
                    } else {
                        System.out.println("Exit1: 关闭符" + chars[i] + "和上一个开始符" + top + "匹配不上！");
                        return false;
                    }
                }
            }
        }
        if (stack.size() == 0) {
            System.out.println("YES!! 正确匹配！");
            return true;
        } else {
            System.out.println("Exit3: Stack里还有开始符号没有被关闭！");
            return false;
        }
    }

    public boolean isValidV2(String s) {
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // next char
            if (parentheses.containsKey(c)) {
                stack.push(c);
                continue;
            }
            Character top = stack.poll(); // Top char in Stack
            if (top == null) { return false; }
            if (parentheses.get(top) != c) { return false; }
        }
        return (stack.size() == 0)? true : false;
    }

    public boolean isValidV3(String s) {
        Deque<Character> stack = new LinkedList<Character>();
        for (char c : s.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
                continue;
            }
            Character top = stack.poll();
            if (top == null) { return false; }
            if (c - top != 2 && c - top != 1) { return false; }
        }
        return (stack.size() == 0)? true : false;
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<Character>();
        for (char c : s.toCharArray()) {
            if (c == '{') { stack.push('}'); continue; }
            if (c == '[') { stack.push(']'); continue; }
            if (c == '(') { stack.push(')'); continue; }
            if (stack.isEmpty() || stack.pop() != c) { return false; }
        }
        return stack.isEmpty();
    }

    private static void testIsValid() {
        ValidParentheses vp = new ValidParentheses();
        System.out.println(vp.parentheses);
        String right1 = "()";
        String right2 = "{[()]}";
        String right3 = "{}[]()";
        String wrong1 = "([)]"; // exit 1: 关闭符号和上一个打开符号匹配不上。
        String wrong2 = ")]"; // exit 2: stack里还没有打开符号，直接来了关闭符号。
        String wrong3 = "{{{[()]}"; // exit 3: stack里还有打开符号没有被关上。
        System.out.println(right1 + ": " + vp.isValid(right1));
        System.out.println(right2 + ": " + vp.isValid(right2));
        System.out.println(right3 + ": " + vp.isValid(right3));
        System.out.println(wrong1 + ": " + vp.isValid(wrong1));
        System.out.println(wrong2 + ": " + vp.isValid(wrong2));
        System.out.println(wrong3 + ": " + vp.isValid(wrong3));
    }
    public static void main(String[] args) {
        testIsValid();
    }
}
