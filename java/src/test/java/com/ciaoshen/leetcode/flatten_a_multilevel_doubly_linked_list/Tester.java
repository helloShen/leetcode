/**
 * Leetcode - flatten_a_multilevel_doubly_linked_list
 */
package com.ciaoshen.leetcode.flatten_a_multilevel_doubly_linked_list;

import java.util.*;
// import com.ciaoshen.leetcode.util.*;

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

import static com.ciaoshen.leetcode.flatten_a_multilevel_doubly_linked_list.Solution.*;

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

    private static Node testcase1() {
        int size = 12;
        Node[] nodes = new Node[size];
        for (int i = size - 1; i >= 0; i--) {
            nodes[i] = new Node(i + 1);
            // if (LOGGER.isDebugEnabled()) {
            //     LOGGER.debug("nodes[{}].val should be {}, nodes[{}] = {}", i, i + 1, i, nodes[i]);
            // }
        }
        // if (LOGGER.isDebugEnabled()) {
        //     LOGGER.debug("Nodes Array = {}", Arrays.toString(nodes));
        // }
        int[] ends = new int[]{6, 10, 12};
        int[] starts = new int[]{1, 7, 11};
        for (int i = 0; i < ends.length; i++) {
            for (int j = ends[i] - 1; j >= starts[i] - 1; j--) {
                if (j + 1 < ends[i]) {
                    nodes[j].next = nodes[j + 1];
                    nodes[j + 1].prev = nodes[j];
                    // if (LOGGER.isDebugEnabled()) {
                    //     LOGGER.debug("Connect node {} to the end of node {}", nodes[j + 1].val, nodes[j].val);
                    // }
                }
            }
        }
        nodes[2].child = nodes[6];
        nodes[7].child = nodes[10];
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("testcase1 = {}", nodes[0]);
        }
        return nodes[0];
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {testcase1(), "1-2-3-7-8-11-12-9-10-4-5-6-NULL"}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private Node head;
    private String expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(Node head, String expected) {
        this.head = head;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        String actual = solution.flatten(head).toString();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", actual);
            LOGGER.debug("expected = {}", expected);
        }
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method flatten() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
