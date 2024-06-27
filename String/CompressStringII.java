/**
 * CompressStringII (LaiCode 611)
 * Given a string, replace adjacent, repeated characters with the character followed by the number of repeated
 * occurrences. If the character appear once, put 1 as its occurrence.
 *
 * “abbcccdeee” → “a1b2c3d1e3”
 * "hhhhhhhhhhhhhhhhhhhhhxxxxxxxxxxxxxxaaaaaaaaaddddffffooooooooooooll" -> "h21x14a9d4f4o12l2"
 */
public class CompressStringII {
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
                newLen += 2;
            } else {
                int len = copyDigits(input, slow, fast - begin);
                slow += len;
                newLen += len + 1;
            }
        }
        // Step 2: deal with the cases where the adjacent occur of the letter == 1
        char[] res = new char[newLen];
        fast = slow - 1;
        slow = newLen - 1;
        while (fast >= 0) {
            if (Character.isDigit(input[fast])) {
                while (fast >= 0 && Character.isDigit(input[fast])) {
                    res[slow--] = input[fast--];
                }
            } else {
                res[slow--] = '1';
            }
            res[slow--] = input[fast--];
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
