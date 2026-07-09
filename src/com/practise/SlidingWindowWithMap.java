package com.practise;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowWithMap {

    public static void main(String[] args) {

        SlidingWindowWithMap sWMap = new SlidingWindowWithMap();
        long res = sWMap.maximumSubarraySum(new int[]{1, 5, 4, 2, 9, 9, 9}, 3);
        System.out.println(res);
    }


    public long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        long maxSum = 0;
        long currentSum = 0;

        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            currentSum += nums[i];
        }

        if (map.size() == k) {
            maxSum = currentSum;
        }


        int left = 0;
        for (int i = k; i < nums.length; i++) {
            int addingVal = nums[i];

            currentSum += addingVal;
            map.put(addingVal, map.getOrDefault(addingVal, 0) + 1);

            int removeNum = nums[left];
            currentSum -= removeNum;


            if (map.get(removeNum) - 1 == 0) {
                map.remove(removeNum);
            } else {
                map.put(removeNum, map.get(removeNum) - 1);
            }

            if (map.size() == k) {
                maxSum = Math.max(maxSum, currentSum);
            }

            left++;
        }
        return maxSum;

    }


}
