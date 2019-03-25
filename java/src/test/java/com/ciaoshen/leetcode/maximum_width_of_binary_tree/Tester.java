/**
 * Leetcode - maximum_width_of_binary_tree
 */
package com.ciaoshen.leetcode.maximum_width_of_binary_tree;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(TesterRunner.class);

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        /* uncomment to switch solutions */
        solution = new Solution1();
        // solution = new Solution2();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    private static TreeNode testcase1() {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three1 = new TreeNode(3);
        TreeNode three2 = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        TreeNode nine = new TreeNode(9);
        one.left = three1;
        one.right = two;
        three1.left = five;
        three1.right = three2;
        two.right = nine;
        return one;
    }

    private static TreeNode testcase2() {
        TreeNode one = new TreeNode(1);
        TreeNode three1 = new TreeNode(3);
        TreeNode three2 = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        one.left = three1;
        three1.left = five;
        three1.right = three2;
        return one;
    }

    private static TreeNode testcase3() {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        one.left = three;
        one.right = two;
        three.left = five;
        return one;
    }

    private static TreeNode testcase4() {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode nine = new TreeNode(9);
        one.left = three;
        one.right = two;
        three.left = five;
        two.right = nine;
        five.left = six;
        nine.right = seven;
        return one;
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {testcase1(), 4},
            {testcase2(), 2},
            {testcase3(), 2},
            {testcase4(), 8}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private TreeNode root;
    private int expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(TreeNode root, int expected) {
        this.root = root;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int actual = solution.widthOfBinaryTree(root);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}, expected = {}", actual, expected);
        }
        assertThat(actual == expected, is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method widthOfBinaryTree() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
