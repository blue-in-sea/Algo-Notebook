
public class BSTemplate {
    // soln-1
    public int lowerBound(int[] array, int target) {
        if (array == null || array.length == 0) 
            return 0;
        
        int l = 0, r = array.length;
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            if (array[mid] >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        
                
        
    }
  
  
}
