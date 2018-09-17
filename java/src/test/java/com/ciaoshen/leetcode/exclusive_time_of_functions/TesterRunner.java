/**
 * Leetcode - exclusive_time_of_functions
 */
package com.ciaoshen.leetcode.exclusive_time_of_functions;

/** basic util */
import java.util.*;
import com.ciaoshen.leetcode.util.*;
/** JUnit */
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TesterRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Tester.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure);
        }
        System.out.println(result.wasSuccessful());
    }

}
