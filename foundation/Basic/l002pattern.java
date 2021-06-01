import java.util.Scanner;
public class l002pattern{
    public static Scanner scn = new Scanner(System.in);

    public static void mirrorTriangle(int row){
        int nst = 1, nsp  = row-1;
        for(int r = 1; r<= row; r++){
            for(int csp = 1; csp<=nsp; csp++){
                System.out.print(" ");
            }

            for(int cst = 1; cst<=nst; cst++){
                System.out.print("*");
            }

            nst++;
            nsp--;
            System.out.println();
        }

    }


    public static void triangle(int row){
        int nst =1;
        for(int r=1; r<=row; r++){
            for(int cst =1; cst<=nst; cst++){
                System.out.print("*\t");
            }

            nst++;
            System.out.println();
        }
    }

    public static void pattern2(int row){
        int nst = row;
        for(int r=1; r<=row; r++){
            for(int cst = 1; cst<=nst; cst++){
                System.out.print("*\t");
            }

            nst--;
            System.out.println();
        }
    }

    public static void pattern3(int row){
        int nst=1, nsp=row-1;
        for(int r=1; r<=row; r++){
            for(int csp = 1; csp<=nsp; csp++){
                System.out.print("\t");
            }

            for(int cst =1; cst<=nst; cst++){
                System.out.print("*\t");
            }

            nsp--;
            nst++;
            System.out.println();
        }
    }

   public static void pattern4(int row){
       int nst=row, nsp=0;
       for(int r=1; r<=row; r++){
           for(int csp=1;csp<=nsp;csp++){
               System.out.print("\t");
           }

           for(int cst=1; cst<=nst; cst++){
               System.out.print("*\t");
           }

           nsp++;
           nst--;
           System.out.println();
       }
   }

   
   public static void pattern7(int row){
       for(int i=1;i<=row;i++){
           for(int j=1;j<=row;j++){
               if(i==j)
               System.out.print("*\t");
               else System.out.print("\t");
           }
           System.out.println();
       }
   }

   public static void pattern8(int row){
       for(int i=1; i<=row; i++){
           for(int j=1; j<=row; j++){
               if(i+j==row+1)
               System.out.print("*\t");
               else System.out.print("\t");
           }
           System.out.println();
       }
   }

   public static void pattern9(int row){
    for(int i=1; i<=row; i++){
        for(int j=1; j<=row; j++){
            if(i+j == row+1|| i==j)
            System.out.print("*\t");
            else System.out.print("\t");
        }
        System.out.println();
    }
   }

   public static void pattern9_1(int row){
        int p= row+1;   
        for(int i =1; i<=row; i++){
            for(int j=1; j<=row; j++){
                if(i+j==p||i+j==p-2||i+j==p+2)
                System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern9_2(int row){
        for(int i=1;i<=row;i++){
            for(int j=1;j<=row;j++){
                if(i==j||j-i==2||i-j==2)
                System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }
  

    public static void pattern5(int row){
        int nst =1, nsp = row/2;
        for(int r=1; r<=row; r++){
        for(int csp =1; csp<=nsp; csp++){
            System.out.print("\t");
        }
            for(int cst=1; cst<=nst;cst++){
                System.out.print("*\t");
            }

            if(r<=row/2){
                nsp--;
                nst+=2;
            }else{
                nst-=2;
                nsp++;
            }
            System.out.println();
        }
    }

    public static void pattern6(int row){
        int nst=(row+1)/2, nsp=1;
        for(int r=1; r<=row; r++){
            for(int cst =1; cst<=nst; cst++){
                System.out.print("*\t");
            }
            for(int csp=1; csp<=nsp; csp++){
                System.out.print("\t");
            }

            for(int cst=1; cst<=nst; cst++){
                System.out.print("*\t");
            }

            if(r<=row/2){
                nsp+=2;
                nst--;
            }else{
                nst++;
                nsp-=2;
            }

            System.out.println();
        }
    }

    public static void pattern15(int row){
        int val=1;
        int nst=1, nsp=row/2;
        for(int r=1; r<=row; r++){
            for(int csp=1;csp<=nsp; csp++){
                System.out.print("\t");

            }
            for(int cst=1;cst<=nst;cst++){
                System.out.print(val+"\t");
                if(cst<=nst/2)val++;
                else val--;
            }
            if(r<=row/2){
                nsp--;
                nst+=2;
                val=r+1;
            }else{
                nst-=2;
                nsp++;
                val=row-r;
            }
            System.out.println();
        }
    }

    public static void pattern16(int row){
        int nst=1, nsp=2*row-3;
        for(int r=1; r<=row; r++){
            int val=1;
            for(int cst=1;cst<=nst;cst++){
                System.out.print(val + "\t");
                val++;
            }
            for(int csp=1; csp<=nsp; csp++){
                System.out.print("\t");
            }
                if(r==row){
                    nst--;
                    val--;
                }
            for(int cst=1;cst<=nst;cst++){
                val--; 
                System.out.print(val + "\t");
            }
            nst++;
            nsp-=2;

            System.out.println();
        }
    }
    public static void main(String[] args){
        int n = scn.nextInt();
        // mirrorTriangle(n);
        // triangle(n);
        pattern16(n);
        // pattern9_1(n);
    }

}