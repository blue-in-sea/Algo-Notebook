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
