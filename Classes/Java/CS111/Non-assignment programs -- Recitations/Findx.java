public class Findx{
    public static void main(String[] args){
        char x[][] = {{'x', 'y', 'x', 'y'}, {'x', 'y', 'y', 'y'}, {'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x'}, {'y', 'x', 'x', 'x'}};
        Stopwatch timer = new Stopwatch();
        StdOut.println(timer.elapsedTime());
        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x[i].length; j++){
                if (x[i][j] == 'x'){
                    if (j == (x[i].length - 1)){
                        System.out.println(j);
                    }
                }
            }
        }
    }
}