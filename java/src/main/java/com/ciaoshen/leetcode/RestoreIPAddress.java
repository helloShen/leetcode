/**
 * Leetcode - Algorithm - Restore IP Address
 */
package com.ciaoshen.leetcode;
import java.util.*;

class RestoreIPAddress {
    public List<String> restoreIpAddressesV1(String s) {
        Set<String> res = new HashSet<>();
        dfsV1(s,0,1,"",0,res);
        dfsV1(s,0,2,"",0,res);
        dfsV1(s,0,3,"",0,res);
        return new ArrayList<String>(res);
    }
    public void dfsV1(String s, int lo, int hi, String temp, int numSection, Set<String> res) { // lo inclusive, hi exclusive
        int length = s.length();
        if (numSection == 4) {
            if (lo == length) { res.add(temp.substring(1)); }
            return;
        }
        if (lo >= length || hi > length) { return; }
        if (s.charAt(lo) == '0' && hi - lo > 1) { return; } // can't be "001"
        int section = Integer.parseInt(s.substring(lo,hi));
        if (section <= 255) {
            temp = temp + "." + section;
            dfsV1(s,hi,hi+1,temp,numSection+1,res);
            dfsV1(s,hi,hi+2,temp,numSection+1,res);
            dfsV1(s,hi,hi+3,temp,numSection+1,res);
        }
    }
    public List<String> restoreIpAddressesV2(String s) {
        Set<String> res = new HashSet<>();
        List<Integer> ip = new ArrayList<>();
        backtracking(s,0,1,ip,res);
        backtracking(s,0,2,ip,res);
        backtracking(s,0,3,ip,res);
        return new ArrayList<String>(res);
    }
    public void backtracking(String s, int lo, int hi, List<Integer> ip, Set<String> res) { // lo inclusive, hi exclusive
        int length = s.length();
        if (ip.size() == 4) {
            if (lo == length) { res.add(toIp(ip)); }
            return;
        }
        if (lo >= length || hi > length) { return; }
        if (s.charAt(lo) == '0' && hi - lo > 1) { return; } // can't be "001"
        int section = Integer.parseInt(s.substring(lo,hi));
        if (section <= 255) {
            ip.add(section);
            backtracking(s,hi,hi+1,ip,res);
            backtracking(s,hi,hi+2,ip,res);
            backtracking(s,hi,hi+3,ip,res);
            ip.remove(ip.size()-1);
        }
    }
    public String toIp(List<Integer> ip) {
        StringBuilder sb = new StringBuilder();
        for (int i : ip) {
            sb.append("." + i);
        }
        return sb.toString().substring(1);
    }
    public List<String> restoreIpAddressesV3(String s) {
        Set<String> res = new HashSet<>();
        dfs(s,0,1,"",0,res);
        dfs(s,0,2,"",0,res);
        dfs(s,0,3,"",0,res);
        return new ArrayList<String>(res);
    }
    public void dfs(String s, int lo, int hi, String ip, int count, Set<String> res) {
        int length = s.length();
        if (count == 4) {
            if (lo == length) { res.add(ip.substring(1)); }
            return;
        }
        if (lo >= length || hi > length) { return; }
        String section = s.substring(lo,hi);
        if (isValideV1(section)) {
            ip = ip + "." + section;
            dfs(s,hi,hi+1,ip,count+1,res);
            dfs(s,hi,hi+2,ip,count+1,res);
            dfs(s,hi,hi+3,ip,count+1,res);
        }
    }
    public boolean isValideV1(String s) {
        System.out.println(s);
        int length = s.length();
        if (s.charAt(0) == '0') { // can't be 001
            return length == 1;
        }
        return Integer.parseInt(s) < 256;
    }
    public List<String> restoreIpAddressesV4(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        if (len < 4) { return res; }
        iLoop:
        for (int i = 1; i < len - 2; i++) {
            jLoop:
            for (int j = i + 1; j < len - 1; j++) {
                kLoop:
                for (int k = j + 1; k < len; k++) {
                    String one = s.substring(0,i), two = s.substring(i,j), three = s.substring(j,k), four = s.substring(k,len);
                    boolean oneValide = isValide(one), twoValide = isValide(two), threeValide = isValide(three), fourValide = isValide(four);
                    if (!oneValide) { break iLoop; }
                    if (!twoValide) { break jLoop; }
                    if (!threeValide) { break kLoop; }
                    if (!fourValide) { continue; }
                    res.add(one + "." + two + "." + three + "." + four);
                }
            }
        }
        return res;
    }
    public boolean isValide(String s) {
        int length = s.length();
        if (length > 3) { return false; }
        if (s.charAt(0) == '0') { return length == 1; }
        int num = Integer.parseInt(s);
        return num < 256;
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        if (len < 4) { return res; }
        for (int i = 1; i < 4 && i < len-2; i++) {
            for (int j = i + 1; j < i+4 && j < len-1; j++) {
                for (int k = j + 1; k < j+4 && k < len; k++) {
                    String one = s.substring(0,i), two = s.substring(i,j), three = s.substring(j,k), four = s.substring(k,len);
                    if (isValide(one) && isValide(two) && isValide(three) && isValide(four)) {
                        res.add(one + "." + two + "." + three + "." + four);
                    }
                }
            }
        }
        return res;
    }
    private static RestoreIPAddress test = new RestoreIPAddress();
    private static void testRestoreIPAddress() {
        String[] strs = new String[]{"25525511135","10101010","111","1111","2552552552552"};
        for (String str : strs) {
            System.out.println(str + " get IP: " + test.restoreIpAddresses(str));
        }
    }
    public static void main(String[] args) {
        testRestoreIPAddress();
    }
}
