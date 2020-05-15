
public class BSTemplate {
    // template-1: with post-processing
    public int lowerBound(int[] array, int target) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int len = array.length;
        int l = 0, r = len - 1;
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            if (array[m] >= target) {
                r = m;
            } else {
                l = m; 
            }
        }
        if (array[l] >= target) {
            return l;
        }
        if (array[r] >= target) {
            return r;
        } 
        return len;
    }
    
    // template-2: no post-processing
    public int lowerBound(int[] array, int target) {

        int len = array.length;
        int l = 0, r = len;
        while (l < r) {
            if (array[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        
        return l;     
    } 
    
    // template-4: 
}
  
  
}
