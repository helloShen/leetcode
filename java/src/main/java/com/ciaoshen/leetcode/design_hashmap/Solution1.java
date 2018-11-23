/**
 * Leetcode - design_hashmap
 */
package com.ciaoshen.leetcode.design_hashmap;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution1 implements Solution {

    private static final int SIZE = 10000;
    private List[] table;

    public void init() {
         table = (LinkedList<int[]>[]) new LinkedList[SIZE];
    }

    public void put(int key, int value) {
        int hash = hash(key);
        if (table[hash] == null) table[hash] = new LinkedList<int[]>();
        List list = table[hash];
        for (Object o : list) {
            int[] entry = (int[]) o;
            if (entry[0] == key) {
                entry[1] = value;
                return;
            }
        }
        list.add(new int[]{key, value});
    }

    public int get(int key) {
        int hash = hash(key);
        List list = table[hash];
        if (list == null) return -1;
        for (Object o : list) {
            int[] entry = (int[]) o;
            if (entry[0] == key) return entry[1];
        }
        return -1;
    }

    public void remove(int key) {
        int hash = hash(key);
        List list = table[hash];
        if (list == null) return;
        Iterator<Object> ite = list.iterator();
        while (ite.hasNext()) {
            int[] entry = (int[]) ite.next();
            if (entry[0] == key) {
                ite.remove();
                return;
            }
        }
        if (table[hash].isEmpty()) table[hash] = null;
    }

    private int hash(int n) {
        return (51 + 31 * n) % SIZE;
    }

}
