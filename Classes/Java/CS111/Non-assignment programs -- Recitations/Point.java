
public class Point {

  private double x; //x coordinate of THIS POINT note: PRIVATE
  private double y; //x coordinate of THIS POINT note: PRIVATE 
  // instance variables (^) are private

  /*
  Default Constructor (no parameters) 
  Creates the point (0,0)
  */
 public Point(){
   this.x = 0; // same as x = 0; implys setting this x point to 0
   this.y = 0;
 }
 

  /*
  Constructor (two parameters) 
  Creates the point (xValue,yValue)
  */
 public Point(double xValue, double yValue){
   x = xValue;
   y = yValue;
 }
 

  /*
  Accessor method (does not change attributes)
 returns the x value of THIS point
  */
  public double getX() {
    return this.x;

  }

  /*
  Accessor method (does not change attributes)
 returns the y value of THIS point
  */
  public double getY() {
    return this.y;
  }
  
  /*
  findDistance between this point and (0,0)
  */
  public double findDistanceFromOrigin() {
    double result = Math.sqrt(this.x *this.x + this.y*this.y);
    return result;
  }


  /*
  find distance between this point and other
  Square root of (x2 - x1)^2 + (y2-y1)^2
  precondition: other is of type Point
  */
  public double findDistanceBetween(Point other) {
    double dx = this.x - other.getX(); // other = name of other point
    double dy = this.y - other.getY();
    double distance = Math.sqrt(dx*dx + dy*dy);
    return distance; 
  }

  /*
  find midpoint between this point and other
  precondition: other is of type Point
  */
  public Point findMidPoint (Point other) {
    double midX = (this.x + other.x)/2;
    double midY = (this.y + other.y)/2;
    Point midpoint = new Point(midX, midY);
    return midpoint; 
  }



  /*
  find slope determined by this point and other
  (y2-y1)/(x2-x1)
  precondition: other is of type Point
  */
  public double findSlope(Point other) {
    return (other.y - this.y)/(other.x - this.x)

  }

  //overrides Object toString
  public String toString() {
    String s = "(" + this.x + ", " + this.y + ")";
    return s;

  }
  

  // overrides Object's
  public boolean equals(Object other) {
    if (other instanceof Point) {
      Point p = (Point) other;
      return (p.getX() == this.x && p.y == this.y);
    }
    else return false;

  }
}
