/**
 * 332. Reconstruct Itinerary
 * You are given a list of airline tickets where tickets[i] = [start_i, end_i] represent the departure and the arrival airports of one flight.
 * Reconstruct the itinerary in order and return it.
 *
 * Requirement:
 * 1. Always start with "JFK"
 * 2. For multiple valid itineraries, return the route with the smallest lexical order
 *    (i.e.) ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"]
 * 3. All tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: ["JFK","SFO","ATL","JFK","ATL","SFO"] is another possible reconstruction;
 *               but it is larger in lexical order.
 */
class ReconstructItinerary {
    /**
     * The problem can be modelled as a directed graph:
     * Vertex -> airport
     * Edge   -> ticket[start, end] with direction
     *
     * Find an Eulerian Path is a list of all edges in a graph in a sequence such that
     * you can go from point A to all other nodes in the graph multiple times,
     * as long as an edge is not visited.
     */
    
    // Time: O(E * log(E/V)) where V vertex, E edges
    // Space: O(V + E)
    public List<String> findItinerary(List<List<String>> tickets) {
        // corner case
        if (tickets == null || tickets.size() == 0) {
            return null;
        }

        // build graph: <key = Node, value = List of children sorting by lexical order>
        Map<String, PriorityQueue<String>> graph = buildGraph(tickets);
        // construct a path when traversing the graph
        List<String> route = new ArrayList<>();
        dfs("JFK", route, graph);
        return route;
    }

    public void dfs(String airport, List<String> route, Map<String, PriorityQueue<String>> graph) {
        // if the we have node, and this node still has its children
        while(graph.containsKey(airport) && !graph.get(airport).isEmpty()) {
            dfs(graph.get(airport).poll(), route, graph);
        }
        route.add(0, airport);
    }

    // build the graph
    private Map<String, PriorityQueue<String>> buildGraph(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        }
        return graph;
    }
}

// Advance: Complexity Analysis
// 1. Time:
// - complexity of the DFS function would be O(E)
// - but before DFS, we use PQ to sort all edges, which dominates the overall complexity **
//   - 1. (worst cae) graph is unbalance:
//         connections are centered in a single airport, JFK will have half of the flights -> V >>> E/2
//         Time: O(VlogV) to sort all nodes this will dominate
//   - 2. (normal case) graph is balance
//         # of airports has equal # of outgoing flights -> 1 airport ~= E/(2V) of flights (include return flights)
//         Time: O(E * log(E/V))
// 2. Space:
// - graph size: O(V + E)
// - stack calls: O(E)
// - total: O(V + 2E) = O(V + E)
