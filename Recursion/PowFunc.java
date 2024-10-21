/**
 * 50. Pow(x, n)
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 *
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 *
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 */
class PowFunc {
    /**
     * Brutal Force: pow(x, n) = x * pow(x, n - 1) where n<0.
     *
     * func pow(x, n):
     *     if n == 0: return 1
     *     if n < 0: return 1 / pow(x, -n)
     *     return x * pow(x, n - 1)
     *
     *
     * Optimization: Binary exponentiation
     * (x^2)^(n/2) - n is even
     * x * (x^2)^[(n-1)/2] - n is odd
     *
     * func pow(x, n):
     *     if n == 0: return 1.0
     *     if n < 0: return 1.0 / pow(x, -n)
     *
     *     if n is odd: return x * pow(x * x, (n - 1) / 2)
     *     otherwise: return pow(x * x, n / 2)
     */
    // Time: O(logn), Space: O(logn) stack calls
    public double myPow(double a, int b) {
        if (a == 0 && b <= 0) {
            throw new ArithmeticException("illegal input");
        }
        return b < 0 ? 1 / (double) pow(a, -b) : pow(a, b);
    }
  
    public double pow(double a, int b) {
        if (b == 0) {
            return 1;
        }
        if (a == 0) {
            return 0;
        }
        double half = pow(a, b / 2);
        return b % 2 == 0 ? half * half : a * half * half;
    }
}
