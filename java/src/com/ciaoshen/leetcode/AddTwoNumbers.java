/**
 * leetcode - add two numbers
 */
package com.ciaoshen.leetcode;
import java.util.*;
import java.lang.Math.*;
import java.math.BigDecimal;

class AddTwoNumbers {
    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) { val = x; }
        public String toString() {
            ListNode index = this;
            StringBuilder sb = new StringBuilder();
            while (true) {
                if (index == null) { break; }
                sb.append(String.valueOf(index.val));
                index = index.next;
            }
            return sb.toString();
        }
    }
    public static class Solution {
        public static ListNode addTwoNumbersNaive(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(-1); // HEAD is not included in the result
            ListNode index = result;
            int carry = 0;
            int num1 = 0;
            int num2 = 0;
            while (true) {
                if (l1 == null && l2 == null) {
                    if (carry == 1) {
                        ListNode next = new ListNode(carry);
                        index.next = next;
                    }
                    return result.next;
                }
                if (l1 != null) {
                    num1 = l1.val;
                    l1 = l1.next;
                } else {
                    num1 = 0;
                }
                if (l2 != null) {
                    num2 = l2.val;
                    l2 = l2.next;
                } else {
                    num2 = 0;
                }
                int sum = num1 + num2 + carry;
                index.next = new ListNode(sum % 10);
                index = index.next;
                carry = sum / 10;
            }
        }
        public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1.val == 0 && l1.next == null) { // base case l1 = 0
                return l2;
            }
            if (l2.val == 0 && l2.next == null) { // base case l2 = 0
                return l1;
            }
            ListNode result = new ListNode(-1); // HEAD is not included in the result
            ListNode index = result;
            int carry = 0;
            int num1 = 0;
            int num2 = 0;
            if (l2.val == 1 && l2.next == null) { // base case l2 = 1
                while (l1 != null && l1.val == 9) {
                    index.next = new ListNode(0);
                    l1 = l1.next;
                    index = index.next;
                    carry = 1;
                }
                if (l1 == null) {
                    index.next = new ListNode(1);
                } else {
                    ListNode newNode = new ListNode(l1.val + 1);
                    newNode.next = l1.next;
                    index.next = newNode;
                }
                return result.next;
            }
            while (true) {
                if (l1 == null && l2 == null) {
                    if (carry == 1) {
                        ListNode next = new ListNode(carry);
                        index.next = next;
                    }
                    return result.next;
                }
                if (l1 == null) { // l2更长，递归
                    index.next = addTwoNumbers(l2,new ListNode(carry));
                    return result.next;
                }
                if (l2 == null) { // l1更长，递归
                    index.next = addTwoNumbers(l1,new ListNode(carry));
                    return result.next;
                }
                // add two numbers on this bit
                int sum = l1.val + l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
                index.next = new ListNode(sum % 10);
                index = index.next;
                carry = sum / 10;
            }
        }
        /**
         * [Parse the ListNode to int]
         */
        public static ListNode addTwoNumbersMath(ListNode l1, ListNode l2) {
            BigDecimal sum = listnodeToBigDicimal(l1).add(listnodeToBigDicimal(l2));
            return bigDecimalToListnode(sum);
        }
        private static BigDecimal listnodeToBigDicimal(ListNode l1) {
            BigDecimal result = new BigDecimal(0);
            for (int index = 0; l1 != null; l1 = l1.next, index++) {
                result = result.add(new BigDecimal(l1.val * Math.pow(10,index)));
            }
            return result;
        }
        private static ListNode bigDecimalToListnode(BigDecimal num) {
            char[] digitsSet = num.toString().toCharArray();
            ListNode result = new ListNode(-1);
            ListNode cursor = result;
            for (int i = digitsSet.length-1; i >= 0; i--) {
                cursor.next = new ListNode(Character.getNumericValue(digitsSet[i]));
                cursor = cursor.next;
            }
            return result.next;
        }
    }
    private static class UnitTest {
        private List<ListNode> list = new ArrayList<>();
        private int times;
        Random r = new Random();
        public UnitTest(int times) {
            this.times = times;
            // 872
            ListNode eightSevenTwo = new ListNode(2);
            eightSevenTwo.next = new ListNode(7);
            eightSevenTwo.next.next = new ListNode(8);
            list.add(eightSevenTwo);
            // 23
            ListNode twoThree = new ListNode(3);
            twoThree.next = new ListNode(2);
            list.add(twoThree);
            // 48902
            ListNode fourEightNightZeroTwo = new ListNode(2);
            fourEightNightZeroTwo.next = new ListNode(0);
            fourEightNightZeroTwo.next.next = new ListNode(9);
            fourEightNightZeroTwo.next.next.next = new ListNode(8);
            fourEightNightZeroTwo.next.next.next.next = new ListNode(4);
            list.add(fourEightNightZeroTwo);
            // 78
            ListNode sevenEight = new ListNode(8);
            sevenEight.next = new ListNode(7);
            list.add(sevenEight);
            //101
            ListNode oneZeroOne = new ListNode(1);
            oneZeroOne.next = new ListNode(0);
            oneZeroOne.next.next = new ListNode(1);
            list.add(oneZeroOne);
            // 0
            ListNode zero = new ListNode(0);
            list.add(zero);
            // 31999
            ListNode threeOneNineNineNine= new ListNode(3);
            threeOneNineNineNine.next = new ListNode(1);
            threeOneNineNineNine.next.next = new ListNode(9);
            threeOneNineNineNine.next.next.next = new ListNode(9);
            threeOneNineNineNine.next.next.next.next = new ListNode(9);
            list.add(threeOneNineNineNine);
        }
        private void testAddTwoNumbersNaive(List<ListNode> list) {
            ListNode num1 = list.get(r.nextInt(list.size()));
            ListNode num2 = list.get(r.nextInt(list.size()));
            System.out.println(num1  + " + " + num2 + " = " + Solution.addTwoNumbersNaive(num1,num2));
        }
        private void testAddTwoNumbers(List<ListNode> list) {
            ListNode num1 = list.get(r.nextInt(list.size()));
            ListNode num2 = list.get(r.nextInt(list.size()));
            System.out.println(num1  + " + " + num2 + " = " + Solution.addTwoNumbers(num1,num2));
        }
        private void testAddTwoNumbersMath(List<ListNode> list) {
            ListNode num1 = list.get(r.nextInt(list.size()));
            ListNode num2 = list.get(r.nextInt(list.size()));
            System.out.println(num1  + " + " + num2 + " = " + Solution.addTwoNumbersMath(num1,num2));
        }
        private void runNaive() {
            for (int i = 0; i < times; i++) {
                System.out.println(list);
                testAddTwoNumbersNaive(list);
            }
        }
        private void run() {
            for (int i = 0; i < times; i++) {
                System.out.println(list);
                testAddTwoNumbers(list);
            }
        }
        private void runMath() {
            for (int i = 0; i < times; i++) {
                System.out.println(list);
                testAddTwoNumbersMath(list);
            }
        }
    }

    public static void main(String[] args) {
        UnitTest test = new UnitTest(10);
        //test.runNaive();
        //test.run();
        test.runMath();
    }
}
