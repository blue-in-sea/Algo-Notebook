class NQueensII {
    // Time: O(N!)
    // Space: O(N)
    public int totalNQueens(int N) {
        List<List<Integer>> result = new ArrayList<>();
        if (N < 1) {
            return 0;
        }
    
        Set<Integer> colSet = new HashSet<>();
        Set<Integer> tlSet = new HashSet<>();
        Set<Integer> brSet = new HashSet<>();
    
        List<Integer> path = new ArrayList<>();
        Helper(colSet, tlSet, brSet, result, path, 0, N);
        return result.size();
    }
    
    private void Helper(Set<Integer> colSet, Set<Integer> tlSet,
                 Set<Integer> brSet, List<List<Integer>> result,
                 List<Integer> path, int index, int N) {
                 
        if (index == N) {
            result.add(new ArrayList<>(path));
            return;
        }
    
        for (int i = 0; i < N; i ++) {
            if (colSet.contains(i) || tlSet.contains(index - i) || brSet.contains(index + i)) {
                continue;
            }
      
            colSet.add(i);
            tlSet.add(index - i);
            brSet.add(index + i);
        
            path.add(i);
            Helper(colSet, tlSet, brSet, result, path, index + 1, N);
            path.remove(path.size() - 1);
        
            colSet.remove(i);
            tlSet.remove(index - i);
            brSet.remove(index + i);
        }
    }
}
