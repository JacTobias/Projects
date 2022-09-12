public class Methods1 {
	public static void main(String[] args){
      System.out.print("How many items in array?" );
      int n = StdIn.readInt();
      //create array
      double[ ] a = create(n);
      //print array elements
      printArray(a);
      System.out.println("Sum of values in the array is: " + findSum(a));
      //fix and add method
      System.out.println("The average of values in array is: " + findAverage(a));
      //fix and add method
      System.out.println("The largest value in the array is: " + findLargest(a));
      double[] aCopy = copyArray(a);
      System.out.println("The copy is ");
      printArray(aCopy);
   }

   public static double[] create(int num) {
      double[] b = new double[num];
      for (int i = 0; i < b.length; i++){
         b[i] = i;
      }
      return b;
   }

   public static double findSum(double[ ] arr) {
      double sum = 0;
      for (int i=0; i < arr.length; i++){
         sum += arr[i];
      }
      return sum;
   }

   public static double findAverage(double[] b){
       double sum = findSum(b);
       return sum/b.length;
   }

   public static double findLargest(double[] arr){
       double large = arr[0];
       for (double elt: arr){
           if (elt > large){
               large = elt;
           }
       }
       return large;
   }

   public static double findSmallest(double[] arr){
      return 0.0;
   }

   public static int findLargestPosition(double[] arr){
      double large = arr[0];
      int position = 0;
      for (int i = 0; i < arr.length; i++){
          if (arr[i] > large){
              large = arr[i];
              position = i;
          }
      }
      return position;
  }
   
  public static double[] reverseArray(double[] arr){
    double[] b = new double[arr.length];
    //loop
    return b;
  }

   public static void printArray(double[] arr){
    for (int i = 0; i < arr.length; i++){
        System.out.print(arr[i] + "   ");
      }   
    System.out.println();
   }

   public static double[] copyArray(double[]arr){
      double[] b = new double[arr.length];
      for(int i = 0; i < arr.length;i++){
         b[i] = arr[i];
      }
      return b;
   }


   
}