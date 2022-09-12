public class Stars{
    public static void main(String[] args){
        int nStars = StdIn.readInt();
        rightSide(nStars);
        upsideDown(nStars);
    }
        
    public static void printRow(int nStars){
        if(nStars == 0){
            System.out.println(); // Force a new line
            return; // no more recursion
        }
        System.out.print("*");
        printRow(nStars - 1);
    }
    public static void rightSide(int nStars){
        if (nStars == 0){
            return;
        }
        printRow(nStars);
        rightSide(nStars - 1);
    }
    public static void upsideDown(int nStars){
        if (nStars == 0){
            return;
        }
        printRow(nStars - 1);
        upsideDown(nStars);
    }
}