/**
 * Leetcode - Generate Parentheses
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class GenerateParenthesis {
    public List<String> generateParenthesisWrong(int n) {
        if (n <= 0) { return new ArrayList<String>(); }
        // base case
        if (n == 1) { return new ArrayList<String>(Arrays.asList(new String[]{"()"})); }
        // Recursive
        List<String> core = generateParenthesis(n-1);
        Set<String> decorated = new HashSet<>();
        for (String s : core) {
            decorated.add("()" + s);
            decorated.add("(" + s + ")");
            decorated.add(s + "()");
        }
        return new ArrayList<String>(decorated);
    }
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0) { return list; }
        generateParenthesisRecursive(list,"",n,0);
        return list;
    }
    public void generateParenthesisRecursive(List<String> list, String str, int n, int stack) {
        if (n == 0 && stack == 0) { list.add(str); return; }
        if (stack > 0) { generateParenthesisRecursive(list,str+")",n,stack-1); }
        if (n > 0) { generateParenthesisRecursive(list,str+"(",n-1,stack+1); }
    }

    private static void testGenerateParenthesis() {
        GenerateParenthesis gp = new GenerateParenthesis();
        System.out.println(gp.generateParenthesis(0));
        System.out.println(gp.generateParenthesis(1));
        System.out.println(gp.generateParenthesis(2));
        System.out.println(gp.generateParenthesis(3));
        System.out.println(gp.generateParenthesis(4));
    }

    public static void main(String[] args) {
        testGenerateParenthesis();
    }
}
