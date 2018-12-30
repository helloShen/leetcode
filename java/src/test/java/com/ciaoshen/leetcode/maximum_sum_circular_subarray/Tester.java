/**
 * Leetcode - maximum_sum_circular_subarray
 */
package com.ciaoshen.leetcode.maximum_sum_circular_subarray;

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
        // solution = new Solution2();
        solution = new Solution3();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {new int[]{1,-2,3,-2}, 3},          // 0
            {new int[]{5,-3,5}, 10},            // 1
            {new int[]{3,-1,2,-1}, 4},          // 2
            {new int[]{3,-2,2,-3}, 3},          // 3
            {new int[]{-2,-3,-1}, -1},          // 4
            {new int[]{0}, 0},                  // 5
            {new int[]{-1}, -1},                // 6
            {new int[]{0,2,4,-5,-6,7}, 13},     // 7
            {new int[]{-5, 4, -6}, 4},          // 8
            {new int[]{3,1,3,2,6}, 15},         // 9
            {new int[]{-2,4,-5,4,-5,9,4}, 15},  // 10
            {new int[]{0,5,8,-9,9,-7,3,-2}, 16}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] nums;
    private int expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] nums, int expected) {
        this.nums = nums;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int actual = solution.maxSubarraySumCircular(nums);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}, expected = {}", actual, expected);
        }
        assertThat(actual == expected, is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method maxSubarraySumCircular() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
