/**
 * Leetcode - validate_ip_adress
 */
package com.ciaoshen.leetcode.validate_ip_adress;

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
            {"172.16.254.1", "IPv4"},
            {"2001:0db8:85a3:0:0:8A2E:0370:7334", "IPv6"},
            {"256.256.256.256", "Neither"},
            {"1e1.4.5.6", "Neither"},
            {"12..33.4", "Neither"},
            {"1.1.1.1.", "Neither"},
            {"0.0.0.256", "Neither"},
            {"0.0.0.-0", "Neither"}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private String IP;
    private String expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String IP, String expected) {
        this.IP = IP;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        String actual = solution.validIPAddress(IP);
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("validIPAddress() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
