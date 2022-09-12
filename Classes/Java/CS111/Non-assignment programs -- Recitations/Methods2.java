public class Methods2 {
	public static void main(String[] args){
        System.out.print("Enter an integer: ");
        int n = StdIn.readInt();
		System.out.println("The factorial of " + n  +  " is " + findFactorial(n));
        System.out.print("Enter the base: ");
        int b = StdIn.readInt();    
        System.out.print("Enter the exponent: ");
        int exp = StdIn.readInt();   
        System.out.println (b + " to the exponent " + exp +  " = " + findPower(b, exp));
    }

    /**
     * n! = n * (n-1) * (n-2) * ... 2 * 1
     * @param num
     * @return  n!
     */
    public static int findFactorial (int num){
        int product = 1;
        for(int i = 1; i <= num; i++){
            product = product * i;
        }
        return product;
    }

    public static int findPower(int base, int exponent){
        int product = 1;
        for(int i = 1; i <= exponent; i++){
            product = product * base;
        
        }
        return product;
    }


	 }