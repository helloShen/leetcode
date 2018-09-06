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
import java.io.FileNotFoundException;
import java.io.IOException;


public class ProblemBuilder {

    /** 读取整个文件 */
    String readFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader r = new BufferedReader(new FileReader(new File(path)));
            String line = "";
            try {
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
            } finally {
                r.close();
            }
        } catch (FileNotFoundException fnfe) { // new FileReader()
            throw new RuntimeException("File not found: <" + path + ">.");
        } catch (IOException ioe) { // r.readLine(), r.close()
            throw new RuntimeException("IOException while reading file: <" + path + ">.");
        }
        return sb.toString();
    }

}
