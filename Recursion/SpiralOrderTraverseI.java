/**
 * 121. Spiral Order Traverse I
 * Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner.
 * Return the list of traversal sequence.
 *
 * Assumptions
 * The 2D array is not null and has size of N * N where N >= 0
 *
 * { {1,  2,  3},
 *   {4,  5,  6},
 *   {7,  8,  9}}
 *
 * the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]
 */

public class SpiralOrderTraverseI {
    public List<Integer> spiral(int[][] matrix) {
        // assume the 2D array is not null and has size of N * N where N >= 0
        List<Integer> result = new ArrayList<>();
        dfs(result, 0, matrix.length, matrix);
        return result;
    }

    private void dfs(List<Integer> result, int offset, int size, int[][] matrix) {
        // base case: where there is only 1 or 0 elements left
        if (size == 0) {
            return;
        }
        // do not miss this base case
        if (size == 1) {
            result.add(matrix[offset][offset]);
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            result.add(matrix[offset][offset + i]);
        }
        for (int i = 0; i < size - 1; i++) {
            result.add(matrix[offset + i][offset + size - 1]);
        }
        for(int i = size - 1; i >= 1; i--) {
            result.add(matrix[offset + size - 1][offset + i]);
        }
        for (int i = size - 1; i >= 1 ; i--) {
            result.add(matrix[offset + i][offset]);
        }
        dfs(result, offset + 1, size - 2, matrix);
    }
}
