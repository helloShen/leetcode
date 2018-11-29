/**
 * Leetcode - delete_node_in_a_bst
 */
package com.ciaoshen.leetcode.delete_node_in_a_bst;

import java.util.*;
import com.ciaoshen.leetcode.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Parameterized.class)
public class Tester {

    /**=========================== static for every test cases ============================== */

    // Solution instance to test
    private static Solution solution;
    // use this Object to print the log (call from slf4j facade)
    private static final Logger LOGGER = LoggerFactory.getLogger(Tester.class);

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        /* uncomment to switch solutions */
        // solution = new Solution1();
        solution = new Solution2();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    private static TreeNode testCase1() {
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode six = new TreeNode(6);
        TreeNode two = new TreeNode(2);
        TreeNode four = new TreeNode(4);
        TreeNode seven = new TreeNode(7);
        five.left = three;
        five.right = six;
        three.left = two;
        three.right = four;
        six.right = seven;
        return five;
    }
    private static TreeNode testCase2() {
        TreeNode two = new TreeNode(2);
        TreeNode one = new TreeNode(1);
        two.left = one;
        return two;
    }

    private static TreeNode testCase3() {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        one.right = two;
        return one;
    }

    private static TreeNode testCase4() {
        TreeNode three = new TreeNode(3);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode two = new TreeNode(2);
        three.left = one;
        three.right = four;
        one.right = two;
        return three;
    }

    private static TreeNode testCase5() {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        three.left = two;
        three.right = four;
        two.left = one;
        return three;
    }

    private static TreeNode testCase6() {
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        five.left = three;
        five.right = eight;
        three.left = two;
        three.right = four;
        eight.right = seven;
        return five;
    }

    private static TreeNode testCase7() {
        TreeNode tree = testCase6();
        tree.right.left = new TreeNode(6);
        return tree;
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {testCase1(), 3, "[[5], [4, 6], [2, null, null, 7]]"},
            {testCase2(), 2, "[[1]]"},
            {testCase2(), 1, "[[2]]"},
            {testCase3(), 2, "[[1]]"},
            {testCase4(), 2, "[[3], [1, 4]]"},
            {testCase5(), 2, "[[3], [1, 4]]"},
            {testCase6(), 5, "[[8], [3, 7], [2, 4, null, null]]"},
            {testCase6(), 0, "[[5], [3, 8], [2, 4, null, 7]]"},
            {testCase7(), 5, "[[6], [3, 8], [2, 4, null, 7]]"}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private TreeNode tree;
    private int key;
    private String expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(TreeNode tree, int key, String expected) {
        this.tree = tree;
        this.key = key;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        String actual = solution.deleteNode(tree, key).toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", actual);
            LOGGER.debug("expected = {}", expected);
        }
        assertThat(actual, is(equalTo(expected)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("deleteNode() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
