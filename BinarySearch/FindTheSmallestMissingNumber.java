/**
 * Given a sorted array of n distinct integers, there are numerous missing elements 
 * in the complete array [0, 1, 2, 3, ..., m - 1] (m > n). For example, [0, 1, 3, 5]
 * is a case when m = 6 and n = 4, and 2, 4 are missing in the array.
 *  
 * Write a binary search solution to find the smallest number that is missing in
 * the array, i.e. 2 in the above example. Return 0 if the array is null or empty.
 * 
 * Input: array = [0, 1, 2, 6, 9] (n = 5, m = 10) Output: 3
 * Input: array = [4, 5, 10, 11] (n = 4, m = 12)  Output: 0
 */
public class FindTheSmallestMissingNumber {
    public int findFirstMissingElement(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int left = 0;
        int right = array.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == mid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (array[left] != left) {
            return left;
        } else {
            if (right == array.length - 1 && array[right] == right) {
                return array.length;
            }
            return right;
        }
    }
}

/**
 * Two corner case in the post-processing 
 * Input: array = [0, 2] (n = 1)        Output: 1
 * Input: array = [0, 1, 2, 3] (n = 4)  Output: 4
 */
