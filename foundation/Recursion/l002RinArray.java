import java.util.Scanner;
public class l002RinArray {
    public static Scanner scn = new Scanner(System.in);

    // print Arry using Recursion
    public static void displayArr(int[]arr, int idx){
        if(idx == arr.length){
            return;
        }

        // System.out.println(arr[idx]);
        displayArr(arr, idx+1);
        System.out.println(arr[idx]);

    }

    public static int maxofArray(int[]arr, int idx){
        if(idx == arr.length -1){
            return arr[idx];
        }
        int misa = maxofArray(arr, idx+1);
        if(misa > arr[idx]){
            return misa;
        }
        else{
            return arr[idx];
        }
    }

    // finding first index of data 
    public static int fod(int[]arr, int idx, int x){
        if(idx == arr.length){
            return -1;
        }
        
        // int fiisa = fod(arr, idx+1, x);  in the first time method first it full fill the stack and then check the data but this is not right method although our answer will come but it required more time.
        // if (arr[idx] == x) {
        //     return idx;
        // }
        // else{
        //     return fiisa;
        // }

        if(arr[idx]==x){
            return idx;
        }else{
            int fiisa = fod(arr, idx+1, x);
            return fiisa;
        }
    }

    public static int lastIndex(int[]arr, int idx, int x){
        if(idx ==arr.length){
            return -1;
        }
        int lisa = lastIndex(arr, idx+1, x);
        if(lisa == -1){
            if(arr[idx]==x){
                return idx;
            }else{
                return -1;
            }
        }else{
            return lisa;
        }
    }

    // print all indices of the array
    public static int[] allIndices(int[]arr, int idx, int x, int fsf){
        if(idx == arr.length){
            return new int[fsf];
        }
        if(arr[idx] == x){
        int[] iar = allIndices(arr, idx+1, x, fsf+1);
        iar[fsf] = idx;
        return iar;
        }
        else{
        int[] iar = allIndices(arr, idx+1, x, fsf);
        return iar;
        }
    }

    public static void main(String[] args){
        int n = scn.nextInt();

        int[] arr = new int[n];
        for( int i =0; i<arr.length; i++){
            arr[i] = scn.nextInt();
        }
        // displayArr(arr, 0);
        // int max = maxofArray(arr, 0);
        // System.out.println(max);

        // int d = scn.nextInt();
        // int fi = fod(arr, 0, d);
        // System.out.println(fi);

        // int x = scn.nextInt();
        // int li = lastIndex(arr, 0, x);
        // System.out.println(li);

        int x = scn.nextInt();
        // int fsf = scn.nextInt();
        int[] ald = allIndices(arr, 0, x, 0);
        // System.out.println(ald);

        for(int i =0; i<ald.length; i++){
            System.out.println(ald[i] +" ");
        }
    }
    
}
