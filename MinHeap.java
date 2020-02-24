public class MinHeap {
    private int[] array;
    private int size;
  
    public MinHeap(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegaArgumentException("input array can not be null or empty");
        }
        
        this.array = array;
        size = array.length;
        heapify();
    }
    
    private void heapify() {
        for (int lastIdx = size / 2 - 1; lastIdx >= 0; lastIdx--) {
            percolateDown(lastIdx);
        }
    }
    
    public MinHeap(int cap) {
        if (cap <= 0) [
            
        
    }



}
