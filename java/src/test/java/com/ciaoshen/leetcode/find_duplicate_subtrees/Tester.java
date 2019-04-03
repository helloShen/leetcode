/**
 * Leetcode - find_duplicate_subtrees
 */
package com.ciaoshen.leetcode.find_duplicate_subtrees;

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

    private static TreeNode testcase1;
    private static List<TreeNode> res1;

    private static void testcase1Init() {
        TreeNode one = new TreeNode(1);
        TreeNode two1 = new TreeNode(2);
        TreeNode two2 = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four1 = new TreeNode(4);
        TreeNode four2 = new TreeNode(4);
        TreeNode four3 = new TreeNode(4);
        one.left = two1;
        two1.left = four1;
        one.right = three;
        three.left = two2;
        two2.left = four2;
        three.right = four3;
        testcase1 = one;
        res1 = new LinkedList<TreeNode>();
        res1.add(four2);
        res1.add(two2);
    }

    static { testcase1Init(); }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {testcase1, res1}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private TreeNode root;
    private List<TreeNode> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(TreeNode root, List<TreeNode> expected) {
        this.root = root;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<TreeNode> actual = solution.findDuplicateSubtrees(root);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", actual);
            LOGGER.debug("expected = {}", expected);
        }
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method findDuplicateSubtrees() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
