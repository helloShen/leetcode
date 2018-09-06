/**
 * ProblemBuilder单元测试

 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;
/** JUnit */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/** java.io */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProblemBuilderTest {

    public ProblemBuilderTest() {
        builder = new ProblemBuilder();
    }
    @Test
    public void testReadFile() {
        String content = builder.readFile(F_TEST);
        assertEquals(content, "Hello Ronald!");
        System.out.println("ProblemBuilder#readFile() method pass JUnit test!");
    }

    /**==================== 【 private 】 =========================*/
    private final String ROOT = "/Users/Wei/github/leetcode/helper";
    private final String RES_DIR = ROOT + "/src/test/resources";
    private final String F_TEST = RES_DIR + "/reader_test.txt";
    private ProblemBuilder builder;
}
