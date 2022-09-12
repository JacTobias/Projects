/*************************************************************************
 *  Compilation:  javac RecursiveAppend.java
 *  Execution:    java RecursiveAppend
 *
 *  @author: Jacqueline-Lillie Tobias, jlt230
 *
 *************************************************************************/

public class RecursiveAppend {

    // Returns the original string appended to the original string n times 
    public static String appendNTimes (String original, int n) {
        if (n == 0){
            return original;
        }
        else{
            return original + appendNTimes(original, (n-1));
        }

	// WRITE YOUR CODE HERE
    }

    public static void main (String[] args) {
    String word = StdIn.readString();
    int n = StdIn.readInt();
    String newWord = appendNTimes(word, n);
    StdOut.println(newWord);
	// WRITE TEST CASES HERE to test your method
    }
}
