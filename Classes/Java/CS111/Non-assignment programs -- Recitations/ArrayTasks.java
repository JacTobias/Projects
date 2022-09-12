import java.util.Arrays;
public class ArrayTasks{

 public static void main(String[] args)  {
     
    int numValues = args.length;
    System.out.println("Number of values entered is: " + numValues);
    int[] arr = new int[numValues];
    System.out.println("Length of array arr is: " + arr.length);

    //Fill the array arr with values that were input
    for (int i = 0; i < arr.length; i++){
        arr[i]= Integer.parseInt(args[i]);
     }
     
    //traverse arr and print values
    for (int i = 0; i < arr.length; i++){
        System.out.print(arr[i] + " ");
     }
     System.out.println();

    //add values in arr
    /*
     int sum = 0; //double sum = 0.0 then no casting
     for (int i = 0; i < arr.length; i++){
         sum = sum + arr[i];   
     }
     */
    double sum = 0.0;
    for (int elt : arr){
        sum += elt;
    }

     //find average of vales in arr
     double average = (double)sum/arr.length;  
     System.out.println("Average = " + average);
     
     //count the number of values in arr greater than the average
     int count = 0;
     for (int i = 0; i < arr.length; i++){
         if(arr[i] > average){
             count++;
         }
     }
     System.out.println("Number above average is:  " + count);
     
     //largest value entered by user
     int largest = arr[0]; // you could also set largerst to Integer.MIN_VALUE
     for (int i = 1; i < arr.length; i++){
         if(arr[i] > largest){
             largest = arr[i];
         }
     }
    System.out.println("largest is: " + largest);

         //largest value entered by user
         largest = arr[0]; // you could also set largerst to Integer.MIN_VALUE
         for (int elt:arr){
             if(elt > largest){
                 largest = elt;
             }
         }
        System.out.println("largest is: " + largest);

     //traverse in reverse order
     System.out.println("Printing the array backwards");
     for (int i = arr.length-1; i >= 0; i--){
         System.out.print (arr[i] + " "); //all in one line
     }
     System.out.println(); //get off line

    //actually reversing the values in arr
    int len = arr.length;
    int halfWay = len/2;
    for (int i = 0; i < halfWay; i++){
        int temp = arr[i];
        arr[i] = arr[len-i-1];
        arr[len-i-1] = temp; 
    }
    System.out.println("Printing the reversed array");
    //traverse arr and print values (array values are now reversed)
    for (int i = 0; i < arr.length; i++){
         System.out.print(arr[i] + " ");
        }
    System.out.println();

    //Copy an array
    int[] b = new int[arr.length];
    for (int i = 0; i < arr.length; i++){
        b[i] = arr[i];
     }
     System.out.println("Printing the copied array");
  
     for (int i = 0; i < b.length; i++){
          System.out.print(b[i] + " ");
         }
  
     System.out.println();
     System.out.println(arr == b); //Does b reference the SAME array as arr?
     boolean result = true;
     for(int i = 0; i < arr.length;i++){
        if (arr[i] != b[i]){
            result = false;
            break;
            }
        }
        System.out.println(result);
        System.out.println(Arrays.equals(arr,b));

        for(int elt: arr){
            System.out.println(elt);
        }
     }

}       

   	  

