/**
 * Integer to Roman
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class IntegerToRoman {
    private static final Map<Integer,String> DIC = new HashMap<>();
    static {
        DIC.put(0,"");
        DIC.put(1,"I");
        DIC.put(2,"II");
        DIC.put(3,"III");
        DIC.put(4,"IV");
        DIC.put(5,"V");
        DIC.put(6,"VI");
        DIC.put(7,"VII");
        DIC.put(8,"VIII");
        DIC.put(9,"IX");
        DIC.put(10,"X");
        DIC.put(20,"XX");
        DIC.put(30,"XXX");
        DIC.put(40,"XL");
        DIC.put(50,"L");
        DIC.put(60,"LX");
        DIC.put(70,"LXX");
        DIC.put(80,"LXXX");
        DIC.put(90,"XC");
        DIC.put(100,"C");
        DIC.put(200,"CC");
        DIC.put(300,"CCC");
        DIC.put(400,"CD");
        DIC.put(500,"D");
        DIC.put(600,"DC");
        DIC.put(700,"DCC");
        DIC.put(800,"DCCC");
        DIC.put(900,"CM");
        DIC.put(1000,"M");
        DIC.put(2000,"MM");
        DIC.put(3000,"MMM");
    }
    public static String intToRomanV1(int num) {
        int[] digits = new int[]{0,0,0,0}; // num < 4000
        int carry = 0;
        while (num > 0) { // 327 = [7,20,300,0]
            digits[carry] = num%10 * (int)Math.pow(10,carry);
            num = num/10;
            carry++;
        }
        System.out.println(Arrays.toString(digits));
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) { // 300=CCC, 20=XX, 7=VII, then, 327=CCXXVII
            sb.append(DIC.get(digits[i]));
        }
        return sb.toString();
    }
    private static final char[][] SYM = new char[][] {
        {'I','V','X'},
        {'X','L','C'},
        {'C','D','M'},
        {'M',Character.MIN_VALUE,Character.MIN_VALUE}
    };
    public static String intToRomanV2(int num) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (num > 0) { // 327 = [7,2,3,0]
            generate(num%10,SYM[carry++],sb);
            num = num/10;
        }
        return sb.toString();
    }
    // Roman[1,2,3,4,5,6,7,8,9,10]: [I,II,III,IV,V,VI,VII,VIII,IX,X]
    // Roman[10,20,30,40,50,60,70,80,90,100]: [X,XX,XXX,XL,L,LX,LXX,LXXX,XC,C]
    // ...
    public static void generate(int num, char[] symbol, StringBuilder sb) { // num = [0...9]
        StringBuilder temp = new StringBuilder();
        if (num < 4) {
            for (int i = 0; i < num; i++) {
                temp = temp.append(symbol[0]);
            }
        } else if (num == 4) {
            temp = temp.append(symbol[0]).append(symbol[1]);
        } else if (num < 9) {
            temp = temp.append(symbol[1]);
            for (int i = 0; i< num-5; i++) {
                temp = temp.append(symbol[0]);
            }
        } else if (num == 9) {
            temp = temp.append(symbol[0]).append(symbol[2]);
        } else {
            throw new NumberFormatException();
        }
        sb = sb.insert(0,temp);
    }

    private static String[][] SYMARRAY = new String[][] {
        {"","I","II","III","IV","V","VI","VII","VIII","IX"}, // 0,1,2,3,...,9
        {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"}, // 0,10,20,30,...,90
        {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"}, // 0,100,200,300,...,900
        {"","M","MM","MMM",null,null,null,null,null,null} // 0,1000,2000,3000
    };
    public static String intToRomanV3(int num) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (num > 0) { // 327 = [7,2,3,0]
            sb.insert(0,SYMARRAY[carry++][num%10]);
            num = num/10;
        }
        return sb.toString();
    }

    public static String intToRoman(int num) {
        return SYMARRAY[3][num/1000] + SYMARRAY[2][num%1000/100] + SYMARRAY[1][num%100/10] + SYMARRAY[0][num%10];
    }

    private static void testIntToRoman() {
        int one = 1;
        int nine = 9;
        int ten = 10;
        int nightyNine = 99;
        int hundred = 100;
        int threeTwoSeven = 327;
        int eightEightEight = 888;
        int k = 1024;
        System.out.println(one + " = " + intToRoman(one));
        System.out.println(nine + " = " + intToRoman(nine));
        System.out.println(ten + " = " + intToRoman(ten));
        System.out.println(nightyNine + " = " + intToRoman(nightyNine));
        System.out.println(hundred + " = " + intToRoman(hundred));
        System.out.println(threeTwoSeven + " = " + intToRoman(threeTwoSeven));
        System.out.println(eightEightEight + " = " + intToRoman(eightEightEight));
        System.out.println(k + " = " + intToRoman(k));
    }
    public static void main(String[] args) {
        testIntToRoman();
    }
}
