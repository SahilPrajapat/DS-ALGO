import java.util.*;
import java.io.*;

public class quesLeetCode {

    public int[] topKFrequent(int[] nums, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int ele : nums)
        map.put(ele, map.getOrDefault(ele, 0) + 1);

        //{val, freq}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->{
            return a[1] - b[1];
        });
        
        for(Integer e: map.keySet()){
            int val = e;
            int freq = map.get(val);

            int[] arr = new int[]{val, freq};
            pq.add(arr);

            if(pq.size() > k)
            pq.remove();
        }

        int[] ans = new int[k];
        int idx = 0;
        while(pq.size() != 0){
            ans[idx++] = pq.remove()[0];
        }

        return ans;
    }

    public static int[] topKFrequent2(int[] nums, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int ele : nums)
        map.put(ele, map.getOrDefault(ele, 0) + 1);

        PriorityQueue<Integer>pq = new PriorityQueue<>((a, b)->{
            return map.get(a) - map.get(b);
        });

        for(Integer e: map.keySet()){
            pq.add(e);
            if(pq.size() > k){
                pq.remove();
            }
        }

        int[] ans = new int[k];
        int idx = 0;
        while(pq.size() != 0){
            ans[idx++] = pq.remove();
        }

        return ans;

    }

    public List<String> topKFrequent(String[] words, int k){
        HashMap<String, Integer> map = new HashMap<>();
        for(String word: words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });

        for(String word : map.keySet()){
            pq.add(word);
            if(pq.size() > k){
                pq.remove();
            }
        }

        int idx = pq.size();
        List<String> ans = new ArrayList<>();
        for(int i =0; i < idx; i++) 
         ans.add("");
        while(pq.size() != 0){
            ans.set(--idx, pq.remove());
        }
        return ans;
    }
    
    public boolean isAnagram(String s, String t){
        if(s.length() != t.length())
            return false;
        
        int[]freq = new int[26];
        for(int i = 0; i < s.length(); i++){
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        for(int i = 0; i < 26; i++){
            if(freq[i] != 0){
                return false;
            }
        }
        return true;
    }

    public static String RLES(String str){     // run length encoding string
        int[] freq = new int[26];
        for(int i = 0; i < str.length(); i++){
            freq[str.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++){
            if(freq[i] != 0){
                sb.append((char)(i + 'a'));
                sb.append(freq[i]);
            }
        }
        return sb.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String s : strs){
            String rles = RLES(s);
            map.putIfAbsent(rles, new ArrayList<>());
            map.get(rles).add(s);
        }
        List<List<String>> ans = new ArrayList<>();
        for(String key : map.keySet())
            ans.add(map.get(key));
        return ans;
    }

    //215
    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void downHeapify(int[] nums, int pi, int li) {
        int maxIdx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (lci <= li && nums[lci] > nums[maxIdx])
            maxIdx = lci;
        if (rci <= li && nums[rci] > nums[maxIdx])
            maxIdx = rci;

        if (pi != maxIdx) {
            swap(nums, pi, maxIdx);
            downHeapify(nums, maxIdx, li);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--)
            downHeapify(nums, i, n - 1);

        int li = n - 1;
        while (k > 1) {
            swap(nums, 0, li--);
            downHeapify(nums, 0, li);
            k--;
        }

        return nums[0];
    }

    //378

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, m = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) ->{
            return matrix[a / m][a % m] - matrix[b / m][b % m];
        });

        for(int i = 0; i < n; i ++)
            pq.add(i * m + 0);

        while(--k > 0){
            int idx = pq.remove();
            int r = idx / m;
            int c = (idx % m);
            if(c + 1 < m)
                pq.add(r * m + c + 1);
        }

        int idx = pq.peek();
        return matrix[idx/m][idx%m];
    
    }

}
