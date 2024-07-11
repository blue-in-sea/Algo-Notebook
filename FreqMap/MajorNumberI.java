class MajorNumberI {

    
    
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        Map<Integer, Integer> map = frequencyMap(nums);
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (majorityEntry == null || e.getValue() > majorityEntry.getValue()) {
                majorityEntry = e;
            }
        }
        return majorityEntry.getKey();  
    }
    
    public Map<Integer, Integer> frequencyMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int ele : nums) {
            if (!map.containsKey(ele)) {
                map.put(ele, 1);
            } else {
                map.put(ele, map.get(ele) + 1);
            }
        }
        return map;
    } 

    // ************************************************************************

    // Method 2:

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
}
