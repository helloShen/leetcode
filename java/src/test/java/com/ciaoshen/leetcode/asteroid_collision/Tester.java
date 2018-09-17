/**
 * Leetcode - asteroid_collision
 */
package com.ciaoshen.leetcode.asteroid_collision;

/** basic util */
import java.util.*;
import com.ciaoshen.leetcode.util.*;
/** JUnit & hamcrest */
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
/** slf4j */
import org.slf4j.impl.StaticLoggerBinder;
import org.slf4j.LoggerFactory;
/** log4j */
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
/** commons-lang3 */
import org.apache.commons.lang3.StringUtils;

@RunWith(Parameterized.class)
public class Tester {

    /**=========================== 【static for different test cases】 ============================== */

    /** Solution to test. */
    private static Solution solution;
    private static Logger logger;

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        /* uncomment to switch solutions */
        solution = new Solution1();
        // solution = new Solution2();

        /* init logger */
        String log4jConfPath = "src/main/resources/log4j.properties"; // log4j.properties is under "src/main/resources"
        PropertyConfigurator.configure(log4jConfPath);
        logger = Logger.getLogger(Tester.class);
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {new int[]{5, 10, -5}, new int[]{5, 10}},
            {new int[]{8, -8}, new int[0]},
            {new int[]{10, 2, -5}, new int[]{10}},
            {new int[]{-2, -1, 1, 2}, new int[]{-2, -1, 1, 2}},
            {new int[]{-2,1,1,-1}, new int[]{-2, 1}}
        });
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}



    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private int[] asteroids;        // input steroids array
    private int[] expected;        // expected output

    /** This constructor must be provided to run parameterized test. */
    public Tester(int[] asteroids, int[] expected) {
        this.asteroids = asteroids;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int[] output = solution.asteroidCollision(asteroids);
        assertThat(output, is(equalTo(expected)));
        System.out.println("asteroidCollision() method pass unit test!");
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
