package numberSystem;
import java.util.Scanner;

public class binarrySubtraction {
    public static Scanner scn = new Scanner(System.in);
    
    public static long decimalSubtraciton(long n, long m){
        // m>n;
        long borrow = 0, pow =1, res =0, sum=0;
        while (n!=0 || m!=0) {
         sum = (m%10 + borrow) - n%10;
            n/=10;
            m/=10;

            if(sum<0){
                sum+=10;
                borrow = -1;
                }else{
                borrow = 0;
                }
            
            res+= sum*pow;
            pow*=10;
         }
            return res;
    }

    public static long binarrySub(long m, long n){
        // m>n
        long borrow =0, pow =1, res = 0, sum =0;
        while (n!=0 || m!=0) {
            sum = (m%10 + borrow) - n%10;
               n/=10;
               m/=10;
   
               if(sum<0){
                   sum+=2;
                   borrow = -1;
                   }else{
                   borrow = 0;
                   }
               
               res+= sum*pow;
               pow*=10;
            }
               return res;
    }

     public static void main(String[] args) {
        long m = scn.nextLong();
        long n = scn.nextLong();
        
        // System.out.println(decimalSubtraciton(n,m));
        System.out.println(binarrySub(n,m));
    }
    
}
