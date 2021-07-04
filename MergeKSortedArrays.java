public class MergeKSortedArrays {
  public int[] merge(int[][] arrayOfArrays) {
    // assume arrayOfArrays is not null, and none of the array is null
    PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(11, new MyComparator());
    int len = 0;
    int k = arrayOfArrays.length;
    for (int i = 0; i < k; i++) {
      int[] array = arrayOfArrays[i];
      len += array.length;
      if (array.length != 0) {
        // we use two index to record the position of each time
        // x - the index of the array in the arrayOfArrays
        // y - the index of the element in the array
        minHeap.offer(new Entry(i, 0, array[0]));
      }
    }

    int[] result = new int[len];
    int cur = 0;   // current index in the return list
    while (!minHeap.isEmpty()) {
      Entry tmp = minHeap.poll();
      result[cur++] = tmp.value;  // add tmp into list
      if (tmp.y + 1 < arrayOfArrays[tmp.x].length) {
        // reuse the same Entry object but advanced index by 1 unit
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
