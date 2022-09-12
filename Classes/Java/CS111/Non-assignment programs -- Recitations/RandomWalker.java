/*************************************************************************
 *  Compilation:  javac RandomWalker.java
 *  Execution:    java RandomWalker 10
 *
 *  @author: Jacqueline-Lillie Tobias, jlt230, jlt230@scarletmail.rutgers.edu
 *
 * The program RandomWalker that takes an int command-line argument n
 * and simulates the motion of a random walk for n steps. Print the
 * location at each step (including the starting point), treating the
 * starting point as the origin (0, 0). Also, print the square of the
 * final Euclidean distance from the origin.
 *
 *  % java RandomWalker 10
 * (0,0)
 * (-1,0)
 * (-1,-1)
 * (-1,-2)
 * (-1,-3)
 * (-1,-4)
 * (-1,-5)
 * (0,-5)
 * (-1,-5)
 * (-2,-5)
 * (-2,-4)
 * Squared distance = 20.0
 *
 *************************************************************************/

public class RandomWalker {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int count = 0;
        int x = 0;
        int y = 0;
        System.out.println("(" + x + "," + y + ")");
        double sum1 = 0.0; //x
        double sum2 = 0.0; //y
        while (count < n){
           double prob = (Math.random());
            if (prob <= .25){
                y++;
                sum2 =+ y;
           }
            else if ((prob <= .5) && (prob > .25)){ 
                y--;
                sum2 =- y;
           }            
            if ((prob <= .75) && (prob > .5)){
                x++;
                sum1 =+ x;
            }
            else if ((prob <= 1) && (prob > .75)){
                x--;
                sum1 =- x;
            }
            System.out.println("(" + x + "," + y + ")");
            count++;
        }
            double pow1 = Math.pow(sum1,2);
            double pow2 = Math.pow(sum2,2);
            double sum = pow1 + pow2;
            System.out.println(sum);
	// WRITE YOUR CODE HERE
    }
}
