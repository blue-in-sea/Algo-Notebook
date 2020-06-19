public class NQueens {
    public List<List<String>> solveNQueens(int N) {
        List<List<String>> result = new ArrayList<>();
        if (N < 1) {
            return result;
        }
    
        Set<Integer> colSet = new HashSet<>();
        Set<Integer> tlSet = new HashSet<>();
        Set<Integer> brSet = new HashSet<>();
    
        List<String> path = new ArrayList<>();
        Helper(colSet, tlSet, brSet, result, path, 0, N);
        return result;
    }
    
    private void Helper(Set<Integer> colSet, Set<Integer> tlSet,
                 Set<Integer> brSet, List<List<String>> result, 
                 List<String> path, int index, int N) {
        
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
        
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < i; col++) sb.append('.');
            sb.append('Q');
            for (int col = i + 1; col < N; col++) sb.append('.');

            path.add(sb.toString());
            Helper(colSet, tlSet, brSet, result, path, index + 1, N);
            path.remove(path.size() - 1);
        
            colSet.remove(i);
            tlSet.remove(index - i);
            brSet.remove(index + i);
        }
    }
}
