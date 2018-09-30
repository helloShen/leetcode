/**
 * Leetcode - serialize_and_deserialize_bst
 */
package com.ciaoshen.leetcode.serialize_and_deserialize_bst;

// basic util
import java.util.*;
// import com.ciaoshen.leetcode.util.*;
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
        return Arrays.asList(new Object[][]{
            {random(10)},
            {null}
            // {}      // test case 3 (parameters in array)
        });
    }

    private static final Random R = new Random();
    private static final int MAX = 100;
    private static TreeNode random(int size) {
        if (size < 1) return null;
        TreeNode root = new TreeNode(R.nextInt(MAX));
        for (int i = 1; i < size; i++) {
            Solution.insert(root, R.nextInt(MAX));
        }
        return root;
    }

    /** Execute once after all of the test methods are executed in this class. */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}



    /**=========================== 【for each test case】 ============================== */

    /** Parameters for each test */
    private TreeNode root;

    /** This constructor must be provided to run parameterized test. */
    public Tester(TreeNode root) {
        this.root = root;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        String serial = solution.serialize(root);
        TreeNode actual = solution.deserialize(serial);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("Serialized String = {}", serial);
            LOGGER.info("Deserialized Tree = {}", actual);
        }
        assertThat(isEqual(root, actual), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("serialize() and deserialize() pass unit test!");
        }
    }
    private boolean isEqual(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if ((a == null && b != null) || (a != null && b == null)) return false;
        if (a.val != b.val) return false;
        boolean left = isEqual(a.left, b.left);
        boolean right = isEqual(a.right, b.right);
        return left && right;
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
