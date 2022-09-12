/*************************************************************************
 *  Compilation:  javac ScenarioAnalysis.java
 *  Execution:    java ScenarioAnalysis
 *
 *  @author: jacqueline-lillie tobias, jlt230
 *
 *************************************************************************/
public class ScenarioAnalysis {

    // Instance variables
    private Vehicle[] vehicles;       // all vehicless being analyzed 
    private double  gasPrice;         // price of one gallon of gas in dollars
    private double  electricityPrice; // price of 1 kWh in cents of a dollar, c$/kWh

    /*
     * Constructor
     */ 
    public ScenarioAnalysis ( double gasPrice, double electricityPrice ) {
        this.gasPrice = gasPrice;
        this.electricityPrice = electricityPrice;
    }

    /*
     * Updates the price of gas
     * Call computeCO2EmissionsAndCost() whenever there is an update on gas prices
     */
    public void setGasPrice ( double gasPrice ) {
        this.gasPrice = gasPrice;
        computeCO2EmissionsAndCost();
    }

    /*
     * Returns the gas price
     */ 
    public double getGasPrice () {
        return gasPrice;
    }

    /*
     * Updates the price of electricity
     * Call computeCO2EmissionsAndCost() whenever there is an update on electricity prices
     */
    public void setElectricityPrice ( double electricityPrice ) {
        this.electricityPrice = electricityPrice;
    }
    
    /*
     * Returns electricity price
     */
    public double getElectricityPrice () {
        return electricityPrice;
    }

    /*
     * Computes and updates the CO2 emissions, fuel cost and total cost for each 
     * vehicle in the vehicles array.
     */
    public void computeCO2EmissionsAndCost () {
        for (int i = 0; i < vehicles.length; i++){
        Fuel fuelGet = vehicles[i].getFuel();
        int fuelType = fuelGet.getType();
        Fuel fuelUsage = vehicles[i].getFuel();
        double usage = fuelUsage.getUsage(); //miles for each gallon/ charge (for both)
        Lease leaseGas = vehicles[i].getLease();
        int mileage = leaseGas.getMileageAllowance(); // how many miles on lease per month
        int months = leaseGas.getNumberOfMonths(); //months on lease
        int monthsYears = months/12; // years on lease
        int totalMiles = monthsYears * mileage; //total miles for lease
        if (fuelType == 1){ //GAS
            double numberOfGallons = totalMiles / usage; //how many gallons are used during lease
            double carbonEmission = numberOfGallons * 8.887; //carbon emission
            vehicles[i].setCO2Emission(carbonEmission); // setting carbon emission
            double gasPrice = getGasPrice(); //getting gas price
            double totalGasPrice = gasPrice * numberOfGallons;
            vehicles[i].setFuelCost(totalGasPrice);
            double priceAtSigning = leaseGas.getDueAtSigning(); //price at beginning of lease signing 
            double monthlyCost = leaseGas.getMonthlyCost();
            double totalMonthlyCost = months * monthlyCost; //monthly cost of lease
            double otherCost = vehicles[i].getOtherCost();
            double totalCostAll = totalMonthlyCost + priceAtSigning + totalGasPrice + otherCost;
            vehicles[i].setTotalCost(totalCostAll);
        }
        if (fuelType == 2){ //ELECTRIC
        Fuel power = vehicles[i].getFuel();
        double powerCharge = power.getKWhPerCharge(); //gets electric power per charge
        double numberOfCharge = totalMiles / usage; //how many miles per charge
        double totalCharge = numberOfCharge * powerCharge; //left half of formula
        double powerEmission = 998.4 / 1000;
        double powerEmissionKG = powerEmission * .45;
        double totalPowerEmission = totalCharge * powerEmissionKG; // total emissions from electric
        vehicles[i].setCO2Emission(totalPowerEmission);
        double powerPrice = getElectricityPrice(); //fuel price
        double totalFuelPowerCost = (powerPrice * totalCharge) / 100; //total cost of power fuel
        vehicles[i].setFuelCost(totalFuelPowerCost);
        double dueAtPower = leaseGas.getDueAtSigning(); //initial signing cost
        double monthlyPowerCost = leaseGas.getMonthlyCost(); //monthly cost for lease (line below)
        double totalMonthlyPowerFee = monthlyPowerCost * months;
        double otherCostPower = vehicles[i].getOtherCost(); // other costs
        double totalCostPower = otherCostPower + totalMonthlyPowerFee + totalFuelPowerCost + dueAtPower;
        vehicles[i].setTotalCost(totalCostPower);
        }

    }
        // WRITE YOUR CODE HERE
    }

    /*
     * Returns vehicles array
     */
    public Vehicle[] getVehicles () {
        return vehicles;
    }

    /*
     * Prints all vehicles
     */
    public void printVehicles () {
        for ( Vehicle v : vehicles ) {
           StdOut.println(v);
        }
    }

    /*
     * Populates the array vehicles from file vehicles.txt
     * 
     * File Format: The file can have either gas or electric lines
     * 
     * gas,      name, cash due at signing lease,lease length in months, monthly lease cost, mileage allowance per 12 months, miles per gallon, cost of oil change
     * electric, name, cash due at signing lease,lease length in months, monthly lease cost, mileage allowance per 12 months, miles per kWh/charge, kWh per charge, cost of home charger
     */ 
    public void populateVehicleArray () {
        StdIn.setFile("vehicles.txt");

        // read the number of car models and allocate array
        int numberOfCars = StdIn.readInt();
        vehicles = new Vehicle[numberOfCars];

        for (int i = 0; i < numberOfCars; i++) {
            String fuelType = StdIn.readString();
            String name     = StdIn.readString();

            // Lease information
            double dueAtSigning  = StdIn.readDouble();
            int numberOfMonths = StdIn.readInt();
            double montlyCost  = StdIn.readDouble();
            int mileageAllowance = StdIn.readInt();
            Lease lease = new Lease(dueAtSigning,numberOfMonths,montlyCost,mileageAllowance);

            // Fuel
            double usage = StdIn.readDouble();
            Fuel fuel = null; 
            if ( fuelType.toLowerCase().equals("electric")) {
                double kWhPerCharge = StdIn.readDouble();
                fuel = new Fuel (usage, kWhPerCharge);
            } else {
                fuel = new Fuel (usage);
            }

            // other cost include oil change for gas-powered or home charger for eletric-powered
            double otherCost = StdIn.readDouble();

            // Instatiate the new vehicle
            vehicles[i] = new Vehicle (name, fuel, lease, otherCost);
        }
    }

    /*
     * Test client
     */
    public static void main (String[] args) {
        ScenarioAnalysis sa = new ScenarioAnalysis(3.45, 0.3);
        sa.populateVehicleArray();
        sa.setGasPrice(2.23);           // $2.23 per gallon of gas
        sa.setElectricityPrice(16.14);  // c$16.14 per kWh
        sa.computeCO2EmissionsAndCost();
        sa.printVehicles();
    }
}