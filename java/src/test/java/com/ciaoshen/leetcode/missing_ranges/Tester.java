/**
 * Leetcode - missing_ranges
 */
package com.ciaoshen.leetcode.missing_ranges;

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
            {new int[]{0, 1, 3, 50, 75}, 0, 99, new ArrayList<String>(Arrays.asList(new String[]{"2", "4->49", "51->74", "76->99"}))},
            {new int[]{}, 1, 1, new ArrayList<String>(Arrays.asList(new String[]{"1"}))},
            {new int[]{Integer.MAX_VALUE}, 0, Integer.MAX_VALUE, new ArrayList<String>(Arrays.asList(new String[]{"0->" + (Integer.MAX_VALUE - 1)}))}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] nums;
    private int lower;
    private int upper;
    private List<String> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] nums, int lower, int upper, List<String> expected) {
        this.nums = nums;
        this.lower = lower;
        this.upper = upper;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<String> actual = solution.findMissingRanges(nums, lower, upper);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", actual);
            LOGGER.debug("expected = {}", expected);
        }
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("findMissingRanges() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
