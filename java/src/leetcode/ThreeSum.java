/**
 * Leetcode 3 Sum
 */
package com.ciaoshen.leetcode;
import java.util.*;

// [-1, 0, 1, 2, -1, -4]
public class ThreeSum{
    public List<List<Integer>> threeSumV1(int[] nums) {
        List<List<Integer>> rlt = new ArrayList<>();
        if (nums == null || nums.length < 3) { return rlt; }
        forI:
        for (int i = 0; i < nums.length-2; i++) {
            forJ:
            for (int j = i+1; j < nums.length-1; j++) {
                forK:
                for (int k = j+1; k < nums.length; k++) {
                    if ((nums[i] + nums[j] + nums[k]) == 0) {
                        List<Integer> ele = new ArrayList<Integer>(Arrays.asList(new Integer[]{nums[i],nums[j],nums[k]}));
                        Collections.sort(ele);
                        innerFor:
                        for (List<Integer> list : rlt) {
                            if (list.equals(ele)) { continue forK; }
                        }
                        rlt.add(ele);
                    }
                }
            }
        }
        return rlt;
    }

    public List<List<Integer>> threeSumV2(int[] nums) {
        Set<List<Integer>> rlt = new HashSet<>();
        if (nums == null || nums.length < 3) { return new ArrayList<List<Integer>>(rlt); }
        Arrays.sort(nums); // 排序
        int firstPositive = firstPositive(nums); //找第一个大于等于0的数
        // 处理特殊情况
        if (firstPositive < nums.length-2 && nums[firstPositive] == 0 && nums[firstPositive+1] == 0 && nums[firstPositive+2] == 0) { // 存在0,0,0
            rlt.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0,0,0})));
        }
        if (firstPositive == 0 || firstPositive == nums.length) { // all >=0, or all <0
            return new ArrayList<List<Integer>>(rlt);
        }
        // ready,go!
        List<Integer> negative = new ArrayList<>();
        for (int i = 0; i < firstPositive; i++) {
            negative.add(nums[i]);
        }
        List<Integer> positive = new ArrayList<>();
        for (int i = firstPositive; i < nums.length; i++) {
            positive.add(nums[i]);
        }
        // searching by indexOf()
        int negaSize = negative.size();
        if (negaSize > 1) {
            for (int i = 0; i < negaSize-1; i++) {
                for (int j = i+1; j < negaSize; j++) {
                    int sum = negative.get(i) + negative.get(j);
                    int index = positive.indexOf(0-sum);
                    if (index != -1) { // find a new triplet
                        rlt.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{negative.get(i),negative.get(j),positive.get(index)})));
                    }
                }
            }
        }
        int posSize = positive.size();
        if (posSize > 1) {
            for (int i = 0; i < posSize-1; i++) {
                for (int j = i+1; j < posSize; j++) {
                    int sum = positive.get(i) + positive.get(j);
                    int index = negative.indexOf(0-sum);
                    if (index != -1) { // find a new triplet
                        rlt.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{positive.get(i),positive.get(j),negative.get(index)})));
                    }
                }
            }
        }
        return new ArrayList<List<Integer>>(rlt);
    }

    public List<List<Integer>> threeSumV3(int[] nums) {
        Set<List<Integer>> rlt = new HashSet<>();
        if (nums == null || nums.length < 3) { return new ArrayList<List<Integer>>(rlt); }
        Arrays.sort(nums); // 排序
        int firstPositive = firstPositive(nums); //找第一个大于等于0的数
        // 处理特殊情况
        if (firstPositive < nums.length-2 && nums[firstPositive] == 0 && nums[firstPositive+1] == 0 && nums[firstPositive+2] == 0) { // 存在0,0,0
            rlt.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0,0,0})));
        }
        if (firstPositive == 0 || firstPositive == nums.length) { // all >=0, or all <0
            return new ArrayList<List<Integer>>(rlt);
        }
        // ready,go!
        int negaSize = firstPositive;
        int posSize = nums.length - firstPositive;
        // searching by indexOf()
        if (negaSize > 1) {
            for (int i = 0; i < negaSize-1; i++) {
                for (int j = i+1; j < negaSize; j++) {
                    int sum = nums[i] + nums[j];
                    int index = indexOf(nums,firstPositive,nums.length-1,0-sum);
                    if (index != -1) { // find a new triplet
                        rlt.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{nums[i],nums[j],nums[index]})));
                    }
                }
            }
        }
        if (posSize > 1) {
            for (int i = firstPositive; i < nums.length-1; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    int sum = nums[i] + nums[j];
                    int index = indexOf(nums,0,firstPositive-1,0-sum);
                    if (index != -1) { // find a new triplet
                        rlt.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{nums[i],nums[j],nums[index]})));
                    }
                }
            }
        }
        return new ArrayList<List<Integer>>(rlt);
    }
    // return the index of the first num >= 0
    private int firstPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) { return i; }
        }
        return nums.length;
    }
    // 数组中二分查找一个数字（数组含左边界，含右边界）
    // return index. or -1 if not found.
    // 如果有多个数值相同，返回任意一个的index都可以。
    private int indexOf(int[] nums, int low, int high, int num) {
        if (low > high) { return -1; }
        int median = low + (high - low) / 2;
        if (nums[median] == num) {
            return median;
        } else if (nums[median] < num) {
            return indexOf(nums,median+1,high,num);
        } else { // nums[median] > num
            return indexOf(nums,low,median-1,num);
        }
    }
    public List<List<Integer>> threeSumV4(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums == null || nums.length < 3) { return new ArrayList<List<Integer>>(result); }
        Arrays.sort(nums);
        int firstPositive = firstPositive(nums);
        // 特殊情况：0,0,0
        if (firstPositive < nums.length - 2 && nums[firstPositive+2] == 0) {
            result.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{0,0,0})));
        }
        // 特殊情况：只有正数，或只有负数，没有解，直接返回
        if (firstPositive == 0 || firstPositive == nums.length) { return new ArrayList<List<Integer>>(result); }
        // begin to parse
        long target = 0; // 防止 Integer.MIN_VALUE = -2147483648 的相反数
        int low = -1, high = -1;
        int cursor = 0;
        while (cursor < nums.length) {
            target = Math.negateExact((long)nums[cursor]);
            if (target > 0) { // cursor指向一个负数
                low = firstPositive;
                high = nums.length - 1;
            } else { // cursor指向一个大于等于零的数
                low = 0;
                high = firstPositive - 1;
            }
            while (low < high) {
                long sum = (long)(nums[low] + nums[high]);
                if (sum <= target) {
                    if (sum == target) {
                        result.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{nums[cursor],nums[low],nums[high]})));
                    }
                    while (low+1 < high && nums[low+1] == nums[low]) { low++; } // skip duplicate numbers
                    low++;
                } else { // sum > target
                    while (high-1 > low  && nums[high-1] == nums[high]) { high--; } // skip duplicate numbers
                    high--;
                }
            }
            while (cursor+1 < firstPositive) {
                if (nums[cursor+1] == nums[cursor]) {
                    cursor++;
                } else {
                    break;
                }
            }
            cursor++;
        }
        return new ArrayList<List<Integer>>(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) { return result; }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int main = 0;
        while (main < nums.length-2) {
            if (nums[main] > 0) { break; }
            int low = main + 1;
            int high = nums.length-1;
            System.out.println("main=" + main + ", low=" + low + ", high=" + high);
            while ( low < high ) {
                long sum = (long)( nums[main] + nums[low] + nums[high] );
                if (sum == 0) {
                    result.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{nums[main],nums[low],nums[high]})));
                }
                if (sum <= 0) {
                    while (low+1 < high && nums[low] == nums[low+1]) { low++; }
                    low++;
                }
                if (sum >= 0) {
                    while (low < high-1 && nums[high] == nums[high-1]) { high--; }
                    high--;
                }
            }
            while (main+1 < nums.length-2 && nums[main] == nums[main+1]) { main++; }
            main++;
        }
        return result;
    }


    private static void testThreeSum() {
        ThreeSum ts = new ThreeSum();
        System.out.println(ts.threeSum(new int[] {-1,0,1,2,-1,-4}));
        System.out.println(ts.threeSum(new int[] {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}));
    }
    public static void main(String[] args) {
        testThreeSum();
    }
}
