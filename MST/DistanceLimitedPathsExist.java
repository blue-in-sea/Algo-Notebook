// https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths-ii/?envType=problem-list-v2&envId=minimum-spanning-tree

class DistanceLimitedPathsExist {
  
    TreeMap<Integer, int[]> map = new TreeMap<>();

    public DistanceLimitedPathsExist(int n, int[][] edgeList) {
        int[] f = new int[n];
        for (int i = 0; i < n; i++) f[i] = i;
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        for (int[] e: edgeList){
            f[find(f, e[0])] = find(f, e[1]);
            map.put(e[2], f.clone());
        }
    }
    
    public boolean query(int p, int q, int limit) {
        Integer version = map.lowerKey(limit);
        if (version == null) return false;
        int[] f = map.get(version);
        return find(f, p) == find(f, q);
    }
    
    int find(int[] f, int id){
        while(id != f[id]){
            f[id] = f[f[id]];
            id = f[id];
        }
        return id;
    }
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * DistanceLimitedPathsExist obj = new DistanceLimitedPathsExist(n, edgeList);
 * boolean param_1 = obj.query(p,q,limit);
 */
