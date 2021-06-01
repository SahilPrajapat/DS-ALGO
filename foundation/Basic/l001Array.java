import java.util.Scanner;

public class l001Array {

    public static Scanner scn = new Scanner(System.in);

    public static void display1(int[] arr){
        for(int i =0; i<=arr.length; i++){
            System.out.print(arr[i]+ " ");
        }
    }

    public static void input(int[] arr){
        for(int i = 0; i<arr.length; i++){
            arr[i] = scn.nextInt();
        }
    }

    public static void test1(){
        int n = scn.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i<n; i++){
            arr[i] = scn.nextInt();
            display1(arr);
        }
    }

    public static int maximum(int[] arr){
        int maxEle = -(int)1e9;
        for(int ele:arr){
            if(ele>maxEle)
            maxEle = ele;
        }
        return maxEle;
    }

    public static int minimum(int[] arr){
        int minEle = (int)1e9;
        for(int ele:arr){
            if(ele<minEle)
            minEle = ele;
        }
          return minEle;
    }

    public static boolean findData(int[] arr, int data){
        for(int ele:arr){
                if(ele == data)
                return true;
        }    
            return false;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // si = starting index, ei = ending index
    public static void reverse(int[] arr, int si, int ei){
        while (si<ei) {
            swap(arr, si++, ei--);
            // 
        }
    }

    public static void addTwoArrays(int[]a, int[]b){
        int n1 = a.length;
        int n2 = b.length;
        int n = Math.max(n1, n2);
        int[] ans = new int[n+1];
        int i = n1-1;
        int j = n2-1;
        int k = ans.length-1;
        int carry = 0;
        while (k>=0) {
            int sum = carry;
            if (i>=0)
                sum+= a[i--];
              if(j>=0)
                sum+= b[j--];
                ans[k--] = sum%10;
                carry = sum/10;
    
        }   
        for (int l=0; l< ans.length; l++){
            if(l==0 && ans[l] ==0)
            continue;
            System.out.println(ans[l]);
        }
    }

    public static void diffTwoArrays(int[]a, int[]b){
        int n1 = a.length;
        int n2 = b.length;
        int n = Math.max(n1, n2);
        int[] ans = new int[n+1];
        int i = n1-1;
        int j = n2-1;
        int k = ans.length-1;
        int borrow = 0;
        while(k>=0){
            int sum = borrow + (j>=0 ? b[j--]:0) - (i>=0 ? a[i--]:0);
            if (sum<0) {
                sum+=10;
                borrow=-1;
            }
            else
            borrow =0;
            ans[k--] = sum;
        } 

        boolean is_you_find_a_non_zero_number = false;
        for(int l=0; l< ans.length; l++){
            if (!is_you_find_a_non_zero_number && ans[l] == 0)
                continue;
                is_you_find_a_non_zero_number = true;
                System.out.println(ans[l]);
        }
    }

    public static void subArrays(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = i; j < arr.length; j++)
            {
                for(int k = i; k<=j; k++)
                {
                    System.out.println(arr[k]+" ");
                }
                System.out.println(" ");
            }
            System.out.println(" ");
        }
    }

    public static int[] prefixSumArrays(int[] arr){
        int [] psum = new int[arr.length+1];
        for(int i = 0; i<arr.length; i++){
            psum[i+1] = psum[i] + arr[i];

        }
        return psum;
    }

    public static void  resolveQuery(){ 
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i =0; i<n; i++)
            arr[i]=scn.nextInt();

            int[] psum = prefixSumArrays(arr);

            int q = scn.nextInt();

            while (q-- > 0) {
                int si,ei;
                si = scn.nextInt();
                ei = scn.nextInt();

                System.out.println(psum[ei+1] - psum[si] + " ");
            }
            
    }






    public static void main(String[] args){
        // test1();
        // int i = scn.nextInt();
        // int[] arr = new int[i];
        // int d = scn.nextInt();
        // // maximum(arr);

        // findData(arr, d);

        int n = scn.nextInt();
        int[] arr = new int[n];
        // reverse(arr, 0, n-1);

        // int n1 = scn.nextInt();
        // int[] a = new int[n1];
        // for(int j = 0; j<a.length; j++){
        //     a[j] = scn.nextInt();
        // }

        // int n2 = scn.nextInt();
        // int[] b = new int[n2];
        // for(int j = 0; j<b.length; j++){
        //     b[j] = scn.nextInt();
        // }
        // addTwoArrays(a, b);

        subArrays(arr);
        // diffTwoArrays(a,b);
    }

}

