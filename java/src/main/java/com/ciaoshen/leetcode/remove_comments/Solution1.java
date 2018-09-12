/**
 * Leetcode - remove_comments
 */
package com.ciaoshen.leetcode.remove_comments;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public List<String> removeComments(String[] source) {
        List<String> list = new ArrayList<>();        
        boolean inComment = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length; i++) {
            char[] line = source[i].toCharArray();
            for (int j = 0; j < line.length; j++) {
                char c = line[j];
                if (inComment) {
                    if (c == '*' && (j + 1 < line.length) && line[j + 1] == '/') {
                        inComment = !inComment;
                        j++;
                    } else {
                        continue;
                    }
                } else { // not in comment
                    if (c == '/' && (j + 1 < line.length)) {
                        if (line[j + 1] == '/') { // comment in line
                            break;
                        } else if (line[j + 1] == '*') {
                            inComment = !inComment;
                            j++;
                        } else {
                            sb.append(c);
                        }
                    } else {
                        sb.append(c);
                    }
                }
            }
            if (!inComment && sb.length() > 0) {
                list.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }
        return list;
    }

}
