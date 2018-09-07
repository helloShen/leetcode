/**
 * ProblemBuilder单元测试

 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;
/** JUnit */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/** slf4j */
import org.slf4j.impl.StaticLoggerBinder;
import org.slf4j.LoggerFactory;
/** commons-lang3 */
import org.apache.commons.lang3.StringUtils;
/** java.io */
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProblemBuilderTest {

    public ProblemBuilderTest() {
        builder = new ProblemBuilder(ARGS);
        assertNotNull(builder);
        assertEquals("/Users/Wei/github/leetcode/helper/src/main/java/com/ciaoshen/leetcode/helper/two_sum", builder.getProblemDir());
        System.out.println("ProblemBuilder constructor pass JUnit test!");
        System.out.println("ProblemBuilder#getProblemDir() method pass JUnit test!");
    }
    @Test
    public void testGetWriter() {
        Writer w = builder.getWriter(W_TEST);
        assertNotNull(w);
        System.out.println("ProblemBuilder#getWriter() method pass JUnit test!");
        try {
            w.close();
        } catch (IOException ioe) {
            throw new RuntimeException("ProblemBuilderTest#testGetWriter() can not close the FileWriter!");
        }
    }
    @Test
    public void testReadFile() {
        String content = builder.readFile(R_TEST);
        assertEquals("Hello Ronald!", content);
        System.out.println("ProblemBuilder#readFile() method pass JUnit test!");
    }

    /**==================== 【 private 】 =========================*/
    /** 根目录 */
    private final String ROOT = "/Users/Wei/github/leetcode/helper";
    /** 构造ProblemBuilder的4个必要参数 */
    private final String SRC_DIR = ROOT + "/src/main/java";
    private final String TPL_DIR = ROOT + "/tpl";
    private final String PCK = "com.ciaoshen.leetcode.helper";
    private final String PROB = "two_sum";
    private final String[] ARGS = new String[]{TPL_DIR, SRC_DIR, PCK, PROB};
    /** 测试FileReader的文本文件 */
    private final String RES_DIR = ROOT + "/src/test/resources";
    private final String R_TEST = RES_DIR + "/reader_test.txt";
    private final String W_TEST = RES_DIR + "/writer_test.txt";
    /** 内置ProlbemBuilder */
    private ProblemBuilder builder;
}
