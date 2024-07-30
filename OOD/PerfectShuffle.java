/**
 * 384. Shuffle an Array
 * Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array 
 * should be equally likely as a result of the shuffling.
 *
 * APIs
 * Solution(int[] nums) - Initializes the object with the integer array nums.
 * int[] reset() - Resets the array to its original configuration and returns it.
 * int[] shuffle() - Returns a random shuffling of the array.
 *
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * Constraints: 
 * 1 <= nums.length <= 50
 * -106 <= nums[i] <= 106
 * All the elements of nums are unique.
 * At most 104 calls in total will be made to reset and shuffle.
 */
class PerfectShuffle {
    int[] origin;
    int[] array;

    public Solution(int[] nums) {
        this.array = nums;
        this.origin = copy(nums);
    }
    
    public int[] reset() {
        return origin;
    }
    
    public int[] shuffle() {
        if (array.length <= 1) return origin;
        // from right to left
        // for idx(i-1), randomly pick one element the first i element
        // [.. unshuffle .. || shuffle part]
        for (int i = array.length; i >= 1; i--) {
            int idx = (int) (Math.random() * i);
            swap(array, idx, i - 1);
        } 
        return array;
    }

    private int[] copy(int[] nums) {
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }
        return copy;
    }

    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
