package com.leetcode;

public class Leetcode_3756 {

    public static void main(String[] args) {
        Leetcode_3756 l = new Leetcode_3756();
        l.sumAndMultiply("10203004", new int[][]{{0, 7}, {1, 3}, {4, 6}});
    }

    public int[] sumAndMultiply(String s, int[][] queries) {
        long MOD = 1_000_000_007l;
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long sum = 0l;
            long x = 0;
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                if (s.charAt(j) != '0') {
                    int digit = s.charAt(j) - '0';
                    x = (x * 10 + digit) % MOD;
                    sum += digit;
                }
            }
            res[i] = Math.toIntExact((x * sum) % MOD);
        }
        return res;
    }
}


