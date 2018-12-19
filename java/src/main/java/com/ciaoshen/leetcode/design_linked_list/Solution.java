/**
 * Leetcode - design_linked_list
 */
package com.ciaoshen.leetcode.design_linked_list;
import java.util.*;
// import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public void init();
    public int get(int index);
    public void addAtHead(int val);
    public void addAtTail(int val);
    public void addAtIndex(int index, int val);
    public void deleteAtIndex(int index);

    static class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
        public String toString() {
            String num = String.valueOf(val);
            return (next == null)? num : num + "->" + next.toString();
        }
    }

}
