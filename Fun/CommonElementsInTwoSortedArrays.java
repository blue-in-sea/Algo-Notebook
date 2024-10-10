/**
 * 652. Common Numbers Of Two Sorted Arrays(Array version)
 * Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).
 *
 * Assumptions
 * In each of the two sorted arrays, there could be duplicate numbers.
 * Both two arrays are not null.
 *
 * A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]
 */
public class CommonElementsInTwoSortedArrays {
    // Method 1: Two Pointers 谁小移谁
    public List<Integer> common(int[] A, int[] B) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) {
                list.add(A[i]);
                i++;
                j++;
            } else if (A[i] < B[j]) {
                i++;
            } else {
                j++;
            }
        }
        return list;
    }

  // Method 2: FreqMap
}

