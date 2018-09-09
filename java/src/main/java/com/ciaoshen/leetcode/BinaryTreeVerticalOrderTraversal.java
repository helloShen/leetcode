/**
 * Leetcode - Algorithm - BinaryTreeVerticalOrderTraversal
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BinaryTreeVerticalOrderTraversal implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BinaryTreeVerticalOrderTraversal() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<List<Integer>> verticalOrder(TreeNode root); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /**
     * 不知道为什么，最后一个测试1，2百个数字里就有2对数字出错
     */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public List<List<Integer>> verticalOrder(TreeNode root) {
            int[] globalRange = new int[]{1,0}; // empty
            Map<Integer,List<Integer>> global = new HashMap<>();
            int[] levelRange = new int[]{1,0};  // empty
            Map<Integer,List<TreeNode>> level = new HashMap<>();
            // parse
            this.<TreeNode>updateMap(level,levelRange,0,root);
            while (levelRange[1] >= levelRange[0]) {
                int[] nextLevelRange = new int[]{1,0};
                Map<Integer,List<TreeNode>> nextLevel = new HashMap<>();
                for (int i = levelRange[0]; i <= levelRange[1]; i++) {
                    if (level.containsKey(i)) {
                        for (TreeNode node : level.get(i)) {
                            // collect info of nodes in this level
                            this.<Integer>updateMap(global,globalRange,i,node.val);
                            // collect nodes in next level (null node will not be added into map)
                            this.<TreeNode>updateMap(nextLevel,nextLevelRange,i-1,node.left);
                            this.<TreeNode>updateMap(nextLevel,nextLevelRange,i+1,node.right);
                        }
                    }
                }
                level = nextLevel;
                levelRange = nextLevelRange;
            }
            // collect list from global map
            List<List<Integer>> res = new ArrayList<>();
            for (int i = globalRange[0]; i <= globalRange[1]; i++) {
                res.add(new ArrayList<>(global.get(i)));
            }
            return res;
        }
        // 即可更新最终统计表global，也可以更新每层统计表level以及nextLevel
        // 约定：不接受item为null。如果map键对应的值为null，则不添加这个键-值对
        private <T> void updateMap(Map<Integer,List<T>> map, int[] range, int order, T item) {
            if (item == null) { return; }
            if (map.containsKey(order)) {
                map.get(order).add(item);
            } else {
                List<T> list = new ArrayList<>();
                list.add(item);
                map.put(order,list);
                // update range
                if (order < range[0]) {
                    range[0] = order;
                } else if (order > range[1]) {
                    range[1] = order;
                }
            }
        }
    }

    /** 用2个ArrayList的BFS */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) { return result; }
            //全局统计
            int[] globalRange = new int[]{0,0};
            Map<Integer,List<Integer>> global = new HashMap<>();
            //按行统计
            List<Integer> orders = new ArrayList<>();
            List<TreeNode> nodes = new ArrayList<>();
            //BFS
            orders.add(0);
            nodes.add(root);
            int size = 1;
            while (size > 0) {
                for (int i = 0; i < size; i++) {
                    TreeNode node = nodes.remove(0);
                    int order = orders.remove(0);
                    if (global.containsKey(order)) {
                        global.get(order).add(node.val);
                    } else {
                        global.put(order,new ArrayList<Integer>(Arrays.asList(new Integer[]{node.val})));
                        if (order < globalRange[0]) {
                            globalRange[0] = order;
                        } else if (order > globalRange[1]) {
                            globalRange[1] = order;
                        }
                    }
                    if (node.left != null) { orders.add(order-1); nodes.add(node.left); }
                    if (node.right != null) { orders.add(order+1); nodes.add(node.right); }
                    size = nodes.size();
                }
            }
            //将全局统计Map转化成List返回
            for (int i = globalRange[0]; i <= globalRange[1]; i++) {
                result.add(new ArrayList<Integer>(global.get(i)));
            }
            return result;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<List<Integer>> verticalOrder(TreeNode root) {
            return null;
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
        private BinaryTreeVerticalOrderTraversal problem = new BinaryTreeVerticalOrderTraversal();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root) {
            // System.out.println((root == null)? "null" : root.bfs());
            System.out.println("Vertical Order: ");
            System.out.println(solution.verticalOrder(root));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** test cases */
            TreeNode root1 = TreeNode.stringToTreeNode("[3,9,8,4,0,1,7,null,null,null,2,5]");
            TreeNode root2 = null;
            String strTree3 = "[-64,12,18,-4,-53,null,76,null,-51,null,null,-93,3,null,-31,47,null,3,53,-81,33,4,null,-51,-44,-60,11,null,null,null,null,78,null,-35,-64,26,-81,-31,27,60,74,null,null,8,-38,47,12,-24,null,-59,-49,-11,-51,67,null,null,null,null,null,null,null,-67,null,-37,-19,10,-55,72,null,null,null,-70,17,-4,null,null,null,null,null,null,null,3,80,44,-88,-91,null,48,-90,-30,null,null,90,-34,37,null,null,73,-38,-31,-85,-31,-96,null,null,-18,67,34,72,null,-17,-77,null,56,-65,-88,-53,null,null,null,-33,86,null,81,-42,null,null,98,-40,70,-26,24,null,null,null,null,92,72,-27,null,null,null,null,null,null,-67,null,null,null,null,null,null,null,-54,-66,-36,null,-72,null,null,43,null,null,null,-92,-1,-98,null,null,null,null,null,null,null,39,-84,null,null,null,null,null,null,null,null,null,null,null,null,null,-93,null,null,null,98]";
            TreeNode root3 = TreeNode.stringToTreeNode(strTree3);

            /** initialize your testcases HERE... */
            // call(root1);
            // call(root2);
            call(root3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        // test.test(3);
    }
}
