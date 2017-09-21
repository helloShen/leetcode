/**
 * This file define some useful function of binary tree.
 * The structure is defined in util/TreeNode.h
 */

#include "../mystd.h"
#include "TreeNode.h"

/**
 * Structure must be initialized before being used.
 * DO NOT USE THIS FUCTION IN YOUR CODE!!! Use {} to initialize the structure as usual.
 * This function is wroten just to shows how to initialize a structure.
 */
void treenode_init(struct TreeNode *node, int n) {
    node->val = n;
    node->left = 0;
    node->right = 0;
}
