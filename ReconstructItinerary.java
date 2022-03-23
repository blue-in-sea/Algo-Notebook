class ReconstructItinerary {
   /**
     * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
     *
     * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
     *
     * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
     * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
     */
  
    
    List<String> route = new ArrayList<>();
   Map<String, PriorityQueue<String>> targets = new HashMap<>();
   
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        }
        dfs("JFK");
        return route;
    }
    
    public void dfs(String airport) {
       // if the we have node, and this node still has its nei
        while(targets.containsKey(airport) && !targets.get(airport).isEmpty()) {
            dfs(targets.get(airport).poll());
        }
        route.add(0, airport);
    }
   
   
   /**
     * The problem can be modelled as a graph, with each node representing a city, and each ticket representing an edge from source city to destination city.
     * The problem asks us to find a connected sequence(path) consisting of all edges in a graph, starting with "JFK".
     *
     * Our general intuition is to jump to algorithms like DFS/BFS. The DFS/BFS algorithms help us traverse all nodes in the graph, but fail to cover all edges in the graph
     *
     * This is where Eulerian Path comes into play. An Eulerian Path is a list of all edges in a graph in a sequence such that you can go from point A to all other nodes in the graph multiple times,
     * as long as an edge is not visited.
     *
     * https://leetcode.com/problems/reconstruct-itinerary/discuss/1161401/C%2B%2B-Explaination%3A-Transition-from-DFS-to-Eulerian-Path
     *
     */
}
