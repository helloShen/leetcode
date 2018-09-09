/**
 * Leetcode - Letter combination of a phone number
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class PhoneNumber {
    private Map<Character,String> keypad = new HashMap<>();
    {
        keypad.put('1',"");
        keypad.put('2',"abc");
        keypad.put('3',"def");
        keypad.put('4',"ghi");
        keypad.put('5',"jkl");
        keypad.put('6',"mno");
        keypad.put('7',"pqrs");
        keypad.put('8',"tuv");
        keypad.put('9',"wxyz");
        keypad.put('0',"");
    }
    public List<String> letterCombinationsV1(String digits) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            String letters = keypad.get(digits.charAt(i));
            System.out.println("Digit = " + digits.charAt(i) + ", Letters = " + letters);
            if (letters != null && letters.length() > 0) {
                ListIterator<String> ite = result.listIterator();
                while (ite.hasNext()) {
                    String old = ite.next();
                    ite.remove();
                    System.out.println(old + " removed!");
                    for (int j = 0; j < letters.length(); j++) {
                        ite.add(old + letters.substring(j,j+1));
                        System.out.println(old + letters.substring(j,j+1) + " added!");
                    }
                }
                if (result.size() == 0) {
                    for (int j = 0; j < letters.length(); j++) {
                        result.add(letters.substring(j,j+1));
                    }
                }
            }
        }
        return result;
    }

    private String[][] letterArray = new String[][]{
        {"","","",""},
        {"","","",""},
        {"a","b","c",""},
        {"d","e","f",""},
        {"g","h","i",""},
        {"j","k","l",""},
        {"m","n","o",""},
        {"p","q","r","s"},
        {"t","u","v",""},
        {"w","x","y","z"}
    };
    public List<String> letterCombinationsV2(String digits) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i)-'0';
            System.out.println("Digit = " + digit);
            if (result.size() == 0) {
                for (int j = 0; j < 4; j++) {
                    if (letterArray[digit][j] != "") {
                        result.add(letterArray[digit][j]);
                    }
                }
            } else {
                ListIterator<String> ite = result.listIterator();
                while (ite.hasNext()) {
                    String old = ite.next();
                    ite.remove();
                    System.out.println(old + " removed!");
                    for (int j = 0; j < 4; j++) {
                        if (letterArray[digit][j] != "") {
                            ite.add(old + letterArray[digit][j]);
                            System.out.println(old + letterArray[digit][j] + " added!");
                        }
                    }
                }
            }
        }
        return result;
    }

    public List<String> letterCombinationsV3(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) { return result; }
        String[] letterPad = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i)-'0';
            String letters = letterPad[digit];
            ListIterator<String> ite = result.listIterator();
            while (ite.hasNext()) {
                String old = ite.next();
                ite.remove();
                System.out.println(old + " removed!");
                for (int j = 0; j < letters.length(); j++) {
                    ite.add(old + letters.charAt(j));
                    System.out.println(old + letters.charAt(j) + " added!");
                }
            }
        }
        return result;
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) { return result; }
        String[] letterPad = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        letterCombinationsRecursive(result,"",letterPad,0,digits);
        return result;
    }
    public void letterCombinationsRecursive(List<String> list, String str, String[] letterPad, int index, String digits) {
        if (index == digits.length()) { list.add(str); return; }
        for (char c : letterPad[digits.charAt(index)-'0'].toCharArray()) {
            letterCombinationsRecursive(list,str+c,letterPad,index+1,digits);
        }
    }

    private static void testLetterCombinations() {
        PhoneNumber pn = new PhoneNumber();
        String digit1 = "23";
        List<String> answer1 = new ArrayList<>(Arrays.asList(new String[]{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"}));
        System.out.println("Result of " + digit1 + " is: " + pn.letterCombinations(digit1));
        System.out.println("Answer is: " + answer1);
    }
    public static void main(String[] args) {
        testLetterCombinations();
    }
}
