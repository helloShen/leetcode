/**
 * Leetcode - license_key_formatting
 */
package com.ciaoshen.leetcode.license_key_formatting;

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
/** commons-lang3 */
import org.apache.commons.lang3.StringUtils;

@RunWith(Parameterized.class)
public class Tester {

    /**=========================== 【static for different testcases】 ============================== */

    /** Solution to test. */
    private static Solution solution;

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        /* uncomment to switch solutions */
        // solution = new Solution1();
        // solution = new Solution2();
        // solution = new Solution3();
        solution = new Solution4();
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {"5F3Z-2e-9-w", 4, "5F3Z-2E9W"},
            {"2-5g-3-J", 2, "2-5G-3J"},
            {"---", 3, ""}
        });
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}


    
    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private String input;
    private int K;
    private String expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String input, int K, String expected) {
        this.input = input;
        this.K = K;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void licenseKeyFormattingTest() {
        String output = solution.licenseKeyFormatting(input, K);
        assertThat(output, is(equalTo(expected)));
        System.out.println("licenseKeyFormatting() method pass unit test!");
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
