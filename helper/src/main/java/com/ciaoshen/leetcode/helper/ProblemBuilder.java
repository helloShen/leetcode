/**
 * Generate a project skeleton for a Leetcode Problem
 *
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;
// java.io
import java.io.File;
import java.io.Writer;
import java.io.StringWriter;
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

    /**
     * @param args Must have 4 arguments:
     *      1. templateDir
     *      2. srcDir
     *      3. package
     *      4. problemName
     *
     * Firstly, the "package" argument is just a package name such as：
     *      > package = "com.ciaoshen.leetcode.helper"
     * transform to,
     *      > packagePath = "com/ciaoshen/leetcode/helper"
     *
     * Then construct the absolute path of source code directory of that problem,
     *      > problemDir = [srcDir/packagePath/problemName]
     *
     * Velocity templates are located in templateDir
     */
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
        // tell velocity where to find .vm template files
        ve.setProperty("file.resource.loader.path", templateDir);
        ve.init();
    }

    /**
     * Call writeTemplate() for each template in templateDir
     */
    void writeTemplates() {
        File[] templates = new File(templateDir).listFiles();
        for (File t : templates) {
            System.out.println(t.getName());
            writeTemplate(t);
        }
    }

    private final String JAVA_EXP = "java";
    /**
     * Convert "Solution.vm" to "/Users/Wei/.../Solution.java"
     * @param f velocity template file
     @ @return absolute path of the corresponding java source file
     */
    String buildSourcePath(File f) {
        String fullFileName = f.getName(); // such as: "Solution.vm"
        String fileName = fullFileName.substring(0, fullFileName.indexOf(".")); // such as: "Solution"
        return problemDir + "/" + fileName + "." + JAVA_EXP; // such as: "/Users/Wei/.../Solution.java"
    }


    // void writeTemplate(File tplFile) {
    //     System.out.println("I'm writing " + buildSourcePath(tplFile));
    // }
    /**
     * Call Velociy to fill a .vm template
     * @param tplPath absolute path of a velocity .vm template
     * @param dst absolute path where store the generated .java source file
     */
    void writeTemplate(File tplFile) {
        Template t = ve.getTemplate(tplFile.getName());
        VelocityContext context = new VelocityContext();
        context.put("package", problemName);
        Writer sw = new StringWriter();
        t.merge(context, sw);
        Writer fw = getFileWriter(buildSourcePath(tplFile));
        try {
            fw.write(sw.toString());
            fw.close();
            sw.close();
        } catch (IOException ioe) {
            throw new RuntimeException("ProblemBuilder#writeTemplate() can not close the Writer!");
        }
    }

    /**
     * Get a FileWriter decorated by BufferedWriter
     * @param  path absolute path of that file
     * @return      A FileWriter decorated by BufferedWriter
     */
    Writer getFileWriter(String path) {
        try {
            String directoryPath = path.substring(0, path.lastIndexOf("/"));
            System.out.println("Directory Path = " + directoryPath);
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            return new BufferedWriter(new FileWriter(new File(path)));
        } catch (FileNotFoundException fnfe) { // new File()
            throw new RuntimeException("File not found: <" + path + ">.");
        } catch (IOException ioe) { // new FileWriter()
            throw new RuntimeException("IOException occurred when creating the new FileWriter.");
        }
    }

    /**
     * Read the content of a file
     * @param  path absolute path of that file
     * @return      Content of that file in an entire String
     */
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

    /**
     * Junit test need constructed absolute path
     * @return the constructed absolution path to the directory of all generated .java files
     */
    String getProblemDir() {
        return problemDir;
    }

    private VelocityEngine ve;

    private String templateDir;             // template directory
    private String srcDir;                  // source code directory for all leetcode problems
    private String packagePath;             // package name such as: com.ciaoshen.leetcode.helper
    private String problemName;             // name of this problem
    /*
     * Absolute path of source code for that problem
     *      [src_dir/package_path/problem_name]
     */
    private String problemDir;

    /**
     * Must have 4 arguments:
     *      1. templateDir
     *      2. srcDir
     *      3. package
     *      4. problemName
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Must have 4 argument!");
        }
    }
}
