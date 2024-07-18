// API: read() - read one number from the flow
// API: sample() - return at any time the sample, if n values have been read
public class ReservoirSampling {
  // how many number has been read so far 
  private int count;
  // only need to maintain the current sample
  private Integer sample; 

  public Solution() {
    // count is intialized to be 0 since there is
    // no single number read at the beginning
    this.count = 0;
    this.sample = null;
  }
  
  public void read(int value) {
    count++;
    // Random rand = new Random();
    // int idx = rand.nextInt(count); 
    int prob = (int) (Math.random() * count);
    // the current read value has the prob (1 / count)
    // to be into the current sample
    if (prob == 0) {
      sample = value;
    }
  }
  
  public Integer sample() {
    return sample;
  }
}


