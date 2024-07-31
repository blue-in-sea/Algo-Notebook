/**
 * 125. Rotate Matrix
 * Rotate an N * N matrix clockwise 90 degrees.
 *
 * The matrix is not null and N >= 0
 * Examples
 *
 * { {1,  2,  3}
 *
 *   {8,  9,  4},
 *
 *   {7,  6,  5} }
 *
 * after rotation is
 *
 * { {7,  8,  1}
 *
 *   {6,  9,  2},
 *
 *   {5,  4,  3} }
 */
class RotateMatrix {
    // Method 1: Clockwise Rotation (In-place)
    // split into levels & for each level split it to 4 parts

    // Time: O(m * n) where total number of cells 
    // Space: O(1) in place
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) return;

        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i]; // bottom-left
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
}
