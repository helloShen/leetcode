/**
 * Leetcode - network_delay_time
 */
package com.ciaoshen.leetcode.network_delay_time;

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
        // solution = new Solution3();
        // solution = new Solution4();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {new int[][]{{1, 2, 3}, {1, 4, 4}, {1, 5, 6}, {2, 3, 7}, {2, 6, 5}, {3, 6, 2}, {5, 6, 1}}, 6, 1, 10},
            {new int[][]{{1, 2, 1}, {2, 1, 3}}, 2, 2, 3},
            {new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 1}}, 3, 2, -1},
            {new int[][]{{3,5,78},{2,1,1},{1,3,0},{4,3,59},{5,3,85},{5,2,22},{2,4,23},{1,4,43},{4,5,75},{5,1,15},{1,5,91},{4,1,16},{3,2,98},{3,4,22},{5,4,31},{1,2,0},{2,5,4},{4,2,51},{3,1,36},{2,3,59}}, 5, 5, 31}
            // {},     // test case 2 (init parameters below: {para1, para2, expected})
            // {}      // test case 3 (init parameters below: {para1, para2, expected})
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[][] times;
    private int n;
    private int k;
    private int expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[][] times, int n, int k, int expected) {
        // initialize test parameters
        this.times = times;
        this.n = n;
        this.k = k;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int actual = solution.networkDelayTime(times, n, k);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", actual);
            LOGGER.debug("expected = {}", expected);
        }
        assertThat(actual, is(equalTo(expected)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("networkDelayTime() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
