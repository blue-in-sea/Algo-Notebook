// 427. Construct Quad Tree
// p.x >= box.center.x - (box.width) / 2 左边界
// p.x <= box.center.x + (box.width) / 2 右边界
// p.y <= box.center.y + (box.width) / 2 上边界
// p.y >= box.center.y - (box.width) / 2 下边界
/**
 *  ---- ---- ----  
 *      
 *        p (p.x, p,y)
 *  
 *  ---- ---- ----  
 */
/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/
class QuadTree {
    /**
     * Brutal Force: Recursion
     * 1. Iterate over all the values in the current matrix, with the top-left coordinate at (x1, y1) and the length of the side as length.
     *    (if all values are same, then create and return a leaf node with the same value)
     * 2. If all values are not the same, create a new node root, and then make recursive calls to the four sub-matrices:
     *   a. Top-Left     (x1, y1)
     *   b. Top-Right    (x1, y1 + length / 2)
     *   c. Bottom-Left  (x1 + length / 2, y1)
     *   d. Bottom-Right (x1 + length / 2, y1 + length / 2)
     * 3. Assign the nodes returned by these recursive calls as the respective child nodes of root
     * 4. Return root
     *
     */
    // Time: O(n^2 logn) (like merge sort) logn level; each level we have  N^2 cell to be build  
    // Space: O(logN) for stack calls
    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length);
    }

    private Node dfs(int[][] grid, int x1, int y1, int length) {
        // Return a leaf node if all values are the same.
        if (sameValue(grid, x1, y1, length)) {
            return new Node(grid[x1][y1] == 1, true);
        } else {
            Node root = new Node(false, false);

            // Recursive call for the four sub-matrices.
            root.topLeft = dfs(grid, x1, y1, length / 2);                            
            root.topRight = dfs(grid, x1, y1 + length / 2, length / 2);
            root.bottomLeft = dfs(grid, x1 + length / 2, y1, length / 2);
            root.bottomRight = dfs(grid, x1 + length / 2, y1 + length / 2, length / 2);

            return root;
        }
    }

    private boolean sameValue(int[][] grid, int x1, int y1, int length) {
        for (int i = x1; i < x1 + length; i++) {
            for (int j = y1; j < y1 + length; j++)
                if (grid[i][j] != grid[x1][y1])
                    return false;
        }
        return true;
    }

    // ******************************************************************************
    /**
     * Optimized: Recursion on 4 sub-matrics !!
     * 1. If length is one, return a new leaf node with value equal to the cell value at (x1, y1)
     * 2. Otherwise,
     *   a. Top-Left     (x1, y1)
     *   b. Top-Right    (x1, y1 + length / 2)
     *   c. Bottom-Left  (x1 + length / 2, y1)
     *   d. Bottom-Right (x1 + length / 2, y1 + length / 2)
     * 3. If all the four nodes returned by the above recursive calls are leaf nodes with the same value.
     *    Then return a new leaf node with the same value.
     * 4. Otherwise, return a non-leaf node with any value having child pointers pointing to the four above-returned nodes.
     */
    // Time: O(n^2) All the cells in the matrix will be iterated only once
    // Space: O(logN) for stack calls
    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length);
    }

    private Node dfs(int[][] grid, int x1, int y1, int length) {
        // Return a leaf node if the matrix size is one.
        if (length == 1) {
            return new Node(grid[x1][y1] == 1, true);
        }

        // Recursive calls to the four sub-matrices.
        Node topLeft = dfs(grid, x1, y1, length / 2);
        Node topRight = dfs(grid, x1, y1 + length / 2, length / 2);
        Node bottomLeft = dfs(grid, x1 + length / 2, y1, length / 2);
        Node bottomRight = dfs(grid, x1 + length / 2, y1 + length / 2, length / 2);

        // If the four returned nodes are leaf and have the same values
        // Return a leaf node with the same value.
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topRight.val == bottomLeft.val
                && bottomLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true);
        }

        // If the four nodes aren't identical, return a non-leaf node with corresponding child pointers.
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}
