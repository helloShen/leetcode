/**
 * Leetcode - all_paths_from_source_to_target
 */
package com.ciaoshen.leetcode.all_paths_from_source_to_target;

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
        // solution = new Solution2();
        solution = new Solution3();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    private static List<List<Integer>> getTestcase(int[][] src) {
        List<List<Integer>> testcase = new LinkedList<>();
        for (int[] arr : src) {
            List<Integer> path = new LinkedList<>();
            for (int n : arr) path.add(n);
            testcase.add(path);
        }
        return testcase;
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {new int[][]{{1,2},{3},{3},{}}, getTestcase(new int[][]{{0,1,3},{0,2,3}})}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[][] graph;
    private List<List<Integer>> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[][] graph, List<List<Integer>> expected) {
        this.graph = graph;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<List<Integer>> actual = solution.allPathsSourceTarget(graph);
        assertThat(actual != null, is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual != null");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual size = {}, expected size = {}", actual.size(), expected.size());
        }
        assertThat(actual.size() == expected.size(), is(true));
        for (int i = 0; i < actual.size(); i++) {
            List<Integer> path = actual.get(i);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("actual = {}", path);
                LOGGER.debug("expected = {}", expected.get(i));
            }
            assertThat(expected.contains(path), is(true));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method allPathsSourceTarget() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
