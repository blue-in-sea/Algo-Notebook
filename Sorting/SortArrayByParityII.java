package Sorting;

public class SortArrayByParityII {
    // 为数组的偶数下标部分和奇数下标部分分别维护指针 i, j
    // 随后，在每一步中，如果 A[i] 为奇数，则不断地向前移动 j
    //（每次移动两个单位），直到遇见下一个偶数。
    // 此时，可以直接将 A[i] 与 A[j] 交换。我们不断进行这样的过程，
    // 最终能够将所有的整数放在正确的位置上。
    // Time: O(N), no extra Space
    public int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        int j = 1; // odd
        for (int i = 0; i < n; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }   
        return A;
    }

    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
