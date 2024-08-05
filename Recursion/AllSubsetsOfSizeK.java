/**
 * LaiCode 641. All Subsets II of Size K
 * Given a set of characters represented by a String, return a list containing all subsets of the characters whose
 * size is K. Notice that each subset returned will be sorted for deduplication.
 *
 * There could be duplicate characters in the original set.
 *
 *
 * Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].
 * Set = "abb", K = 2, all the subsets are [“ab”, “bb”].
 * Set = "abab", K = 2, all the subsets are [“aa”, “ab”, “bb”].
 * Set = "", K = 0, all the subsets are [""].
 * Set = "", K = 1, all the subsets are [].
 * Set = null, K = 0, all the subsets are [].
 */
public class AllSubsetsOfSizeK {
    // Time: O(2^k * k) 
    // Space: O(k) 
    public List<String> subSetsOfSizeK(String set, int k) {
        List<String> list = new ArrayList<>();
        if (set == null) {
            return list;
        }
        char[] array = set.toCharArray();
        StringBuilder sb = new StringBuilder();
        DFS(list, sb, 0, k, array);
        return list;
    }

    private void DFS(List<String> list, StringBuilder sb, int index, int k, char[] array) {
        // base case: we have picked k elements, we add the subset to the result, and return
        if (sb.length() == k) {
            list.add(sb.toString());
            return;
        }
        
        // base case: we've decided for all characters pick or not, we
        // reach the last index of the input array, we must return
        if (index == array.length) {
            return;
        }
        
        // 1. pick the char at the index i
        DFS(list, sb.append(array[index]), index + 1, k, array);
        sb.deleteCharAt(sb.length() - 1);
        // 2. not pick the char at the index i
        DFS(list, sb, index + 1, k, array);
    }

    // version 2
    private void dfs(List<String> res, StringBuilder sb, int index, int k, char[] array) {
        // base case: we have picked k elements, we add the subset 
        // to the result, and return 
        if (sb.length() == k) {
            res.add(sb.toString());
            return;
        }

        for (int i = index; i < array.length; i++) {
            if (i > index && array[i] == array[i - 1]) continue;
            sb.append(array[i]);
            dfs(res, sb, i + 1, k, array);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
