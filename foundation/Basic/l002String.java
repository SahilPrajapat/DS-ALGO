import java.util.Scanner;
import java.util.ArrayList;

public class l002String {
    public static Scanner scn = new Scanner(System.in);

    public static boolean isPalindrome(String str){
        int si =0, ei = str.length()-1;

        while (si<ei) {
            if (str.charAt(si++)!= str.charAt(ei--)) 
            return false;
        }
        return true;
    }

    public static void getAllSubstring(String str){
        for(int i =0; i<str.length();i++){
            for(int len = 1; i+len<=str.length(); len++){
                System.out.println(str.substring(i, i+len));
            }
        }
    }

    public static void getAllPalindromicSubstrings(String str){
        int count  = 0;
        for(int i =0; i<str.length(); i++){
            for(int len = 1; i+len<=str.length(); len++){
                String s = str.substring(i, i+len);
                if(isPalindrome(s)){
                    count++;
                System.out.println(s);
                }
            }
        }
        System.out.println(count);
    }

    public static String compression1(String str) {

        String s = str.charAt(0) + "";

        for (int i = 1; i < str.length(); i++) {
            char curr = str.charAt(i);
            char prev = str.charAt(i - 1);

            if (curr != prev) {
                s += curr;
            }
        }
        return s;
    }

    public static String compression2(String str) {

        String s = str.charAt(0) + "";
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            char curr = str.charAt(i);
            char prev = str.charAt(i - 1);

            if (curr == prev) {
                count++;
            } else {
                if (count > 1) {
                    s += count;
                    count = 1;
                }
                s += curr;
            }

        }
        if (count > 1) {
            s += count;
            count = 1;
        }
    return s;
    }

    public static String toggleCase(String str) {
        StringBuilder sb = new StringBuilder(str);

        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                char uch = (char)('A' + ch - 'a');
                sb.setCharAt(i, uch);
            } else {
                if (ch >= 'A' && ch <= 'Z') {
                    char lch = (char)('a' + ch - 'A');
                    sb.setCharAt(i, lch);
                }
            }
        }
        return sb.toString();
    }

    public static void permutation(String str){
        ArrayList<String> ans = new ArrayList<>();
        ans.add("");

        for(int i= 0; i<str.length(); i++){
            char ch = str.charAt(i);
            ArrayList<String> myans = new ArrayList<>();

            for(String s: ans){
                for(int j =0; j<=s.length(); j++){
                    myans.add(s.substring(0, j) + ch + s.substring(j));
                }
            } 
            ans = myans;
        }
        System.out.println(ans);
    }


    public static String differenceoftwostring(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        for (int i = 1; i < str.length(); i++) {
            char curr = str.charAt(i);
            char next = str.charAt(i - 1);
            int gap = curr - next;

            sb.append(gap);
            sb.append(curr);
        }
        return sb.toString();
    }

    public static boolean isPrime(int val){
        for(int i=2; i*i<=val;i++){
            if(val%i == 0)
            return false;
        }
        return true;
    }

    public static void removePrime(ArrayList<Integer>al){
        for(int i =al.size(); i>=0; i--){
           int val = al.get(i);
            if(isPrime(val) == true)
            al.remove(i);
        }
    }

    // Find all the roots or lcm of all the number

    public static ArrayList <Integer> allPrimes(int n){
        ArrayList <Integer> list = new ArrayList<>();
        for(int i=2; i*i <=n; i++){
            if(isPrime(i))
            list.add(i);
        }
        return list;
    }

    public static void powerForm(int num, ArrayList<Integer> list){
        System.out.println(num + "->");
        int idx =0;
        while(idx<list.size()&&num>1){
            int count = 0;
            while (num%list.get(idx)==0 && num>1) {
                num/= list.get(idx);
                count++;
            }
                if (count>0) {
                    System.out.print(list.get(idx)+"^"+count+" ");
                    idx++;
                }
        }        
                    if (num>1) {
                        System.out.print(num + "^" + 1 + " " );
                        System.out.println();
                     }
    }


    public static void exponForm(int[]query) {
        ArrayList <Integer> list = allPrimes(100000);
        // System.out.println(list);

        for(int ele:query){
            powerForm(ele, list);
        }
    }




    public static void main(String[] args){
        // String str = scn.next();
        // getAllPalindromicSubstrings(str);
        // System.out.println(compression1(str));

        // int n = scn.nextInt();
        // ArrayList <Integer> al = new ArrayList<>();
        // for(int i =0; i<=n; i++){
        //     al.add(scn.nextInt());
        // }
        // removePrime(al);
        // System.out.println(al);

        int[] arr = {11,44,77,55,45,34,99,2222,1999,30000,30};
        exponForm(arr);
    }
    
}
