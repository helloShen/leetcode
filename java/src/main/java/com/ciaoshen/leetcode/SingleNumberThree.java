/**
 * Leetcode - Algorithm - SingleNumberThree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class SingleNumberThree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private SingleNumberThree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[] singleNumber(int[] nums);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int[] singleNumber(int[] nums) {
            Set<Integer> dic = new HashSet<>();
            for (int num : nums) {
                if (!dic.add(num)) { dic.remove(num); }
            }
            int[] result = new int[2];
            if (dic.size() != 2) { return result; }
            Iterator<Integer> ite = dic.iterator();
            result[0] = ite.next();
            result[1] = ite.next();
            return result;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int[] singleNumber(int[] nums) {
            // 用XOR让成对的数字互相抵消，得到两个目标数字的XOR混合后的结果。
            int mix = 0;
            for (int num : nums) { mix ^= num; }
            // 获得两目标数XOR混合后最低的1位。
            // 假设 a = 1010 0101, b = 1001 0101, 混合后 a ^ b = 0011 0000, 这步得到的就是 0001 0000
            // 之所以能这么做，因为int是用2的补码形式编码
            mix &= -mix;
            // 把所有的数分成两组：第一组，两个目标数不同的那一位是1；第二组，两个目标数不同的那一位是0.
            // 然后把两组数分别做XOR混合，最后得到的就是两个目标数。
            // 因为两个目标数被分在了不同的组，和他们分在一组的所有其他数都是成对的，会被XOR混合抵消。
            int[] result = new int[2];
            for (int num : nums) {
                if ((num & mix) == mix) { // 两个目标数不同的那一位是1
                    result[0] ^= num;
                } else { // 两个目标数不同的那一位是0
                    result[1] ^= num;
                }
            }
            return result;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int[] singleNumber(int[] nums) {
            return null;
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
        private SingleNumberThree problem = new SingleNumberThree();
        private Solution solution = null;

        private void call(int[] nums, String answer) {
            System.out.println("In array: " + Arrays.toString(nums) + "\n");
            System.out.println(Arrays.toString(solution.singleNumber(nums)) + " are the single numbers.     [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int[] nums1 = new int[]{1, 2, 1, 3, 2, 5};
            call(nums1,"[3,5]");
            // call();
            // call();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        // test.test(3);
    }
}
