/* *
 * Given a integer dictionary A of unknown size, where the numbers in the dictionary are 
 * sorted in ascending order, determine if a given target integer T is in the dictionary. 
 * Return the index of T in A, return -1 if T is not in A.
 * 
 * Assumptions: 
 * 1) dictionary A is not null
 * 2) dictionary.get(i) will return null, if index i is out of bounds
 */

import java.util.Arrays;

public class UnknownSizeBinarySearch {
    public int search(Dictionary dict, int target) {
        if (dict == null) {
            return -1;
        }
        int left = 0;
        int right = 1;
        // define the right boundary for binary search, extends util
        // we are sure the target is within [left, right] range
        while (dict.get(right) != null && dict.get(right) < target) {
            // 1. move left to right
            // 2. double right index
            left = right;
            right = 2 * right;
        }
        return binarySearch(left, right, dict, target);
    }

    private int binarySearch(int left, int right, Dictionary dict, int target) {
        // classic binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (dict.get(mid) == null || dict.get(mid) > target) {
                right = mid - 1;
            } else if (dict.get(mid) < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        UnknownSizeBinarySearch soln = new UnknownSizeBinarySearch();

        Dictionary dict = new DictImpl(new int[0]);
        int target = 0;
        System.out.println("Expected: -1, Actual: " + soln.search(dict, target));  // Expected: -1, Actual: -1

        dict = new DictImpl(new int[] { 1 });
        target = 1;
        System.out.println("Expected: 0, Actual: " + soln.search(dict, target));  // Expected: 0, Actual: 0

        dict = new DictImpl(new int[] { 1 });
        target = 2;
        System.out.println("Expected: -1, Actual: " + soln.search(dict, target));  // Expected: -1, Actual: -1

        dict = new DictImpl(new int[] { 1, 3 });
        target = 0;
        System.out.println("Expected: -1, Actual: " + soln.search(dict, target));  // Expected: -1, Actual: -1

        dict = new DictImpl(new int[] { 1, 3 });
        target = 3;
        System.out.println("Expected: 1, Actual: " + soln.search(dict, target));  // Expected: 1, Actual: 1

        dict = new DictImpl(new int[] { 1, 3, 4, 4, 6, 10, 11, 12, 15, 17});
        target = 6;
        System.out.println("Expected: 4, Actual: " + soln.search(dict, target));  // Expected: 4, Actual: 4

        dict = new DictImpl(largeArray(100000));
        target = 9999;
        System.out.println("Expected: 9999, Actual: " + soln.search(dict, target));  // Expected: 9999, Actual: 9999
        target = 1000003;
        System.out.println("Expected: -1, Actual: " + soln.search(dict, target));  // Expected: -1, Actual: -1
    }

    public static int[] largeArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }
}

interface Dictionary {
    public Integer get(int index);
}

// No length() provided to outside
class DictImpl implements Dictionary {
    private int[] array;

    public DictImpl(int[] array) {
        this.array = array;
    }

    // If the index is out of bound, null will be return
    @Override
    public Integer get(int index) {
        if (array == null || index >= array.length) {
            return null;
        }
        return array[index];
    }

    // For pretty printout
    @Override
    public String toString() {
        if (array == null) {
            return String.valueOf(null);
        }
        if (array.length <= 10) {
            return Arrays.toString(array);
        }
        // Truncate output if array is too large
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 5; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.append("......, ");
        for (int i = array.length - 4; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
