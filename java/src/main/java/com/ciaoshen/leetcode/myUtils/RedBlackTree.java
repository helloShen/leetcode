/**
 * Simple Red Black Tree
 */
package com.ciaoshen.leetcode.myUtils;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedBlackTree<T extends Comparable> {

    // call log4j from slf4j facade
    Logger log = LoggerFactory.getLogger(RedBlackTree.class);

    Node<T> root;

    public RedBlackTree() {
        root = null;
    }
    public RedBlackTree(Node<T> root) {
        this.root = root;
    }

    static final boolean RED = false;
    static final boolean BLACK = true;

    static class Node<T> {

        T val;
        Node<T> parent;
        Node<T> left, right;
        boolean color; // true = black, false = red

        Node(T t) {
            val = t;
        }

        /** serialize a single node */
        final String BLACK_LEFT = "[";
        final String BLACK_RIGHT = "]";
        final String RED_LEFT = "(";
        final String RED_RIGHT = ")";
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append((color == BLACK)? BLACK_LEFT : RED_LEFT);
            sb.append(val.toString());
            sb.append((color == BLACK)? BLACK_RIGHT : RED_RIGHT);
            return sb.toString();
        }

    }

    public boolean search(T t) {
        return searchHelper(root, t);
    }

    private boolean searchHelper(Node<T> node, T t) {
        if (node == null) return false;
        if (node.val == t) return true;
        return searchHelper(node.left, t) || searchHelper(node.right, t);
    }

    public void insert(T t) {
        Node<T> newNode = new Node<>(t);
        if (root == null) {
            root = newNode;
            root.color = BLACK;
        } else {
            insertHelper(newNode, root);
        }
    }

    /** assertion: node != null */
    void insertHelper(Node<T> newNode, Node<T> node) {
        checkFourNode(node);
        checkRotation(node);
        if (newNode.val.compareTo(node.val) < 0) {
            if (node.left == null) {
                node.left = newNode;
                newNode.parent = node;
                checkRotation(newNode);
                if (log.isDebugEnabled()) {
                    log.debug("{} insert to left of {}, tree becomes {}", newNode.toString(), node.toString(), toString());
                }
            } else {
                insertHelper(newNode, node.left);
            }
        } else if (newNode.val.compareTo(node.val) > 0) {
            if (node.right == null) {
                node.right = newNode;
                newNode.parent = node;
                checkRotation(newNode);
                if (log.isDebugEnabled()) {
                    log.debug("{} insert to right of {}, tree becomes {}", newNode.toString(), node.toString(), toString());
                }
            } else {
                insertHelper(newNode, node.right);
            }
        }
    }

    /** assertion: node != null */
    void checkFourNode(Node<T> node) {
        if (node.color == BLACK &&
            node.left != null && node.left.color == RED &&
            node.right != null && node.right.color == RED) {
                if (log.isDebugEnabled()) {
                    log.debug("{} is four-node!", node.toString());
                }
                flipColor(node);
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("{} IS NOT four-node!", node.toString());
                }
            }
    }

    /** assertion: node != null */
    void checkRotation(Node<T> node) {
        Node<T> p = node.parent;
        if (node.color == BLACK || p == null || p.color == BLACK) return;
        if (log.isDebugEnabled()) {
            log.debug("{} is red, his parent {} is red too. Need to rotate!", node.toString(), p.toString());
        }
        Node<T> gp = p.parent;
        if (p == gp.left) {
            if (node == p.left) {
                if (log.isDebugEnabled()) {
                    log.debug("left zig-zig. 1 right rotation!");
                }
                rotateRight(gp);
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("left zig-zag. rotate left + rotate right!");
                }
                rotateLeft(p);
                rotateRight(gp);
            }
        } else {
            if (node == p.right) {
                if (log.isDebugEnabled()) {
                    log.debug("right zig-zig. 1 left rotation!");
                }
                rotateLeft(gp);
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("right zig-zag. rotate right + rotate left!");
                }
                rotateRight(p);
                rotateLeft(gp);
            }
        }
    }

    /**
     * assertion: suppose this is a black node with two red children.
     * Or, we can say a "4 node" in 2-3-4 tree
     */
    void flipColor(Node<T> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
        if (node == root) node.color = BLACK;
    }

    void rotateRight(Node<T> node) {
        Node<T> left = node.left;
        Node<T> parent = node.parent;
        left.parent = parent;
        if (parent != null) {
            if (parent.left == node) {
                parent.left = left;
            } else {
                parent.right = left;
            }
            if (log.isDebugEnabled()) {
                log.debug("parent {}, is connected to the left child {}", parent.toString(), left.toString());
            }
        }
        Node<T> leftRight = left.right;
        node.left = leftRight;
        if (leftRight != null) leftRight.parent = node;
        left.right = node;
        node.parent = left;
        left.color = node.color;
        node.color = RED;
        if (root == node) root = left;
    }

    void rotateLeft(Node<T> node) {
        Node<T> right = node.right;
        Node<T> parent = node.parent;
        right.parent = parent;
        if (parent != null) {
            if (parent.left == node) {
                parent.left = right;
            } else {
                parent.right = right;
            }
            if (log.isDebugEnabled()) {
                log.debug("parent {}, is connected to the right child {}", parent.toString(), right.toString());
            }
        }
        Node<T> rightLeft = right.left;
        node.right = rightLeft;
        if (rightLeft != null) rightLeft.parent = node;
        right.left = node;
        node.parent = right;
        right.color = node.color;
        node.color = RED;
        if (root == node) root = right;
    }

    /** serialize the whole red black tree */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Node<T>> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            StringBuilder level = new StringBuilder("\n{");
            boolean ignoreThisLevel = true;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node<T> node = queue.remove(0);
                level.append((node == null)? "null" : node.toString());
                if (i + 1 < size) level.append(", ");
                if (node != null) {
                    ignoreThisLevel = false;
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            level.append("}");
            if (level.length() > 2 && !ignoreThisLevel) sb.append(level.toString());
        }
        return sb.toString();
    }
}
