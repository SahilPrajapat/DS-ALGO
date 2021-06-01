package numberSystem;
import java.util.Scanner;

public class abtd {
    public static Scanner scn = new Scanner(System.in);
    public static long AnyBinarytoDecimal(long n, long b){
        long pow =1, res =0;
        while (n!=0) {
            long lastDigit = n%10;
            n/=10;

            res+= lastDigit*pow;
            pow*=b;
        }

        return res;
    }

    public static void main(String[] args){
        long n = scn.nextLong();
        long b = scn.nextLong();

        System.out.println(AnyBinarytoDecimal(n, b));

    }
}
