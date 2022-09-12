public class InOut {
	public static void main(String[] args){
        System.out.println("Input should be: String int double boolean");
        String s = args[0];
        System.out.println("This is a String:" +  s);// + is concatenation
        int i = Integer.parseInt(args[1]);
        System.out.println("This is an integer:" +  i);// + concatenates the int to string
        double d = Double.parseDouble(args[2]);
        System.out.println("This is a double:" +  d);// + concatenates the double to string
        boolean b = Boolean.parseBoolean(args[3]);
        System.out.println("This is a boolean:" +  b);// + concatenates the boolean to string
        boolean result = (i > 0) && (d < 10) && b;
        System.out.println("result will evaluate to true if each of the three parts evaluates to true.");
        System.out.println(result);
    }
}
        
