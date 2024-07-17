/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder
 * is the postorder traversal of the same tree, construct and return the binary tree.
 *
 *         3
 *       /   \
 *     9       20
 *           /  \
 *         15     7
 *
 * (Solution) DFS
 *  * Key: inorder sequence used to define left-subtree & right subtree
 *                            R
 * postorder: [9, 15, 7, 20   |3]
 * inorder:   [9, |3|  15, 20, 7]
 *         L-tree      R-tree
 *
 *                      R
 * postorder: [15, 7   |20]
 * inorder:   [15, |20| 7]
 *          L-tree      R-tree
 */
