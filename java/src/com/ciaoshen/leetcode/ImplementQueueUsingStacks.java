/**
 * Leetcode - Algorithm - Implement Queue Using Stacks
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ImplementQueueUsingStacks {
    public class MyQueueV1 {

        /** main container */
        private Deque<Integer> stack = new LinkedList<>();
        private Deque<Integer> buffer = new LinkedList<>();

        /** Push element x to the back of queue. */
        public void push(int x) {
            int size = stack.size();
            for (int i = 0; i < size; i++) { buffer.offerFirst(stack.pollFirst()); } // out all
            stack.offerFirst(x);
            for (int i = 0; i < size; i++) { stack.offerFirst(buffer.pollFirst()); } // back all
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return stack.pollFirst();
        }

        /** Get the front element. */
        public int peek() {
            return stack.peekFirst();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }
    public class MyQueue {

        /** main container */
        private Deque<Integer> stack = new LinkedList<>();

        /** Push element x to the back of queue. */
        public void push(int x) {
            int size = stack.size();
            int[] buffer = new int[size];
            for (int i = 0; i < size; i++) { buffer[i] = stack.pollFirst(); } // out all
            stack.offerFirst(x);
            for (int i = size-1; i >= 0; i--) { stack.offerFirst(buffer[i]); } // back all
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return stack.pollFirst();
        }

        /** Get the front element. */
        public int peek() {
            return stack.peekFirst();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }
    public class MyQueueV3 {

        /** main container */
        Deque<Integer> input = new LinkedList<>();
        Deque<Integer> output = new LinkedList<>();

        /** Push element x to the back of queue. */
        public void push(int x) {
            input.offerFirst(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            check();
            return output.pollFirst();
        }

        /** Get the front element. */
        public int peek() {
            check();
            return output.peekFirst();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return input.isEmpty() && output.isEmpty();
        }

        /** Move elements from input to output if output is empty. */
        private void check() {
            if (output.isEmpty()) {
                while (!input.isEmpty()) { output.offerFirst(input.pollFirst()); }
            }
        }
    }
    private class Test {
        private void callMyQueue(MyQueue queue) {
            System.out.println("Original Queue: " + queue.stack);
            queue.push(100);
            System.out.println("After push 100: " + queue.stack);
            int half = queue.stack.size() / 2;
            System.out.println("Following are the last " + half + " element in the queue: ");
            for (int i = 0; i < half; i++) { System.out.println(queue.pop()); }
            System.out.println("My Queue is empty now? " + queue.empty());
            System.out.println("Now the last element in the queue is: " + queue.peek());
        }
        private void callMyQueueV3(MyQueueV3 queue) {
            queue.check();
            System.out.println("Original Queue: " + queue.output);
            queue.push(100);
            while (!queue.output.isEmpty()) {
                System.out.println(queue.pop());
            }
            System.out.println("My Queue is empty now? " + queue.empty());
            System.out.println("Now the last element in the queue is: " + queue.peek());
        }
        private void test() {
            MyQueue queue = problem.new MyQueue();
            for (int i = 0; i < 10; i++) { queue.push(i); }
            callMyQueue(queue);
        }
        private void test2() {
            MyQueue queue = problem.new MyQueue();
            queue.push(1);
            queue.push(2);
            System.out.println(queue.peek());
        }
        private void testV3() {
            MyQueueV3 queue = problem.new MyQueueV3();
            for (int i = 0; i < 10; i++) { queue.push(i); }
            callMyQueueV3(queue);
        }
    }
    private static ImplementQueueUsingStacks problem = new ImplementQueueUsingStacks();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        // test.test();
        // test.test2();
        test.testV3();
    }
}
