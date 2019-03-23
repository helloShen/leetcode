/**
 * Leetcode - expressive_words
 */
package com.ciaoshen.leetcode.expressive_words;

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
            // {"heeellooo", new String[]{"hello", "hi", "helo"}, 1},
            // {"abcd", new String[]{"abc"}, 0},
            // {"lee", new String[]{"le"}, 0},
            {"ggkyyyyffffbbhddddrxxsiixccqqqqkmmmiiiiiivvvyyuuujccuuuhhhhwssssnnttoyuuuussggttttfeeeebbbbeedddddqq",
             new String[]{"ggkyyfbbhdrxxsiixccqkmmiiivvvyyujccuuuhhwsnnttoyuuussggtttfeeebbbeedddqq","ggkyyfffbbhddrxxsiixccqqkmmmiiiivvvyyuujccuuuhhhwsnnttoyuuussggtttfebeedddddqq","ggkyyyyffbbhdrxxsiixccqkmmiiiivyyujccuhhwsssnnttoyuuussggtfebeeddddqq","ggkyyfffbbhdddrxxsiixccqkmmmiiiiivyyujccuuhhwsssnnttoyuuussggtfebbeeddddqq","ggkyyyyfffbbhdddrxxsiixccqkmmmiiivvvyyuujccuhhwssnnttoyuuussggtfeeebbbeedddddqq","ggkyyyyfffbbhddrxxsiixccqqkmiiiiivyyuuujccuuuhwsnnttoyuussggtfeebbbeedddddqq","ggkyyffbbhdddrxxsiixccqqkmiiiiivvyyuujccuhwsnnttoyussggtttfeeebbbeedddqq","ggkyyyfbbhddrxxsiixccqqqkmiiivvvyyuuujccuhhwsnnttoyuussggttfebeeddddqq","ggkyyyfbbhdrxxsiixccqqqkmmiiiivvyyujccuuhwsnnttoyussggtfeebbeedddqq","ggkyyyfbbhdddrxxsiixccqkmmmiiiivyyuujccuhhhwsssnnttoyuussggttfeeebeedddqq","ggkyyyfbbhdrxxsiixccqkmmiiiiivyyujccuhhhwssnnttoyussggtttfeebeedddqq","ggkyyyfffbbhddrxxsiixccqqqkmmmiiivvyyuuujccuuhhhwssnnttoyuussggttfeebeedddddqq","ggkyyfffbbhdrxxsiixccqqkmmiiiiivvyyuuujccuuuhhwsnnttoyuussggttfeebbeedddddqq","ggkyyfffbbhdddrxxsiixccqkmiiiivyyuuujccuuhwssnnttoyuussggtfebeedddddqq","ggkyyyyfffbbhddrxxsiixccqqkmmiiivyyuujccuuuhhwssnnttoyussggtfebbbeedddddqq","ggkyyyyffbbhdrxxsiixccqkmmiiiivyyujccuhwsssnnttoyussggtttfebeeddddqq","ggkyyyfbbhddrxxsiixccqqkmiiiiivvyyuuujccuhhhwsssnnttoyuuussggttfeeebbbeedddqq","ggkyyyyffbbhdddrxxsiixccqkmmmiiiivvvyyuuujccuuhhhwssnnttoyussggtttfeeebbbeeddddqq","ggkyyyfbbhdddrxxsiixccqqqkmiiivvvyyuujccuuhhwsnnttoyuuussggtfeebbbeedddqq","ggkyyyffbbhdddrxxsiixccqqqkmiiiivvyyuuujccuuhwssnnttoyuussggttfeebbbeedddqq","ggkyyyyfbbhdddrxxsiixccqkmmmiiiiivvvyyujccuuhhwsnnttoyuuussggttfebbbeedddddqq","ggkyyyfbbhdddrxxsiixccqqqkmmiiiivvyyuujccuuhhwssnnttoyuuussggttfebeeddddqq","ggkyyyyfbbhddrxxsiixccqkmmiiivvvyyuujccuuhhhwsnnttoyussggtfeeebbbeedddqq","ggkyyyfffbbhdrxxsiixccqqkmiiiiivvyyujccuuhwsnnttoyussggtttfeebbeedddddqq","ggkyyyyfffbbhddrxxsiixccqqqkmiiiivyyuuujccuuuhhwsssnnttoyuuussggttfebbeedddqq","ggkyyffbbhddrxxsiixccqkmiiivvyyujccuuhwssnnttoyuuussggtttfeebbeedddddqq","ggkyyyfffbbhdddrxxsiixccqqkmmmiiiiivvyyuuujccuuuhhwssnnttoyussggtttfeeebbeeddddqq","ggkyyyyfbbhddrxxsiixccqkmmmiiivvvyyujccuuhhhwssnnttoyuuussggtfeeebbeedddddqq","ggkyyyyfffbbhdddrxxsiixccqkmmmiiiivyyuuujccuhhhwsssnnttoyuussggtttfeeebbeedddddqq","ggkyyyyfbbhdrxxsiixccqqkmmiiiiivyyuujccuuuhhwsnnttoyuussggttfebbeedddqq","ggkyyyfbbhdrxxsiixccqkmiiiivvyyujccuhhhwsnnttoyussggttfeeebbeedddddqq","ggkyyyfffbbhddrxxsiixccqqqkmiiivyyuujccuuuhhwssnnttoyuuussggtfeebeedddqq","ggkyyffbbhdrxxsiixccqqkmmiiiiivyyuujccuhhhwsnnttoyuuussggtfebeedddddqq","ggkyyyfffbbhddrxxsiixccqkmiiiiivvvyyuujccuuuhhwsnnttoyuuussggttfeeebbeeddddqq","ggkyyyfffbbhdddrxxsiixccqqkmmmiiiivvyyuujccuuhwssnnttoyuussggtfebeedddqq","ggkyyfbbhdddrxxsiixccqqkmiiiiivyyujccuuuhhwsssnnttoyuuussggtttfeeebeeddddqq","ggkyyyyffbbhdddrxxsiixccqqkmmiiiiivvyyuuujccuuhhhwssnnttoyuussggtfeebbbeedddddqq","ggkyyffbbhdrxxsiixccqkmmiiiivyyuujccuuhhhwssnnttoyuussggtfeebeeddddqq","ggkyyyffbbhddrxxsiixccqkmmiiiiivvyyujccuuuhhwssnnttoyuussggtttfeeebbbeeddddqq","ggkyyyfffbbhdrxxsiixccqqqkmiiiivvvyyuujccuhhhwsssnnttoyuuussggtttfebbeeddddqq","ggkyyffbbhdrxxsiixccqqkmiiiiivyyuuujccuuuhwsnnttoyuuussggttfeeebbeeddddqq","ggkyyyfbbhdrxxsiixccqqkmiiivyyujccuuuhhhwsnnttoyussggtfebbbeeddddqq","ggkyyfffbbhddrxxsiixccqqkmmiiivyyuujccuuuhhwsnnttoyuussggtttfeeebbeedddddqq","ggkyyyyfbbhdrxxsiixccqqkmmmiiiiivvvyyujccuuuhhhwssnnttoyuussggtttfeebbeeddddqq","ggkyyyffbbhdrxxsiixccqqqkmiiiivvvyyuuujccuuhhhwsssnnttoyussggtttfeebeeddddqq","ggkyyyyfbbhddrxxsiixccqkmiiiiivvvyyuuujccuuuhhwssnnttoyuussggttfeeebeeddddqq","ggkyyyyffbbhdrxxsiixccqqkmmiiivvvyyuujccuuhhhwsnnttoyuussggttfeeebbbeedddqq","ggkyyfffbbhddrxxsiixccqkmiiiiivvyyuuujccuuuhwsssnnttoyuuussggtttfebeedddddqq","ggkyyyfbbhdrxxsiixccqkmmmiiiiivvyyuuujccuuuhwssnnttoyuussggttfeeebbeedddddqq","ggkyyyffbbhdrxxsiixccqkmmiiiivyyujccuuuhhwssnnttoyussggtttfebbbeeddddqq","ggkyyyffbbhdrxxsiixccqqkmmmiiiivvvyyuujccuhwssnnttoyussggtfebeeddddqq","ggkyyyffbbhdddrxxsiixccqqkmiiivvyyuujccuuhhhwssnnttoyussggtfeebbeeddddqq","ggkyyyffbbhdrxxsiixccqqqkmmiiiivvvyyujccuhhhwsnnttoyuuussggttfebbbeedddqq","ggkyyyfbbhddrxxsiixccqqkmiiiiivvyyuujccuuhhwsssnnttoyuuussggtfebbbeeddddqq","ggkyyffbbhddrxxsiixccqqkmmiiiiivvyyujccuhhhwsssnnttoyuuussggtttfeebbbeedddddqq","ggkyyyfffbbhdrxxsiixccqqkmiiivvyyuujccuhhwsssnnttoyuussggttfeeebbeedddqq","ggkyyyfffbbhdrxxsiixccqkmmmiiiivvyyuujccuuuhwssnnttoyussggtfebbbeeddddqq","ggkyyyyffbbhdrxxsiixccqqkmiiiivvvyyuuujccuuhwsnnttoyuussggttfebbbeedddddqq","ggkyyyyffbbhddrxxsiixccqqkmmmiiiiivvyyuuujccuhwsssnnttoyuussggtfeeebbeeddddqq","ggkyyyyfbbhdddrxxsiixccqqkmmiiivyyujccuuuhhwsssnnttoyussggtfebbeedddqq","ggkyyyffbbhdrxxsiixccqkmiiiiivvyyuujccuhhwssnnttoyussggtfebeedddqq","ggkyyyffbbhdrxxsiixccqkmmiiivyyujccuuhhhwsssnnttoyuuussggtttfeeebbbeeddddqq","ggkyyyyfffbbhdddrxxsiixccqqqkmmmiiiiivvyyujccuuhhhwssnnttoyuuussggtttfebbbeedddqq","ggkyyyfbbhdddrxxsiixccqqkmmiiivvvyyujccuuuhhhwssnnttoyuussggtttfebbbeeddddqq","ggkyyyfbbhdrxxsiixccqqqkmmmiiivvyyuuujccuuhhwsssnnttoyuuussggtttfebeedddqq","ggkyyyyfbbhddrxxsiixccqkmmiiiiivvvyyuuujccuuhhwssnnttoyuuussggtfeeebeedddddqq","ggkyyyyfffbbhdddrxxsiixccqqkmiiiivvyyujccuuhhwsssnnttoyussggtfebbbeedddqq","ggkyyyffbbhdrxxsiixccqqkmmmiiivvyyuuujccuhhhwsssnnttoyussggtttfebbbeeddddqq","ggkyyyfffbbhdddrxxsiixccqqkmmmiiiiivvvyyuuujccuuuhhwsnnttoyuussggttfeebeedddddqq","ggkyyyyfffbbhdddrxxsiixccqqqkmmmiiiivvyyuuujccuuuhhhwsssnnttoyussggtfeebbbeedddddqq","ggkyyyfbbhdddrxxsiixccqkmiiiiivyyuuujccuhhhwsnnttoyuussggtttfeebeedddqq","ggkyyyfbbhdrxxsiixccqqqkmmmiiiiivyyujccuhhwsnnttoyuussggttfeebbeedddqq","ggkyyyyffbbhdrxxsiixccqqqkmmiiivvyyujccuhhhwssnnttoyussggttfeeebbbeedddddqq","ggkyyyfffbbhdrxxsiixccqqqkmiiiiivyyujccuhhwsssnnttoyuuussggtfeebbbeeddddqq","ggkyyyffbbhdrxxsiixccqqkmiiiivvyyuuujccuhhhwssnnttoyussggttfeeebbbeedddqq","ggkyyyyffbbhdrxxsiixccqkmiiiiivvyyuujccuhhwssnnttoyuussggtfeeebeedddqq","ggkyyyfbbhdddrxxsiixccqkmmmiiivvyyujccuuhhhwsssnnttoyuussggtttfeebeedddddqq","ggkyyyyfffbbhdddrxxsiixccqqqkmmmiiiiivvvyyuuujccuuhwssnnttoyuuussggtfeeebbeedddddqq","ggkyyfbbhdrxxsiixccqkmiiiivvyyujccuuuhhhwssnnttoyuussggttfebbeedddqq","ggkyyyfbbhddrxxsiixccqqqkmmiiiivyyuujccuuhhwsnnttoyuussggttfebbeedddddqq","ggkyyyyfbbhdddrxxsiixccqkmmiiivyyujccuhwsssnnttoyussggttfeebbbeedddqq","ggkyyyyfbbhdrxxsiixccqkmiiiiivvvyyuuujccuuuhhwsnnttoyuuussggtfeebeeddddqq","ggkyyffbbhddrxxsiixccqqkmmiiiivyyuujccuuhhwsssnnttoyuussggtttfeeebbeedddqq","ggkyyyfffbbhddrxxsiixccqqqkmmiiivvvyyuujccuhhwsnnttoyuussggttfebbbeeddddqq","ggkyyfffbbhdrxxsiixccqkmmmiiivvvyyuuujccuuuhwsssnnttoyussggttfeeebeedddddqq","ggkyyyyffbbhdrxxsiixccqqqkmmiiiiivvyyuuujccuhhwsnnttoyuuussggtttfeeebbeedddqq","ggkyyyyfffbbhdrxxsiixccqkmmiiivvvyyuujccuhhwsssnnttoyuuussggttfeebbeedddddqq","ggkyyyyfffbbhdddrxxsiixccqqqkmiiivvyyuuujccuuhhhwssnnttoyuuussggttfebbbeedddddqq","ggkyyffbbhdrxxsiixccqqqkmmmiiiiivvvyyujccuuuhwsssnnttoyuussggtttfeeebbbeeddddqq","ggkyyyyfffbbhdddrxxsiixccqkmmmiiiiivyyujccuuuhwsnnttoyuuussggtttfeeebeedddddqq","ggkyyfffbbhdrxxsiixccqkmmmiiiiivvyyuujccuuuhwsssnnttoyussggtfebbeedddddqq","ggkyyyyfbbhddrxxsiixccqqqkmiiivyyuujccuuhhhwssnnttoyussggttfeeebbbeedddddqq","ggkyyffbbhddrxxsiixccqkmmiiivvvyyuuujccuuhhwsssnnttoyuuussggtfeeebbeedddddqq","ggkyyffbbhdddrxxsiixccqkmiiiivvvyyuujccuuhhhwsssnnttoyuuussggttfebbeedddqq","ggkyyyyffbbhdrxxsiixccqkmmmiiiiivyyuujccuuuhwsnnttoyuuussggtttfebeeddddqq","ggkyyffbbhddrxxsiixccqkmmmiiiivyyuuujccuuhhhwsssnnttoyuuussggtfeeebeedddqq","ggkyyyyfbbhdrxxsiixccqkmmmiiivyyuujccuhwsnnttoyuuussggtttfeeebbeeddddqq","ggkyyyyfffbbhdddrxxsiixccqqkmiiivvyyuujccuhhhwsnnttoyuuussggttfeeebbeedddqq","ggkyyyyfffbbhdddrxxsiixccqqkmmmiiivvyyuuujccuuuhwssnnttoyuussggtttfeebeedddqq","ggkyyyyfffbbhdddrxxsiixccqkmiiiiivyyuujccuuuhhwssnnttoyussggtttfeeebeeddddqq"}, 100}
        });
    }

    /**=========================== for each test case ============================== */

    /**
     * Parameters for each test (initialized in testcases() method)
     * You can change the type of parameters
     */
    private String S;
    private String[] words;
    private int expected;

    /** This constructor must be provided to run parameterized test. */
    public Tester(String S, String[] words, int expected) {
        this.S = S;
        this.words = words;
        this.expected = expected;
    }

    /** Execute before each test method in this class is executed. */
    @Before
    public void setUp() throws Exception {}

    /** Executed as a test case. */
    @Test
    public void test() {
        int actual = solution.expressiveWords(S, words);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("actual = {}, expected = {}", actual, expected);
        }
        assertThat(actual == expected, is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method expressiveWords() pass unit test!");
        }
    }

    /** Execute after each test method in this class is executed. */
    @After
    public void tearDown() throws Exception {}

}
