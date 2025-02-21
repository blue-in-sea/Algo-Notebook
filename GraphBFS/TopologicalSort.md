# Topological Sort

Topological sort is an algorithm used in graph theory to linearly order the vertices of a directed acyclic graph (DAG) such that for every directed edge \( (u, v) \), vertex \( u \) comes before vertex \( v \) in the ordering. This ordering is useful in scenarios where tasks or events have dependencies, such as scheduling, build systems, or course prerequisites.

---

## Key Concepts

1. **Directed Acyclic Graph (DAG):**
   - A graph with directed edges and no cycles.
   - Topological sort is only applicable to DAGs.

2. **Indegree:**
   - The number of edges directed into a vertex.
   - Vertices with an indegree of 0 can be starting points for the sort.

3. **Linear Ordering:**
   - The output of the topological sort is a sequence of vertices where all dependencies are satisfied.

## Algorithm

#### BFS

```
indegree = an array indicating indegrees for each node
neighbours = a HashMap recording neighbours of each node
queue = []
for i in indegree:
    if indegree[i] == 0:
        queue.append(i)
		
while !queue.empty():
    node = queue.dequeue()
    for neighbour in neighbours[node]:
        indegree[neighbour] -= 1
        if indegree[neighbour] == 0:
            queue.append(neighbour)

```

#### DFS
