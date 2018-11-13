/**
 * Leetcode - two_sum_three
 */
package com.ciaoshen.leetcode.two_sum_three;

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
            {new int[]{1, 3, 5}, new int[]{4, 7}, new ArrayList<Boolean>(Arrays.asList(new Boolean[]{true, false}))},
            {new int[]{3, 1, 2}, new int[]{3, 6}, new ArrayList<Boolean>(Arrays.asList(new Boolean[]{true, false}))}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] addArr;
    private int[] findArr;
    private List<Boolean> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] addArr, int[] findArr, List<Boolean> expected) {
        this.addArr = addArr;
        this.findArr = findArr;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        solution.init();
        List<Boolean> actual = new ArrayList<>();
        for (int n : addArr) solution.add(n);
        for (int n : findArr) actual.add(solution.find(n));
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Class TwoSumThree pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
