/**
 * Leetcode - asteroid_collision
 */
package com.ciaoshen.leetcode.asteroid_collision;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution2 implements Solution {

    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < asteroids.length; i++) {
            int asteroid = asteroids[i];
            if (asteroid > 0) { // move to right
                list.add(asteroid);
            } else { // move to left
                int opponent = 0;
                int absAsteroid = -asteroid;
                while (opponent < absAsteroid && !list.isEmpty() && list.getLast() > 0) {
                    opponent = list.pollLast();
                }
                if (opponent < absAsteroid) { // beats all previous right move asteroid
                    list.add(asteroid);
                } else if (opponent > absAsteroid) {
                    list.add(opponent); // one of previous right move asteroid beats current left move one
                }
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

}
