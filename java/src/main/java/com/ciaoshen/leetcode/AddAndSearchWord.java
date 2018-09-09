/**
 * Leetcode - Algorithm - Add and Search Word (Data Structure Design)
 */
package com.ciaoshen.leetcode;
import java.util.*;

class AddAndSearchWord {
    /** To Test the inner Letter and WordDictionary class, Please comment-out the outer Solution class */
    private static class SolutionV1 {
        /**
         * Based on Map
         */
        private static class Letter {
            private boolean isWord;
            Map<Character,Letter> letters = new HashMap<>();
            private Letter() { }
            public String toString() {
                return inorder(this);
            }
            private String inorder(Letter letter) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<Character,Letter> entry : letter.letters.entrySet()) {
                    sb.append(entry.getKey());
                    sb.append(inorder(entry.getValue()));
                }
                return sb.toString();
            }
        }
        public class WordDictionary {
            private Letter dummy = new Letter();
            /** Initialize your data structure here. */
            public WordDictionary() { }

            /** Adds a word into the data structure. */
            public void addWord(String word) {
                if (word.isEmpty()) { return; }
                Letter cur = dummy;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    Letter nextLetter = cur.letters.get(c);
                    if (nextLetter == null) {
                        Letter newLetter = new Letter();
                        cur.letters.put(c,newLetter);
                        cur = newLetter;
                    } else {
                        cur = nextLetter;
                    }
                }
                cur.isWord = true;
            }

            /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
            public boolean search(String word) {
                return dfs(word,0,dummy);
            }

            public boolean dfs(String word, int offset, Letter cur) {
                char c = word.charAt(offset);
                if (c != '.') { // [a-z]
                    // System.out.println(c + " is not [.]");
                    Letter letter = cur.letters.get(c);
                    // System.out.println("Get words with prefix " + c + ": " + letter);
                    if (letter == null) { // letter not find
                        // System.out.println(c + " not find!");
                        return false;
                    } else { // recursion
                        if (offset == word.length()-1) { // last letter
                            // System.out.println(word + " end at " + c + "? " + letter.isWord);
                            return letter.isWord;
                        } else {
                            return dfs(word,offset+1,letter);
                        }
                    }
                } else { // [.]
                    if (cur.letters.isEmpty()) {
                        // System.out.println("[.] cannot be matched!");
                        return false;
                    }
                    if (offset == word.length()-1) { // last letter
                        for (Map.Entry<Character,Letter> entry : cur.letters.entrySet()) {
                            if (entry.getValue().isWord) {
                                // System.out.println(entry.getKey() +  " can match this [.]");
                                return true;
                            }
                        }
                    } else { // recursion
                        for (Map.Entry<Character,Letter> entry : cur.letters.entrySet()) {
                            if (dfs(word,offset+1,entry.getValue())) {
                                // System.out.println(entry.getKey() +  " can match this [.]");
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
            public String toString() {
                return dummy.toString();
            }
        }
    }
    /** To Test the inner Letter and WordDictionary class, Please comment-out the outer Solution class */
    private static class SolutionV2 {
        /**
         * Based on array
         */
        private static class Letter {
            private char letter;
            private boolean isEnd;
            private Letter[] next = new Letter[26];
            private Letter(char c) {
                letter = c;
            }
            public String toString() {
                return inorder(this);
            }
            private String inorder(Letter letter) {
                StringBuilder sb = new StringBuilder();
                sb.append("[" + letter.letter);
                for (Letter l : letter.next) {
                    if (l != null) {
                        sb.append(inorder(l));
                    }
                }
                sb.append("]");
                return sb.toString();
            }
        }
        public class WordDictionary {
            Letter dummy = new Letter('\u0000');

            /** Adds a word into the data structure. */
            public void addWord(String word) {
                Letter cur = dummy;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    int offset = c - 'a';
                    if (cur.next[offset] == null) {
                        cur.next[offset] = new Letter(c);
                    }
                    cur = cur.next[offset];
                }
                cur.isEnd = true;
            }

            /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
            public boolean search(String word) {
                return dfs(word,0,dummy);
            }

            /** Iterate the Prefix Tree with Depth First Search */
            public boolean dfs(String word, int index, Letter curr) {
                boolean result = false;
                char c = word.charAt(index);
                int offset = c - 'a';
                if (offset >= 0) { // [a-z]
                    Letter letter = curr.next[offset];
                    if (letter == null) {
                        result = false;
                    } else if (index != word.length()-1) {
                        result = dfs(word,index+1,letter);
                    } else {
                        result = letter.isEnd;
                    }
                } else { // [.]
                    for (Letter letter : curr.next) {
                        if (index != word.length()-1) {
                            if (letter != null && dfs(word,index+1,letter)) { result = true; }
                        } else {
                            if (letter != null && letter.isEnd) { result = true; }
                        }
                    }
                }
                return result;
            }
            public String toString() {
                return dummy.toString();
            }
        }
    }
    /** To Test the inner Letter and WordDictionary class, Please comment-out the outer Solution class */
    private static class SolutionV3 {
        /**
         * Based on List
         */
        private static class Letter {
            private char letter;
            private boolean isEnd;
            private List<Letter> next = new ArrayList<>();
            private Letter(char c) {
                letter = c;
            }
            private Letter getNext(char c) {
                for (Letter l : next) {
                    if (l.letter == c) { return l; }
                }
                return null;
            }
            public String toString() {
                return inorder(this);
            }
            private String inorder(Letter letter) {
                StringBuilder sb = new StringBuilder();
                sb.append("[" + letter.letter);
                for (Letter l : letter.next) {
                    if (l != null) {
                        sb.append(inorder(l));
                    }
                }
                sb.append("]");
                return sb.toString();
            }
        }
        public class WordDictionary {
            Letter dummy = new Letter('\u0000');

            /** Adds a word into the data structure. */
            public void addWord(String word) {
                Letter cur = dummy;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    Letter l = cur.getNext(c);
                    if (l == null) {
                        l =  new Letter(c);
                        cur.next.add(l);
                    }
                    cur = l;
                }
                cur.isEnd = true;
            }

            /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
            public boolean search(String word) {
                return dfs(word,0,dummy);
            }

            /** Iterate the Prefix Tree with Depth First Search */
            public boolean dfs(String word, int index, Letter curr) {
                boolean result = false;
                char c = word.charAt(index);
                if (c != '.') { // [a-z]
                    Letter l = curr.getNext(c);
                    if (l == null) {
                        result = false;
                    } else if (index != word.length()-1) {
                        result = dfs(word,index+1,l);
                    } else {
                        result = l.isEnd;
                    }
                } else { // [.]
                    for (Letter l : curr.next) {
                        if (index != word.length()-1) {
                            if (l != null && dfs(word,index+1,l)) { result = true; }
                        } else {
                            if (l != null && l.isEnd) { result = true; }
                        }
                    }
                }
                return result;
            }
            public String toString() {
                return dummy.toString();
            }
        }
    }
    /** To Test the inner Letter and WordDictionary class, Please comment-out the outer Solution class */
    // private static class SolutionV4 {
        /**
         * Based on array - More Concise Version
         */
        private static class Letter {
            private Letter[] postfixs = new Letter[26];
            private boolean isEnd;
            public String toString() {
                return inorder(this);
            }
            private String inorder(Letter letter) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    Letter postfix = letter.postfixs[i];
                    if (postfix != null) {
                        sb.append("[" + (char)(i + 'a'));
                        sb.append(inorder(postfix));
                    }
                }
                sb.append("]");
                return sb.toString();
            }
        }
        public class WordDictionary {
            Letter dummy = new Letter();

            /** Adds a word into the data structure. */
            public void addWord(String word) {
                Letter cur = dummy;
                for (int i = 0; i < word.length(); i++) {
                    int offset = word.charAt(i) - 'a';
                    if (cur.postfixs[offset] == null) {
                        cur.postfixs[offset] = new Letter();
                    }
                    cur = cur.postfixs[offset];
                }
                cur.isEnd = true;
            }

            /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
            public boolean search(String word) {
                return dfs(word,0,dummy);
            }

            /** Iterate the Prefix Tree with Depth First Search */
            public boolean dfs(String word, int index, Letter curr) {
                // base case
                if (curr == null) { return false; } // too deep
                if (index == word.length()) { return curr.isEnd; }
                // recursion
                int offset = word.charAt(index) - 'a';
                if (offset >= 0) { // [a-z]
                    return dfs(word,index+1,curr.postfixs[offset]);
                } else { // [.]
                    for (Letter l : curr.postfixs) {
                        if (dfs(word,index+1,l)) { return true; }
                    }
                }
                return false;
            }
            public String toString() {
                return dummy.toString();
            }
        }
    // }
    private static AddAndSearchWord test = new AddAndSearchWord();
    private static void callWordDictionary(String[] words, String[] testWords, String[] ant) {
        WordDictionary dic = test.new WordDictionary();
        for (String word : words) {
            dic.addWord(word);
        }
        System.out.println("Word Dictionary is: " + dic);
        for (String testWord : testWords) {
            System.out.println("Find " + testWord + "? " + dic.search(testWord));
        }
        System.out.println("Answer is: " + Arrays.toString(ant));
    }
    private static void test() {
        String[] words1 = new String[]{"bad","dad","mad"};
        String[] testWords1 = new String[]{"pad","bad",".ad","b..","b.p"};
        String[] answer1 = new String[]{"false","true","true","true","false"};
        callWordDictionary(words1,testWords1,answer1);
        String[] words2 = new String[]{"a","ab"};
        String[] testWords2 = new String[]{"a","a.","ab",".a",".b","ab.",".",".."};
        String[] answer2 = new String[]{"ture","true","true","false","true","false","true","true"};
        callWordDictionary(words2,testWords2,answer2);
        String[] words3 = new String[]{"at","and","an","add","bat"};
        String[] testWords3 = new String[]{"a",".at","bat","an.","a.d.","b.","a.d","."};
        String[] answer3 = new String[]{"false","true","true","true","false","false","true","false"};
        callWordDictionary(words3,testWords3,answer3);
    }
    public static void main(String[] args) {
        test();
    }
}
