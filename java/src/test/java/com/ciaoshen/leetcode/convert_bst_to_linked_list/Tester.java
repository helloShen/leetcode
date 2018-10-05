/**
 * Leetcode - convert_bst_to_linked_list
 */
package com.ciaoshen.leetcode.convert_bst_to_linked_list;

// basic util
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;
// JUnit & hamcrest
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
// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Parameterized.class)
public class Tester {

    /**=========================== 【static for different test cases】 ============================== */

    // Solution to test
    private static Solution solution;
    // use this Object to print the log (call from slf4j facade)
    private static final Logger LOGGER = LoggerFactory.getLogger(TesterRunner.class);

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        /* uncomment to switch solutions */
        // solution = new Solution1();
        solution = new Solution2();
    }

    /** Initialize test cases */
    @Parameters
    public static Collection<Object[]> testcases() {
        Node one = new Node(1, null, null);
        Node three = new Node(3, null, null);
        Node five = new Node(5, null, null);
        Node two = new Node(2, one, three);
        Node four = new Node(4, two, five);

        return Arrays.asList(new Object[][]{
            {four}
        });
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}



    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private Node root;

    /** This constructor must be provided to run parameterized test. */
    public Tester(Node root) {
        this.root = root;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        Node actual = solution.treeToDoublyList(root);
        assertThat(actual.val, is(equalTo(1)));
        assertThat(actual.left.val, is(equalTo(5)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("treeToDoublyList() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
