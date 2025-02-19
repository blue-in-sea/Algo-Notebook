/**
 * 7. Reverse Integer
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside
 * the signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 * Input: x = 123
 * Output: 321
 *
 * Input: x = -123
 * Output: -321
 *
 * Input: x = 120
 * Output: 21
 * 
 * Constraints: -2^31 <= x <= 2^31 - 1
 */
class ReverseInteger {
    /**
     * 1. Handle Negative Numbers: if input x is negative => this will not affect the res
     * 2. Reverse the Digits
     *    We repeatedly extract the last digit of x
     *
     *    // pop operation:
     *    digit = x % 10;
     *    x /= 10;
     *
     *    and append it to the result.
     *    // push operation:
     *    temp = rev * 10 + digit;
     *    rev = temp;
     *
     * 3. Check for Overflow: INT_MAX 2147483647 > x > -2147483648 INT_MIN
     * 4. Return the reversed result 
     */

    // Time: O(n) or O(log10(x))
    //       where n is number of digits and since n = log10(x) + 1 where n is is number of digits in x
    // Space: O(1)
    public int reverse(int x) {
        int rev = 0;

        while (x != 0) {
            int digit = x % 10; // extract the last digit
            x /= 10; // remove the last digit from x

            // check overflow
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }

            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            rev = rev * 10 + digit;
        }

        return rev;
    }
}
