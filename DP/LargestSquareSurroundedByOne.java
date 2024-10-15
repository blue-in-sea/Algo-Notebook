/**
 * 637. Largest Square Surrounded By One
 * Determine the largest square surrounded by 1s in a binary matrix (a binary matrix only contains 0 and 1), 
 * return the length of the largest square.
 *
 * The given matrix is guaranteed to be of size M * N, where M, N >= 0
 *
 * {{1, 0, 1, 1, 1},
 *  {1, 1, 1, 1, 1},
 *  {1, 1, 0, 1, 0},
 *  {1, 1, 1, 1, 1},
 *  {1, 1, 1, 0, 0}}
 */
public class LargestSquareSurroundedByOne {
    public int largestSquareSurroundedByOne(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int result = 0;
        int M = matrix.length;
        int N = matrix[0].length;

        int[][] left = new int[M + 1][N + 1];
        int[][] up = new int[M + 1][N + 1];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) {
                    left[i + 1][j + 1] = left[i + 1][j] + 1;
                    up[i + 1][j + 1] = up[i][j + 1] + 1;
                    // the max length of surounnded by 1 matrix with rightbottom
                    // position matric[i][j] is determine by the min value of
                    // left[i+1][j+1] and up[i+1][j+1],
                    // and we check one by one all the possible lengths if it can
                    // provide the actual matrix.
                    // we only need to check the longest left arm length of righttop
                    // cell, and the longest up arm length of the left bottom cell.

                    for (int maxLength = Math.min(left[i + 1][j + 1], up[i + 1][j + 1]); maxLength >= 1; maxLength--) {
                        if (left[i + 2 - maxLength][j + 1] >= maxLength && up[i + 1][j + 2 - maxLength] >= maxLength) {
                            result = Math.max(result, maxLength);
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }
}
