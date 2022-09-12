/*************************************************************************
 *  Compilation:  javac TwoSmallest.java
 *  Execution:    java TwoSmallest 1.1 6.9 0.3
 *
 *  @author: Jacqueline-Lillie Tobias, jlt230, jlt230@scarletmail.rutgers.edu
 *
 *  The program TwoSmallest takes a set of double command-line
 *  arguments and prints the smallest and second-smallest number, in that
 *  order. It is possible for the smallest and second-smallest numbers to
 *  be the same (if the sequence contains duplicate numbers).
 *
 *  Note: display one number per line
 *
 *  % java TwoSmallest 17.0 23.0 5.0 1.1 6.9 0.3
 *  0.3
 *  1.1
 *
 *  % java TwoSmallest 17.0 23.0 5.0 1.1 6.9 0.3
 *  0.3
 *  0.3
 *************************************************************************/

public class TwoSmallest {

    public static void main(String[] args) {
        double small;
        double smaller;
        double c;
        int count = 0;
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        small = a;
        smaller = b;
        if (a < b){
            small = b;
            smaller = a;
        }
        else {
            small = a;
            smaller = b;
        }
        while (count < args.length){
        c = Double.parseDouble(args[count]);
            if ((c < a) && (c < b) && (b < a)){
                small = b;
                smaller = c;
                a = c;
            }
            else if ((c < a) && (c < b) && (a < b)){
                small = a;
                smaller = c;
                b = c;
            }
            else if ((b < c) && (c < a) && (b < a)){
                small = c;
                smaller = b;
                a = c;
            }
            else if ((b < a) && (b < c) && (a < c)){
                small = a;
                smaller = b;
            }
            else if ((a < b) && (b < c) && (a < c)){
                small = b;
                smaller = a;
            }
            else if ((a < c) && (c < b) && (a < b)){
                small = c;
                smaller = a;
                b = c;
            }
            count++;
        }
        System.out.println(smaller);
        System.out.println(small);
    }
}