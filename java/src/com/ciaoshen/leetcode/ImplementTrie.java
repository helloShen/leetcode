/**
 * Leetcode - Algorithm - Implement Trie 第一种解法：基于Binary Search Tree的Trie
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ImplementTrie {
    /**
     * 二叉树
     * Trie的主力数据结构
     */
    private static class TreeNode {
        private String val;
        private TreeNode left;
        private TreeNode right;
        private TreeNode(String s) {
            val = s;
        }
        /**
         * BFS，按层打印二叉树
         */
        public String toString() { // bfs
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(this);
            int level = 0;
            while (!queue.isEmpty()) {
                level++;
                sb.append("\nLevel " + level + ": [");
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node != null) {
                        sb.append(" (" + node.val + ")");
                        queue.offer(node.left);
                        queue.offer(node.right);
                    } else {
                        sb.append(" (null)");
                    }
                }
                sb.append("]");
            }
            return sb.toString();
        }
        /**
         * 随机生成一棵平衡二叉树
         */
        private static TreeNode random(int size) {
            TreeNode head = new TreeNode(nextLetter());
            int remain = size-1;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(head);
            while (!queue.isEmpty()) {
                int max = queue.size();
                for (int i = 0; i < max; i++) {
                    TreeNode node = queue.poll();
                    if (remain-- > 0) {
                        node.left = new TreeNode(nextLetter());
                        queue.offer(node.left);
                    }
                    if (remain-- > 0) {
                        node.right = new TreeNode(nextLetter());
                        queue.offer(node.right);
                    }
                }
            }
            return head;
        }
        /**
         * 随机生成一个[a-z]字母，以String的形式返回
         */
        private static String nextLetter() {
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            sb.append((char)(r.nextInt(26)+'a'));
            return sb.toString();
        }
        /**
         * 随机生成特定长度的一个随机字符串，全部由[a-z]小写字母构成
         */
        private static String randomString(int length) {
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append((char)(r.nextInt(26)+'a'));
            }
            return sb.toString();
        }
    }
    /**
     * 基于二叉树的Trie
     * 三个函数 insert(), search()和startsWith()复杂度都为 O(logn)
     */
    public class Trie {
        // 哨兵，内容是一个空字符串。dummy.right节点才是二叉树真正的根节点
        private TreeNode dummy;
        public Trie() {
            dummy = new TreeNode("");
        }

        /**
         * 往Trie插入新单词
         * 不重复插入重复单词
         * 可以接受 "" 空字符
         */
        public void insert(String word) {
            TreeNode pre = dummy, cur = dummy.right;
            boolean goLeft = false;
            while (cur != null) {
                int diff = word.compareTo(cur.val);
                pre = cur;
                if (diff < 0) { // go left
                    cur = cur.left;
                    goLeft = true;
                } else if (diff > 0) { // go right
                    cur = cur.right;
                    goLeft = false;
                } else { // word == cur.val (word already exist)
                    return;
                }
            }
            TreeNode newNode = new TreeNode(word);
            if (goLeft) {
                pre.left = newNode;
            } else {
                pre.right = newNode;
            }
        }

        /**
         * 查询Trie中是否有word
         * 支持 "" 空字符
         */
        public boolean search(String word) {
            TreeNode cur = dummy.right;
            while (cur != null) {
                int diff = cur.val.compareTo(word);
                if (diff < 0) { // go right
                    cur = cur.right;
                } else if (diff > 0) { // go left
                    cur = cur.left;
                } else { // found target
                    return true;
                }
            }
            return false;
        }

        /**
         * 查询Trie中有没有以prefix开头的单词
         * 支持 "" 空字符
         */
        public boolean startsWith(String prefix) {
            String begin = firstLargerEqual(prefix);
            String nextPrefix = nextString(prefix);
            String end = firstLargerEqual(nextPrefix);
            return ((begin == null && end == null) || begin.equals(end))? false : true;
        }
        /**
         * 获得字符在不增加长度情况下进位后的下一个字符串。比如，
         * "aaa" => "bbb"
         * "zzz" => "{"  (注：大括号"{"是ascii表上，"z"后的第一个字符。 "z"=122, "{"=123)
         * "" => "a"
         */
        private String nextString(String word) {
            char[] letters = word.toCharArray();
            int tail = letters.length;
            if (tail == 0) { return "a"; }
            for (int i = letters.length-1; i >= 0; i--) {
                if (letters[i] < 'z') {
                    letters[i] = (char)(letters[i] + 1);
                    break;
                } else {
                    tail--;
                }
            }
            return (tail == 0)? "{" : new String(Arrays.copyOfRange(letters,0,tail));   // "{"是z之后的第一个字符
        }
        /**
         * 返回第一个 >= word 的元素
         * 如果没有（可能Trie为空，或者元素都小于word），返回null
         * 支持 "" 空字符，空字符 "" 是最小的字符，"a > 空字符"
         */
        private String firstLargerEqual(String word) {
            String lastLarger = null;
            TreeNode pre = dummy, cur = dummy.right;
            while (cur != null) {
                int diff = cur.val.compareTo(word);
                if (diff > 0) { // go left
                    lastLarger = cur.val;
                    cur = cur.left;
                } else if (diff < 0) { // go right
                    cur = cur.right;
                } else { // found word
                    return word;
                }
            }
            return lastLarger;
        }

        /**
         * 打印Trie
         */
        public String toString() {
            return (dummy.right == null)? "\nLevel 1: [(null)]" : dummy.right.toString();
        }
    }
    private static ImplementTrie test = new ImplementTrie();
    private static void testTreeNode() {
        System.out.println(TreeNode.random(20));
    }
    private static void testInsert() {
        Trie trie = test.new Trie();
        for (int i = 0; i < 10; i++) {
            String word = TreeNode.nextLetter();
            System.out.println("\n\nAfter Insert \"" + word + "\" to the trie: ");
            trie.insert(word);
            System.out.println(trie);
        }
    }
    private static void testSearch() {
        Trie trie = test.new Trie();
        for (int i = 0; i < 10; i++) {
            trie.insert(TreeNode.nextLetter());
        }
        System.out.println("I have Trie: " + trie);
        for (int i = 0; i < 26; i++) {
            String letter = new String(new char[]{(char)(i+'a')});
            System.out.println("My trie contains letter \"" + letter + "\"? " + trie.search(letter));
        }
    }
    private static void testFirstLargerEqual() {
        Trie trie = test.new Trie();
        for (int i = 0; i < 10; i++) {
            trie.insert(TreeNode.nextLetter());
        }
        System.out.println("I have Trie: " + trie);
        for (int i = 0; i < 26; i++) {
            String letter = new String(new char[]{(char)(i+'a')});
            System.out.println("The first letter larger or equals to \"" + letter + "\" is: " + trie.firstLargerEqual(letter));
        }
    }
    private static void callNextString(String prefix, String ant) {
        Trie trie = test.new Trie();
        System.out.println("The next String for \"" + prefix + "\" is: " + trie.nextString(prefix) + ".     [answer is: " + ant + "]");
    }
    private static void testNextString() {
        String s1 = "";
        String s2 = "aaa";
        String s3 = "zzz";
        String s4 = "abcde";
        callNextString(s1,"a");
        callNextString(s2,"aab");
        callNextString(s3,"{");
        callNextString(s4,"abcdf");
    }
    private static void testStartsWith() {
        Trie trie = test.new Trie();
        for (int i = 0; i < 20; i++) {
            trie.insert(TreeNode.randomString(5));
        }
        System.out.println("I have a trie: ");
        System.out.println(trie);
        for (int i = 0; i < 26; i++) {
            String prefix = new String(new char[]{(char)(i+'a')});
            System.out.println("Has words starts with \"" + prefix + "\"? " + trie.startsWith(prefix));
        }
    }
    private static void testStartsWithEmpty() {
        Trie trieWithEmpty = test.new Trie();
        Trie trieWithoutEmpty = test.new Trie();
        trieWithEmpty.insert("");
        for (int i = 0; i < 26; i++) {
            trieWithEmpty.insert(TreeNode.randomString(3));
            trieWithoutEmpty.insert(TreeNode.randomString(3));
        }
        System.out.println("In trieWithEmpty :");
        System.out.println(trieWithEmpty);
        System.out.println("Has words start with \"\" ?     " + trieWithEmpty.startsWith(""));
        System.out.println("In trieWithoutEmpty :");
        System.out.println(trieWithoutEmpty);
        System.out.println("Has words start with \"\" ?     " + trieWithoutEmpty.startsWith(""));
    }
    public static void main(String[] args) {
        // testInsert();
        // testTreeNode();
        // testSearch();
        // testFirstLargerEqual();
        // testNextString();
        // testStartsWith();
        testStartsWithEmpty();
    }
}
