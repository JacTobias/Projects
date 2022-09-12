public class Shifts {
	public static void main(String[] args){
        int[] a = new int[args.length];
        for(int i = 0; i < args.length; i++){
           a[i] = Integer.parseInt(args[i]);
        } 
        printArray(a);
        //shift left   1 2 3 4 5 becomes 2 3 4 5 1
        //code goes here
        printArray(a); 
        
        //shift right  1 2 3 4 5  becomes 5 1 2 3 4
        //code goes here
        printArray(a);
    }


    
    //method to print the array values of the array parameter arr
    public static void printArray(int[] arr){
        //print array values
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            } 
        System.out.println();

        }
	

	 
	
}