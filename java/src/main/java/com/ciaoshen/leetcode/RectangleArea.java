/**
 * Leetcode - Algorithm - Rectangle Area
 */
package com.ciaoshen.leetcode;
import java.util.*;

class RectangleArea {
    /**
     * 傻办法，一个个情况去比较
     */
    public class SolutionV1 {
        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            int leftA = A, upperA = D, bottomA = B, rightA = C;
            int leftB = E, upperB = H, bottomB = F, rightB = G;
            int heightA = upperA - bottomA, widthA = rightA - leftA;
            int heightB = upperB - bottomB, widthB = rightB - leftB;
            int areaA = heightA * widthA;
            // System.out.println("Height A = " + heightA + ", Width A = " + widthA + ",   Area A = " + areaA);
            int areaB = heightB * widthB;
            // System.out.println("Height B = " + heightB + ", Width B = " + widthB + ",   Area B = " + areaB);
            int totalArea = areaA + areaB;
            if (upperA <= bottomB || upperB <= bottomA || leftA >= rightB || leftB >= rightA) { return totalArea; } // 不重叠
            int overlappingHeight = 0;
            if (upperA >= upperB ) {
                if (bottomA >= bottomB) { // A上B下重叠
                    overlappingHeight = upperB - bottomA;
                    // System.out.println("A上B下重叠");
                } else { // B包含在A内
                    overlappingHeight = heightB;
                    // System.out.println("B包含在A内");
                }
            } else {
                if (bottomA <= bottomB) { // A下B上重叠
                    overlappingHeight = upperA - bottomB;
                    // System.out.println("A下B上重叠");
                } else { // A包含在B内
                    overlappingHeight = heightA;
                    // System.out.println("A包含在B内");
                }
            }
            // System.out.println("OverlappingHeight = " + overlappingHeight);
            int overlappingWidth = 0;
            if (leftA <= leftB) {
                if (rightA <= rightB) { // A左B右重叠
                    overlappingWidth = rightA - leftB;
                    // System.out.println("A左B右重叠");
                } else { // B包含在A内
                    overlappingWidth = widthB;
                    // System.out.println("B包含在A内");
                }
            } else {
                if (rightA >= rightB) { // A右B左重叠
                    overlappingWidth = rightB - leftA;
                    // System.out.println("A右B左重叠");
                } else { // A包含在B内
                    overlappingWidth = widthA;
                    // System.out.println("A包含在B内");
                }
            }
            // System.out.println("OverlappingWidth = " + overlappingWidth);
            // System.out.println("Overlapping area = " + (overlappingHeight * overlappingWidth));
            return totalArea - (overlappingHeight * overlappingWidth);
        }
    }
    /**
     * 利用 Math.max()和 Math.min()
     */
    public class Solution {
        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            int leftA = A, upperA = D, bottomA = B, rightA = C;
            int leftB = E, upperB = H, bottomB = F, rightB = G;
            int areaA = (upperA - bottomA) * (rightA - leftA);
            int areaB = (upperB - bottomB) * (rightB - leftB);
            int overLappingLeft = Math.max(leftA,leftB);
            int overLappingRight = Math.min(rightA,rightB);
            int overLappingUpper = Math.min(upperA,upperB);
            int overLappingBottom = Math.max(bottomA,bottomB);
            int overlappingHeight = (overLappingUpper > overLappingBottom)? (overLappingUpper - overLappingBottom) : 0;
            int overlappingWidth = (overLappingRight > overLappingLeft)? (overLappingRight - overLappingLeft) : 0;
            int areaOverlapping = overlappingHeight * overlappingWidth;
            return areaA + areaB - areaOverlapping;
        }
    }
    private class Test {
        private void callComputeArea(int[] nums, String answer) {
            System.out.println("For the rectangle [" + nums[0] + "," + nums[1] + "," + nums[2] + "," + nums[3] + "]");
            System.out.println("And the rectangle [" + nums[4] + "," + nums[5] + "," + nums[6] + "," + nums[7] + "]");
            System.out.println("Their total area is: " + solution.computeArea(nums[0],nums[1],nums[2],nums[3],nums[4],nums[5],nums[6],nums[7]) + "      [answer = 45]");
        }
        private void test() {
            int[] nums1 = new int[]{-3,0,3,4,0,-1,9,2};
            // int[] nums2 = new int[]{};
            // int[] nums3 = new int[]{};
            callComputeArea(nums1,"45");
            // callComputeArea(nums2);
            // callComputeArea(nums3);
        }
    }
    private static RectangleArea problem = new RectangleArea();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
