/**
 * 110. Generalized Reservoir Sampling
 * Consider an unlimited flow of data elements. How do you sample k element from this flow, such that at any point 
 * during the processing of the flow, you can return a random set of k elements from the n elements read so far. 
 *
 * Assumptions
 * k >= 1
 */

// read(int value) - read one number from the flow

// sample() - return at any time the k samples as a list, return the list of all values read when the number of 
// * values read so fas <= k.

public class ReservoirSamplingWithKSamples  {
  private final int k;
  private int count;
  private List<Integer> sample;
  
  public Solution(int k) {
    if (k <= 0) {
      throw new IllegalArgumentException("k must be > 0");
    }
    // we need to sample k elements instead of just one element
    // usually we will need this validation in the constructor 
    this.k = k;
    this.count = 0;
    sample = new ArrayList<Integer>();
  }
  
  public void read(int value) {
    count++;
    if (count <= k) {
      // for the first k elements, we just add them into the sample list
      sample.add(value);
    } else {
      int random = (int) (Math.random() * count);
      // for the recent read element, it should have the prob of 
      // k / count to be as one of the sample
      if (random < k) {
        // replace the sample at corresponding index 
        sample.set(random, value);
      }
    }
  }
  
  public List<Integer> sample() {
    return sample;
  }
}
