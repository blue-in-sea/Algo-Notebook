/**
 * Given an array A of length N containing all positive integers from [1...N]. 
 * How to get an array B such that B[i] represents: 
 * how many elements A[j] (j > i) in array A that are smaller than A[i].
 *
 * Assumption: the given array A is not null.
 * A = { 4, 1, 3, 2 }, we should get B = { 3, 0, 1, 0 }.
 */
class CountArray {
    private int[] indexArray;
    private int[] countArray;  // the actual return array
    private int[] helper;      // helpe with merge sorting indices 

    public int[] countArray(int[] array) {
        indexArray = initialIndexArray(array);
        countArray = new int[array.length];
        helper = new int[array.length];

        mergeSort(array, 0, array.length - 1);
        return countArray;
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        // 归并排序的优化，同样适用于该问题
        // 如果索引数组有序，就没有必要再继续计算了
        if (array[indexArray[mid]] > array[indexArray[mid + 1]]) {
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = indexArray[i];
        }

        int i = left;
        int j = mid + 1;
        int cur = left;

        while (i <= mid) {
            if (j > right || array[helper[i]] <= array[helper[j]]) {
                countArray[helper[i]] += (j - mid - 1);
                indexArray[cur++] = helper[i++];
            } else {
                // no need to update count
                indexArray[cur++] = helper[j++];
            }
        }
    }

    /* *
     * IndexArray store the indices
     * merge sort the array but records the sequence of
     * original indices after sorting
     */
    private int[] initialIndexArray(int[] array) {
        int[] indices = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            indices[i] = i;
        }
        return indices;
    }
}

/**
 *              4 1 3 2
 *               /   \
 *            4  1   3  2
 *           /   \   /   \
 *        4(0) 1(0) 3(0) 2(0)
 * conuter (semantic meaning) : in the process of merger, how many ele that is 
 * smaller than me have move from my right-side to my left-side
 * ===========================================================================
 *          \   /    \   /
 *        1(0) 4(1) 2(0) 3(1)
 *              i              j
 *               \    /
 *         1(0) 2(0) 3(1) 4(1+2)
 *
 *   xxxx X xxxx   yyyy Y yyyyyy
 *        i             j
 *
 *  res : [xyyxyxyyyxx]
 *  case 1: if X < Y
 *     res.push_back(X)     // [4]  
 *     X's counter += j
 *     i++
 *  case 2: if X > Y
 *     res.push_back(Y)     // [2, 3]
 *     Y's counter += 0
 *     j++
 */

/**
 * Index Array:  [1, 0, 2, 3]  位置
 * Helper Array: [0, 1, 0, 0]  辅助   xxxx  yyyy (index)
 * Count Array:  [1, 0, 0, 0]  计数
 * =============
 * Index Array:  [1, 0, 3, 2]
 * Helper Array: [0, 1, 2, 3]
 * Count Array:  [1, 0, 1, 0]
 * =============
 * Index Array:  [1, 3, 2, 0]
 * Helper Array: [1, 0, 3, 2]
 * Count Array:  [3, 0, 1, 0]
 * =============
 * Result: [3, 0, 1, 0]
 */
