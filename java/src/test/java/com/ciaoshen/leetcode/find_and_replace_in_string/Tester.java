/**
 * Leetcode - find_and_replace_in_string
 */
package com.ciaoshen.leetcode.find_and_replace_in_string;

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
            {"abcd", new int[]{0,2}, new String[]{"a","cd"}, new String[]{"eee","ffff"}, "eeebffff"},
            {"abcd", new int[]{0,2}, new String[]{"ab","ec"}, new String[]{"eee","ffff"}, "eeecd"},
            {"vmokgggqzp", new int[]{3,5,1}, new String[]{"kg","ggq","mo"}, new String[]{"s","so","bfr"}, "vbfrssozp"}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private String s;
    private int[] indexs;
    private String[] sources;
    private String[] targets;
    private String expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String s, int[] indexs, String[] sources, String[] targets, String expected) {
        this.s = s;
        this.indexs = indexs;
        this.sources = sources;
        this.targets = targets;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        String actual = solution.findReplaceString(s, indexs, sources, targets);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}, expected = {}", actual, expected);
        }
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method findReplaceString() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
