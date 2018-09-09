/**
 * Leetcode - Algorithm - FindDuplicateFileInSystem
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FindDuplicateFileInSystem implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FindDuplicateFileInSystem() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<List<String>> findDuplicate(String[] paths); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public List<List<String>> findDuplicate(String[] paths) {
            Map<String,List<String>> map = new HashMap<>();
            for (String path : paths) {
                int bar = path.indexOf(" ");
                String dir = path.substring(0,bar);
                String filePart = path.substring(bar+1);
                String prefix = dir + "/";
                // System.out.println("Dir = " + prefix);
                String[] files = filePart.split(" ");
                for (String file : files) {
                    bar = file.indexOf("(");
                    String fileName = file.substring(0,bar);
                    // System.out.println("\tFile Name = " + fileName);
                    String content = file.substring(bar+1,file.length()-1);
                    // System.out.println("\tContent = " + content);
                    if (map.containsKey(content)) {
                        // System.out.println("Find Duplicate file! " + fileName);
                        map.get(content).add(prefix + fileName);
                    } else {
                        map.put(content,new ArrayList<>(Arrays.asList(new String[]{prefix + fileName})));
                    }
                }
            }
            List<List<String>> res = new ArrayList<>();
            for (Map.Entry<String,List<String>> entry : map.entrySet()) {
                List<String> filesWithSameName = entry.getValue();
                if (filesWithSameName.size() > 1) {
                    res.add(filesWithSameName);
                }
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public List<List<String>> findDuplicate(String[] paths) {
            return new ArrayList<>();
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<List<String>> findDuplicate(String[] paths) {
            return new ArrayList<>();
        }
    }
    // you can expand more solutions HERE if you want...


    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private FindDuplicateFileInSystem problem = new FindDuplicateFileInSystem();
        private Solution solution = null;

        private void call(String[] paths, String[][] ans) {
            System.out.println(Arrays.toString(paths));
            System.out.println("Duplicate files are: " + solution.findDuplicate(paths));
            System.out.println("Answer should be: ");
            Matrix.print(ans);
            System.out.println("\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String[] paths1 = new String[]{"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
            String[][] ans1 = new String[][]{
                {"root/a/2.txt","root/c/d/4.txt","root/4.txt"},
                {"root/a/1.txt","root/c/3.txt",""}
            };


            /** involk call() method HERE */
            call(paths1, ans1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
