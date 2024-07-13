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
        
        public int majorityElement(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> numToCnt = new HashMap<>();
        for (int num : nums) {
            numToCnt.put(num, numToCnt.getOrDefault(num, 0) + 1);
        }
       
        for (Integer key : numToCnt.keySet()) {
            if (numToCnt.get(key) > n / 2) return key;
        }

        return -1;
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
