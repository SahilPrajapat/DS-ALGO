import java.util.*;
public class timeAndSpaceComp {
    public static Scanner scn = new Scanner(System.in);

    public static void BubbleSort(int[] arr){
        for(int itr = 1; itr <= arr.length - 1; itr++){
            for(int j = 0; j < arr.length - itr; j++){
                if(isSmaller(arr, j+1, j)){
                    Swap(arr, j+1, j);
                }
            }
        }
    }

    public static void SelectionSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int mi = i;

            for(int j = i+1; j < arr.length; j++){
                if(isSmaller(arr, j, mi)){
                    mi = j;
                }
            }
            Swap(arr, i, mi);
        }
    }

    public static void insertionSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            for(int j = i-1; j >=0; j--){
                if(isGreater(arr, j, j+1)){
                    Swap(arr, j, j+1);
                }else{
                    break;
                }
            }
        }
    }

    public static int[] mergeTwoSortedArray(int[] a, int [] b){
        System.out.println("Merging these two arrays ");
        System.out.println("Left array ->");
        print(a);
        System.out.println("Right array ->");
        print(b);
        int[] res = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < a.length && j < b.length){
            if(a[i] < b[j]){
                res[k] = a[i];
                i++;
                k++;
            }else{
                res[k] = b[j];
                j++;
                k++;
            }
        }

        while(i < a.length){
            res[k] = a[i];
            i++;
            k++;
        }
        while(j < b.length){
            res[k] = b[j];
            j++;
            k++;
        }
        return res;
    }

    public static int[] mergeSort(int[] arr, int lo, int hi){
        if(lo == hi){
            int[] ba = new int[1];
            ba[0] = arr[lo];
            return ba;
        }

        int mid = (lo + hi)/2;
        int[] fsh = mergeSort(arr, lo, mid);
        int[] ssh = mergeSort(arr, mid+1, hi);
        int[] fsa = mergeTwoSortedArray(fsh, ssh);

        return fsa;
    }

    public static void partition(int[] arr, int pivot){
        // 0 to i -> <= pivot
        // j to i-1 -> >pivot
        // 0 to end -> unknown
        int i = 0;
        int j = 0;

        while(i < arr.length){
            if(arr[i] > pivot){
                i++;
            }
            else{
                Swap(arr, i, j);
                i++;
                j++;
            }
        }
    }

    public static void quickSort(int[] arr, int lo, int hi){
        if(lo >= hi){
            return;
        }

        int pivot = arr[hi];
        int pi = partition2(arr, pivot, lo, hi);
        quickSort(arr, lo, pi - 1);
        quickSort(arr, pi + 1, hi);
    }

    public static int quickSelect(int[]arr, int lo, int hi, int k){
        int pivot = arr[hi];
        int pi = partition2(arr, pivot, lo, hi);

        if(k > pi){
            return quickSelect(arr, pi + 1, hi, k);
        }else if( k < pi){
            return quickSelect(arr, lo, pi - 1, k);
        }else {
            return pivot;
        }
    }

    public static void countSort(int[] arr, int min, int max){
        int range = max - min +1;
        // make frequency array
        int[] farr = new int[range];

        for(int i = 0; i < arr.length; i++){
            int idx = arr[i] - min;
            farr[idx]++;
        }
        // convert into prefix sum array
        for(int i = 1; i < farr.length; i++){
            farr[i] = farr[i] + farr[i - 1];
        }
        // stable sorting(filling ans array)
        int [] ans = new int[arr.length];

        for(int i = arr.length - 1; i >= 0; i--){
            int val = arr[i];
            int pos = farr[val - min];
            int idx = pos - 1;
            ans[idx] = val;
            farr[val - min]--;
        }
        // filling original array with the help of ans array
        for(int i = 0; i < arr.length; i++){
            arr[i] = ans[i];
        }
    }

    public static void radixSort(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int val:arr){
            if(val > max){
                max = val;
            }
        }

        int exp = 1;
        while(exp <= max){
            countSort2(arr, exp);
            exp = exp*10;
        }
    }


    public static void sortDates(String[] arr){
        countSort3(arr, 1000000, 100, 32);//days
        countSort3(arr, 10000, 100, 13);//months
        countSort3(arr, 1, 1000, 2501);//years
    }

    public static void countSort3(String[] arr,int div, int mod, int range) {
    
         String[] ans = new String[arr.length];
    
            int[] far = new int[range];
            for(int i = 0; i < arr.length; i++){
                far[Integer.parseInt(arr[i],10)/div%mod]++;
            }
    
            for(int i = 1; i < far.length; i++){
                far[i]+= far[i-1];
            }
    
            for(int i = arr.length - 1; i >= 0; i--){
                int pos = far[Integer.parseInt(arr[i],10)/div%mod] - 1;
                ans[pos] = arr[i];
                far[Integer.parseInt(arr[i],10)/div%mod]--;
            }
    
            for(int i = 0; i < arr.length; i++){
                arr[i] = ans[i];
            }
      }

      public static void print2(String[] arr){
          for(int i = 0; i < arr.length; i++){
              System.out.println(arr[i]);
          }
      }

      public static void sort012(int[] arr){
            int i = 0;
            int j = 0;
            int k = arr.length - 1;
    
            while(i <= k){
                if(arr[i] == 1){
                    i++;
                }
                 else if (arr[i] == 0){
                     Swap(arr, i, j);
                     i++;
                     j++;
                    
                }else{
                    Swap(arr,i,k);
                    k--;
                }
            } 
        }

        public static void tagetSumPair(int[] arr, int target){
            Arrays.sort(arr);

            int li = 0;
            int ri = arr.length - 1;

            while(li < ri){
                if(arr[li] + arr[ri] < target){
                    li++;
                }else if(arr[li] + arr[ri] > target){
                    ri--;
                }else{
                    System.out.println(arr[li] + ", " + arr[ri]);
                    li++;
                    ri--;
                }
            }
        }

        public static int findPivot(int[] arr){
            int lo = 0;
            int hi = arr.length - 1;

            while(lo < hi){
                 int mid = (lo+hi)/2;
                if(arr[mid] < arr[hi]){
                    hi = mid;
                }else{
                    lo = mid + 1;
                }
            }
            return arr[hi];
        }
    
   

    // ---------------------------------------------------------------------------------------------------------------------------

    public static void Swap(int[]arr, int i, int j){
        System.out.println("Swapping" + arr[i] + "and" + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp; 
    }
    
    public static boolean isSmaller(int[] arr, int i, int j){
        System.out.println("Comparing" + arr[i] + "and" + arr[j]);
        if(arr[i] < arr[j]){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isGreater(int[] arr, int i, int j){
        System.out.println("Comparing" + arr[i] + "and" + arr[j]);
        if(arr[i] < arr[j]){
            return true;
        }else {
            return false;
        }
    }

    public static void print(int[]arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    public static int partition2(int[] arr, int pivot, int lo, int hi){
        System.out.println("pivot ->" + pivot);
        int i = lo, j = lo;
        while(i <= hi){
            if(arr[i] <= pivot){
                Swap(arr, i, j);
                i++;
                j++;
            } else {
                i++;
            }
        }
        System.out.println("pivot index ->" + (j - 1));
        return (j - 1);
    }

    public static void countSort2(int[]arr, int exp){
        int[] ans = new int[arr.length];

        int[] far = new int[10];
        for(int i = 0; i < arr.length; i++){
            far[arr[i]/exp%10]++;
        }

        for(int i = 1; i < far.length; i++){
            far[i]+= far[i-1];
        }

        for(int i = arr.length - 1; i >= 0; i--){
            int pos = far[arr[i]/exp%10] - 1;
            ans[pos] = arr[i];
            far[arr[i]/exp%10]--;
        }

        for(int i = 0; i < arr.length; i++){
            arr[i] = ans[i];
        }

        System.out.println("After Sorting " +exp + "place ->");
        print(arr);
    }





    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scn.nextInt();
        }
        // // BubbleSort(arr);
        // SelectionSort(arr);
        // print(arr);

        // int m = scn.nextInt();
        // int[] b = new int[m];
        // for(int i = 0; i < m; i++){
        //     b[i] = scn.nextInt();
        // }
        // int[] mergedArray = mergeTwoSortedArray(a, b);
        // print(mergedArray);
        // int [] sa = mergeSort(arr, 0, arr.length-1);
        // System.out.println("Sorted Array ->");
        // print(sa);
        // int pivot = scn.nextInt();
        // partition(arr, pivot);
        // print(arr);

        // quickSort(arr, 0, arr.length - 1);
        // print(arr);

        // int k = scn.nextInt();
        // System.out.println(quickSelect(arr, 0, arr.length - 1, k -1));

        // int n = scn.nextInt();
        // int[] arr = new int[n];
        // int max = Integer.MIN_VALUE;
        // int min = Integer.MAX_VALUE;

        // for(int i = 0; i < n; i++){
        //     arr[i] = scn.nextInt();
        //     max = Math.max(max, arr[i]);
        //     min = Math.min(min, arr[i]);
        // }
        // countSort(arr, min, max);
        // print(arr);

        // radixSort(arr);;
        // print(arr);

        // int n = scn.nextInt();
        // String[] arr = new String[n];
        // for(int i = 0; i < n; i++){
        //     String str = scn.next();
        //     arr[i] = str;
        // }
        // sortDates(arr);
        // print2(arr);

        // int target = scn.nextInt();
        // tagetSumPair(arr, target);

        int pivot = findPivot(arr);
        System.out.println(pivot);
    }    
}
