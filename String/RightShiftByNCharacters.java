/**
 * Right shift a given string by n characters. (LaiCode 397)
 * "abc", 4 -> "cab"
 */
public class RightShiftByNCharacters {
    /**
     * Extended topic from (ReverseString)
     *
     * input =  abcd ef   (shift the whole string to right side by 2 positions)
     * result = ef abcd
     *
     * Step1     dcab  |  fe
     * reverse str[0 … length - 3]				  - Time O(n), Space O(1)
     * reverse str[length - 2 … length - 1]			 - Time O(n), Space O(1)
     *
     * Step2     ef  |  abcd
     * reverse the entire String			       - Time O(n), Space O(1)
     */

    // Time: O(n)
    // Space: O(1)
    public String rightShift(String input, int n) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        n %= array.length;
        reverse(array, array.length - n, array.length - 1);
        reverse(array, 0, array.length - n - 1);
        reverse(array, 0, array.length - 1);
        return new String(array);
    }

    public void reverse(char[] array, int left, int right) {
        while (left < right) {
            char tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
    }
}

