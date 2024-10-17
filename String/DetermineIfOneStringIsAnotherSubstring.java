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

    // ============================================
    // Method 2: Naive Solution
    public int strstr(String large, String small) {
        if (large.length() < small.length()) {
            return -1;
        }
        // return 0 if small is empty
        if (small.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= large.length() - small.length(); i++) {
            if (equals(large, i, small)) {
                return i;
            }
        }
        return -1;
    }

    private boolean equals(String large, int start, String small) {
        for (int i = 0; i < small.length(); i++) {
            if (large.charAt(i + start) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // ============================================
    // Method 3: RabinKarp
    public int strstr(String large, String small) {
        if (large.length() < small.length()) {
            return -1;
        }
        // return 0 if small is empty
        if (small.length() == 0) {
            return 0;
        }
        // we need a large prime as module end
        int largePrime = 101;
        // we also need a small prime to calculate the hash value
        // (since the charset would be very large), (e.g.) 1,112,064 for using UTF
        // we can not use that number
        int prime = 31;
        int seed = 1;
        // hash value is calculated using smallPrime aand taken the module operation on 
        // largePrime.
        // hash = (s0*smallP^k + s0*smallP^(k-1) + .. + s0*smallP^0) % largeP
        int targetHash = small.charAt(0) % largePrime;
        for (int i = 1; i < small.length(); i++) {
            seed = moduleHash(seed, 0, prime, largePrime);
            targetHash = moduleHash(targetHash, small.charAt(i), prime, largePrime);
        }
        int hash = 0;
        for (int i = 0; i < small.length(); i++) {
            hash = moduleHash(hash, large.charAt(i), prime, largePrime);
        }
        if (hash == targetHash && equals(large, 0, small)) {
            return 0;
        }
        for (int i = 1; i <= large.length() - small.length(); i++) {
            // we need to make sure the module number is non-negative
            hash = nonNegative(hash - seed * large.charAt(i - 1) % largePrime, largePrime);
            hash = moduleHash(hash, large.charAt(i + small.length() - 1), prime, largePrime);
            // Notice: if the hash matches, it does not mean we really find a substring match.
            // because there is collision, we need to check if the substring really matches the 
            // small string. On average, this will not increase the time complexty, the prob
            // of collision is O(1) so we still have O(N + M)
            if (hash == targetHash && equals(large, i, small)) {
                return i;
            }
        }
        return -1;
    }

    private int moduleHash(int hash, int addition, int prime, int largePrime) {
        return (hash * prime % largePrime + addition) % largePrime;
    }

    private boolean equals(String large, int start, String small) {
        for (int i = 0; i < small.length(); i++) {
            if (large.charAt(i + start) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public int nonNegative(int hash, int largePrime) {
        if (hash < 0) {
            hash += largePrime;
        }
        return hash;
    }
}
