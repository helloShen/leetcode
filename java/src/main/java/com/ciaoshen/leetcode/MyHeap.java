/**
 * My implements of Heap
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MyHeap {
    private List<Integer> list = new LinkedList<>();
    public void offer(int n) {
        add(n);
    }
    public Integer pop() {
        Integer res = list.get(0);
        remove(0);
        return res;
    }
    public Integer peek() {
        return list.get(0);
    }
    /* 添加一个新元素n */
    private void add(int n) {
        list.add(n);
        siftUp(list.size()-1);
    }
    /* 删掉特定位置上的元素 */
    private void remove(int index) {
        if (index < 0) { return; }
        if (index == list.size() - 1) { list.remove(list.size()-1); return; }
        list.set(index,list.get(list.size()-1));
        list.remove(list.size()-1);
        // System.out.println("After copy last element: " + list);
        int newIndex = siftDown(index);
        if (newIndex == index) {
            newIndex = siftUp(index);
        }
    }
    /* 向下冒泡 */
    private int siftDown(int index) {
        int val = list.get(index);
        while (true) {
            int left = index * 2 + 1;
            int right = left + 1;
            Integer leftVal = null;
            Integer rightVal = null;
            if (left < list.size()) { leftVal = list.get(left); }
            if (right < list.size()) { rightVal = list.get(right); }
            // 左右两子元素都不行，结束迭代
            if ((leftVal == null || leftVal <= val) && (rightVal == null || rightVal <= val)) { break; }
            // 右边不行，左边行
            if (rightVal == null || rightVal <= val) {
                list.set(index,leftVal);
                index = left;
                continue;
            }
            // 左边不行，右边行
            if (leftVal == null || leftVal <= val) {
                list.set(index,rightVal);
                index = right;
                continue;
            }
            // 左右两边都行
            if (leftVal >= rightVal) {
                list.set(index,leftVal);
                index = left;
            } else {
                list.set(index,rightVal);
                index = right;
            }
        }
        list.set(index,val);
        return index;
    }
    /* 向上冒泡 */
    private int siftUp(int index) {
        int val = list.get(index);
        while (index > 0) {
            int parent = (index - 1) / 2;
            int p = list.get(parent);
            if (p < val) {
                list.set(index,p);
                index = parent;
            } else {
                list.set(index,val);
                return index;
            }
        }
        list.set(index,val);
        return index;
    }
    public static void main(String[] args) {
        MyHeap heap = new MyHeap();
        List<Integer> list = new ArrayList<>();
        Random r = new Random();
        int max = 100;
        int size = 20;
        for (int i = 0; i < size; i++) {
            int n = r.nextInt(max) + 1;
            // System.out.println("Add " + n);
            heap.offer(n);
            list.add(n);
            // System.out.println(heap.list);
        }
        System.out.println("After insert " + size + " elements: ");
        System.out.println(heap.list);
        for (int i = 0; i < size/2; i++) {
            heap.pop();
            // System.out.println(heap.list);
        }
        Collections.sort(list);
        System.out.println(list);
        int target = list.get((size-size/2)-1);
        System.out.println("After remove " + size/2 + " elements: ");
        System.out.println("[" + target + "]" + heap.list);
    }
}
