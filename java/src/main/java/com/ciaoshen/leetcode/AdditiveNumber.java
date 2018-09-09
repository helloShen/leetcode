/**
 * Leetcode - Algorithm - AdditiveNumber
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class AdditiveNumber implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private AdditiveNumber() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean isAdditiveNumber(String num); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public boolean isAdditiveNumber(String num) {
            int len = num.length();
            if (len < 3) { return false; }
            for (int i = 0; i <= len / 2 - 1; i++) {
                for (int j = i + 1; (len - j) >= Math.max(i,j-i); j++) {
                    if (isAdditive(num,i,j)) { return true; }
                }
            }
            return false;
        }
        /* slow points to the end of first number, fast points to the end of second number */
        private boolean isAdditive(String num, int slow, int fast) {
            int pre = 0; // first character of first number
            while (fast < num.length()-1) {
                if ((num.charAt(pre) == '0' && slow - pre > 0) || (num.charAt(slow+1) == '0' && fast - slow - 1 > 0)) { return false; }
                String x = num.substring(pre,slow+1);
                String y = num.substring(slow+1,fast+1);
                System.out.println("x=" + x + ", y=" + y);
                String sum = sum(x,y);
                int nextEnd = indexOf(num,fast+1,sum);
                if (nextEnd == -1) { System.out.println("not additive"); return false; }
                pre = slow+1;
                slow = fast;
                fast = nextEnd;
            }
            System.out.println("is additive");
            return true;
        }
        /* return the end index of target number. return -1 if not exist */
        private int indexOf(String num, int start, String sum) {
            int curNum = start;
            int curSum = 0;
            while (curNum < num.length() && curSum < sum.length()) {
                if (sum.charAt(curSum++) != num.charAt(curNum++)) { return -1; }
            }
            return (curSum == sum.length())? curNum-1 : -1;
        }
        /* return the sum of a and b */
        private String sum(String x, String y) {
            StringBuilder sb = new StringBuilder();
            int cx = x.length()-1, cy = y.length()-1;
            int carry = 0;
            while (cx >= 0 && cy >= 0) {
                int sum = (x.charAt(cx--) - '0') + (y.charAt(cy--) - '0') + carry;
                carry = sum / 10;
                sb.insert(0,sum % 10);
            }
            while (cx >= 0) {
                int sum = (x.charAt(cx--) - '0') + carry;
                carry = sum / 10;
                sb.insert(0,sum % 10);
            }
            while (cy >= 0) {
                int sum = (y.charAt(cy--) - '0') + carry;
                carry = sum / 10;
                sb.insert(0,sum % 10);
            }
            if (carry == 1) { sb.insert(0,'1'); }
            return sb.toString();
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public boolean isAdditiveNumber(String num) {
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public boolean isAdditiveNumber(String num) {
            return false;
        }
    }
    // you can expand more solutions HERE if you want...


    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private AdditiveNumber problem = new AdditiveNumber();
        private Solution solution = null;

        // call method in solution
        private void call(String nums, String answer) {
            System.out.println(nums + " -> " + solution.isAdditiveNumber(nums) + "  [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String nums0 = "0";
            String answer0 = "false";

            String nums1 = "112358";
            String answer1 = "true";

            String nums2 = "199100199";
            String answer2 = "true";

            String nums3 = "123244771118";
            String answer3 = "true";

            String nums4 = "134792734698";
            String answer4 = "false";

            String nums5 = "123456789101098765432123333333231";
            String answer5 = "true";

            String nums6 = "199001200";
            String answer6 = "false";

            String nums7 = "101";
            String answer7 = "true";

            /** involk call() method HERE */
            call(nums0,answer0);
            call(nums1,answer1);
            call(nums2,answer2);
            call(nums3,answer3);
            call(nums4,answer4);
            call(nums5,answer5);
            call(nums6,answer6);
            call(nums7,answer7);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
