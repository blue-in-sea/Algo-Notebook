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
