/*************************************************************************
 *  Compilation:  javac RURottenTomatoes.java
 *  Execution:    java RURottenTomatoes
 *
 *  @author: jacqueline-lillie Tobias, jlt230
 *
 * RURottenTomatoes creates a 2 dimensional array of movie ratings 
 * from the command line arguments and displays the index of the movie 
 * that has the highest sum of ratings.
 *
 *  java RURottenTomatoes 3 2 5 2 3 3 4 1
 *  0
 *************************************************************************/

public class RURottenTomatoes {

    public static void main(String[] args) {
		int r = Integer.parseInt(args[0]);
		int c = Integer.parseInt(args[1]);
		int [][] movie = new int [r][c];
		int count = 2;
		for (int row = 0; row < r; row++){
			for (int column = 0; column < c; column++){
			movie[row][column] = Integer.parseInt(args[(count)]);
				count++;
			}
		}
		int arr[] = new int[c];
		for (int j = 0; j < c; j++){
			int sum = 0;
			for (int i = 0; i < r; i++){
				sum += movie[i][j];
				arr[j] = sum;
			}		
		}
		int largestSum = arr[0];
		int index = 0;
		for (int i = 1; i < arr.length; i++){
			if (arr[i] > arr[0]){
				largestSum = arr[i];
				index = i;
			}
		}
		System.out.println(index);
		// WRITE YOUR CODE HERE
	}
}
