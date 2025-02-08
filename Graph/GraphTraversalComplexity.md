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

---

# Time and Space Complexity of Adjacency Matrix and List for Graph Representation

When representing a graph, the choice between an adjacency matrix and an adjacency list has significant implications for both time and space complexity. Here's a breakdown:

---

## **1. Adjacency Matrix**
An adjacency matrix is a 2D array of size \( V \times V \), where \( V \) is the number of vertices in the graph. The entry at \( [i][j] \) is 1 (or the weight) if there is an edge between vertex \( i \) and vertex \( j \), and 0 (or infinity) otherwise.

### **Space Complexity**
- **Worst Case:** \( O(V^2) \)
  - The matrix explicitly stores all possible edges, even if most are absent (e.g., in sparse graphs).

### **Time Complexity**
- **Check if there is an edge between two vertices:** \( O(1) \)
  - Directly access the matrix entry.
- **Find all adjacent vertices of a vertex:** \( O(V) \)
  - Scan the entire row for the vertex.
- **Add/Remove an edge:** \( O(1) \)
  - Update the matrix entry.
- **Add/Remove a vertex:** \( O(V^2) \)
  - Resizing the matrix requires copying all data.

---

## **2. Adjacency List**
An adjacency list uses an array of linked lists (or dynamic arrays) to store only the existing edges. Each vertex has a list of its adjacent vertices.

### **Space Complexity**
- **Worst Case:** \( O(V + E) \)
  - \( V \) for the array of vertices, and \( E \) for the total number of edges.
  - More space-efficient for sparse graphs (where \( E \ll V^2 \)).

### **Time Complexity**
- **Check if there is an edge between two vertices:** \( O(\text{degree of } u) \)
  - Traverse the list of one vertex to check for the other.
  - In the worst case, \( O(V) \) (if the vertex is connected to all others).
- **Find all adjacent vertices of a vertex:** \( O(\text{degree of } u) \)
  - Directly access the list of the vertex.
- **Add/Remove an edge:** \( O(1) \) (if no duplicates are allowed)
  - Append to the list (or remove from it).
- **Add/Remove a vertex:** \( O(1) \) (amortized)
  - Add a new list or remove an existing one.

---

## **Comparison Summary**

| Operation                  | Adjacency Matrix | Adjacency List       |
|----------------------------|------------------|----------------------|
| **Space Complexity**       | \( O(V^2) \)     | \( O(V + E) \)       |
| **Check Edge (u, v)**      | \( O(1) \)       | \( O(\text{degree of } u) \) |
| **Find Adjacent Vertices** | \( O(V) \)       | \( O(\text{degree of } u) \) |
| **Add/Remove Edge**        | \( O(1) \)       | \( O(1) \)           |
| **Add/Remove Vertex**      | \( O(V^2) \)     | \( O(1) \)           |

---

## **When to Use Which?**
- **Adjacency Matrix:**
  - Best for dense graphs (where \( E \approx V^2 \)).
  - Frequent edge existence checks.
- **Adjacency List:**
  - Best for sparse graphs (where \( E \ll V^2 \)).
  - Frequent traversal of adjacency lists.

The choice depends on the graph's density and the operations you need to perform frequently.
