/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class TopologicalSorting {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        
        // initialize indgree map <K, V> : <Node : InDegree>
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        for (DirectedGraphNode n : graph) {
            for (DirectedGraphNode nei : n.neighbors) {
                map.put(nei, map.getOrDefault(nei, 0) + 1);
            }
        }
        
        // BFS 
        Queue<DirectedGraphNode> q = new LinkedList<>();
        // put InDegree = 0's node into queue to start our BFS
        for (DirectedGraphNode n : graph) {
            if (!map.containsKey(n)) {
                q.offer(n);
                res.add(n);
            }
        }
        
        while (!q.isEmpty()) {
            DirectedGraphNode cur = q.poll();
             
            for (DirectedGraphNode nei : cur.neighbors){
                map.put(nei, map.get(nei) - 1);
                
                if (map.get(nei) == 0) {
                    q.offer(nei);
                    res.add(nei);
                }
                 
            }
        }
        
        return res;
    }
}
