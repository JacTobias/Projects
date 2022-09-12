public class Randoms {
	public static void main(String[] args){
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		for (int i = 0; i < 10; i++){
		  int num = (int) (Math.random() * (b - a + 1) + a);
		  System.out.println (num);
		}
	 }
	
}