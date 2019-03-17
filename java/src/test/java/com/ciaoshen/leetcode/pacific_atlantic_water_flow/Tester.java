/**
 * Leetcode - pacific_atlantic_water_flow
 */
package com.ciaoshen.leetcode.pacific_atlantic_water_flow;

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
            {new int[][]{
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
            }, new ArrayList<int[]>(Arrays.asList(new int[][]{
                {0, 4}, {1, 3}, {1, 4}, {2, 2}, {3, 0}, {3, 1}, {4, 0}
            }))}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[][] matrix;
    private List<int[]> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[][] matrix, List<int[]> expected) {
        this.matrix = matrix;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<int[]> actual = solution.pacificAtlantic(matrix);
        if (LOGGER.isDebugEnabled()) {
            StringBuilder sba = new StringBuilder();
            for (int[] pair : actual) sba.append(Arrays.toString(pair) + ",");
            if (sba.length() > 0) sba.delete(sba.length() - 1, sba.length());
            LOGGER.debug("actual = {}", sba.toString());
            StringBuilder sbe = new StringBuilder();
            for (int[] pair : expected) sbe.append(Arrays.toString(pair) + ",");
            if (sbe.length() > 0) sbe.delete(sbe.length() - 1, sbe.length());
            LOGGER.debug("expected = {}", sbe.toString());
        }
        assertThat(actual.size() == expected.size(), is(true));
        for (int[] pairA : actual) {
            boolean containsInAnyOrder = false;
            for (int[] pairE : expected) {
                if (pairA[0] == pairE[0] && pairA[1] == pairE[1]) {
                    containsInAnyOrder = true;
                    break;
                }
            }
            assertThat(containsInAnyOrder == true, is(true));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method pacificAtlantic() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
