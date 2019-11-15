/**
 * Find all pairs of elements in a given array that sum to the pair the given target number. 
 * Return all the distinct pairs of values.
 * 
 * Example
 * A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6
 * return [[2, 4], [3, 3]]
 *
 * Assumption
 * The given array is not null and has length of at least 2
 * The order of the values in the pair does not matter
 */

public class TwoSumPairsII {
  // Method 1: sort the array first and use two pointers
  public List<List<Integer>> allPairs(int[] array, int target) {
    // asume array is not null and has length of at least 2
    Arrays.sort(array);
    List<List<Integer>> res = new ArrayList<>();
    int left = 0;
    int right = array.length - 1;
    while (left < right) {
      // we ignore all consecutive depulicate values when we want 
      // to determine the smaller element of the pair 
      if (left > 0 && array[left] == array[left - 1]) {
        left++; 
        continue;
      }
      // after dedup left, no need dedup right, since there is 
      // one-to-one relation between pair(L, R) == target  
      int sum = array[left] + array[right];
      if (sum == target) {
        res.add(Arrays.asList(array[left], array[right]));
        left++;
        right--;
      } else if (sum < target) {
        left++;
      } else{
        right--;
      }
    }
    return res;
  }
  
  // Method 2: use HashSet 
  public List<List<Integer>> allPairs(int[] array, int target) {
    // asume array is not null and has length of at least 2
    List<List<Integer>> res = new ArrayList<>();
    // Frequency Map<K, V> : <num, count>
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : array) {
      // Two cases when we make the pairs a solution 
      // 1. if 2 * x is target, we need to make sure there is no duplicates
      // 2. if x + y = target, and this is the first time both x and y are
      // present, so we can make sure there is no duplicates
      Integer count = map.get(num);
      if (num * 2 == target && count != null && count == 1) {
        res.add(Arrays.asList(num, num));
      } else if (map.containsKey(target - num) && count == null) {
        res.add(Arrays.asList(target - num, num));
      } 
      if (count == null) {
        map.put(num, 1);
      } else {
        map.put(num, count + 1);
      }
    }
    return res;
  }
}
