/**
 * Leetcode - repeated_string_match
 */
package com.ciaoshen.leetcode.repeated_string_match;

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
        solution = new Solution2();
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {"abcd", "cdabcdab", 3},
            {"", "", 1},
            {"", "a", -1},
            {"a", "", 1},
            {"aabaa", "aaab", 2},
            {"aaaaaaaaaaaaaaaaaaaaaab", "ba", 2}
        });
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}



    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private String a;
    private String b;
    private int expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String a, String b, int expected) {
        this.a = a;
        this.b = b;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("A = {}", a);
            LOGGER.info("B = {}", b);
        }
        int actual = solution.repeatedStringMatch(a, b);
        if (LOGGER.isDebugEnabled()) {
            if (actual != -1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < actual; i++) sb.append(a);
                String repeated = sb.toString();
                LOGGER.info("\"{}\" is substring of \"{}\"", b, repeated);
            } else {
                LOGGER.info("\"{}\" can not be substring of \"{}\"", b, a);
            }
        }
        assertThat(actual, is(equalTo(expected)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("repeatedStringMatch() method pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
