#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

static bool isAnagram(char *, char *);
static void print(char *, char *, bool);
static void testplusplus();

int main() {
    /*
    char *s1 = "anagram";
    char *t1 = "nagaram";
    char *s2 = "rat";
    char *t2 = "car";
    bool r1 = isAnagram(s1,t1);
    bool r2 = isAnagram(s2,t2);
    print(s1,t1,r1);
    print(s2,t2,r2);
    */
   testplusplus();
}

static bool isAnagram(char *s, char *t) {
    int len = strlen(s);
    if (strlen(t) != len) { return false; }
    int freq[26] = {0}; // the local array will not be initialized to 0 if I declare by with "freq[26];"
    for (int i = 0; i < len; i++) {
        freq[*s++ - 'a']++;
        freq[*t++ - 'a']--;
    }
    for (int i = 0; i < 26; i++) {
        if (freq[i]) { return false; }
    }
    return true;
}

static void print(char *s, char *t, bool result) {
    printf("[%s] and [%s] are anagram? %s\n", s, t, result? "true" : "false");
}

static void testplusplus() {
    int nums[10] = {0};
    for (int i = 0; i < 10; ++i) {
        nums[i] = i;
    }
    printf("[");
    for (int i = 0; i < 10; i++) {
        printf("%d",nums[i]);
        if (i < 9) { printf(","); }
    }
    printf("]\n");
}
