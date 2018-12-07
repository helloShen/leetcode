/**
 * Leetcode - accounts_merge
 */
package com.ciaoshen.leetcode.accounts_merge;

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

    private static List<List<String>> testcase1() {
        String[][] accounts = new String[][]{
            {"John", "johnsmith@mail.com", "john00@mail.com"},
            {"John", "johnnybravo@mail.com"},
            {"John", "johnsmith@mail.com", "john_newyork@mail.com"},
            {"Mary", "mary@mail.com"}
        };
        List<List<String>> testcase = new ArrayList<>();
        for (String[] account : accounts) {
            testcase.add(new ArrayList<String>(Arrays.asList(account)));
        }
        return testcase;
    }

    private static List<List<String>> result1() {
        String[][] merged = new String[][]{
            {"John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"},
            {"John", "johnnybravo@mail.com"},
            {"Mary", "mary@mail.com"}
        };
        List<List<String>> result = new ArrayList<>();
        for (String[] group : merged) {
            result.add(new ArrayList<String>(Arrays.asList(group)));
        }
        return result;
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {testcase1(), result1()}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private List<List<String>> accounts;
    private List<List<String>> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(List<List<String>> accounts, List<List<String>> expected) {
        this.accounts = accounts;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<List<String>> actual = solution.accountsMerge(accounts);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Actual = \n");
            for (List<String> line : actual) {
                LOGGER.debug("{}", line);
            }
            LOGGER.debug("\nExpected = \n");
            for (List<String> line : expected) {
                LOGGER.debug("{}", line);
            }
        }
        for (int i = 0; i < actual.size(); i++) {
            assertThat((actual.get(i)).equals(expected.get(i)), is(true));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method accountsMerge() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
