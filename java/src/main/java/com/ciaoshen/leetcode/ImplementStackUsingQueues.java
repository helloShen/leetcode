/**
 * Leetcode - Algorithm - Implement Stack using Queues
 *
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ImplementStackUsingQueues {

    public class MyStackV1 {
        Queue<Integer> q = new LinkedList<>();

        /** * Push element x onto stack.  O(n) */
        public void push(int x) {
            Queue<Integer> buffer = new LinkedList<>();
            while (!q.isEmpty()) { buffer.offer(q.poll()); }
            q.offer(x);
            while (!buffer.isEmpty()) { q.offer(buffer.poll()); }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return q.poll();
        }

        /** Get the top element. */
        public int top() {
            return q.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q.isEmpty();
        }

        private int size() {
            return q.size();
        }
    }
    public class MyStack {
        Queue<Integer> q = new LinkedList<>();

        /** * Push element x onto stack.  O(n) */
        public void push(int x) {
            int size = q.size();
            q.offer(x);
            for (int i = 0; i < size; i++) {
                q.offer(q.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return q.poll();
        }

        /** Get the top element. */
        public int top() {
            return q.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q.isEmpty();
        }

        private int size() {
            return q.size();
        }
    }
    private class Test {
        private void callMyStack() {
            int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
            for (int n : nums) { stack.push(n); }
            int half = nums.length / 2;
            for (int i = 0; i < half; i++) {
                System.out.println(stack.pop());
            }
            System.out.println("After pop " + half + " numbers, the first number on the top is " + stack.top());
            System.out.println("It remains " + stack.size() + " numbers in the stack.");
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                System.out.println(stack.pop());
            }
            System.out.println("Is the stack empty now? " + stack.empty());
        }
        private void test() {
            callMyStack();
        }
    }
    private static ImplementStackUsingQueues problem = new ImplementStackUsingQueues();
    private static MyStack stack = problem.new MyStack();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
