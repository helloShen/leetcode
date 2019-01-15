/**
 * Leetcode - kill_process
 */
package com.ciaoshen.leetcode.kill_process;

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
            {new Integer[]{1,3,10,5}, new Integer[]{3,0,5,3}, 5, new Integer[]{5,10}}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private Integer[] pid;
    private Integer[] ppid;
    private int kill;
    private Integer[] expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(Integer[] pid, Integer[] ppid, int kill, Integer[] expected) {
        this.pid = pid;
        this.ppid = ppid;
        this.kill = kill;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<Integer> actual = solution.killProcess(new ArrayList<Integer>(Arrays.asList(pid)), new ArrayList<Integer>(Arrays.asList(ppid)), kill);
        List<Integer> expectedList = new ArrayList<Integer>(Arrays.asList(expected));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}", actual);
            LOGGER.debug("expected = {}", expectedList);
        }
        assertThat(actual.equals(expectedList), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method killProcess() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
