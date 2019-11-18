class KdiffPairsEqualK {
    // K = 0, not pass the test case
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }
/*
        if (k == 0) {
            Map<Integer, Integer> map0 = new HashMap<>();
            int cnt0 = 0;
            for (int i : nums) {
                 map0.put(i, map0.getOrDefault(i, 0) + 1);
            }
            
            for (Map.Entry<Integer, Integer> entry : map0.entrySet()) {
                if (entry.getValue() >= 2) {
                    cnt0++;
                } 
            }
            return cnt0;
        }       
*/   
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
