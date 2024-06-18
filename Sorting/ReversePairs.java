package Sorting;

/**
 * LCR 170. 交易逆序对的总数
 * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。
 * 请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
 *
 * 输入：record = [9, 7, 5, 4, 6]
 * 输出：8
 * 解释：交易中的逆序对为 (9, 7), (9, 5), (9, 4), (9, 6), (7, 5), (7, 4), (7, 6), (5, 4)。
 *
 * See GetCountArray.java, MergeSort.java
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
class ReversePairs {
    public int reversePairs(int[] array) {
        int[] indexArray = initialIndexArray(array);
        int[] countArray = new int[array.length]; 
        int[] helper = new int[array.length];     
        mergeSort(array, indexArray, countArray, helper, 0, array.length - 1);
        
        int count = 0;
        for (int i = 0; i < countArray.length; i++) {
            count += countArray[i];
        }
        return count;
    }

    private void mergeSort(int[] array, int[] indexArray, int[] countArray, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, indexArray, countArray, helper, left, mid);
        mergeSort(array, indexArray, countArray, helper, mid + 1, right);
        merge(array, indexArray, countArray, helper, left, mid, right);
    }

    private void merge(int[] array, int[] indexArray, int[] countArray, int[] helper, int left, int mid, int right) {
        copyArray(indexArray, helper, left, right);
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

    private int[] copyArray(int[] indexArray, int[] helper, int left, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = indexArray[i];
        }
        return helper;
    }

    private int[] initialIndexArray(int[] array) {
        int[] indices = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            indices[i] = i;
        }
        return indices;
    }
}
