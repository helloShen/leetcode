/**
 * Leetcode - top_k_frequent_words
 */
package com.ciaoshen.leetcode.top_k_frequent_words;

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
            {
                new String[]{"i", "love", "leetcode", "i", "love", "coding"},
                2,
                new ArrayList<String>(Arrays.asList(new String[]{"i", "love"}))
            },
            {
                new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"},
                4,
                new ArrayList<String>(Arrays.asList(new String[]{"the", "is", "sunny", "day"}))
            }
        });
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}



    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private String[] words;
    private int k;
    private List<String> expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String[] words, int k, List<String> expected) {
        this.words = words;
        this.k= k;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        List<String> actual = solution.topKFrequent(words, k);
        logger.info("Actual = " + actual);
        logger.info("Expected = " + expected);
        assertThat(actual.equals(expected), is(true));
        logger.info("topKFrequent() pass unit test!");
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
