/**
 * 85. Determine If One String Is Another's Substring
 * Determine if a small string is a substring of another large string.
 * Return the index of the first occurrence of the small string in the large string.
 * Return -1 if the small string is not a substring of the large string.
 *
 * Assumptions
 * Both large and small are not null
 * If small is empty string, return 0
 *
 * Examples
 * “ab” is a substring of “bcabc”, return 2
 * “bcd” is not a substring of “bcabc”, return -1
 * "" is substring of "abc", return 0
 */
public class DetermineIfOneStringIsAnotherSubstring {
    // Assumption
    // 1. There is no assumption about the charSet used in the String
    //    so that we can NOT assume 26 lower characters
    // 2. This is the most correct version of RabinKarp in computer programming,
    //    we need to handle 1. we could use arbitrary charset, 2. the overflow case
    // 3. Probably don't require for the interview on the two cases for interview,
    //    but it's necessary to understand the reason behind it.

    // Method 1: Java API
    public int strstr(String large, String small) {
        return large.indexOf(small);
    }
}
