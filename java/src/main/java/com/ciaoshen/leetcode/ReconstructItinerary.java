/**
 * Leetcode - Algorithm - ReconstructItinerary
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ReconstructItinerary implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ReconstructItinerary() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<String> findItinerary(String[][] tickets); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public List<String> findItinerary(String[][] tickets) {
            Map<String,List<String>> map = getMap(tickets);
            return dfs(map,"JFK");
        }
        private Map<String,List<String>> getMap(String[][] tickets) {
            Map<String,List<String>> map = new HashMap<>();
            for (String[] ticket : tickets) {
                if (map.containsKey(ticket[0])) {
                    map.get(ticket[0]).add(ticket[1]);
                } else {
                    map.put(ticket[0],new ArrayList<String>(Arrays.asList(new String[]{ticket[1]})));
                }
            }
            for (Map.Entry<String,List<String>> entry : map.entrySet()) {
                Collections.sort(entry.getValue());
            }
            return map;
        }
        private List<String> dfs(Map<String,List<String>> map, String from) {
            if (map.isEmpty()) {
                // System.out.println("Find a possible itinerary, end with " + from + "!");
                return new ArrayList<String>(Arrays.asList(new String[]{from}));
            }
            List<String> destinies = map.get(from);
            if (destinies == null) { return null; }
            for (int i = 0; i < destinies.size(); i++) {
                String to = destinies.remove(i);
                if (destinies.isEmpty()) {
                    map.remove(from);
                }
                List<String> subItinerary = dfs(map,to);
                if (subItinerary != null) {
                    subItinerary.add(0,from);
                    return subItinerary;
                }
                if (destinies.isEmpty()) {
                    map.put(from,destinies);
                }
                destinies.add(i,to);
            }
            return null;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public List<String> findItinerary(String[][] tickets) {
            return new ArrayList<String>();
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public List<String> findItinerary(String[][] tickets) {
            return new ArrayList<String>();
        }
    }
    // you can expand more solutions HERE if you want...


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
        private ReconstructItinerary problem = new ReconstructItinerary();
        private Solution solution = null;

        private void call(String[][] tickets, String[] ans) {
            Matrix.print(tickets);
            System.out.println(solution.findItinerary(tickets));
            System.out.println(Arrays.toString(ans) + "\n\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String[][] tickets1 = new String[][]{
                {"MUC", "LHR"},
                {"JFK", "MUC"},
                {"SFO", "SJC"},
                {"LHR", "SFO"}
            };
            String[] ans1 = new String[]{"JFK", "MUC", "LHR", "SFO", "SJC"};
            String[][] tickets2 = new String[][]{
                {"JFK","SFO"},
                {"JFK","ATL"},
                {"SFO","ATL"},
                {"ATL","JFK"},
                {"ATL","SFO"}
            };
            String[] ans2 = new String[]{"JFK","ATL","JFK","SFO","ATL","SFO"};
            String[][] tickets3 = new String[][]{
                {"JFK","KUL"},
                {"JFK","NRT"},
                {"NRT","JFK"}
            };
            String[] ans3 = new String[]{"JFK","NRT","JFK","KUL"};

            /** involk call() method HERE */
            call(tickets1,ans1);
            call(tickets2,ans2);
            call(tickets3,ans3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
