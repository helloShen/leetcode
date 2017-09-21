/**
 * Leetcode - Algorithm - GroupShiftedStrings
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class GroupShiftedStrings implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private GroupShiftedStrings() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<List<String>> groupStrings(String[] strings);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public List<List<String>> groupStrings(String[] strings) {
            Map<String,List<String>> dic = new HashMap<>();
            for (String s : strings) {
                int dis = 'a' + 26 - s.charAt(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    sb.append((char)((s.charAt(i) - 'a' + dis) % 26 + 'a'));
                }
                String std = sb.toString();
                List<String> list = dic.get(std);
                if (list == null) {
                    list = new ArrayList<String>();
                    dic.put(std,list);
                }
                list.add(s);
            }
            List<List<String>> result = new ArrayList<>();
            for (Map.Entry<String,List<String>> entry : dic.entrySet()) {
                result.add(entry.getValue());
            }
            return result;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public List<List<String>> groupStrings(String[] strings) {
            Map<String,Integer> dic = new HashMap<>();
            List<List<String>> result = new ArrayList<>();
            int nextA = 'a' + 26;
            for (String s : strings) {
                int dis = nextA - s.charAt(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    sb.append((char)((s.charAt(i) - 'a' + dis) % 26 + 'a'));
                }
                String std = sb.toString();
                Integer index = dic.get(std);
                if (index == null) {
                    dic.put(std,result.size());
                    List<String> list = new ArrayList<>(Arrays.asList(new String[]{s}));
                    result.add(list);
                } else {
                    List<String> list = result.get(index);
                    list.add(s);
                }
            }
            return result;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<List<String>> groupStrings(String[] strings) {
            Map<String,Integer> dic = new HashMap<>();
            List<List<String>> result = new ArrayList<>();
            for (String s : strings) {
                String std = std2(s);
                Integer index = dic.get(std);
                if (index == null) {
                    dic.put(std,result.size());
                    List<String> list = new ArrayList<>(Arrays.asList(new String[]{s}));
                    result.add(list);
                } else {
                    List<String> list = result.get(index);
                    list.add(s);
                }
            }
            return result;
        }
        // map a string to standard space: start with 'a'
        private String std(String s) {
            int dis = 'a' + 26 - s.charAt(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                sb.append((char)((s.charAt(i) - 'a' + dis) % 26 + 'a'));
            }
            return sb.toString();
        }
        // map a string to standard space: start with 'a'
        private String std2(String s) {
            int dis = s.charAt(0) - 'a';
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = (char)(s.charAt(i) - dis);
                if (c < 'a') { c += 26; }
                sb.append(c);
            }
            return sb.toString();
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
        private GroupShiftedStrings problem = new GroupShiftedStrings();
        private Solution solution = null;

        // call method in solution
        private void call(Solution s, String[] strings) {
            System.out.println("For String List: " + Arrays.toString(strings));
            System.out.println("Shifted String Groups are: " + s.groupStrings(strings));
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            String[] str1 = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};

            call(solution,str1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        test.test(3);
    }
}
