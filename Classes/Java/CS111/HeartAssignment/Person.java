public class  Person {
    
    //Heart Condition Cause Code 
    public static final int CAUSE_VIRAL                = 0;
    public static final int CAUSE_CONGENITAL           = 1;
    public static final int CAUSE_ACCIDENT             = 2;
    public static final int CAUSE_HEART_ARTERY_DISEASE = 3;
    public static final int CAUSE_HEART_MUSCLE_DISEASE = 4; 
    
    // State Of Health Code. Health is independent of heart.
    public static final int HEALTH_POOR                = 5;
    public static final int HEALTH_GOOD                = 6;
    public static final int HEALTH_EXCELLENT           = 7;
    
    // Urgency Code
    public static final int URGENCY_EXTREME            = 8;
    public static final int URGENCY_MODERATE           = 9;

    // Ethnicity Code
    public static final int ETHNICITY_AFRICAN_AMERICAN = 10;
    public static final int ETHNICITY_CAUCASIAN        = 11;
    public static final int ETHNICITY_HISPANIC         = 12;
    
    // Gender Code
    public static final int GENDER_FEMALE               = 13;
    public static final int GENDER_MALE                 = 14;

    // Condition after 3 years post transplant
    public static final int POST_TRANSPLANT_CONDITION_DEAD       = 15;
    public static final int POST_TRANSPLANT_CONDITION_DOING_WELL = 16;
    public static final int POST_TRANSPLANT_CONDITION_EXCELLENT  = 17;

    // Instance variables or fields
    private int id;          // unique identification of the person
    private int ethnicity;
    private int gender;
    private int age;
    private int cause;
    private int stateOfHealth;
    private int urgency;

    /*
     * Constructor
     */
    public Person (int id, int ethnicity, int gender, int age, int cause, int urgency, int stateOfHealth) {
    
        this.id = id;   // unique identification of this person
        this.ethnicity = ethnicity;
        this.gender = gender;
        this.age = age;
        this.cause = cause;
        this.urgency = urgency;
        this.stateOfHealth = stateOfHealth;
    }

    /*
     * Returns the Person's age
     */
    public int getAge() {
        return age;
    }

    /*
     * Returns the Person's ethinicity
     */
    public int getEthnicity() {
        return ethnicity;
    }

    /*
     * Returns the Person's gender
     */
    public int getGender() {
        return gender;
    }

    /*
     * Returns the Person's cause for the heart condition
     */
    public int getCause() {
        return cause;
    }

    /*
     * Returns the Person's urgency for the transplant
     */
    public int getUrgency() {
        return urgency;
    }

    /*
     * Returns the Person's state of health
     */
    public int getStateOfHealth() {
        return stateOfHealth;
    }

    public boolean equals (Object other) {

        if ( other == null || !(other instanceof Person)) {
            return false;
        }
        Person o = (Person) other;

        return (id == o.id && age == o.age && 
        ethnicity == o.ethnicity && 
        gender == o.gender && cause == o.cause &&
        urgency == o.urgency && stateOfHealth == o.stateOfHealth);
    }

    /*
     * Returns the string representation of the Person
     */
    public String toString() {

        String ret = "" + id;
       
        if (ethnicity == ETHNICITY_AFRICAN_AMERICAN) {
            ret += ", african american";
        } else if (ethnicity == ETHNICITY_CAUCASIAN) {
            ret += ", caucasian";
        } else {
            ret += ", hispanic";
        }

        ret += (gender == GENDER_FEMALE) ? ", female" : ", male";

        ret += ", " + age;

        if (cause == CAUSE_ACCIDENT) {
            ret += ", accident";
        } else if ( cause == CAUSE_CONGENITAL) {
            ret += ", congenital";
        } else if ( cause == CAUSE_HEART_ARTERY_DISEASE) {
            ret += ", heart artery disease";
        } else if ( cause == CAUSE_HEART_MUSCLE_DISEASE) {
            ret += ", heart muscle disease";
        } else {
            ret += ", viral";
        }

        if (urgency == URGENCY_EXTREME) {
            ret += ", extreme";
        } else {
            ret += ", moderate";
        }

        if (stateOfHealth == HEALTH_EXCELLENT) {
            ret += ", excellent";
        } else if (stateOfHealth == HEALTH_GOOD) {
            ret += ", good";
        } else {
            ret += ", poor";
        }

        return ret;
    }
}