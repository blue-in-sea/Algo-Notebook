/**
 * 357. Count Numbers with Unique Digits
 * Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.
 *
 * Input: n = 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding 11,22,33,44,55,66,77,88,99
 *
 * Input: n = 0
 * Output: 1
 *
 * Constraints: 0 <= n <= 8
 */
public class CountNumberWithUniqueDigits {
    /**
     * Math Equation
     * For n = 1: The numbers range from 0 to 9, so there are 10 possible numbers.
     *
     * For n = 2: The first digit can be any digit from 1 to 9 (since it cannot be 0), and the second digit can be
     * any digit from 0 to 9 except the first digit. So, there are 9 * 9 = 81 possibilities.
     *
     * For n > 2: The number of possibilities decreases because each additional digit must be unique and cannot
     * repeat any of the previous digits.
     *
     * The total number of integers with exactly n unique digits is:
     * 9 * 9 * 8 * 7 ... (10−n+1)
     *
     * The first digit has 9 options (1-9).
     * The second digit has 9 options (0-9, excluding the first digit).
     * The third digit has 8 options, and so on.
     */
    
    // f(n) = 9 * 9 * 8 * ... * (10 - n + 1)
    // Time: O(n), Space: O(1)
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1; // Only the number 0 is valid
        if (n > 10) return 0; // No valid numbers if n > 10 (digits 0-9)

        int result = 10; // For n = 1, there are 10 possibilities (0-9)
        int uniqueDigits = 9; // For the first digit (1-9)
        int availableDigits = 9; // For the second digit onwards

        for (int i = 2; i <= n; i++) {
            uniqueDigits *= availableDigits;
            result += uniqueDigits;
            availableDigits--;
        }

        return result;
    }
}
