package com.leetcode;

public class Leectcode_3532 {
    public static void main(String[] args) {
        Leectcode_3532 le = new Leectcode_3532();
//        le.pathExistenceQueries(2, new int[]{1, 3}, 1, new int[][]{{0, 0}, {0, 1}});

        boolean[] res = le.pathExistenceQueries(4, new int[]{2, 5, 6, 8}, 2, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}});

        for (boolean b : res) {
            System.out.println(b);
        }

    }

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] component = new int[n];

        int group = 0;
        component[0] = group;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                group++;
            }

            component[i] = group;
        }

        boolean[] res = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            res[i] = component[u] == component[v];
        }

        return res;
    }
}
