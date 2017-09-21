/**
 * This structure can be used to represent a node in binary tree.
 */

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

void treenode_init(struct TreeNode *, int);
