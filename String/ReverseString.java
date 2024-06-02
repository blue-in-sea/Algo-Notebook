

public class ReverseString {
    /**
     * String Reversal - While loop
     * Time: O(n) for two pointers scan the entire input
     * Extra Space: O(1) for in-place operation on the array
     *
     * 逆向双指针：i，j  分别指向首末，相向而行 【解法 in-place】
     *      *
     *      * A  P  P  L  E    →  E  L  P  P  A
     *      * i           j
     *      *
     *      *  * while (i < j)
     *      *  * * swap(i, j)
     *      *  * * i++
     *      *  * * j--
     */
    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            swap(s, i++, j--);
        }  
    }

    private void swap(char[] array, int a, int b) {
        char tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    // =============================================
    // for input as string not char[]

    public String reverse(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int left = 0, right = array.length - 1;
        while (left < right) {
            swap(array, left++, right--);
        }
        return new String(array);
    }

    // ==============================================

    /**
     * String Reversal - for-loop 现实
     * Time: O(n) - looping 
     * Space: O(1) - in-place
     */
    public String reverse(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        for (int left = 0, right = array.length - 1; left < right; left++, right--) {
            swap(array, left, right);
        }
        return new String(array);
    }

    // ==============================================

    /**
     * String Reversal - recursion 现实
     * Time: O(N)
     * Space: O(N) for n/2 stack calls
     */
    public String reverse(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        helper(array, 0, array.length - 1);
        return new String(array);
    }

    public void helper(char[] array, int left, int right) {
        // base case
        if (left >= right) {
            return;
        }
        // recursion
        helper(array, left + 1, right - 1);
        swap(array, left, right);              // 虚线框
    }

    // ==============================================
}
