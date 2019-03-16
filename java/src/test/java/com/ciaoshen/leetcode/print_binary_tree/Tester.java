/**
 * Leetcode - print_binary_tree
 */
package com.ciaoshen.leetcode.print_binary_tree;

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
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        one.left = two;
        one.right = three;
        two.right = four;
        return one;
    }

    private static List<List<String>> result1() {
        List<List<String>> res = new ArrayList<List<String>>();
        res.add(new ArrayList<String>(Arrays.asList(new String[]{"", "", "", "1", "", "", ""})));
        res.add(new ArrayList<String>(Arrays.asList(new String[]{"", "2", "", "", "", "3", ""})));
        res.add(new ArrayList<String>(Arrays.asList(new String[]{"", "", "4", "", "", "", ""})));
        return res;
    }
    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {testcase1(), result1()}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private TreeNode root;
    private List<List<String>> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(TreeNode root, List<List<String>> expected) {
        this.root = root;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<List<String>> actual = solution.printTree(root);
        assertThat(actual.size() == expected.size(), is(true));
        for (int i = 0; i < actual.size(); i++) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{}", actual.get(i));
            }
            assertThat(actual.get(i).equals(expected.get(i)), is(true));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method printTree() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
