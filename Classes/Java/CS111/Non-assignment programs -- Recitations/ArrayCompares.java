public class ArrayCompares {
    public static void main(String[] args){
    int []a = {3, 1, 4, 1, 5 };
    int []b = {3, 1, 4, 1};
    int []c = {3, 1, 4, 1, 5};
    int []d = {2, 7, 1, 8, 2};
    int []e = a;

    StdOut.println(eq(a, a));
    StdOut.println(eq(a, b));
    StdOut.println(eq(a, c));
    StdOut.println(eq(a, d));
    StdOut.println(eq(e, a));
    StdOut.println();
    }
    public static boolean eq(int[] array1, int[] array2){
        if(array1.length == array2.length){
            for (int i = 0; i < array1.length; i++){
                if (array1[i] != array2[i]){
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    public static boolean id(int[] array1, int[] array2){
        if (array1 == array2){
            return true;
        }
        return false; 
    }
}