/**
 * LaiCode 644. Common Elements In K Sorted Lists
 * Find all common elements in K sorted lists.
 *
 * The input and its elements are not null, and support fast random access.
 * There could be duplicate elements in each of the lists.
 *
 * Input = {{1, 2, 2, 3}, {2, 2, 3, 5}, {2, 2, 4}}, the common elements are {2, 2}.
 */
public class CommonElementsInKSortedLists {
    // General iterative solution: Two-Pointers, 同向而行，谁小移谁
    // Time: 2n * k = O(kn)
    // Space: 2n = O(n) 
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
        // assume input and its elements and not null, 
        // and they support fast random access
        List<Integer> result = input.get(0);
        int k = input.size();
        for (int i = 0; i < k; i++) {
            result = helper(result, input.get(i));
        }
        return result;
    }
    public List<Integer> helper(List<Integer> a, List<Integer> b) {
        // assume a and b are not null, and sorted in ascending order
        List<Integer> result = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()) {
            int compare = a.get(i).compareTo(b.get(j));
            if (compare == 0) {
                result.add(a.get(i));
                i++;
                j++;
            } else if (compare < 0) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}
