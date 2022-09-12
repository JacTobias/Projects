public class ParkFee {
        /* Admission to an amusement park is charged as follows:
        * Free for children under 5 years old.
        * $20 per person for all others. 
        * The park offers a family discount rate of 10% on the admission fee for any family of 5 or more members. 
        * After the admission fee is determined, the parking fee is calculated:
        *      On Weekends there is a parking fee of $25 but on other days parking is free.
        *
        * Inputs:   number of childen under 5 (int)
        *           number of others (>= 5) (int)
        *           weekend (boolean)
        */
	public static void main(String[] args){
        int children = Integer.parseInt(args[0]);
        int others = Integer.parseInt(args[1]);    
        boolean weekend = Boolean.parseBoolean(args[2]); 
        double totalCost = others *  20.0;
        if (others + children >= 5){
            totalCost = 0.9 * totalCost;
        }
        if (weekend){   //this is equivalent to: if (weekend == true)
            totalCost += 25.00;
        }
        System.out.println("$" + totalCost); 
	 }
	
}