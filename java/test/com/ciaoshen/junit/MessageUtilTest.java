/**
 * MessageUtil类的测试类
 */
package com.ciaoshen.junit;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MessageUtilTest {
    private String message = "Hello Ronald";
    MessageUtil mu = new MessageUtil(message);

    @Test
    public void testPrintMessage() {
        assertEquals(message, mu.printMessage());
    }
    public static void main(String[] args) {
        MessageUtilTest test = new MessageUtilTest();
        test.testPrintMessage();
    }
}
