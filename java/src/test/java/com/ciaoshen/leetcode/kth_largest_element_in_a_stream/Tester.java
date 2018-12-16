/**
 * Leetcode - kth_largest_element_in_a_stream
 */
package com.ciaoshen.leetcode.kth_largest_element_in_a_stream;

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
            {3, new int[]{4,5,8,2}, new int[]{3,5,10,9,4}, new int[]{4,5,5,8,8}}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int k;
    private int[] nums;
    private int[] addNums;
    private int[] expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int k, int[] nums, int[] addNums, int[] expected) {
        this.k = k;
        this.nums = nums;
        this.addNums = addNums;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int[] actual = new int[addNums.length];
        solution.init(k, nums);
        for (int i = 0; i < addNums.length; i++) {
            actual[i] = solution.add(addNums[i]);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", Arrays.toString(actual));
            LOGGER.debug("expected = {}", Arrays.toString(expected));
        }
        assertThat(Arrays.equals(actual, expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Kth largest element pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
