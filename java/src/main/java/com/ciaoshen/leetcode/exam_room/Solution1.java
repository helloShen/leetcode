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
class Solution1 implements Solution {

    private class Gap {
        private int lo, hi;
        private Gap(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Gap)) return false;
            return this.lo == ((Gap)o).lo;
        }

    }

    private int size;
    private Set<Integer> seats;
    private TreeMap<Gap, Gap> gaps;

    /** TreeMap<Integer, Gap> 直接记录distance */
    public Solution1(int n) {
        size = n;
        seats = new HashSet<Integer>();
        gaps = new TreeMap<Gap, Gap>(new Comparator<Gap>(){
            public int compare(Gap a, Gap b) {
                int disA = (a.lo == 0 || a.hi == (size - 1))? (a.hi - a.lo) : (a.hi - a.lo) / 2;
                int disB = (b.lo == 0 || b.hi == (size - 1))? (b.hi - b.lo) : (b.hi - b.lo) / 2;
                if (disA != disB) return disA - disB;
                return b.lo - a.lo;
            }
        });
        gaps.put(new Gap(0, n - 1), null);
    }

    public int seat() {
        if (gaps.isEmpty()) return -1;
        Gap gap = gaps.pollLastEntry().getKey();
        if (gap.lo == 0) return takeFirst(gap);
        if (gap.hi == size - 1) return takeLast(gap);
        int seat = gap.lo + (gap.hi - gap.lo) / 2;
        if (seat - gap.lo > 0) gaps.put(new Gap(gap.lo, seat - 1), null);
        if (gap.hi - seat > 0) gaps.put(new Gap(seat + 1, gap.hi), null);
        seats.add(seat);
        return seat;
    }

    private int takeFirst(Gap gap) {
        seats.add(0);
        if (gap.hi > 0) gaps.put(new Gap(1, gap.hi), null);
        return 0;
    }

    private int takeLast(Gap gap) {
        seats.add(size - 1);
        if (gap.lo < size - 1) gaps.put(new Gap(gap.lo, size - 2), null);
        return size - 1;
    }

    public void leave(int p) {
        if (log.isDebugEnabled()) {
            log.debug("leave seat {}", p);
        }
        seats.remove(p);
        int head = p - 1;
        while (head >= 0 && !seats.contains(head)) head--;
        if (p - head > 1) gaps.remove(new Gap(head + 1, p - 1));
        int tail = p + 1;
        while (tail < size && !seats.contains(tail)) tail++;
        if (tail - p > 1) gaps.remove(new Gap(p + 1, tail - 1));
        gaps.put(new Gap(head + 1, tail - 1), null);
        if (log.isDebugEnabled()) {
            log.debug("new gap = [{},{}]", head + 1, tail - 1);
        }
    }

}
