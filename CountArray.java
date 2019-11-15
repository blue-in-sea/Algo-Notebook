public class CountArray {
  private int[] indexArray;
  private int[] countArray;  // the actual return array
  private int[] helper;      // helpe with merge sorting indices 
  
  public int[] countArray(int[] array) { 
    // assume the array is not null;
    indexArray = initialindexArray(array);
    countArray = new int[array.length];
    helper = new int[array.length];
    mergeSort();
    mergeSort();
    merge();
    return countArray;
  }
}
