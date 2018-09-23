/**
 * Leetcode - coin_change_two
 */
package com.ciaoshen.leetcode.coin_change_two;

// basic util
import java.util.*;
import com.ciaoshen.leetcode.util.*;
// JUnit & hamcrest
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
// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Parameterized.class)
public class Tester {

    /**=========================== 【static for different test cases】 ============================== */

    // Solution to test
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

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {5, new int[]{1, 2, 5}, 4},
            {3, new int[]{2}, 0}
            // {}      // test case 3 (parameters in array)
        });
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}



    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private int amount;
    private int[] coins;
    private int expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int amount, int[] coins, int expected) {
        this.amount= amount;
        this.coins= coins;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int actual = solution.change(amount, coins);
        assertThat(actual, is(equalTo(expected)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("method change() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
