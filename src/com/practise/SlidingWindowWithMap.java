package com.practise;

import java.util.*;

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


    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();

        for (int n : nums) {

            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }


    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        return (n * (n + 1) / 2) - sum;
    }


    public int[] twoSum(int[] nums, int target) {

        //sol2:
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];

            if (map.containsKey(val)) {
                return new int[]{map.get(val), i};

            }
            map.put(nums[i], i);

        }


        //soln1
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }

        }
        return new int[]{};
    }


}
