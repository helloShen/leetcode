/**
 * Leetcode - exam_room
 */
package com.ciaoshen.leetcode.exam_room;

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

    // use this Object to print the log (call from slf4j facade)
    private static final Logger LOGGER = LoggerFactory.getLogger(TesterRunner.class);

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {10, new String[]{"seat", "seat", "seat", "seat", "leave", "seat"}, new int[]{4}, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 9, 4, 2, 5}))},
            {4, new String[]{"seat", "seat", "seat", "seat", "leave", "leave", "seat"}, new int[]{1, 3}, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 3, 1, 2, 1}))},
            {10, new String[]{"seat","seat","seat","leave","leave","seat","seat","seat","seat","seat","seat","seat","seat","seat","leave"}, new int[]{0, 4, 0}, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 9, 4, 0, 4, 2, 6, 1, 3, 5, 7, 8}))}
            // {}      // test case 3 (init parameters below: {para1, para2, expected})
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private Solution solution;
    private String[] methods;
    private int[] seats;
    private List<Integer> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int n, String[] methods, int[] seats, List<Integer> expected) {
        // solution = new Solution1(n);
        // solution = new Solution2(n);
        solution = new Solution3(n);
        this.methods = methods;
        this.seats = seats;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<Integer> actual = new ArrayList<>();
        int seatsP = 0;
        for (String method : methods) {
            switch (method) {
                case "seat":
                    actual.add(solution.seat()); break;
                case "leave":
                    solution.leave(seats[seatsP++]); break;
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Actual list = {}", actual);
            LOGGER.debug("Expected list = {}", expected);
        }
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ExamRoom class pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
