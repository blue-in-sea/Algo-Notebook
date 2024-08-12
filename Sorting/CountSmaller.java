/**
 * 1365. How Many Numbers Are Smaller Than the Current Number
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for
 * each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 *
 * Return the answer in an array.
 *
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 *
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 *
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 *
 *
 * Constraints:
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
class CountSmaller {
    /**
     * Approach: counting
     * We will create a count array to store the occurrences of each number in the input array. Then, we will iterate
     * through this count array to accumulate the counts. Finally, we will use this information to determine the count of
     * smaller numbers than the current number.
     *
     * Time: O(n)
     * Space: O(N) where count[101] + result[n] = O(1) + O(N)
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            count[nums[i]]++;
        }

        for (int i = 1; i <= 100; i++) {
            count[i] += count[i - 1];
        }

        int[] result = new int[n];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                result[i] = 0;
            } else {
                result[i] = count[nums[i] - 1];
            }
        }

        return result;
    }

    // ================================================
    // Brutal Force O(n^2)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }

                result[i] = count;
            }
        }
        return result;
    }
}
