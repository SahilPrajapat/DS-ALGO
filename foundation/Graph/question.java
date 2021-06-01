import java.lang.reflect.Array;
import java.util.*;

public class question {
    
    public boolean isBipartite(int[][]graph, int src, int[]vis){
        // -1 -> undefine, 0 -> red, 1 -> green

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        int color = 0;

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rVtx = que.removeFirst();
                if(vis[rVtx] != -1){
                    if(vis[rVtx] != color)
                        return false;
                    
                        continue;
                }

                vis[rVtx] = color;
                for(int v : graph[rVtx]) {
                    if(vis[v] == -1) {
                        que.addLast(v);
                    }
                }
            }

            color = (color + 1) % 2;
        }

        return true;
    }

    public boolean isBipartite(int[][] graph){
        int N = graph.length;
        int [] vis = new int[N];
        Arrays.fill(vis, -1);

        for(int i = 0; i < N; i++){
            if(vis[i] == -1 && !isBipartite(graph, i, vis))
                return false;
        }

        return true;
    }
}
