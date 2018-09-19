/**
 * Leetcode - evaluate_division
 */
package com.ciaoshen.leetcode.evaluate_division;

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
        // solution = new Solution1();
        // solution = new Solution2();
        solution = new Solution3();

        /* init logger */
        String log4jConfPath = "src/main/resources/log4j.properties"; // log4j.properties is under "src/main/resources"
        PropertyConfigurator.configure(log4jConfPath);
        logger = Logger.getLogger(Tester.class);
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {
                new String[][]{{"a", "b"}, {"b", "c"}},
                new double[]{2.0, 3.0},
                new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}},
                new double[]{6.0, 0.5, -1.0, 1.0, -1.0}
            },
            {
                new String[][]{{"a","b"},{"e","f"},{"b","e"}},
                new double[]{3.4,1.4,2.3},
                new String[][]{{"b","a"},{"a","f"},{"f","f"},{"e","e"},{"c","c"},{"a","c"},{"f","e"}},
                new double[]{0.29411764705882354, 10.948, 1.0, 1.0, -1.0, -1.0, 0.7142857142857142}
            }
            // {}      // test case 3 (parameters in array)
        });
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}



    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private String[][] equations;
    private double[] values;
    private String[][] queries;
    private double[] expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String[][] equations, double[] values, String[][] queries, double[] expected) {
        this.equations = equations;
        this.values = values;
        this.queries = queries;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        double[] result = solution.calcEquation(equations, values, queries);
        double[] anotherExpected = new double[]{0.29411764705882354, 10.947999999999999, 1.0, 1.0, -1.0, -1.0, 0.7142857142857143};
        assertThat(Arrays.equals(result, expected) || Arrays.equals(result, anotherExpected), is(true));
        System.out.println("calcEquation() method pass unit test!");
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
