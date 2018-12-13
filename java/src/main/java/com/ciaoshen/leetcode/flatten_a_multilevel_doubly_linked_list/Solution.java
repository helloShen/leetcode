/**
 * Leetcode - flatten_a_multilevel_doubly_linked_list
 */
package com.ciaoshen.leetcode.flatten_a_multilevel_doubly_linked_list;
import java.util.*;
// import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public Node flatten(Node head);

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node prev, Node next, Node child) {
            this.val = val;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }

        @Override
        public String toString() {
            return toStringHelper(this).toString();
        }

        private final String SPLITER = "-";
        private StringBuilder toStringHelper(Node node) {
            if (node == null) return new StringBuilder("NULL");
            StringBuilder sb = new StringBuilder();
            sb.append(node.val);
            // if (log.isDebugEnabled()) {
            //     log.debug("sb should be {}, actually sb = {}", node.val, sb);
            // }
            sb.append(SPLITER + toStringHelper(node.next));
            StringBuilder child = toStringHelper(node.child);
            if (!child.toString().equals("NULL")) sb.append("\n" + toStringHelper(node.child));
            return sb;
        }

    }


}
