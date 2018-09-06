/**
 * ProblemBuilder单元测试

 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;

/** JUnit */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

class ProblemBuilderTest {

    @Before
    public void init() {
        builder = new ProblemBuilder();
    }
    @Test
    public void testGetWriter() {
        BufferedWriter w = builder.getWriter(R_TEST);
        assertNotNull(w);
    }
    @Test
    public void testGetReader() {
        BufferedReader r = builder.getReader(R_TEST);
        assertNotNull(r);
    }
    @Test
    public void testReadFile() {
        BufferedReader r = builder.getReader(R_TEST);
        String content = builder.readFile(R_TEST);
        assertEquals(content, "Hello Ronald!");
    }

    /**==================== 【 private 】 =========================*/
    private final String ROOT = "/Users/Wei/github/leetcode/helper"
    private final String RES_DIR = ROOT + "/src/test/resources";
    private final String R_TEST = RES_DIR + "/reader_test.txt";
    private ProblemBuilder builder;
}
