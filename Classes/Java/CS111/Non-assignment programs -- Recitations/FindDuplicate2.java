public class FindDuplicate2 {

    public static void main(String[] args) {
		int[] a = new int[(args.length)];
        int count = 0;
        int currentNum = 0;
        boolean finalBool = false;
		for (int i = 0; i < (args.length); i++){
            currentNum = Integer.parseInt(args[i]);
            count = 0;
			for (int j = 0; j < args.length; j ++){
                if (currentNum == Integer.parseInt(args[j])){
                    count =+ 1;
                }
                if (count == 2){
                    finalBool = true;
                }
            }
        }
        System.out.println(finalBool);
    }
}