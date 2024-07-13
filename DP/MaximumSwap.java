/**
 * 670. Maximum Swap 
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 *
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 */
class MaximumSwap {
    // Greedy: 从后往前，对于每个 index 找最理想 swap 的 candidates
    // Time: O(n)
    // Space: O(1)
    public int maximumSwap(int num) {
        int max = Integer.MIN_VALUE;
        int maxIdx = -1;
        int lIdx = -1;
        int rIdx = 1;
        
        char[] arr = convertToArr(num);
        for (int i = arr.length - 1; i >= 0; i--) {

            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i;
                continue;
            }

            if (arr[i] < max) {
                lIdx = i;
                rIdx = maxIdx;
            }
        } 
 
        // check for is number already in order
        if (lIdx == -1) return num;
 
        swap(arr, lIdx, rIdx);
 
        return convertToNum(arr);
    }

    char[] convertToArr(int num) {
        return String.valueOf(num).toCharArray();
    }

    void swap(char[] arr, int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    int convertToNum(char[] arr) {
        return Integer.parseInt(new String(arr));
    }

    // *****************************************************

    // Brutal Force: 从前往后，对于每个 index 找其后最大的数
    // Time: O(n^2)
    // Space: O(1)
    public int maximumSwap(int num) {
        int max = -1;
        int maxIndex = -1;

        // converting the number to string
        char[] arr = numToCharArray(num);
        for (int i = 0; i < arr.length; i++) {

            for (int j = i; j < arr.length; j++) {
                if (arr[j] >= max) { // 注意：这里是等于，我们需要处于最后的 maximum 换到前面
                    max = Math.max(arr[j], max);
                    maxIndex = j;
                }
            }

            if (max > arr[i]) {
                swap(i, maxIndex, arr);
                break;
            } else {
                max = -1;
                maxIndex = -1;
            }
        }

        return charArrayToNum(arr);
    }
}
