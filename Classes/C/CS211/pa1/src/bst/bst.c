#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct node {
        int value;
        struct node *left;
        struct node *right;
} node_t;

node_t *search_tree(node_t *root, int givenvalue){
    //printf("here100000");
    if(root == NULL){
        //printf("here");
        printf("absent\n");
        return NULL;
    }else if(root->value == givenvalue){
        //printf("herese");
        printf("present\n");
        return root;
    }else if(givenvalue > root->value){
        return search_tree(root->right, givenvalue);
    }else if(givenvalue < root->value){
        return search_tree(root->left, givenvalue);
    }
return root;
}

node_t *makeNew_node(int givenvalue){
    //printf("herenew");
        //int *valueInt = malloc(sizeof(int));
        node_t *newNode = malloc(1*sizeof(node_t));
        //valueInt = givenvalue;
        newNode->value = givenvalue;
        newNode->left = NULL;
        newNode->right = NULL;
        return newNode;
}

node_t *insert_tree(node_t *root, int givenvalue){
    //printf("hereins");
    if(root == NULL){ // no root
        root = makeNew_node(givenvalue);
        printf("inserted\n");
        return root;

    }else if(root->value == givenvalue){
        printf("not inserted\n");
        return root;
    }else if(givenvalue > root->value){
        root->right = insert_tree(root->right, givenvalue);
    }else{//given value less then root
        root->left = insert_tree(root->left, givenvalue);
    }
return root;
}

node_t *find_maxnode(node_t *root){//initial call: node of leftsubtree
//printf("here");
//printf("%d", root->value);
    if(root == NULL){
        return NULL;
    }else if(root->right != NULL){ //greater then current root
        //printf("%d", root->value);
        return find_maxnode(root->right);
    }
return root;
}

void recursive_print(node_t *root){
    if(root == NULL){
        return;
    }else{
        printf("(");
        recursive_print(root->left);
        printf("%d", root->value);
        recursive_print(root->right);
        printf(")");
    }
}

void print_tree(node_t *root, int numberofnodes){ //inorder, iterative
    //char *stringreturned = malloc( (3*(numberofnodes+1))*sizeof(char) );
    recursive_print(root);
    printf("\n");
}

node_t *delete_tree(node_t *root, int givenvalue){
    //printf("here");
    if(root == NULL){
        printf("absent\n");
        return root;
    }else if(givenvalue > root->value){
        root->right = delete_tree(root->right, givenvalue);
    }else if(givenvalue < root->value){
        root->left = delete_tree(root->left, givenvalue);
    }else if(givenvalue == root->value){
        if( (root->left == NULL) && (root->right == NULL) ){ //no children
        //printf("here2");
            //printf("%d", root->value);
            //print_tree(root, 20);
            //free(root->value);
            free(root);
            printf("deleted\n");
            return NULL;
        }else if( (root->left != NULL) && (root->right != NULL) ){ //has two children
            node_t *tempmaximum = find_maxnode(root->left);
            root->value = tempmaximum->value;
            //printf("%d", root->value);
            root->left = delete_tree(root->left, tempmaximum->value);

            return root;

        }else if(root->left == NULL || root->right == NULL){//one child
        //printf("here6");
            node_t *temp;
            if( (root->left != NULL) && (root->right == NULL) ){ //left child
            //printf("herel");
                temp = root->left;
            }else if( (root->left == NULL) && (root->right != NULL) ){
                //printf("herer");
                temp = root->right;
            }
            //printf("deleted\n");
            //free(root->value);
            free(root);
            printf("deleted\n");
            return temp;
        }
    }
//printf("deleted\n");
return root;
}

int calculate_nodes(node_t *root){
    if(root == NULL){
        return 0;
    }
    return 1 + calculate_nodes(root->left) + calculate_nodes(root->right);
}



void free_tree(node_t *root){
    //printf("freehere");
    if(root == NULL){
        return;
    }else{
        free_tree(root->left);
        free_tree(root->right);
        //free(root->value);
        free(root);
        
    }
}
int main(int argc, char* argv[]){
    int num;
    char input[10];
    node_t *root = NULL;
    int numberofnodes;
    while( (scanf("%s", input)) != EOF){
        if((strcmp(input, "s") == 0)){//search
            scanf("%d", &num);
            if(root != NULL){
                //printf("%d", num);
              search_tree(root, num);  
              
            }else{
                //printf("here0");
                printf("absent\n");
            }    
        }else if((strcmp(input, "d") == 0)){ //delete
            scanf("%d", &num);
            root = delete_tree(root, num);
            if(root != NULL){
            //printf("%d", root->value);
                }
        }else if((strcmp(input, "i") == 0)){ //insert
        scanf("%d", &num);
            root = insert_tree(root, num);
           // printf("%d", root->value);
        }else if((strcmp(input, "p") == 0)){//print
            numberofnodes = calculate_nodes(root);
            //printf("%d", numberofnodes);
            //printf("%d", numberofnodes);
            print_tree(root, numberofnodes);

        }
    }
    //print_tree(root);
    free_tree(root);

}