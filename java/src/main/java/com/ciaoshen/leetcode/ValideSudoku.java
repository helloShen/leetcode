/**
 * Leetcode - Valide Sudoku
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ValideSudoku {
    public boolean isValidSudokuV1(char[][] board) {
        if (! checkSizeV1(board)) {
            //System.out.println("Size Wrong!");
            return false;
        }
        // assertion: size of array = 9 x 9
        List<Character> nums = new ArrayList<>(Arrays.asList(new Character[]{'1','2','3','4','5','6','7','8','9'}));
        if (! checkLineV1(board,nums)) {
            //System.out.println("Lines Sum Wrong!");
            return false;
        }
        if (! checkColumnV1(board,nums)) {
            //System.out.println("Columns Sum Wrong!");
            return false;
        }
        if (! checkSubBoxV1(board,nums)) {
            //System.out.println("SubBox Sum Wrong!");
            return false;
        }
        return true;
    }
    public boolean checkSizeV1(char[][] board) {
        int height = board.length;
        if (height != 9) { return false; } // must have 9 lines
        for (int i = 0; i < height; i++) {
            if (board[i].length != 9) { return false; } // must have 9 columns for each line
        }
        return true;
    }
    public boolean checkLineV1(char[][] board, List<Character> nums) {
        for (int i = 0; i < board.length; i++) { // loop line
            List<Character> copyNums = new ArrayList<>(nums);
            for (int j= 0; j < board.length; j++) { // loop column
                char num = board[i][j];
                if (num != '.' && ! copyNums.contains(num)) {
                    return false;
                } else if (num != '.') {
                    copyNums.remove(new Character(num));
                }
            }
        }
        return true;
    }
    public boolean checkColumnV1(char[][] board, List<Character> nums) {
        for (int j = 0; j < board.length; j++) { // loop column
            List<Character> copyNums = new ArrayList<>(nums);
            for (int i= 0; i < board.length; i++) { // loop line
                char num = board[i][j];
                if (num != '.' && ! copyNums.contains(num)) {
                    return false;
                } else if (num != '.') {
                    copyNums.remove(new Character(num));
                }
            }
        }
        return true;
    }
    public boolean checkSubBoxV1(char[][] board, List<Character> nums) {
        for (int i = 0; i < 3; i++) { // loop line
            for (int j = 0; j < 3; j++) { // loop column
                List<Character> copyNums = new ArrayList<>(nums);
                if (! checkThreeThreeBoxV1(board,i*3,j*3,copyNums)) { return false; }
            }
        }
        return true;
    }
    public boolean checkThreeThreeBoxV1(char[][] board, int startHeight, int startWidth, List<Character> nums) {
        for (int i = startHeight; i < startHeight+3; i++) { // loop line
            for (int j = startWidth; j < startWidth+3; j++) { // loop column
                char num = board[i][j];
                if (num != '.' && ! nums.contains(num)) {
                    //System.out.println("SubBox Wrong, Position: " + "[" + i + "," + j + "]");
                    return false;
                } else if (num != '.') {
                    nums.remove(new Character(num));
                }
            }
        }
        return true;
    }

    public boolean isValidSudokuV2(char[][] board) {
        if (board.length != 9 || board[0].length != 9) { return false; }
        // assertion: size of array = 9 x 9
        if (! checkLineV2(board)) { return false; }
        if (! checkColumnV2(board)) { return false; }
        if (! checkSubBoxV2(board)) { return false; }
        return true;
    }
    // if not duplicate number return the updated bitMap
    // return MAX_VALUE if duplicate found
    public int checkBitMap(int bitMap, char c) {
        int num = c - '0';
        if (num > 0 && num <= 9) { // 0-9的数字
            int mask = 1 << (num-1);
            if ( (bitMap & mask) == mask ) { // 数字重复
                return Integer.MAX_VALUE;
            } else {
                bitMap = bitMap | mask; // 数字没出现过，就把数字写进bitMap
            }
        } else {
            if (c != '.') { return Integer.MAX_VALUE; } //错误数字，既不是0-9，又不是‘.’
        }
        return bitMap;
    }
    public boolean checkLineV2(char[][] board) {
        for (int i = 0; i < board.length; i++) { // loop line
            int bitMap = 0;
            for (int j= 0; j < board.length; j++) { // loop column
                bitMap = checkBitMap(bitMap,board[i][j]);
                if (bitMap == Integer.MAX_VALUE) { return false; }
            }
        }
        return true;
    }
    public boolean checkColumnV2(char[][] board) {
        for (int j = 0; j < board.length; j++) { // loop column
            int bitMap = 0;
            for (int i= 0; i < board.length; i++) { // loop line
                bitMap = checkBitMap(bitMap,board[i][j]);
                if (bitMap == Integer.MAX_VALUE) { return false; }
            }
        }
        return true;
    }
    public boolean checkSubBoxV2(char[][] board) {
        for (int i = 0; i < 3; i++) { // loop line
            for (int j = 0; j < 3; j++) { // loop column
                if (! checkThreeThreeBoxV2(board,i*3,j*3)) { return false; }
            }
        }
        return true;
    }
    public boolean checkThreeThreeBoxV2(char[][] board, int startHeight, int startWidth) {
        int bitMap = 0;
        for (int i = startHeight; i < startHeight+3; i++) { // loop line
            for (int j = startWidth; j < startWidth+3; j++) { // loop column
                bitMap = checkBitMap(bitMap,board[i][j]);
                if (bitMap == Integer.MAX_VALUE) { return false; }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9) { return false; }
        // assertion: size of array = 9 x 9
        for (int i = 0; i < board.length; i++) {
            int lineBitMap = 0;
            int columnBitMap = 0;
            int boxBitMap = 0;
            for (int j = 0; j < board.length; j++) {
                // check for line
                lineBitMap = checkBitMap(lineBitMap,board[i][j]);
                if (lineBitMap == Integer.MAX_VALUE) { return false; }
                // check for column
                columnBitMap = checkBitMap(columnBitMap,board[j][i]);
                if (columnBitMap == Integer.MAX_VALUE) { return false; }
                // check for each box
                int boxLineIndex = i/3;
                int boxColumnIndex = i%3;
                boxBitMap = checkBitMap(boxBitMap,board[boxLineIndex*3+j/3][boxColumnIndex*3+j%3]);
                if (boxBitMap == Integer.MAX_VALUE) { return false; }
            }
        }
        return true;
    }

    private static char[][] wrong1 = new char[][] { // 6x6。 大小不对
        {'5','3','.','.','7','.'},
        {'6','.','.','1','9','5'},
        {'.','9','8','.','.','.'},
        {'8','.','.','.','6','.'},
        {'4','.','.','8','.','3'},
        {'.','.','.','.','8','.'}
    };
    private static char[][] wrong2 = new char[][] {
        {'5','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','0','5','.','.','.'}, // 0不该出现
        {'.','9','8','.','.','.','.','6','.'},
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','3','.','.','1'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'},
        {'.','.','.','.','8','.','.','7','9'}
    };
    private static char[][] wrong3 = new char[][] {
        {'5','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','9','5','.','.','.'},
        {'.','9','8','.','.','.','.','6','.'},
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','8','.','.','1'}, // 2个8，重复了
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'},
        {'.','.','.','.','8','.','.','7','9'}
    };
    private static char[][] right1 = new char[][] { // 为空，肯定对
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','1','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'}
    };
    private static char[][] right2 = new char[][] {
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','1','.','.','.','.','.','.'}, // 只有一个1，肯定对
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.'}
    };
    private static char[][] right3 = new char[][] {
        {'5','3','.','.','7','.','.','.','.'},
        {'6','.','.','1','9','5','.','.','.'},
        {'.','9','8','.','.','.','.','6','.'},
        {'8','.','.','.','6','.','.','.','3'},
        {'4','.','.','8','.','3','.','.','1'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','6','.','.','.','.','2','8','.'},
        {'.','.','.','4','1','9','.','.','5'},
        {'.','.','.','.','8','.','.','7','9'}
    };
    private static void testIsValidSudoku() {
        ValideSudoku test = new ValideSudoku();
        System.out.println("Wrong1 is " + test.isValidSudoku(wrong1));
        System.out.println("Wrong2 is " + test.isValidSudoku(wrong2));
        System.out.println("Wrong3 is " + test.isValidSudoku(wrong3));
        System.out.println("Right1 is " + test.isValidSudoku(right1));
        System.out.println("Right2 is " + test.isValidSudoku(right2));
        System.out.println("Right3 is " + test.isValidSudoku(right3));
    }
    public static void main(String[] args) {
        testIsValidSudoku();
    }
}
