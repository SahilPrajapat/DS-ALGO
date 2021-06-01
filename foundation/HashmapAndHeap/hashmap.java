import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class hashmap {
    public static void basic() {
        HashMap<String, Integer> map = new HashMap<>();
          map.put("Philipins", 53);
          map.put("India", 34);
          map.put("Russia", 23);
          map.put("USA", 45);
          map.put("Germany", 98);

        //   System.out.println(map);
        for(String keys: map.keySet()){
            System.out.println(keys + " -> " + map.get(keys));
        }

        String key = "USA";
        if(map.containsKey(key))
         System.out.println(map.get(key));
        else
         System.out.println("not Found");
    }

    public static void printHighestFreq(String str){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(map.containsKey(ch)){
                int of = map.get(ch);
                int nf = of + 1;
                map.put(ch, nf);
            }else {
                map.put(ch, 1);
            }
        }

        char mfc = str.charAt(0);
        for(Character key: map.keySet()){
            if(map.get(key) > map.get(mfc))
            mfc = key;
        }
        System.out.println(mfc);
    }

    public static void positionOfAllChar(String str){
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < str.length; i++){
            char ch = str.charAt(i);
            map.putIfAbsent(ch, new ArrayList<>());
            map.get(ch).add(i);
        }

        for(Character ch: map.keySet()){
            System.out.println(ch + " -> " + map.get(ch));
        }
    }


    public static void insertionOfTwoArrayWithoutDuplicate(int[] arr1, int[] arr2){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int ele: arr1){
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }

        for(int ele: arr2){
            if(map.containsKey(ele)){
                System.out.println(ele);
                map.remove(ele);
            }
        }
    }

    public static void insertionOfTwoArrayWithDuplicate(int[] arr1, int[]arr2){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int ele: arr1){
            if(map.containsKey(ele)){
                int of = map.get(ele);
                int nf = of + 1;
                map.put(ele, nf);
            }else {
                map.put(ele, 1);
            }
        }

        for(int ele: arr2){
            if(map.containsKey(ele) && map.get(ele) > 0){
                System.out.println(ele);
                int of = map.get(ele);
                int nf = of - 1;
                map.put(ele, nf);
            }
        }
    }

    public static void longestConsecutiveSequence(int[]arr){
        HashMap<Integer, Boolean> map = new HashMap<>();
        for(int val: arr){
            map.put(val, true);
        }

        for(int val: arr){
            if(map.containsKey(val - 1)){
                map.put(val, false);
            }
        }

        int ml = 0;
        int msp = 0;
        for(int val: arr){
            if(map.get(val) == true){
                int tl = 1;
                int tsp = val;

                while(map.containsKey(tsp + tl)){
                    tl++;
                }

                if(tl > ml){
                    ml = tl;
                    msp = tsp;
                }
            }
        }
        for(int i = 0; i < ml; i++){
            System.out.println(msp + i);
        }
    }



    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        // basic();
        // String str = scn.nextLine();
        // printHighestFreq(str);

        int n1 = scn.nextInt();
        int[]arr1 = new int[n1];
        for(int i = 0; i < arr1.length; i++){
            arr1[i] = scn.nextInt();
        }
        
        
        // int n2 = scn.nextInt();
        // int[]arr2 = new int[n2];
        // for(int i = 0; i < arr2.length; i++){
        //     arr2[i] = scn.nextInt();
        // }

        // insertionOfTwoArrayWithoutDuplicate(arr1, arr2);
        // insertionOfTwoArrayWithDuplicate(arr1, arr2);

        longestConsecutiveSequence(arr1);
    }
    
}
