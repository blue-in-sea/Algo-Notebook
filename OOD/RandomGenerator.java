/**
 * Write a RandomGenerator class:
 * The contructor will be passed an integer n. generate is supposed to return a random number between 0 to n, 
 * but it is not supposed to return a number that it has already returned. 
 * 
 * If possiblities are exhauted, return -1.
 */
static class RandomGenerator {
	int[] arr;
	Random rand;
	int cur = 0;
	
	public RandomGenerator(int n) {
		arr = new int[n];
		rand = new Random();
		for(int i=0;i<arr.length;i++) {
			arr[i] = i;
			int next = rand.nextInt(i+1);
			if(next != i) {
        // swap(arr, i, next);
				int tmp = arr[i];
				arr[i] = arr[next];
				arr[next] = tmp;
			}
		}
	}
	
	public int generate() {
		if(cur < arr.length)
			return arr[cur++];
		return -1;
	}
}
