/*************************************************************************
 *  Compilation:  javac Sierpinski.java
 *  Execution:    java Sierpinski
 *
 *  @author: jacqueline-Lillie Tobias, jlt230
 *
 *************************************************************************/

public class Sierpinski {

    // Height of an equilateral triangle whose sides are of the specified length. 
    public static double height(double length) {
        double heightNew = ((Math.sqrt(3)*length)/2);
        return heightNew;
	// WRITE YOUR CODE HERE
    }

    // Draws a filled equilateral triangle whose bottom vertex is (x, y) 
    // of the specified side length. 
    public static void filledTriangle(double x, double y, double length) { 
        double newHeight = height(length);
        double x1 = x; // bottom
        double y1 = y;
        double x2 = x - length/2;  // left
        double y2 = y + newHeight;
        double x3 = x + length/2;  // right
        double y3 = y + newHeight;
        double xNew[] = {x1, x2, x3};
        double yNew[] = {y1, y2, y3};
        StdDraw.filledPolygon(xNew, yNew);
    // WRITE YOUR CODE HERE
    }

    // Draws a Sierpinski triangle of order n, such that the largest filled 
    // triangle has bottom vertex (x, y) and sides of the specified length. 
    public static void sierpinski(int n, double x, double y, double length) {
        System.out.println("n" + n + " " + x + " " + y + " " + length);
        length = length/2;
        double height = height(length);
        if (n == 1){
            filledTriangle(x, y, length);
            return;
        }
        else{
            filledTriangle(x, y, length);
            sierpinski((n - 1), (x - length/2), y, length);
            sierpinski((n - 1), (x + length/2), y, length);
            sierpinski((n - 1), x, (y + (height(length))), length);
        }
	// WRITE YOUR CODE HERE
    }
    // Takes an integer command-line argument n; 
    // draws the outline of an equilateral triangle (pointed upwards) of length 1; 
    // whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and 
    // draws a Sierpinski triangle of order n that fits snugly inside the outline. 
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double heightOutline = (Math.sqrt(3)/2);
        double []x = {0, 1, .5}; 
        double []y = {0, 0, (heightOutline)};
        double length = 1.0;
        StdDraw.polygon(x, y); //outline triangle
    // WRITE YOUR CODE HERE 
        sierpinski(n, 0.5, 0, length);
    }
}
