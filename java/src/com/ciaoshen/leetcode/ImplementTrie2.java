/**
 * Leetcode - Algorithm - Implement Trie - 解法二：基于 Prefix Tree 的Trie
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ImplementTrie2 {
    private static class TreeNode {
        private char val;
        private boolean isWord;
        private TreeNode[] children = new TreeNode[26];
        private TreeNode(char c) {
            val = c;
        }
    }
    public class Trie {
        private TreeNode root = new TreeNode('\u0000');
        /** Initialize your data structure here. */
        public Trie() { }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TreeNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int offset = c - 'a';
                TreeNode node = cur.children[offset];
                if (node == null) {
                    node = new TreeNode(c);
                    cur.children[offset] = node;
                }
                cur = node;
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TreeNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int offset = word.charAt(i) - 'a';
                if (cur.children[offset] == null) {
                    return false;
                } else {
                    cur = cur.children[offset];
                }
            }
            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TreeNode cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                int offset = prefix.charAt(i) - 'a';
                if (cur.children[offset] == null) {
                    return false;
                } else {
                    cur = cur.children[offset];
                }
            }
            return true;
        }

    }
    private static ImplementTrie2 test = new ImplementTrie2();
    private static void callSearch(Trie trie, String s, String ant) {
        System.out.println("Trie contains " + s + " ? " + trie.search(s) + "    [answer is: " + ant + "]");
    }
    private static void callStartsWith(Trie trie, String s, String ant) {
        System.out.println("Trie contains String starts with " + s + " ? " + trie.startsWith(s) + "    [answer is: " + ant + "]");
    }
    private static void testTrie() {
        Trie trie = test.new Trie();
        String s1 = "aaa", s2 = "aaabbb", s3 = "aabb", s4 = "bbb";
        trie.insert(s1);
        trie.insert(s2);
        trie.insert(s3);
        trie.insert(s4);
        callSearch(trie,s2,"true");
        callSearch(trie,"aa","false");
        callStartsWith(trie,s1,"true");
        callStartsWith(trie,"aaaa","false");
    }
    public static void main(String[] args) {
        testTrie();
    }
}
