#include <stdio.h>
#include <stdlib.h>
#include <string.h>


static int *diffWaysToCompute(char*, int*);
static int size(char *);
static int *merge(int *, int , int *, int);
static void charcpy(char *, char *, int);
static void intcpy(int *, int *, int);
static void printarray(int *,int);
static void printstr(char *);
static void test_size();
static void test_merge();
static void test_pointer();

int main() {
    int size = 0;
    int *returnSize = &size;
    char *input = "2*3-4*5";
    // printf("%c\n",*input);
    int *result = diffWaysToCompute(input,returnSize);
    // printf("%c\n",*result);
    printarray(result,*returnSize);
    free(result);

    // test_size();
    // test_merge();
    // test_pointer();
}

/**
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
static int* diffWaysToCompute(char *input, int *returnSize) {
    char *cur = input;
    int *result = NULL;
    *returnSize = 0;
    while (*cur++) {
        char c = *(cur-1);
        if (c == '+' || c == '-' || c == '*') { // 递归左右两个子数组
            // 递归左半边
            int ls = cur - 1 - input; // 准备左子字符串
            char *l_str = malloc((ls+1)*sizeof(char));
            charcpy(l_str,input,ls);
            *(l_str+ls) = '\0';
            int  l_rs = 0; // 准备用来返回长度的指针
            int *l_rsp = &l_rs;
            int *l = diffWaysToCompute(l_str,l_rsp); // 递归
            free(l_str);
            // 递归右半边
            int rs = size(input) - ls - 1; // 准备右子字符串
            char *r_str = malloc((rs+1)*sizeof(char));
            charcpy(r_str,cur,rs);
            *(r_str+rs) = '\0';
            int r_rs = 0; // 准备用来返回长度的指针
            int *r_rsp = &r_rs;
            int *r = diffWaysToCompute(r_str,r_rsp); // 递归
            free(r_str);
            // 得到左右两边计算后的结果
            int calculateSize = *l_rsp * *r_rsp;
            int *calculate = (int *)malloc(calculateSize * sizeof(int));
            int *cur = calculate;
            for (int i = 0; i < *l_rsp; i++) {
                for (int j = 0; j < *r_rsp; j++) {
                    switch (c) {
                        case '+': *cur++ = *(l+i) + *(r+j); break;
                        case '-': *cur++ = *(l+i) - *(r+j); break;
                        case '*': *cur++ = *(l+i) * *(r+j); break;
                    }
                }
            }
            // 把这轮计算结果，加入总结果中。
            int *newResult = merge(result,*returnSize,calculate,calculateSize);
            *returnSize += calculateSize;
            free(result); free(calculate);
            result = newResult;
        }
    }
    if (!*returnSize) {
        result = (int *)malloc(sizeof(int));
        *result = atoi(input);
        *returnSize = 1;
    }
    return result;
}
/** return the size of a string */
static int size(char *input) {
    int count = 0;
    char *cur = input;
    while (*cur++) { count++; }
    return count;
}
/** test size() function */
static void test_size() {
    char *pointer = "hello, world!";
    char array[] = "hello, world!";
    printf("Size of pointer \"hello, world!\" is: %d\n",size(pointer));
    printf("Size of array \"hello, world!\" is: %d\n",size(array));
}

/**
 *  合并两个数组。 合并完之后返回新malloc()出的一块内存。调用函数应负责free()内存。
 *  不负责清理原先两个数组。
 *  如果两个待合并数组都为空，则返回空。
 */
static int *merge(int *a, int sa, int*b, int sb) {
    int size = sa + sb;
    size_t memo_size = size * sizeof(int);
    if (!size) { return NULL; }
    int *result = (int *)malloc(memo_size);
    intcpy(result,a,sa);
    // printarray(result,sa);
    int *mid = result + sa;
    intcpy(mid,b,sb);
    // printarray(result,size);
    return result;
}
/** test merge function() */
static void test_merge() {
    int len1 = 2;
    int *one = (int *)malloc(len1);
    *one = 1;
    *(one+1) = 2;
    printf("Array one = ");
    printarray(one,len1);
    int len2 = 3;
    int *two = (int *)malloc(len2);
    *two = 7;
    *(two+1) = 8;
    *(two+2) = 9;
    printf("Array two = ");
    printarray(two,len2);
    int *one_two = merge(one,len1,two,len2);
    free(one); free(two);
    printf("Array one_two = ");
    printarray(one_two,len1+len2);
    free(one_two);
}

/**
 *  copy n characters from src to des.
 *  只负责拷贝，不负责内存
 *  只负责字符串内容的拷贝，不负责字符串末尾的 "\0"结尾符
 */
static void charcpy(char *des, char *src, int n) {
    while (n--) { *des++ = *src++; } // len to 1
}
/**
 *  copy n int from src to des.
 *  只负责拷贝不负责内存
 */
static void intcpy(int *des, int *src, int n) {
    while (n--) { *des++ = *src++; } // len to 1
}

/**
 * Print array
 */
static void printarray(int* array, int len) {
    int* cur = array;
    printf("%s","[");
    for (int i = 0; i < len; i++) {
        printf("%d",*cur++);
        if (i < len - 1) { printf("%s",","); }
    }
    printf("%s","]\n");
}
/**
 * print string
 */
static void printstr(char *str) {
    while (*str) { printf("%c",*str++); }
    printf("\n");
}

/**
 * a是一个指向int的指针。
 * 用a[5]来指向a向后数第5个int
 * 原因是：[]是一个后缀表达式。这种表达式前面必须是指针。
 * 所以，看到a[5]，第一反应应该是：a是不是数组我不知道，但a肯定是一个指针。
 */
static void test_pointer() {
    int *a = (int *)malloc(10*sizeof(int));
    *(a+5) = 100; // a[5] = 100
    printf("a[5] = %d\n",a[5]);
}
