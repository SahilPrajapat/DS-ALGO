import java.util.*;

public class l002RinArrayList {
    public static Scanner scn = new Scanner(System.in);

    // Print all the subsequence of the arraylist
    public static ArrayList <String> gss(String str){
        if(str.length() == 0){
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);
        ArrayList<String> rres = gss(ros);

        ArrayList<String> mres = new ArrayList<>();
        for(String rstr : rres){
            mres.add(rstr + "");
        }

        for(String rstr : rres){
            mres.add(rstr + ch);
        }

        return mres;
    }

    // print the keppad words 
    static String[] codes = {".,","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};
    public static ArrayList <String> getKPC(String str){
        if(str.length()==0){
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);
        ArrayList <String> rres = getKPC(ros);

        ArrayList<String> mres = new ArrayList<>();
        String codeforch = codes[ch - '0'];
        for(int i = 0 ;i < codeforch.length(); i++){
            char chcode = codeforch.charAt(i);
            for(String rstr : rres){
                mres.add(rstr + chcode);
            }
        }
        return mres;
    }

    // Stairs/Ladder all paths 
    public static ArrayList <String> getPaths(int n){
        if (n ==0 ) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }else if (n < 0) {
            ArrayList<String> bres = new ArrayList<>();
            return bres;
        }

        ArrayList <String> path1 = getPaths(n-1);
        ArrayList <String> path2 = getPaths(n-2);
        ArrayList <String> path3 = getPaths(n-3);

        ArrayList <String> paths = new ArrayList<>();

        for(String path : path1){
            paths.add(1 + path);
        }
        for(String path : path2){
            paths.add(2 + path);
        }
        for(String path : path3){
            paths.add(3 + path);
        }

        return paths;
    }

    // Maze path

    public static ArrayList<String> getMaze(int sr, int sc, int dr, int dc){

        if(sr==dr && sc==dc){
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        ArrayList<String> hpaths = new ArrayList<>();
        ArrayList<String> vpaths = new ArrayList<>();

        if(sr<dr){
            hpaths = getMaze(sr+1, sc, dr, dc);
        }
        if(sc<dc){
            vpaths = getMaze(sr, sc+1, dr, dc);
        }

        ArrayList<String> path = new ArrayList<>();
        for(String hpath : hpaths){
            path.add('h' + hpath);
        }
        for(String vpath : vpaths){
            path.add('v'+ vpath);
        }

        return path;
    }

    // maze with jump
    public static ArrayList<String> getMazeJump(int sr, int sc, int dr, int dc){
        if(sr==dr && sc==dc){
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        ArrayList<String> paths = new ArrayList<>();

        for(int ms = 1; ms<=dc-sc; ms++){
            ArrayList<String> hpaths = getMazeJump(sr, sc+ms, dr, dc);
            for(String hpath : hpaths){
                paths.add("h" + ms + hpath);
            }
        }
        for(int ms = 1; ms<=dr-sr; ms++){
            ArrayList<String> vpaths = getMazeJump(sr+ms, sc, dr, dc);
            for(String vpath : vpaths){
                paths.add("v" + ms + vpath);
            }
        }
        for(int ms = 1; ms<=dc-sc && ms<dr-sr; ms++){
            ArrayList<String> dpaths = getMazeJump(sr+ms, sc+ms, dr, dc);
            for(String dpath : dpaths){
                paths.add("d" + ms + dpath);
            }
        }

        return paths;
    }




    public static void main(String[] args){
        // String str = scn.next();
        // ArrayList<String> res = gss(str);
        // System.out.println(res);

        // ArrayList <String> words = getKPC(str);
        // System.out.println(words);

        // int n = scn.nextInt();
        // ArrayList<String> pathss = getPaths(n);
        // System.out.println(pathss);

        // int n = scn.nextInt();
        // int m = scn.nextInt();
        // ArrayList<String> pathss = getMaze(1, 1, n, m);
        // System.out.println(pathss);

        int n = scn.nextInt();
        int m = scn.nextInt();
        ArrayList<String> pathss = getMazeJump(1, 1, n, m);
        System.out.println(pathss);
    }
}
