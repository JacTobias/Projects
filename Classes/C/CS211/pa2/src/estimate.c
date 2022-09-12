#include <stdlib.h>
#include <stdio.h>

//have product, transpose, and inverse be different methods

double **tranpose_matrix(double** trainingmatrix, int rows, int columns){
    double **transpose = malloc( (columns*rows)*sizeof(double) ); //columns changes to rows, rows change to columns
    for(int i = 0; i < columns; i++){
        transpose[i] = malloc( rows*sizeof(double) );
    }
    for(int i = 0; i < rows; i++){
        for(int j = 0; j < columns; j++){
            transpose[j][i] = trainingmatrix[i][j];
        }
    }
return transpose;
}

double **multiply_together(double **firstmatrix, double **secondmatrix, int rows1, int columns1, int rows2, int columns2){
    double **multipliedtogether = malloc( (rows1*columns2)*sizeof(double) );
    for(int i = 0; i < rows1; i++){
        multipliedtogether[i] = malloc( columns2*sizeof(double) );
    }
    double sum;
    for(int i = 0; i < rows1; i++){
        for(int j = 0; j < columns2; j++){
            sum = 0.0;
            for(int k = 0; k < rows2; k++){
                sum = sum + (firstmatrix[i][k] * secondmatrix[k][j]);
            }
            multipliedtogether[i][j] = sum;
        }
    }
return multipliedtogether;
}

double **make_identity(int rows, int columns){
    double **identity = malloc( (rows*columns)*sizeof(double) );
    for(int i = 0; i < rows; i++){
        identity[i] = malloc( columns*sizeof(double) );
        for(int j = 0; j < columns; j++){
            if(i == j){
                identity[i][j] = 1;
            }else{
                identity[i][j] = 0;
            }
        }
    }
return identity;
}

double **find_inverse(double** invertthis, double** identity, int rows, int columns){
    for(int p = 0; p <= rows-1; p++){
        double f = invertthis[p][p];
        for(int i = 0; i < rows; i++){
            invertthis[p][i] = (invertthis[p][i] / f);
           // printf("%lf ", invertthis[p][i]);
        }
       // printf("\n");
        for(int i = 0; i < rows; i++){
            identity[p][i] = (identity[p][i] / f);
        }
        for(int i = p+1; i <= rows-1; i++){
            //printf("%di= ", i);
            double g = invertthis[i][p];
            //double subtract = 0;
            for(int j = 0; j < rows; j++){
                invertthis[i][j] = invertthis[i][j] - (g*invertthis[p][j]);
            }
            //subtract = 0;
            for(int j = 0; j < rows; j++){
                identity[i][j] = identity[i][j] - (g*identity[p][j]);
                //identity[i][j] = subtract;
            }
        }
    }
    for(int p = rows-1; p >= 0; p--){
        for(int i = p-1; i >= 0; i--){
            double f = invertthis[i][p];
            //double subtract = 0;
            for(int j = 0; j < rows; j++){
                invertthis[i][j] = invertthis[i][j] - (f*invertthis[p][j]);
                //invertthis[i][j] = subtract;
            }
            //subtract = 0;
            for(int j = 0; j < rows; j++){
                identity[i][j] = identity[i][j] - (f*identity[p][j]);
                //identity[i][j] = subtract;
            }
        }
    }

return identity;
}

void free_matrix(double **freethis, int rows){
    for(int i = 0; i < rows; i++){
        free(freethis[i]);
    }
    free(freethis);
}

int main (int argc, char* argv[]){
    FILE *training;
    training = fopen(argv[1], "r");
    int attributes; //columns
    int houses; //rows -1
    char *string = malloc(10*sizeof(char));
    fscanf(training, "%s", string);
    fscanf(training, "%d", &attributes);
    fscanf(training, "%d", &houses);
    double **trainingmatrix = malloc( (houses*(attributes+1))*sizeof(double) );
    double **prices = malloc( (houses)*sizeof(double) );
    for(int i = 0; i < houses; i++){
        prices[i] = malloc(sizeof(double));
    }
    int row = 0;
    int column = 0;
    int count = 0;
    int numberofcolumns = attributes + 1;
    while(count != 1){
        for(row = 0; row < houses; row++){
            trainingmatrix[row] = malloc( (numberofcolumns)*sizeof(double) );
            for(column = 0; column <= (numberofcolumns); column++){
                if(column == 0){
                    trainingmatrix[row][column] = 1;    
                }else if(column == (numberofcolumns)){//attributes+1 = final column in file, equals to prices
                    fscanf(training, "%lf", &prices[row][0]);
                }else{//not first column, start scanning file
                    fscanf(training, "%lf", &trainingmatrix[row][column]);
                }
            }
        }
        count++;
    }
    //second input
    FILE *data;
    data = fopen(argv[2], "r");
    int attributesInput;
    int housesInput;
    char *string2 = malloc(10*sizeof(char));
    fscanf(data, "%s", string2);
    fscanf(data, "%d", &attributesInput);
    fscanf(data, "%d", &housesInput);
    double **inputmatrix = malloc( (housesInput*(1+attributesInput))*sizeof(double) );

    int rowI = 0;
    int columnI = 0;
    int countI = 0;
    int numberofcolumnsI = attributesInput + 1;
    while(countI != 1){
        for(rowI = 0; rowI < housesInput; rowI++){
            inputmatrix[rowI] = malloc( (numberofcolumnsI)*sizeof(double) );
            for(columnI = 0; columnI < (numberofcolumnsI); columnI++){
                if(columnI == 0){
                    inputmatrix[rowI][columnI] = 1;    
                }else{//not first column, start scanning file
                    fscanf(data, "%lf", &inputmatrix[rowI][columnI]);
                }
            }
        }
        countI++;
    }
    //check if same number of attributes
    if(attributes != attributesInput){

    }else{

    //make new malloc matrix for transpose, rows = attributes+1, columns = houses
    //have X right now, need to transpose it
    double **tranposed = tranpose_matrix(trainingmatrix, houses, numberofcolumns);
    int rowsT = numberofcolumns;
    int columnsT = houses;
    //tranposed, now multiple transposed with trainingmatrix
    double **multiplied = multiply_together(tranposed, trainingmatrix, rowsT, columnsT, houses, numberofcolumns);
    int sqrows = rowsT;
    int sqcolumns = numberofcolumns;
    double **identity = make_identity(sqrows, sqcolumns);
    identity = find_inverse(multiplied, identity, sqrows, sqcolumns); //mtrix returned is the inverse matrix from identity
    fclose(training);
    double **multipliedagain = multiply_together(identity, tranposed, sqrows, sqcolumns, rowsT, columnsT);
    double **finalmultiply = multiply_together(multipliedagain, prices, sqrows, columnsT, houses, 1);//this is the W
    free_matrix(multipliedagain, sqrows);
    free_matrix(tranposed, numberofcolumns);
    free_matrix(multiplied, rowsT);
    free_matrix(identity, sqrows);
    free(string);
    free_matrix(trainingmatrix, houses);

    
    
    double **finaloutput = multiply_together(inputmatrix, finalmultiply, housesInput, numberofcolumnsI, sqrows, 1);
    fclose(data);
    for(int i = 0; i < housesInput; i++){
        printf("%.0f\n", finaloutput[i][0]);
    }
    free(string2);
    free_matrix(finalmultiply, sqrows);
    free_matrix(inputmatrix, housesInput);
    free_matrix(finaloutput, housesInput);
    free_matrix(prices, houses);
    return EXIT_SUCCESS;
    }
}