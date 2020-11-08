class Solution {
    // version - 1
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < 1001; i++) map.put(i, new ArrayList<>());

        // "But we need the last possible edge that will form a cycle, so we can just set it to ret and move on without adding it."
        // I think the first edge that form a cycle will be the last possible edge, 
        // so you can just return edge if dfs(u,v,0,graph) == true
        for (int[] edge : edges) {
            int v = edge[0], u = edge[1];
            // But we need the last possible edge that will form a cycle, 
            // so we can just set it to ret and move on without adding it.
            if (dfsFindCycle(map, u, v, 0)) {
                return edge;
            } else {
                if (!map.containsKey(v)) map.put(v, new ArrayList<>());
                map.get(v).add(u);

                if (!map.containsKey(u)) map.put(u, new ArrayList<>());
                map.get(u).add(v);
            }
        }

        return null;
    }

    // dfs check cycle node
    private boolean dfsFindCycle(Map<Integer, List<Integer>> graph, int curr, int next, int parent) {
        // base case 
        if (curr == next) return true;

        for (int nei : graph.get(curr)) {
            if (nei == parent) continue;
            if (dfsFindCycle(graph, nei, next, curr)) {
                return true;
            }
        }

        return false;
    }
    
    // version - 2
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < 1001; i++) map.put(i, new ArrayList<>());

        int[] ret = null;
        
        for (int[] edge : edges) {
            int v = edge[0], u = edge[1];
            // But we need the last possible edge that will form a cycle, 
            // so we can just set it to ret and move on without adding it.
            if (dfsFindCycle(map, u, v, 0)) {
                ret = edge;
            } else {
                if (!map.containsKey(v)) map.put(v, new ArrayList<>());
                map.get(v).add(u);

                if (!map.containsKey(u)) map.put(u, new ArrayList<>());
                map.get(u).add(v);
            }
        }

        return ret;
    }

    // dfs check cycle node
    private boolean dfsFindCycle(Map<Integer, List<Integer>> graph, int curr, int next, int parent) {
        // base case 
        if (curr == next) return true;

        for (int nei : graph.get(curr)) {
            if (nei == parent) continue;
            if (dfsFindCycle(graph, nei, next, curr)) {
                return true;
            }
        }

        return false;
    }
}
