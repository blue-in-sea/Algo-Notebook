/**
 * Counting sort is a sorting algorithm that sorts the elements of an array by 
 * counting the number of occurrences of each unique element in the array. 
 * The count is stored in an auxiliary array and the sorting is done by mapping 
 * the count as an index of the auxiliary array.
 */
class CountingSort {
    // Time: O(n + k), where n is the number of elements in the array 
    //       and k is the range (max - min + 1)
    // Space: O(n + k), due to the auxiliary count and output arrays.
    public int[] sortArray(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int a : array) {
            min = Math.min(a, min);
            max = Math.max(a, max);
        }

        // init cnt arrray 
        int[] cnt = new int[max - min + 1];
        
        // cnt the freq
        for (int i = 0; i < array.length; i++) {
            cnt[array[i] - min]++;
        }

        // cumulate the prev freq to find the index
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }

        // contruct the output
        int[] res = new int[array.length];
        for (int i = array.length - 1; i >=0; i--) {
            res[cnt[array[i] - min] - 1] = array[i];
            cnt[array[i] - min]--;
        }

        return res;
    }
}
