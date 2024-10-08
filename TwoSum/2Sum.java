public class 2Sum {
    // Method 1: sort the array first, then use 2 pointers 
    // Time: O(nlogn), Space: O(1)
    // Method 1: while-loop version
    public boolean existSum(int[] array, int target) {
        // Assume the given array is not null, and has length at leaste 2
        Arrays.sort(array);
        
        int l = 0, r = array.length - 1;
        while (l < r) {
            int sum = array[l] + array[r];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }
    
    // Method 2: HashSet to store ele, then check ele's complement
    // Time: O(n), Space: O(n)
    public boolean existSum(int[] array, int target) {
        Set<Integer> set = new HashSet<>();
        
        for (int ele : array) {
            if (set.contains(target - ele)) {
                return true;
            }
            set.add(ele);
        }
        return false;      
    }
}
