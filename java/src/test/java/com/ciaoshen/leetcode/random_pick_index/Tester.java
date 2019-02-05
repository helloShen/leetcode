/**
 * Leetcode - random_pick_index
 */
package com.ciaoshen.leetcode.random_pick_index;

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
            {new int[]{1,2,3,3,3}, 3}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] nums;
    private int target;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int size = 100;
        int[] res = new int[3];
        solution.init(nums);
        for (int i = 0; i < size; i++) {
            int idx = solution.pick(target);
            switch(idx) {
                case 2: res[0]++; break;
                case 3: res[1]++; break;
                case 4: res[2]++; break;
                default: break;
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Freq = {}", Arrays.toString(res));
        }
        assertThat(res[0] > 5, is(true));
        assertThat(res[0] < 50, is(true));
        assertThat(res[1] > 5, is(true));
        assertThat(res[1] < 50, is(true));
        assertThat(res[2] > 5, is(true));
        assertThat(res[2] < 50, is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method pick() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
