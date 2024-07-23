/**
 * Move All 0’s to the End
 *
 * index   0  1  2  3  4  5  6  7  8  9  10
 * input   1  9  8  4  0  0  2  7  0  6  0
 * return  1  9  8  4  2  7  6  0  0  0  0
 *                              s
 *                                       f
 */
/**
 * 快慢同相双指针
 * slow: all elements to the left side of slow (excluding slow) are non-zero
 * fast: the current index of linear scan
 *
 * Initialize: s = 0, f = 0
 * For each step
 * case 1	a[f] == 0, then f++                                 (f and s index-gap is # of consecutive zeros)
 * case 2 	a[f] != 0, then a[s] = a[f], s++, f++               (for non-zero element, replace with itself, or pushed forward)
 *
 * Post-processing fill 0s into rest of the array
 * (if we use swap, we do not need post processing, since we do not kill the zeros)
 */
public class MoveZeroes {
    // 快慢同相双指针!! 推荐算法
    // Time: O(n), Space: O(1)
    public void moveZeroes(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return;

        int s = 0;
        for (int f = 0; f < nums.length; f++) {
            // case 1: nums[f] != 0, a[s] = a[f], s++, f++
            if (nums[f] != 0) {
                nums[s] = nums[f];
                s++;
                // or swap(array, fast, slow++);
            }

            // case 2: nums[f] == 0, skip, f++
        }

        while (s < nums.length) {
            nums[s++] = 0;
        }
    }


    // **************************************************
    // Version 2: similar to quickSort's partition 
    
    public int[] moveZero(int[] array) {
        // assume array is not null
        if (array.length <= 1) {
            return array;
        }
        int left = 0;
        int right = array.length - 1;
        // [left, right] is the unexplory subarray
        while (left <= right) {
            if (array[left] != 0) {
                left++;
            } else if (array[right] == 0) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }
        return array;
    }

    // **************************************************
    // Utils

    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
}
