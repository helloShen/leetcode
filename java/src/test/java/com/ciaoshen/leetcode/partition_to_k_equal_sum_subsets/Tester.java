/**
 * Leetcode - partition_to_k_equal_sum_subsets
 */
package com.ciaoshen.leetcode.partition_to_k_equal_sum_subsets;

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

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {new int[]{4, 3, 2, 3, 5, 2, 1}, 4, true},
            {new int[]{4, 85, 8, 10, 98, 12, 6, 90, 11}, 3, true},
            {new int[]{10, 6, 1, 7, 92, 84}, 2, true},
            {new int[]{1, 10, 8, 94, 82, 5}, 2, true},
            {new int[]{6, 8, 2, 7, 6, 5, 5, 4}, 4, false},
            {new int[]{4, 7, 9, 11, 23, 12, 5, 6}, 7, false},
            {new int[]{15, 4, 3, 7, 16, 1, 4}, 5, false},
            {new int[]{2,11,1,10,4,10,1,4,2}, 3, true}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] nums;
    private int k;
    private boolean expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] nums, int k, boolean expected) {
        this.nums = nums;
        this.k = k;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        boolean actual = solution.canPartitionKSubsets(nums, k);
        assertThat(actual, is(equalTo(expected)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("canPartitionKSubsets() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
