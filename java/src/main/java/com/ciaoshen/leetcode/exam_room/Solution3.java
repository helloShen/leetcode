/**
 * Leetcode - exam_room
 */
package com.ciaoshen.leetcode.exam_room;
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
class Solution3 implements Solution {


    private class Gap {
        private int lo, hi, dis;
        private Gap(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            this.dis = (lo == 0 || hi == size - 1)? hi - lo : (hi - lo) / 2;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Gap)) return false;
            return this.lo == ((Gap)o).lo;
        }

    }

    private int size;
    private PriorityQueue<Gap> gaps;

    public Solution3(int n) {
        size = n;
        gaps = new PriorityQueue<Gap>((Gap a, Gap b) -> (a.dis != b.dis)? b.dis - a.dis : a.lo - b.lo);
        gaps.add(new Gap(0, n - 1));
    }

    public int seat() {
        Gap gap = gaps.poll();
        if (gap.lo == 0) return takeFirst(gap);
        if (gap.hi == size - 1) return takeLast(gap);
        int seat = gap.lo + (gap.hi - gap.lo) / 2;
        if (seat - gap.lo > 0) gaps.add(new Gap(gap.lo, seat - 1));
        if (gap.hi - seat > 0) gaps.add(new Gap(seat + 1, gap.hi));
        return seat;
    }

    private int takeFirst(Gap gap) {
        if (gap.hi > 0) gaps.add(new Gap(1, gap.hi));
        return 0;
    }

    private int takeLast(Gap gap) {
        if (gap.lo < size - 1) gaps.add(new Gap(gap.lo, size - 2));
        return size - 1;
    }

    public void leave(int p) {
        if (log.isDebugEnabled()) {
            log.debug("leave seat {}", p);
        }
        Iterator<Gap> ite = gaps.iterator();
        int head = p, tail = p;
        while (ite.hasNext()) {
            Gap gap = ite.next();
            if (gap.hi == p - 1) {
                head = gap.lo;
                ite.remove();
            } else if (gap.lo == p + 1) {
                tail = gap.hi;
                ite.remove();
            }
        }
        gaps.add(new Gap(head, tail));
        if (log.isDebugEnabled()) {
            log.debug("new gap = [{},{}]", head, tail);
        }
    }

}
