/**
 * Leetcode - asteroid_collision
 */
package com.ciaoshen.leetcode.asteroid_collision;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> moveRight = new LinkedList<>();
        List<Integer> moveLeft = new ArrayList<>();
        for (int i = 0; i < asteroids.length; i++) {
            int asteroid = asteroids[i];
            if (asteroid > 0) { // move to right
                moveRight.add(asteroid);
            } else { // move to left
                int opponent = 0;
                int absAsteroid = -asteroid;
                while (opponent < absAsteroid && !moveRight.isEmpty()) {
                    opponent = moveRight.pollLast();
                }
                if (opponent > absAsteroid) { // this asteroid to left explods
                    moveRight.add(opponent);
                } else if (opponent < absAsteroid) { // this asteroid distroy all previous right move asteroids and free now
                    moveLeft.add(asteroid);
                }
            }
        }
        int[] res = new int[moveLeft.size() + moveRight.size()];
        int resP = 0;
        for (Integer n : moveLeft) {
            res[resP++] = n;
        }
        for (Integer n : moveRight) {
            res[resP++] = n;
        }
        return res;
    }

}
