/**
 * Leetcode - flood_fill
 */
package com.ciaoshen.leetcode.flood_fill;

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
            {new int[][]{{1,1,1},
                         {1,1,0},
                         {1,0,1}}, 1, 1, 2,
             new int[][]{{2,2,2},
                         {2,2,0},
                         {2,0,1}}},
            {new int[][]{{0,0,0},
                         {0,1,1}}, 1, 1, 1,
             new int[][]{{0,0,0},
                         {0,1,1}}}
            // {}      // test case 3 (init parameters below: {para1, para2, expected})
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[][] image;
    private int sr;
    private int sc;
    private int newColor;
    private int[][] expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[][] image, int sr, int sc, int newColor, int[][] expected) {
        // initialize test parameters
        this.image = image;
        this.sr = sr;
        this.sc = sc;
        this.newColor = newColor;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int[][] actual = solution.floodFill(image, sr, sc, newColor);
        assertThat(matrixEqual(actual, expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("floodFill() pass unit test!");
        }
    }

    private boolean matrixEqual(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) return false;
        for (int i = 0; i < matrix1.length; i++) {
            if (!Arrays.equals(matrix1[i], matrix2[i])) return false;
        }
        return true;
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
