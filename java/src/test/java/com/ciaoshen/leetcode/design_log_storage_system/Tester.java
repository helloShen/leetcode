/**
 * Leetcode - design_log_storage_system
 */
package com.ciaoshen.leetcode.design_log_storage_system;

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

        solution.put(1, "2017:01:01:23:59:59");
        solution.put(2, "2017:01:01:22:59:59");
        solution.put(3, "2016:01:01:00:00:00");
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {"2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year", new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2, 3}))},
            {"2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour", new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2}))}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private String s;
    private String e;
    private String gra;
    private List<Integer> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String s, String e, String gra, List<Integer> expected) {
        this.s = s;
        this.e = e;
        this.gra = gra;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<Integer> actual = solution.retrieve(s, e, gra);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", actual);
            LOGGER.debug("expected = {}", expected);
        }
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Solution{} pass unit test!", solution.getClass().getName());
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
