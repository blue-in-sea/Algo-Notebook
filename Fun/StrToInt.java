/**
 * 8. String to Integer (atoi)
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 * 
 * Rules:
 * 1. Whitespace: Ignore any leading whitespace (" ").
 * 2. Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither 
 *    present.
 * 3. Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of 
 *    the string is reached. If no digits were read, then the result is 0.
 * 4. Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to 
 *    remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 
 *    231 - 1 should be rounded to 231 - 1.
 *    Return the integer as the final result.
 *
 * Example 1:
 * Input: s = "42"
 * Output: 42
 *
 * The underlined characters are what is read in and the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "42" ("42" is read in)
 *            ^
 *            
 * Example 2:
 * Input: s = " -042"
 * Output: -42
 * 
 * Step 1: "   -042" (leading whitespace is read and ignored)
 *             ^
 * Step 2: "   -042" ('-' is read, so the result should be negative)
 *              ^
 * Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
 *                ^
 * 
 * Example 3:
 * Input: s = "1337c0d3"
 * Output: 1337
 *
 * Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
 *              ^
 * Example 4:
 * Input: s = "0-1"
 * Output: 0
 * 
 * Step 1: "0-1" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
 *           ^
 *           
 * Example 5:
 * Input: s = "words and 987"
 * Output: 0
 * Reading stops at the first non-digit character 'w'.
 * 
 * Constraints:
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 */
class StrToInt {
    // Time: O(n), Space: O(1)
    public int myAtoi(String s) {
        int index = 0;
        int sign = 1;
        int result = 0;
        int n = s.length();
        
        // Step 1: Ignore leading whitespace
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }
        
        // Step 2: Determine the sign
        if (index < n && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            sign = (s.charAt(index) == '-') ? -1 : 1;
            index++;
        }
        
        // Step 3: Convert the number and handle overflow
        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';
            
            // Check for overflow
            if (result > Integer.MAX_VALUE / 10 || 
                (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            result = result * 10 + digit;
            index++;
        }
        
        return result * sign;
    }
}
