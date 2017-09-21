#include "mystd.h"

typedef int bool;
#define T 1
#define F 0

bool isUgly(int num);

int main() {
    for (int i = 0; i < 20; i++) {
        char *answer = "false";
        if (isUgly(i) == T) { answer = "true"; }
        printf("%2d is ugly number? %5s\n",i,answer);
    }
}

bool isUgly(int num) {
    bool res = 0;
    if (num <= 0) { return res; }
    while (T) {
        if (num % 2 == 0) { num /= 2; continue; }
        if (num % 3 == 0) { num /= 3; continue; }
        if (num % 5 == 0) { num /= 5; continue; }
        break;
    }
    if (num == 1) { res = 1; }
    return res;
}
