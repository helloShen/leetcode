/**
 * Leetcode - Algorithm - Min Stack
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MinStack {
    public class SolutionV1 {
        private static final int DEFAULT_SIZE = 16;
        private int[] nums;
        private int min;
        private int size;

        /** initialize your data structure here. */
        public SolutionV1() {
            nums = new int[DEFAULT_SIZE];
            min = Integer.MAX_VALUE;
            size = 0;
        }

        public void push(int x) {
            if (size == nums.length) {
                nums = Arrays.copyOf(nums,nums.length * 2); // auto double the size
            }
            nums[size] = x;
            size++;
            min = Math.min(min,x);
        }

        public void pop() {
            if (size > 0) {
                size--;
                if (nums[size] == min) {
                    min = Integer.MAX_VALUE;
                    for (int i = 0; i < size; i++) {
                        min = Math.min(min,nums[i]);
                    }
                }
            }
        }

        public int top() {
            if (size == 0) { return 0; }
            return nums[size-1];
        }

        public int getMin() {
            return min;
        }
    }

    public class SolutionV2 {
        private List<Integer> nums;
        private int min;

        public SolutionV2() {
            nums = new LinkedList<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            nums.add(x);
            min = Math.min(min,x);
        }

        public void pop() {
            if (! nums.isEmpty()) {
                if (min == nums.remove(nums.size()-1)) {
                    min = Integer.MAX_VALUE;
                    for (int num : nums) {
                        if (num < min) { min = num; }
                    }
                }
            }
        }

        public int top() {
            if (! nums.isEmpty()) {
                return nums.get(nums.size()-1);
            } else {
                return 0;
            }
        }

        public int getMin() {
            return min;
        }
    }
    public class Solution {
        private Deque<Long> nums;
        private long min;

        public Solution() {
            nums = new LinkedList<>();
            min = (long)Integer.MAX_VALUE;
        }

        public void push(int x) { // 储存的是和前任min的差值
            long gap = (long)x - min; // always store the difference to the min value
            //System.out.println("Gap of " + x + " and " + min + " = " + gap);
            nums.offerFirst(gap);
            if (gap < 0) {
                min = x;
            }
        }

        public void pop() {
            if (! nums.isEmpty()) {
                long gap = nums.pollFirst();
                if (gap < 0) { // current ele == min value
                    min = min - gap; // to find thw previous min value
                }
            }
        }

        public int top() {
            if (! nums.isEmpty()) {
                long gap = nums.peekFirst();
                if (gap < 0) { // 当前堆顶就是最小值
                    return (int)min;
                } else { // 当前堆顶不是当前最小值，需要计算
                    return (int)(min + gap);
                }
            } else {
                return 0;
            }
        }

        public int getMin() {
            return (int)min;
        }
    }
    private static MinStack test = new MinStack();
    private static Solution solution = test.new Solution();
    public static void main(String[] args) {
        solution.pop();
        System.out.println("Current top = " + solution.top()); // should be 0
        solution.push(0);
        solution.push(-2);
        solution.push(-3);
        System.out.println(solution.top()); // should be -3
        solution.pop(); // throw -3
        System.out.println(solution.getMin()); // should be -2
    }
}
