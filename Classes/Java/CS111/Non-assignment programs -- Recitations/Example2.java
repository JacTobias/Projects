public class  Example2{

    public static void main(String[] args) {
     double sum = 0;
     int num = 1;
     while (num < 10){
        sum += 1.0/num*num;
        num = num + 1;
     }
    System.out.println(sum);
    }
}