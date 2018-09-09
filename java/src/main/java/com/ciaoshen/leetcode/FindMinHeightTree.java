/**
 * Leetcode - Algorithm - FindMinHeightTree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FindMinHeightTree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FindMinHeightTree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
        register(new Solution6());
        register(new Solution7());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<Integer> findMinHeightTrees(int n, int[][] edges); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }

    /**
     * 最笨的办法
     * 暴力递归，计算出所有节点两两之间的距离
     */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer> res = new ArrayList<>();
            localEdges = edges;
            table = new int[n][n];
            for (int i = 0; i < n; i++) {
                walkthrough(i,-1,i,0);
            }
            int minHeight = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int height = 0;
                for (int j = 0; j < n; j++) {
                    height = Math.max(height,table[i][j]);
                }
                if (height <= minHeight) {
                    if (height < minHeight) {
                        minHeight = height;
                        res.clear();
                    }
                    res.add(i);
                }
            }
            return res;
        }
        private int[][] table = new int[0][0];
        private int[][] localEdges = new int[0][0];
        private void walkthrough(int root, int from, int curr, int dis) {
            table[root][curr] = dis;
            table[curr][root] = dis;
            for (int[] edge : localEdges) {
                if (edge[0] == curr && edge[1] != from) {
                    walkthrough(root,curr,edge[1],dis+1);
                } else if (edge[1] == curr && edge[0] != from) {
                    walkthrough(root,curr,edge[0],dis+1);
                }
            }
        }
    }

    /**
     * 基本思想是计算树的每条路径的长度。全局最长路径的中位节点即是我们要找的节点
     * 通过回溯算法来找最长线段
     * 复杂度O(n^2)
     */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            init(n,edges);
            for (int i = 0; i < n; i++) {
                backtracking(-1,i);
            }
            return new ArrayList<Integer>(res);
        }
        private Set<Integer> res = new HashSet<>();
        private Map<Integer,List<Integer>> localEdges = new HashMap<>();
        private int longestSegment = 0;
        private int[] path = new int[0];
        private int cursor = 0;
        private void init(int n, int[][] edges) {
            res.clear();
            initEdges(edges);
            longestSegment = 0;
            path = new int[n+1];
            Arrays.fill(path,-1);
            cursor = 0;
        }
        private void initEdges(int[][] edges) {
            localEdges.clear();
            if (edges.length == 0) { return; }
            for (int[] edge : edges) {
                if (localEdges.containsKey(edge[0])) {
                    localEdges.get(edge[0]).add(edge[1]);
                } else {
                    localEdges.put(edge[0],new ArrayList<Integer>(Arrays.asList(new Integer[]{edge[1]})));
                }
                if (localEdges.containsKey(edge[1])) {
                    localEdges.get(edge[1]).add(edge[0]);
                } else {
                    localEdges.put(edge[1],new ArrayList<Integer>(Arrays.asList(new Integer[]{edge[0]})));
                }
            }
        }
        private void backtracking(int from, int curr) {
            path[cursor++] = curr;
            int child = 0;
            if (!localEdges.isEmpty()) {
                for (int next : localEdges.get(curr)) {
                    if (next != from) {
                        child++;
                        backtracking(curr,next);
                    }
                }
            }
            if (child == 0) {
                if (cursor >= longestSegment) {
                    if (cursor > longestSegment) {
                        longestSegment = cursor;
                        res.clear();
                    }
                    int mid = (cursor - 1) / 2;
                    res.add(path[mid]);
                    if (cursor % 2 == 0) { res.add(path[mid+1]); }
                }
            }
            path[--cursor] = -1;
        }
    }

    /**
     * 原理：找到树中最长的线段，然后找他/他们的中位节点
     * 利用回溯算法计算两两叶节点间的距离
     */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            if (n == 1) { return new ArrayList<Integer>(Arrays.asList(new Integer[]{0})); }
            init(n,edges);
            for (int i = 0; i < n; i++) {
                backtracking(i,-1,i);
            }
            return new ArrayList<Integer>(res);
        }
        private Set<Integer> res = new HashSet<>();
        private Map<Integer,List<Integer>> localEdges = new HashMap<>();
        private List<Integer> internals = new ArrayList<>();
        private List<Integer> leaves = new ArrayList<>();
        private int longestSegment = 0;
        private int[] path = new int[0];
        private int cursor = 0;

        private void init(int n, int[][] edges) {
            res.clear();
            initEdges(edges);
            initInternalsLeaves();
            initBacktracking(n);
        }
        // 列出和每个节点直接连接（edge）的所有节点列表
        private void initEdges(int[][] edges) {
            localEdges.clear();
            if (edges.length == 0) { return; }
            for (int[] edge : edges) {
                if (localEdges.containsKey(edge[0])) {
                    localEdges.get(edge[0]).add(edge[1]);
                } else {
                    localEdges.put(edge[0],new ArrayList<Integer>(Arrays.asList(new Integer[]{edge[1]})));
                }
                if (localEdges.containsKey(edge[1])) {
                    localEdges.get(edge[1]).add(edge[0]);
                } else {
                    localEdges.put(edge[1],new ArrayList<Integer>(Arrays.asList(new Integer[]{edge[0]})));
                }
            }
        }
        // 把所有节点分为【叶节点】和【内部节点】
        // 所谓一条【路径】就是两个【叶节点】之间的距离
        private void initInternalsLeaves() {
            internals.clear();
            leaves.clear();
            for (Map.Entry<Integer,List<Integer>> list : localEdges.entrySet()) {
                if (list.getValue().size() == 1) {
                    leaves.add(list.getKey());
                } else {
                    internals.add(list.getKey());
                }
            }
        }
        private void initBacktracking(int n) {
            longestSegment = 0;
            path = new int[n+1];
            cursor = 0;
        }
        /**
         * 回溯算法
         * 找出一个叶节点（root）到所有其他叶节点的路径长度
         *      curr: 是当前节点
         *      pre: 是上一个经过的节点
         */
        private void backtracking(int root, int pre, int curr) {
            path[cursor++] = curr;
            // base case: 到了另一个叶节点
            if (leaves.contains(curr) && curr != root) {
                if (cursor >= longestSegment) {
                    // 如果找到更长的路径，首先清空之前的结果列表
                    if (cursor > longestSegment) {
                        longestSegment = cursor;
                        res.clear();
                    }
                    // 更新结果列表: 把中位节点加入结果列表
                    int mid = (cursor - 1) / 2;
                    res.add(path[mid]);
                    if (cursor % 2 == 0) {
                        res.add(path[mid+1]);
                    }
                }
            } else {
                // 遇到非叶节点就递归出去（除了来的这条路）
                for (int next : localEdges.get(curr)) {
                    if (next != pre) {
                        backtracking(root,curr,next);
                    }
                }
            }
            --cursor;
        }
    }
    // you can expand more solutions HERE if you want...
    /**
     * 还是剥洋葱法，一层层剥离叶节点
     * 统计所有节点以及他们的相关边，并储存在一个HashMap里
     */
    private class Solution4 extends Solution {
        { super.id = 4; }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            // System.out.println("n = " + n);
            // Matrix.print(edges);
            Map<Integer,List<Integer>> edgeMap = initEdgeMap(edges);
            // System.out.println("Edge Map = " + edgeMap);
            List<Integer> allNodes = new ArrayList<>();
            for (int i = 0; i < n; i++) { allNodes.add(i); }
            List<Integer> leaves = findLeaves(edgeMap);
            // System.out.println("Leaves = " + leaves);
            while (allNodes.size() > 2) {
                for (int leaf : leaves) {
                    // System.out.println("\t>>>>>>新的一轮开始<<<<<<");
                    // 删除一个叶节点，以及和它相关的边
                    deleteALeaf(edgeMap,leaf);
                    allNodes.remove(new Integer(leaf));
                    // System.out.println("移除leaf: " + leaf);
                    // System.out.println("现在allNodes列表" + leaves);
                    // System.out.println("现在Leaves列表：" + leaves);
                    // System.out.println("现在EdgeMap： " + edgeMap);
                }
                // 重新找到当前的叶节点
                leaves = findLeaves(edgeMap);
            }
            return allNodes;
        }

        // 列出和每个节点直接连接（edge）的所有节点列表
        private Map<Integer,List<Integer>> initEdgeMap(int[][] edges) {
            Map<Integer,List<Integer>> edgeMap = new HashMap<>();
            for (int[] edge : edges) {
                if (edgeMap.containsKey(edge[0])) {
                    edgeMap.get(edge[0]).add(edge[1]);
                } else {
                    edgeMap.put(edge[0],new ArrayList<Integer>(Arrays.asList(new Integer[]{edge[1]})));
                }
                if (edgeMap.containsKey(edge[1])) {
                    edgeMap.get(edge[1]).add(edge[0]);
                } else {
                    edgeMap.put(edge[1],new ArrayList<Integer>(Arrays.asList(new Integer[]{edge[0]})));
                }
            }
            return edgeMap;
        }
        // 找出所有的叶节点
        private List<Integer> findLeaves(Map<Integer,List<Integer>> edgeMap) {
            List<Integer> leaves = new ArrayList<>();
            for (Map.Entry<Integer,List<Integer>> edgeGroup : edgeMap.entrySet()) {
                if (edgeGroup.getValue().size() == 1) {
                    leaves.add(edgeGroup.getKey());
                }
            }
            return leaves;
        }
        // 从树中删除某个叶节点
        private void deleteALeaf(Map<Integer,List<Integer>> edgeMap, int leaf) {
            int leafBro = edgeMap.remove(leaf).get(0);
            edgeMap.get(leafBro).remove(new Integer(leaf));
        }
    }


    /**
     * 还是剥洋葱法，一层层剥离叶节点
     * 统计所有节点以及他们的相关边，并储存在一个HashMap里
     */
    private class Solution5 extends Solution {
        { super.id = 5; }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            // 计算所有节点的相关边数（degree）。所有叶节点的degree=1
            int[] degrees = new int[n];
            for (int[] edge : edges) {
                degrees[edge[0]]++;
                degrees[edge[1]]++;
            }
            int numEdges = edges.length;
            Map<Integer,List<Integer>> edgeMap = initEdgeMap(edges);
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) { res.add(i); }
            int[] temp = new int[n];
            while (numEdges > 1) {
                for (int i = 0; i < n; i++) {
                    if (degrees[i] == 1) {
                        System.out.println("Remove leaf: " + i);
                        res.remove(new Integer(i));
                        degrees[i]--;
                        int leafBro = edgeMap.remove(i).get(0);
                        temp[leafBro]++;
                        edgeMap.get(leafBro).remove(new Integer(i));
                        numEdges--;
                    }
                }
                // 相关边数要等一次遍历完了之后再更新
                for (int i = 0; i < n; i++) {
                    degrees[i] -= temp[i];
                    temp[i] = 0;
                }
            }
            return res;
        }
        private Map<Integer,List<Integer>> initEdgeMap(int[][] edges) {
            Map<Integer,List<Integer>> edgeMap = new HashMap<>();
            for (int[] edge : edges) {
                if (edgeMap.containsKey(edge[0])) {
                    edgeMap.get(edge[0]).add(edge[1]);
                } else {
                    edgeMap.put(edge[0],new ArrayList<Integer>(Arrays.asList(new Integer[]{edge[1]})));
                }
                if (edgeMap.containsKey(edge[1])) {
                    edgeMap.get(edge[1]).add(edge[0]);
                } else {
                    edgeMap.put(edge[1],new ArrayList<Integer>(Arrays.asList(new Integer[]{edge[0]})));
                }
            }
            return edgeMap;
        }
    }

    /**
     * 还是剥洋葱法
     * 但尝试用List[]泛型数组替代HashMap来储存节点及其相关边信息
     */
    private class Solution6 extends Solution {
        { super.id = 6; }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            // 计算所有节点的相关边数
            Map<Integer,List<Integer>> nodes = new HashMap<>();
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                result.add(i);
                nodes.put(i,new ArrayList<Integer>());
            }
            for (int[] edge : edges) {
                nodes.get(edge[0]).add(edge[1]);
                nodes.get(edge[1]).add(edge[0]);
            }
            // 在最后还剩少于等于2个节点之前一直循环
            int[] leaves = new int[n*2]; // [节点号，对应节点，节点号，对应节点，...]
            while (result.size() > 2) {
                int leafP = 0;
                // 取出所有叶节点，并删掉所有叶节点和相关边
                for (Map.Entry<Integer,List<Integer>> node : nodes.entrySet()) {
                    if (node.getValue().size() == 1) {
                        leaves[leafP++] = node.getKey();
                        leaves[leafP++] = node.getValue().get(0);
                    }
                }
                for (int i = 0; i < leafP; i+=2) {
                    int leaf = leaves[i];
                    result.remove(new Integer(leaf));
                    nodes.remove(leaf);
                    int neighbour = leaves[i+1];
                    nodes.get(neighbour).remove(new Integer(leaf));
                }
            }
            return result;
        }
    }

    /**
     * 最优版本
     */
    private class Solution7 extends Solution {
        { super.id = 7; }

        @SuppressWarnings({"unchecked","rawtypes"})
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            if (n == 0) { return new ArrayList<Integer>(); }
            if (n == 1) { return new ArrayList<Integer>(Arrays.asList(new Integer[]{0})); }
            // 计算所有节点的相关边数
            List<Integer>[] nodes = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = (ArrayList<Integer>) new ArrayList();
            }
            for (int[] edge : edges) {
                nodes[edge[0]].add(edge[1]);
                nodes[edge[1]].add(edge[0]);
            }
            // 取出所有叶节点，并删掉所有叶节点和相关边
            List<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (nodes[i].size() == 1) {
                    leaves.add(i);
                }
            }
            // 在最后还剩少于等于2个节点之前一直循环
            int size = n;
            while (size > 2) {
                List<Integer> newLeafCandidates = new ArrayList<>();
                for (int leaf : leaves) {
                    int neighbour = nodes[leaf].remove(0);
                    nodes[neighbour].remove(new Integer(leaf));
                    if (nodes[neighbour].size() == 1) { newLeafCandidates.add(neighbour); }
                    size--;
                }
                leaves = newLeafCandidates;
            }
            return leaves;
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
        private FindMinHeightTree problem = new FindMinHeightTree();
        private Solution solution = null;

        // call method in solution
        private void call(int n, int[][] edges) {
            System.out.println("最终结果：Min Height Tree = " + solution.findMinHeightTrees(n, edges));
            System.out.println("###############################################");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int n1 = 4;
            int[][] edges1 = new int[][]{{1, 0}, {1, 2}, {1, 3}};
            int n2 = 6;
            int[][] edges2 = new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
            int n3 = 1;
            int[][] edges3 = new int[0][];
            int n4 = 7;
            int[][] edges4 = new int[][]{{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}};
            int n5 = 8;
            int[][] edges5 = new int[][]{{0,1},{1,2},{2,3},{0,4},{4,5},{4,6},{6,7}};

            /** involk call() method HERE */
            call(n1,edges1);
            call(n2,edges2);
            call(n3,edges3);
            call(n4,edges4);
            call(n5,edges5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.test(4);
        // test.test(5);
        // test.test(6);
        test.test(7);
        // test.test(8);
    }
}
