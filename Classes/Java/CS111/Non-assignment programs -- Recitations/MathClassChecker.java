public class MathClassChecker {
  public static void main(String[] args){

    System.out.println("Math.sqrt(54.3) =  " + Math.sqrt(54.3));
    System.out.println("Math.pow(2,6) =   " + Math.pow(2,6));
    //note we COULD use the entire path name for methods in the Math Class.
    System.out.println("java.lang.Math.pow(9, 0.5) =   " + java.lang.Math.pow(9, 0.5));
    System.out.println("java.lang.Math.abs(-10)  =   " +  java.lang.Math.abs(-10));
    System.out.println( "Math.max(54,4)  =  " + Math.max(54,4));
    System.out.println( "Math.min(54,4)  =  " +  Math.min(54,4));
    System.out.println( "Math.ceil(54.4)  =   " + Math.ceil(54.4));
    System.out.println( "Math.floor(54.4)  =  " + Math.floor(54.4));
    System.out.println( "Math.ceil(-54.4)  =   " + Math.ceil(-54.4));
    System.out.println("Math.floor(-54.4)  =   " + Math.floor(-54.4));

  }
}
