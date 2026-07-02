import java.util.*;

public class SlidingWindowLearning {
    public static void main(String[] args) {
        SlidingWindowLearning slidingWindowLearning = new SlidingWindowLearning();
        int[] ints = slidingWindowLearning.maxInEveryWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (int i : ints) {
            System.out.println(i);
        }
//        int res = slidingWindowLearning.maxDistinctInWindow(new int[]{1, 2, 1, 3, 4, 2, 3}, 3);
//        System.out.println(res);

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
