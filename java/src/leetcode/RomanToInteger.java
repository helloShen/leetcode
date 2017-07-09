/**
 * Roman to Integer
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class RomanToInteger {
    private static final Map<String,Integer> DIC = new HashMap<>();
    static {
        DIC.put("",0);
        DIC.put("I",1);
        DIC.put("II",2);
        DIC.put("III",3);
        DIC.put("IV",4);
        DIC.put("V",5);
        DIC.put("VI",6);
        DIC.put("VII",7);
        DIC.put("VIII",8);
        DIC.put("IX",9);
        DIC.put("X",10);
        DIC.put("XX",20);
        DIC.put("XXX",30);
        DIC.put("XL",40);
        DIC.put("L",50);
        DIC.put("LX",60);
        DIC.put("LXX",70);
        DIC.put("LXXX",80);
        DIC.put("XC",90);
        DIC.put("C",100);
        DIC.put("CC",200);
        DIC.put("CCC",300);
        DIC.put("CD",400);
        DIC.put("D",500);
        DIC.put("DC",600);
        DIC.put("DCC",700);
        DIC.put("DCCC",800);
        DIC.put("CM",900);
        DIC.put("M",1000);
        DIC.put("MM",2000);
        DIC.put("MMM",3000);
    }

    public static int romanToIntV1(String s) {
        int length = s.length();
        int high = 0, result = 0;
        while (high < length) {
            int low = length;
            while (low > high) {
                Integer num = DIC.get(s.substring(high,low--));
                if (num != null) {
                    result += num;
                    high = low+1;
                    break;
                }
            }
        }
        return result;
    }

    public static int romanToInt(String s) {
        char c[]=s.toCharArray();
        int sum=0;
        for(int count = 0; count <= s.length()-1; count++){
           if(c[count]=='M') sum+=1000;
           if(c[count]=='D') sum+=500;
           if(c[count]=='C') sum+=100;
           if(c[count]=='L') sum+=50;
           if(c[count]=='X') sum+=10;
           if(c[count]=='V') sum+=5;
           if(c[count]=='I') sum+=1;
        }
        if(s.indexOf("IV")!=-1){sum-=2;}
        if(s.indexOf("IX")!=-1){sum-=2;}
        if(s.indexOf("XL")!=-1){sum-=20;}
        if(s.indexOf("XC")!=-1){sum-=20;}
        if(s.indexOf("CD")!=-1){sum-=200;}
        if(s.indexOf("CM")!=-1){sum-=200;}
        return sum;
   }

    private static String one = "I";
    private static String nine = "IX";
    private static String ten = "X";
    private static String nightyNine = "XCIX";
    private static String hundred = "C";
    private static String threeTwoSeven = "CCCXXVII";
    private static String eightEightEight = "DCCCLXXXVIII";
    private static String k = "MXXIV";
    private static void testRomanToInt() {
        System.out.println(one + " = " + romanToInt(one));
        System.out.println(nine + " = " + romanToInt(nine));
        System.out.println(ten + " = " + romanToInt(ten));
        System.out.println(nightyNine + " = " + romanToInt(nightyNine));
        System.out.println(hundred + " = " + romanToInt(hundred));
        System.out.println(threeTwoSeven + " = " + romanToInt(threeTwoSeven));
        System.out.println(eightEightEight + " = " + romanToInt(eightEightEight));
        System.out.println(k + " = " + romanToInt(k));
    }
    public static void main(String[] args) {
        testRomanToInt();
    }
}
