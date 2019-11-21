public class CopyBooks {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // 二分抄书时间
        int res = 0;
        int l = 0, r = Integer.MAX_VALUE;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(pages, mid, k)) {
                // mid is a possible answer, but time could be lesser
                res = mid;
                r = mid - 1;
            } else {
                // mid is not an answer, time needed to be more 
                l = mid + 1;
            }
        }
        
        return res;
    }
    
    private boolean check(int[] pages, int timeLimit, int k) {
        int person = 0;
        int remain = 0;
        for (int book : pages) {
            if (book > timeLimit) {
                return false;
            }
            if (book > remain) {
                person++;
                remain = timeLimit;
            }
            remain -= book;
        }
        return person <= k;
    }
    
    // version 2
}
