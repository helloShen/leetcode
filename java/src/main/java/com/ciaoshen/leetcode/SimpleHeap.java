/**
 * 最简单的Heap
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SimpleHeap {
    private static final int INIT_SIZE = 16;
    private int[] heap = new int[INIT_SIZE+1];
    private int size = 1;

    public SimpleHeap() {}
    public SimpleHeap(int num) { heap = new int[num+1]; }

    public void offer(int n) {
        if (size == heap.length) { expand(); }
        heap[size++] = n;
        up2(size-1);
    }
    public int pop() {
        if (size < 2) { return 0; }
        if (size == 2) { return heap[--size]; }
        int last = heap[--size];
        int top = heap[1];
        heap[1] = last;
        down2(1);
        return top;
    }
    private void expand() {
        heap = Arrays.copyOf(heap,(heap.length-1)*2+1);
    }
    private void up(int pos) {
        int parent = pos / 2;
        int val = heap[pos];
        while (parent > 0 && heap[parent] < val) {
            heap[pos] = heap[parent];
            pos = parent;
            parent = pos / 2;
        }
        heap[pos] = val;
    }

    private void down(int pos) {
        int val = heap[pos];
        while (pos < size) {
            int left = pos * 2, right = left + 1;
            int larger = 0, largerPos = 0;
            if (right < size) {
                if (heap[left] >= heap[right]) {
                    larger = heap[left]; largerPos = left;
                } else {
                    larger = heap[right]; largerPos = right;
                }
            } else if (left < size) {
                larger = heap[left];
                largerPos = left;
            } else {
                break;
            }
            if (larger > val) {
                heap[pos] = larger;
                pos = largerPos;
            } else {
                break;
            }
        }
        heap[pos] = val;
    }
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(heap,1,size));
    }


    /***************************************************
     * 练习写Heap。 第二遍
     * 简化up()和down()函数
     * offer()和pop()沿用第一版
     **************************************************/
    private void up2(int pos) {
        int val = heap[pos];
        while (pos > 1) {
            int parent = pos/2;
            if (heap[parent] >= val) { break; }
            heap[pos] = heap[parent];
            pos = parent;
        }
        heap[pos] = val;
    }
    private void down2(int pos) {
        int val = heap[pos];
        while (pos * 2 < size) {
            int left = pos * 2, right = left + 1, next = left;
            if (right < size && heap[left] < heap[right]) { next = right; }
            if (heap[next] <= val) { break; }
            heap[pos] = heap[next];
            pos = next;
        }
        heap[pos] = val;
    }

    /***************************************************
     * 练习写Heap。 第三遍
     * offer(), pop(), up(), down() 都写一遍
     **************************************************/
    /* 插入新元素 */
    public void offer3(int n) {
        if (size == heap.length) { expand3(); }
        heap[size++] = n;  // add to the end
        up3();              // matain the order
    }
    private void expand3() {
        heap = Arrays.copyOf(heap,heap.length*2-1);
    }
    /* 返回堆中最大元素 */
    public int pop3() {
        if (size < 2) { return 0; }
        if (size == 2) { return heap[--size]; }
        int top = heap[1];
        heap[1] = heap[--size];    // delete root, move last element to the root bucket
        down3();                    // maintain the order
        return top;
    }
    /* 尾元素上游 */
    private void up3() {
        int pos = size-1;
        int val = heap[pos];
        while (pos > 1) { // root = 1
            int parent = pos / 2;
            int pv = heap[parent];
            if (pv > val) { break; }
            heap[pos] = pv;
            pos = parent;
        }
        heap[pos] = val;
    }
    /* 根元素下潜 */
    private void down3() {
        int pos = 1;
        int val = heap[pos];
        while (pos * 2 < heap.length) {
            int next = pos * 2;
            int nv = heap[next];
            if (next + 1 < heap.length && heap[next+1] > heap[next]) { nv = heap[++next]; }
            if (nv <= val) { break; }
            heap[pos] = nv;
            pos = next;
        }
        heap[pos] = val;
    }

    public static void main(String[] args) {
        SimpleHeap heap = new SimpleHeap();
        List<Integer> list = new ArrayList<>();
        Random r = new Random();
        int max = 100;
        int size = 20;
        for (int i = 0; i < size; i++) {
            int n = r.nextInt(max) + 1;
            // heap.offer(n);
            heap.offer3(n);
            list.add(n);
        }
        System.out.println("After insert " + size + " elements: ");
        System.out.println(heap);
        for (int i = 0; i < size/2; i++) {
            // heap.pop();
            heap.pop3();
        }
        Collections.sort(list);
        System.out.println(list);
        int target = list.get((size-size/2)-1);
        System.out.println("After remove " + size/2 + " elements: ");
        System.out.println("[" + target + "]" + heap);
    }
}
