/**
 * Leetcode - repeated_string_match
 */
package com.ciaoshen.leetcode.repeated_string_match;
import java.util.*;
import com.ciaoshen.leetcode.util.*;
// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class Solution1 implements Solution {

    private static final Logger LOGGER = LoggerFactory.getLogger(Solution1.class);

    public int repeatedStringMatch(String A, String B) {
        int la = A.length(), lb = B.length(); // length A & length B
        if (lb == 0) return 1;
        if (la == 0) return 0;
        int p = 0;
        char hb = B.charAt(0); // head B
        int from = 0;
        while (p < la) {
            p = A.indexOf(hb, from);
            if (p == -1) break;
            int pb = 0; // pointer at head of B
            int repeat = 1;
            for (int pa = p; pb < lb; pa = (pa + 1) % la, pb++) {
                if (A.charAt(pa) != B.charAt(pb)) break;
                // if (LOGGER.isDebugEnabled()) {
                //     LOGGER.debug("pb = {} at \"{}\", pa = {} at \"{}\".", pb, B.charAt(pb), pa, A.charAt(pa));
                // }
                if ((pa + 1 == la) && (pb + 1 != lb)) {
                    repeat++;
                    // if (LOGGER.isDebugEnabled()) {
                    //     LOGGER.debug("pb = {} at \"{}\", pa = at \"{}\" reach the end of A.", pb, B.charAt(pb), pa, A.charAt(pa));
                    // }
                }
            }
            if (pb == lb) return repeat;
            from = p + 1;
        }
        return -1;
    }

}
