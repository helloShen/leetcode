/**
 * Leetcode - Algorithm - Simplify Path
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SimplifyPath {
    public String simplifyPathV1(String path) {
        // [./]是为了跳过开头的./
        // [.]为了跳过单独的[.]
        List<String> deleteSelf = new ArrayList<>(Arrays.asList(new String[]{ "./","." }));
        // [../]是正常情况
        // [..]是为了应付[/..]这样的情况
        List<String> deleteSelfAndLast = new ArrayList<>(Arrays.asList(new String[]{ "../",".." }));
        List<String> snippets = splitSlash(path); // 切完是这样，斜杠在后：[/] [a/] [./] [b/] [../] [../] [c/]
        List<String> res = new LinkedList<>();
        for (String str : snippets) {
            if (deleteSelf.contains(str)) { continue; }
            if (deleteSelfAndLast.contains(str)) {
                if (!res.isEmpty()) {
                    boolean lastIsSlash = res.get(res.size()-1).equals("/");
                    if (!lastIsSlash) { // [/../]的情况不能删第一个[/]
                        res.remove(res.size()-1);
                    }
                    continue;
                }
            }
            res.add(str);
        }
        if (endWithSlash(res)) { // 删除末尾多余的[/]
            removeEndSlash(res);
        }
        StringBuilder sb = new StringBuilder();
        for (String str : res) {
            sb.append(str);
        }
        return sb.toString();
    }
    public List<String> splitSlash(String s) {
        List<String> res = new ArrayList<>();
        if (s.isEmpty()) { return res; }
        int slow = 0, fast = 0;
        while (fast < s.length()) {
            if (s.charAt(fast) == '/') {
                if (fast > 0 && s.charAt(fast-1) == '/') {
                    //System.out.println("Skip duplicate slash!");
                    fast++; slow++;
                    continue;
                } // 跳过重复的[/]
                //System.out.println("slow=" + slow + ", fast=" + fast);
                res.add(s.substring(slow,fast+1));
                slow = fast+1;
            }
            fast++;
        }
        if (slow != s.length()) {
            res.add(s.substring(slow));
        }
        return res;
    }
    public boolean endWithSlash(List<String> list) {
        if (list.isEmpty()) { return false; }
        String s = list.get(list.size()-1);
        if (s.equals("/")) { return false; }; // 只是[/]的情况，不能删[/]
        int size = s.length();
        return (size > 0) && (s.charAt(size-1) == '/');
    }
    public void removeEndSlash(List<String> list) {
        String str = list.remove(list.size()-1);
        list.add(str.substring(0,str.length()-1));
    }
    // 处理不了：[/...][/.../]
    public String simplifyPathV2(String path) {
        if (path.isEmpty()) { return path; }
        char[] chars = path.toCharArray();
        //System.out.println(Arrays.toString(chars));
        Deque<Character> stack = new LinkedList<Character>();
        boolean headSlash = (chars[0] == '/')? true:false; //起始的[/]要特殊处理
        Character root = '@';
        if (headSlash) {
            chars[0] = root;
        }
        boolean waitNextDot = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '/' && !stack.isEmpty() && (stack.peekFirst() == '/' || stack.peekFirst() == root)) { continue; } // 避免重复[/]
            if (chars[i] == '.') {
                if (waitNextDot) { // 遇到[..]，上级菜单
                    waitNextDot = false;
                    if (!stack.isEmpty() && stack.peekFirst() == '/') { stack.removeFirst(); }
                    while (!stack.isEmpty() && stack.peekFirst() != '/' && stack.peekFirst() != root) {
                        stack.removeFirst();
                        if (stack.isEmpty()) { break; }
                    }
                } else { // 单独[.]的情况
                    waitNextDot = true;
                }
                continue;
            }
            stack.offerFirst(chars[i]);
            waitNextDot = false;
        }
        if (!stack.isEmpty() && stack.peekFirst() == '/') { stack.removeFirst(); } // 清理末尾[/]的情况
        if (headSlash) { // 归还起始的代表根目录的[/]
            stack.pollLast();
            stack.addLast('/');
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
    public String simplifyPathV3(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) {
                //System.out.println("Pop :" + stack.peek());
                stack.pop();
            }
            else if (!skip.contains(dir)) { stack.push(dir); }
        }
        String res = "";
        for (String dir : stack) res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;
    }
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<String>();
        String[] segments = path.split("/");
        Set<String> skip = new HashSet<>(Arrays.asList(new String[]{"",".",".."}));
        Set<String> pop = new HashSet<>(Arrays.asList(new String[]{".."}));
        for (String str : segments) {
            if (pop.contains(str) && !stack.isEmpty()) { stack.removeFirst(); continue; }
            if (!skip.contains(str)) { stack.offerFirst(str); }
        }
        String res = "";
        while (!stack.isEmpty()) { res += ("/" + stack.pollLast()); }
        return (res == "")? "/":res;
    }
    private static SimplifyPath test = new SimplifyPath();
    private static String[] testCases = new String[]{
        //".",
        "/",
        //"..",
        //"./",
        //"../",
        "/../",
        "/..",
        "/...",
        "/a",
        //"./a",
        //"../a",
        "/home/",
        "/home//a/b",
        "/a/./b/../../c/",
        //"a/./b/../../c/",
        //"a/"
    };
    private static void testSimplifyPath() {
        for (String str : testCases) {
            System.out.println(str + "   -->    " + test.simplifyPath(str));
        }
    }
    public static void main(String[] args) {
        testSimplifyPath();
    }
}
