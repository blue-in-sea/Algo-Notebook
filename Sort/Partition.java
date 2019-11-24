public class Partition {
    public void partition(int[] array, int pivotIndex) {
        if (array == null || array.length <= 1) {
            return;
        }
        // 1. select partition pivot
        int pivot = array[pivotIndex];
        // 2. swap the pivot element to the rightmost position first
        int lastIndex = array.length - 1;
        swap(array, pivotIndex, lastIndex);
        // 3. partition process
        int left = 0;
        int right = lastIndex - 1;
        while (left <= right) {
            if (array[left] <= pivot) {
                left++;
            } else if (array[right] >= pivot) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }
        // 4. swap back the pivot element
        swap(array, left, lastIndex);
    }

    private void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    public static void main(String[] args) {
        Partition solution = new Partition();

        int[] array = null;
        solution.partition(array,0);
        System.out.println(Arrays.toString(array));

        array = new int[0];
        solution.partition(array,0);
        System.out.println(Arrays.toString(array));

        array = new int[] {3, 2, 1};
        solution.partition(array,0);
        System.out.println(Arrays.toString(array));

        array = new int[] {3, 2, 1, 2};
        solution.partition(array, 1);
        System.out.println(Arrays.toString(array));

        array = new int[] {4, 8, 5, 6, 3};
        solution.partition(array, 2);
        System.out.println(Arrays.toString(array));
    }
}
