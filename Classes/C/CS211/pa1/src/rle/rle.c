#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]){

    //determine how many counterchanges, if (counterchanges*2)(<-- this length of compressed) 
    //is longer than length, return given string
    int counterChanges = 0;
    char currentLetter;
    int hasDigits = 0; //no digits for now
    for(int i = 0; i < strlen(argv[1]); i++){
        if(i == 0){
            if(argv[1][i] > 47 && argv[1][i] < 58){//digits
                hasDigits = 1;
            }else{
            counterChanges = 1;
            currentLetter = argv[1][i];
            } //first letter in argv
        }
        else{
            if(argv[1][i] > 47 && argv[1][i] < 58){//digits
                hasDigits = 1;
            }
            else if(currentLetter != argv[1][i]){
                counterChanges = counterChanges + 1;
                currentLetter = argv[1][i];
            }
        }
    }
    //printf("%d", counterChanges);
    if( ((counterChanges*2) > strlen(argv[1])) && (hasDigits == 0)){ //more changes*2 then length
        printf("%s", argv[1]);
    }else if(hasDigits == 1){
        printf("error");
    }
    else{//less changes between letters in string
    char* compressedString = (char*)malloc(((counterChanges*2)+2)*sizeof(char)); //allocates a pointer for inputted string
    int individualCounter = 0;
    char currentLetter;
    int currentEmptyIndex = 0;
    for(int i = 0; i < strlen(argv[1]); i++){
        //printf("%d and %d\n", i, individualCounter);
        if(i == 0){
            //printf("here0");
            currentLetter = argv[1][i];
            individualCounter++;
        }else{
            if(currentLetter != argv[1][i]){
                //printf("here1");
                //printf("%d and %d", currentLetter, argv[1][i]);
                compressedString[currentEmptyIndex] = currentLetter;
                currentEmptyIndex++;
                char buffedC[100];
                int length = sprintf(buffedC, "%d", individualCounter);
                if(length > 1){
                    for(int i = 0; i < length; i++){
                        compressedString[currentEmptyIndex] = buffedC[i];
                        currentEmptyIndex++;
                    }
                    currentLetter = argv[1][i];
                    individualCounter = 1;
                }else{//length of individual counter just 1
                //printf("here2");
                compressedString[currentEmptyIndex] = *buffedC;
                currentLetter = argv[1][i];
                individualCounter = 1; //the currentLetter = first one (1)
                currentEmptyIndex++;
                }
            }else{ //equal, increase counter
            //printf("here3");
                individualCounter = individualCounter + 1;
            }
        }
        if((i + 1) == strlen(argv[1])){ //last character in argv[1]
        //printf("here4");
            compressedString[currentEmptyIndex] = currentLetter;
            currentEmptyIndex++;
            char buffed[100];
            int length = sprintf(buffed, "%d", individualCounter);
            if(length > 1){
                for(int i = 0; i < length; i++){
                    compressedString[currentEmptyIndex] = buffed[i];
                    currentEmptyIndex++;
                }
            }else{
            compressedString[currentEmptyIndex] = *buffed;
            currentEmptyIndex++;
            }
        }
    }
    if(hasDigits == 0){//no digits
    compressedString[currentEmptyIndex] = '\0';
    printf("%s", compressedString);
    free(compressedString);
    }else{

    }
    }
    
}