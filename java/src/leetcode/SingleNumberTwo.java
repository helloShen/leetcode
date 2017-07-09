/**
 * Leetcode - Algorithm - Single Number Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SingleNumberTwo {
    public class SolutionV1 {
        public int singleNumber(int[] nums) {
            Map<Integer,Integer> memo = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                Integer remain = memo.get(nums[i]);
                if (remain == null) {
                    memo.put(nums[i],2);
                } else if (remain > 1) {
                    memo.replace(nums[i],remain-1);
                } else if (remain == 1) {
                    memo.remove(nums[i]);
                }
            }
            int res = 0;
            for (Map.Entry<Integer,Integer> entry : memo.entrySet()) {
                res = entry.getKey();
            }
            return res;
        }
    }
    public class SolutionV2 {
        public int singleNumber(int[] nums) {
            Map<Integer,Integer> memo = new HashMap<>();
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                res ^= num;
                if (memo.get(num) == null) {
                    memo.put(num,0);
                } else {
                    memo.remove(num);
                    res ^= num;
                }
            }
            return res;
        }
    }
    public class SolutionV3 {
        public int singleNumber(int[] nums) {
            Set<Integer> members = new HashSet<>();
            Set<Integer> duplicates = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                Integer num = nums[i];
                if (!duplicates.contains(num)) {
                    if (!members.add(num)) {
                        duplicates.add(num);
                    }
                }
            }
            System.out.println("Members: " + members);
            System.out.println("Duplicates: " + duplicates);
            members.removeAll(duplicates);
            return (int)members.toArray()[0];
        }
    }
    public class SolutionV4 {
        public int singleNumber(int[] nums) {
            Map<Integer,Integer> members = new HashMap<>();
            Map<Integer,Integer> duplicates = new HashMap<>();
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                res ^= nums[i];
                int num = nums[i];
                if (!duplicates.containsKey(num)) {
                    if (members.put(num,0) != null) {
                        duplicates.put(num,0);
                        res ^= nums[i];
                    }
                }
            }
            return res;
        }
    }
    /**
     * 排名最高的解法。有点耍聪明。
     */
    public class SolutionV5 {
        public int singleNumber(int[] A) {
            int ones = 0, twos = 0;
            for(int i = 0; i < A.length; i++){
                ones = (ones ^ A[i]) & ~twos;
                System.out.println("Ones=" + lastEight(ones));
                twos = (twos ^ A[i]) & ~ones;
                System.out.println("Twos=" + lastEight(twos));
            }
            return ones;
        }
    }
    /**
     * 位操作的一种解法。
     * 统计每一位上1出现的次数。只要不是3的倍数的位，表明落单数在这些位上是1.
     */
    public class SolutionV6 {
        public int singleNumber(int[] nums) {
            int[] bitCount = new int[32];
            for (int num : nums) {
                for (int i = 0, mask = 1; i < 32; i++, num = num >> 1) {
                    int eachBit = num & mask;
                    if (eachBit == 1) {
                        bitCount[i] = bitCount[i] + 1;
                    }
                }
            }
            //System.out.println(Arrays.toString(bitCount));
            int res = 0;
            for (int i = 0, mask = 1; i < 32; i++, mask = mask << 1) {
                if (bitCount[i] % 3 != 0) {
                    res = res | mask;
                }
            }
            return res;
        }
    }
    /**
     * 更好的一种位操作解法。
     * 通过bit table，设计出一种位操作流程。
     *  1. 当一个数字出现第一次，会在a中体现它的信息。
     *  2. 当它出现第二次，会在b中体现它的信息。
     *  3. 当它出现第三次，它的信息将彻底消失。
     */
    public class Solution {
        public int singleNumber(int[] nums) {
            //  1. 当一个数字出现第一次，会在a中体现它的信息。
            //  2. 当它出现第二次，会在b中体现它的信息。
            //  3. 当它出现第三次，它的信息将彻底消失。
            //we need to implement a tree-time counter(base 3) that if a bit appears three time ,it will be zero.
            //#curent  income  ouput
            //# ab      c/c       ab/ab
            //# 00      1/0       01/00
            //# 01      1/0       10/01
            //# 10      1/0       00/10
            // a=~abc+a~b~c;
            // b=~a~bc+~ab~c;
            int a=0;
            int b=0;
            for(int c:nums){
                int ta=(~a&b&c)|(a&~b&~c);
                b=(~a&~b&c)|(~a&b&~c);
                a=ta;
            }
            //we need find the number that is 01,10 => 1, 00 => 0.
            return b;

        }
    }
    /**
     * 打印二进制的最后8位
     */
    private static String lastEight(int n) {
        StringBuilder sb = new StringBuilder();
        int mask = 1;
        for (int i = 0; i < 8; i++, n = n >> 1) {
            int eachBit = n & mask;
            sb = sb.insert(0,eachBit);
        }
        return sb.toString();
    }
    private static SingleNumberTwo test = new SingleNumberTwo();
    private static void testSingleNumber() {
        Solution solution = test.new Solution();
        int[] nums = new int[]{5,3,4,2,4,3,2,3,2,1,5,4,5};
        System.out.println(Arrays.toString(nums) + " >>> Single Number = " + solution.singleNumber(nums));
        int[] nums2 = new int[]{-2,-2,1,1,-3,1,-3,-3,-4,-2};
        System.out.println(Arrays.toString(nums) + " >>> Single Number = " + solution.singleNumber(nums2));
    }
    public static void main(String[] args) {
        testSingleNumber();
    }
}
