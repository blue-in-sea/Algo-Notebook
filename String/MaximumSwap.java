class MaximumSwap {
    public int maximumSwap(int num) {
        int max = -1;
        int maxIndex = -1;
        int startIndex = -1;
        int endIndex = 1;
 
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
}
