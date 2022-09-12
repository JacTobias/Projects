public class Factorial{
    public static void main(String[] args){
        int n = StdIn.readInt();
        StdOut.println(factorial(n));
    }
    public static int factorial(int n){
        if ((n == 0) || (n == 1)){ //base case
            return 1;
        }
        else{
            return n * factorial(n - 1);
        }
    }
}