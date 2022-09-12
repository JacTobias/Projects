public class OriginalPolygon {
    // Returns a new array that is an exact copy of the given array. 
    // The given array is not mutated. 
    public static double[] copy(double[] array){
        double []copyArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            copyArray[i] = array[i];
        }
        return copyArray;
    }
    // Scales the given polygon by the factor alpha. 
   public static void scale(double[] x, double[] y, double alpha) {
    StdDraw.setXscale(-5.0, 5.0);
    StdDraw.setYscale(-5.0, 5.0);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.polygon(x, y);
    double []xScaled = new double[x.length];
    double []yScaled = new double[y.length];
    for (int i = 0; i < x.length; i++){
      xScaled[i] = x[i] * alpha;
    }
    for (int j = 0; j < y.length; j++){
      yScaled[j] = y[j] * alpha;
    }
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.polygon(xScaled, yScaled);
	// WRITE YOUR CODE HERE
    }
    // Translates the given polygon by (dx, dy). 
    public static void translate(double[] x, double[] y, double dx, double dy) {
    StdDraw.setXscale(-5.0, 5.0);
    StdDraw.setYscale(-5.0, 5.0);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.polygon(x, y);
    double []xTranslate = new double[x.length];
    double []yTranslate = new double[y.length];
    for (int i = 0; i < x.length; i++){
        xTranslate[i] = x[i] + dx;
    }
    for (int j = 0; j < y.length; j++){
        yTranslate[j] = y[j] + dy;
    }
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.polygon(xTranslate, yTranslate);
	// WRITE YOUR CODE HERE
    }
    // Rotates the given polygon theta degrees counterclockwise, about the origin. 
   public static void rotate(double[] x, double[] y, double theta) {
    StdDraw.setXscale(-5.0, 5.0);
    StdDraw.setYscale(-5.0, 5.0);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.polygon(x, y);
    double theta1 = Math.toRadians(theta);
    double []xRotate = new double[x.length];
    double []yRotate = new double[y.length];
    double sin = Math.sin(theta);
    double cos = Math.cos(theta);
    for (int i = 0; i < x.length; i++){
        xRotate[i] = (x[i] * cos) - (y[i] * sin);
    }
    for (int j = 0; j < y.length; j++){
        yRotate[j] = (x[j] * sin) + (y[j] * cos);
    }
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.polygon(xRotate, yRotate);
	// WRITE YOUR CODE HERE
    }
    // Tests each of the API methods by directly calling them. 
    public static void main(String[] args) {
        double x[] = {0, 1, 1, 0};
        double y[] = {0, 0, 2, 1};
        double []copy = copy(x);
        double a = 2.0;
        double b = 2.0;
        double c = 1.0;
        double t = Math.toRadians(45.0);
        scale(x, y, a);
        translate(x, y, b, c);
        rotate(x, y, t);
    }
	// WRITE YOUR CODE HERE
}