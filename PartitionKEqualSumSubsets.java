/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into
 * k non-empty subsets whose sums are all equal.
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 */

public class PartitionKEqualSumSubsets {
    public static void main(String[] args) {
        int nums[] = {4, 3, 2, 3, 5, 2, 1};
        System.out.print(canPartitionKSubsets(nums, 4));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        boolean[] visited = new boolean[nums.length]; // visited[i] 的物理意义是选了就不能在选了
        return totalSum % k == 0 && dfs(nums, k, totalSum / k, 0, 0, visited);
    }

    private static boolean dfs(int[] nums, int k, int targetSum, int startIndex, int sum, boolean[] visited) {
        if (k == 0) return true;
        if (sum > targetSum) return false;
        if (sum == targetSum) return dfs(nums, k - 1, targetSum, 0, 0, visited);
        for (int i = startIndex; i < nums.length; i++) {
            // print
            if (!visited[i]) {
                visited[i] = true;
                sum += nums[i];
                if (dfs(nums, k, targetSum, i + 1, sum, visited)) {
                    return true;
                }
                // back-tracking
                visited[i] = false;
                sum -= nums[i];
            }
        }
        return false;
    }
}

/**
 * Time Complexity: O(n) = N * 2^N < N!
 * 它只有进入了 if 才进入下一层 recursive call
 * 但是如果它触动了第三个 base case 的话它会直接回到最开始
 */

/**
 * nums = [4, 3, 2, 3, 5, 2, 1], k = 4, True
 * Explanation: It's possible to divide it into 4 subsets
 * (5), (1, 4), (2,3), (2,3) with equal sums.
 * nums[i]: 4, k: 4, current sum: 0, startIndex: 0, visited[i]: [false, false, false, false, false, false, false]
 * nums[i]: 3, k: 4, current sum: 4, startIndex: 1, visited[i]: [true, false, false, false, false, false, false]
 * nums[i]: 2, k: 4, current sum: 4, startIndex: 1, visited[i]: [true, false, false, false, false, false, false]
 * nums[i]: 3, k: 4, current sum: 4, startIndex: 1, visited[i]: [true, false, false, false, false, false, false]
 * nums[i]: 5, k: 4, current sum: 4, startIndex: 1, visited[i]: [true, false, false, false, false, false, false]
 * nums[i]: 2, k: 4, current sum: 4, startIndex: 1, visited[i]: [true, false, false, false, false, false, false]
 * nums[i]: 1, k: 4, current sum: 4, startIndex: 1, visited[i]: [true, false, false, false, false, false, false]
 * ----------------------------------------------------------------------------------------------------- (1, 4)
 * nums[i]: 4, k: 3, current sum: 0, startIndex: 0, visited[i]: [true, false, false, false, false, false, true]
 * nums[i]: 3, k: 3, current sum: 0, startIndex: 0, visited[i]: [true, false, false, false, false, false, true]
 * nums[i]: 2, k: 3, current sum: 3, startIndex: 2, visited[i]: [true, true, false, false, false, false, true]
 * ----------------------------------------------------------------------------------------------------- (2, 3)
 * nums[i]: 4, k: 2, current sum: 0, startIndex: 0, visited[i]: [true, true, true, false, false, false, true]
 * nums[i]: 3, k: 2, current sum: 0, startIndex: 0, visited[i]: [true, true, true, false, false, false, true]
 * nums[i]: 2, k: 2, current sum: 0, startIndex: 0, visited[i]: [true, true, true, false, false, false, true]
 * nums[i]: 3, k: 2, current sum: 0, startIndex: 0, visited[i]: [true, true, true, false, false, false, true]
 * nums[i]: 5, k: 2, current sum: 3, startIndex: 4, visited[i]: [true, true, true, true, false, false, true]
 * nums[i]: 2, k: 2, current sum: 3, startIndex: 4, visited[i]: [true, true, true, true, false, false, true]
 * ----------------------------------------------------------------------------------------------------- (2, 3)
 * nums[i]: 4, k: 1, current sum: 0, startIndex: 0, visited[i]: [true, true, true, true, false, true, true]
 * nums[i]: 3, k: 1, current sum: 0, startIndex: 0, visited[i]: [true, true, true, true, false, true, true]
 * nums[i]: 2, k: 1, current sum: 0, startIndex: 0, visited[i]: [true, true, true, true, false, true, true]
 * nums[i]: 3, k: 1, current sum: 0, startIndex: 0, visited[i]: [true, true, true, true, false, true, true]
 * nums[i]: 5, k: 1, current sum: 0, startIndex: 0, visited[i]: [true, true, true, true, false, true, true]
 * ----------------------------------------------------------------------------------------------------- (5)
 */
