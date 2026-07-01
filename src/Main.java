import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        System.out.println(
//                main.isAnagram("anagram","nagaram"));


//        int[] out = main.twoSum(new int[]{2, 7, 11, 15}, 9);
//
//        for (int n : out) {
//            System.out.println(n);
//        }

        System.out.println(main.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

        main.FindAnagramsinString("cbaebabacd", "abc");
    }

    public boolean isAnagramCheck(String input, String ang) {

        for (int i = 0; i < ang.length(); i++) {
            if (!input.contains(ang.charAt(i) + "")) {
                return false;
            }
        }
        return true;
    }


    public int FindAnagramsinString(String input, String ang) {
        int res = 0;
        int left = 0;

        for (int right = ang.length(); right < input.length(); right++) {

            int totalLength = right - left;
            String sbStr = input.substring(left, totalLength);
            if (isAnagram(sbStr, ang)) {
                left++;
                res++;
            }

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