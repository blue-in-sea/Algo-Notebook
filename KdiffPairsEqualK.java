class KdiffPairsEqualK {
    // K = 0, not pass the test case
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // <K, V> : < num, used true or false
        Map<Integer, Boolean> map = new HashMap<>();
        
        for (int num : nums) {
            map.put(num, true);
        }
        
        int cnt = 0;
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
