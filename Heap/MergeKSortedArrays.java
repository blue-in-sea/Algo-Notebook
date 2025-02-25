public class MergeKSortedArrays {
  // Time: O(N logK) 
  // Space: O(N + K) size of results + size of heap 
  public int[] merge(int[][] arrayOfArrays) {
    // assume arrayOfArrays is not null, and none of the array is null
    
    PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(11, new MyComparator());
    int len = 0;
    int k = arrayOfArrays.length;
    
    // Step 1: Populate the heap with the first element of each array
    for (int i = 0; i < k; i++) {
      int[] array = arrayOfArrays[i];
      len += array.length;
      if (array.length != 0) {
        minHeap.offer(new Entry(i, 0, array[0]));
      }
    }

    // Step 2: Initialize the result array
    int[] result = new int[len];
    int cur = 0;

    // Step 3: Merge the arrays using the min-heap
    while (!minHeap.isEmpty()) {
      Entry tmp = minHeap.poll();
      result[cur++] = tmp.value;
      if (tmp.y + 1 < arrayOfArrays[tmp.x].length) {
        tmp.y++;
        tmp.value = arrayOfArrays[tmp.x][tmp.y];
        minHeap.offer(tmp);
      }
    }
    
    return result;
  }

  static class MyComparator implements Comparator<Entry> {
    @Override
    public int compare(Entry e1, Entry e2) {
      if (e1.value == e2.value) {
        return 0;
      }
      return e1.value < e2.value ? -1 : 1;
    }
  }

  static class Entry {
    // the row number
    int x;
    // the col number 
    int y;
    // the corresponding value
    int value;

    Entry(int x, int y, int value) {
      this.x = x;
      this.y = y;
      this.value = value;
    }
  }
}
