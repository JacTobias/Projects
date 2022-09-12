#include <stddef.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main (int argc, char *argv[]){
    FILE *file;
    file = fopen(argv[1], "r");
    //char inputLine[100];
    int matrixsize;
    fscanf(file, "%d", &matrixsize);
    if(matrixsize == 1){
        int *matrix = malloc(matrixsize*sizeof(int));
        fscanf(file, "%d", &matrix[0]);
        int multiple;
        fscanf(file, "%d", &multiple);
        //int *finalmatrix = malloc(matrixsize*sizeof(int));
        int multiplied = 1;
        for(int i = 0; i < multiple; i++){
            multiplied = multiplied*matrix[0];
        }
        matrix[0] = multiplied;
        printf("%d", matrix[0]);
        free(matrix);
    }else{
    int **matrix = malloc( (matrixsize*matrixsize)*sizeof(int));
    //printf("%d", matrixsize);
    int count = 0;
    //int matrixentries;
    int row;
    int columnEnt;
    while(count != 1){
            for(row = 0; row < matrixsize; row++){
                matrix[row] = malloc(matrixsize*sizeof(int));
                for(columnEnt = 0; columnEnt < matrixsize; columnEnt++){
                    fscanf(file, "%d", &matrix[row][columnEnt]);
                    //printf("\n%d", matrix[row][columnEnt]);
                }
            }
            count++;
    }
    int multiple;
    fscanf(file, "%d", &multiple);
    int **matrixMult = malloc( (matrixsize*matrixsize)*sizeof(int) );
    //printf("%d", multiple);
    fclose(file);
    int sum;
    if (multiple > 2){//need to store another matrix
        for(int i = 0; i < matrixsize; i++){ //the first 2 times
                matrixMult[i] = malloc(matrixsize*sizeof(int));
                for(int j = 0; j < matrixsize; j++){
                    sum = 0;
                    for(int k = 0; k < matrixsize; k++){
                        sum = sum + (matrix[i][k] * matrix[k][j]);
                    }
                    matrixMult[i][j] = sum;
                }
        }
        int smaller = multiple - 2;
        //printf("%d", smaller);
        //int sum2;
        int **matrixMulti = malloc( (matrixsize*matrixsize)*sizeof(int) );
        for(int i = 0; i < matrixsize; i++){
            matrixMulti[i] = malloc((matrixsize)*sizeof(int));
        }
        for(int s = 0; s < smaller; s++){//how many more times need to multiple matrix
            for(int i = 0; i < matrixsize; i++){
                    for(int j = 0; j < matrixsize; j++){
                        sum = 0;
                        for(int k = 0; k < matrixsize; k++){
                            sum = sum + (matrixMult[i][k] * matrix[k][j]);
                        }
                        matrixMulti[i][j] = sum;
                    }
            }
        //transfer matrixmulti into matrixmult
            for(int i = 0; i < matrixsize; i++){
                for(int j = 0; j < matrixsize; j++){
                    matrixMult[i][j] = matrixMulti[i][j];
                }
            }
        }
        for(int i = 0; i < matrixsize; i++){
            for(int j = 0; j < matrixsize; j++){
                printf("%d", matrixMulti[i][j]);
                if(j != matrixsize-1){
                    printf(" ");
                }
            }
            printf("\n");
        }
        for(int i = 0; i < matrixsize; i++){
            free(matrixMulti[i]);
        }
        free(matrixMulti);
    }else{ //less than 2
        if( multiple == 0){
            //just print zero???
        }else if(multiple == 1){
            //just print regular matrix
            for(int i = 0;i < matrixsize; i++){
                for(int j = 0; j < matrixsize; j++){
                    printf("%d", matrix[i][j]);
                    if(j != matrixsize-1){
                        printf(" ");
                    }
                }
                printf("\n"); //this is done
            }
        }else{// == 2
            for(int i = 0; i < matrixsize; i++){
                matrixMult[i] = malloc(matrixsize*sizeof(int));
                for(int j = 0; j < matrixsize; j++){
                    sum = 0;
                    for(int k = 0; k < matrixsize; k++){
                        sum = sum + (matrix[i][k] * matrix[k][j]);
                    }
                    matrixMult[i][j] = sum;
                }
            }
            for(int i = 0; i < matrixsize; i++){
                for(int j = 0; j < matrixsize; j++){
                    printf("%d", matrixMult[i][j]);
                    if(j != matrixsize - 1){
                        printf(" ");
                    }
                }
                printf("\n");
            }
        }
    }
    for(int i = 0; i < matrixsize; i++){
        free(matrix[i]);
    }
    free(matrix);
    for(int i = 0; i < matrixsize; i++){
        free(matrixMult[i]);
    }
    free(matrixMult);
    return 0;
}
}