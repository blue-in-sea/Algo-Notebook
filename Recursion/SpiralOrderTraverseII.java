/**
 * 122. Spiral Order Traverse II
 * Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. 
 * Return the list of traversal sequence.
 *
 * The 2D array is not null and has size of M * N where M, N >= 0
 *
 * { {1,  2,  3,  4},
 *   {5,  6,  7,  8},
 *   {9, 10, 11, 12} }
 *
 * the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
 */
public class SpiralOrderTraverseII {
    public List<Integer> spiral(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        // assume the matrix is not null and has size of M * N 
        // where M >= 0, N >= 0
        int rows = matrix.length;
        if (rows == 0) {
            return result;
        }
        int cols = matrix[0].length;
        int left = 0, right = cols - 1;
        int up = 0, down = rows - 1;
        // the base case is more complicate than the square matrix of size N * N 
        // 1. there is no element left
        // 2. there is one row left
        // 3. there is one col left
        while (left < right && up < down) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[up][i]);
            }
            for (int i = up + 1; i <= down - 1; i++) {
                result.add(matrix[i][right]);
            }
            for (int i = right; i >= left; i--) {
                result.add(matrix[down][i]);
            }
            for (int i = down - 1; i >= up + 1; i--) {
                result.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        // post-processing
        // 1. if there is nothing left
        if (left > right || up > down) {  // negation of our while-condition
            return result;
        }
        // 2. if there is one col left
        if (left == right) {
            for (int i = up; i <= down; i++) {
                result.add(matrix[i][left]);
            }
        } else {
            // 3. if there is one row left
            for (int i = left; i <= right; i++) {
                result.add(matrix[up][i]);
            }
        }

        return result;
    }
}
