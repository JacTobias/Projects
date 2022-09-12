
public class PointTester {

  public static void main ( String[] args ) {
   
    Point p1 = new Point(3,4);
    Point p2 = new Point();
    Point p3 = new Point (3,4);


    System.out.println("p1 = " + p1);
    System.out.println("p2 = " + p2);
    System.out.println("p3 = " + p3);

    Point midpoint = p1.findMidPoint ( p2 ) ;
    System.out.println ( midpoint + " is midpoint of " + p1 + " and " + p2 );

    System.out.println ( p1.findDistanceFromOrigin() + " is the distance between the origin and " + p1 );

    System.out.println ( p1.findDistanceBetween(p2) + " is the distance between " + p1 + " and " + p2 );

    System.out.println(p2.findSlope (p1) +  " is the slope between " + p2 + " and " + p1);
   
    System.out.println ( p1.equals ( p3) );
    System.out.println ( p1 == p3 );
    
  }

}
