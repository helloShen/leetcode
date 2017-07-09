/**
 * Leetcode - Algorithm - Clone Graph
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.UndirectedGraphNode;


class CloneGraph {
    public class SolutionV1 {
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) { return null; }
            UndirectedGraphNode dummyOrigin = new UndirectedGraphNode(0);
            dummyOrigin.neighbors.add(node);
            UndirectedGraphNode dummyCopy = new UndirectedGraphNode(0);
            dfs(dummyOrigin,dummyCopy,new ArrayList<UndirectedGraphNode>());
            return dummyCopy.neighbors.get(0);
        }
        public void dfs(UndirectedGraphNode origin, UndirectedGraphNode copy, List<UndirectedGraphNode> existNodes) {
            outFor:
            for (UndirectedGraphNode node : origin.neighbors) {
                inFor:
                for (UndirectedGraphNode exist : existNodes) {
                    if (exist.label == node.label) {
                        copy.neighbors.add(exist);
                        continue outFor;
                    }
                }
                UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
                existNodes.add(newNode);
                copy.neighbors.add(newNode);
                dfs(node,newNode,existNodes);
            }
        }
    }
    public class SolutionV2 {
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) { return null; }
            UndirectedGraphNode dummyOrigin = new UndirectedGraphNode(0);
            dummyOrigin.neighbors.add(node);
            UndirectedGraphNode dummyCopy = new UndirectedGraphNode(0);
            dfs(dummyOrigin,dummyCopy,new HashMap<Integer,UndirectedGraphNode>());
            return dummyCopy.neighbors.get(0);
        }
        public void dfs(UndirectedGraphNode origin, UndirectedGraphNode copy, Map<Integer,UndirectedGraphNode> existNodes) {
            for (UndirectedGraphNode node : origin.neighbors) {
                UndirectedGraphNode searchInExistNodes = existNodes.get(node.label);
                if (searchInExistNodes != null) {
                    copy.neighbors.add(searchInExistNodes);
                    continue outFor;
                } else {
                    UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
                    existNodes.put(node.label,newNode);
                    copy.neighbors.add(newNode);
                    dfs(node,newNode,existNodes);
                }
            }
        }
    }
    public class Solution {
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) { return null; }
            UndirectedGraphNode dummyOrigin = new UndirectedGraphNode(0);
            dummyOrigin.neighbors.add(node);
            UndirectedGraphNode dummyCopy = new UndirectedGraphNode(0);
            bfs(dummyOrigin,dummyCopy,new HashMap<Integer,UndirectedGraphNode>());
            return dummyCopy.neighbors.get(0);
        }
        public void bfs(UndirectedGraphNode origin, UndirectedGraphNode copy, Map<Integer,UndirectedGraphNode> existNodes) {
            Map<UndirectedGraphNode,UndirectedGraphNode> newNodes = new HashMap<>();
            for (UndirectedGraphNode node : origin.neighbors) {
                UndirectedGraphNode searchInExistNodes = existNodes.get(node.label);
                if (searchInExistNodes != null) {
                    copy.neighbors.add(searchInExistNodes);
                } else {
                    UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
                    copy.neighbors.add(newNode);
                    newNodes.put(node,newNode);
                    existNodes.put(node.label,newNode);
                }
            }
            for (Map.Entry<UndirectedGraphNode,UndirectedGraphNode> pair : newNodes.entrySet()) {
                bfs(pair.getKey(),pair.getValue(),existNodes);
            }
        }
    }
    private static CloneGraph test = new CloneGraph();
    private static void testCloneGraph () {
        UndirectedGraphNode one = new UndirectedGraphNode(1);
        UndirectedGraphNode two = new UndirectedGraphNode(2);
        UndirectedGraphNode three = new UndirectedGraphNode(3);
        UndirectedGraphNode four = new UndirectedGraphNode(4);
        one.neighbors.add(two);
        one.neighbors.add(three);
        two.neighbors.add(four);
        four.neighbors.add(two);
        System.out.println("Original Graph: " + one);
        System.out.println("Copy Graph: " + test.new Solution().cloneGraph(one));
        UndirectedGraphNode zero = new UndirectedGraphNode(0);
        zero.neighbors.add(zero);
        zero.neighbors.add(zero);
        System.out.println("Original Graph: " + zero);
        System.out.println("Copy Graph: " + test.new Solution().cloneGraph(zero));
    }
    public static void main(String[] args) {
        testCloneGraph();
    }
}
