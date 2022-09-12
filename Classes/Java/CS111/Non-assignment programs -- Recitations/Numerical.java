 
public class Numerical{
    public static void main(String[] args){
        StdOut.print("Enter your fun number: ");   
        int funNum = StdIn.readInt();
        StdOut.println("The number of digits in " + funNum + " is " + numDigits(funNum));
        StdOut.println("The sum of the digits in " + funNum + " is " + sumDigits(funNum));
        StdOut.println("The result of isPrime for " + funNum + " is " + isPrime(funNum));
        StdOut.println("The result of isPerfect for " + funNum + " is " + isPerfect(funNum));
        int reversed = reverseNum(funNum);
        StdOut.println("The result of reverseNum for " + funNum + " is " + reversed);
        int sum = funNum + reversed;
        StdOut.println("The sum of " + funNum + " and " + reversed + " is " + sum);
    }

	/** 
	* counts the digits in this FunNumber value
	* @return the number of the digits 
	*/
	public static int numDigits(int num) {
	   int digs = 0;
       int n = num;
        while(n >  0)
        {
            n = (n/10);
            digs++;
        }
        return digs;
    }
	/** 
	* sums the digits in this FunNumber value
	* @return the sum of the digits 
	*/
	public static int sumDigits(int num)
	{
	    int temp = num;
        int sum = 0;
        while (temp > 0)
        {
            sum += temp%10;
            temp = temp / 10;
        }
        return sum;
	}
	/** 
	* determines if this FunNumber value is prime
	* @return true if this value is prime, false otherwise
	*/
	public static boolean isPrime(int num)
	{
      if (num == 1) return false;
      if (num == 2) return true;
      for(int i = 2; i <= num/2; i++) // could be <= Math.sqrt(num); could be < num
      {
         if (num % i == 0) return false;
      }
      return true;
	}
	/**
	* returns true if this FunNumber value is a ìperfectî number	
        * a perfect number is a number that has a sum of proper factors 
	* equal to the value of this number. 6 is a perfect number 
	* because 6 = 1 + 2 + 3.
	@ return true if this FunNumber value is a perfect number, false 
	* otherwise.
	*/
	public static boolean isPerfect(int num)
	{
		int sum = 0;
        for (int i = 1; i < num; i++)
        {
            if(num % i == 0)
            {
                sum += i;
            }
        }
        /*
        if (num == sum)
          return true;
        else 
          return false;
        */
        return (sum == num);
	}

	/**
	* returns an int that is a number composed of the digits of this 
	* FunNumber's value in reverse order.
	*/
	public static int reverseNum(int num)
	{        
	    int rev = 0, dig;

        int temp = num;
        while(temp > 0)
        {
            dig = temp % 10;
            rev = 10 * rev + dig;
            temp = temp / 10;
        }
        return rev;
	}
	/** 
	 * determines if the sum of the digits of this FunNumber divides into it evenly
	 * @return true if the sum of the digits of this FunNumber
	 * is a divisor of the funNumber. Otherwise returns false
	 */
	 public static boolean sumIsDivisor(int num)
	 {
	     return(num % sumDigits(num) == 0);
	 }	
}
