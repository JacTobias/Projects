#include <stdio.h>
#include <ctype.h>
#include <string.h>

void stringUpper(char *word){
    int length, i;
    length = strlen(word);
    for(i = 0; i < length; i++){
        word[i] = toupper(word[i]);
    }
}
int main(int argc, char *argv[]){
    //char a;
    if(argc == 1){ //no command arguments
    printf("%s", argv[1]);
    return 0;
    }
    int len;
    len = strlen(argv[1]);
    if(len == 0){
        //printf("here");
        //printf("%s!!\n", argv[1]);
        printf(" ");
        return 0;
    }else{
    stringUpper(argv[1]);
    printf("%s!!\n", argv[1]);
    return 0;
    }
}

