/*************************************************************************
 *  Compilation:  javac CheckDigit.java
 *  Execution:    java CheckDigit 020131452
 *
 *  @author: Jacqueline-Lillie Tobias, jlt230, jlt230@scarletmail.rutgers.edu
 *
 *  Takes a 12 or 13 digit integer as a command line argument, then computes
 *  and displays the check digit
 *
 *  java CheckDigit 048231312622
 *  0
 *
 *  java CheckDigit 9780470458310
 *  0
 * 
 *  java CheckDigit 9780470454310
 *  8
 * 
 *  Print only the check digit character, nothing else.
 *
 *************************************************************************/

public class CheckDigit {

    public static void main (String[] args) {
        long n = Long.parseLong(args[0]);
        int count = 0;
        int sum1 = 0;
        int sum2 = 0;
        while (count <= 12 || count <= 13){
            int digit = (int)(n % 10);
            count++;
            if (count % 2 == 1){ 
                sum1 += digit;
            } 
            if (count % 2 == 0){
                sum2 += digit;
            }
            n = n / 10;
        }
        int rem1 = sum1 % 10;
        int rem2 = sum2 % 10;
        sum2 = rem2;
        int prod1 = sum2 * 3;
        int rem3 = prod1 % 10;
        int sum3 = rem3 + rem1;
        int rem4 = sum3 % 10;
        System.out.println(rem4);
        // WRITE YOUR CODE HERE
    }
}