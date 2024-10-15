/**
 * 264. Keep Distance For Identical Elements
 * Given an integer k, arrange the sequence of integers [1, 1, 2, 2, 3, 3, ...., k - 1, k - 1, k, k], such that the output integer array satisfy this condition:
 * Between each two i's, they are exactly i integers (for example: between the two 1s, there is one number, between the two 2's there are two numbers).
 *
 * If there does not exist such sequence, return null.
 * k is guaranteed to be > 0
 *
 * k = 3, The output = { 2, 3, 1, 2, 1, 3 }.
 */
public class KeepDistanceForIdenticalElements {
    // Method 3: k levels,
    // Time: O(k^k)
    public int[] keepDistance(int k) {
        // corner case
        if (k < 0) return null;

        int[] res = new int[k * 2];
        return dfs(res, k) ? res : null;
    }

    private boolean dfs(int[] res, int k) {
        // base case
        if (k == 0) return true;

        for (int i = 0; i + k + 1 < res.length; i++) {
            if (res[i] == 0 && res[i + k + 1] == 0) {
                res[i] = k;
                res[i + k + 1] = k;
                if (dfs(res, k - 1)) {
                    return true;
                }
                res[i] = 0;
                res[i + k + 1] = 0;
            }
        }

        return false;
    }
    
    // ===========================================
    // Method 1: used swap swap to generate all permutations
    public int[] keepDistance(int k) {
        // Assume K > 0
        int[] array = new int[2 * k];
        for (int i = 0; i < k; i++) {
            array[i * 2] = i + 1;
            array[i * 2 + 1] = i + 1;
        }
        // used[i] == true if and only if i is used once
        boolean[] used = new boolean[k + 1];
        return helper1(array, 0, used) ? array : null;
    }

    private boolean helper1(int[] array, int index, boolean[] used) {
        // base case
        if (index == array.length) {
            return true;
        }

        for (int i = index; i < array.length; i++) {
            int cur = array[i];
            if (!used[cur] || (index > cur && array[index - cur - 1] == cur)) {
                used[cur] = !used[cur];
                swap(array, index, i);
                if (helper1(array, index + 1, used)) {
                    return true;
                }
                swap(array, index, i);
                used[cur] = !used[cur];
            }
        }
        return false;
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    // ===========================================
    // Method 2: used another way to generate all permutations
    public int[] keepDistance(int k) {
        int[] array = new int[2 * k];
        int[] used = new int[k + 1];
        return helper2(array, 0, used) ? array : null;
    }

    private boolean helper2(int[] array, int index, int[] used) {
        if (index == array.length) {
            return true;
        }
        for (int i = 1; i < used.length; i++) {
            if (used[i] == 0 || (used[i] == 1 && index > i && array[index - i - 1] == i)) {
                array[index] = i;
                used[i]++;
                if (helper2(array, index + 1, used)) {
                    return true;
                }
                used[i]--;
            }
        }
        return false;
    }
}
