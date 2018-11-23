/**
 * Leetcode - design_hashmap
 */
package com.ciaoshen.leetcode.design_hashmap;

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
            {new int[]{1, 2, 3}, new int[]{111, 222, 333}, new int[]{1, 2, 3, 4, 5}, new int[]{}, new int[]{111, 222, 333, -1, -1}},
            {new int[]{1, 1, 1}, new int[]{111, 222, 333}, new int[]{1, 2, 3, 4, 5}, new int[]{}, new int[]{333, -1, -1, -1, -1}},
            {new int[]{1, 2, 3}, new int[]{111, 222, 333}, new int[]{1, 2, 3, 4, 5}, new int[]{1, 2}, new int[]{-1, -1, 333, -1, -1}}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] puts;
    private int[] values;
    private int[] gets;
    private int[] removes;
    private int[] expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] puts, int[] values, int[] gets, int[] removes, int[] expected) {
        this.puts = puts;
        this.values = values;
        this.gets = gets;
        this.removes = removes;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int[] actual = new int[gets.length];
        solution.init();
        for (int i = 0; i < puts.length; i++) solution.put(puts[i], values[i]);
        for (int r : removes) solution.remove(r);
        int idx = 0;
        for (int g : gets) {
            actual[idx++] = solution.get(g);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", Arrays.toString(actual));
            LOGGER.debug("expected = {}", Arrays.toString(expected));
        }
        assertThat(Arrays.equals(actual, expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("My HashMap pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
