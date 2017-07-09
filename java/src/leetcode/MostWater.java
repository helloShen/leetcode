/**
 * Container with Most Water
 */
package com.ciaoshen.leetcode;
import java.util.*;
import java.lang.Math.*;

public class MostWater {
    public static int maxAreaV1(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j-i) * Math.min(height[i],height[j]);
                max = Math.max(max,area);
            }
        }
        return max;
    }
    public static int maxAreaV2(int[] height) {
        int maxArea = 0;
        int maxLower = 0;
        for (int size = height.length - 1; size > 0; size--) {
            int lower = 0;
            for (int i = 0, j = i + size; j < height.length; i++,j++) {
                int smaller = Math.min(height[i],height[j]);
                lower = Math.max(smaller,lower);
            }
            if (lower > maxLower) {
                int area = lower * size;
                maxArea = Math.max(maxArea,area);
                maxLower = lower;
            }
        }
        return maxArea;
    }

    public static int maxAreaV3(int[] height) {
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>();
        for (int i = 0; i < height.length; i++) {
            list.add(new AbstractMap.SimpleEntry<Integer,Integer>(i,height[i]));
        }
        list.sort(new Comparator<Map.Entry<Integer,Integer>>() {
            public int compare(Map.Entry<Integer,Integer> entry1, Map.Entry<Integer,Integer> entry2) {
                return Integer.compare(entry2.getValue(),entry1.getValue());
            }
        });
        // System.out.println(list);
        int theHeight = list.get(1).getValue();
        int num1 = list.get(0).getKey();
        int num2 = list.get(1).getKey();
        int[] theRange = new int[]{Math.min(num1,num2), Math.max(num1,num2)};
        int maxArea = theHeight * (theRange[1] - theRange[0]);
        for (int i = 2; i < list.size(); i++) {
            theHeight = list.get(i).getValue();
            int newRange = list.get(i).getKey();
            theRange[0] = Math.min(theRange[0],newRange);
            theRange[1] = Math.max(theRange[1],newRange);
            int newArea = theHeight * (theRange[1] - theRange[0]);
            maxArea = Math.max(maxArea,newArea);
        }
        return maxArea;
    }

    public static int maxArea(int[] height) {
        int left = 0, right = height.length-1, maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left],height[right]) * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    private static final int LEN = 20;
    private static final Random R = new Random();
    private static int[] height = new int[LEN];
    static {
        for (int i = 0; i < LEN; i++) {
            height[i] = R.nextInt(100);
        }
    }
    private static void testMaxArea() {
        System.out.println(Arrays.toString(height));
        System.out.println(maxAreaV1(height));
        System.out.println(maxAreaV2(height));
        System.out.println(maxAreaV3(height));
        System.out.println(maxArea(height));
    }
    public static void main(String[] args) {
        testMaxArea();
    }
}
