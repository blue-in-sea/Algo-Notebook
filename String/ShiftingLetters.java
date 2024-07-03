/**
 * 848. Shifting Letters
 * 
 * You are given a string s of lowercase English letters and an integer array shifts of the same length.
 *
 * Call the shift() of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
 *
 * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
 * Now for each shifts[i] = x, we want to shift the first i + 1 letters of s, x times.
 *
 * Return the final string after all such shifts to s are applied.
 * 
 * Input: s = "abc", shifts = [3,5,9]
 * Output: "rpl"
 * Explanation: We start with "abc".
 * After shifting the first 1 letters of s by 3, we have "dbc".
 * After shifting the first 2 letters of s by 5, we have "igc".
 * After shifting the first 3 letters of s by 9, we have "rpl", the answer.
 *
 * Input: s = "aaa", shifts = [1,2,3]
 * Output: "gfd"
 */
class ShiftingLetters {
    /**
     * Let X be the number of times the current ith character is shifted.
     * Then the next character i+1 is shifted X - shifts[i] times.
     *
     * For example, if S.length = 4
     * S[0] is shifted X = shifts[0] + shifts[1] + shifts[2] + shifts[3] times
     * S[1] is shifted shifts[1] + shifts[2] + shifts[3] times
     * S[2] is shifted shifts[2] + shifts[3] times
     *
     * In general, we need to do X -= shifts[i] to maintain the correct value of X as we increment i
     */

    // Time: O(n) N is the length of S (and shifts).
    // Space: O(n)
    public String shiftingLetters(String s, int[] shifts) {
        StringBuilder sb = new StringBuilder();
        int X = 0;
        for (int shift: shifts) {
            X = (X + shift) % 26;
        }

        for (int i = 0; i < s.length(); ++i) {
            int index = s.charAt(i) - 'a';
            sb.append((char) ((index + X) % 26 + 97));
            X = Math.floorMod(X - shifts[i], 26);
        }

        return sb.toString();
    }
}
