public class ClosestInSortedArray {
    public int closest(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        
        int l = 0, r = array.length - 1;
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            if (array[m] == target) {
                return m;
            } else if (array[m] < target) {
                l = m;
            } else {
                r = m;
            }
        }
        
        if (Math.abs(array[l] - target) <= Math.abs(array[r] - target)) {
            return l;
        } else {
            return r;
        }
    }
}
