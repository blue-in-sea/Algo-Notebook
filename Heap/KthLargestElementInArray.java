/**
 * 215. Kth Largest Element in an Array
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 */
public class KthLargestElementInArray {
    // ===========================================================	
    // 1. Sort
    // Time: O(nlogn), Space: O(1)
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // ===========================================================	
    // 2. Heap (Optimal!!)
    // Time: O(nlogk), Space: O(1)
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(); // minHeap
        for (int num : nums) {
            heap.offer(num);

            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }
	
    // ===========================================================
    // 3. Quick Select 
    // Time: O(nlogn) on average and O(^2) worst case for unluck pivot
    // Space: O(1)
    public int findKthLargest(int[] array, int k) {
        return quickSelect(array, k, 0, array.length - 1);
    }

    private int quickSelect(int[] array, int k, int left, int right) {
        int pivotIndex = partition(array, left, right);

        if (pivotIndex == array.length - k) {
            return array[pivotIndex];
        } else if (pivotIndex > array.length - k) {
            return quickSelect(array, k, left, pivotIndex - 1);
        } else {
            return quickSelect(array, k, pivotIndex + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = findPivotIndex(left, right);
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);

        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (array[i] <= pivot) {
                i++;
            } else if (array[j] >= pivot) {
                j--;
            } else {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        swap(array, i, right);
        return i;
    }

    private int findPivotIndex(int left, int right) {
        return left + (int) Math.random() * (right - left + 1);
    }

    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }	

    // ===========================================================
    // 4. Counting sort 
    // Time: O(n + m) where n as the length of nums and m as maxValue - minValue
    // Space: O(m)
    Random r = new Random();
    public int findKthLargest(int[] nums, int k) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        
        for (int num: nums) {
            minValue = Math.min(minValue, num);
            maxValue = Math.max(maxValue, num);
        }
        
        int[] count = new int[maxValue - minValue + 1];
        for (int num: nums) {
            count[num - minValue]++;
        }
        
        int remain = k;
        for (int num = count.length - 1; num >= 0; num--) {
            remain -= count[num];
            if (remain <= 0) {
                return num + minValue;
            }
        }
        
        return -1;
    }
	

	

}
