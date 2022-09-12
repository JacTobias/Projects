/*************************************************************************
 *  Compilation:  javac FindDuplicate.java
 *  Execution:    java FindDuplicate
 *
 *  @author: jacqueline-Lillie Tobias, jlt230
 *
 * FindDuplicate that reads n integer arguments from the command line 
 * into an integer array of length n, where each value is between is 1 and n, 
 * and displays true if there are any duplicate values, false otherwise.
 *
 *  % java FindDuplicate 10 8 5 4 1 3 6 7 9
 *  false
 *
 *  % java FindDuplicate 4 5 2 1 
 *  true
 *************************************************************************/

public class FindDuplicate {

    public static void main(String[] args) {
		int n = args.length;
		int[]duplicate = new int[(n)];
		boolean finalBool = false;
		for (int i = 0; i < n; i++){
			duplicate[i] = Integer.parseInt(args[i]);
		}
		for (int a = 0; a < n; a++){
			for (int b = (a + 1); b < n; b++){
				if(duplicate[b] == duplicate[a]){
					finalBool = true;
				}
			}
		}
		System.out.println(finalBool);
	}
}