public class 3SumClosest {
    // essence: search in sorted matrix
    // time: O(nlogn) + O(n^2) = O(n^2)
    // space: O(1) since two pointers
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                // update the result sum
                if(Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                } 
                // two pointers 
                if(sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    return res;
                }
            }
        }
        return res;
    }
}
