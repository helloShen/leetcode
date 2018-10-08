/**
 * Leetcode - flood_fill
 */
package com.ciaoshen.leetcode.flood_fill;
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

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        height = image.length;
        if (height == 0) return image;
        width = image[0].length;
        oldC = image[sr][sc];
        if (oldC == newColor) return image;
        newC = newColor;
        imageRef = image;
        floodFillHelper(sr, sc);
        if (log.isDebugEnabled()) {
            log.debug("Image = ");
            for (int i = 0; i < height; i++) {
                log.debug("{}", Arrays.toString(image[i]));
            }
        }
        return image;
    }

    private int oldC, newC;
    private int height, width;
    private int[][] imageRef;

    private void floodFillHelper(int sr, int sc) {
        if (sr < 0 || sr >= height || sc < 0 || sc >= width) return;
        if (imageRef[sr][sc] == oldC) {
            imageRef[sr][sc] = newC;
            floodFillHelper(sr - 1, sc);
            floodFillHelper(sr + 1, sc);
            floodFillHelper(sr, sc - 1);
            floodFillHelper(sr, sc + 1);
        }
    }

}
