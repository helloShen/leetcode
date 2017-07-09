/**
 * Leetcode - Algorithm - Word Search
 */
package com.ciaoshen.leetcode;
import java.util.*;

class WordSearch {

    /**
     * [
     *  ['A','B','C','E'],
     *  ['S','F','C','S'],
     *  ['A','D','E','E']
     * ]
     */
    public boolean existV1(char[][] board, String word) {
        if (board.length == 0 || word.length() == 0) { return false; } // 0 x 0 array
        if (board.length == 1 && board[0].length == 1) { // 1 x 1 array
            return (word.length() == 1 && word.charAt(0) == board[0][0]);
        }
        char[] letters = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] used = new boolean[board.length][board[0].length];
                if (scanV1(board,used,letters,0,i,j)) { return true; }
            }
        }
        return false;
    }
    public boolean scanV1(char[][] board, boolean[][] used, char[] letters, int cursor, int row, int col) {
        if (cursor == letters.length) { return true; } // end ?
        if (board[row][col] != letters[cursor] || used[row][col]) { return false; } // verify current ?
        used[row][col] = true; // note the used element
        /* look around */
        if (row > 0) { // not first row
            if (scanV1(board,used,letters,cursor+1,row-1,col)) { return true; } // check higher row
        }
        if (row < board.length-1) { // not last row
            if (scanV1(board,used,letters,cursor+1,row+1,col)) { return true; } // check lower row
        }
        if (col > 0) { // not first colum
            if (scanV1(board,used,letters,cursor+1,row,col-1)) { return true; } // check left column
        }
        if (col < board[0].length-1) { // not last column
            if (scanV1(board,used,letters,cursor+1,row,col+1)) { return true; } // check right column
        }
        used[row][col] = false; // 注意回溯，一条路径一旦失败，所有标记used的全部改回来。
        return false;
    }
    public boolean existV2(char[][] board, String word) {
        if (board.length == 0 || word.length() == 0) { return false; } // 0 x 0 array
        if (board.length == 1 && board[0].length == 1) { // 1 x 1 array
            return (word.length() == 1 && word.charAt(0) == board[0][0]);
        }
        char[] letters = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (scanV2(board,letters,0,i,j)) { return true; }
            }
        }
        return false;
    }
    public boolean scanV2(char[][] board, char[] letters, int cursor, int row, int col) {
        if (cursor == letters.length) { return true; } // end ?
        if (board[row][col] != letters[cursor]) { return false; } // verify current ?
        board[row][col] = (char)((int)board[row][col] ^ 256); // 取补码，标记已用
        /* look around */
        if (row > 0) { // not first row
            if (scanV2(board,letters,cursor+1,row-1,col)) { return true; } // check higher row
        }
        if (row < board.length-1) { // not last row
            if (scanV2(board,letters,cursor+1,row+1,col)) { return true; } // check lower row
        }
        if (col > 0) { // not first colum
            if (scanV2(board,letters,cursor+1,row,col-1)) { return true; } // check left column
        }
        if (col < board[0].length-1) { // not last column
            if (scanV2(board,letters,cursor+1,row,col+1)) { return true; } // check right column
        }
        board[row][col] = (char)((int)board[row][col] ^ 256); // 注意回溯，一条路径一旦失败，所有标记used的全部改回来。
        return false;
    }
    public boolean existV3(char[][] board, String word) {
        if (board.length == 0) { return false; }
        char[] letters = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (scanV3(board,letters,0,i,j)) { return true; }
            }
        }
        return false;
    }
    public boolean scanV3(char[][] board, char[] letters, int cursor, int row, int col) {
        if (cursor == letters.length) { return true; }
        if (row < 0 || row == board.length || col < 0 || col == board[row].length) { return false; } // 出界
        if (board[row][col] != letters[cursor]) { return false; }
        board[row][col] = (char)((int)board[row][col] ^ 256);
        boolean left = scanV3(board,letters,cursor+1,row,col-1);
        boolean right = scanV3(board,letters,cursor+1,row,col+1);
        boolean high = scanV3(board,letters,cursor+1,row-1,col);
        boolean low = scanV3(board,letters,cursor+1,row+1,col);
        boolean res = left || right || high || low;
        board[row][col] = (char)((int)board[row][col] ^ 256);
        return res;
    }
    public boolean existV4(char[][] board, String word) {
        if (board.length == 0) { return false; }
        char[] letters = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (scanV4(board,letters,0,i,j)) { return true; }
            }
        }
        return false;
    }
    public boolean scanV4(char[][] board, char[] letters, int cursor, int row, int col) {
        if (cursor == letters.length) { return true; }
        if (row < 0 || row == board.length || col < 0 || col == board[row].length) { return false; } // 出界
        if (board[row][col] != letters[cursor]) { return false; }
        board[row][col] = (char)((int)board[row][col] ^ 256);
        boolean res =  scanV4(board,letters,cursor+1,row,col-1)
                    || scanV4(board,letters,cursor+1,row,col+1)
                    || scanV4(board,letters,cursor+1,row-1,col)
                    || scanV4(board,letters,cursor+1,row+1,col);
        board[row][col] = (char)((int)board[row][col] ^ 256);
        return res;
    }
    public boolean exist(char[][] board, String word) {
        if (board.length == 0) { return false; }
        char[] letters = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (scan(board,letters,0,i,j)) { return true; }
            }
        }
        return false;
    }
    public boolean scan(char[][] board, char[] letters, int cursor, int row, int col) {
        if (cursor == letters.length) { return true; }
        if (row < 0 || row == board.length || col < 0 || col == board[row].length) { return false; } // 出界
        if (board[row][col] != letters[cursor]) { return false; }
        board[row][col] = (char)((int)board[row][col] ^ 256);
        if (scan(board,letters,cursor+1,row,col-1)) { return true; }
        if (scan(board,letters,cursor+1,row,col+1)) { return true; }
        if (scan(board,letters,cursor+1,row-1,col)) { return true; }
        if (scan(board,letters,cursor+1,row+1,col)) { return true; }
        board[row][col] = (char)((int)board[row][col] ^ 256);
        return false;
    }
    private static void testExist() {
        WordSearch test = new WordSearch();
        char[][] board1 = new char[][]{
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        char[][] board2 = new char[][]{
            {'C','A','A'},
            {'A','A','A'},
            {'B','C','D'}
        };
        char[][] board3 = new char[][]{
            {'A','B','C','E'},
            {'S','F','E','S'},
            {'A','D','E','E'}
        };
        String[] words1 = new String[]{"ABCCED","SEE","ABCB"};
        String[] words2 = new String[]{"AAB"};
        String[] words3 = new String[]{"ABCESEEEFS"};
        for (String word : words1) {
            System.out.println(word + " ???\t" + test.exist(board1,word));
        }
        for (String word : words2) {
            System.out.println(word + " ???\t" + test.exist(board2,word));
        }
        for (String word : words3) {
            System.out.println(word + " ???\t" + test.exist(board3,word));
        }
    }
    public static void main(String[] args) {
        testExist();
    }
}
