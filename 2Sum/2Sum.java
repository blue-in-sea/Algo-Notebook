public class 2Sum {
    // Method 1: sort the array first, then use 2 pointers 
    // Time: O(nlogn), Space: O(1)
    public boolean existSum(int[] arr, int k) {
        // Assume the given array is not null, and has length at leaste 2
        Arrays.sort(arr); // operation costs O(nlogn)
        
        for (int i = 0, j = arr.length - 1; i < j; ) {
            if (arr[i] + arr[j] == k) return true;
            else if (arr[i] + arr[j] < k) i++;
            else j--;
        }
        return false;
    }
    
    // Method 1: while-loop version
    public boolean existSum(int[] array, int target) {
        // Assume the given array is not null, and has length at leaste 2
        Arrays.sort(array);
        
        int l = 0, r = array.length - 1;
        while (l < r) {
            int sum = array[l] + array[r];
            if (sum == target) return true;
            else if (sum < target) l++;
            else r--;
        }
        return false;
    }
}
