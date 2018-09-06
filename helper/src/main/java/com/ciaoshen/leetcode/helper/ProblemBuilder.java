/**
 * Leetcode默认项目生成器
 *
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;


public class ProblemBuilder {

    /** 拿到Writer */
    private BufferedWriter getWriter(String tgtPath) {
        return new BufferedWriter(new FileWriter(new File(tgtPath)));
    }
    /** 拿到Reader */
    private BufferedReader getReader(String srcPath) {
        return new BufferedReader(new FileReader(new File(srcPath)));
    }
    /** 读取整个文件 */
    public static String readFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader r = getReader(path);
            String line = "";
            try {
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
            } finally {
                r.close();
            }
        } catch (NullPointerException npe) { // new File() get null input path
            throw new RuntimeException("File path is null.");
        } catch (FileNotFoundException fnfe) { // new FileReader() file not exist
            throw new RuntimeException("File not found: <" + path + ">.");
        } catch (IOException ioe) { // r.readLine()
            throw new RuntimeException("IOException while reading file: <" + path + ">.");
        }
        return sb.toString();
    }

}
