public class AllSubsetsOfSizeK {
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

    private void DFS(List<String> list, StringBuilder sb,
                     int index, int k, char[] array) {
        // base case: we have picked k elements, we add the subset
        // to the result, and return
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
}
