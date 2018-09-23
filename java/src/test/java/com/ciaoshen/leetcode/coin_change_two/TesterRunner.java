/**
 * Leetcode - coin_change_two
 */
package com.ciaoshen.leetcode.coin_change_two;

// basic util
import java.util.*;
import com.ciaoshen.leetcode.util.*;
// leetcode-helper library
import com.ciaoshen.leetcode.helper.PropertyScanner;
// junit 
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
// log4j
import org.apache.log4j.PropertyConfigurator;
// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TesterRunner {

    // use this Object to print the log (call from slf4j facade)
    private static final Logger LOGGER = LoggerFactory.getLogger(TesterRunner.class);
    // both log4j and slf4j are included in leetcode-helper.jar
    private static final String LOG4J = "log4j.properties";

    public static void main(String[] args) {
        // use log4j as Logger implementation
        Properties log4jProps = PropertyScanner.load(LOG4J); // PropertyScanner load "log4j.properties" for us
        PropertyConfigurator.configure(log4jProps);

        // run Tester.class
        Result result = JUnitCore.runClasses(Tester.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Pass all JUnit test? {}", result.wasSuccessful());
        }
    }

}
