/**
 * 120. Largest And Second Largest
 * 
 * Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. Return
 * the largest number and 2nd largest number.
 *
 * Assume the input is not null and has length of at least 2
 * 
 * Examples
 * {2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
 */
public class LargestAndSecondLargest {
    // The element class will be sued to store the origin value in the array
    // and all values compared to it
    static class Element {
        int value;
        List<Integer> comparedValues;

        public Element(int value) {
            this.value = value;
            this.comparedValues = new ArrayList<>();
        }
    }

    public int[] largestAndSecond(int[] array) {
        // Assume array is not null and array.length >= 2
        // Convert the original array to element array
        Element[] eleArr = convert(array);
        // n is the left partition's length containing the larger values
        // after each round of comparison;
        // we compare the pair (x, n - 1 - x) in each round of iteration 
        // and put the larger element in the left side.
        int n = array.length;
        // loop will stop if there is 1 element left on the larger partition, 
        // which is the largest vakyes; the 2nd largest will be in the 
        // compared values of the largest
        while (n > 1) {
            compareAndSwap(eleArr, n);
            n = (n + 1) / 2;
        }
        return new int[]{ eleArr[0].value , largest(eleArr[0].comparedValues) };
    }

    private int largest(List<Integer> list) {
        int largest = list.get(0);
        for (int ele : list) {
            largest = Math.max(ele, largest);
        }
        return largest;
    }

    private Element[] convert(int[] array) {
        Element[] eleArr = new Element[array.length];
        for (int i = 0; i < array.length; i++) {
            eleArr[i] = new Element(array[i]);
        }
        return eleArr;
    }

    private void compareAndSwap(Element[] arr, int n) {
        for (int i = 0; i < n/2; i++) {
            if (arr[i].value < arr[n - 1 - i].value) {
                swap(arr, i, n - 1 - i);
            }
            arr[i].comparedValues.add(arr[n - 1 - i].value);
        }
    }

    private void swap(Element[] arr, int a, int b) {
        Element tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
