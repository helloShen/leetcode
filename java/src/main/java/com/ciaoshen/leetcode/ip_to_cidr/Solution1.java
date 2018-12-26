/**
 * Leetcode - ip_to_cidr
 */
package com.ciaoshen.leetcode.ip_to_cidr;
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

    private static int[] table = new int[]{1045, 1044, 1043, 1042, 1041, 1040, 1039, 1038, 1037, 1036, 1035, 1034, 1033, 1032, 1031, 1030, 1029, 1028, 1027, 1026, 1025, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};

    public List<String> ipToCIDR(String ip, int n) {
        List<String> res = new ArrayList<>();
        char[] ca = toBinaryIP(ip);
        if (log.isDebugEnabled()) {
            log.debug("initially get ip char array = {}", new String(ca));
        }
        int idx = 31;
        while (idx > 24 && ca[idx] == '0') idx--;
        while (n > table[idx]) {
            // if (log.isDebugEnabled()) {
            //     log.debug("n = {}, idx = {}, idxNum = {}, do greater() method.", n, idx, table[idx]);
            // }
            res.add(getCIDR(ca, idx + 1));
            // if (log.isDebugEnabled()) {
            //     log.debug("find range: {}", getCIDR(ca, idx + 1));
            // }
            n -= table[idx];
            while (idx > 0 && ca[idx] == '1') ca[idx--] = '0';
            ca[idx] = '1';
            // if (log.isDebugEnabled()) {
            //     log.debug("n becomes {}", n);
            // }
        }
        while (n > 0) {
            idx = idxOf(n);
            // if (log.isDebugEnabled()) {
            //     log.debug("n = {}, idx = {}, idxNum = {}, do smaller() method.", n, idx, table[idx]);
            //     log.debug("in smaller() method ip char array = {}", Arrays.toString(ca));
            // }
            res.add(getCIDR(ca, idx + 1));
            // if (log.isDebugEnabled()) {
            //     log.debug("find range: {}", getCIDR(ca, idx + 1));
            // }
            ca[idx] = '1';
            n -= table[idx];
        }
        return res;
    }

    private char[] toBinaryIP(String ip) {
        char[] cs = new char[32];
        // if (log.isDebugEnabled()) {
        //     log.debug("ip string = {}", ip);
        //     log.debug("ip char array = {}", Arrays.toString(cs));
        // }
        String[] segs = ip.split("\\.");
        // if (log.isDebugEnabled()) {
        //     log.debug("split() method works! segs[] length = {}", segs.length);
        // }
        // if (log.isDebugEnabled()) {
        //     for (String seg : segs) {
        //         log.debug("split ip seg = {}", seg);
        //     }
        // }
        int p = 0;
        for (String seg : segs) {
            int segNum = Integer.parseInt(seg);
            int mask = 128;
            for (int i = 0; i < 8; i++) {
                if (segNum >= mask) {
                    cs[p++] = '1';
                    segNum -= mask;
                } else {
                    cs[p++] = '0';
                }
                mask >>= 1;
            }
            // if (log.isDebugEnabled()) {
            //     log.debug("ip char array = {}", Arrays.toString(cs));
            // }
        }
        return cs;
    }

    private String toDecimalIP(char[] ip) {
        // if (log.isDebugEnabled()) {
        //     log.debug("toDecimalIP() method get ip char array = {}", Arrays.toString(ip));
        // }
        StringBuilder sb = new StringBuilder();
        int p = 0;
        for (int i = 0; i < 4; i++) {
            int seg = 0, mask = 128;
            for (int j = 0; j < 8; j++) {
                if (ip[p++] == '1') seg += mask;
                mask >>= 1;
            }
            // if (log.isDebugEnabled()) {
            //     log.debug("seg = {}", seg);
            // }
            sb.append(String.valueOf(seg)).append(".");
        }
        sb.deleteCharAt(sb.length() - 1);
        // if (log.isDebugEnabled()) {
        //     log.debug("get ip = {}", sb.toString());
        // }
        return sb.toString();
    }

    private String getCIDR(char[] ip, int mask) {
        return toDecimalIP(ip) + "/" + String.valueOf(mask);
    }

    private int idxOf(int n) {
        for (int i = 31; i >= 0; i--) {
            if (table[i] > n) return i + 1;
        }
        return -1;
    }

}
