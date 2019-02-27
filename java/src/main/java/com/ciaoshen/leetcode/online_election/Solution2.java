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
class Solution2 implements Solution {

    private final int maxCandidates = 5000;
    private int[] timeScopes;
    private int[] winners;

    public void init(int[] persons, int[] times) {
        int voteSize = persons.length;
        timeScopes = new int[voteSize];
        winners = new int[voteSize];
        int[] blackboard = new int[maxCandidates];
        int leaderCount = 0, leader = 0;
        for (int i = 0; i < voteSize; i++) {
            int vote = persons[i];
            int time = times[i];
            if (++blackboard[vote] >= leaderCount) {
                leaderCount = blackboard[vote];
                leader = vote;
            }
            timeScopes[i] = time;
            winners[i] = leader;
        }
    }

    public int q(int t) {
        int idx = Arrays.binarySearch(timeScopes, t);
        if (idx >= 0) {
            return winners[idx];
        } else {
            return winners[-(idx + 1) - 1];
        }
    }

}
