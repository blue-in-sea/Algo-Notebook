import java.util.*;
/**
 * Given an array of integers, sort the array in ascending order and return it.
 * Time: O(n2)
 * Space: O(1)
 * https://www.programiz.com/dsa/bubble-sort
 */
class BubbleSort {
    /**
     * bubbleSort(array)
     *   for i <- 1 to sizeOfArray - 1
     *     for j <- 1 to sizeOfArray - 1 - i
     *       if leftElement > rightElement
     *         swap leftElement and rightElement
     * end bubbleSort
     */
    static void bubbleSort(int array[]) {
        int size = array.length;

        // loop to access each array element
        for (int i = 0; i < size - 1; i++)

            // loop to compare array elements
            for (int j = 0; j < size - i - 1; j++)

                // compare two adjacent elements
                // change > to < to sort in descending order
                if (array[j] > array[j + 1]) {

                    // swapping occurs if elements
                    // are not in the intended order
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
    }

    public static void main(String args[]) {
        int[] data = { -2, 45, 0, 11, -9 }; // expect [-9, -2, 0, 11, 45]
        bubbleSort(data);
        System.out.println("Sorted Array in Ascending Order:");
        System.out.println(Arrays.toString(data));
    }
}
