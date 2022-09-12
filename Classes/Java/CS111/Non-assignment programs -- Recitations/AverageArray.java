public class AverageArray{
    public static void main(String[] args)
    {
        int[]a = new int{1,7,3,6,3,2}; // iterate through array
        double sum = 0;
        for(int i = 0; i < a.length; i++){
            sum += a[i];
        }
        System.out.println(sum);
    }
}
