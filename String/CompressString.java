/**
 * Compress String (LaiCode 173)
 * Given a string, replace adjacent, repeated characters with the character followed by the number of repeated
 * occurrences. If the character does not has any adjacent, repeated occurrences, it is not changed.
 *
 * “abbcccdeee” → “ab2c3de3”
 * "aaaaaaaaaaaanneeeeeeefffffffwwwwwwwwwwwwww" -> "a12n2e7f7w14"
 */
public class CompressString {
    // Method: Fast + Slow Pointer
    // Time: O(n) where n is the len of input
    // Space: O(n) size of result
    public String compress(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        char[] array = input.toCharArray();
        return encode(array);
    }

    private String encode(char[] input) {
        // Step 1: deal with the cases where the adjacent occur of the letter >= 2
        int slow = 0;
        int fast = 0;
        int newLen = 0;
        while (fast < input.length) {
            int begin = fast;
            while (fast < input.length && input[fast] == input[begin]) {
                fast++;
            }
            input[slow++] = input[begin];
            if (fast - begin == 1) {
                newLen += 1;
            } else {
                int len = copyDigits(input, slow, fast - begin);
                slow += len;
                newLen += len + 1;
            }
        }
        // Step 2: copy the input[0 - newLen] into the res[]
        char[] res = new char[newLen];
        for (int i = 0; i < newLen; i++) {
            res[i] = input[i];
        }
        return new String(res);
    }
    // copy "count" as digits into "input" starting at "index"
    private int copyDigits(char[] input, int index, int count) {
        int len = 0;
        for (int i = count; i > 0; i /= 10) {
            index++;
            len++;
        }
        for (int i = count; i > 0; i /= 10) {
            int digit = i % 10;
            input[--index] = (char) ('0' + digit);
        }
        return len;
    }
}
