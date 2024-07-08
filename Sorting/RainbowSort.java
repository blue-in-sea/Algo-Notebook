/**
 * 75. Sort Colors
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 */
class RainbowSort {
   /**
     * Rainbow Sort:
     * Dutch National Flag Problem
     *
     * [0, i): red
     * [i, j): white
     * [j, k]: unchecked element 
     * [(k, len - 1]: blue
     */
  
    // Time: O(n)
    // Space: O(1)
    public void sortColors(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        // Other special case handling: (ask the in t)
        // if array contains other invalid color, should we throw e or should we skip?
        int i = 0;
        int j = 0;
        int k = array.length - 1;
        while (j <= k) {
            if (array[j] == 0) {              
                swap(array, i++, j++);
            } else if (array[j] == 1) {
                j++;
            } else {
                swap(array, j, k--);
            }
        }
    }
    
    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
