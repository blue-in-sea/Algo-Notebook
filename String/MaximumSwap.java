class MaximumSwap {
    // Greedy: 从后往前，对于每个 index 找最理想 swap 的 candidates
    // Time: O(n)
    // Space: O(1)
    public int maximumSwap(int num) {
        int max = -1;
        int maxIndex = -1;
        int startIndex = -1; // 前一个 swap 的 candidate
        int endIndex = 1;    // 后一个 swap 的 candidate
 
        // converting the number to string
        char[] arr = numToCharArray(num);
        for (int i = arr.length - 1; i >= 0; i--) {
            // current digit is the largest by far
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
                continue;
            }

            if (arr[i] < max) {
                startIndex = i;
                endIndex = maxIndex;
            }
        }
 
        // check for is number already in order
        if (startIndex == -1) {
            return num;
        }
 
        swap(startIndex, endIndex, arr);
        return charArrayToNum(arr);
    }

    private void swap(int a, int b, char[] arr) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private char[] numToCharArray(int num) {
        return String.valueOf(num).toCharArray();
    }

    private int charArrayToNum(char[] arr) {
        return Integer.parseInt(new String(arr));
    }

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
