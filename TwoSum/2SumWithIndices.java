/**
 * 181. 2 Sum All Pair I （with indices)
 * Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.
 *
 * Assumptions
 * The given array is not null and has length of at least 2.
 *
 * Examples
 * A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
 * A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
 */
class 2SumWithIndices {
    // Algo: HashMap <Key: num, Value: Index>
    // for i, for j such that nums[i] + nums[j] == target
    // Time: O(n), Space: O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
