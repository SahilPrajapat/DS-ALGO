import java.io.*;
import java.util.*;

public class test {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt) {
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }
  
    public static int findEdge(int src, int nbr){
        for(int i = 0; i < graph[src].size(); i++){
            Edge e = graph[src].get(i);
            if(e.nbr == nbr)
            return i;
        }
        return -1;
        
    }
   public static int N=0;
    public static void hamintion_dfs(int src, int osrc, boolean[] vis, int vtces, String psf){
        if(vtces == N - 1){
            System.out.print(psf + src);
            int idx = findEdge(src, osrc);
            if(idx != -1)
                {System.out.println("*");
                return;}
            
            System.out.println(".");
        }

        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.nbr])
            hamintion_dfs(e.nbr, osrc, vis, vtces + 1, psf + src);
        }
        vis[src] = false;
    }
    public  static  ArrayList<Edge>[] graph;
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      N=vtces;
      graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      int src = Integer.parseInt(br.readLine());

      // write all your codes here
        boolean[] vis = new boolean[vtces];
        hamintion_dfs(src, src, vis, 0, "");
   }


}
