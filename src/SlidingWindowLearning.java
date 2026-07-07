import java.util.*;

public class SlidingWindowLearning {
    public static void main(String[] args) {
        SlidingWindowLearning slidingWindowLearning = new SlidingWindowLearning();
//        slidingWindowLearning.countGoodSubstrings("xyzzaz");
//        int[] ints = slidingWindowLearning.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
//        for (int i : ints) {
//            System.out.println(i);
//        }
//        int res = slidingWindowLearning.maxDistinctInWindow(new int[]{1, 2, 1, 3, 4, 2, 3}, 3);
//        System.out.println(res);

        System.out.println(slidingWindowLearning.maximumSubarraySum(new int[]{1, 5, 4, 2, 9, 9, 9}, 3));

    }

    //need to rewrite it
    public long maximumSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            maxSum += nums[i];
        }
        if (map.size() != k) {
            maxSum = 0;
        }
        int left = 0;
        int currentSum = 0;
        for (int i = k; i < nums.length; i++) {


            if (map.get(nums[left]) - 1 == 0) {
                maxSum += nums[i];
                map.remove(nums[left]);
                maxSum -= nums[left];
            } else {
                map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
                currentSum = maxSum;
                maxSum = 0;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            maxSum = Math.max(currentSum, maxSum);
            left++;
        }
        return maxSum;
    }


    public int countGoodSubstrings(String s) {
        if (s.length() < 3) {
            return 0;
        }

        int fixedWindow = 3;
        Map<Character, Integer> map = new HashMap<>();
        int unique = 0;
        for (int i = 0; i < fixedWindow; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        if (fixedWindow == map.size()) {
            unique++;
        }

        int left = 0;
        for (int i = fixedWindow; i < s.length(); i++) {
            char rightChar = s.charAt(i);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
            char removeCharFromLeft = s.charAt(left);
            if (map.get(removeCharFromLeft) - 1 == 0) {
                map.remove(removeCharFromLeft);
            } else {
                map.put(removeCharFromLeft, map.get(removeCharFromLeft) - 1);
            }
            if (map.size() == fixedWindow) {
                unique++;
            }
            left++;
        }

        return unique;
    }


    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || k <= 0 || k > nums.length) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        int resultIndex = 0;

        for (int right = 0; right < nums.length; right++) {

            // Remove smaller values from the BACK
            while (!deque.isEmpty()
                    && nums[deque.peekLast()] <= nums[right]) {

                deque.pollLast();
            }

            // Add current index
            deque.offerLast(right);

            // Window start position
            int left = right - k + 1;

            // Only start storing answers once window size becomes k
            if (left >= 0) {

                // Remove index if it has left the window
                if (deque.peekFirst() < left) {
                    deque.pollFirst();
                }

                // Front always contains the maximum value's index
                result[resultIndex++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }


    public int[] maxInEveryWindow(int[] nums, int k) {
        int res[] = new int[nums.length - k + 1];
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            maxVal = Math.max(maxVal, nums[i]);
        }
        int index = 0;
        res[index++] = maxVal;


        int left = 1;
        for (int right = k; right < nums.length; right++) {
            maxVal = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                maxVal = Math.max(maxVal, nums[i]);
            }
            res[index++] = maxVal;

            left++;
        }
        return res;
    }


    public int[] firstNegativeInWindow(int[] nums, int k) {

        if (nums == null || k <= 0 || k > nums.length) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        // Build the first window
        for (int i = 0; i < k; i++) {
            if (nums[i] < 0) {
                queue.offer(i); // store index of negative number
            }
        }

        // Answer for the first window
        result[0] = queue.isEmpty() ? 0 : nums[queue.peek()];

        int left = 0;

        // Slide remaining windows
        for (int right = k; right < nums.length; right++) {

            // 1. Add the new right value if it is negative
            if (nums[right] < 0) {
                queue.offer(right);
            }

            // 2. Remove front index only when it has left the window
            if (!queue.isEmpty() && queue.peek() == left) {
                queue.poll();
            }

            // 3. Store answer for this window
            result[left + 1] = queue.isEmpty() ? 0 : nums[queue.peek()];

            // 4. Move left boundary
            left++;
        }

        return result;
    }


    public int maxDistinctInWindow(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int uniqueSize = map.keySet().size();
        int left = 0;
        for (int i = k; i < nums.length; i++) {
            int leftValue = map.get(nums[left]);
            if (leftValue > 1) {
                map.put(nums[left], leftValue - 1);
            } else {
                map.remove(nums[left]);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            uniqueSize = Math.max(uniqueSize, map.keySet().size());
            left++;

        }
        return uniqueSize;

    }
}
