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
public class AllSubsetsIIofSizeK {
    public List<String> subSetsIIOfSizeK(String set, int k) {
        List<String> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        char[] array = set.toCharArray();
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, 0, k, array);
        return res;
    }

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
