package numberSystem;
import java.util.Scanner;

public class dtab {
    public static Scanner scn = new Scanner(System.in);

    public static long DecimmaltoanyBinary(long n,long b){
            long pow = 1, res =0;
            while (n!=0) {
                long rem = n%b;
                n/=b;

                res+=rem*pow;
                pow*=10;
            }
            return res;
    }

    public static void main(String[] args){
        long n = scn.nextLong();
        long b = scn.nextLong();

        System.out.println(DecimmaltoanyBinary(n,b));
    }
    
}
