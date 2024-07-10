/**
 * 532. K-diff Pairs in an Array
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 *
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * 1. 0 <= i, j < nums.length
 * 2. i != j
 * 3. |nums[i] - nums[j]| == k (Notice that |val| denotes the absolute value of val)
 *
 * Input: nums = [3,1,4,1,5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 *
 * Input: nums = [1,2,3,4,5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 *
 * Input: nums = [1,3,1,5,4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 */
class KdiffPairsEqualK {
    // Method 1: Two HashSet (Optimal!!)
    // set1: store all visited num
    // set2: store all (i, j) where |nums[i] - nums[j]| == k
    // Time: O(N), Space: O(2N) -> O(N)
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }
        Set<Integer> seen = new HashSet<>();
        Set<Integer> used = new HashSet<>();
        for (int num : nums) {
            if (k != 0 && seen.contains(num + k)) {
                used.add(num);
            }
            if (seen.contains(num - k)) {
                used.add(num - k);
            }
            seen.add(num);
        }
        return used.size();
    }
    
    // Method 2: One HashMap
    // freqMap <key: num, value: boolean true if this element has not been used>
    // Time: O(N), Space: O(N)
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }

        int cnt = 0;
        if (k == 0) {
            // K = 0 as a separate corner case
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                if (map.containsKey(num) && map.get(num) == 1) {
                    cnt++;
                }
                map.put(num, map.getOrDefault(num ,0) + 1);
            }
            return cnt;
            
        } else {
            // main logics
            Map<Integer, Boolean> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, true);
            }

            for (int num : nums) {
                if (map.containsKey(num - k) && map.get(num) && map.get(num - k)) {
                    cnt++; 
                }
                if (map.containsKey(num + k) && map.get(num) && map.get(num + k)) {
                    cnt++;
                }
                map.put(num, false);
            }
            return cnt;
        }
    }
}

/**
 * 16. 3Sum Closest
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The
 */
