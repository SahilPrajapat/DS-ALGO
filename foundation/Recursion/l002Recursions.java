import java.util.Scanner;
public class l002Recursions {
    public static Scanner scn = new Scanner(System.in);

    public static void decreasing(int n){
        if(n==0){
            return ;
        }
        System.out.println(n);
        decreasing(n-1);
    }

    public static void increasing(int n){
        if(n == 0){
            return ;
        }
        increasing(n-1);
        System.out.println(n);
        
    }

    public static void decreasingIncreasing(int n){
        if(n == 0){
            return ;
        }

        System.out.println(n);
        decreasingIncreasing(n-1);
        System.out.println(n);
    }

    public static int factorial1(int n){
        if(n==1){
            return 1;
        }

        int fnm1 = factorial1(n-1);
        int fn = n*fnm1;
        return fn;
       
    }

    public static int power(int x, int n){

        if(n == 0){
            return 1;
        }

        int xnm1 = power(x, n-1);
        int xn = x*xnm1;
        return xn;
    }
    
    // power function by log

    public static int powerlog(int x, int n){
        if(n == 0){
            return 1;

        }

        int xnb2 = power(x,n/2);
        int xn = xnb2*xnb2;

        if(n%2==1){
            xn = x*xn;
        }
        return xn;
    }

    // Zig zag moment by tree 

    public static void pizz(int n){
        if (n==0) {
            return ;
        }
        System.out.print(n + " ");
        pizz(n-1);
        System.out.print(n + " ");
        pizz(n-1);
        System.out.print(n + " ");
    }

    // Tower of hanoi

    public static void toh(int n, int t1id, int t2id, int t3id){
        if (n==0) {
            return ;
        }
        toh(n-1, t1id, t3id, t2id);  // will print the instructions to move n-1 disks from A to C using B (*)
        System.out.println(n + "[" + t1id + " -> " + t2id + "]");
        toh(n-1, t3id, t2id, t1id);
    }




    public static void main(String[] args) {
        // int n = scn.nextInt();
        // decreasing(n);
        // increasing(n);
        // decreasingIncreasing(n);
        // int f = factorial1(n);
        // System.out.println(f);

        // int x = scn.nextInt();
        // int n = scn.nextInt();

        // int xn = powerlog(x, n);
        // System.out.println(xn);

        // int n = scn.nextInt();
        // pizz(n);

        int n = scn.nextInt();
        int t1d = scn.nextInt();
        int t2d = scn.nextInt();
        int t3d = scn.nextInt();
        toh(n, t1d, t2d, t3d);

    }
}
