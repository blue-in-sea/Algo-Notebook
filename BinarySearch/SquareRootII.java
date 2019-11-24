public class SquareRootII {  
    /**
     * @param x: a double
     * @return: the square root of x
     */
    public double sqrt(double x) {
        double l = 0;
        double r = Math.max(x, 1.0);
        double eps = 1e-12;
        
        while (l + eps < r) {
            double mid = l + (r - l) / 2;
            if (mid * mid <= x) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        return l;
    }
    
    public double sqrt(double x) {
        double l = 0;
        double r = Math.max(x, 1.0);
        double eps = 1e-12;
        double res = 0;
        
        while (l <= r) {
            double mid = l + (r - l) / 2;
            if (mid * mid <= x) {
                res = mid;
                l = mid + eps;
            } else {
                r = mid - eps;
            }
        }
        
        return res;
    }
}
