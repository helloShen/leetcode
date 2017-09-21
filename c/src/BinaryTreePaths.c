#include "mystd.h"
#include "util/TreeNode.h"

static char** binaryTreePaths(struct TreeNode *, int*);
static char **dfs(struct TreeNode *, const char *, int *);
static char *getlocalpath(int);
static char *appendpath(const char *, const char *);
static char **mergelist(char **, int, char **, int);
static void print(char **, int);

static void test_getlocalpath();
static void test_appendpath();
static void test_mergelist();

int main() {
    // structure must be initialized before being used.
    struct TreeNode one = {1};
    struct TreeNode two = {2};
    struct TreeNode three = {3};
    struct TreeNode five = {5};
    /**
     * if we have our own initializer, we can initialize the sturcture as follow.
     *
    struct TreeNode one;
    treenode_init(&one,1);
    struct TreeNode two;
    treenode_init(&two,2);
    struct TreeNode three;
    treenode_init(&three,3);
    struct TreeNode five;
    treenode_init(&five,5);
    */
    one.left = &two;
    one.right = &three;
    two.right = &five;
    // printf("five.left %s\n",(five.left == 0)? " is null." : " is not null.");
    // printf("five.right %s\n",(five.right == 0)? " is null." : " is not null.");
    int returnSize = 0;
    char ** list = binaryTreePaths(&one,&returnSize);
    // print(list,returnSize);
    // free the string in the list one by one
    for (int i = 0; i < returnSize; i++) {
        free(list[i]);
    }
    free(list);
    // test_getlocalpath();
    // test_appendpath();
    // test_mergelist();
}

static char** binaryTreePaths(struct TreeNode* root, int* returnSize) {
    char path[] = "";
    return dfs(root,path,returnSize);
}
/**
 * [backtracking description]
 * @param  root       [pointer to the TreeNode structure]
 * @param  path       [allocated char array on heap]
 * @param  returnSize [length of returned string list]
 * @return            [address of first element in the return string list. memory allocated in heap.]
 */
static char **dfs(struct TreeNode *root, const char *path, int *returnSize) {
    char **result = (char **)malloc(sizeof(char *));
    result = 0; *returnSize = 0; // null pointer
    // base case
    if (!root) { return result; } // return null pointer
    // append path in heap
    char *localpath = getlocalpath(root->val);
    char *newpath = appendpath(path,localpath);
    // printf("Local Path = %s\n",localpath);
    // printf("Merged Path = %s\n",newpath);
    free(localpath);
    // recursion
    int leftlen = 0;
    // printf("To Left ...\n");
    char **left = dfs(root->left,newpath,&leftlen);
    // printf("Left sub problem = ");
    // print(left,leftlen);
    int rightlen = 0;
    // printf("To Right ...\n");
    char **right = dfs(root->right,newpath,&rightlen);
    // printf("Right sub problem = ");
    // print(right,rightlen);
    result = mergelist(left,leftlen,right,rightlen);
    *returnSize = leftlen + rightlen;
    // printf("Merged list = ");
    // print(result,*returnSize);
    free(left); free(right);
    // add current path into the result list, if two returned list are both null.
    if (!*returnSize) {
        result = (char **)malloc(sizeof(char *));
        int len = strlen(newpath);
        char *pathtowrite = (char *)malloc((len-1) * sizeof(char)); //allocate space on heap for path string
        strcpy(pathtowrite,&newpath[2]);
        pathtowrite[len-2] = '\0';
        result[0] = pathtowrite;
        *returnSize = 1;
    }
    free(newpath);
    return result;
}

/**
 * return the allocated char pointer in heap
 * ex: given 10, return "->10", with null "\0" at the end
 */
static char *getlocalpath(int n) {
    char *path = (char *)malloc(15 * sizeof(char));
    strcpy(path,"->"); // "\0" at the end
    snprintf(&path[2],13,"%d",n); // "\0" at the end
    return path;
}
/** assert: function getlocalpath() works well! */
static void test_getlocalpath() {
    char *path = getlocalpath(10);
    printf("%s\n",path);
    free(path);
}
/**
 * append the local path to the end of inherit path.
 * return an allocated char array in heap.
 * do not free() two given paths.
 */
static char *appendpath(const char *orig, const char *toappend) {
    int newlen = strlen(orig) + strlen(toappend) + 1;
    char *newpath = (char *)malloc(newlen * sizeof(char));
    strcpy(newpath,orig);
    strcat(newpath,toappend);
    return newpath;
}
/** assert: function append() works well! */
static void test_appendpath() {
    char *path1 = getlocalpath(10);
    char *path2 = getlocalpath(11);
    char *merged = appendpath(path1,path2);
    printf("%s\n",merged);
    free(path1); free(path2); free(merged);
}
/**
 * merge two string list "char **"
 * return allocated string list in heap, return null pointer if total len is 0.
 * do not free() two given string list
 */
static char **mergelist(char **list1, int len1, char **list2, int len2) {
    char **result = (char **)malloc(sizeof(char *));
    int sum = len1 + len2;
    if (!sum) { return result; }
    result = (char **)realloc(result,sum * sizeof(char *));
    if (len1) {
        for (int i = 0; i < len1; i++) {
            result[i] = list1[i];
        }
    }
    if (len2) {
        for (int i = 0; i < len2; i++) {
            result[len1+i] = list2[i];
        }
    }
    return result;
}
/** assert: function mergelist() works well! */
static void test_mergelist() {
    char *path10 = getlocalpath(10);
    char *path11 = getlocalpath(11);
    char *merged1 = appendpath(path10,path11);
    char **list1 = (char **)malloc(3 * sizeof(char *));
    list1[0] = path10;
    list1[1] = path11;
    list1[2] = merged1;
    printf("%s","List 1 = ");
    print(list1,3);
    free(path10); free(path11); free(merged1); free(list1);
    char *path20 = getlocalpath(20);
    char *path21 = getlocalpath(21);
    char *merged2 = appendpath(path20,path21);
    char **list2 = (char **)malloc(3 * sizeof(char *));
    list2[0] = path20;
    list2[1] = path21;
    list2[2] = merged2;
    printf("%s","List 2 = ");
    print(list2,3);
    free(path20); free(path21); free(merged2); free(list2);
}
/**
 * print the given string list
 * assert: function print() works well!
 */
static void print(char **list, int size) {
    printf("[");
    for (int i = 0; i < size; i++) {
        printf("\"%s\"",list[i]);
        if (i < size - 1) { printf(","); }
    }
    printf("]\n");
}
