/**
 * Leetcode - Algorithm - SortCharactersByFrequency
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class SortCharactersByFrequency implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private SortCharactersByFrequency() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String frequencySort(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public String frequencySort(String s) {
            Map<Character,Integer> freqMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                freqMap.put(c,freqMap.getOrDefault(c,0)+1);
            }
            List<Map.Entry<Character,Integer>> freqList = new ArrayList<>(freqMap.entrySet());
            Collections.sort(freqList,new Comparator<Map.Entry<Character,Integer>>(){
                public int compare(Map.Entry<Character,Integer> a, Map.Entry<Character,Integer> b) {
                    return b.getValue() - a.getValue();
                }
            });
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Character,Integer> entry : freqList) {
                for (int i = 0; i < entry.getValue(); i++) {
                    sb.append(entry.getKey());
                }
            }
            return sb.toString();
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public String frequencySort(String s) {
            if (s.length() < 2) { return s; }
            PriorityQueue<StringBuilder> queue = new PriorityQueue<>(s.length(), new Comparator<StringBuilder>(){
                public int compare(StringBuilder sb1, StringBuilder sb2) {
                    return sb2.length() - sb1.length();
                }
            });
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                System.out.println(c);
                StringBuilder old = null;
                System.out.println("Before: " + queue);
                Iterator<StringBuilder> ite = queue.iterator();
                while (ite.hasNext()) {
                    StringBuilder str = ite.next();
                    if (str.charAt(0) == c) {
                        ite.remove();
                        old = str;
                        break;
                    }
                }
                queue.offer((old == null)? new StringBuilder().append(c) : (old.append(c)));
                System.out.println("After: " + queue);
            }
            StringBuilder sb = new StringBuilder();
            while(!queue.isEmpty()) {
                sb.append(queue.poll());
            }
            return sb.toString();
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public String frequencySort(String s) {
            if (s.length() < 2) { return s; }
            Map<Character,Integer> freqMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                freqMap.put(c,freqMap.getOrDefault(c,0)+1);
            }
            StringBuilder[] buckets = new StringBuilder[s.length()+1];
            for (Map.Entry<Character,Integer> entry : freqMap.entrySet()) {
                int freq = entry.getValue();
                char c = entry.getKey();
                if (buckets[freq] == null) { buckets[freq] = new StringBuilder(); }
                buckets[freq].append(c);
            }
            StringBuilder res = new StringBuilder();
            for (int i = buckets.length - 1; i > 0; i--) {
                StringBuilder sb = buckets[i];
                if (sb != null) {
                    for (int j = 0; j < sb.length(); j++) {
                        char c = sb.charAt(j);
                        for (int k = 0; k < i; k++) {
                            res.append(c);
                        }
                    }
                }
            }
            return res.toString();
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
        private SortCharactersByFrequency problem = new SortCharactersByFrequency();
        private Solution solution = null;

        // call method in solution
        private void call(String s, String ans) {
            System.out.println("Original String = \"" + s + "\"");
            System.out.println("Sorted String = \"" + solution.frequencySort(s) + "\"");
            System.out.println("Answer should be = \"" + ans + "\"\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "tree";
            String ans1 = "eert";
            String s2 = "cccaaa";
            String ans2 = "aaaccc";
            String s3 = "Aabb";
            String ans3 = "bbAa";
            String s4 = "raaeaedere";
            String ans4 = "eeeeaaarrd";
            String s5 = "fjjsrielrdjkdiiriewwej";
            String ans5 = "iiiijjjjeeerrrwwddfls";

            /** involk call() method HERE */
            call(s1,ans1);
            call(s2,ans2);
            call(s3,ans3);
            call(s4,ans4);
            call(s5,ans5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
