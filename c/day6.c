#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[]) {
  const char * s = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
  char chunk[5];
  chunk[4] = '\0';
  int length = strlen(s);
  for (int i = 0; i <= length-4; i++) {
    strncpy(chunk, &s[i], 4);
    printf("%s\n", chunk);
  }
  return 0;
}
