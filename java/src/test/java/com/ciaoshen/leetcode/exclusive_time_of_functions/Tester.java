/**
 * Leetcode - exclusive_time_of_functions
 */
package com.ciaoshen.leetcode.exclusive_time_of_functions;

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
// import org.slf4j.impl.StaticLoggerBinder;
// import org.slf4j.LoggerFactory;
// /** log4j */
// import org.apache.log4j.PropertyConfigurator;
// import org.apache.log4j.Logger;
// /** commons-lang3 */
// import org.apache.commons.lang3.StringUtils;

@RunWith(Parameterized.class)
public class Tester {

    /**=========================== 【static for different test cases】 ============================== */

    /** Solution to test. */
    private static Solution solution;
    // private static Logger logger;

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        /* uncomment to switch solutions */
        solution = new Solution1();
        // solution = new Solution2();

        // /* init logger */
        // String log4jConfPath = "src/main/resources/log4j.properties"; // log4j.properties is under "src/main/resources"
        // PropertyConfigurator.configure(log4jConfPath);
        // logger = Logger.getLogger(Tester.class);
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        return Arrays.asList(new Object[][]{
            {
                new Integer(2), 
                new ArrayList<String>(Arrays.asList(new String[]{"0:start:0", "1:start:2", "1:end:5", "0:end:6"})),
                new int[]{3,4}
            },     
            {
                new Integer(1),
                new ArrayList<String>(Arrays.asList(new String[]{"0:start:0","0:start:1","0:start:2","0:end:3","0:end:4","0:end:5"})),
                new int[]{6} 
            },
            {
                new Integer(3), 
                new ArrayList<String>(Arrays.asList(new String[]{"0:start:0","0:end:0","1:start:1","1:end:1","2:start:2","2:end:2","2:start:3","2:end:3"})),
                new int[]{1,1,2}
            }     
        });
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}



    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private int n;                       
    private List<String> logs;                       
    private int[] expected;        

    /** This constructor must be provided to run parameterized test. */
    public Tester(Integer n, List<String> logs, int[] expected) {
        this.n = n;
        this.logs = logs;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int[] result = solution.exclusiveTime(n, logs);
        System.out.println("Result = " + Arrays.toString(result));
        System.out.println("Expected = " + Arrays.toString(expected));
        assertThat(Arrays.equals(result,expected), is(true));
        System.out.println("exclusiveTime() pass unit test!\n");
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
