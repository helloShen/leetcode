/**
 * Leetcode - Algorithm - Construct Binary Tree from preorder and inorder traversal
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;


class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTreeV1(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) { return null; }
        List<Integer> inorderList = new ArrayList<>();
        for (int num : inorder) {
            inorderList.add(num);
        }
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int index = inorderList.indexOf(preorder[i]);
            if (index == -1) { return null; } // ERROR: find node in preorder but not inorder
            TreeNode cur = root;
            while (cur != null) {
                if (inorderList.indexOf(cur.val) < index) { // 新节点应当在这个节点的右子树
                    //System.out.println("\t Index of " + preorder[i] + " = " + index);
                    //System.out.println("\t Index of " + cur.val + " = " + inorderList.indexOf(cur.val));
                    if (cur.right == null) {
                        cur.right = new TreeNode(preorder[i]);
                        //System.out.println(preorder[i] + " become right of " + cur.val);
                        break;
                    } else {
                        cur = cur.right;
                    }
                } else { // 新节点应当在这个节点的左子树
                    if (cur.left == null) {
                        if (cur.right != null) { // ERROR: find conflit of preorder & inorder
                            return null;
                        } else {
                            cur.left = new TreeNode(preorder[i]);
                            //System.out.println(preorder[i] + " become left of " + cur.val);
                            break;
                        }
                    } else {
                        cur = cur.left;
                    }
                }
            }
        }
        return root;
    }
    public TreeNode buildTreeV2(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) { return null; }
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int index = indexOf(inorder,preorder[i]);
            if (index == -1) { return null; } // ERROR: find node in preorder but not inorder
            TreeNode cur = root;
            while (cur != null) {
                if (indexOf(inorder,cur.val) < index) { // 新节点应当在这个节点的右子树
                    //System.out.println("\t Index of " + preorder[i] + " = " + index);
                    //System.out.println("\t Index of " + cur.val + " = " + inorderList.indexOf(cur.val));
                    if (cur.right == null) {
                        cur.right = new TreeNode(preorder[i]);
                        //System.out.println(preorder[i] + " become right of " + cur.val);
                        break;
                    } else {
                        cur = cur.right;
                    }
                } else { // 新节点应当在这个节点的左子树
                    if (cur.left == null) {
                        if (cur.right != null) { // ERROR: find conflit of preorder & inorder
                            return null;
                        } else {
                            cur.left = new TreeNode(preorder[i]);
                            //System.out.println(preorder[i] + " become left of " + cur.val);
                            break;
                        }
                    } else {
                        cur = cur.left;
                    }
                }
            }
        }
        return root;
    }
    // return -1 if not exist
    public int indexOf(int[] nums, int n) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n) { return i; }
        }
        return -1;
    }
    public TreeNode buildTreeV3(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) { return null; }
        Map<Integer,Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int index = inorderMap.get(preorder[i]);
            if (index == -1) { return null; } // ERROR: find node in preorder but not inorder
            TreeNode cur = root;
            while (cur != null) {
                if (inorderMap.get(cur.val) < index) { // 新节点应当在这个节点的右子树
                    //System.out.println("\t Index of " + preorder[i] + " = " + index);
                    //System.out.println("\t Index of " + cur.val + " = " + inorderList.indexOf(cur.val));
                    if (cur.right == null) {
                        cur.right = new TreeNode(preorder[i]);
                        //System.out.println(preorder[i] + " become right of " + cur.val);
                        break;
                    } else {
                        cur = cur.right;
                    }
                } else { // 新节点应当在这个节点的左子树
                    if (cur.left == null) {
                        if (cur.right != null) { // ERROR: find conflit of preorder & inorder
                            return null;
                        } else {
                            cur.left = new TreeNode(preorder[i]);
                            //System.out.println(preorder[i] + " become left of " + cur.val);
                            break;
                        }
                    } else {
                        cur = cur.left;
                    }
                }
            }
        }
        return root;
    }
    // 分治法，只对合法的BST有效
    public TreeNode buildTreeV4(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) { return null; }
        TreeNode root = new TreeNode(preorder[0]);
        dac(preorder,0,preorder.length-1,inorder,0,inorder.length-1,root);
        return root;
    }
    // range are inclusive in both side
    public void dac(int[] preorder, int preLo, int preHi, int[] inorder, int inLo, int inHi, TreeNode root) {
        if (preLo >= preHi) { return; }
        int firstGreater = firstGreater(preorder, preLo+1, preHi, preorder[preLo]);
        System.out.println("In Preorder: From " + preorder[preLo] + ", To " + preorder[preHi] + ", First Greater than " + preorder[preLo] + " is " + firstGreater);
        int indexInInorder = indexOf(inorder, inLo, inHi, preorder[preLo]); // 当前节点在inorder中的位置
        System.out.println("In Inorder: From " + inorder[inLo] + ", To " + inorder[inHi] + ", index of " + preorder[preLo] + " is " + indexInInorder);
        if (firstGreater != preLo+1) { // 左子树不为空
            root.left = new TreeNode(preorder[preLo+1]);
            if (firstGreater > preLo+1) { // 左子树不为空，右子树不为空
                root.right = new TreeNode(preorder[firstGreater]);
                dac(preorder,preLo+1,firstGreater-1,inorder,inLo,indexInInorder-1,root.left);
                dac(preorder,firstGreater,preHi,inorder,indexInInorder+1,inHi,root.right);
            } else if (firstGreater == -1) { // 左子树不为空，右子树为空
                dac(preorder,preLo+1,preHi,inorder,inLo,indexInInorder-1,root.left);
            }
        } else { // 左子树为空，右子树不为空
            root.right = new TreeNode(preorder[preLo+1]);
            dac(preorder,firstGreater,preHi,inorder,indexInInorder+1,inHi,root.right);
        }
    }
    // return the index of target number in the array
    // return -1 if target not found
    // [lo,hi], both sides are inclusive
    public int indexOf(int[] nums, int lo, int hi, int target) {
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == target) { return i; }
        }
        return -1;
    }
    // return the index of first number larger than target
    // return -1 if target not found
    // [lo,hi], both sides are inclusive
    public int firstGreater(int[] nums, int lo, int hi, int target) {
        for (int i = lo; i <= hi; i++) {
            if (nums[i] > target) { return i; }
        }
        return -1;
    }
    public TreeNode buildTreeV5(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) { return null; }
        return buildTreeRecurV1(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    public TreeNode buildTreeRecurV1(int[] preorder, int preLo, int preHi, int[] inorder, int inLo, int inHi) {
        if (preLo > preHi) { return null; }
        System.out.println("preLo = " + preLo + ", proHi = " + preHi + ", inLo = " + inLo + ", inHi = " + inHi);
        TreeNode root = new TreeNode(preorder[preLo]);
        int indexInInorder = indexOf(inorder,inLo,inHi,preorder[preLo]);
        int leftSize = indexInInorder - inLo;
        int rightSize = inHi - indexInInorder;
        System.out.println("Index of " + preorder[preLo] + " = " + indexInInorder);
        root.left = buildTreeRecurV1(preorder,preLo+1,preLo+leftSize,inorder,inLo,indexInInorder-1);
        root.right = buildTreeRecurV1(preorder,preLo+leftSize+1,preHi,inorder,indexInInorder+1,inHi);
        return root;
    }
    public TreeNode buildTreeV6(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) { return null; }
        Map<Integer,Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        int[] mapArray = new int[preorder.length];
        for (int i = 0; i < preorder.length; i++) {
            mapArray[i] = inorderMap.get(preorder[i]);
        }
        return buildTreeRecurV2(preorder,0,preorder.length-1,inorder,0,inorder.length-1,mapArray);
    }
    public TreeNode buildTreeRecurV2(int[] preorder, int preLo, int preHi, int[] inorder, int inLo, int inHi, int[] mapArray) {
        if (preLo > preHi) { return null; }
        System.out.println("preLo = " + preLo + ", proHi = " + preHi + ", inLo = " + inLo + ", inHi = " + inHi);
        TreeNode root = new TreeNode(preorder[preLo]);
        int indexInInorder = mapArray[preLo];
        int leftSize = indexInInorder - inLo;
        int rightSize = inHi - indexInInorder;
        System.out.println("Index of " + preorder[preLo] + " = " + indexInInorder);
        root.left = buildTreeRecurV2(preorder,preLo+1,preLo+leftSize,inorder,inLo,indexInInorder-1,mapArray);
        root.right = buildTreeRecurV2(preorder,preLo+leftSize+1,preHi,inorder,indexInInorder+1,inHi,mapArray);
        return root;
    }
    public TreeNode buildTreeV7(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) { return null; }
        Map<Integer,Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) { inorderMap.put(inorder[i],i); }
        int[] mapArray = new int[preorder.length];
        for (int i = 0; i < preorder.length; i++) { mapArray[i] = inorderMap.get(preorder[i]); }
        return buildTreeRecurV3(preorder,0,0,inorder.length-1,mapArray);
    }
    public TreeNode buildTreeRecurV3(int[] preorder, int cur, int lo, int hi, int[] inIndex) {
        if (lo > hi) { return null; }
        TreeNode root = new TreeNode(preorder[cur]);
        int indexInInorder = inIndex[cur];
        int leftSize = indexInInorder - lo;
        int rightSize = hi - indexInInorder;
        root.left = buildTreeRecurV3(preorder,cur+1,lo,indexInInorder-1,inIndex);
        root.right = buildTreeRecurV3(preorder,cur+leftSize+1,indexInInorder+1,hi,inIndex);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) { return null; }
        Map<Integer,Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) { inorderMap.put(inorder[i],i); }
        return buildTreeRecur(preorder,0,0,inorder.length-1,inorderMap);
    }
    public TreeNode buildTreeRecur(int[] preorder, int cur, int lo, int hi, Map<Integer,Integer> inorderMap) {
        if (lo > hi) { return null; }
        TreeNode root = new TreeNode(preorder[cur]);
        int indexInInorder = inorderMap.get(preorder[cur]);
        int leftSize = indexInInorder - lo;
        int rightSize = hi - indexInInorder;
        root.left = buildTreeRecur(preorder,cur+1,lo,indexInInorder-1,inorderMap);
        root.right = buildTreeRecur(preorder,cur+leftSize+1,indexInInorder+1,hi,inorderMap);
        return root;
    }
    private static ConstructBinaryTreeFromPreorderAndInorderTraversal test = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
    private static void testBuildTree() {
        TreeNode tree = TreeNode.randomBST(20);
        System.out.println("Original Tree: " + tree.bfs());
        System.out.println("preorder = " + Arrays.toString(tree.preOrderToArray()));
        System.out.println("inorder = " + Arrays.toString(tree.toArray()));
        int[] preorder = tree.preOrderToArray();
        int[] inorder = tree.toArray();
        TreeNode builtTree = test.buildTree(preorder,inorder);
        System.out.println("Built Tree: " + builtTree.bfs());
    }
    private static void observation() {
        TreeNode tree = TreeNode.randomBST(20);
        System.out.println("Preorder: " + tree.preOrder());
        System.out.println("Inorder: " + tree);
        System.out.println("BFS: " + tree.bfs());
    }
    public static void main(String[] args) {
        //observation();
        testBuildTree();
    }
}
