/**
 * Given two integer arrays A1 and A2, sort A1 in such a way that the relative order among the elements will be same 
 * as those are in A2.
 *
 * For the elements that are not in A2, append them in the right end of the A1 in ascending order.
 *
 * Assumptions:
 * A1 and A2 are both not null.
 * There are no duplicate elements in A2.
 *
 * A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3}, A1 is sorted to {2, 2, 1, 1, 3, 5, 7, 9}
 */
public class SortInSpecifiedOrder {
  static class MyComparator implements Comparator<Integer> {
    // key: element, value: index
    Map<Integer, Integer> map;

    public MyComparator(int[] arr) {
      map = new HashMap<>();
      for (int i = 0; i < arr.length; i++) {
        map.put(arr[i], i);
      }
    }

    @Override
    public int compare(Integer a, Integer b) {
      Integer aIdx = map.get(a);
      Integer bIdx = map.get(b);

      // case 1: if both are in A2, maintain the original order by Index
      // case 2: if both NOT in A2, sort in asceding order 
      // case 3: if one in A2, whoever in A2 goes first 
      if (aIdx != null && bIdx != null) {
        return aIdx.compareTo(bIdx);  
      } else if (aIdx == null && bIdx == null){
        return a.compareTo(b);
      }

      return aIdx != null ? -1 : 1; 
    }
  }

  public int[] sortSpecial(int[] A1, int[] A2) {
    Integer[] copy = toIntegerArr(A1);
    Arrays.sort(copy, new MyComparator(A2));
    return toIntArr(copy);
  }

  private Integer[] toIntegerArr(int[] arr) {
    Integer[] res = new Integer[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  private int[] toIntArr(Integer[] arr) {
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }
}
