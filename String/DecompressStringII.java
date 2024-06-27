/**
 * Decompress String II (LaiCode 175)
 * Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the
 * original string are compressed to have the character followed by the number of repeated occurrences.
 *
 * “a1c0b2c4” → “abbcccc”
 * "x2y0i0z3" → "xxzzz"
 */
public class DecompressStringII {
    // Method 1: "In-place"
    public String decompress(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();
        // "a0", "a1", "a2" -> the decoded string will be shorter or equal
        // "a3", "a4",  ..  -> the decoded string will be longer
        return decodeLong(array, decodeShort(array));
    }

    private int decodeShort(char[] array) {
        int end = 0;
        for (int i = 0; i < array.length; i += 2) {
            int digit = getDigit(array[i+1]);
            if (digit >= 0 && digit <= 2) {
                for (int j = 0; j < digit; j++) {
                    array[end++] = array[i];
                }
            } else {
                // we skip the copy, will later to handle the longer decoded string 
                array[end++] = array[i];
                array[end++] = array[i + 1];
            }
        }
        return end;
    }

    private String decodeLong(char[] array, int len) {
        int newLen = len;
        for (int i = 0; i < len; i++) {
            int digit = getDigit(array[i]);
            if (digit > 2 && digit <= 9) {
                newLen += digit - 2;
            }
        }

        // Note: if it's required to do this in-place, usually the input array is sufficient 
        // large, we will not need to allocate a new array; but if not,
        // we will need to open a new array with larger length 
        char[] res = new char[newLen];
        int end = newLen - 1;
        for (int i = len - 1; i >= 0; i--) {
            int digit = getDigit(array[i]);
            if (digit > 2 && digit <= 9) {
                i--;
                for (int j = 0; j < digit; j++) {
                    res[end--] = array[i];
                }
            } else {
                // we already take care the shorter cases in decodeShort()
                res[end--] = array[i];
            }
        }

        return new String(res);
    }

    private int getDigit(char digit) {
        return digit - '0';
    }
}
