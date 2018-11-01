/**
 * Leetcode - validate_ip_adress
 */
package com.ciaoshen.leetcode.validate_ip_adress;
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

    public String validIPAddress(String IP) {
        if (IP.contains(".")) return (isValidIP4(IP))? "IPv4" : "Neither";
        if (IP.contains(":")) return (isValidIP6(IP))? "IPv6" : "Neither";
        return "Neither";
    }

    private boolean isValidIP4(String IP) {
        String[] segments = IP.split("\\.");
        if (segments.length != 4 || IP.charAt(IP.length() - 1) == '.') return false;
        for (String seg : segments) {
            if (seg.length() == 0) return false;
            char c0 = seg.charAt(0);
            if (c0 == '+' || c0 == '-' || (c0 == '0' && (!seg.equals("0")))) return false;
            try {
                int num = Integer.parseInt(seg);
                if (num < 0 || num > 255) return false;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidIP6(String IP) {
        int segLen = 0, segCount = 0;
        for (int i = 0; i < IP.length(); i++) {
            char c = IP.charAt(i);
            if (!isValidIP6Char(c)) return false;
            if (c == ':') {
                if (segLen == 0) return false;
                segLen = 0;
                segCount++;
                continue;
            }
            if (++segLen > 4) return false;
        }
        return segCount == 7 && segLen > 0;
    }

    private boolean isValidIP6Char(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F') || (c == ':');
    }

}
