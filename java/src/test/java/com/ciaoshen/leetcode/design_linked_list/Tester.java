/**
 * Leetcode - design_linked_list
 */
package com.ciaoshen.leetcode.design_linked_list;

import java.util.*;
// import com.ciaoshen.leetcode.util.*;
import static com.ciaoshen.leetcode.design_linked_list.Solution.Node;

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

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {new String[]{"1->2->3", "2", "1->3", "3"}}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private String[] expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String[] expected) {
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        String[] actual = new String[4];
        solution.init();
        solution.addAtHead(1);
        solution.addAtTail(3);
        solution.addAtIndex(1,2);
        actual[0] = solution.toString();
        actual[1] = String.valueOf(solution.get(1));
        solution.deleteAtIndex(1);
        actual[2] = solution.toString();
        actual[3] = String.valueOf(solution.get(1));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", Arrays.toString(actual));
            LOGGER.debug("expected = {}", Arrays.toString(expected));
        }
        assertThat(Arrays.equals(actual, expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("class MyLinkedList pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
