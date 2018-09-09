/**
 * Leetcode - Algorithm - TopKFrequentElements
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class TopKFrequentElements implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private TopKFrequentElements() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<Integer> topKFrequent(int[] nums, int k); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* 复杂度 O(nlogn) */
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer,Integer> freq = new HashMap<>();
            for (int n : nums) {
                freq.put(n,(freq.containsKey(n))? freq.get(n)+1 : 1);
            }
            List<Map.Entry<Integer,Integer>> list = new ArrayList<>(freq.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<Integer,Integer>>(){
                public int compare(Map.Entry<Integer,Integer> a, Map.Entry<Integer,Integer> b) {
                    int freqA = a.getValue();
                    int freqB = b.getValue();
                    if (freqA > freqB) { // 降序
                        return -1;
                    } else if (freqA < freqB) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            List<Integer> res = new ArrayList<>();
            for (Map.Entry<Integer,Integer> entry : list) {
                if (k > 0) {
                    res.add(entry.getKey());
                    --k;
                } else {
                    break;
                }
            }
            return res;
        }
    }

    /* 复杂度 O(n) 不用给Map排序 */
    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public List<Integer> topKFrequent(int[] nums, int k) {
            int len = nums.length;
            Map<Integer,Integer> freq = new HashMap<>();
            for (int n : nums) {
                Integer f = freq.get(n);
                freq.put(n,(f == null)? 1 : f + 1);
            }
            Integer[][] matrix = new Integer[len+1][len+1]; // 行下标表示频率。每行最后一列表示指向当前行元素的指针。
            for (Integer[] numArray : matrix) {
                numArray[len] = 0;
            }
            for (Map.Entry<Integer,Integer> entry : freq.entrySet()) {
                int f = entry.getValue();
                matrix[f][matrix[f][len]++] = entry.getKey();
            }
            List<Integer> res = new ArrayList<>();
            for (int i = len; i >= 0; i--) {
                Integer[] numArray = matrix[i];
                for (Integer num : numArray) {
                    if (k > 0) {
                        if (num != null) {
                            res.add(num); --k;
                        } else {
                            break;
                        }
                    } else {
                        return res;
                    }
                }
            }
            return res;
        }
    }

    /* O(n). 不给Map排序。用一个List[]数组，来代替二维数组 */
    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer,Integer> freq = new HashMap<>();
            for (int num : nums) {
                Integer f = freq.get(num);
                freq.put(num,(f == null)? 1 : f + 1);
            }
            // 不能创建泛型数组。折中方案是先使用通配符，然后转型。
            // 但这样仍然是类型不安全的，需要用@SuppressWarnings去掉警告
            @SuppressWarnings("unchecked")
            List<Integer>[] matrix = (List<Integer>[])new List<?>[nums.length+1];
            for (Map.Entry<Integer,Integer> entry : freq.entrySet()) {
                Integer f = entry.getValue();
                if (matrix[f] == null) { matrix[f] = new ArrayList<Integer>(); }
                matrix[f].add(entry.getKey());
            }
            List<Integer> res = new ArrayList<>();
            for (int i = nums.length; i >= 0; i--) {
                if (matrix[i] != null) {
                    for (Integer num : matrix[i]) {
                        if (k > 0) {
                            res.add(num); --k;
                        } else {
                            return res;
                        }
                    }
                }
            }
            return res;
        }
    }
    // you can expand more solutions HERE if you want...
    private class Solution4 extends Solution {
        { super.id = 4; }
        // implement your solution's method HERE...
        public List<Integer> topKFrequent(int[] nums, int k) {
            return null;
        }
        protected void sometest() {
            TreeNode tree = TreeNode.randomBST(20);
            System.out.println(tree.bfs());
        }
    }
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
        private TopKFrequentElements problem = new TopKFrequentElements();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums, int k) {
            System.out.println(Arrays.toString(nums));
            System.out.println("K top frequent elements are: " + solution.topKFrequent(nums,k));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1};
            int k1 = 1;
            int[] nums2 = new int[]{1,1,1,2,2,3};
            int k2 = 2;
            // int[] nums3 = new int[]{};
            // int k3 = ;

            /** involk call() method HERE */
            // call(nums1,k1);
            // call(nums2,k2);
        }
        public void otherTest(int id) {
            solution = problem.solution(id);
            solution.sometest();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.test(4);

        /* other tests */
        test.otherTest(4);
    }
}
