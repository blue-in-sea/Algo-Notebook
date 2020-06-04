/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Input: [1,2,0]        Output: 3
 * Input: [3,4,-1,1]     Output: 2
 * Input: [7,8,9,11,12]  Output: 1
 */
class FirstMissingPositive {
    public int firstMissingPositive(int[] array) {
        for (int i = 0; i < array.length; i++) {
            while (array[i] > 0 && array[i] <= array.length 
                   && array[array[i] - 1] != array[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // (e.g.) 3 should be placed at index 2
                swap(array, i, array[i] - 1);
            }
        }
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] != i + 1) {
                return i + 1;
            }
        }
        return array.length + 1;
  }
  
    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
}
