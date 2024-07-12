/**
 * LT219. Contains Duplicate II
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array 
 * such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 */
public class ContainsDuplicateII {
  /**
   * Method 1: Naive Linear Search
   * Time: O(n * min(k, n))
   * Space: O(1)
   */
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = Math.max(i - k, 0); j < i; j++) {
        if (nums[i] == nums[j]) return true;
      }
    }
    return false;
  }
  
  /**
   * Method 2: Keep a sliding window of k elements using Hash Set
   * Time: O(n) We need n operations of search, delete and insert, each with O(1) constant time complexity.
   * Space: O(min(n, k)) for maintaining the hashset 
   */
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (set.contains(nums[i])) {
        return true;
      }
      set.add(nums[i]);
      if (set.size() > k) {
        set.remove(nums[i - k]);
      }
    }
    return false;
  }
  
  /**
   * Method 2: Keep a sliding window of k elements using self-balancing BST
   * Time: O(n) We need n operations of search, delete and insert,
   *            Each costs O(logn) logarithmic time complexity in the sliding window of size min(k, n).
   * Space: O(min(n, k)) for maintaining the treeset
   */
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    Set<Integer> set = new TreeSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (set.contains(nums[i])) {
        return true;
      }
      set.add(nums[i]);
      if (set.size() > k) {
        set.remove(nums[i - k]);
      }
    }
    return false;
  }
}
