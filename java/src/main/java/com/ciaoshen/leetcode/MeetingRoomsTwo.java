/**
 * Leetcode - Algorithm - MeetingRoomsTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MeetingRoomsTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MeetingRoomsTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
        register(new Solution6());
        register(new Solution7());
        register(new Solution8());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int minMeetingRooms(Interval[] intervals);
    }
    /**
     * 不排序，不行！因为不排序直接用贪婪算法，无法找到浪费最小的安排法。会议之间会有比较大的时间空隙。
     * [[5,10],[1,3],[2,5],[9,12],[10,14],[3,9]]会被安排在3个房间：
     *          room#1: [[1,3],[5,10],[10,14]]
     *          room#2: [[2,5],[9,12]
     *          room#3: [[3,9]]
     * 实际上只需要2个房间就够了：
     *          room#1: [[1,3],[3,9],[9,12]]
     *          room#2: [2,5],[5,10],[10,14]]
     */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int minMeetingRooms(Interval[] intervals) {
            List<Interval> list = new LinkedList<>(Arrays.asList(intervals));
            int count = 0;
            while (!list.isEmpty()) {
                List<Interval> group = new ArrayList<>();
                group.add(list.remove(0)); count++;
                // System.out.println("Header " + group.get(0) + " is removed!");
                Iterator<Interval> ite = list.iterator();
                while (ite.hasNext()) {
                    Interval noGroup = ite.next();
                    boolean conflict = false;
                    for (Interval inGroup : group) {
                        if (isConflict(inGroup,noGroup)) { conflict = true; break; }
                    }
                    if (!conflict) {
                        group.add(noGroup);
                        ite.remove();
                        // System.out.println(noGroup + " removed!");
                    }
                }
                System.out.println("Group#" + count + ": " + group);
            }
            return count;
        }
        private boolean isConflict(Interval a, Interval b) {
            boolean abfb = (a.start < b.start && a.end <= b.start);
            boolean aaftb = (b.start < a.start && b.end <= a.start);
            return !(abfb || aaftb);
        }

        // {
        //     Interval i1 = new Interval(8,9);
        //     Interval i2 = new Interval(8,9);
        //     System.out.println(i1 + " & " + i2 + " is conflict?" + isConflict(i1,i2));
        // }
    }
    /**
     * 完整捋出每个room的完整时间表
     */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public int minMeetingRooms(Interval[] intervals) {
            Arrays.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval a, Interval b) {
                    return a.start - b.start;
                }
            });
            List<Interval> list = new ArrayList<>(Arrays.asList(intervals));
            int count = 0;
            while (!list.isEmpty()) {
                int end = list.remove(0).end; count++; // create new timeline
                Iterator<Interval> ite = list.iterator();
                while (ite.hasNext()) {
                    Interval next = ite.next();
                    if (next.start >= end) { // add to current timeline
                        end = next.end;
                        ite.remove();
                    }
                }
            }
            return count;
        }
    }
    /**
     * 完整捋出room的整个时间表。把时间表打出来给你看。
     */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public int minMeetingRooms(Interval[] intervals) {
            Arrays.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval a, Interval b) {
                    return a.start - b.start;
                }
            });
            List<Interval> list = new ArrayList<>(Arrays.asList(intervals));
            int count = 0;
            while (!list.isEmpty()) {
                List<Interval> room = new ArrayList<>();
                Interval firstMeeting = list.remove(0);
                room.add(firstMeeting); count++; // create new timeline
                int end = firstMeeting.end;
                Iterator<Interval> ite = list.iterator();
                while (ite.hasNext()) {
                    Interval nextMeeting = ite.next();
                    if (nextMeeting.start >= end) { // add this meeting into the schedule of this room
                        end = nextMeeting.end; // update the available time of this room
                        room.add(nextMeeting);
                        ite.remove();
                    }
                }
                System.out.println("Group#" + count + ": " + room);
            }
            return count;
        }
    }
    // you can expand more solutions HERE if you want...
    /**
     * 不用容器，直接在数组上完成。（但是会破坏参数数组）
     */
    private class Solution4 extends Solution {
        { super.id = 4; }

        public int minMeetingRooms(Interval[] intervals) {
            Arrays.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval a, Interval b) {
                    return a.start - b.start;
                }
            });
            int count = 0;
            for (int i = 0; i < intervals.length; i++) {
                Interval ii = intervals[i];
                if (ii == null) { continue; }
                int end = ii.end;
                intervals[i] = null; count++;
                for (int j = i+1; j < intervals.length; j++) {
                    Interval ij = intervals[j];
                    if (intervals[j] == null) { continue; }
                    if (ij.start >= end) {
                        end = ij.end;
                        intervals[j] = null;
                    }
                }
            }
            return count;
        }
    }

    /**
     * Using PriorityQueue to simulate the occupy rooms.
     */
    private class Solution5 extends Solution {
        { super.id = 5; }

        public int minMeetingRooms(Interval[] intervals) {
            if (intervals.length == 0) { return 0; }
            Arrays.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval a, Interval b) { return a.start - b.start; }
            });
            PriorityQueue<Integer> rooms = new PriorityQueue<>(intervals.length);
            for (Interval meeting : intervals) {
                Integer nextRoom = rooms.poll(); // give the room to be available soon
                if (nextRoom == null) { rooms.add(meeting.end); continue; }
                if (nextRoom > meeting.start) { rooms.offer(nextRoom); } // can not use this room
                rooms.offer(meeting.end);
            }
            return rooms.size();
        }
    }

    /**
     * Lazy Releasing Rooms
     */
    private class Solution6 extends Solution {
        { super.id = 6; }

        public int minMeetingRooms(Interval[] intervals) {
            int len = intervals.length;
            if (len == 0) { return 0; }
            int[] start = new int[len];
            int[] end = new int[len];
            for (int i = 0; i < len; i++) {
                start[i] = intervals[i].start;
                end[i] = intervals[i].end;
            }
            Arrays.sort(start);
            Arrays.sort(end);
            int rooms = 0;
            for (int i = 0, j = 0; i < len; i++) {
                if (start[i] < end[j]) {
                    rooms++;
                } else {
                    j++;
                }
            }
            return rooms;
        }
    }


    private class Solution7 extends Solution {
        { super.id = 7; }

        public int minMeetingRooms(Interval[] intervals) {
            // get max array size
            int size = 0;
            for (Interval meeting : intervals) { // need a big array of size [max value in intervals]
                size = Math.max(size,meeting.end);
            }
            // accumulate informations
            int[] start = new int[size+1];
            int[] end = new int[size+1];
            for (Interval meeting : intervals) {
                start[meeting.start]++;
                end[meeting.end]--;
            }
            // collect result
            int curr = 0, max = 0;
            for (int i = 0; i <= size; i++) {
                // end[i] rooms released at time (i), then start[i] new meetings ask for rooms
                curr += start[i]; curr += end[i];
                max = Math.max(max, curr);
            }
            return max;
        }
    }

    private class Solution8 extends Solution {
        { super.id = 8; }

        public int minMeetingRooms(Interval[] intervals) {
            // get max size
            int size = 0;
            for (Interval meeting : intervals) {
                size = Math.max(size,meeting.end);
            }
            // accumulate informations
            int count[] = new int[size+1];
            for (Interval meeting : intervals) {
                count[meeting.start]++;
                count[meeting.end]--;
            }
            // get result
            int max = 0, curr = 0;
            for (int i = 0; i <= size; i++) {
                curr += count[i];
                max = Math.max(max,curr);
            }
            return max;
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
        private MeetingRoomsTwo problem = new MeetingRoomsTwo();
        private Solution solution = null;

        private void call(Interval[] intervals, String answer) {
            // System.out.println("For intervals: " + Arrays.toString(intervals));
            System.out.println("We need at least " + solution.minMeetingRooms(intervals) + " meeting rooms!         answer[" + answer + "]");
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            // test case
            int[][] matrix1 = new int[][]{{0,30},{5,10},{15,20}};
            Interval[] intervals1 = Interval.intervals(matrix1);
            int[][] matrix2 = new int[][]{{5,10},{15,30},{50,100}};
            Interval[] intervals2 = Interval.intervals(matrix2);
            int[][] matrix3 = new int[][]{{1,17},{7,10},{12,14}};
            Interval[] intervals3 = Interval.intervals(matrix3);
            int[][] matrix4 = new int[][]{{1,5},{8,9},{8,9}};
            Interval[] intervals4 = Interval.intervals(matrix4);
            // int[][] matrix5 = new int[][]{{64738,614406},{211748,780229},{208641,307338},{499908,869489},{218907,889449},{177201,481150},{123679,384415},{120440,404695},{191810,491295},{800783,826206},{165175,221995},{420412,799259},{484168,617671},{746410,886281},{765198,792311},{493853,971285},{194579,313372},{119757,766274},{101315,917883},{557309,599256},{167729,723580},{731216,988021},{225883,752657},{588461,854166},{231328,285640},{772811,869625},{892212,973218},{143535,306402},{336799,998119},{65892,767719},{380440,518918},{321447,558462},{54489,234291},{43934,44986},{11260,968186},{248987,707178},{355162,798511},{661962,781083},{149228,412762},{71084,953153},{44890,655659},{708781,956341},{251847,707658},{650743,932826},{561965,814428},{697026,932724},{583473,919161},{463638,951519},{769086,785893},{17912,923570},{423089,653531},{317269,395886},{412117,701471},{465312,520002},{168739,770178},{624091,814316},{143729,249836},{699196,879379},{585322,989087},{501009,949840},{424092,580498},{282845,345477},{453883,926476},{392148,878695},{471772,771547},{339375,590100},{110499,619323},{8713,291093},{268241,283247},{160815,621452},{168922,810532},{355051,377247},{10461,488835},{220598,261326},{403537,438947},{221492,640708},{114702,926457},{166567,477230},{856127,882961},{218411,256327},{184364,909088},{130802,828793},{312028,811716},{294638,839683},{269329,343517},{167968,391811},{25351,369583},{210660,454598},{166834,576380},{296440,873280},{660142,822072},{33441,778393},{456500,955635},{59220,954158},{306295,429913},{110402,448322},{44523,88192},{231386,353197},{120940,902781},{348758,597599},{329467,664450},{208411,890114},{230238,516885},{434113,602358},{349759,419831},{10689,308144},{94526,180723},{435280,986655},{611999,690154},{75208,395348},{403243,489260},{498884,611075},{487209,863242},{13900,873774},{656706,782943},{53478,586952},{226216,723114},{554799,922759},{467777,689913},{80630,147482},{277803,506346},{532240,976029},{206622,761192},{148277,985819},{10879,807349},{952227,971268},{172074,919866},{239230,384499},{607687,984661},{4405,264532},{41071,437502},{432603,661142},{144398,907360},{139605,360037},{943191,997317},{12894,171584},{382477,800157},{452077,518175},{208007,398880},{375250,489928},{384503,726837},{278181,628759},{114470,635575},{382297,733713},{156559,874172},{507016,815520},{164461,532215},{17332,536971},{418721,911117},{11497,14032}};
            // Interval[] intervals5 = Interval.intervals(matrix5);
            int[][] matrix6 = new int[][]{{5,10},{1,3},{2,5},{9,12},{10,14},{3,9}};
            Interval[] intervals6 = Interval.intervals(matrix6);

            // call solution
            call(intervals1,"2");
            call(intervals2,"1");
            call(intervals3,"2");
            call(intervals4,"2");
            // call(intervals5,"77");
            call(intervals6,"2");
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
        // test.test(7);
        test.test(8);
    }
}
