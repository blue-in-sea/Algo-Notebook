/**
 * Given a target integer T and an integer array A sorted in ascending order, 
 * find the index i such that A[i] == T or return -1 if there is no such index. 
 *
 * Assumption: There can be duplicate elements in the array, 
 * and you can return any of the indices i such that A[i] == T.
 */

public class ClassicalBinarySearchTemplate {
    // template-1
    pulic int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        
        int l = 0, r = array.length - 1;
        
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                l = mid;   // 右区间搜索
            } else {
                r = mid;   // 左区间搜索
            }
        }
        
        if (array[l] == target) return l;
        if (array[r] == target) return r;
        return -1;
    }
    
    // template-2
    public int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        
        int l = 0, r = array.length - 1;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                l = mid + 1;   // 右区间搜索
            } else {
                r = mid - 1;   // 左区间搜索
            }
        }

        return -1;
    }
}

