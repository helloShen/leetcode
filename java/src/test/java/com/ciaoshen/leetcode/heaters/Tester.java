/**
 * Leetcode - heaters
 */
package com.ciaoshen.leetcode.heaters;

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
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {new int[]{1,2,3}, new int[]{2}, 1},
            {new int[]{1,2,3,4}, new int[]{1,4}, 1},
            {new int[]{1,5}, new int[]{2}, 3},
            {new int[]{282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923},
             new int[]{823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612}, 161834419}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] houses;
    private int[] heaters;
    private int expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] houses, int[] heaters, int expected) {
        this.houses = houses;
        this.heaters = heaters;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int actual = solution.findRadius(houses, heaters);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}, expected = {}", actual, expected);
        }
        assertThat(actual == expected, is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method findRadius() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
