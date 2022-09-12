/*************************************************************************
 *  Compilation:  javac HeartTransplant.java
 *  Execution:    java HeartTransplant < data.txt
 *
 *  @author: Jacqueline Tobias, jlt230, jlt230@scarletmail.rutgers.edu
 *
 *************************************************************************/

public class HeartTransplant {
    /* ------ Instance variables  -------- */ 
    // Person array, each Person is read from the data file
    private Person[] listOfPatients;
    // SurvivabilityByAge array, each rate is read from data file
    private SurvivabilityByAge[] survivabilityByAge;
    // SurvivabilityByCause array, each rate is read from data file
    private SurvivabilityByCause[] survivabilityByCause;
    /* ------ Constructor  -------- */
    /*
     * Initializes all instance variables to null.
     */
    public HeartTransplant() {
        Person[] listOfPatients = null;
        SurvivabilityByAge[] survivabilityByAge = null;
        SurvivabilityByCause[] surviveabilityByCause = null;
        // WRITE YOUR CODE HERE
    }
    /* ------ Methods  -------- */
    /*
     * Inserts Person p into listOfPatients
     * 
     * Returns:  0 if successfully inserts p into array, 
     *          -1 if there is not enough space to insert p into array
     */
    public int addPerson(Person p, int arrayIndex) {
        if (arrayIndex < listOfPatients.length){
            listOfPatients[arrayIndex] = p;
            return 0;
        }
        else {
            return -1;
        }
        // WRITE YOUR CODE HERE
    }

    /*
     * 1) Creates the listOfPatients array with numberOfLines length.
     * 
     * 2) Reads from the command line data file.
     *    File Format: ID, Ethinicity, Gender, Age, Cause, Urgency, State of health
     *    Each line refers to one Person.
     * 
     * 3) Inserts each person from file into listOfPatients
     *    Hint: uses addPerson() method
     * 
     * Returns the number of patients read from file
     */
    public int readPersonsFromFile(int numberOfLines) {
        listOfPatients =  new Person[numberOfLines];
        for (int i = 0; i < numberOfLines; i++){
            int personID = StdIn.readInt();
            int ethnicity = StdIn.readInt();
            int gender = StdIn.readInt();
            int age = StdIn.readInt();
            int cause = StdIn.readInt();
            int urgency = StdIn.readInt();
            int stateOfHealth = StdIn.readInt();
            Person newPerson = new Person(personID, ethnicity, gender, age, cause, urgency, stateOfHealth); 
            listOfPatients[i] = newPerson;
            addPerson(listOfPatients[i], i);
        }
        // WRITE YOUR CODE HERE
        return numberOfLines;
    }

    /*
     * 1) Creates the survivabilityByAge array with numberOfLines length.
     * 
     * 2) Reads from the command line file.
     *    File Format: Age YearsPostTransplant Rate
     *    Each line refers to one survivability rate by age.
     * 
     * 3) Inserts each rate from file into the survivabilityByAge array
     * 
     * Returns the number of survivabilities rates read from file
     */
    public int readSurvivabilityRateByAgeFromFile (int numberOfLines) {
        survivabilityByAge = new SurvivabilityByAge[numberOfLines];
        for (int i = 0; i < numberOfLines; i++){
            int age = StdIn.readInt();
            int yearsPostTransplant = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByAge[i] = new SurvivabilityByAge(age, yearsPostTransplant, rate);
        }
        // WRITE YOUR CODE HERE
        return survivabilityByAge.length;
    }

    /*
     * 1) Creates the survivabilityByCause array with numberOfLines length.
     * 
     * 2) Reads from the command line file.
     *    File Format: Cause YearsPostTransplant Rate
     *    Each line refers to one survivability rate by cause.
     * 
     * 3) Inserts each rate from file into the survivabilityByCause array
     * 
     * Returns the number of survivabilities rates read from file
     */
    public int readSurvivabilityRateByCauseFromFile (int numberOfLines) {
        survivabilityByCause = new SurvivabilityByCause[numberOfLines];
        for (int i = 0; i < numberOfLines; i++){
            int cause = StdIn.readInt();
            int yearsPostTransplant = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByCause[i] = new SurvivabilityByCause(cause, yearsPostTransplant, rate);
        }
        // WRITE YOUR CODE HERE
        return numberOfLines;
    }
    
    /*
     * Returns listOfPatients
     */
    public Person[] getListOfPatients() {
        return listOfPatients;
    } 

    /*
     * Returns survivabilityByAge
     */
    public SurvivabilityByAge[] getSurvivabilityByAge() {
        return survivabilityByAge;
    }

    /*
     * Returns survivabilityByCause
     */
    public SurvivabilityByCause[] getSurvivabilityByCause() {
        return survivabilityByCause;
    }

    /*
     * Returns a Person array in which with every Person that has 
     * age above the parameter age from the listOfPatients array.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of persons with age above the parameter age.
     * 
     * Return null if there is no Person with age above the 
     * parameter age.
     */ 
    public Person[] getPatientsWithAgeAbove(int age) {
        int count = 0;
        for (int i = 0; i < listOfPatients.length; i++){
            int personAge = listOfPatients[i].getAge();
            if (personAge > age){
                count++;
            }
        }
        if (count == 0){
            return null;
        }
        else {
        int countNew = 0;
        Person agePerson[] = new Person[count];
        for (int i = 0; i < listOfPatients.length; i++){
            int perAge = listOfPatients[i].getAge();
            if (perAge > age){
                agePerson[countNew] = listOfPatients[i];
                countNew++;
            }
        } 
        return agePerson;
        }
        // WRITE YOUR CODE HERE
    }
    
    /*
     * Returns a Person array with every Person that has the state of health 
     * equal to the parameter state from the listOfPatients array.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of persons with the state of health equal to the parameter state.
     * 
     * Return null if there is no Person with the state of health 
     * equal to the parameter state.
     */ 
    public Person[] getPatientsByStateOfHealth(int state) {
        int count = 0;
        for (int i = 0; i < listOfPatients.length; i++){
            int stateOfHealth = listOfPatients[i].getStateOfHealth();
            if (stateOfHealth == state){
                count++;
            }
        }
        if (count == 0){
            return null;
        }
        else{
            Person healthPatients[] = new Person[count];
            int countNew = 0;
            for (int i = 0; i < listOfPatients.length; i++){
            int health = listOfPatients[i].getStateOfHealth();
                if (health == state){
                    healthPatients[countNew] = listOfPatients[i];
                    countNew++;
                }
            }
        return healthPatients;
        }
        // WRITE YOUR CODE HERE
    }

    /*
     * Returns a Person array with every person that has the heart 
     * condition cause equal to the parameter cause from the listOfPatients array.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of persons with the heart condition cause equal to the parameter cause.
     * 
     * Return null if there is no Person with the heart condition cause 
     * equal to the parameter cause.
     */ 
    public Person[] getPatientsByHeartConditionCause(int cause) {
       int count = 0;
       for (int i = 0; i < listOfPatients.length; i++){
           int causePerson = listOfPatients[i].getCause();
           if (causePerson == cause){
               count++;
           }
       }
       if (count == 0){
           return null;
       }
       else {
        Person causePerPerson[] = new Person[count];
        int countNew = 0;
        for (int i = 0; i < listOfPatients.length; i++){
            int causeHealth = listOfPatients[i].getCause();
            if (causeHealth == cause){
                causePerPerson[countNew] = listOfPatients[i];
                countNew++;
            }
        }
        return causePerPerson;
       }
        // WRITE YOUR CODE HERE
    }

    /*
     * Assume there are numberOfHearts available for transplantation surgery.
     * Also assume that the hearts are of the same blood type as the
     * persons on the listOfPatients.
     * This method finds a set of persons to be the recepients of these
     * hearts.
     * 
     * The method returns a Person array from the listOfPatients
     * array that have the highest potential for survivability after
     * the transplant. The array size is numberOfHearts.
     * 
     * If numberOfHeartsAvailable is greater than listOfPatients
     * array size all Persons will receive a transplant.
     * 
     * If numberOfHeartsAvailable is smaller than listOfPatients
     * array size find the set of people with the highest
     * potential for survivability.
     * 
     * There is no correct solution, you may come up with any set of
     * persons from the listOfPatients array.
     */ 
    public Person[] match(int numberOfHearts) {
    Person peopleForTransplant[] = new Person[numberOfHearts];
    Person temporaryPeople[] = new Person[listOfPatients.length];
    Person temporaryPeople2[] = new Person[temporaryPeople.length];
    if (numberOfHearts >= listOfPatients.length){
        return listOfPatients;
    }
    else {
    int a = 0;
    for (int i = 0; i < temporaryPeople.length; i++){
        if (listOfPatients[i].getCause() == 3){
        temporaryPeople[a] = listOfPatients[i];
        a++;
        }
    }
    int j = 0;
    for (int i = 0; i < a; i ++){
        int urgency = 8;
        if (temporaryPeople[i].getUrgency() == 8){
        temporaryPeople2[j] = temporaryPeople[i];
        j++;
        }
    }
    int finalNumber = 0;
    if (temporaryPeople2.length > numberOfHearts){
    int min = temporaryPeople2[0].getAge();
    for (int i = 0; i < numberOfHearts; i++){
        peopleForTransplant[i] = temporaryPeople2[i];
    }
    return peopleForTransplant;
    }
    else{
        return temporaryPeople2;
    }
}
 // WRITE YOUR CODE HERE
    }

    /*
     * Client to test the methods you write
     */
    public static void main (String[] args) {

        HeartTransplant ht = new HeartTransplant();

        // read persons from file
        int numberOfLines = StdIn.readInt();
        int numberOfReadings = ht.readPersonsFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " patients read from file.");
 
        // read survivability by age from file
        numberOfLines = StdIn.readInt();
        numberOfReadings = ht.readSurvivabilityRateByAgeFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " survivability rates by age lines read from file.");

        // read survivability by heart condition cause from file        
        numberOfLines = StdIn.readInt();
        numberOfReadings = ht.readSurvivabilityRateByCauseFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " survivability rates by cause lines read from file.");

        ht.getPatientsWithAgeAbove(20);
        ht.getPatientsByStateOfHealth(5);
        ht.getPatientsByHeartConditionCause(0);
        ht.match(3);
        // list all patients
        for (Person p : ht.getListOfPatients()) {
            //StdOut.println(p);
        }

        // list survivability by age rates
        for (SurvivabilityByAge rate : ht.getSurvivabilityByAge()) {
            //StdOut.println(rate);
        }

        // list survivability by cause rates
        for (SurvivabilityByCause rate : ht.getSurvivabilityByCause()) {
           // StdOut.println(rate);
        }

    }
}
