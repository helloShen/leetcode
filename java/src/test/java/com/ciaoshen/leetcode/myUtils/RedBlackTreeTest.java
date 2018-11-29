/**
 * JUnit test of:
 *      com.ciaoshen.leetcode.myUtils.RedBlackTree
 */
package com.ciaoshen.leetcode.myUtils;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedBlackTreeTest {

    // call log4j from slf4j facade
    private static final Logger LOG = LoggerFactory.getLogger(RedBlackTreeTest.class);

    @Test
    public void testNodeToString() {
        RedBlackTree.Node<Integer> hundred = new RedBlackTree.Node<>(100);
        String actual = hundred.toString();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Node hundred = {}", actual);
        }
        String expected = "(100)";
        assertThat(actual.equals(expected), is(true));
    }

    /**
     * [*] -> black node
     * (*) -> red node
     *
     *              [5]
     *            /     \
     *          [3]     [10]
     *         /   \    /   \
     *       null (4) null  (12)
     */
    private RedBlackTree.Node<Integer> getTestCase1() {
        RedBlackTree.Node<Integer> five = new RedBlackTree.Node<>(5);
        RedBlackTree.Node<Integer> ten = new RedBlackTree.Node<>(10);
        RedBlackTree.Node<Integer> three = new RedBlackTree.Node<>(3);
        RedBlackTree.Node<Integer> four = new RedBlackTree.Node<>(4);
        RedBlackTree.Node<Integer> twelve = new RedBlackTree.Node<>(12);
        five.color = RedBlackTree.BLACK;
        five.left = three;
        three.parent = five;
        five.right = ten;
        ten.parent = five;
        three.color = RedBlackTree.BLACK;
        ten.color = RedBlackTree.BLACK;
        three.right = four;
        four.parent = three;
        ten.right = twelve;
        twelve.parent = ten;
        return five;
    }

    @Test
    public void testToString() {
        RedBlackTree<Integer> tree = new RedBlackTree<>(getTestCase1());
        if (LOG.isDebugEnabled()) {
            LOG.debug("Root = {}", tree.root);
        }
        String str = tree.toString();
        String expected = "\n{[5]}\n{[3], [10]}\n{null, (4), null, (12)}";
        if (LOG.isDebugEnabled()) {
            LOG.debug("str = {}", str);
            LOG.debug("expected = {}", expected);
        }
        assertThat(str.equals(expected), is(true));
    }


    /**
     * [*] -> black node
     * (*) -> red node
     *
     *              [10]
     *            /     \
     *          (7)     [15]
     *         /   \
     *       [5]  [8]
     *      /  \
     *    (3)  (6)
     */
    private Map<Integer, RedBlackTree.Node<Integer>> getTestCase2() {
        RedBlackTree.Node<Integer> ten = new RedBlackTree.Node<>(10);
        RedBlackTree.Node<Integer> seven = new RedBlackTree.Node<>(7);
        RedBlackTree.Node<Integer> fifteen = new RedBlackTree.Node<>(15);
        RedBlackTree.Node<Integer> five = new RedBlackTree.Node<>(5);
        RedBlackTree.Node<Integer> eight = new RedBlackTree.Node<>(8);
        RedBlackTree.Node<Integer> three = new RedBlackTree.Node<>(3);
        RedBlackTree.Node<Integer> six = new RedBlackTree.Node<>(6);
        Map<Integer, RedBlackTree.Node<Integer>> map = new HashMap<>();
        map.put(10, ten);
        map.put(7, seven);
        map.put(15, fifteen);
        map.put(5, five);
        map.put(8, eight);
        map.put(3, three);
        map.put(6, six);
        ten.left = seven;
        seven.parent = ten;
        ten.right = fifteen;
        fifteen.parent = ten;
        ten.color = RedBlackTree.BLACK;
        fifteen.color = RedBlackTree.BLACK;
        seven.left = five;
        five.parent = seven;
        five.color = RedBlackTree.BLACK;
        seven.right = eight;
        eight.parent = seven;
        eight.color = RedBlackTree.BLACK;
        five.left = three;
        three.parent = five;
        five.right = six;
        six.parent = five;
        return map;
    }

    @Test
    public void testFlipColor() {
        Map<Integer, RedBlackTree.Node<Integer>> nodeTable = getTestCase2();
        RedBlackTree<Integer> tree = new RedBlackTree<>(nodeTable.get(10));
        if (LOG.isDebugEnabled()) {
            LOG.debug("Root = {}", tree.root);
        }
        assertThat(tree.root.val== 10, is(true));
        tree.flipColor(nodeTable.get(5));
        String fliped = tree.toString();
        String expected = "\n{[10]}\n{(7), [15]}\n{(5), [8], null, null}\n{[3], [6], null, null}";
        if (LOG.isDebugEnabled()) {
            LOG.debug("fliped tree = {}", fliped);
            LOG.debug("expected = {}", expected);
        }
        assertThat(fliped.equals(expected), is(true));
    }

    /**
     * [*] -> black node
     * (*) -> red node
     *
     * After rotation, tree should look like:
     *
     *               [7]
     *            /      \
     *          (5)      (10)
     *         /   \     /  \
     *       [3]  [6]  [8]  [15]
     */
    @Test
    public void testRotateRight() {
        Map<Integer, RedBlackTree.Node<Integer>> nodeTable = getTestCase2();
        RedBlackTree<Integer> tree = new RedBlackTree<>(nodeTable.get(10));
        if (LOG.isDebugEnabled()) {
            LOG.debug("Root = {}", tree.root);
        }
        assertThat(tree.root.val== 10, is(true));
        tree.flipColor(nodeTable.get(5));
        tree.rotateRight(nodeTable.get(10));
        String rotated = tree.toString();
        String expected = "\n{[7]}\n{(5), (10)}\n{[3], [6], [8], [15]}";
        if (LOG.isDebugEnabled()) {
            LOG.debug("rotated tree = {}", rotated);
            LOG.debug("expected tree = {}", expected);
        }
        assertThat(rotated.equals(expected), is(true));
    }

    /**
     * [*] -> black node
     * (*) -> red node
     *
     *              [10]
     *            /     \
     *          (4)     [15]
     *         /   \
     *       [3]  [7]
     *           /  \
     *         (6)  (8)
     */
    private Map<Integer, RedBlackTree.Node<Integer>> getTestCase3() {
        RedBlackTree.Node<Integer> ten = new RedBlackTree.Node<>(10);
        RedBlackTree.Node<Integer> four = new RedBlackTree.Node<>(4);
        RedBlackTree.Node<Integer> fifteen = new RedBlackTree.Node<>(15);
        RedBlackTree.Node<Integer> three = new RedBlackTree.Node<>(3);
        RedBlackTree.Node<Integer> seven = new RedBlackTree.Node<>(7);
        RedBlackTree.Node<Integer> six = new RedBlackTree.Node<>(6);
        RedBlackTree.Node<Integer> eight = new RedBlackTree.Node<>(8);
        Map<Integer, RedBlackTree.Node<Integer>> map = new HashMap<>();
        map.put(10, ten);
        map.put(4, four);
        map.put(15, fifteen);
        map.put(3, three);
        map.put(7, seven);
        map.put(6, six);
        map.put(8, eight);
        ten.left = four;
        four.parent = ten;
        ten.right = fifteen;
        fifteen.parent = ten;
        ten.color = RedBlackTree.BLACK;
        fifteen.color = RedBlackTree.BLACK;
        four.left = three;
        three.parent = four;
        three.color = RedBlackTree.BLACK;
        four.right = seven;
        seven.parent = four;
        seven.right = eight;
        seven.color = RedBlackTree.BLACK;
        seven.left = six;
        six.parent = seven;
        seven.right = eight;
        eight.parent = seven;
        return map;
    }


    /**
     * [*] -> black node
     * (*) -> red node
     *
     * After rotation, tree should look like:
     *
     *              [7]
     *            /     \
     *          (4)     (10)
     *         /   \    /   \
     *       [3]  [6] [8]  [15]
     */
    @Test
    public void testRotateLeft() {
        Map<Integer, RedBlackTree.Node<Integer>> nodeTable = getTestCase3();
        RedBlackTree<Integer> tree = new RedBlackTree<>(nodeTable.get(10));
        if (LOG.isDebugEnabled()) {
            LOG.debug("Root = {}", tree.root);
        }
        assertThat(tree.root.val== 10, is(true));
        tree.flipColor(nodeTable.get(7));
        tree.rotateLeft(nodeTable.get(4));
        String medium = tree.toString();
        String expectedMedium = "\n{[10]}\n{(7), [15]}\n{(4), [8], null, null}\n{[3], [6], null, null}";
        tree.rotateRight(nodeTable.get(10));
        String rotated = tree.toString();
        String expected = "\n{[7]}\n{(4), (10)}\n{[3], [6], [8], [15]}";
        if (LOG.isDebugEnabled()) {
            LOG.debug("medium tree = {}", medium);
            LOG.debug("expected medium tree = {}", expectedMedium);
            LOG.debug("rotated tree = {}", rotated);
            LOG.debug("expected tree = {}", expected);
        }
        assertThat(medium.equals(expectedMedium), is(true));
        assertThat(rotated.equals(expected), is(true));
    }

    private char[][] letters = new char[][]{
        {'A', 'B', 'C'},
        {'C', 'B', 'A'},
        {'A', 'B', 'C', 'D'},
        {'A', 'S', 'E', 'R', 'C', 'H', 'I', 'N', 'G', 'X'},
        {'A', 'S', 'R', 'C', 'H', 'I', 'N', 'G', 'E', 'X', 'M', 'P', 'L'}
    };
    private String[] expected = new String[]{
        "\n{[B]}\n{(A), (C)}",
        "\n{[B]}\n{(A), (C)}",
        "\n{[B]}\n{[A], [C]}\n{null, null, null, (D)}",
        "\n{[I]}\n{[E], [R]}\n{[A], [H], [N], [S]}\n{null, (C), (G), null, null, null, null, (X)}",
        "\n{[I]}\n{[C], [R]}\n{[A], [G], (N), [S]}\n{null, null, (E), (H), [M], [P], null, (X)}\n{null, null, null, null, (L), null, null, null, null, null}"
    };

    @Test
    public void testInsert() {
        for (int i = 0; i < letters.length; i++) {
            RedBlackTree tree = new RedBlackTree();
            for (char letter : letters[i]) {
                tree.insert(letter);
            }
            String actual = tree.toString();
            if (LOG.isDebugEnabled()) {
                LOG.debug("actual = {}", actual);
                LOG.debug("expected = {}", expected[i]);
            }
            assertThat(actual.equals(expected[i]), is(true));
        }
    }

    @Test
    public void testSearch() {
        RedBlackTree tree = new RedBlackTree();
        for (char letter : letters[4]) {
            tree.insert(letter);
        }
        for (char letter : letters[4]) {
            assertThat(tree.search(letter), is(true));
        }
        char[] otherLetters = new char[]{'B', 'D', 'F', 'J', 'K', 'O', 'Q', 'T', 'U', 'V', 'W', 'Y', 'Z'};
        for (char otherLetter : otherLetters) {
            assertThat(tree.search(otherLetter), is(false));
        }
    }

}
