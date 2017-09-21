#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

static int *diffWaysToCompute(char*, int*);
static int *compute(char*, int, int*);
static void ptints(int *, int);
static void ptchars(char *,int);

int main() {
    char *input = "2*3-4*5";
    int size = 0;
    int * result = diffWaysToCompute(input,&size);
    ptints(result,size);
    free(result);
}

/**
 * return the pointer of int. memory allocated by malloc() in heap.
 * the caller should release the memory by free().
 * @param  input      [input string]
 * @param  returnSize [size of return int array(can be a int pointer)]
 * @return            [a pointer to list of int]
 */
static int *diffWaysToCompute(char *input, int *returnSize) {
    return compute(input,strlen(input),returnSize);
}
/**
 * [recursive helper to the diffWaysToCompute() methode]
 * @param  input      [pointer to first char in substring]
 * @param  size       [size of substring]
 * @param  returnSize [the size of returned int]
 * @return            [a pointer to list of int]
 */
static int *compute(char *input, int size, int *returnSize) {
    ptchars(input,size);
    // prepare memory
    int * ptr = 0; // init to null
    *returnSize = 0;
    int *l, *r; // result of two sub-problem
    int ls, rs; // size of the result of two sub-problem
    // iteration
    char *cur = input;
    for (int i = 0; i < size; i++) {
        char c = *cur++;
        if (!isdigit((int)c)) {
            l = compute(input,cur-input-1,&ls);
            r = compute(cur,size-(cur-input),&rs);
            int start = *returnSize;
            *returnSize += (ls * rs);
            ptr = (int *)realloc(ptr, *returnSize * sizeof(int));
            for (int j = 0; j < ls; j++) {
                for (int k = 0; k < rs; k++) {
                    switch(c) {
                        case '+': ptr[start++] = l[j] + r[k]; break;
                        case '-': ptr[start++] = l[j] - r[k]; break;
                        case '*': ptr[start++] = l[j] * r[k]; break;
                    }
                }
            }
            free(l); free(r);
        }
    }
    if (!ptr) {
        ptr = (int *)realloc(ptr,sizeof(int));
        *returnSize = 1;
        ptr[0] = atoi(input);
    }
    return ptr;
}

/** print the given list of int */
static void ptints(int *input, int size) {
    printf("[");
    while (size--) {
        printf("%d",*input++);
        if (size > 0) { printf(","); }
    }
    printf("]\n");
}

/** print the given list of chars */
static void ptchars(char *input, int size) {
    printf("[");
    while (size--) {
        printf("%c",*input++);
        if (size > 0) { printf(","); }
    }
    printf("]\n");
}
