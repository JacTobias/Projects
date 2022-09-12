
#include <stdlib.h>
#include <stdio.h>
#include <stddef.h>
#include <string.h>

typedef struct node {
    int *value;
    struct node *next;
} node_t;//this is name of the node

node_t *insert_node(node_t *head, int givenInt, int numberofnodes){ //node_t
    int *intValue = (int*)malloc(sizeof(int));
    if(intValue == NULL){
        return NULL;
    }
    //intValue = &givenInt;
    intValue[0] = givenInt;
    node_t *newNode = (node_t*)malloc(1*sizeof(node_t));
    if(newNode == NULL){
        return NULL;
    }
    newNode->value = intValue;
    newNode->next = NULL;
    //node_t *pointer;
    if(head == NULL){ //first inserted
        //head = newNode;
        //numberofnodes++;
        return newNode;
    }else if(newNode->value[0] < head->value[0]){
        //printf("here1");
        newNode->next = head;
        //head = newNode;
        //numberofnodes++;
        return newNode;
    }else{
        //printf("here2");
        node_t *temp = head;
        node_t *dupcheck = head;
        int isDup = 0; //no dup, 1 if yes dup
        for(dupcheck = head; dupcheck != NULL; dupcheck = dupcheck->next){
            if(dupcheck->value[0] == givenInt){
                isDup = 1;
            }
        }
        if(isDup == 1){
            free(newNode->value);
            free(newNode);
        }else{
        //node_t *current = &temp;
        //temp.next = head;
        //printf("%ls", newNode->value);
        while(temp->next != NULL && (temp->next->value[0] < newNode->value[0])){
            temp = temp->next;
        }
        newNode->next = temp->next;
        temp->next = newNode;
        //numberofnodes++;
        return head;
        }
    }
return head;
}

node_t *delete_node(node_t *head, int givenInt, int numberofnodes){
    if(head == NULL){
        return NULL;
    }
    if(head->value[0] == givenInt){
        node_t *temp = head;
        head = temp->next;
        free(temp->value);
        free(temp);
        //numberofnodes--;
    }
    node_t *pointerDel = head;
    node_t * previousNode;
    while(pointerDel != NULL && pointerDel->value[0] != givenInt){
        previousNode = pointerDel;
        pointerDel = pointerDel->next;
    }
    if(pointerDel == NULL){
        return head;
    }
        previousNode->next = pointerDel->next;
        pointerDel->next = NULL;
        free(pointerDel->value);
        free(pointerDel);
        //numberofnodes--;
    return head;
}

void free_list(node_t *head){
    node_t *freenodet;
    while(head != NULL){
        freenodet = head;
        head = head->next;
        free(freenodet->value);
        free(freenodet);

    }
}

void print_list(int numberofnodes, node_t* head){
    
    node_t *print = head;
    node_t *temp = head;
    for(temp = head; temp != NULL; temp = temp->next){
        numberofnodes++;
    }
    printf("%d :", numberofnodes);
    for(print = head; print != NULL; print = print->next){
        printf(" %d", print->value[0]);
    }
    printf("\n");
}

int main(int argc, char *argv[]){
    /*FILE *file;
    file = fopen(argv[1], "r");
    //printf("%s", argv[1]);
    char inputLine[1000];*/
    char insDel[10];
    int num;
    node_t *head = NULL;
    int numberofnodes = 0;
    //while(fgets(inputLine, 1000, file) != NULL){
    while( (scanf("%s %d", insDel, &num)) !=EOF ){
        //sscanf(inputLine, "%s  %d", insDel, &num);
        if((strcmp(insDel, "i") == 0)){//insert function
            head = insert_node(head, num, numberofnodes);
            print_list(numberofnodes, head);
        }
        else if((strcmp(insDel, "d") == 0)){//delete function, zero for equal strings
            head = delete_node(head, num, numberofnodes);
            print_list(numberofnodes, head);
        }
        //printf("%d", numberofnodes);
    }
    //fclose(file);
    
    //printf("%d", numberofnodes);
    /*node_t *printTemp = head;
    for(printTemp = head; printTemp != NULL; printTemp = printTemp->next){
        printf("%d", printTemp->value[0]);
    }*/
    free_list(head);
    return 0;
}