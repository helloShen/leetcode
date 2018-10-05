/**
 * Leetcode - minimum_time_difference
 */
package com.ciaoshen.leetcode.minimum_time_difference;

// basic util
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;
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
            {new ArrayList<String>(Arrays.asList(new String[]{"23:59", "00:00"})), 1},
            {new ArrayList<String>(Arrays.asList(new String[]{"07:53", "06:54", "09:18", "03:27", "10:08", "11:23"})), 50},
            {new ArrayList<String>(Arrays.asList(new String[]{"05:31","22:08","00:35"})), 147}
        });
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}



    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private List<String> timePoints;
    private int expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(List<String> timePoints, int expected) {
        this.timePoints = timePoints;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int actual = solution.findMinDifference(timePoints);
        assertThat(expected, is(equalTo(actual)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("findMinDifference() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
