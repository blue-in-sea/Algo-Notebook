public class MoveZeroes {
    // Two pointers: slow & fast
    public void moveZeroes(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return;

        int s = 0;
        for (int f = 0; f < nums.length; f++) {
            // case 1: nums[f] != 0, a[s] = a[f], s++, f++
            if (nums[f] != 0) {
                nums[s] = nums[f];
                s++;
            }

            // case 2: nums[f] == 0, skip, f++
        }

        while (s < nums.length) {
            nums[s++] = 0;
        }
    }
}
