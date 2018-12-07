/**
 * Leetcode - accounts_merge
 */
package com.ciaoshen.leetcode.accounts_merge;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution1 implements Solution {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int size = accounts.size();
        // parse email-accountId map
        Map<String, List<Integer>> mailMap = new HashMap<>();
        for (int accountId = 0; accountId < size; accountId++) {
            List<String> account = accounts.get(accountId);
            for (int i = 1; i < account.size(); i++) {
                String mail = account.get(i);
                if (!mailMap.containsKey(mail)) {
                    mailMap.put(mail, new LinkedList<Integer>());
                }
                mailMap.get(mail).add(accountId);
            }
        }
        // build union-find board based on mailMap
        initUnionFind(size);
        for (Map.Entry<String, List<Integer>> entry : mailMap.entrySet()) {
            List<Integer> accountIds = entry.getValue();
            int rootId = accountIds.get(0);
            for (int i = 1; i < accountIds.size(); i++) {
                union(rootId, accountIds.get(i));
            }
        }
        // collect results from union-find board
        Map<Integer, Set<String>> resultMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int root = find(i);
            if (!resultMap.containsKey(root)) {
                resultMap.put(root, new HashSet<String>());
            }
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                resultMap.get(root).add(account.get(j));
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : resultMap.entrySet()) {
            List<String> mailList = new ArrayList<String>(entry.getValue());
            Collections.sort(mailList);
            mailList.add(0, accounts.get(entry.getKey()).get(0));
            result.add(mailList);

        }
        return result;
    }

    private void initUnionFind(int size) {
        board = new int[size];
        for (int i = 0; i < size; i++) board[i] = i;
    }

    // union-find
    private int[] board;

    /** append group B to group A */
    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        board[rootB] = rootA;
    }

    private int find(int a) {
        if (board[a] == a) return a;
        int root = find(board[a]);
        board[a] = root; // path compress
        return root;
    }

}
