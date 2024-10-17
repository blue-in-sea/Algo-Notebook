/**
 * 75. Number Of Different Bits
 * Determine the number of bits that are different for two given integers.
 *
 * 5(“0101”) and 8(“1000”) has 3 different bits
 */
public class NumberOfDifferentBits {
    public int diffBits(int a, int b) {
        // affter exclusive or, only the bits where a and b
        // are different will be 1
        a ^= b;
        int count = 0;
        // In Java, notice that we are using logical right shift
        // and a != 0 as the terminate condition
        while (a != 0) {
            count += a & 1;
            a >>>= 1;
        }
        return count;
    }
}
