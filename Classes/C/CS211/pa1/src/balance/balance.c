#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char* argv[]){

char* stack = (char*) malloc((strlen(argv[1]) + 2)*sizeof(char));
//char* ptr;

int currentEmptyIndex = 0; //also currentIndex in argument array
int previousIndex;
for(int i = 0; i < strlen(argv[1]); i++){
    if(i == 0){ //first character
        //printf("here0");
        //printf("%d", i);
        if( (argv[1][i] != '[') && (argv[1][i] != '{') && (argv[1][i] != '(') && (argv[1][i] != ']') && (argv[1][i] != '}') && (argv[1][i] != ')') ){
           // printf("heregg");
            //printf("%c", argv[1][0]);
            //ignore
        }
        else if((argv[1][i] == '[') || (argv[1][i] == '{') || (argv[1][i] == '(')){
            //printf("here01 %c\n", argv[1][i]);
            //printf("hereeeee");
            stack[currentEmptyIndex] = argv[1][i];
            previousIndex = currentEmptyIndex;
            currentEmptyIndex++;
        }else{//nothing to compare
            //printf("here1111");
            //char buffedC[10];
            //sprintf(buffedC, "%c", argv[1][i]);
            printf("%d: %c", currentEmptyIndex, argv[1][i]);
            free(stack);
            return EXIT_FAILURE;
            //return 0;// EXIT_FAILURE;
        }
    }else{ 
        //printf("here?");//i not equal to zero
        //printf("%d", currentEmptyIndex);
        if( (argv[1][i] != '[') && (argv[1][i] != '{' ) && (argv[1][i] != '(') && (argv[1][i] != ']') && (argv[1][i] != '}') && (argv[1][i] != ')') ){
        //ignore
        //printf("%c" ,argv[1][i]);
        //printf("here!");
        }
        else if((argv[1][i] == '[') || (argv[1][i] == '{') || (argv[1][i] == '(')){
            //printf("hrerii");
            //printf("here1");
            stack[currentEmptyIndex] = argv[1][i];
            //printf("%d", currentEmptyIndex);
            previousIndex = currentEmptyIndex;
            currentEmptyIndex++;
        }else{ //closing, ],}, or ), check previously added open
        //printf("hereeee");
            if(currentEmptyIndex == 0){//the stack is empty
                free(stack);
               // printf("heeeeere");
                //char buffed[10];
                //sprintf(buffed, "%c", argv[1][i]);
                printf("%d: %c", i, argv[1][i]);
                return EXIT_FAILURE;
            }else{ //stack isnt empty
            if(argv[1][i] == (stack[previousIndex] + 2)){ //either [ or {
                //remove previousIndex from stack;
                //printf("here2");
                currentEmptyIndex = previousIndex;
                previousIndex--;
            }
            else if(argv[1][i] == (stack[previousIndex] + 1)){ // ( )
                currentEmptyIndex = previousIndex;
                previousIndex--;
                //printf("here3");
            }else{ //not equal to previous stacked
                //printf("here4");
                //char buffedC[10];
                //sprintf(buffedC, "%c", argv[1][i]);
                printf("%d: %c", currentEmptyIndex, argv[1][i]);
                free(stack);
                return EXIT_FAILURE;
                //return EXIT_FAILURE0; //EXIT_FAILURE;
            }
        }
        }
    }
}
if(currentEmptyIndex != 0){ //stack still has opens
    //printf("HELLO %d\n", previousIndex); 
    //previousIndex = last inputted open to stack
    for(int i = previousIndex; i >= 0; i--){
        //printf("%d\n i", i);
        //printf("%c", stack[i]);
        if(argv[1][i] == '('){
            stack[i] = stack[i] + 1;
        }else if(argv[1][i] == '['){
            stack[i] = stack[i] + 2;
            //printf("jjjj%d", stack[i]);
        }else{ //{}
            //printf("%c", stack[i]);
            stack[i] = stack[i] + 2;
            
            //printf("gggg%d", stack[i]);
        }
    }
    stack[currentEmptyIndex] = '\0';
    char* returned = (char*)malloc((strlen(stack) + 2)* sizeof(char));
    int j = 0;
    for(int i = (strlen(stack) -1); i >= 0; i--){
        //printf("%d", j);
        returned[j] = stack[i];
        j++;
        //printf("%c", returned[i]);
        if(i-1 < 0){
            returned[j] = '\0';
        }
    }
    printf("open: %s", returned);
    free(returned);
    free(stack);
    return EXIT_FAILURE;
}else{
free(stack);
return EXIT_SUCCESS;
    }
}