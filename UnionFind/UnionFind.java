/**
 * Union-Find, known as Disjoint Set Union (DSU), is a data structure that keeps track of elements partitioned
 * into a number of disjoint (non-overlapping) subsets.
 *
 * Find: Determine which subset a particular element is in.
 *       This can be used to determine if two elements are in the same subset, or graph-component.
 *
 * Union: Join two subsets into a single subset.
 */

/**
 * 1. Data Struct
 * Parent Array: Tracks the parent of each element.
 * Initially, each element is its own parent.
 *
 * Rank Array: Tracks the depth of the tree for each root.
 * Used to keep the tree flat during the union operation.
 *
 * 2. APIs / Operations
 * Find() - Recursively finds the root of the set containing x.
 *          with path compression to archive armotized complexity
 *
 * Union() - Merges the sets containing x and y.
 *           union by rank to attach the smaller tree to the root of the larger tree.
 *
 * Connected() - Checks if two elements are in the same set by comparing their roots.
 */

/**
 * Operation	Time Complexity (Amortized)	Space Complexity
 * Find           	O(α(n))                 	O(n)
 * Union           	O(α(n))                 	O(n)
 * Connected        O(α(n))                 	O(n)
 */
public class UnionFind {
  
    private int[] parent;
    private int[] rank;

    // Constructor
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i; // Each element is its own parent
            rank[i] = 1;   // Each set has a rank of 1
        }
    }

    // Find the root of the set containing x (with path compression)
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union the sets containing x and y (with union by rank)
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    }

    // Check if x and y are in the same set
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
