public class Square{
    public static void main(String[] args){
        double side = sideLength();
        StdOut.println("Side length entered:" + side);
        StdOut.println("Area of square: " + areaSides(side));
        StdOut.println("Perimeter of square: " + perimeterSides(side));
        StdOut.println("Half of area: " + halfArea(side));
        StdOut.println("Half of perimeter: " + halfPerimeter(side));
        double newSide = enlargeSide(side);
        StdOut.println("New length of side: " + enlargeSide(side));
        StdOut.println("New area: " + areaSides(newSide));
        StdOut.println("New perimeter: " + perimeterSides(newSide));
        StdOut.println("Half of new area: " + halfArea(newSide));
        StdOut.println("Half of new perimeter: " + halfPerimeter(newSide));
    }
    public static double sideLength(){
     StdOut.println("Enter a side length: ");
     double side = StdIn.readDouble();
        return side;
    }
    public static double areaSides(double side){
        double area = Math.pow(side, 2);
        return area;
    }
    public static double perimeterSides(double side){
        double perimeter = side * 4;
        return perimeter;
    }
    public static double halfArea(double area){
        double halfA = areaSides(area) / 2;
        return halfA;
    }
    public static double halfPerimeter(double perimeter){
        double halfP = perimeterSides(perimeter) / 2;
        return halfP;
    }
    public static double enlargeSide(double side){
    double newSide = side * 2;
    return newSide;
    }
}