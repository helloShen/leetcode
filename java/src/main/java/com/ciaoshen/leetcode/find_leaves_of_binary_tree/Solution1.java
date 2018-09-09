/**
 * Leetcode - #366 - Find Leaves of Binary Tree
 * 
 */
package com.ciaoshen.leetcode.find_leaves_of_binary_tree;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        map = new HashMap<Integer, List<Integer>>();
        dfs(root);
        for (int i = 0; i < map.size(); i++) {
            list.add(map.get(i));
        }
        return list;
    }

    /**=================== 【以下为私有】 ========================= */
    private Map<Integer,List<Integer>> map;
    // 把节点按层次归类
    // 叶节点层次为0，越往父节点，层次越高。 NULL节点层次为-1。
    private int dfs(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        int level = Math.max(left,right) + 1;        
        if (!map.containsKey(level)) {
            map.put(level, new ArrayList<Integer>());
        }
        map.get(level).add(node.val);
        return level;
    }
}
