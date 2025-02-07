# Graph Traversal Time & Space Complexity in V, E

When analyzing the time and space complexity of graph traversal algorithms, such as Depth-First Search (DFS) and Breadth-First Search (BFS), we typically express the complexity in terms of \( V \) (the number of vertices) and \( E \) (the number of edges) in the graph.

---

## Time Complexity

### DFS and BFS
- Both DFS and BFS visit each vertex once and each edge once.
- Therefore, the time complexity is \( O(V + E) \).

**Explanation**:
- Visiting all vertices takes \( O(V) \) time.
- Visiting all edges takes \( O(E) \) time.
- Since \( V \) and \( E \) are independent, we add them together.

### Adjacency Matrix vs. Adjacency List
- If the graph is represented using an **adjacency matrix**, the time complexity increases to \( O(V^2) \) because checking all possible edges for each vertex takes \( O(V) \) time per vertex.
- If the graph is represented using an **adjacency list**, the time complexity remains \( O(V + E) \).

---

## Space Complexity

### DFS
- The space complexity of DFS is \( O(V) \) in the worst case.
- This is due to the recursion stack (or explicit stack in an iterative implementation) that can grow to the depth of the graph, which is \( O(V) \) in the worst case (e.g., a straight-line graph).

### BFS
- The space complexity of BFS is \( O(V) \) in the worst case.
- This is due to the queue that stores the vertices to be visited, which can grow to the width of the graph, which is \( O(V) \) in the worst case (e.g., a star-shaped graph).

---

## Summary
- **Time Complexity**: \( O(V + E) \) for both DFS and BFS when using an adjacency list.
- **Space Complexity**: \( O(V) \) for both DFS and BFS.

These complexities assume that the graph is represented efficiently (e.g., using an adjacency list) and that the traversal is implemented optimally.
