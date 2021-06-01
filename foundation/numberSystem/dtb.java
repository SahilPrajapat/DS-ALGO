package numberSystem;
import java.util.Scanner;

public class dtb {
    public static Scanner scn = new Scanner(System.in);

    public static long DecimaltoBinary(long n){
        long pow = 1, res=0;
        while(n!=0){
            long rem = n%2;
            n/=2;

            res+=rem*pow;
            pow*=10;
        }

        return res;
    }

    public static void main(String[] args) {
        long n = scn.nextInt();
        System.out.println(DecimaltoBinary(n));
        
    }

    
}
