/**
 * LT 532. K-diff Pairs in an Array
 */

class KdiffPairsEqualK {
    // Time: O(N) for one Hash Map 
    // Space: O(N)
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }

        int cnt = 0;
        if (k == 0) {
            // case 1
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                if (map.containsKey(num) && map.get(num) == 1) {
                    cnt++;
                }
                map.put(num, map.getOrDefault(num ,0) + 1);
            }
            return cnt;
            
        } else {
            // case 2
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
    // K = 0 as a separate corner case
    // Version 0
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
