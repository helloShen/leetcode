/**
 * Merege intervals
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MergeIntervals {

    public List<Interval> mergeV1(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.isEmpty()) { return res; }
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval first, Interval second) {
                if (first.start < second.start) {
                    return -1;
                } else if (first.start > second.start) {
                    return 1;
                } else { // first.start == second.start
                    return 0;
                }
            }
        });
        Interval pool = intervals.get(0);
        for (Interval next : intervals) {
            if (isOverlapping(pool,next)) {
                pool.start = Math.min(pool.start,next.start);
                pool.end = Math.max(pool.end,next.end);
                System.out.println("Merge! Start=" + pool.start + ", End=" + pool.end);
            } else {
                res.add(pool);
                pool = next;
            }
        }
        res.add(pool);
        return res;
    }
    private boolean isOverlapping(Interval pool, Interval next) {
        if (pool.end < next.start || pool.start > next.end) { return false; }
        return true;
    }
    private static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
    public List<Interval> mergeV2(List<Interval> intervals) {
            List<Interval> res = new ArrayList<>();
            if (intervals.isEmpty()) { return res; }
            Collections.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval first, Interval second) {
                    return first.start - second.start;
                }
            });
            Interval pool = intervals.get(0);
            for (Interval next : intervals) {
                if (next.start > pool.end) {
                    res.add(pool);
                    pool = next;
                } else {
                    pool.end = Math.max(pool.end,next.end);
                }
            }
            res.add(pool);
            return res;
    }
    public List<Interval> merge(List<Interval> intervals) {
            List<Interval> res = new ArrayList<>();
            if (intervals.isEmpty()) { return res; }
            Collections.sort(intervals, (Interval first, Interval second) -> first.start - second.start);
            Interval pool = intervals.get(0);
            for (Interval next : intervals) {
                if (next.start > pool.end) {
                    res.add(pool);
                    pool = next;
                } else {
                    pool.end = Math.max(pool.end,next.end);
                }
            }
            res.add(pool);
            return res;
    }

    private static MergeIntervals test = new MergeIntervals();
    private static List<Interval> list1 = new ArrayList<>(Arrays.asList(new Interval[]{
        new Interval(1,3), new Interval(2,6), new Interval(8,10), new Interval(15,18)
    }));
    private static List<Interval> list2 = new ArrayList<>(Arrays.asList(new Interval[]{
        new Interval(1,4), new Interval(0,5)
    }));
    private static List<Interval> list3 = new ArrayList<>(Arrays.asList(new Interval[]{
        new Interval(2,3), new Interval(4,5), new Interval(6,7), new Interval(8,9), new Interval(1,10)
    }));
    private static void testMerge() {
        System.out.println("Before Merge: " + list1);
        System.out.println("After Merge: " + test.merge(list1));
        System.out.println("Before Merge: " + list2);
        System.out.println("After Merge: " + test.merge(list2));
        System.out.println("Before Merge: " + list3);
        System.out.println("After Merge: " + test.merge(list3));
    }
    public static void main(String[] args) {
        testMerge();
    }
}
