/**
 * Leetcode - online_election
 */
package com.ciaoshen.leetcode.online_election;
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

    private List<Integer>[] blackboard;

    @SuppressWarnings("unchecked")
    public void init(int[] persons, int[] times) {
        blackboard = (List<Integer>[]) new List[5000];
        for (int i = 0; i < persons.length; i++) {
            int person = persons[i], vote = times[i];
            if (blackboard[person] == null) blackboard[person] = new LinkedList<Integer>();
            blackboard[person].add(vote);
        }
        // if (log.isDebugEnabled()) {
        //     for (int i = 0; i < blackboard.length; i++) {
        //         if (blackboard[i] != null) {
        //            log.debug("candidate @{} -> {}", i, blackboard[i]);
        //         }
        //     }
        // }
    }

    public int q(int t) {
        int max = 0, maxPerson = 0, recentTime = 0;
        for (int i = 0; i < blackboard.length; i++) {
            List<Integer> votes = blackboard[i];
            if (votes != null) {
                int idx = Collections.binarySearch(votes, t);
                // if (log.isDebugEnabled()) {
                //     log.debug("for time[{}] binary search idx = {}", t, idx);
                // }
                int voteCount = 0, voteTime = 0;
                if (idx >= 0) {
                    voteCount = idx + 1;
                    voteTime = votes.get(idx);
                } else {
                    voteCount = -(idx + 1);
                    if (voteCount > 0) voteTime = votes.get(voteCount - 1);
                }
                // if (log.isDebugEnabled()) {
                //     log.debug("until time [{}], candidate @{} get [{}] votes (recent vote time = [{}]).", t, i, voteCount, voteTime);
                // }
                if (voteCount > max || (voteCount == max && voteTime > recentTime)) {
                    max = voteCount;
                    maxPerson = i;
                    recentTime = voteTime;
                }
            }
        }
        return maxPerson;
    }

}
