package numberSystem;
import java.util.Scanner;

public class anybaseMultiplication {
    public static Scanner scn = new Scanner(System.in);

    public static long anyBaseAdd1(long n, long m, long b){
        // if (n==0 || m==0) 
        //     return max(n,m);

            long carry =0, pow =1, res =0;
            while (n!=0 || m!=0 || carry!=0) {
                long sum = 0;
                sum+= carry + n%10 + m%10;
                n/=10;
                m/=10;

                long ld = sum%b;
                carry = sum/b;

                res+= ld*pow;
                pow*=10;
            }

            return res;
    }

    public static long multiplyNumberWithDigit(long n, long d, long b, long pow){
        long res = 0, carry =0;
        while (n!=0) {
            long sum = (n%10)*d + carry;
            long lastDigit = sum%b;
            carry = sum/b;

            res+= lastDigit*pow;
            pow*=10;

        }
        return res;
    }

    public static long multiplyTwoNumber(long n, long m,long b){
        long pow =1;
        long res = 0;
        while (m!=0) {
            long lastDigit = m%10;
            long smallAns = multiplyNumberWithDigit(n, lastDigit, b, pow);
            res = anyBaseAdd1(res, smallAns, b);
            pow*=10;
        }

        // System.out.println(multiplyTwoNumber(b, n, m));
        return res;
    }

    public static void main(String[] args){
        long b = scn.nextLong();
        long n = scn.nextLong();
        long m = scn.nextLong();

        System.out.println(multiplyTwoNumber(b, n, m));
        // multiplyTwoNumber(b, n, m);

    }
    
}
