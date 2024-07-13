/**
 * 169. Majority Element
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times. 
 * 
 * Assume that the majority element always exists in the array.
 *
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 */
class MajorNumberI {
    // Optimal: CntMap
    // Time: O(n), Space: O(n)
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

    // Brutal Force
    // Time: O(n^2), Space: O(1)
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
