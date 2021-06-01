import java.util.Scanner;
public class l001Basic{
    public static Scanner scn = new Scanner(System.in);
        
        public static int multiply(int a, int b) {
            int c = a*b;
            return c;
        }

        public static int  add(int a, int b) {
            int c = a+b;
            return c;
        }

        public static int  subtract(int a, int b) {
            int c = a-b;
            return c;
        }

        public static int divide(int a, int b) {
            int c= a/b;
            return c;
        }

        public static void printmessage() {
            System.out.println("See you at midnight");
        }

        public static void printNTime(int n){
            for(int i = 1; i<=10; i++){
                System.out.println( n + "X" + i + "=" + n*i);
            }
        }  
        
        public static void printTableTillM(int m){
            for(int i = 1; i<=m; i++){
                printNTime(i);
                System.out.println("");
            }
        }

        public static boolean isPrime(int n){
            for(int i=2; i*i <=n; i++){
                if(n%i == 0) 
                return false;
            
            }
            return true;
        }
        
        public static void primeNumber(){
            // int t= scn.nextInt();
            // for(int i =1; i<=t; i++){
                int n = scn.nextInt();

                if(isPrime(n)){
                    System.out.println("Prime");
                }else{
                    System.out.println("Not Prime");
                }
            // }
        }

        public static void primeTillN(int n, int m){
            for(int i = n; i<=m; i++){
                if(isPrime(i)) System.out.println(i);
            }
        }
        public static void main(String[] args) {
            
            int a = scn.nextInt();
            // int b = scn.nextInt();

            // System.out.println(multiply(a,b));
            // System.out.println(add(a,b));
            // System.out.println(subtract(a,b));
            // System.out.println(divide(a,b));
            // printmessage();
            // int n = scn.nextInt();
            // printNTime(a);
            printTableTillM(a);

          


            // primeNumber();
            // primeTillN(a, b);
        }



}