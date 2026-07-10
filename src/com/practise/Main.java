package com.practise;

import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        System.out.println(main.maxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));


        Map<Integer, String> map =
                new LinkedHashMap<>(16, 0.75f, true);
        for (int i = 0; i < 17; i++) {
            map.put(i, "A");
        }

        System.out.println(map);
        System.out.println(map.get(1));

        map.put(55, "A");


        System.out.println(map);
        map.put(6, "A");
        System.out.println(map);

    }


    public double maxAverage(int[] nums, int k) {
        int totalSum = 0;
        for (int i = 0; i < k; i++) {
            totalSum += nums[i];
        }

        double avg = totalSum / Double.valueOf(k);

        int left = 0;
        for (int right = k; right < nums.length; right++) {
            totalSum = totalSum - nums[left] + nums[right];
            double avgNew = totalSum / Double.valueOf(k);
            avg = Math.max(avg, avgNew);
            left++;
        }
        return avg;

    }


    public int maxOnesInWindow(int[] nums, int k) {
        int res = 0;
        int totalSum = 0;
        for (int i = 0; i < k; i++) {
            if (nums[i] >= 1) {
                res++;
            }
        }
        int sum = res;
        int left = 0;
        for (int right = k; right < nums.length; right++) {

            sum = (sum - nums[left]) + nums[right];
            res = Math.max(res, sum);
            left++;

        }
        return res;

    }


    public int countWindows(int[] nums, int k, int threshold) {

        int res = 0;
        int totalSum = 0;
        for (int i = 0; i < k; i++) {
            totalSum += nums[i];
        }
        if (totalSum >= k * threshold) {
            res++;
        }

        int left = 0;
        for (int right = k; right < nums.length; right++) {
            totalSum -= nums[left];
            totalSum += nums[right];
            if (totalSum >= k * threshold) {
                res++;
            }
            left++;
        }
        return res;
    }


    public boolean checkInclusion(String s1, String s2) {

        if (s2.length() < s1.length()) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] windowCount = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            windowCount[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(s1Count, windowCount)) {
            return true;
        }
        int left = 0;
        for (int right = s1.length(); right < s2.length(); right++) {
            windowCount[s2.charAt(left) - 'a']--;
            windowCount[s2.charAt(right) - 'a']++;

            if (Arrays.equals(s1Count, windowCount)) {
                return true;
            }
            left++;
        }

        return false;
    }


    public int FindAnagramsinString(String input, String ang) {
        if (input.length() < ang.length()) {
            return 0;
        }
        int res = 0;
        int[] angCount = new int[26];
        int[] windowCount = new int[26];

        for (int i = 0; i < ang.length(); i++) {
            angCount[ang.charAt(i) - 'a']++;
            windowCount[input.charAt(i) - 'a']++;
        }

        if (Arrays.equals(angCount, windowCount)) {
            res++;
        }

        int left = 0;
        for (int right = ang.length(); right < input.length(); right++) {
            windowCount[input.charAt(left) - 'a']--;
            windowCount[input.charAt(right) - 'a']++;


            if (Arrays.equals(angCount, windowCount)) {
                res++;
            }
            left++;
        }
        return res;
    }


    //pwwkew
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLength = 0;
        Set<Character> set = new HashSet<>();
        for (int rigth = 0; rigth < s.length(); rigth++) {

            char rightChar = s.charAt(rigth);

            while (set.contains(rightChar)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(rightChar);
            maxLength = Math.max(maxLength, rigth - left + 1);
        }

        return maxLength;
    }

    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }
        return false;
    }

    public int findVowels(String str, int windowSize) {
        int count = 0;
        for (int i = 0; i < windowSize; i++) {
            char ch = str.charAt(i);
            if (isVowel(ch)) {
                count++;
            }
        }
        int totalCount = count;
        int left = 0;
        for (int i = windowSize; i < str.length(); i++) {

            char leavingChar = str.charAt(left);

            if (isVowel(leavingChar)) {
                count--;
            }

            char addingChar = str.charAt(i);

            if (isVowel(addingChar)) {
                count++;
            }

            totalCount = Math.max(totalCount, count);

            left++;
        }
        return totalCount;

    }


    //2,1,5,1,3,2
    //0,1,2,3,4,5
    public int continuousSum(int[] arr, int k) {
        int left = 0;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int totalSum = sum;

        for (int right = k; right < arr.length; right++) {
            sum = sum - arr[left] + arr[right];
            totalSum = Math.max(totalSum, sum);
            left++;
        }
        return totalSum;

    }


    public int numberOfSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 3; j <= s.length(); j++) {
                String str = s.substring(i, j);
                if (str.contains("a") && str.contains("b") && str.contains("c")) {
                    count++;
                }
            }
        }
        return count;
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            List<String> out = new ArrayList<>();
            for (int j = i + 1; j < strs.length; j++) {
                if (isAnagram(strs[i], strs[j])) {
                    if (!(out.contains(strs[i]) || out.contains(strs[j]))) {
                        out.add(strs[i]);
                        out.add(strs[j]);
                    }
                }
            }
            res.add(out);
        }
        return res;

    }


    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        Arrays.sort(chs);
        Arrays.sort(cht);

        for (int i = 0; i < chs.length; i++) {
            if (chs[i] != cht[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    public boolean containsDuplicate(int[] nums) {

        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            if (seen.contains(n)) {
                return true;
            }
            seen.add(n);
        }
        return false;
    }


    public int containsDuplicateNum(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            if (seen.contains(n)) {
                return n;
            }
            seen.add(n);
        }
        return -1;
    }


    public int missingNumber(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i - 1] - nums[i]) != 1) {
                return nums[i - 1] + 1;
            }
        }
        return 0;

    }


}