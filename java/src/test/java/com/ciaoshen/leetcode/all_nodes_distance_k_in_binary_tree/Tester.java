/**
 * Leetcode - all_nodes_distance_k_in_binary_tree
 */
package com.ciaoshen.leetcode.all_nodes_distance_k_in_binary_tree;

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

    private static TreeNode[] getTree1() {
        int size = 9, target = 5;
        TreeNode[] nodes = new TreeNode[9];
        for (int i = 0; i < 9; i++) {
            nodes[i] = new TreeNode(i);
        }
        nodes[3].left = nodes[5];
        nodes[3].right = nodes[1];
        nodes[5].left = nodes[6];
        nodes[5].right = nodes[2];
        nodes[1].left = nodes[0];
        nodes[1].right = nodes[8];
        nodes[2].left = nodes[7];
        nodes[2].right = nodes[4];
        TreeNode[] res = new TreeNode[2];
        res[0] = nodes[3];
        res[1] = nodes[5];
        return res;
    }
    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        TreeNode[] testcase = getTree1();
        return Arrays.asList(new Object[][]{
            {testcase[0], testcase[1], 2, new ArrayList<Integer>(Arrays.asList(new Integer[]{7,4,1}))}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private TreeNode root;
    private TreeNode target;
    private int k;
    private List<Integer> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(TreeNode root, TreeNode target, int k, List<Integer> expected) {
        this.root = root;
        this.target = target;
        this.k = k;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<Integer> actual = solution.distanceK(root, target, k);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", actual);
            LOGGER.debug("expected = {}", expected);
        }
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method distanceK() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
