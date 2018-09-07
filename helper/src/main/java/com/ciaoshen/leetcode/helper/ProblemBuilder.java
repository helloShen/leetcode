/**
 * Leetcode默认项目生成器
 *
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;
// java.io
import java.io.File;
import java.io.Writer;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
// velocity
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;


public class ProblemBuilder {

    public ProblemBuilder(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Must have 4 arguments!");
        }
        templateDir = args[0];
        srcDir = args[1];
        packagePath = args[2].replaceAll("\\.","/");
        problemName = args[3];
        problemDir = srcDir + "/" + packagePath + "/" + problemName;
        ve = new VelocityEngine();
        ve.init();
    }
    /**
     * 针对一个
     * @param path [description]
    void writeTemplate(String fileName) {
        Template t = ve.getTemplate(templatePath);
        VelocityContext context = new VelocityContext();
        context.put("package", problemName);
        Writer w = getWriter(targetPath);
        t.merge(context, w);
        w.write(w.toString());
        try {
            w.close();
        } catch (IOException ioe) {
            throw new RuntimeException("ProblemBuilder#writeTemplate() can not close the Writer!");
        }
    }
     */

    /** 获取一个用BufferedWriter装饰的FileWriter */
    Writer getWriter(String path) {
        try {
            return new BufferedWriter(new FileWriter(new File(path)));
        } catch (FileNotFoundException fnfe) { // new File()
            throw new RuntimeException("File not found: <" + path + ">.");
        } catch (IOException ioe) { // new FileWriter()
            throw new RuntimeException("IOException occurred when creating the new FileWriter.");
        }
    }

    /** 读取指定文本文件 */
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

    String getProblemDir() {
        return problemDir;
    }

    private VelocityEngine ve;
    private String templateDir;             // 模板所在根目录
    private String srcDir;                  // 项目源代码根目录
    private String packagePath;             // 像com.ciaoshen.leetcode.helper这样的包名
    private String problemName;             // 问题名字
    // 以[src_dir/package_path/problem_name]格式构成的项目源码目录
    private String problemDir;

    /**
     * Must have 4 arguments:
     *      1. templateDir
     *      2. srcDir
     *      3. package
     *      4. problemName
     *
     * 首先，参数传进来的"package"只是个包名："com.ciaoshen.leetcode.helper"，
     * 需要转换成 "com/ciaoshen/leetcode/helper"样式的路径：packagePath
     *
     * 这些参数被用来构造我们生成的文件的目标路径：
     *
     *      problemDir = [srcDir/packagePath/problemName]
     *
     * 最后在templateDir里有几个模板文件，就在problemDir里填充这几个模板.
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Must have 4 argument!");
        }
    }
}
