/**
 * 643. All Permutations of Subsets
 * Given a string with no duplicate characters, return a list with all permutations of the string and all its subsets.
 *
 * Set = “abc”, all permutations are [“”, “a”, “ab”, “abc”, “ac”, “acb”, “b”, “ba”, “bac”, “bc”, “bca”, “c”, “cb”, “cba”, “ca”, “cab”].
 * Set = “”, all permutations are [“”].
 * Set = null, all permutations are [].
 */
public class AllPermutationsofSubsets {
    public List<String> allPermutationsOfSubsets(String set) {
        // Return all permutations of the string and all its subsets

        // Complexity: https://chat.deepseek.com/a/chat/s/cecb11c4-7b80-4fbe-8f79-2356769405cc
        // Time: O(n! * n)
        // Space: O(n! * n)
        List<String> list = new ArrayList<>();
        if (set == null) {
            return list;
        }
        char[] array = set.toCharArray();
        DFS(list, 0, array);
        return list;
    }
    private void DFS(List<String> list, int index, char[] array) {
        // return nodes of all level in our base case
        // but print the solution from (0, index) on each recusion level
        list.add(new String(array, 0, index));
        for (int i = index; i < array.length; i++) {
            swap(array, i, index);
            DFS(list, index + 1, array);
            swap(array, i, index);
        }
    }
    private void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
