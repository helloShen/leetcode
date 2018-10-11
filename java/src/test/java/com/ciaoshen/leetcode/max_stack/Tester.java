/**
 * Leetcode - max_stack
 */
package com.ciaoshen.leetcode.max_stack;

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
    private static Solution stack;
    // use this Object to print the log (call from slf4j facade)
    private static final Logger LOGGER = LoggerFactory.getLogger(TesterRunner.class);

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        /* uncomment to switch solutions */
        // stack = new Solution1();
        // stack = new Solution2();
        stack = new Solution3();
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {new int[]{5, 1, 5}, new String[]{"push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"},
             new ArrayList<Integer>(Arrays.asList(new Integer[]{5, 5, 1, 5, 1, 5}))}
            // {},     // test case 2 (init parameters below: {para1, para2, expected})
            // {}      // test case 3 (init parameters below: {para1, para2, expected})
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private int[] nums;
    private String[] methods;
    private List<Integer> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] nums, String[] methods, List<Integer> expected) {
        // initialize test parameters
        this.nums = nums;
        this.methods = methods;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<Integer> actual = run(nums, methods);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Actual = {}", actual);
            LOGGER.debug("Expected = {}", expected);
        }
        assertThat(actual.equals(expected), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MaxStack class pass unit test!");
        }
    }

    private List<Integer> run(int[] nums, String[] methods) {
        int np = 0, mp = 0;
        List<Integer> output = new ArrayList<>();
        for (String m : methods) {
            switch(m) {
                case "push":
                    stack.push(nums[np++]); break;
                case "pop":
                    output.add(stack.pop()); break;
                case "top":
                    output.add(stack.top()); break;
                case "peekMax":
                    output.add(stack.peekMax()); break;
                case "popMax":
                    output.add(stack.popMax()); break;
            }
        }
        return output;
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
