public class Hours {
	public static void main(String[] args){
        double hoursWorked=Double.parseDouble(args[0]);
        double ratePerHour=Double.parseDouble(args[1]);
        double totalPay;
        if (hoursWorked < 0 || ratePerHour < 0) {
            System.out.println("Error");
        }
        else {
            totalPay = hoursWorked * ratePerHour;
            System.out.println(totalPay);
        }  
    }
}