/**
 * Leetcode - most_common_word
 */
package com.ciaoshen.leetcode.most_common_word;

/** basic util */
import java.util.*;
import com.ciaoshen.leetcode.util.*;
/** JUnit */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/** slf4j */
import org.slf4j.impl.StaticLoggerBinder;
import org.slf4j.LoggerFactory;
/** commons-lang3 */
import org.apache.commons.lang3.StringUtils;

public class Tester {

    public Tester() {
        // solution = new Solution1();
        solution = new Solution2();
    }

    @Test
    public void mostCommonWordTest() {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = new String[]{"hit"};
        String answer = "ball";
        String result = solution.mostCommonWord(paragraph, banned);
        System.out.println("Result = " + result);
        assertEquals(answer, result);
        System.out.println("mostCommonWord() method pass unit test!");
    }

    /**==================== 【 private 】 =========================*/
    private Solution solution;

}
