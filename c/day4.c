#include <stdio.h>

int is_included(int l1, int r1, int l2, int r2) {
  return (l1 <= l2 && r1 >= r2) || (l2 <= l1 && r2 >= r1);
}

int is_overlapping(int l1, int r1, int l2, int r2) {
  return l2 <= r1 && l1 <= r2;
}

int main(int argc, char* argv[]) {
  FILE* fp = fopen("../input/day4.txt", "r");
  if (fp != NULL) {
    int a, b, c, d, sum_included = 0, sum_overlapping = 0;
    while (fscanf(fp, "%d-%d, %d-%d", &a, &b, &c, &d) == 4) {
      sum_included += is_included(a, b, c, d);
      sum_overlapping += is_overlapping(a, b, c, d);
    }
    printf("Part 1: %d\n", sum_included);
    printf("Part 2: %d\n", sum_overlapping);
    fclose(fp);
  }
  return 0;
}
