/**
 * Leetcode - ip_to_cidr
 */
package com.ciaoshen.leetcode.ip_to_cidr;

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
            {"255.0.0.7", 10, new String[]{"255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"}},
            {"0.0.0.0", 10, new String[]{"0.0.0.0/29","0.0.0.8/31"}},
            {"198.253.127.134", 129, new String[]{"198.253.127.134/31", "198.253.127.136/29", "198.253.127.144/28", "198.253.127.160/27", "198.253.127.192/26", "198.253.128.0/30", "198.253.128.4/31", "198.253.128.6/32"}}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private String ip;
    private int n;
    private String[] expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String ip, int n, String[] expected) {
        this.ip = ip;
        this.n = n;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    private String getString(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) sb.append(s).append(", ");
        if (sb.length() > 0) sb = sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
    /** Executed as a test case. */
    @Test
    public void test() {
        List<String> res = solution.ipToCIDR(ip, n);
        String[] actual = res.toArray(new String[res.size()]);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", getString(actual));
            LOGGER.debug("expected = {}", getString(expected));
        }
        boolean eq = actual.length == expected.length;
        for (int i = 0; i < actual.length; i++) {
            if (!actual[i].equals(expected[i])) {
                eq = false;
                break;
            }
        }
        assertThat(eq, is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method ipToCIDR() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
