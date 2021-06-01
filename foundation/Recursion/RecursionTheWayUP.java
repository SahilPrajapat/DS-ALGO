import java.util.*;

public class RecursionTheWayUP {
    public static Scanner scn = new Scanner(System.in);

    // print all the substring but this type youhave to print not get it will wipe the stack after its execution;
    public static void printSS(String str , String ans){
        if(str.length()==0){
            System.out.println(ans);
            return;
        }

        char ch = str.charAt(0);
        String roq = str.substring(1);

        printSS(roq, ans + ch);
        printSS(roq, ans + "");
    }

    static String[] codes = {".,","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};
    public static void printKPC(String str, String ans){
        if(str.length()==0){
            System.out.println(ans);
            return ;
        }
        char ch = str.charAt(0);
        String ros = str.substring(1);

        String codeforch = codes[ch - '0'];
        for(int i = 0; i<codeforch.length(); i++){
            char cho = codeforch.charAt(i);
            printKPC(ros, ans + cho);
        }
    }

    public static void printStairsPath(int n , String path){
        if(n<0){
            return ;
        }
        if(n == 0){
            System.out.println(path);
            return ;
        }
        printStairsPath(n-1, path + "1");
        printStairsPath(n-2, path + "2");
        printStairsPath(n-3, path + "3");
    }

    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf) {
        if(sr > dr || sc > dc){
            return ; 
        }
        if(sr == dr && sc == dc){
            System.out.println(psf);
            return ;
        }
        printMazePaths(sr, sc+1, dr, dc, psf + "h");
        printMazePaths(sr+1, sc, dr, dc, psf + "v");
    }

    public static void printMazeJump(int sr, int sc, int dr, int dc, String psf){

        if(sr == dr && sc == dc){
            System.out.println(psf);
            return ;
        }
        for(int i = 1 ; i <= dc-sc; i++){
        printMazeJump(sr, sc+i, dr, dc, psf + "h" + i);
        }
        for(int i = 1; i <= dr-sr; i++){
        printMazeJump(sr+i, sc, dr, dc, psf + "v" + i);
        }
        for(int i =1; i <=dr-sr && i <=dc-sc ; i++){
        printMazeJump(sr+i, sc+i, dr, dc, psf + "d" + i);
        }

    }

    public static void printPermutation(String qus, String ans){

        if(qus.length()==0){
            System.out.println(ans);
            return ;
        }
        for(int i = 0; i<qus.length(); i++){
            char ch = qus.charAt(i);
            String left = qus.substring(0,i);
            String right = qus.substring(i+1);
            String ros = left + right;
            printPermutation(ros, ans + ch);
        }
    }

    public static void encoding(String qus, String asf){
        if(qus.length()==0){
            System.out.println(asf);
            return;
        }else if(qus.length()==1){
            char ch = qus.charAt(0);
            if(ch == 0){
                return ;
            }else {
                int chv = ch -'0';
                char code = (char)('a' + chv - 1);
                System.out.println(asf + code);
            }
        } else {
            char ch = qus.charAt(0);
            String roq = qus.substring(1);
            if(ch == 0){
                return ;
            }else {
                int chv = ch -'0';
                char code = (char)('a' + chv - 1);
                encoding(roq, asf + code);
            }

            String ch12 = qus.substring(0,2);
            String roq12 = qus.substring(2);

            int chv12v = Integer.parseInt(ch12);
            if(chv12v <= 26){
                char code = (char)('a' + chv12v - 1);
                encoding(roq12, asf + code);
            }
        }
    }

    

    public static void main(String[] args) {
        // String str = scn.next();
        // printSS(str, "");
        // printKPC(str, "");

        // int n = scn.nextInt();
        // printStairsPath(n, "");

        // int n = scn.nextInt();
        // int m = scn.nextInt();
        // // printMazePaths(1, 1, n, m, "");
        // printMazeJump(1, 1, n, m, "");
        
        // String str =scn.next();
        // printPermutation(str, "");

        String str =  scn.next();
        encoding(str , "");


    }
}
