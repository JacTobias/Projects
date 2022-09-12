#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

typedef struct node{ //stores inputs and all outputs
    int numberassociated;
    char inputoroutput[20]; //name of input/output
    struct node *next;
    int finaloutput; //1 if true, 0 if false, final output is its in the OUTPUT part of file
    int currentTF;
    int decoderindex;
    int muxinputindex;
}inputoroutputNode_t;

inputoroutputNode_t *insert_newinsouts(inputoroutputNode_t *head, char newIOstring[], int numberassociated, int finaloutputnum, int currentTF, int decoderindex, int muxinputindex){
    inputoroutputNode_t *insertthis = (inputoroutputNode_t*)malloc(sizeof(inputoroutputNode_t));
    insertthis->numberassociated = numberassociated;
    strcpy(insertthis->inputoroutput, newIOstring);
    insertthis->finaloutput = finaloutputnum;
    insertthis->next = NULL;
    insertthis->currentTF = currentTF;
    insertthis->muxinputindex = muxinputindex;
    if(head == NULL){
        head = insertthis;
        return head;
    }else{
        inputoroutputNode_t *temp = head;
        while(temp->next != NULL){
            temp = temp->next;
        }
        temp->next = insertthis;
        return head;
    }
}

void free_IOList(inputoroutputNode_t *head){
    inputoroutputNode_t *freetemp;
    while(head != NULL){
        freetemp = head;
        head = head->next;
        free(freetemp);
    }
}

void print_IOlist(inputoroutputNode_t *head){
    inputoroutputNode_t *temp = head;
    while(temp != NULL){
        printf(" %s ", temp->inputoroutput);
        printf(" %d ", temp->numberassociated);
        //printf(" %d\n", temp->finaloutput);
        temp = temp->next;
    }
   // printf("done1");
}

int and_gate(int inputs[2]){
    int input1 = inputs[0];
    int input2 = inputs[1];
    if(input1 == 1 && input2 == 1){
        return 1;
    }else if(input1 == 0 && input2 == 0){
        return 0;
    }else{
        return 0;
    }
return 1;
}

int or_gate(int inputs[2]){
    int input1 = inputs[0];
    int input2 = inputs[1];
    if(input1 == 1 || input2 == 1){
        return 1;
    }else{
        return 0;
    }
}

int nor_gate(int inputs[2]){
   int input1 = inputs[0];
   int input2 = inputs[1];
   if(input1 == 1 || input2 == 1){
       return 0;
   }else{
       return 1;
   }

}

int xor_gate(int inputs[2]){
    int input1 = inputs[0];
    int input2 = inputs[1];
    //printf("%d %d", input1, input2);
    if(input1 == input2){
        return 0;
    }else{
        return 1;
    }
}

int nand_gate(int inputs[2]){
    int input1 = inputs[0];
    int input2 = inputs[1];
    if(input1 == 1 && input2 == 1){
        return 0;
    }else{ 
        return 1;
    }
}

int main (int argc, char* argv[]){
    FILE *file;
    file = fopen(argv[1], "r");
    char *string = malloc(20*sizeof(char));
    int numberofinputs;
    int inputnumber = 0;
    int outputnumber = 0;
    int numberofoutputs;
    //int computednumberofoutputs;
    inputoroutputNode_t *headI = NULL;
    inputoroutputNode_t *headO = NULL;
    //inputoroutputNode_t *decoderLLinputs = NULL;
    //inputoroutputNode_t *decoderLLoutputs = NULL;
    //int nextdecodercolumn = 0;
    while(1){
        //printf("here");
        fscanf(file, "%s", string);
        //printf("here");
        if( feof(file)){
            break;
        }else{
            if( (strcmp(string, "INPUT") == 0) ){
                fscanf(file, "%d", &numberofinputs);
                while(inputnumber < numberofinputs){  
                char tempstring[10];
                fscanf(file, "%s", tempstring);
                headI = insert_newinsouts(headI, tempstring, inputnumber, 0, 0, 0, 0);
                inputnumber++;
            }
            }else if ( (strcmp(string, "OUTPUT") == 0) ){
                fscanf(file, "%d", &numberofoutputs);
                while(outputnumber < numberofoutputs){  
                char tempstringO[10];
                fscanf(file, "%s", tempstringO);
                headO = insert_newinsouts(headO, tempstringO, outputnumber, 1, 0, 0, 0);
                outputnumber++;
                }
            }
        }
    }
    int temporaryoutputs = numberofoutputs;
    numberofoutputs = pow(2, numberofinputs);
    //computednumberofoutputs = pow(2, numberofinputs);
    int addtonextcolumn = 0; //1 if yes add to next column, 0 if no
    int *arrayTruthTable = malloc(numberofinputs*sizeof(int));
    rewind(file);
    for(int r = 0; r < numberofoutputs; r++){
        for(int c = numberofinputs-1; c >= 0; c--){
            //printf(" %d ", c);
            if(r == 0){//all zeros
            arrayTruthTable[c] = 0; 
            } 
            else if(c == numberofinputs-1){//last index
            int temp = arrayTruthTable[c];
            temp++;
                if(temp == 2){
                arrayTruthTable[c] = 0;
                addtonextcolumn = 1;
                }else if(temp == 1){
                    arrayTruthTable[c] = temp;
                }
            }
            else if(addtonextcolumn == 1){ //need to add a 1 to this column;
                int temp2 = arrayTruthTable[c];
                temp2++;
                if(temp2 > 1){
                    //keep addtonextcolumn the same
                    arrayTruthTable[c] = 0;
                }else if(temp2 == 1){
                    arrayTruthTable[c] = temp2++;
                    addtonextcolumn = 0;
                }
            }
        inputoroutputNode_t *tempI = headI;
        while(tempI != NULL){
            if(tempI->numberassociated == c){
                tempI->currentTF = arrayTruthTable[c];
            }
            tempI = tempI->next;
         }
        }
        int decoder = 0;
        //while(1){
        while(fscanf(file, "%s", string) != EOF){
            //printf("%s", string);
            //printf("here");
            if(feof(file)){
                //decoder = 1;
                //printf("here");
                break;
            }
            else{
                //printf("here");
        //this is where is should start reading file again
        //fscanf(file, "%s", string);
        if( (strcmp(string, "AND")) == 0){ 
            int arrIn[2];
            char tempstring1[10];
            fscanf(file, "%s", tempstring1);
            char tempstring2[10];
            fscanf(file, "%s", tempstring2);
            inputoroutputNode_t *tempin = headI;
            int didfind1 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring1)) == 0){
                    didfind1 = 1;
                    arrIn[0] = tempin->currentTF;
                    }
                tempin = tempin->next;
            }
            tempin = headI;
            int didfind11 = 0;
            if(didfind1 == 0){//not in inputs, check outputs
                    inputoroutputNode_t *tempout = headO;
                    while(tempout != NULL){
                        if( (strcmp(tempout->inputoroutput, tempstring1)) == 0){
                            arrIn[0] = tempout->currentTF;
                            didfind11 = 1;
                        }
                    tempout = tempout->next;
                    }
            }
            if(didfind11 == 0){//entered as either 1 or 0
                if( strcmp(tempstring1, "0") == 0){
                    arrIn[0] = 0;
                }else if( strcmp(tempstring1, "1") == 0){
                    arrIn[0] = 1;
                }
            }
            int didfind2 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring2)) == 0){
                    didfind2 = 1;
                    arrIn[1] = tempin->currentTF;
                }
            tempin = tempin->next;
            }
            int didfind22 = 0;
            if(didfind2 == 0){//didnt find inout to and in input
                inputoroutputNode_t *tempout = headO;
                while(tempout != NULL){
                    if( (strcmp(tempout->inputoroutput, tempstring2)) == 0){
                        arrIn[1] = tempout->currentTF;
                        didfind22 = 1;
                    }
                tempout = tempout->next;
                }
            }
            if(didfind22 == 0){//entered as either 1 or 0
                if( strcmp(tempstring2, "0") == 0){
                    arrIn[1] = 0;
                }else if( strcmp(tempstring2, "1") == 0){
                    arrIn[1] = 1;
                }
            }
                char tempstring[10];
                fscanf(file, "%s", tempstring); //this is an output of the game, insert into output gate with the value from andgate
                int outputofAnd = and_gate(arrIn);
                inputoroutputNode_t *tempO = headO;
                int isfinaloutput = 0;
                int isinLL = 0;
                outputnumber++;
                while(tempO != NULL){
                    if( (strcmp(tempO->inputoroutput, tempstring)) == 0){
                        isinLL = 1;
                        if(tempO->finaloutput == 1){
                          tempO->currentTF = outputofAnd;  
                        }
                    }
                tempO = tempO->next;
                }
                if(isinLL == 1 && isfinaloutput == 0){
                    inputoroutputNode_t *temporary = headO;
                    while(temporary != NULL){
                        if( (strcmp(temporary->inputoroutput, tempstring)) == 0){
                            temporary->currentTF = outputofAnd;
                        }
                    temporary = temporary->next;
                    }
                }
                if(isinLL == 0 && isfinaloutput == 1){
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 1, outputofAnd, 0, 0);
                }
                if(isfinaloutput == 0 && isinLL == 0){//not the final output
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 0, outputofAnd, 0, 0);
                }
                //outputnumber++;
        }else if( (strcmp(string, "NOT")) == 0){ //output = not of input
            char tempstring[10];
            fscanf(file, "%s", tempstring);
            int foundInput = 0; //1 if found, 0 if not
            int notinput;
            inputoroutputNode_t *temp = headI;
            while(temp != NULL){
                if( (strcmp(temp->inputoroutput, tempstring)) == 0){
                    foundInput = 1;
                    if(temp->currentTF == 0){
                        notinput = 1;
                    }else{ // == 1
                        notinput = 0;
                    }
                }
            temp = temp->next;
            }
            if(foundInput == 0){//did not find input in headI LL
                temp = headO;
                while(temp != NULL){
                    if( (strcmp(temp->inputoroutput, tempstring)) == 0){
                        foundInput = 1;
                        if(temp->currentTF == 0){
                            notinput = 1;
                        }else{// == 1
                            notinput = 0;
                        }
                    }
                    temp = temp->next;
                }
            }
            if(foundInput == 0){
                if (strcmp(tempstring, "0") == 0){
                    notinput = 1;
                }else if( strcmp(tempstring, "1") == 0){
                    notinput = 0;
                }
            }
            outputnumber++;
            char tempstringO[10];
            fscanf(file, "%s",tempstringO);
            temp = headO;
            int isfinaloutput = 0;
            int foundinout = 0;
            while(temp != NULL){
                if( (strcmp(temp->inputoroutput, tempstringO)) == 0){
                    foundinout = 1;
                    if(temp->finaloutput == 1){
                        isfinaloutput = 1;
                        temp->currentTF = notinput;
                    }
                }
            temp = temp->next;
            }
            if(foundinout == 1 && isfinaloutput == 0){
                inputoroutputNode_t *temporary = headO;
                while(temporary != NULL){
                    if( (strcmp(temporary->inputoroutput, tempstringO)) == 0){
                        temporary->currentTF = notinput;
                    }
                temporary = temporary->next;
                }
            }
            if(foundinout == 0 && isfinaloutput == 1){
                headO = insert_newinsouts(headO, tempstringO, outputnumber, 1, notinput, 0, 0); 
            }
            if(foundinout == 0 && isfinaloutput == 0){//new output, put in heado ll, also not finaloutput
                headO = insert_newinsouts(headO, tempstringO, outputnumber, 0, notinput, 0, 0);
            }
        }else if( (strcmp(string, "OR")) == 0){
            int arrIn[2];
            char tempstring1[10];
            fscanf(file, "%s", tempstring1);
            char tempstring2[10];
            fscanf(file, "%s", tempstring2);
            inputoroutputNode_t *tempin = headI;
            int didfind1 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring1)) == 0){
                    didfind1 = 1;
                    arrIn[0] = tempin->currentTF;
                    }
                tempin = tempin->next;
            }
            tempin = headI;
            if(didfind1 == 0){//not in inputs, check outputs
                    inputoroutputNode_t *tempout = headO;
                    while(tempout != NULL){
                        if( (strcmp(tempout->inputoroutput, tempstring1)) == 0){
                            arrIn[0] = tempout->currentTF;
                            didfind1 = 1;
                        }
                    tempout = tempout->next;
                    }
            }
            if(didfind1 == 0){
                if(strcmp(tempstring1, "0") == 0){
                    arrIn[0] = 0;
                }else if(strcmp(tempstring1, "1") == 0){
                    arrIn[0] = 1;
                }
            }
            int didfind2 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring2)) == 0){
                    didfind2 = 1;
                    arrIn[1] = tempin->currentTF;
                }
            tempin = tempin->next;
            }
            if(didfind2 == 0){//didnt find inout to and in input
                inputoroutputNode_t *tempout = headO;
                while(tempout != NULL){
                    if( (strcmp(tempout->inputoroutput, tempstring2)) == 0){
                        arrIn[1] = tempout->currentTF;
                        didfind2 = 1;
                    }
                tempout = tempout->next;
                }
            }
            if(didfind2 == 0){
                if(strcmp(tempstring2, "0") == 0){
                    arrIn[1] = 0;
                }else if(strcmp(tempstring2, "1") == 0){
                    arrIn[1] = 1;
                }
            }
                char tempstring[10];
                fscanf(file, "%s", tempstring); //this is an output of the game, insert into output gate with the value from andgate
                int outputofOr = or_gate(arrIn);
                //printf("|%s|", tempstring);
                inputoroutputNode_t *tempO = headO;
                int isfinaloutput = 0;
                int isinLL = 0;
                //outputnumber++;
                //printf("   |%sor|  ", tempstring);
                while(tempO != NULL){
                    if( (strcmp(tempO->inputoroutput, tempstring)) == 0){
                        //printf("%s%s", tempO->inputoroutput, tempstring);
                        isinLL = 1;
                        if(tempO->finaloutput == 1){
                          tempO->currentTF = outputofOr;  
                          isfinaloutput = 1;
                        }
                    }
                tempO = tempO->next;
                }
                //printf("|%dL%df|", isinLL, isfinaloutput);
                //printf("|%dOr|", outputofOr);
                if(isinLL == 1 && isfinaloutput == 0){
                    //printf("hereLF");
                    inputoroutputNode_t *temporary = headO;
                    while(temporary != NULL){
                        if( (strcmp(temporary->inputoroutput, tempstring)) == 0){
                            temporary->currentTF = outputofOr;
                        }
                    temporary = temporary->next;
                    }
                }
                if(isinLL == 0 && isfinaloutput == 1){
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 1, outputofOr, 0, 0);
                    outputnumber++;
                }
                if(isfinaloutput == 0 && isinLL == 0){//not the final output
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 0, outputofOr, 0, 0);
                    outputnumber++;
                    //printf("  1O  ");
                }

        }else if( (strcmp(string, "NOR")) == 0){
            int arrIn[2];
            char tempstring1[10];
            fscanf(file, "%s", tempstring1);
            char tempstring2[10];
            fscanf(file, "%s", tempstring2);
            inputoroutputNode_t *tempin = headI;
            int didfind1 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring1)) == 0){
                    didfind1 = 1;
                    arrIn[0] = tempin->currentTF;
                    }
                tempin = tempin->next;
            }
            tempin = headI;
            if(didfind1 == 0){//not in inputs, check outputs
                    inputoroutputNode_t *tempout = headO;
                    while(tempout != NULL){
                        if( (strcmp(tempout->inputoroutput, tempstring1)) == 0){
                            arrIn[0] = tempout->currentTF;
                            didfind1 = 1;
                        }
                    tempout = tempout->next;
                    }
            }
            if(didfind1 == 0){
                if(strcmp(tempstring1, "0") == 0){
                    arrIn[0] = 0;
                }else if(strcmp(tempstring1, "1") == 0){
                    arrIn[0] = 1;
                }
            }
            int didfind2 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring2)) == 0){
                    didfind2 = 1;
                    arrIn[1] = tempin->currentTF;
                }
            tempin = tempin->next;
            }
            if(didfind2 == 0){//didnt find inout to and in input
                inputoroutputNode_t *tempout = headO;
                while(tempout != NULL){
                    if( (strcmp(tempout->inputoroutput, tempstring2)) == 0){
                        arrIn[1] = tempout->currentTF;
                        didfind2 = 1;
                    }
                tempout = tempout->next;
                }
            }
            if(didfind2 == 0){
                if(strcmp(tempstring2, "0") == 0){
                    arrIn[1] = 0;
                }else if(strcmp(tempstring2, "1") == 0){
                    arrIn[1] = 1;
                }
            }
                char tempstring[10];
                fscanf(file, "%s", tempstring); //this is an output of the game, insert into output gate with the value from andgate
                int outputofNor = nor_gate(arrIn);
                inputoroutputNode_t *tempO = headO;
                int isfinaloutput = 0;
                int isinLL = 0;
                outputnumber++;
                while(tempO != NULL){
                    if( (strcmp(tempO->inputoroutput, tempstring)) == 0){
                        isinLL = 1;
                        if(tempO->finaloutput == 1){
                          tempO->currentTF = outputofNor;  
                        }
                    }
                tempO = tempO->next;
                }
                if(isinLL == 1 && isfinaloutput == 0){
                    inputoroutputNode_t *temporary = headO;
                    while(temporary != NULL){
                        if( (strcmp(temporary->inputoroutput, tempstring)) == 0){
                            temporary->currentTF = outputofNor;
                        }
                    temporary = temporary->next;
                    }
                }
                if(isinLL == 0 && isfinaloutput == 1){
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 1, outputofNor, 0, 0);
                }
                if(isfinaloutput == 0 && isinLL == 0){//not the final output
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 0, outputofNor, 0, 0);
                }
        }else if( (strcmp(string, "XOR")) == 0){
            int arrIn[2];
            char tempstring1[10];
            fscanf(file, "%s", tempstring1);
            char tempstring2[10];
            fscanf(file, "%s", tempstring2);
            inputoroutputNode_t *tempin = headI;
            int didfind1 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring1)) == 0){
                    didfind1 = 1;
                    arrIn[0] = tempin->currentTF;
                    }
                tempin = tempin->next;
            }
            tempin = headI;
            if(didfind1 == 0){//not in inputs, check outputs
                    inputoroutputNode_t *tempout = headO;
                    while(tempout != NULL){
                        if( (strcmp(tempout->inputoroutput, tempstring1)) == 0){
                            arrIn[0] = tempout->currentTF;
                            didfind1 = 1;
                        }
                    tempout = tempout->next;
                    }
            }
            if(didfind1 == 0){
                if(strcmp(tempstring1, "0") == 0){
                    arrIn[0] = 0;
                }else if(strcmp(tempstring1, "1") == 0){
                    arrIn[0] = 1;
                }
            }
            int didfind2 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring2)) == 0){
                    didfind2 = 1;
                    arrIn[1] = tempin->currentTF;
                }
            tempin = tempin->next;
            }
            if(didfind2 == 0){//didnt find inout to and in input
                inputoroutputNode_t *tempout = headO;
                while(tempout != NULL){
                    if( (strcmp(tempout->inputoroutput, tempstring2)) == 0){
                        arrIn[1] = tempout->currentTF;
                        didfind2 = 1;
                    }
                tempout = tempout->next;
                }
            }
            if(didfind2 == 0){
                if(strcmp(tempstring2, "0") == 0){
                    arrIn[1] = 0;
                }else if(strcmp(tempstring2, "1") == 0){
                    arrIn[1] = 1;
                }
            }
                char tempstring[10];
                fscanf(file, "%s", tempstring); //this is an output of the game, insert into output gate with the value from andgate
                int outputofXor = xor_gate(arrIn);
                inputoroutputNode_t *tempO = headO;
                int isfinaloutput = 0;
                int isinLL = 0;
                outputnumber++;
                while(tempO != NULL){
                    if( (strcmp(tempO->inputoroutput, tempstring)) == 0){
                        isinLL = 1;
                        if(tempO->finaloutput == 1){
                          tempO->currentTF = outputofXor;  
                        }
                    }
                tempO = tempO->next;
                }
                if(isinLL == 1 && isfinaloutput == 0){
                    inputoroutputNode_t *temporary = headO;
                    while(temporary != NULL){
                        if( (strcmp(temporary->inputoroutput, tempstring)) == 0){
                            temporary->currentTF = outputofXor;
                        }
                    temporary = temporary->next;
                    }
                }
                if(isinLL == 0 && isfinaloutput == 1){
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 1, outputofXor, 0, 0);
                }
                if(isfinaloutput == 0 && isinLL == 0){//not the final output
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 0, outputofXor, 0, 0);
                }
        }else if( (strcmp(string, "NAND")) == 0){
            int arrIn[2];
            char tempstring1[10];
            fscanf(file, "%s", tempstring1);
            char tempstring2[10];
            fscanf(file, "%s", tempstring2);
            inputoroutputNode_t *tempin = headI;
            int didfind1 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring1)) == 0){
                    didfind1 = 1;
                    arrIn[0] = tempin->currentTF;
                    }
                tempin = tempin->next;
            }
            tempin = headI;
            if(didfind1 == 0){//not in inputs, check outputs
                    inputoroutputNode_t *tempout = headO;
                    while(tempout != NULL){
                        if( (strcmp(tempout->inputoroutput, tempstring1)) == 0){
                            arrIn[0] = tempout->currentTF;
                            didfind1 = 1;
                        }
                    tempout = tempout->next;
                    }
            }
            if(didfind1 == 0){
                if(strcmp(tempstring1, "0") == 0){
                    arrIn[0] = 0;
                }else if(strcmp(tempstring1, "1") == 0){
                    arrIn[0] = 1;
                }
            }
            int didfind2 = 0;
            while(tempin != NULL){
                if( (strcmp(tempin->inputoroutput, tempstring2)) == 0){
                    didfind2 = 1;
                    arrIn[1] = tempin->currentTF;
                }
            tempin = tempin->next;
            }
            if(didfind2 == 0){//didnt find inout to and in input
                inputoroutputNode_t *tempout = headO;
                while(tempout != NULL){
                    if( (strcmp(tempout->inputoroutput, tempstring2)) == 0){
                        arrIn[1] = tempout->currentTF;
                        didfind2 = 1;
                    }
                tempout = tempout->next;
                }
            }
            if(didfind2 == 0){
                if(strcmp(tempstring2, "0") == 0){
                    arrIn[1] = 0;
                }else if(strcmp(tempstring2, "1") == 0){
                    arrIn[1] = 1;
                }
            }
                char tempstring[10];
                fscanf(file, "%s", tempstring); //this is an output of the game, insert into output gate with the value from andgate
                int outputofnand = nand_gate(arrIn);
                inputoroutputNode_t *tempO = headO;
                int isfinaloutput = 0;
                int isinLL = 0;
                outputnumber++;
                while(tempO != NULL){
                    if( (strcmp(tempO->inputoroutput, tempstring)) == 0){
                        isinLL = 1;
                        if(tempO->finaloutput == 1){
                          tempO->currentTF = outputofnand;  
                        }
                    }
                tempO = tempO->next;
                }
                if(isinLL == 1 && isfinaloutput == 0){
                    inputoroutputNode_t *temporary = headO;
                    while(temporary != NULL){
                        if( (strcmp(temporary->inputoroutput, tempstring)) == 0){
                            temporary->currentTF = outputofnand;
                        }
                    temporary = temporary->next;
                    }
                }
                if(isinLL == 0 && isfinaloutput == 1){
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 1, outputofnand, 0, 0);
                }
                if(isfinaloutput == 0 && isinLL == 0){//not the final output
                    headO = insert_newinsouts(headO, tempstring, outputnumber, 0, outputofnand, 0, 0);
                }
        }else if( (strcmp(string, "PASS")) == 0){
            char tempstring[10];
            fscanf(file, "%s", tempstring);
            char passinto[10];
            fscanf(file, "%s", passinto);
            inputoroutputNode_t *temp = headI;
            int currentTrFa;
            int foundininputs = 0;
            while(temp != NULL){
                if( (strcmp(temp->inputoroutput, tempstring) == 0) ){
                    currentTrFa = temp->currentTF;
                    foundininputs = 1;
                }
            temp = temp->next;
            }
            int foundinoutputs = 0;
            if(foundininputs == 0){
                inputoroutputNode_t *temp2 = headO;
                while(temp2 != NULL){
                    if( strcmp(temp2->inputoroutput, tempstring) == 0){
                        currentTrFa = temp2->currentTF;
                        foundinoutputs = 1;
                    }
                temp2 = temp2->next;
                }
            }
            if(foundinoutputs == 0){
                if(strcmp(tempstring, "0") == 0){
                    currentTrFa = 0;
                }else if(strcmp(tempstring, "1") == 1){
                    currentTrFa = 1;
                }
            }
            //check if passinto is a final output
            inputoroutputNode_t *tempO = headO;
            int isfinaloutput = 0;
            while(tempO != NULL){
                if( (strcmp(tempO->inputoroutput, passinto) == 0) && tempO->finaloutput == 1){
                    isfinaloutput = 1;
                    tempO->currentTF = currentTrFa;
                }
            tempO = tempO->next;
            }
            outputnumber++;
            if(isfinaloutput == 0){
               headO = insert_newinsouts(headO, passinto, outputnumber, 0, currentTrFa, 0, 0); 
            }
            
        }else if( (strcmp(string, "DECODER")) == 0){
            if(decoder == 1){
                //already did this
            }else{
            inputoroutputNode_t *decoderLLinputs = NULL;
            inputoroutputNode_t *decoderLLoutputs = NULL;
            int inputsdecoder;
            fscanf(file, "%d", &inputsdecoder);
            int outputsdecoder = pow(2, inputsdecoder);
            int temporaryinputs = 0;
            while(temporaryinputs < inputsdecoder){
                char tempstring[10];
                int isinHeadI = 0;
                //int isinHeadO = 0;
                fscanf(file, "%s", tempstring);
                inputoroutputNode_t *tempinputsD = headI;
                while(tempinputsD != NULL){
                    if( strcmp(tempinputsD->inputoroutput, tempstring) == 0){
                        isinHeadI = 1;
                        decoderLLinputs = insert_newinsouts(decoderLLinputs, tempinputsD->inputoroutput, tempinputsD->numberassociated, tempinputsD->finaloutput, tempinputsD->currentTF, tempinputsD->decoderindex, 0);
                    }
                tempinputsD = tempinputsD->next;
                }
                if(isinHeadI == 0){ //not in input, check output;
                    inputoroutputNode_t *tempoutputsD = headO;
                    while(tempoutputsD != NULL){
                        if( strcmp(tempstring, tempoutputsD->inputoroutput) == 0){
                            //isinHeadO = 1;
                            decoderLLinputs = insert_newinsouts(decoderLLinputs, tempoutputsD->inputoroutput, tempoutputsD->numberassociated, tempoutputsD->finaloutput, tempoutputsD->currentTF, tempoutputsD->decoderindex, 0);
                        }
                    tempoutputsD = tempoutputsD->next;
                    }
                }
            temporaryinputs++;
            }
            inputoroutputNode_t *tempdecoder = decoderLLinputs;
            int indexforone = 0;
            int counterindex = inputsdecoder-1;
            int power;
            while((tempdecoder != NULL) && (counterindex >=0)){
                power = pow(2, counterindex);
                int temporary = power * (tempdecoder->currentTF);
                indexforone = indexforone + temporary;
            counterindex--;
            tempdecoder = tempdecoder->next;
            }
            indexforone = indexforone+1; //this is index with 1 only
            //printf("  |%d|  \n", indexforone);
            //int tempindexforone = indexforone;
            int tempindex = 1;
            int enteredalready = 0;
            while( tempindex < outputsdecoder+1){
                char tempstringD[10];
                fscanf(file, "%s", tempstringD);
                if( (tempindex == indexforone) && (enteredalready == 0)){
                    //printf("here");
                    enteredalready = 1;
                    decoderLLoutputs = insert_newinsouts(decoderLLoutputs, tempstringD, 0, 0, 1, tempindex, 0);
                }else{
                    decoderLLoutputs = insert_newinsouts(decoderLLoutputs, tempstringD, 0, 0, 0, tempindex, 0);
                }
            tempindex++;
            }
            //printf("here");
            inputoroutputNode_t *tempDecoder = decoderLLoutputs;
            while(tempDecoder != NULL){
                inputoroutputNode_t *tempHeadO = headO;
                int isinhead = 0;
                while(tempHeadO != NULL){
                    if( strcmp(tempDecoder->inputoroutput, tempHeadO->inputoroutput) == 0){
                        isinhead = 1;
                        tempHeadO->currentTF = tempDecoder->currentTF;
                    }
                tempHeadO = tempHeadO->next;
                }
                if(isinhead == 0){
                    headO = insert_newinsouts(headO, tempDecoder->inputoroutput, outputnumber, 0, tempDecoder->currentTF, tempDecoder->decoderindex, 0);
                    outputnumber++;
                }
            tempDecoder = tempDecoder->next;
            }
            free_IOList(decoderLLinputs);
            free_IOList(decoderLLoutputs);
        }
        }else if( (strcmp(string, "MULTIPLEXER")) == 0){
            inputoroutputNode_t *selectorLL = NULL;
            inputoroutputNode_t *inputsMux = NULL;
            int numberofselectors;
            fscanf(file, "%d", &numberofselectors);
            //printf("|%d|", numberofselectors);
            int numinputsMux = pow(2, numberofselectors);
            int temporary = 0;
            int inputsindex = 0;
            while(temporary < numinputsMux){//scanning in inputs
                char tempstring[10];
                fscanf(file, "%s", tempstring);
                int isinheadI = 0;
                int isinheadO = 0;
                inputoroutputNode_t *tempHeadI = headI;
                while(tempHeadI != NULL){
                    if( strcmp(tempstring, tempHeadI->inputoroutput) == 0){
                        inputsMux = insert_newinsouts(inputsMux, tempstring, tempHeadI->numberassociated, tempHeadI->finaloutput, tempHeadI->currentTF, tempHeadI->decoderindex, temporary);
                        inputsindex++;
                        isinheadI = 1;
                    }
                    
                tempHeadI = tempHeadI->next;
                }
                if(isinheadI == 0){
                    inputoroutputNode_t *tempHeadO = headO;
                    while(tempHeadO != NULL){
                        if( strcmp(tempstring, tempHeadO->inputoroutput) == 0){
                            inputsMux = insert_newinsouts(inputsMux, tempstring, tempHeadO->numberassociated, tempHeadO->finaloutput, tempHeadO->currentTF, tempHeadO->decoderindex, temporary);
                            inputsindex++;
                            isinheadO = 1;
                        }
                    tempHeadO = tempHeadO->next;
                    }
                }
                if(isinheadO == 0){
                    if( strcmp(tempstring, "1") == 0) {
                        //printf("here1");
                        inputsMux = insert_newinsouts(inputsMux, tempstring, inputsindex, 0, 1, 0, temporary);
                        inputsindex++;
                        //printf("|%d 1|", inputsindex);
                    }else if(strcmp(tempstring, "0") == 0){
                        //printf("here0");
                        inputsMux = insert_newinsouts(inputsMux, tempstring, inputsindex, 0, 0, 0, temporary);
                        inputsindex++;
                        //printf("|%d 0|", inputsindex);
                    }
                }
            temporary++;
            }
            //printf("  |in%ddex", inputsindex);
            int selecttemp = 0;
            while(selecttemp < numberofselectors){
                char tempstring[10];
                //printf("here3%d", numberofselectors);
                fscanf(file, "%s", tempstring);
                int isinheadI = 0;
                //int isinheadO = 0;
                inputoroutputNode_t *tempI = headI;
                while(tempI != NULL){
                    if(strcmp(tempstring, tempI->inputoroutput) == 0){
                        //printf("hereI");
                        selectorLL = insert_newinsouts(selectorLL, tempstring, tempI->numberassociated, tempI->finaloutput, tempI->currentTF, tempI->decoderindex, 0);
                        isinheadI = 1;
                    }
                tempI = tempI->next;
                }
               // printf("|%dheadI|", isinheadI);
                if(isinheadI == 0){
                    
                    inputoroutputNode_t *tempO = headO;
                    while(tempO != NULL){
                        if(strcmp(tempstring, tempO->inputoroutput) == 0){
                            //printf("hereO");
                            selectorLL = insert_newinsouts(selectorLL, tempstring, tempO->numberassociated, tempO->finaloutput, tempO->currentTF, tempO->decoderindex, 0);
                            //isinheadO = 1;
                        }
                    tempO = tempO->next;
                    }
                }
            selecttemp++;
            }
            int temp2 = numberofselectors-1;
            //printf(" |%d| ", temp2);
            //inputoroutputNode_t *temporaryS = selectorLL;
            inputoroutputNode_t *tempsel = selectorLL;
            int indexforoutput = 0;
            while(tempsel != NULL){
                //printf("here");
                int power = temp2;
                int temps = pow(2, power);
                int currentvalue = temps*(tempsel->currentTF);
                indexforoutput = indexforoutput + currentvalue;
            tempsel = tempsel->next;
            temp2--;
            }
            int tempoutput = indexforoutput;
            //printf("  |%d|  ", tempoutput);
            //int tempo = 0;
            inputoroutputNode_t *tempMux = inputsMux;
            while(tempMux != NULL){
                if(tempMux->muxinputindex == tempoutput){
                    //printf(" |%dMux|  ", tempMux->muxinputindex);
                    char tempstringO[10];
                    fscanf(file, "%s", tempstringO);
                    int isinheadOut = 0;
                    inputoroutputNode_t *tempO2 = headO;
                    while(tempO2 != NULL){
                        if(strcmp(tempstringO, tempO2->inputoroutput) == 0){
                            isinheadOut = 1;
                            tempO2->currentTF = tempMux->currentTF;
                        }
                    tempO2 = tempO2->next;
                    }
                    if(isinheadOut == 0){
                        headO = insert_newinsouts(headO, tempstringO, outputnumber, 0, tempMux->currentTF, 0, 0);
                        outputnumber++;
                    }
                    break;
                }
            tempMux = tempMux->next;
            }

            free_IOList(selectorLL);
            free_IOList(inputsMux);
        }
        }
    }
    for(int i = 0; i < numberofinputs; i++){
        printf("%d ", arrayTruthTable[i]);
        //printf("here");
    }
    //printf("\n");
    printf("| ");
    inputoroutputNode_t *tempOut = headO;
    int outputcounter = 1;
    //printf("%d", numberofoutputs);
    while(tempOut != NULL){
        if(tempOut->finaloutput == 1){
            printf("%d", tempOut->currentTF);
            if(outputcounter < temporaryoutputs){
                printf(" ");
            }
        }
    outputcounter++;
    tempOut = tempOut->next;
    }
    printf("\n");
    rewind(file);
    }
    fclose(file);
    free(arrayTruthTable);
    free_IOList(headI);
    free_IOList(headO);
    //free_IOList(decoderLLinputs);
    //free_IOList(decoderLLoutputs);
    free(string);
}