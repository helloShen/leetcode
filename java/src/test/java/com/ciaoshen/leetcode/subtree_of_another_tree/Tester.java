/**
 * Leetcode - subtree_of_another_tree
 */
package com.ciaoshen.leetcode.subtree_of_another_tree;

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
        // solution = new Solution1();
        solution = new Solution2();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** [4,[1,2]] */
    private static TreeNode testCase1() {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode four = new TreeNode(4);
        four.left = one;
        four.right = two;
        return four;
    }

    /** [3,[4,5],[1,2,null,null]] */
    private static TreeNode testCase2() {
        TreeNode four = testCase1();
        TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        three.left = four;
        three.right = five;
        return three;
    }

    /** [3,[4,5],[1,2,null,null],[null,null,0,null]] */
    private static TreeNode testCase3() {
        TreeNode three = testCase2();
        three.left.right.left = new TreeNode(0);
        return three;
    }

    /** [1,[1]] */
    private static TreeNode testCase4() {
        TreeNode one1 = new TreeNode(1);
        TreeNode one2 = new TreeNode(1);
        one1.left = one2;
        return one1;
    }


    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {testCase2(), testCase1(), true},
            {testCase3(), testCase1(), false},
            {testCase4(), new TreeNode(1), true}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private TreeNode s;
    private TreeNode t;
    private boolean expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(TreeNode s, TreeNode t, boolean expected) {
        this.s = s;
        this.t = t;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        boolean actual = solution.isSubtree(s, t);
        assertThat(actual == expected, is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method isSubtree() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
