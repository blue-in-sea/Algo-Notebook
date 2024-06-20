package FreqMap;

class MajorityNumberII {
    public int majorityElement(int[] nums) {
        int majorityCount = nums.length / 2;
        
        for (int num : nums) {
            int count = 0;
            for (int elem : nums) {
                if (elem == num) {
                    count += 1;
                }
            }
            if (count > majorityCount) return num;
        }
        
        return -1;
    }
    
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int cnt = entry.getValue();
            if (cnt > n / 3) result.add(entry.getKey());
        }

        return result;
    }
}
