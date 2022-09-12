package poly;

import java.io.IOException;
import java.nio.channels.FileLockInterruptionException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		if(poly1 == null && poly2 == null){
			return null;
		}
		else{
			if(poly1 == null && poly2 != null){  //poly1 only empty file
				return poly2;
			}else if(poly1 != null && poly2 == null){
				return poly1;
			}else{
				Node temp1 = null;
			Node change = poly1;
			Node temp2 = poly2;
		while(temp2 != null){
			int poly2Degree = temp2.term.degree;
			float poly2Coeff= temp2.term.coeff;
			temp1 = new Node(poly2Coeff, poly2Degree, change);
			change = temp1;
		temp2 = temp2.next;
		}
		int largestDegree = temp1.term.degree;
		Node temp = temp1;
		while(temp != null){
			if(temp.term.degree > largestDegree){
				largestDegree = temp.term.degree;
			}
		temp = temp.next;
		}
		Node newIn = null;
		Node change1 = null;
		float combinedCoeff = 0;
		for(int x = largestDegree; x > -1; x--){
			for(Node tempp = temp1; tempp != null; tempp = tempp.next){
				if(tempp.term.degree == x){
					combinedCoeff = combinedCoeff + tempp.term.coeff;
				}
			} if(combinedCoeff == 0){
			}else{
			newIn = new Node(combinedCoeff, x, change1);
			combinedCoeff = 0;
			change1 = newIn;}
			}
		return newIn;}
		}
		
		//return null;
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		//return null;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		if(poly1 == null || poly2 == null){
			return null;
		}else {
			Node tempLL = null;
		Node newNode = null;
		for(Node temp1 = poly1; temp1 != null; temp1 = temp1.next){
			for(Node temp2 = poly2; temp2 != null; temp2 = temp2.next){
				int tempdegree = temp1.term.degree + temp2.term.degree;
				float tempCoeff = temp1.term.coeff * temp2.term.coeff;
				newNode = new Node(tempCoeff, tempdegree, tempLL);
				tempLL = newNode;

			}
		}
		Node temp = newNode;
		System.out.println();
		int largestDegree = temp.term.degree;
		float largestDegreeCoeff = temp.term.coeff;
		while(temp != null){
			if(temp.term.degree > largestDegree){
				largestDegree = temp.term.degree;
				largestDegreeCoeff = temp.term.coeff;
			}
		temp = temp.next;
		}
		Node temp1 = newNode;
		while(temp1 != null){
			System.out.println(temp1.term.degree + " " + temp1.term.coeff);
		temp1 = temp1.next;
		}
		Node  newIn = null;
		Node change = null;
		float combinedCoeff = 0;
		for(int x = largestDegree; x > -1; x--){
			for(Node temp2 = newNode; temp2 != null; temp2 = temp2.next){
				if(temp2.term.degree == x){
					combinedCoeff = combinedCoeff + temp2.term.coeff;
				}
			}
			if(combinedCoeff == 0){

			}else{
			newIn = new Node(combinedCoeff, x, change);
			//System.out.println(newIn.term.degree + " " + newIn.term.coeff);
			combinedCoeff = 0;
			change = newIn;}
		}
		return newIn;	
		}
		
		
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		//return null;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		Node temp = poly;
		float finalNumber = 0;
		while (temp!= null){
			double tempDegree = (double)temp.term.degree; //converts to double
			double xDouble = (double)x; //converts to double
			double degreeNumber = Math.pow(xDouble, tempDegree); //evaluated exponent
			float degreeFloat = (float) degreeNumber; //converts to float
			float evaluatedNumber = degreeFloat * temp.term.coeff;
			finalNumber = finalNumber + evaluatedNumber;
			temp = temp.next;
		}

		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return finalNumber;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
