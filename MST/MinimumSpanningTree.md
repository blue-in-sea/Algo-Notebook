# Minimum Spanning Tree (MST)

* Given a graph G = (V, E) we want to find a Minimum Spanning Tree in the graph (it may not be unique).
* A **minimum spanning tree** is a subset of the edges which connect all vertices in the graph with the minimal total edge cost.
* LC: https://leetcode.com/problem-list/minimum-spanning-tree/

---

## Key Properties of MST

1. **Connects all vertices**: The MST spans all the vertices in the graph.
2. **No cycles**: It is a tree, so it contains no cycles.
3. **Minimum total weight**: The sum of the edge weights is the smallest possible among all spanning trees.

---

## Algorithms to Find MST

Two of the most common algorithms for finding an MST are:

### 1. Kruskal's Algorithm

- **Approach**:
  - Sort all the edges in the graph in non-decreasing order of their weight.
  - Add the smallest edge to the MST, ensuring it doesn’t form a cycle.
  - Repeat until all vertices are connected.
- **Data Structures**:
  - Uses a **Union-Find (Disjoint Set Union)** data structure to detect cycles efficiently.
- **Time Complexity**: `O(E log E)` or `O(E log V)`, where `E` is the number of edges and `V` is the number of vertices.

### 2. Prim's Algorithm

- **Approach**:
  - Start with an arbitrary vertex.
  - At each step, add the smallest edge that connects a vertex in the MST to a vertex outside the MST.
  - Repeat until all vertices are included.
- **Data Structures**:
  - Uses a **Priority Queue (Min-Heap)** to efficiently select the next edge.
- **Time Complexity**:
  - `O(E log V)` Eager Prim's
  - `O(E log E)`Lazy Prim's     

---

## **Kruskal's Algorithm**

### Key Idea:
- Tutorial: [Union Find Kruskal's Algorithm](https://www.youtube.com/watch?v=JZBQLXgSGfs)

### Steps:
1. Sort edges by ascending edge weight.
2. Walk through the sorted edges and look at the two nodes the edge belongs to. If the nodes are already unified we don't include this edge (which it may create the cycle), otherwise we include it and unify the nodes.
3. The algorithm terminates when every edge has been processed or all the vertices have been unified.

---

# Comparison: Lazy Prim's vs Eager Prim's Algorithm

---

**Prim's Algorithm** for finding the **Minimum Spanning Tree (MST)** can be implemented in two main ways: **Lazy Prim's** and **Eager Prim's**. Both approaches use a priority queue (min-heap) to select edges, but they differ in how they manage the heap and handle edge updates.

---

## **Lazy Prim's Algorithm**

### Key Idea:
- The priority queue stores **all edges** that connect vertices in the MST to vertices outside the MST.
- When the smallest edge is extracted from the heap, it is added to the MST only if it connects to a vertex not yet in the MST.

### Steps:
1. Start with an arbitrary vertex.
2. Add all its edges to the priority queue.
3. Extract the smallest edge from the heap:
   - If it connects to a vertex not in the MST, add the edge to the MST and add all edges of the new vertex to the heap.
   - If it connects to a vertex already in the MST, discard it.
4. Repeat until all vertices are included in the MST.

### Pros:
- Simpler to implement.
- No need to update keys in the heap.

### Cons:
- The heap can contain many stale edges (edges connecting vertices already in the MST).
- Higher space complexity due to storing all edges.

### Time Complexity:
- `O(E log E)`, where `E` is the number of edges.

---

## **Eager Prim's Algorithm**

### Key Idea:
- The priority queue stores **vertices** (not edges) and their **key values** (the minimum weight of an edge connecting them to the MST).
- For each vertex, only the smallest edge connecting it to the MST is kept in the heap.
- When a vertex is added to the MST, its key is updated, and the heap is adjusted accordingly.

### Steps:
1. Start with an arbitrary vertex and initialize its key to 0.
2. Initialize all other vertices' keys to infinity.
3. Extract the vertex with the smallest key from the heap and add it to the MST.
4. For each edge of the extracted vertex, update the key of the adjacent vertex if the edge weight is smaller than its current key.
5. Repeat until all vertices are included in the MST.

### Pros:
- More efficient in terms of space and time.
- Avoids storing stale edges in the heap.

### Cons:
- More complex to implement due to the need to update keys in the heap.

### Time Complexity:
- `O(E log V)`, where `V` is the number of vertices.

---

## **Comparison**

| Feature                  | Lazy Prim's                          | Eager Prim's                         |
|--------------------------|--------------------------------------|--------------------------------------|
| **Heap Contents**         | Stores edges                        | Stores vertices and their keys       |
| **Stale Entries**         | May contain stale edges             | No stale entries                     |
| **Space Complexity**      | `O(E)`                              | `O(V)`                               |
| **Time Complexity**       | `O(E log E)`                        | `O(E log V)`                         |
| **Implementation**        | Simpler                             | More complex                         |
| **Best Use Case**         | Sparse graphs                       | Dense graphs                         |

---

## **Example**

Consider the following graph:

```
Vertices: A, B, C, D
Edges:
A-B: 1
A-C: 4
A-D: 3
B-C: 2
B-D: 5
C-D: 6
```
Graph:
```
      A
/     |   \
1     4   3
/     |    \
B--2--C   /
 \  |    /
  5 6   /
   \|/
    D
```

MST:
```
A
/ \
1   3
/     \
B       D
 \
  2
   \
    C
```


### Lazy Prim's Execution:
```
1. Start with A. Add edges (A-B:1), (A-C:4), (A-D:3) to the heap.
2. Extract A-B (1). Add B to MST. Add edges (B-C:2), (B-D:5) to the heap.
3. Extract B-C (2). Add C to MST. Add edge (C-D:6) to the heap.
4. Extract A-D (3). Add D to MST.
5. Discard stale edges (A-C:4, B-D:5, C-D:6).
```

### Eager Prim's Execution:
```
1. Start with A (key=0). Initialize B, C, D with keys=∞.
2. Extract A. Update keys of B (1), C (4), D (3).
3. Extract B (key=1). Update key of C (2).
4. Extract C (key=2). Update key of D (6).
5. Extract D (key=3).
```

---

## **When to Use Which?**
- Use **Lazy Prim's** for sparse graphs (where `E ≈ V`) because it is simpler to implement and the overhead of storing extra edges is minimal.
- Use **Eager Prim's** for dense graphs (where `E ≈ V^2`) because it is more space-efficient and avoids processing stale edges.
