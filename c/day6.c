#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int duplicates(const char * p, int count) {
  uint32_t characters = 0;
  char c;
  int i;
  for (i=0; i<count; i++) {
    c = p[i];
    int idx = c - 'a';
    uint32_t bit = 1 << idx;
    if (characters & bit)
      return 1;
    characters |= bit;
  }
  return 0;
}

int first_marker_pos(const char * s, int count) {
  char chunk[count];
  int length = strlen(s);
  for (int i = 0; i <= length - count; i++) {
    if (!duplicates(&s[i], count))
      return i + count;
  }
  return -1;
}

int main(int argc, char* argv[]) {
  FILE* f = fopen("../input/day6.txt", "r");
  if (f) {
    fseek(f, 0, SEEK_END);
    int length = ftell(f);
    fseek(f, 0, SEEK_SET);
    char s[length];
    fread(s, 1, length, f);
    fclose (f);
    printf("Part 1: %d\n", first_marker_pos(s, 4));
    printf("Part 2: %d\n", first_marker_pos(s, 14));
  }
  return 0;
}
