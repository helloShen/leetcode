/**
 * Read template from "~/github/leetcode/java/template/problem.txt"
 * To generate template of a class under "~/github/leetcode/java/src/com/ciaoshen/leetcode/"
 */
package com.ciaoshen.leetcode.template;
import java.util.*;
import java.io.*;

class ProblemGenerator {
    /**
     * @param args [classname and method]
     * args[0]: classname
     * args[1]: method
     */
    private static final String CLASS_FLAG = "_CLASS_";
    private static final String METHOD_FLAG = "_METHOD_";
    public static void main(String[] args) {
        if (args.length < 4) { // defense
            System.out.println("Parameters not enough!"); return;
        }
        String srcpath = args[0]; // template source path
        String despath = args[1]; // new class path
        String classname = args[2]; // calss name
        String method = args[3]; // method signature

        String content = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(srcpath)));
            StringBuilder sb = new StringBuilder();
            try {
                while (true) {
                    String line = br.readLine();
                    if (line == null) { break; }
                    line = line.replaceAll(CLASS_FLAG,classname);
                    line = line.replaceAll(METHOD_FLAG,method);
                    sb.append(line+"\n");
                }
                content = sb.toString();
            } finally {
                br.close();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Check file path: " + fnfe);
        } catch (IOException ioe) {
            System.out.println("Error when reading file " + ioe);
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(despath)));
            try {
                bw.write(content,0,content.length());
            } finally {
                bw.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error when writing file " + ioe);
        }
    }
}
