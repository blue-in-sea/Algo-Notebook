### Topological Sort
Topological sorting is a linear ordering of vertices in a Directed Acyclic Graph (DAG). 
Given a directed acyclic graph (DAG), a topological sort is a linear ordering of all vertices such that for any edge (u, v), u comes before v.

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
