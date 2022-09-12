public class Small {

    public static void main(String[] args) {
        int count = 1; 
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        smaller = b;
        small = a;
        if (a < b){
            small = b;
            smaller = a;
        }
        else if (b < a){
            small = a;
            smaller = b;
        }
        while (count < args.length){
        c = Double.parseDouble(args[count]);
            if ((c <= a) && (c <= b) && (b <= a)){
                small = b;
                smaller = c;
                a = c;
            }
            else if ((c <= a) && (c <= b) && (a <= b)){
                small = a;
                smaller = c;
                b = c;
            }
            else if ((b <= c) && (c <= a) && (b <= a)){
                small = c;
                smaller = b;
                a = c;
            }
            else if ((b <= a) && (b <= c) && (a <= c)){
                small = a;
                smaller = b;
            }
            else if ((a <= b) && (b <= c) && (a <= c)){
                small = b;
                smaller = a;
            }
            else if ((a <= c) && (c <= b) && (a <= b)){
                small = c;
                smaller = a;
                b = c;
            }
            count++;
        }
        System.out.println(smaller);
        System.out.println(small);
	// WRITE YOUR CODE HERE
    }
}